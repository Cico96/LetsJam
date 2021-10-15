package it.univaq.disim.mwt.letsjam.presentation;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.google.gson.JsonObject;
import it.univaq.disim.mwt.letsjam.business.ScoreAnalyzerService;
import it.univaq.disim.mwt.letsjam.business.SongService;
import it.univaq.disim.mwt.letsjam.business.SpotifyApiService;

import it.univaq.disim.mwt.letsjam.presentation.viewModels.CreateUpdateSheetViewModel;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import it.univaq.disim.mwt.letsjam.security.CustomUserDetails;
import it.univaq.disim.mwt.letsjam.business.GenreService;
import it.univaq.disim.mwt.letsjam.business.InstrumentService;
import it.univaq.disim.mwt.letsjam.business.LyricsService;
import it.univaq.disim.mwt.letsjam.business.MusicSheetService;
import it.univaq.disim.mwt.letsjam.domain.Genre;
import it.univaq.disim.mwt.letsjam.domain.Instrument;
import it.univaq.disim.mwt.letsjam.domain.MusicSheet;
import it.univaq.disim.mwt.letsjam.domain.MusicSheetData;
import it.univaq.disim.mwt.letsjam.domain.Song;
import it.univaq.disim.mwt.letsjam.domain.User;
import it.univaq.disim.mwt.letsjam.presentation.viewModels.MusicSheetSearchViewModel;
import org.springframework.web.multipart.MultipartFile;

import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.Track;

@Controller
@RequestMapping("/musicsheets")
public class MusicSheetController {
	
	@Autowired
	private MusicSheetService spartitoService;
	@Autowired
	private GenreService genreService;
	@Autowired
	private InstrumentService instrumentService;
	@Autowired
	private ScoreAnalyzerService as;
	@Autowired
    private SpotifyApiService spotifyService;
	@Autowired
    private LyricsService lyricsService;
	@Autowired
	private SongService songService;
	
	private static final int PAGE_SIZE = 5;

	@GetMapping("/")
	public String all(Model model){
		List<Genre> genres = genreService.getAllGenres();
		Page<MusicSheet> musicSheets = spartitoService.getAllMusicSheets(PageRequest.of(0, PAGE_SIZE));
		List<Instrument> instruments = instrumentService.getAllInstruments();
		MusicSheetSearchViewModel formData = new MusicSheetSearchViewModel();
		formData.setTotalPages(musicSheets.getTotalPages());
        formData.setPageNumber(0);
		
		model.addAttribute("formData", formData);
		model.addAttribute("instruments", instruments);
		model.addAttribute("musicSheets", musicSheets);
		model.addAttribute("genres", genres);
		return "musicSheets/all";
	}

	@PostMapping("/")
	public String search(MusicSheetSearchViewModel formData, Model model){
		List<Genre> genres = genreService.getAllGenres();
		List<Instrument> instruments = instrumentService.getAllInstruments();
		Page<MusicSheet> musicSheets = spartitoService.searchMusicSheets(
			formData.getSearch(), formData.getSortBy(), 
			formData.getSortDirection(), formData.getGenres(), 
			formData.getInstruments(), formData.getVerified(), 
			formData.getRearranged(), formData.getPageNumber(), PAGE_SIZE);
		formData.setTotalPages(musicSheets.getTotalPages());
		
		model.addAttribute("formData", formData);
		model.addAttribute("instruments", instruments);
		model.addAttribute("musicSheets", musicSheets);
		model.addAttribute("genres", genres);
		return "musicSheets/all";
	}
	
	@GetMapping("/{id}")
	public String getSpartitoSingolo(@PathVariable("id") long id, Model model){
		MusicSheet musicSheet = spartitoService.findMusicSheetById(id);
		model.addAttribute("musicSheet", musicSheet);
		return "musicSheets/musicsheet";
	}
	
	@GetMapping("/spartito/{id}/delete")
	public String deleteSpartito(@PathVariable("id") long id) {
		spartitoService.deleteMusicSheetById(id);
		return "home/home";
	}

	@GetMapping("/create")
	public String view(Model model){
		List<Instrument> instrumentList = instrumentService.getAllInstruments();
		model.addAttribute("instruments", instrumentList);
		model.addAttribute("pageData", new CreateUpdateSheetViewModel());
		return "musicSheets/create-upload";
	}
	
