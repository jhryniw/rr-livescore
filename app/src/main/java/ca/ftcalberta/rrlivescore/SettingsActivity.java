package ca.ftcalberta.rrlivescore;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import ca.ftcalberta.rrlivescore.models.Alliance;
import ca.ftcalberta.rrlivescore.models.CurrentUser;
import ca.ftcalberta.rrlivescore.models.Settings;


public class SettingsActivity extends AppCompatActivity
    implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.radio_blue_alliance)
    RadioButton radioBlueAlliance;
    @BindView(R.id.radio_red_alliance)
    RadioButton radioRedAlliance;

    @BindView(R.id.radio_front)
    RadioButton radioFrontCryptobox;
    @BindView(R.id.radio_back)
    RadioButton radioBackCryptobox;

    private Settings appSettings;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Alliance alliance = Settings.getInstance().getAlliance();
        if (alliance == Alliance.RED) {
            setTheme(R.style.AppTheme_Red);
        }
        else {
            setTheme(R.style.AppTheme_Blue);
        }

        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.settings_toolbar);
        setSupportActionBar(myToolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        RadioGroup allianceSelector = (RadioGroup) findViewById(R.id.alliance_selector);
        RadioGroup cryptoboxIdSelector = (RadioGroup) findViewById(R.id.cryptobox_selector);

        appSettings = Settings.getInstance(this);

        if (appSettings.getAlliance() == Alliance.RED &&
                !radioRedAlliance.isChecked()) {
            radioRedAlliance.setChecked(true);
        }
        else if (!radioBlueAlliance.isChecked()){
            radioBlueAlliance.setChecked(true);
        }

        if (appSettings.getCryptoboxId() == Settings.CRYPTOBOX_BACK
                && !radioBackCryptobox.isChecked()) {
            radioBackCryptobox.setChecked(true);
        }
        else if (!radioFrontCryptobox.isChecked()){
            radioFrontCryptobox.setChecked(true);
        }

        allianceSelector.setOnCheckedChangeListener(this);
        cryptoboxIdSelector.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId) {
            case R.id.radio_blue_alliance:
                appSettings.setAlliance(this, Alliance.BLUE);
                recreate();
                break;
            case R.id.radio_red_alliance:
                appSettings.setAlliance(this, Alliance.RED);
                recreate();
                break;
            case R.id.radio_front:
                appSettings.setCryptoboxId(this, Settings.CRYPTOBOX_FRONT);
                break;
            case R.id.radio_back:
                appSettings.setCryptoboxId(this, Settings.CRYPTOBOX_BACK);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_appbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
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

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
