package ca.ftcalberta.rrlivescore.models;


import android.content.Context;
import android.content.SharedPreferences;

public class Settings {

    private static final String SETTINGS_PREF_KEY = "RR_LIVESCORE_SETTINGS";
    private static final String ALLIANCE_KEY = "ALLIANCE";
    private static final String CRYPTOBOX_ID_KEY = "CRYPTOBOX_ID";

    public static final int CRYPTOBOX_FRONT = 0;
    public static final int CRYPTOBOX_BACK = 1;

    private static Settings instance;

    private SharedPreferences preferences;
    private Alliance alliance;
    private int cryptoboxId;

    private Settings(Alliance alliance, int cryptoboxId) {
        this.alliance = alliance;
        this.cryptoboxId = cryptoboxId;
    }

    public static Settings getInstance(Context context) {
        if (instance != null) {
            return instance;
        }

        SharedPreferences savedPrefs = getPreferences(context);
        Alliance savedAlliance = Enum.valueOf(Alliance.class,
                savedPrefs.getString(ALLIANCE_KEY, Alliance.BLUE.toString()).toUpperCase());
        int savedCryptoboxId = savedPrefs.getInt(CRYPTOBOX_ID_KEY, CRYPTOBOX_FRONT);

        instance = new Settings(savedAlliance, savedCryptoboxId);
        return instance;
    }

    public static Settings getInstance() {
        if (instance != null) {
            return instance;
        }

        instance = new Settings(Alliance.BLUE, CRYPTOBOX_FRONT);
        return instance;
    }

    public Alliance getAlliance() {
        return alliance;
    }

    public void setAlliance(Context context, Alliance alliance) {
        if (alliance == Alliance.BLUE || alliance == Alliance.RED
                && context != null) {
            this.alliance = alliance;

            SharedPreferences.Editor editor = getPreferences(context).edit();
            editor.putString(ALLIANCE_KEY, alliance.toString());
            editor.apply();
        }
    }

    public int getCryptoboxId() {
        return cryptoboxId;
    }

    public void setCryptoboxId(Context context, int cryptoboxId) {
        if ((cryptoboxId == CRYPTOBOX_FRONT || cryptoboxId == CRYPTOBOX_BACK)
                && context != null) {
            this.cryptoboxId = cryptoboxId;

            SharedPreferences.Editor editor = getPreferences(context).edit();
            editor.putInt(CRYPTOBOX_ID_KEY, cryptoboxId);
            editor.apply();
        }
    }

    private static SharedPreferences getPreferences(Context context) {
        return context.getApplicationContext()
                .getSharedPreferences(SETTINGS_PREF_KEY, Context.MODE_PRIVATE);
    }
}
