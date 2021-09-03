package it.univaq.disim.mwt.letsjam.business;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchTracksRequest;
import it.univaq.disim.mwt.letsjam.domain.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import org.apache.hc.core5.http.ParseException;

@Service
public class SpotifyApiService {
    @Autowired
    private Environment env;
    private static SpotifyApi spotifyApi;
    private static  ClientCredentialsRequest clientCredentialsRequest;

    @PostConstruct
    public void init(){
        this.spotifyApi = new SpotifyApi.Builder()
                .setClientId(env.getProperty("spotify.client_id"))
                .setClientSecret(env.getProperty("spotify.client_secret"))
                .build();
        this.clientCredentialsRequest = spotifyApi.clientCredentials().build();
        getAccessToken();
    }
    public SpotifyApi getObjectSpotify(){
        return this.spotifyApi;
    }
    private void getAccessToken(){
        try {
            final ClientCredentials clientCredentials = clientCredentialsRequest.execute();

            // Set access token for further "spotifyApi" object usage
            spotifyApi.setAccessToken(clientCredentials.getAccessToken());

            System.out.println("Expires in: " + clientCredentials.getExpiresIn());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void setSongInfo(Song song){
        String q = "artist:"+ song.getAuthor() + " track:" + song.getTitle();
        SearchTracksRequest request = spotifyApi.searchTracks(q)
                .build();
        try {
            Paging<Track> tracks = request.execute();
            Track traccia = tracks.getItems()[0];
            ArtistSimplified[] artists = traccia.getArtists();
            String author = "";
            for(int i=0; i<artists.length; i++){
                author += artists[i].getName() + ", ";
            }
            author = author.substring(0,author.length() - 2);
            song.setAuthor(author);
            song.setTitle(traccia.getName());
            song.setImageUrl(traccia.getAlbum().getImages()[0].getUrl());
            song.setAlbumType(traccia.getAlbum().getAlbumType().toString());
            song.setAlbumName(traccia.getAlbum().getName());
            song.setDuration(traccia.getDurationMs());
            song.setIsExplicit(traccia.getIsExplicit());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }


}
