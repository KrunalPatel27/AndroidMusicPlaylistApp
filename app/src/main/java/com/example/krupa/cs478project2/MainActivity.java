package com.example.krupa.cs478project2;
import android.app.Dialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
        playlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), WebAppActivity.class);
                intent.putExtra("URL", playlist.getVideoURL(position));
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options, menu);

        SubMenu listSongs = menu.addSubMenu("Remove");
        menu.add("Exit");
        for (int i=0; i< playlist.getPlaylistSize(); i++)
            listSongs.add(0, i,0,playlist.getTitle(i));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getTitle().toString()){
            case "New":
                openAddSongDialog();
                break;
            case "Remove":
                break;
            case "Exit":
                exitApp();
                break;
            default:
                if( playlist.removeSongFromPlaylist(item.getItemId()) ) {
                    break;
                }
                else
                    return super.onOptionsItemSelected(item);
        }
        invalidateOptionsMenu();
        adapter.notifyDataSetChanged();

        return true;
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
                sArtistWikiURL = ArtistWikiURL.getText().toString();
                sSongWikiURL = SongWikiURL.getText().toString();
                sVideoURL = VideoURL.getText().toString();

                if(sTitle.equals("") || sArtist.equals("") || sSongWikiURL.equals("")
                        || sArtistWikiURL.equals("") || sVideoURL.equals("")){
                    Toast.makeText(getActivity(), "Missing Details!", Toast.LENGTH_SHORT).show();
                }
                else{
                    playlist.addSongToPlaylist(sTitle,sArtist,sArtistWikiURL, sSongWikiURL, sVideoURL);
                    invalidateOptionsMenu();
                    adapter.notifyDataSetChanged();

                    dialog.hide();

                }

            }
        });
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.hide();
            }
        });
        dialog.show();
    }

    private void exitApp(){
        finishAndRemoveTask();
    }
    private Context getActivity(){
        return this;
    }

    private void setInitialPlaylist(){
        playlist = new Playlist();
    }
}
