package com.example.krupa.cs478project2;
import android.app.Dialog;
import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {
    private Playlist playlist;
    private String [] itemsList;
    ListView playlistView;
    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playlistView = (ListView) findViewById(R.id.PlaylistView);
        setInitialPlaylist();

        adapter = new MyAdapter(this, playlist);
        playlistView.setAdapter(adapter);
        registerForContextMenu(playlistView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.new_song:
                openAddSongDialog();
                return true;
            case R.id.remove_song:
                return true;
            case R.id.exit:
                return true;
            default:
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        // Get the list item position
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
        int position = info.position;
        menu.setHeaderTitle("Select The Action");
        menu.add(0, position, 0, "Video Clip");
        menu.add(1, position, 0, "Song Info");
        menu.add(2, position, 0, "Artist/Band Info");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getTitle() == "Video Clip"){
            Intent intent = new Intent(this, WebAppActivity.class);
            intent.putExtra("URL", playlist.getVideoURL(item.getItemId()));
            startActivity(intent);
        }else if(item.getTitle() == "Song Info"){
            Intent intent = new Intent(this, WebAppActivity.class);
            intent.putExtra("URL", playlist.getSongWikiURL(item.getItemId()));
            startActivity(intent);
        }else if(item.getTitle() == "Artist/Band Info") {
            Intent intent = new Intent(this, WebAppActivity.class);
            intent.putExtra("URL", playlist.getWikiURL(item.getItemId()));
            startActivity(intent);
        }else{
            return false;
        }
        return true;
    }

    private void openAddSongDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.new_song);

        final EditText Title = (EditText) dialog.findViewById(R.id.Title);
        final EditText Artist = (EditText) dialog.findViewById(R.id.Artist);
        final EditText SongWikiURL = (EditText) dialog.findViewById(R.id.SongWiki);
        final EditText ArtistWikiURL = (EditText) dialog.findViewById(R.id.ArtistWiki);
        final EditText VideoURL = (EditText) dialog.findViewById(R.id.VideoURL);

        Button Done = (Button) dialog.findViewById(R.id.done);
        Button Cancel = (Button) dialog.findViewById(R.id.cancel);

        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sTitle, sArtist, sSongWikiURL, sArtistWikiURL, sVideoURL;
                sTitle = Title.getText().toString();
                sArtist = Artist.getText().toString();
                sSongWikiURL = SongWikiURL.getText().toString();
                sArtistWikiURL = ArtistWikiURL.getText().toString();
                sVideoURL = VideoURL.getText().toString();

                if(sTitle != null && sArtist != null && sSongWikiURL != null
                        && sArtistWikiURL != null && sVideoURL != null){

                }
                dialog.hide();
            }
        });
        dialog.show();
    }

    private void setInitialPlaylist(){
        playlist = new Playlist();
    }
}
