package com.androidlab2017.epam;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        LinearLayout headerContainer = (LinearLayout) navigationView.getHeaderView(0);
        ImageView imageView = (ImageView) headerContainer.findViewById(R.id.id_task5_image_view);
        Picasso.with(this).load("https://s-media-cache-ak0.pinimg.com/736x/7d/01/73/7d01733f266952973b88e6fb21cc7e84.jpg").into(imageView);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.nav_custom_view:
                getSupportFragmentManager().
                        beginTransaction().
                        replace(R.id.id_container_fragments,new CustomViewFragment()).
                        commit();
                break;
            case R.id.nav_play:
                getSupportFragmentManager().
                        beginTransaction().
                        replace(R.id.id_container_fragments,new SecondFragment()).
                        commit();
                break;
            case R.id.nav_mute:
                getSupportFragmentManager().
                        beginTransaction().
                        replace(R.id.id_container_fragments,new ThirdFragment()).
                        commit();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
