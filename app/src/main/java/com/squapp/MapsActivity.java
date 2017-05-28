package com.squapp;

import android.content.Intent;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    FragmentManager fragmentManager;
    ListView drawerListView;
    String[] tagsList;
    android.support.v7.app.ActionBarDrawerToggle drawerToggle;
    Bundle savedInstanceState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        setContentView(R.layout.activity_maps);
        fragmentManager = getSupportFragmentManager();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");

        initDrawer();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    void initDrawer(){
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerListView = (ListView) findViewById(R.id.drawer_list);
        //drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        tagsList = getResources().getStringArray(R.array.Tags);
        ArrayList<DrawerItem> listItems = new ArrayList<DrawerItem>();
        listItems.add(new DrawerItem(tagsList[0], R.drawable.games));
        listItems.add(new DrawerItem(tagsList[1], R.drawable.find));
        listItems.add(new DrawerItem(tagsList[2], R.drawable.account));
        listItems.add(new DrawerItem(tagsList[3], R.drawable.settings));

        drawerListView.setAdapter(new DrawerListAdapter(this, listItems));
        drawerListView.setOnItemClickListener(new DrawerItemClickListener());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerToggle = new android.support.v7.app.ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.string.drawer_open,
                R.string.drawer_close
        ) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(getTitle());
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(getTitle());
            }
        };
        //Seteamos la escucha
        drawerLayout.setDrawerListener(drawerToggle);

        if (savedInstanceState == null) {
            selectedItem(0);
        }
    }
    private void selectedItem(int position) {

            switch (position) {

                case 0:
                    Toast toast = Toast.makeText(getBaseContext(),"MyGames/Maps",Toast.LENGTH_LONG);
                    toast.show();
                    MapsFragment mapsFragment = new MapsFragment();
                    fragmentManager.beginTransaction().replace(R.id.drawer_container, mapsFragment).commit();
                    break;
                case 1:

                    break;
                case 2:


                    break;
                case 3:


                    break;

                default:

                    break;

            }
            // Se actualiza el item seleccionado y el título, después de cerrar el drawer
            drawerListView.setItemChecked(position, true);
            setTitle(tagsList[position]);
            drawerLayout.closeDrawer(drawerListView);
        }
    @Override
    public void onBackPressed() {

    }
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectedItem(position);
        }
    }
}