	@PostMapping("/create")
	public String upload(CreateUpdateSheetViewModel pageData, Authentication authentication,Model model){
		User loggedUser = ((CustomUserDetails) authentication.getPrincipal()).getUser();

		JSONObject brano = new JSONObject(pageData.getBrano()); 
		Long songId = brano.optLong("songId");
		String spotifyId = brano.getString("spotifyId");

		
		Map<String, String> mappings = as.getInstruments(new JSONObject(pageData.getContent()));
		Set<Instrument> strumenti = as.toInstrumentSet(mappings);

		MusicSheetData data = new MusicSheetData();
		data.setContent(pageData.getContent());
		data.setInstrumentMapping(mappings);

		MusicSheet spartito = new MusicSheet();
		spartito.setData(data);
		spartito.setTitle(pageData.getTitle());
		spartito.setAuthor(pageData.getAuthor());
		spartito.setUser(loggedUser);
		spartito.setInstruments(strumenti);
		spartito.setRearranged(false);
		spartito.setVerified(false);
		spartito.setHasTablature(as.hasTablature(new JSONObject(pageData.getContent())));

		Song song;
		if(songId != 0){
				song = songService.findSongById(songId);
		}
		else{
			song = spotifyService.getSongFromSpotifyId(spotifyId);
			lyricsService.setLyrics(song);
		}
		spartito.setSong(song);
		spartitoService.addMusicSheet(spartito);
		
		List<Instrument> instrumentList = instrumentService.getAllInstruments();
		model.addAttribute("instruments", instrumentList);
		model.addAttribute("pageData", new CreateUpdateSheetViewModel());
		return "musicSheets/create-upload";
	}
	

	@PostMapping("/analyze")
	public ResponseEntity<String> analyze(@RequestParam("score") String score){
		if(score != null && score.length() > 0){
				String title = as.getScoreTitle(new JSONObject(score));
				String author = as.getScoreAuthor(new JSONObject(score));
				JSONObject result = new JSONObject();
				result.put("title", title);
				result.put("author", author);
				result.put("content", score);

				return new ResponseEntity<String>(result.toString(), HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	@PostMapping("/getEmptyScore")
	public ResponseEntity<String> getEmptyScore(@RequestParam("instruments") String instrumentList){
		if(instrumentList != null && instrumentList.length() > 0){
				List<String> strumenti = (new JSONArray(instrumentList))
											.toList()
											.stream()
											.map(Object::toString)
											.collect(Collectors.toList());
				JSONObject result = as.makeEmptyScore(strumenti);
				return new ResponseEntity<String>(result.toString(), HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}


	@GetMapping("/brani")
	public ResponseEntity<String> searchSong(@RequestParam("songSearchString") String searchString, Model model){
		JSONArray result = new JSONArray();
		CreateUpdateSheetViewModel pageData = new CreateUpdateSheetViewModel();
		List<Song> dbSongs = songService.searchSongsByTitleAndAuthor(searchString);
		ArrayList<Track> spotifySongs = new ArrayList<Track>(spotifyService.searchSong(searchString));  
		List<Integer> toRemove = new ArrayList<Integer>();

		if(!dbSongs.isEmpty()) {
			for(Song s : dbSongs){
				for(Track t : spotifySongs){
					if(s.getSpotifyId() != null && s.getSpotifyId().equals(t.getId())){
						toRemove.add(spotifySongs.indexOf(t));
					}
				}
			}
			dbSongs.forEach(s -> {
				JSONObject branodb = new JSONObject();
							branodb.put("title", s.getTitle());
							branodb.put("author",s.getAuthor());
							branodb.put("spotifyId",s.getSpotifyId());
							branodb.put("id",s.getId());
							result.put(branodb);
			});
		}

		if(toRemove.size() > 0) toRemove.forEach(r -> spotifySongs.remove(r.intValue()));

		spotifySongs.forEach(t -> {
			JSONObject branoSpoty = new JSONObject();
				branoSpoty.put("title",t.getName());
				String authors = "";
				for(ArtistSimplified a : List.of(t.getArtists())){
					authors+=a.getName()+" ";
				}
				branoSpoty.put("author",authors);
				branoSpoty.put("spotifyId",t.getId());
				branoSpoty.put("id", JSONObject.NULL);
				result.put(branoSpoty);
		});
			
//		model.addAttribute("pageData", pageData);
//		System.out.println(pageData.getSongs());
		return new ResponseEntity<String>(result.toString(), HttpStatus.OK);
	}
}
