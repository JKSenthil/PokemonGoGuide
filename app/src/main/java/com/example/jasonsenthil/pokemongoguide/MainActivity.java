package com.example.jasonsenthil.pokemongoguide;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private static final float BYTES_PER_PX = 4.0f;
    ImageView image, image2;


    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    FragmentTransaction fragmentTransaction;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_clse);

        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_container, new InformationFragment());
        fragmentTransaction.commit();
        getSupportActionBar().setTitle("Information");

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.information_id:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new InformationFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Information");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.systemspecs_id:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new SystemSpecsFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("System Specs");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.teams_id:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new TeamsFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Teams");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.eggs_id:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new EggFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Eggs");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.catching_id:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new CatchingPokemonFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Catching Pokemon");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.gyms_id:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new GymFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Gyms");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.pokestop_id:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new PokeStopFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("PokeStops");
                        item.setChecked(true);
                        drawerLayout.closeDrawer();
                        break;
                    case R.id.level_id:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new LevelFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Leveling and Exp");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.cp_id:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new CPFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Combat Points");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.items_id:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new ItemFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Items");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.shop_id:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new ShopFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Shop");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.pokemonlist_id:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new PokeListFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Pokemon List #(1-75)");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.pokemonlist2_id:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new PokeListTwoFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Pokemon List #(76-126)");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.pokemonlist3_id:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new PokeListThreeFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Pokemon List #(126-151)");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                }
                return true;
            }
        });
    }

    private void loadImage(){
        if(readBitmapInfo() > MemUtils.megabytesFree()){
            subSampleImage(32);
        }else{
            image2.setImageResource(R.drawable.teams);
        }
    }

    private float readBitmapInfo(){
        final Resources res = this.getResources();
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, R.drawable.teams, options);
        final float imageHeight = options.outHeight;
        final float imageWidth = options.outWidth;
        final String imageMimeType = options.outMimeType;
        return imageWidth*imageHeight*BYTES_PER_PX/MemUtils.BYTES_IN_MB;
    }

    private void subSampleImage(int powerOf2){
        if(powerOf2 < 1 || powerOf2 > 32){
            return;
        }
        final Resources res = this.getResources();
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = powerOf2;
        final Bitmap bmp = BitmapFactory.decodeResource(res, R.drawable.teams);

        image2.setImageBitmap(bmp);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }
}
