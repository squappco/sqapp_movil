package com.squapp;

import android.app.FragmentManager;
import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MapsActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    FragmentManager fragmentManager;
    ListView drawerListView;
    String[] tagsList;
    android.support.v7.app.ActionBarDrawerToggle drawerToggle;
    Bundle savedInstanceState;
    //Fragments
    MapsFragment mapsFragment;
    FindGamesFragment findGamesFragment;
    MyGamesFragment myGamesFragment;
    SettingsFragment settingsFragment;
    AccountFragment accountFragment;
    VenuesListFragment venuesListFragment;
    CreateGameFragment createGameFragment;
    GamesOfPlaceFragment gamesOfPlaceFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        setContentView(R.layout.activity_maps);
        fragmentManager = getFragmentManager();

        initFragments();


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

        listItems.add(new DrawerItem(tagsList[0], R.drawable.radar));
        listItems.add(new DrawerItem(tagsList[1], R.drawable.games));
        listItems.add(new DrawerItem(tagsList[2], R.drawable.find));
        listItems.add(new DrawerItem(tagsList[3], R.drawable.settings));

        drawerListView.setAdapter(new DrawerListAdapter(this, listItems));
        drawerListView.setOnItemClickListener(new DrawerItemClickListener());


        drawerToggle = new android.support.v7.app.ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.string.drawer_open,
                R.string.drawer_close
        ) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(getTitle());
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(getTitle());
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        if (savedInstanceState == null) {
            selectedItem(0);
        }
    }
    void initFragments(){
        mapsFragment = new MapsFragment();
        accountFragment = new AccountFragment();
        findGamesFragment = new FindGamesFragment();
        myGamesFragment = new MyGamesFragment();
        settingsFragment = new SettingsFragment();
        venuesListFragment = new VenuesListFragment();
        createGameFragment = new CreateGameFragment();
        gamesOfPlaceFragment = new GamesOfPlaceFragment();
    }
    void selectedItem(int position) {

            switch (position) {

                case 0:
                    fragmentManager.beginTransaction().replace(R.id.drawer_container, mapsFragment).commit();
                    break;
                case 1:
                    fragmentManager.beginTransaction().replace(R.id.drawer_container, myGamesFragment).commit();
                    break;
                case 2:
                    fragmentManager.beginTransaction().replace(R.id.drawer_container, venuesListFragment).commit();
                    break;
                case 3:
                    fragmentManager.beginTransaction().replace(R.id.drawer_container, settingsFragment).commit();
                    break;
                default:

                    break;

            }
            // Se actualiza el item seleccionado y el título, después de cerrar el drawer
            drawerListView.setItemChecked(position, true);
            setTitle(tagsList[position]);
            drawerLayout.closeDrawer(drawerListView);
        }
    public void createGameFragmentTransition(){
        fragmentManager.beginTransaction().replace(R.id.drawer_container, createGameFragment).commit();
    }
    public void viewGamesFragmentTransition(){
        fragmentManager.beginTransaction().replace(R.id.drawer_container, gamesOfPlaceFragment).commit();
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
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }
}
