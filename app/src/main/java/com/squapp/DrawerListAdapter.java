package com.squapp;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by JdRod on 27/05/2017.
 */

public class DrawerListAdapter extends ArrayAdapter<DrawerItem> {

    public DrawerListAdapter(Context context, List<DrawerItem> resources) {
        super(context,0, resources);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE
            );
            convertView = inflater.inflate(R.layout.drawer_item_layout,null);
        }
        ImageView drawerIcon = (ImageView) convertView.findViewById(R.id.drawer_icon);
        TextView drawerText = (TextView) convertView.findViewById(R.id.drawer_text);

        DrawerItem drawerItem = getItem(position);
        drawerIcon.setImageResource(drawerItem.getDrawerIconID());
        drawerText.setText(drawerItem.getDrawerText());
        return convertView;
    }
}
