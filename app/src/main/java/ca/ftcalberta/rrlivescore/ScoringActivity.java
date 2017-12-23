package ca.ftcalberta.rrlivescore;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScoringActivity extends FragmentActivity
    implements BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.fragment_container)
    ViewPager viewPager;

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoring);
        ButterKnife.bind(this);

        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new AutonomousFragment(), "Autonomous");
        adapter.addFragment(new TeleopFragment(), "Teleop");

        viewPager.setAdapter(adapter);

        navigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_autonomous:
                viewPager.setCurrentItem(0);
                return true;
            case R.id.navigation_teleop:
                viewPager.setCurrentItem(1);
                return true;
        }
        return false;
    }
}
