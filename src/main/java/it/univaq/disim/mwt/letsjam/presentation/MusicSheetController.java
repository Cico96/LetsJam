package it.univaq.disim.mwt.letsjam.presentation;

import java.util.*;
import java.util.stream.Collectors;

import it.univaq.disim.mwt.letsjam.business.ScoreAnalyzerService;
import it.univaq.disim.mwt.letsjam.business.SongService;
import it.univaq.disim.mwt.letsjam.business.SpotifyApiService;
import it.univaq.disim.mwt.letsjam.presentation.viewModels.CreateUpdateSheetViewModel;
import it.univaq.disim.mwt.letsjam.presentation.viewModels.RearrangeMusicSheetViewModel;

import org.apache.commons.lang3.StringUtils;
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
import it.univaq.disim.mwt.letsjam.business.CommentService;
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
import it.univaq.disim.mwt.letsjam.domain.Comment;
import it.univaq.disim.mwt.letsjam.presentation.viewModels.MusicSheetSearchViewModel;
import it.univaq.disim.mwt.letsjam.presentation.viewModels.ReplyViewModel;

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
	@Autowired
	private CommentService commentService;
	
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
	public String search(@ModelAttribute MusicSheetSearchViewModel formData, Model model){
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
	public String getSpartitoSingolo(@PathVariable("id") long id, Model model, Authentication authentication){
		MusicSheet musicSheet = spartitoService.findMusicSheetById(id);
		MusicSheetData data = spartitoService.getMusicSheetData(id);
		User loggedUser = ((CustomUserDetails) authentication.getPrincipal()).getUser();
		List<Comment> comments = commentService.getMusicSheetComments(musicSheet.getId());
		model.addAttribute("comments", comments);
		model.addAttribute("musicSheet", musicSheet);
		model.addAttribute("musicSheetData", data);
		model.addAttribute("loggedUser", loggedUser);
		return "musicSheets/musicSheet";
	}
	
	@GetMapping("/spartito/{id}/delete")
	public String deleteSpartito(@PathVariable("id") long id) {
		spartitoService.deleteMusicSheetById(id);
		return "home/home";
	}

	@GetMapping("/create")
	public String view(Model model){
		List<Instrument> instrumentList = instrumentService.getAllInstruments();
		List<Genre> generi = genreService.getAllGenres();
		model.addAttribute("genres", generi);
		model.addAttribute("instruments", instrumentList);
		model.addAttribute("pageData", new CreateUpdateSheetViewModel());
		return "musicSheets/create-upload";
	}
	
	@PostMapping("/create")
	public String upload(@ModelAttribute CreateUpdateSheetViewModel pageData, Authentication authentication,Model model){
		User loggedUser = ((CustomUserDetails) authentication.getPrincipal()).getUser();

		System.out.println(pageData.getVisibility());

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
		spartito.setVisibility(pageData.getVisibility());

		//Brano
		Song song;
		if(!pageData.getSongType()) {
			JSONObject brano = new JSONObject(pageData.getBrano()); 
			Long songId = brano.optLong("songId");
			String spotifyId = brano.getString("spotifyId");
			
			if(songId != 0){
					song = songService.findSongById(songId);
			}
			else{
				song = spotifyService.getSongFromSpotifyId(spotifyId);
				lyricsService.setLyrics(song);
				songService.updateSong(song);
			}
		}
		else{
			song = new Song();
			song.setTitle(pageData.getSongTitle());
			song.setAuthor(pageData.getSongAuthor());
			song.setGenre(genreService.findGenreById(pageData.getSongGenre()));
			songService.updateSong(song);
		}

		spartito.setSong(song);
		MusicSheet persistedMusicSheet = spartitoService.addMusicSheet(spartito);
		
		List<Instrument> instrumentList = instrumentService.getAllInstruments();
		List<Genre> generi = genreService.getAllGenres();
		model.addAttribute("genres", generi);
		model.addAttribute("instruments", instrumentList);
		model.addAttribute("pageData", new CreateUpdateSheetViewModel());
		return "redirect:"+persistedMusicSheet.getId();
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

	@GetMapping("/rearrange/{id}")
	public String showRearrangeMusicSheets(@PathVariable("id") long id, Model model){
		List<Instrument> instrumentList = instrumentService.getAllInstruments();
		MusicSheet musicSheet = spartitoService.findMusicSheetById(id);
		MusicSheetData musicSheetData = spartitoService.getMusicSheetData(id);
		RearrangeMusicSheetViewModel pageData = new RearrangeMusicSheetViewModel();
		model.addAttribute("pageData", pageData);
		model.addAttribute("musicSheetData", musicSheetData);
		model.addAttribute("musicSheet", musicSheet);
		model.addAttribute("instruments", instrumentList);
		return "musicSheets/rearrangeMusicSheet";
	}

	@PostMapping("/rearrange")
	public String rearrangeMusicSheets(@ModelAttribute RearrangeMusicSheetViewModel pageData, Model model, Authentication authentication){
		User loggedUser = ((CustomUserDetails) authentication.getPrincipal()).getUser();

		Map<String, String> mappings = as.getInstruments(new JSONObject(pageData.getContent()));
		Set<Instrument> strumenti = as.toInstrumentSet(mappings);

		MusicSheetData data = new MusicSheetData();
		data.setContent(pageData.getContent());
		data.setInstrumentMapping(mappings);

		MusicSheet ms = new MusicSheet();
		ms.setData(data);
		ms.setAuthor(pageData.getAuthor());
		ms.setTitle(pageData.getTitle());
		ms.setInstruments(strumenti);
		ms.setVisibility(pageData.getVisibility());
		ms.setUser(loggedUser);
		ms.setVerified(false);
		ms.setRearranged(true);
		ms.setHasTablature(as.hasTablature(new JSONObject(pageData.getContent())));

		ms = spartitoService.addMusicSheet(ms);
		model.addAttribute("pageData", pageData);
		return "redirect:"+ms.getId();
	}


	@GetMapping("/brani")
	public ResponseEntity<String> searchSong(@RequestParam("songSearchString") String searchString, Model model){
		JSONArray result = new JSONArray();
		List<Song> dbSongs = songService.searchSongsByTitleAndAuthor(searchString);
		ArrayList<Track> spotifySongs = new ArrayList<Track>(spotifyService.searchSong(searchString));  
		List<Integer> toRemove = new ArrayList<Integer>();

		if(!dbSongs.isEmpty()) {
			for(Song s : dbSongs){
				System.out.println("DB: "+s.getTitle());
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

		if(toRemove.size() > 0 && spotifySongs.size() > 0) toRemove.forEach(r -> {
			Track t = spotifySongs.get(r.intValue());
			if(t != null) spotifySongs.remove(t);
		});

		if(spotifySongs.size()>0){
			spotifySongs.forEach(t -> {
				System.out.println("SPOTY: "+t.getName());
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
		}
			
		return new ResponseEntity<String>(result.toString(), HttpStatus.OK);
	}

	@PostMapping("/getScoreWithOnlyParts")
	public ResponseEntity<String> getScoreWithOnlyParts(@RequestParam("partList") String partList, @RequestParam("musicSheetId") long id){
		if(partList != null && partList.length() > 0){
				MusicSheetData data = spartitoService.getMusicSheetData(id);
				List<String> strumenti = (new JSONArray(partList))
											.toList()
											.stream()
											.map(Object::toString)
											.collect(Collectors.toList());
				JSONObject result = as.extractInstrumentPart(new JSONObject(data.getContent()), strumenti);
				return new ResponseEntity<String>(result.toString(), HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	@PostMapping("/addComment")
	public ResponseEntity<String> addComment(
		@RequestParam("parentId") String parentId, 
		@RequestParam("musicSheetId") long musicSheetId, 
		@RequestParam("content") String content,
		Authentication authentication){

		User loggedUser = ((CustomUserDetails) authentication.getPrincipal()).getUser();
		MusicSheet musicSheet = spartitoService.findMusicSheetById(musicSheetId);
		Comment c = new Comment();
		c.setUser(loggedUser);
		c.setContent(content);
		c.setMusicSheet(musicSheet);

		if(StringUtils.isNotEmpty(parentId)){
			Long parent = Long.parseLong(parentId);
			c.setParentComment(commentService.findCommentById(parent));
		}

		return new ResponseEntity<String>("Ok", HttpStatus.OK);
	}

	@PostMapping("/getReplies")
	public ResponseEntity<String> getReplies(@RequestParam("parentId") String parentId,Authentication authentication){
		List<Comment> replies = commentService.getReplies(Long.parseLong(parentId));
		List<ReplyViewModel> result = new ArrayList<ReplyViewModel>();
		replies.forEach(r -> {
			ReplyViewModel reply = new ReplyViewModel();
			reply.setId(r.getId());
			reply.setContent(r.getContent());
			reply.setFirstName(r.getUser().getFirstname());
			reply.setLastName(r.getUser().getLastname());
			reply.setUserAvatar(r.getUser().getAvatar());
			result.add(reply);
		});
		return new ResponseEntity<String>((new JSONArray(result)).toString(), HttpStatus.OK);
	}

	@PostMapping("/like")
	public ResponseEntity<String> like(@RequestParam("musicSheetId") long musicSheetId, Authentication authentication){
		User loggedUser = ((CustomUserDetails) authentication.getPrincipal()).getUser();
		MusicSheet spartito = spartitoService.findMusicSheetById(musicSheetId);
		spartitoService.addLike(spartito, loggedUser);
		System.out.println("liked");
		return new ResponseEntity<String>("Liked", HttpStatus.OK);
	}

	@PostMapping("/dislike")
	public ResponseEntity<String> dislike(@RequestParam("musicSheetId") long musicSheetId, Authentication authentication){
		User loggedUser = ((CustomUserDetails) authentication.getPrincipal()).getUser();
		MusicSheet spartito = spartitoService.findMusicSheetById(musicSheetId);
		spartitoService.removeLike(spartito, loggedUser);
		System.out.println("disliked");
		return new ResponseEntity<String>("Liked", HttpStatus.OK);
	}

	@PostMapping("/update")
	ResponseEntity<String> update(@RequestParam("musicSheetId") Long musicSheetId, @RequestParam("musicSheetContent") String content ){
		MusicSheet musicSheet = spartitoService.findMusicSheetById(musicSheetId);
		MusicSheetData data = musicSheet.getData();
		data.setContent(content);
		musicSheet.setData(data);
		spartitoService.update(musicSheet);
		return new ResponseEntity<String>("Updated", HttpStatus.OK);
	}
}
