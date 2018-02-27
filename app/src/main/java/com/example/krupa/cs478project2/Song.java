package com.example.krupa.cs478project2;

/**
 * Created by krupa on 2/26/2018.
 */

public class Song {
    private String songTitle;
    private String Artist;
    private String ArtistWikiURL;
    private String SongWikiURL;
    private String youtubeURL;

    public Song(String songTitle, String Artist, String ArtistWikiURL, String SongWikiURL,String youtubeURL){
        this.songTitle = songTitle;
        this.Artist = Artist;
        this.ArtistWikiURL = ArtistWikiURL;
        this.SongWikiURL = SongWikiURL;
        this.youtubeURL = youtubeURL;
    }
    public String getSongTitle() {
        return songTitle;
    }

    public String getArtist() {
        return Artist;
    }

    public String getArtistWikiURL() {
        return ArtistWikiURL;
    }

    public String getSongWikiURL() {
        return SongWikiURL;
    }

    public String getYoutubeURL() {
        return youtubeURL;
    }

}
