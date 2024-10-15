package com.AccesoDatos.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.AccesoDatos.entity.Films;
import com.AccesoDatos.entity.ParkAttractions;
import com.AccesoDatos.entity.Personaje;
import com.AccesoDatos.entity.PersonajeCompartido;
import com.AccesoDatos.entity.PersonajeGuardado;
import com.AccesoDatos.entity.ShortFilms;
import com.AccesoDatos.entity.TvShows;
import com.AccesoDatos.entity.Usuario;
import com.AccesoDatos.entity.VideoGames;
import com.AccesoDatos.service.PersonajeCompartidoService;
import com.AccesoDatos.service.PersonajeGuardadoService;
import com.AccesoDatos.service.PersonajeService;
import com.AccesoDatos.service.PeticionGetExterna;
import com.AccesoDatos.service.UsuarioService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/personajes")
public class PersonajeController {

	private static final String MODEL_SEARCH = "vistabusqueda";

	@Autowired
	private Gson gson;

	@Autowired
	@Qualifier("peticionGetExternaImpl")
	private PeticionGetExterna peticion;

	@Autowired
	@Qualifier("usuarioServiceImpl")
	private UsuarioService usuarioService;

	@Autowired
	@Qualifier("personajeServiceImpl")
	private PersonajeService personajeService;

	@Autowired
	@Qualifier("personajeGuardadoServiceImpl")
	private PersonajeGuardadoService personajeGuardadoService;

	@Autowired
	@Qualifier("personajeCompartidoServiceImpl")
	private PersonajeCompartidoService personajeCompartidoService;

	@GetMapping("/buscar")
	public String createForm(Usuario user, Model model) {		
		model.addAttribute("user", user);
		System.out.println("Mail usuario desde metodo buscar: " + user.getEmail());
		model.addAttribute("personaje", personajeService);
		return MODEL_SEARCH;
	}
	
