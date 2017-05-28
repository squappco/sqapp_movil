package com.squapp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.squapp.model.mygames.Data;

import java.util.ArrayList;

/**
 * Created by JdRod on 27/05/2017.
 */

public class MyGameAdapter extends ArrayAdapter<Data> {
    Activity activity;
    public MyGameAdapter(Activity activity, ArrayList<Data> games) {
        super(activity,0, games);
        this.activity = activity;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = activity.getLayoutInflater().inflate(R.layout.game_item_list, null);

        Data game = getItem(position);

         ((TextView)convertView.findViewById(R.id.list_date)).setText(activity.getString(R.string.date) + game.getDate());
         ((TextView)convertView.findViewById(R.id.list_hour)).setText(activity.getString(R.string.hour) + game.getStartTime());
         ((TextView)convertView.findViewById(R.id.list_price)).setText(activity.getString(R.string.end_hour) + game.getEndTime());
         ((TextView)convertView.findViewById(R.id.list_level)).setText(activity.getString(R.string.level) + game.getField());
         ((TextView)convertView.findViewById(R.id.list_players)).setText(activity.getString(R.string.players) + game.getMaxPlayers());
        return convertView;
    }
}
