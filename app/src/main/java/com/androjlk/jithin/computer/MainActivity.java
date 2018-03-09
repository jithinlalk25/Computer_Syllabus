package com.androjlk.jithin.computer;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private Toolbar mToolbar;
    Bundle args = new Bundle();
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.fragment_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);

        Fragment detail = new DetailFragment();
        detail.setArguments(args);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, detail).commit();


        SharedPreferences prefs = getSharedPreferences("sem", MODE_PRIVATE);
        int b = prefs.getInt("b", 0);

        spinner = (Spinner) findViewById(R.id.spinner);
        final List<String> categories = new ArrayList<String>();
        categories.add("S3");
        categories.add("S4");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, categories);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);
        spinner.setSelection(b);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapter, View v,
                                       int position, long id) {

int b;
                switch (position) {
                    case 0:
                        SharedPreferences prefs = getSharedPreferences("sem", MODE_PRIVATE);
                        b = prefs.getInt("b", 0);
                        if(b!=0) {
                            SharedPreferences.Editor editor = getSharedPreferences("sem", MODE_PRIVATE).edit();
                            editor.putInt("b", 0);
                            editor.commit();

                            FragmentManager fm = getFragmentManager();
                            NavigationDrawerFragment fragment = (NavigationDrawerFragment) fm.findFragmentById(R.id.fragment_drawer);
                            fragment.zxc(0);

                            mNavigationDrawerFragment.openDrawer();
                        }
                    break;
                    case 1:
                        SharedPreferences pref = getSharedPreferences("sem", MODE_PRIVATE);
                        b = pref.getInt("b", 0);
                        if(b!=1) {
                            SharedPreferences.Editor editor = getSharedPreferences("sem", MODE_PRIVATE).edit();
                            editor.putInt("b", 1);
                            editor.commit();

                            FragmentManager fm = getFragmentManager();
                            NavigationDrawerFragment fragment = (NavigationDrawerFragment) fm.findFragmentById(R.id.fragment_drawer);
                            fragment.zxc(1);

                            mNavigationDrawerFragment.openDrawer();
                        }
                    break;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        if(position!=9){
        SharedPreferences.Editor editor = getSharedPreferences("sub",MODE_PRIVATE).edit();
        editor.putInt("c", position);
        editor.commit();}
        // update the main content by replacing fragments
        //Toast.makeText(this, "Menu item selected -> " + position, Toast.LENGTH_SHORT).show();
        SharedPreferences prefs = getSharedPreferences("sem", MODE_PRIVATE);
        int b = prefs.getInt("b", 0);

        if(b==0) {
            switch (position) {
                case 0:
                    args.putString("Menu", getString(R.string.linear));
                    break;
                case 1:
                    args.putString("Menu", getString(R.string.discrete));
                    break;
                case 2:
                    args.putString("Menu", getString(R.string.switching));
                    break;
                case 3:
                    args.putString("Menu", getString(R.string.data));
                    break;
                case 4:
                    args.putString("Menu", getString(R.string.electronics));
                    break;
                case 5:
                    args.putString("Menu", getString(R.string.lifeskill));
                    break;
                case 6:
                    args.putString("Menu", getString(R.string.business));
                    break;
                case 7:
                    args.putString("Menu", getString(R.string.datalab));
                    break;
                case 8:
                    args.putString("Menu", getString(R.string.electronicslab));
                    break;
                case 9:
                    args.putString("Menu", getString(R.string.about));
                    break;

            }
        }
        else {
            switch (position) {
                case 0:
                    args.putString("Menu", getString(R.string.probability));
                    break;
                case 1:
                    args.putString("Menu", getString(R.string.computer));
                    break;
                case 2:
                    args.putString("Menu", getString(R.string.operating));
                    break;
                case 3:
                    args.putString("Menu", getString(R.string.object));
                    break;
                case 4:
                    args.putString("Menu", getString(R.string.principles));
                    break;
                case 5:
                    args.putString("Menu", getString(R.string.business));
                    break;
                case 6:
                    args.putString("Menu", getString(R.string.lifeskill));
                    break;
                case 7:
                    args.putString("Menu", getString(R.string.free));
                    break;
                case 8:
                    args.putString("Menu", getString(R.string.digital));
                    break;
                case 9:
                    args.putString("Menu", getString(R.string.about));
                    break;

            }
        }
        Fragment detail = new DetailFragment();
        detail.setArguments(args);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, detail).commit();
    }


    @Override
    public void onBackPressed() {
        if (mNavigationDrawerFragment.isDrawerOpen())
            mNavigationDrawerFragment.closeDrawer();
        else
            super.onBackPressed();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
   //     if (id == R.id.action_settings) {
   //         return true;
   //     }

        return super.onOptionsItemSelected(item);
    }


}
