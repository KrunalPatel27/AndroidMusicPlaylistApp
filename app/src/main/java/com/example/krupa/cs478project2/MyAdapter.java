package com.example.krupa.cs478project2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by krupa on 2/26/2018.
 */

public class MyAdapter extends BaseAdapter {

    private Playlist playlist;
    private Context context;

    public MyAdapter (Context context, Playlist playlist){
        this.context = context;
        this.playlist = playlist;
    }

    @Override
    public int getCount() {
        return playlist.getPlaylistSize();
    }

    @Override
    public Object getItem(int position) {
        return playlist.getSongByPosition(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.listview_design, parent, false);
        TextView Title = (TextView) view.findViewById(R.id.Title);
        TextView Artist = (TextView) view.findViewById(R.id.Artist);

        Title.setText(playlist.getTitle(position));
        Artist.setText(playlist.getArtist(position));
        return view;
    }
}
