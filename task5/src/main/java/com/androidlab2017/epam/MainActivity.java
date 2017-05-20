package com.androidlab2017.epam;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.androidlab2017.epam.custom_fragment.CustomViewFragment;
import com.androidlab2017.epam.second_fragment.SecondFragment;
import com.androidlab2017.epam.third_fragment.ThirdFragment;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    private static final String IMAGE_URL =
            "https://s-media-cache-ak0.pinimg.com/736x/7d/01/73/7d01733f266952973b88e6fb21cc7e84.jpg";
    private DrawerLayout mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewsById();
        initToolbar();
        initNavigationView();
    }

    
    private void findViewsById(){
        mDrawer = (DrawerLayout)findViewById(R.id.drawer_layout);
    }

    private void initToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void initNavigationView(){
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        LinearLayout headerContainer = (LinearLayout) navigationView.getHeaderView(0);
        ImageView imageView = (ImageView) headerContainer.findViewById(R.id.id_task5_image_view);
        Picasso.with(this).load(IMAGE_URL).into(imageView);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_custom_view:
                replaceToFragment(R.id.id_container_fragments, new CustomViewFragment());
                break;
            case R.id.nav_play:
                replaceToFragment(R.id.id_container_fragments, new SecondFragment());
                break;
            case R.id.nav_mute:
                replaceToFragment(R.id.id_container_fragments, new ThirdFragment());
                break;
        }

        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void replaceToFragment(int container, Fragment fragment){
        getSupportFragmentManager().
                beginTransaction().
                replace(container,fragment).
                commit();
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