	//CONTROLADOR PARA BUSCAR PERSONAJES
	@RequestMapping(value = "/buscarpersonaje", method = RequestMethod.GET)
	public String buscarPersonaje(@RequestParam(value = "name") String name, Model model, HttpSession session) {
	    Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
	    System.out.println("Email desde metodo buscarpersonaje: " + usuario);
	    try {
	        String url = "https://api.disneyapi.dev/character?name=" + URLEncoder.encode(name, StandardCharsets.UTF_8.toString());
	        String responseText = peticion.sendGET(url);
	        System.out.println("ResponseText del metodo buscarpersonaje:" + responseText);

	        JsonObject responseJson = gson.fromJson(responseText, JsonObject.class);
	        JsonElement dataElement = responseJson.get("data");
	        
	        //Cuando recibimos varios personajes en la busqueda
	        if (dataElement.isJsonArray()) {
	            JsonArray dataArray = dataElement.getAsJsonArray();
	            List<Personaje> personajes = new ArrayList<>();

	            for (JsonElement element : dataArray) {
	                JsonObject characterObject = element.getAsJsonObject();
	                Personaje personaje = new Personaje();
	                personaje.setName(characterObject.get("name").getAsString());
	                personaje.setImageUrl(characterObject.get("imageUrl").getAsString());
	                personaje.setId(characterObject.get("_id").getAsInt());
	                personaje.setSourceUrl(characterObject.get("sourceUrl").getAsString());

	                List<Films> filmsList = new ArrayList<>();
					JsonArray filmsArray = characterObject.getAsJsonArray("films");
					for (JsonElement filmElement : filmsArray) {
						String filmName = filmElement.getAsString();
						Films film = new Films();
						film.setTitulo(filmName);
						filmsList.add(film);
					}
					personaje.setFilms(filmsList);

					List<ShortFilms> shortFilmList = new ArrayList<>();
					JsonArray shortFilmsArray = characterObject.getAsJsonArray("shortFilms");
					for (JsonElement shortFilmElement : shortFilmsArray) {
						String shortFilmName = shortFilmElement.getAsString();
						ShortFilms shortFilm = new ShortFilms();
						shortFilm.setTitulo(shortFilmName);
						shortFilmList.add(shortFilm);
					}
					personaje.setShortFilms(shortFilmList);

					List<TvShows> tvShowsList = new ArrayList<>();
					JsonArray tvShowsArray = characterObject.getAsJsonArray("tvShows");
					for (JsonElement tvShowElement : tvShowsArray) {
						String tvShowName = tvShowElement.getAsString();
						TvShows tvShow = new TvShows();
						tvShow.setTitulo(tvShowName);
						tvShowsList.add(tvShow);
					}
					personaje.setTvShows(tvShowsList);

					List<ParkAttractions> parkAttractionsList = new ArrayList<>();
					JsonArray parkAttractionsArray = characterObject.getAsJsonArray("parkAttractions");
					for (JsonElement parkAttractionsElement : parkAttractionsArray) {
						String parkAttractionsName = parkAttractionsElement.getAsString();
						ParkAttractions parkAttractions = new ParkAttractions();
						parkAttractions.setNombreParque(parkAttractionsName);
						parkAttractionsList.add(parkAttractions);
					}
					personaje.setParkAttractions(parkAttractionsList);

					List<VideoGames> videoGamesList = new ArrayList<>();
					JsonArray videoGamesArray = characterObject.getAsJsonArray("videoGames");
					for (JsonElement videoGamesElement : videoGamesArray) {
						String videoGamesName = videoGamesElement.getAsString();
						VideoGames videoGames = new VideoGames();
						videoGames.setTitulo(videoGamesName);
						videoGamesList.add(videoGames);
					}
					personaje.setVideoGames(videoGamesList);
					
					//Añadimos el personaje a la lista
	                personajes.add(personaje);
	            }
	            
	            session.setAttribute("personajesBuscados", personajes);
	            model.addAttribute("personajes", personajes);
	            
	         // Manejamos el caso en que la respuesta json es un solo objeto (un solo personaje)
	        } else if (dataElement.isJsonObject()) {
	            JsonObject characterObject = dataElement.getAsJsonObject();
	            Personaje personaje = new Personaje();
	            personaje.setName(characterObject.get("name").getAsString());
	            personaje.setImageUrl(characterObject.get("imageUrl").getAsString());
	            personaje.setId(characterObject.get("_id").getAsInt());
	            personaje.setSourceUrl(characterObject.get("sourceUrl").getAsString());

	            List<Films> filmsList = new ArrayList<>();
				JsonArray filmsArray = characterObject.getAsJsonArray("films");
				for (JsonElement filmElement : filmsArray) {
					String filmName = filmElement.getAsString();
					Films film = new Films();
					film.setTitulo(filmName);
					filmsList.add(film);
				}
				personaje.setFilms(filmsList);

				List<ShortFilms> shortFilmList = new ArrayList<>();
				JsonArray shortFilmsArray = characterObject.getAsJsonArray("shortFilms");
				for (JsonElement shortFilmElement : shortFilmsArray) {
					String shortFilmName = shortFilmElement.getAsString();
					ShortFilms shortFilm = new ShortFilms();
					shortFilm.setTitulo(shortFilmName);
					shortFilmList.add(shortFilm);
				}
				personaje.setShortFilms(shortFilmList);

				List<TvShows> tvShowsList = new ArrayList<>();
				JsonArray tvShowsArray = characterObject.getAsJsonArray("tvShows");
				for (JsonElement tvShowElement : tvShowsArray) {
					String tvShowName = tvShowElement.getAsString();
					TvShows tvShow = new TvShows();
					tvShow.setTitulo(tvShowName);
					tvShowsList.add(tvShow);
				}
				personaje.setTvShows(tvShowsList);

				List<ParkAttractions> parkAttractionsList = new ArrayList<>();
				JsonArray parkAttractionsArray = characterObject.getAsJsonArray("parkAttractions");
				for (JsonElement parkAttractionsElement : parkAttractionsArray) {
					String parkAttractionsName = parkAttractionsElement.getAsString();
					ParkAttractions parkAttractions = new ParkAttractions();
					parkAttractions.setNombreParque(parkAttractionsName);
					parkAttractionsList.add(parkAttractions);
				}
				personaje.setParkAttractions(parkAttractionsList);

				List<VideoGames> videoGamesList = new ArrayList<>();
				JsonArray videoGamesArray = characterObject.getAsJsonArray("videoGames");
				for (JsonElement videoGamesElement : videoGamesArray) {
					String videoGamesName = videoGamesElement.getAsString();
					VideoGames videoGames = new VideoGames();
					videoGames.setTitulo(videoGamesName);
					videoGamesList.add(videoGames);
				}
				personaje.setVideoGames(videoGamesList);

	            List<Personaje> personajes = new ArrayList<>();
	            personajes.add(personaje);

	            session.setAttribute("personajesBuscados", personajes);
	            model.addAttribute("personajes", personajes);
	        } else {
	            System.out.println("Ningún personaje encontrado.");
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return "vistabusqueda";
	}
	
	//CONTROLADOR PARA GUARDAR EL PERSONAJE
	@GetMapping("/guardarpersonaje")
	public String guardarPersonaje(@RequestParam("id") int characterId, Model model, HttpSession session) {
	    Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
	    System.out.println("Usuario desde el metodo guardarpersonaje: " + usuario);
	    if (usuario == null) {
	        return "redirect:/user/registro";
	    } else {
	        try {
	            // Realizamos la solicitud a la API para obtener el personaje por su ID
	            String url = "https://api.disneyapi.dev/character/" + characterId;
	            String responseText = peticion.sendGET(url);
	            System.out.println("ResponseText del metodo guardarpersonaje:" + responseText);

	            JsonObject responseJson = gson.fromJson(responseText, JsonObject.class);
	            JsonElement characterElement = responseJson.get("data");

	            // Verificamos si la respuesta contiene el campo "data"
	            if (characterElement != null && characterElement.isJsonObject()) {
	                guardarPersonajeFromJson(characterElement.getAsJsonObject(), usuario, model);
	                return "forward:buscarpersonaje";
	            } else {
	                // Si la respuesta de la API no contiene el campo "data"
	                System.out.println("La respuesta de la API no contiene el campo 'data'.");
	                return "redirect:/buscarpersonaje?error=Error en la respuesta de la API";
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return "redirect:buscarpersonaje";
	    }
	}

	private void guardarPersonajeFromJson(JsonElement characterElement, Usuario usuario, Model model) {
	    JsonObject characterObject = characterElement.getAsJsonObject();
	    String characterName = characterObject.get("name").getAsString();
	    
	    // Creamos la instancia y se establecen los valores
	    Personaje p = new Personaje();
	    p.setName(characterObject.get("name").getAsString());
	    p.setImageUrl(characterObject.get("imageUrl").getAsString());
	    p.setId(characterObject.get("_id").getAsInt());
	    p.setSourceUrl(characterObject.get("sourceUrl").getAsString());
	    	    
	    List<Films> filmsList = new ArrayList<>();
		JsonArray filmsArray = characterObject.getAsJsonArray("films");
		for (JsonElement filmElement : filmsArray) {
			String filmName = filmElement.getAsString();
			Films film = new Films();
			film.setTitulo(filmName);
			film.setPersonaje(p);
			filmsList.add(film);
		}
		p.setFilms(filmsList);

		List<ShortFilms> shortFilmList = new ArrayList<>();
		JsonArray shortFilmsArray = characterObject.getAsJsonArray("shortFilms");
		for (JsonElement shortFilmElement : shortFilmsArray) {
			String shortFilmName = shortFilmElement.getAsString();
			ShortFilms shortFilm = new ShortFilms();
			shortFilm.setTitulo(shortFilmName);			
			shortFilm.setPersonaje(p);
			shortFilmList.add(shortFilm);
		}
		p.setShortFilms(shortFilmList);

		List<TvShows> tvShowsList = new ArrayList<>();
		JsonArray tvShowsArray = characterObject.getAsJsonArray("tvShows");
		for (JsonElement tvShowElement : tvShowsArray) {
			String tvShowName = tvShowElement.getAsString();
			TvShows tvShow = new TvShows();
			tvShow.setTitulo(tvShowName);
			tvShow.setPersonaje(p);
			tvShowsList.add(tvShow);
		}
		p.setTvShows(tvShowsList);

		List<ParkAttractions> parkAttractionsList = new ArrayList<>();
		JsonArray parkAttractionsArray = characterObject.getAsJsonArray("parkAttractions");
		for (JsonElement parkAttractionsElement : parkAttractionsArray) {
			String parkAttractionsName = parkAttractionsElement.getAsString();
			ParkAttractions parkAttractions = new ParkAttractions();
			parkAttractions.setNombreParque(parkAttractionsName);
			parkAttractions.setPersonaje(p);
			parkAttractionsList.add(parkAttractions);
		}
		p.setParkAttractions(parkAttractionsList);

		List<VideoGames> videoGamesList = new ArrayList<>();
		JsonArray videoGamesArray = characterObject.getAsJsonArray("videoGames");
		for (JsonElement videoGamesElement : videoGamesArray) {
			String videoGamesName = videoGamesElement.getAsString();
			VideoGames videoGames = new VideoGames();
			videoGames.setTitulo(videoGamesName);
			videoGames.setPersonaje(p);
			videoGamesList.add(videoGames);
		}
		p.setVideoGames(videoGamesList);
		
	    personajeService.guardarPersonaje(p);
	    
	    PersonajeGuardado personajeGuardado = new PersonajeGuardado();
	    personajeGuardado.setUsuario(usuario);
	    personajeGuardado.setPersonaje(p);
	    
	    personajeGuardadoService.guardarPersonajeParaUsuario(usuario, p);
	    
	    model.addAttribute("personaje", personajeGuardado);
	}

	
	//CONTROLADOR PARA MOSTRAR MIS PERSONAJES GUARDADOS
	@GetMapping("/mispersonajes")
	public String mostrarMisPersonajes(Model model, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
		if (usuario == null) {
			return "redirect:/user/registro";
		} else {
	        List<PersonajeGuardado> personajesGuardados = personajeGuardadoService.obtenerPersonajesGuardados(usuario);
	        //Pasamos a la vista los personajes
	        model.addAttribute("personajes", personajesGuardados);
	        model.addAttribute("titulo", "Mis Personajes Favoritos");
	        model.addAttribute("tipo", "propios");
	        return "vistalista";
		}
	}
	
	// CONTROLADOR QUE MUESTRA LISTA DE MAILS DE USUARIOS REGISTRADOS
	@GetMapping("/mailusuarios")
	public String mostrarMailUsuarios(@RequestParam("id") int characterId, @RequestParam(value = "name") String name, @RequestParam("email") String email, Model model) {
	    List<Usuario> usuarios = usuarioService.mostrarUsuarios();
	    List<Integer> personajeIds = new ArrayList<>();
	    for (Usuario usuario : usuarios) {
	        personajeIds.add(characterId);
	    }
	    model.addAttribute("usuarios", usuarios);
	    model.addAttribute("personajeIds", personajeIds);
	    model.addAttribute("email", email);
	    model.addAttribute("name", name);
	    return "listamailsusuarios";
	}
	
	// CONTROLADOR QUE GUARDA LOS PERSONAJES COMPARTIDOS AL USUARIO
	@GetMapping("/personajescompartidos")
	public String guardarPersonajeParaUsuario(@RequestParam(value= "email") String email,
	        @RequestParam("id") int characterId, @RequestParam("name") String name, Model model, HttpSession session) {
	    try {
	        Usuario usuarioRecibe = usuarioService.encontrarPorEmail(email);
	        System.out.println("Usuario encontrado: " + usuarioRecibe);
	        
	        if (usuarioRecibe != null) {
	            // Obtenemos el objeto Personaje de la búsqueda anterior
	            List<Personaje> personajesBuscados = (List<Personaje>) session.getAttribute("personajesBuscados");
	            Personaje personajeSeleccionado = null;
	            if (personajesBuscados != null) {
	                for (Personaje personaje : personajesBuscados) {
	                    if (personaje.getId() == characterId) {
	                        personajeSeleccionado = personaje;
	                        break;
	                    }
	                }
	            }
	            // Verificamos si el personaje seleccionado existe
	            if (personajeSeleccionado != null) {
	                // Verificar si el personaje ya existe en la base de datos
	                Personaje personaje = personajeService.buscarPorId(personajeSeleccionado.getId());
	                if (personaje == null) {
	                    // Si el personaje no existe en la base de datos guardamos
	                	personaje = personajeService.guardarPersonaje(personajeSeleccionado);
	                }

	                // Obtener el usuario que está actualmente logueado
	                Usuario usuarioComparte = (Usuario) session.getAttribute("usuarioLogueado");
	                
	                PersonajeCompartido nuevoPersonajeCompartido = new PersonajeCompartido(usuarioRecibe, usuarioComparte, personaje);
	                
	                personajeCompartidoService.guardarPersonajeCompartido(nuevoPersonajeCompartido);
	                
	                return "redirect:buscarpersonaje?name=" + URLEncoder.encode(name, StandardCharsets.UTF_8.toString());

	            } else {
	                // Manejar el caso en el que no se encontró el personaje
	                return "redirect:/buscarpersonaje?error=Personaje no encontrado";
	            }
	        } else {
	            return "redirect:buscarpersonaje?error=Usuario no encontrado";
	        }        
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "redirect:buscarpersonaje";
	    }        
	}
	
	// CONTROLADOR QUE MUESTRA LOS PERSONAJES QUE LE HAN COMPARTIDO AL USUARIO
	@GetMapping("/mispersonajescompartidos")
	public String mostrarPersonajesCompartidos(Model model, HttpSession session) {
	    Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
	    if (usuario == null) {
	        return "redirect:/user/registro";
	    } else {
	        // Cargar todos los personajes compartidos para el usuario
	        List<PersonajeCompartido> personajesCompartidos = personajeCompartidoService.mostrarPersonajeCompartidosPorUsuario(usuario);
	        
	        // Obtener la lista de emails de los usuarios que compartieron cada personaje
	        List<String> emailsUsuariosQueCompartieron = personajesCompartidos.stream()
	            .map(pc -> pc.getUsuarioQueCompartio().getEmail())
	            .collect(Collectors.toList());
	        
	        // Pasamos a la vista los personajes compartidos y los emails de los usuarios
	        model.addAttribute("personajes", personajesCompartidos);
	        model.addAttribute("emailsUsuariosQueCompartieron", emailsUsuariosQueCompartieron);
	        model.addAttribute("titulo", "Personajes Compartidos para mí");
	        
	        List<Integer> idsPersonajes = new ArrayList<>();
	        // Iteramos sobre la lista y agregamos ids al modelo
	        for (PersonajeCompartido personajeCompartido : personajesCompartidos) {
	            idsPersonajes.add(personajeCompartido.getPersonaje().getId());
	        }
	        // Agregar la lista de ids de personajes al modelo
	        model.addAttribute("idsPersonajes", idsPersonajes);
	        model.addAttribute("tipo", "compartidos");
	        
	        return "vistalista";
	    }
	}

	// CONTROLADOR QUE MUESTRA DETALLE DE PERSONAJES GUARDADOS POR USUARIO
	@GetMapping("/mispersonajes/{id}")
	public String mostrarDetallesPersonaje(@PathVariable("id") long id, Model model) {
		System.out.println("ID del personaje desde el metodo mostrarDetallesPersonaje: " + id);
	    // Obtener el personaje guardado por su ID
	    PersonajeGuardado personajeGuardado = personajeGuardadoService.obtenerPersonajeGuardadoPorId(id);
	    
	    // Verificar si el personaje guardado existe
	    if (personajeGuardado != null) {
	        Personaje personaje = personajeGuardado.getPersonaje();	        
	        
	        model.addAttribute("personaje", personaje);
	        model.addAttribute("titulo", "Detalles del Personaje");
	        
	        return "vistadetalle";
	    } else {	        
	        return "redirect:/error";
	    }
	}
	
	// CONTROLADOR QUE MUESTRA EL DETALLE DE LOS PERSONAJES COMPARTIDOS
	@GetMapping("/mispersonajescompartidos/{id}")
	public String mostrarDetallesPersonajeCompartido(@PathVariable("id") long id, Model model) {
	    System.out.println("ID del personaje desde el metodo mostrarDetallesPersonajeCompartido: " + id);
	    
	    // Llamada a la API Disney
	    String apiUrl = "https://api.disneyapi.dev/character/" + id;
	    try {
	        String responseText = peticion.sendGET(apiUrl);
	        System.out.println("ResponseText de la API: " + responseText);
	        
	        // Convertir la respuesta JSON en un objeto JsonObject
	        JsonObject responseJson = gson.fromJson(responseText, JsonObject.class);
	        JsonElement characterElement = responseJson.get("data");
	        
	        // Verificar si se encontró el personaje en la API
	        if (characterElement != null && characterElement.isJsonObject()) {
	            // Extraer los detalles del personaje
	            JsonObject characterObject = characterElement.getAsJsonObject();
	            
	            // Creamos una instancia de Personaje y establecemos sus detalles
	            Personaje personaje = new Personaje();
	            personaje.setName(characterObject.get("name").getAsString());
	            personaje.setImageUrl(characterObject.get("imageUrl").getAsString());
	            personaje.setSourceUrl(characterObject.get("sourceUrl").getAsString());
	    	    
	    	    List<Films> filmsList = new ArrayList<>();
	    		JsonArray filmsArray = characterObject.getAsJsonArray("films");
	    		for (JsonElement filmElement : filmsArray) {
	    			String filmName = filmElement.getAsString();
	    			Films film = new Films();
	    			film.setTitulo(filmName);
	    			film.setPersonaje(personaje);
	    			filmsList.add(film);
	    		}
	    		personaje.setFilms(filmsList);

	    		List<ShortFilms> shortFilmList = new ArrayList<>();
	    		JsonArray shortFilmsArray = characterObject.getAsJsonArray("shortFilms");
	    		for (JsonElement shortFilmElement : shortFilmsArray) {
	    			String shortFilmName = shortFilmElement.getAsString();
	    			ShortFilms shortFilm = new ShortFilms();
	    			shortFilm.setTitulo(shortFilmName);			
	    			shortFilm.setPersonaje(personaje);
	    			shortFilmList.add(shortFilm);
	    		}
	    		personaje.setShortFilms(shortFilmList);

	    		List<TvShows> tvShowsList = new ArrayList<>();
	    		JsonArray tvShowsArray = characterObject.getAsJsonArray("tvShows");
	    		for (JsonElement tvShowElement : tvShowsArray) {
	    			String tvShowName = tvShowElement.getAsString();
	    			TvShows tvShow = new TvShows();
	    			tvShow.setTitulo(tvShowName);
	    			tvShow.setPersonaje(personaje);
	    			tvShowsList.add(tvShow);
	    		}
	    		personaje.setTvShows(tvShowsList);

	    		List<ParkAttractions> parkAttractionsList = new ArrayList<>();
	    		JsonArray parkAttractionsArray = characterObject.getAsJsonArray("parkAttractions");
	    		for (JsonElement parkAttractionsElement : parkAttractionsArray) {
	    			String parkAttractionsName = parkAttractionsElement.getAsString();
	    			ParkAttractions parkAttractions = new ParkAttractions();
	    			parkAttractions.setNombreParque(parkAttractionsName);
	    			parkAttractions.setPersonaje(personaje);
	    			parkAttractionsList.add(parkAttractions);
	    		}
	    		personaje.setParkAttractions(parkAttractionsList);

	    		List<VideoGames> videoGamesList = new ArrayList<>();
	    		JsonArray videoGamesArray = characterObject.getAsJsonArray("videoGames");
	    		for (JsonElement videoGamesElement : videoGamesArray) {
	    			String videoGamesName = videoGamesElement.getAsString();
	    			VideoGames videoGames = new VideoGames();
	    			videoGames.setTitulo(videoGamesName);
	    			videoGames.setPersonaje(personaje);
	    			videoGamesList.add(videoGames);
	    		}
	    		personaje.setVideoGames(videoGamesList);
	            
	            // Añadir los detalles del personaje al modelo
	            model.addAttribute("personaje", personaje);
	            model.addAttribute("titulo", "Detalles del Personaje");
	            
	            return "vistadetalle";
	        } else {
	            
	            return "redirect:/error";
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	        
	        return "redirect:/error";
	    }
	}
	
	// CONTROLADOR PARA GUARDAR PUNTUACION DE LOS PERSONAJES
	@GetMapping("/guardarpuntuacion")
	public String guardarPuntuacion(@RequestParam("id") int id, 
	                                 @RequestParam("puntuacion") int puntuacion,
	                                 @RequestParam("tipo") String tipo, 
	                                 Model model) {
	    if (tipo.equals("propios")) {
	        // Lógica para personajes propios
	        PersonajeGuardado personajeGuardado = personajeGuardadoService.obtenerPersonajeGuardadoPorId(id);
	        if (personajeGuardado != null) {
	            personajeGuardado.setPuntuacion(puntuacion);
	            personajeGuardadoService.guardarPuntuacionPersonaje(personajeGuardado, puntuacion);
	            model.addAttribute("mensaje", "La puntuación se guardó correctamente.");
	        } else {
	            model.addAttribute("mensajeError", "No se pudo encontrar el personaje guardado.");
	        }
	        return "redirect:/personajes/mispersonajes";
	    } else if (tipo.equals("compartidos")) {
	        // Lógica para personajes compartidos
	        PersonajeCompartido personajeCompartido = personajeCompartidoService.obtenerPersonajeCompartidoPorId(id);
	        if (personajeCompartido != null) {
	            personajeCompartido.setPuntuacion(puntuacion);
	            personajeCompartidoService.guardarPuntuacionPersonajeCompartido(personajeCompartido, puntuacion);
	            model.addAttribute("mensaje", "La puntuación se guardó correctamente.");
	        } else {
	            model.addAttribute("mensajeError", "No se pudo encontrar el personaje compartido.");
	        }
	        return "redirect:/personajes/mispersonajescompartidos";
	    } else {
	        // Tipo inválido
	        model.addAttribute("mensajeError", "Tipo de personaje no válido.");
	        return "redirect:/pagina_de_error";
	    }
	}
		
}

	
	

