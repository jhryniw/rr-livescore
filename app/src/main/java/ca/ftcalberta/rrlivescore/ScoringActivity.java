package ca.ftcalberta.rrlivescore;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScoringActivity extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener,
        ViewPager.OnPageChangeListener {

    @BindView(R.id.fragment_container)
    ViewPager viewPager;

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoring);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.scoring_toolbar);
        setSupportActionBar(myToolbar);

        ButterKnife.bind(this);

        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new AutonomousFragment(), "Autonomous");
        adapter.addFragment(new TeleopFragment(), "Teleop");

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);

        navigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.navigation_autonomous:
                if (viewPager.getCurrentItem() != 0) {
                    viewPager.setCurrentItem(0);
                }
                return true;
            case R.id.navigation_teleop:
                if (viewPager.getCurrentItem() != 1) {
                    viewPager.setCurrentItem(1);
                }
                return true;
        }
        return false;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                navigation.setSelectedItemId(R.id.navigation_autonomous);
                break;
            case 1:
                navigation.setSelectedItemId(R.id.navigation_teleop);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.scoring_appbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
