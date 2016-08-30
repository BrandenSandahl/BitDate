package com.sixtel.bitdate;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.parse.Parse;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ImageView mChoosingIcon;
    private ImageView mMatchesIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //sign in if no user is signed in already
        if (UserDAO.getCurrentUser() == null) {
            Intent i = new Intent(this, SignInActivity.class );
            startActivity(i);
        }

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        pager.setOnPageChangeListener(this);

        mChoosingIcon = (ImageView) findViewById(R.id.logo_icon);
        mMatchesIcon = (ImageView) findViewById(R.id.chat_icon);
        mChoosingIcon.setSelected(true);
        toggleColor(mChoosingIcon);
        toggleColor(mMatchesIcon);


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mChoosingIcon.setSelected(!mChoosingIcon.isSelected());
        mMatchesIcon.setSelected(!mMatchesIcon.isSelected());

        toggleColor(mChoosingIcon);
        toggleColor(mMatchesIcon);
    }

    private void toggleColor(ImageView v) {
        if (v.isSelected()) {
            v.setColorFilter(Color.WHITE);
         } else {
            v.setColorFilter(getResources().getColor(R.color.colorPrimaryDark));
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    public class PagerAdapter extends FragmentStatePagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new MainActivityFragment();
                case 1:
                    return new MatchesFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
