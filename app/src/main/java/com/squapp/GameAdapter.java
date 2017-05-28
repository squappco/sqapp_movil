package com.squapp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.security.acl.Group;
import java.util.ArrayList;

/**
 * Created by JdRod on 27/05/2017.
 */

public class GameAdapter extends ArrayAdapter<Game> {
    Activity activity;
    public GameAdapter(Activity activity, ArrayList<Game> games) {
        super(activity,0, games);
        this.activity = activity;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = activity.getLayoutInflater().inflate(R.layout.game_item_list, null);

        Game game = getItem(position);

        ((TextView)convertView.findViewById(R.id.list_date)).setText(activity.getString(R.string.date) + game.getDate());
        ((TextView)convertView.findViewById(R.id.list_hour)).setText(activity.getString(R.string.hour) + game.getHour());
        ((TextView)convertView.findViewById(R.id.list_price)).setText(activity.getString(R.string.cost) + game.getPrice());
        ((TextView)convertView.findViewById(R.id.list_level)).setText(activity.getString(R.string.level) + game.getLevel());
        ((TextView)convertView.findViewById(R.id.list_players)).setText(activity.getString(R.string.players) + game.getPlayers());
        return convertView;
    }
}
