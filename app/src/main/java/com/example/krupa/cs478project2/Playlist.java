package com.example.krupa.cs478project2;

import java.net.URL;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by krupa on 2/26/2018.
 */

public class Playlist {
    public ArrayList <Song> SongPlaylist;

    public Playlist (){
        SongPlaylist = new ArrayList<Song>();
        SongPlaylist.add(new Song("Magic in the Air",
                        "Magic System",
                        "https://en.wikipedia.org/wiki/Magic_System",
                        "https://en.wikipedia.org/wiki/Magic_in_the_Air_(Magic_System_song)",
                        "https://www.youtube.com/watch?v=BAkqJT_sMKQ&list=RDGMEMQ1dJ7wXfLlqCjwV0xfSNbAVMBAkqJT_sMKQ"));
        SongPlaylist.add(new Song("Viva La Vida",
                        "Coldplay",
                        "https://en.wikipedia.org/wiki/Coldplay",
                        "https://en.wikipedia.org/wiki/Viva_la_Vida",
                        "https://www.youtube.com/watch?v=dvgZkm1xWPE&index=6&list=RDGMEMQ1dJ7wXfLlqCjwV0xfSNbAVMBAkqJT_sMKQ"));
        SongPlaylist.add(new Song("Alone",
                        "Alan Walker",
                        "https://en.wikipedia.org/wiki/Alan_Walker_(music_producer)",
                        "https://en.wikipedia.org/wiki/Alone_(Alan_Walker_song)",
                        "https://www.youtube.com/watch?v=1-xGerv5FOk&index=24&list=RDGMEMQ1dJ7wXfLlqCjwV0xfSNbAVMBAkqJT_sMKQ"));
        SongPlaylist.add(new Song("X Gon' Give It to Ya",
                        "DMX",
                        "https://en.wikipedia.org/wiki/DMX_(rapper)%22",
                        "https://en.wikipedia.org/wiki/X_Gon%27_Give_It_to_Ya",
                        "https://www.youtube.com/watch?v=fGx6K90TmCI"));
        SongPlaylist.add(new Song("If I Had a Heart",
                        "Fever Ray",
                        "https://en.wikipedia.org/wiki/Karin_Dreijer%22",
                        "https://en.wikipedia.org/wiki/If_I_Had_a_Heart",
                        "https://www.youtube.com/watch?v=EBAzlNJonO8"));
    }
    public int getPlaylistSize(){
        return SongPlaylist.size();
    }
    public Object getSongByPosition(int position){
        return SongPlaylist.get(position);
    }
    public String getTitle(int position){
        return SongPlaylist.get(position).getSongTitle();
    }
    public String getArtist(int position){
        return SongPlaylist.get(position).getArtist();
    }
    public String getWikiURL(int position){
        return SongPlaylist.get(position).getArtistWikiURL();
    }
    public String getSongWikiURL(int position){
        return SongPlaylist.get(position).getSongWikiURL();
    }
    public String getVideoURL(int position){
        return SongPlaylist.get(position).getYoutubeURL();
    }
    public void addSongToPlaylist(String songTitle, String Artist, String ArtistWikiURL, String SongWikiURL,String videoURL){
        SongPlaylist.add(new Song(songTitle,Artist,ArtistWikiURL,SongWikiURL,videoURL));
    }
    public boolean removeSongFromPlaylist(int position){
        try {
            SongPlaylist.remove(position);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
