package ca.ftcalberta.rrlivescore;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import ca.ftcalberta.rrlivescore.models.Alliance;
import ca.ftcalberta.rrlivescore.models.CurrentUser;
import ca.ftcalberta.rrlivescore.models.Settings;

public class ScoringActivity extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener,
        ViewPager.OnPageChangeListener {

    private static final int AUTO_FRAGMENT_ID = 0;
    private static final int TELE_FRAGMENT_ID = 1;

    @BindView(R.id.fragment_container)
    ViewPager viewPager;

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    ViewPagerAdapter adapter;

    private Alliance alliance;
    private int cryptoboxId;

    private AutonomousFragment autonomousFragment;
    private TeleopFragment teleopFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Settings settings = Settings.getInstance();
        alliance = settings.getAlliance();
        cryptoboxId = settings.getCryptoboxId();

        if (alliance.isRed()) {
            setTheme(R.style.AppTheme_Red);
        }
        else {
            setTheme(R.style.AppTheme_Blue);
        }

        setContentView(R.layout.activity_scoring);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.scoring_toolbar);
        setSupportActionBar(myToolbar);

        ButterKnife.bind(this);

        autonomousFragment = new AutonomousFragment();
        teleopFragment = new TeleopFragment();

        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(autonomousFragment, "Autonomous");
        adapter.addFragment(teleopFragment, "Teleop");
        setTitle("Autonomous");

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);

        navigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Settings currentSettings = Settings.getInstance();
        if (currentSettings.getAlliance() != alliance
                || currentSettings.getCryptoboxId() != cryptoboxId) {
            alliance = currentSettings.getAlliance();
            cryptoboxId = currentSettings.getCryptoboxId();
            reset();
            finish();
            startActivity(getIntent());
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.navigation_autonomous:
                if (viewPager.getCurrentItem() != AUTO_FRAGMENT_ID) {
                    viewPager.setCurrentItem(AUTO_FRAGMENT_ID);
                }
                return true;
            case R.id.navigation_teleop:
                if (viewPager.getCurrentItem() != TELE_FRAGMENT_ID) {
                    viewPager.setCurrentItem(TELE_FRAGMENT_ID);
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
            case AUTO_FRAGMENT_ID:
                navigation.setSelectedItemId(R.id.navigation_autonomous);
                setTitle(adapter.getPageTitle(AUTO_FRAGMENT_ID));
                break;
            case TELE_FRAGMENT_ID:
                navigation.setSelectedItemId(R.id.navigation_teleop);
                setTitle(adapter.getPageTitle(TELE_FRAGMENT_ID));
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
            case R.id.action_reset:
                confirmReset();
                return true;
            case R.id.action_settings:
                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                startActivity(settingsIntent);
                return true;
            case R.id.action_logout:
                CurrentUser.signOut();
                Intent backToLogin = new Intent(this, LoginActivity.class);
                startActivity(backToLogin);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Confirm before resetting
     */
    private void confirmReset() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Confirm Reset?");
        alert.setMessage("You will lose the currently tracked state.");
        alert.setPositiveButton("Reset", new Dialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (viewPager.getCurrentItem() == TELE_FRAGMENT_ID) {
                    viewPager.setCurrentItem(AUTO_FRAGMENT_ID);
                }
                reset();
                dialog.dismiss();
            }
        });
        alert.setNegativeButton("Cancel", new Dialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alert.show();
    }

    public void reset() {
        autonomousFragment.reset();
        teleopFragment.reset();
    }
}
