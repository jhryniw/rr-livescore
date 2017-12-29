package ca.ftcalberta.rrlivescore;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import ca.ftcalberta.rrlivescore.data.ScoreButton;
import ca.ftcalberta.rrlivescore.data.SyncedCryptobox;
import ca.ftcalberta.rrlivescore.data.SyncedRelic;
import ca.ftcalberta.rrlivescore.models.Alliance;
import ca.ftcalberta.rrlivescore.models.Cryptobox;
import ca.ftcalberta.rrlivescore.models.Glyph;
import ca.ftcalberta.rrlivescore.models.Relic;
import ca.ftcalberta.rrlivescore.models.OpMode;
import ca.ftcalberta.rrlivescore.models.Settings;

public class TeleopFragment extends Fragment implements
        View.OnClickListener,
        View.OnLongClickListener {

    private Cryptobox mCryptobox;
    private Relic mRelic;
    private ScoreButton scoreBalance;
    private boolean isBalanced;

    private ArrayList<Button> glyphButtons;

    @BindView(R.id.glyph00) Button btnGlyph00;
    @BindView(R.id.glyph01) Button btnGlyph01;
    @BindView(R.id.glyph02) Button btnGlyph02;
    @BindView(R.id.glyph10) Button btnGlyph10;
    @BindView(R.id.glyph11) Button btnGlyph11;
    @BindView(R.id.glyph12) Button btnGlyph12;
    @BindView(R.id.glyph20) Button btnGlyph20;
    @BindView(R.id.glyph21) Button btnGlyph21;
    @BindView(R.id.glyph22) Button btnGlyph22;
    @BindView(R.id.glyph30) Button btnGlyph30;
    @BindView(R.id.glyph31) Button btnGlyph31;
    @BindView(R.id.glyph32) Button btnGlyph32;

    @BindView(R.id.zone_1) Button btnZone1;
    @BindView(R.id.zone_2) Button btnZone2;
    @BindView(R.id.zone_3) Button btnZone3;
    @BindView(R.id.balance) ImageButton btnBalance;

    Pattern glyphPattern = Pattern.compile("^glyph(\\d)(\\d)$");
    Pattern zonePattern = Pattern.compile("^zone_(\\d)$");

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            return;
        }

        Settings appSettings = Settings.getInstance();
        this.mCryptobox = new SyncedCryptobox(appSettings.getAlliance(), OpMode.TELEOP, appSettings.getCryptoboxId());
        this.mRelic = new SyncedRelic(appSettings.getAlliance());
        this.scoreBalance = new ScoreButton("balance");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teleop, container, false);

        ButterKnife.bind(this, view);

        glyphButtons = new ArrayList<>(12);
        glyphButtons.add(btnGlyph00);
        glyphButtons.add(btnGlyph01);
        glyphButtons.add(btnGlyph02);
        glyphButtons.add(btnGlyph10);
        glyphButtons.add(btnGlyph11);
        glyphButtons.add(btnGlyph12);
        glyphButtons.add(btnGlyph20);
        glyphButtons.add(btnGlyph21);
        glyphButtons.add(btnGlyph22);
        glyphButtons.add(btnGlyph30);
        glyphButtons.add(btnGlyph31);
        glyphButtons.add(btnGlyph32);

        for(Button glyphButton : glyphButtons) {
            glyphButton.setOnClickListener(this);
            glyphButton.setOnLongClickListener(this);
        }

        btnZone1.setOnClickListener(this);
        btnZone2.setOnClickListener(this);
        btnZone3.setOnClickListener(this);
        btnBalance.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View view) {
        String tag = (String)view.getTag();
        Settings appSettings = Settings.getInstance();

        Matcher glyphMatcher = glyphPattern.matcher(tag);
        Matcher zoneMatcher = zonePattern.matcher(tag);

        if(glyphMatcher.matches()){
            int row = Integer.parseInt(glyphMatcher.group(1));
            int col = Integer.parseInt(glyphMatcher.group(2));

            mCryptobox.toggleGlyph(row, col);

            Glyph glyph = mCryptobox.getGlyph(row, col);
            if (glyph.getColor() == Glyph.Color.BROWN) {
                int color = getResources().getColor(R.color.glyphBrown);
                view.setBackgroundColor(color);
            }
            else {
                int color = getResources().getColor(R.color.glyphGray);
                view.setBackgroundColor(color);
            }
        }
        else if(zoneMatcher.matches()){
            int zone = Integer.parseInt(zoneMatcher.group(1));
            btnZone1.setBackgroundResource(R.drawable.button_zone);
            btnZone2.setBackgroundResource(R.drawable.button_zone);
            btnZone3.setBackgroundResource(R.drawable.button_zone);

            if(mRelic.getZone() == zone) {
                if (mRelic.isUpright()) {
                    mRelic.setUpright(false);
                    view.setBackgroundResource(R.drawable.button_zone);
                    zone = 0;
                } else {
                    mRelic.setUpright(true);
                    view.setBackgroundResource(R.drawable.relic_upright);
                }
            } else {
                mRelic.setUpright(false);
                view.setBackgroundResource(R.drawable.relic_tipped);
            }
            mRelic.setZone(zone);
        } else if(tag.equals("balance")){
            isBalanced = !isBalanced;
            view.setSelected(!view.isSelected());
            scoreBalance.updateScore(isBalanced ? 30: 0);
        }
    }

    @Override
    public boolean onLongClick(View view) {
        String tag = (String)view.getTag();

        Matcher glyphMatcher = glyphPattern.matcher(tag);
        if(glyphMatcher.matches()){
            int row = Integer.parseInt(glyphMatcher.group(1));
            int col = Integer.parseInt(glyphMatcher.group(2));

            mCryptobox.removeGlyph(row, col);
            view.setBackgroundResource(R.drawable.glyph_button);
            return true;
        }
        return false;
    }

    public void reset() {
        // Reset cryptobox
        if (mCryptobox != null) {
            mCryptobox.reset();

            for (Button glyphButton : glyphButtons) {
                glyphButton.setBackgroundResource(R.drawable.glyph_button);
            }
        }
        if(mRelic != null){
            mRelic.reset();

            btnZone1.setBackgroundResource(R.drawable.button_zone);
            btnZone2.setBackgroundResource(R.drawable.button_zone);
            btnZone3.setBackgroundResource(R.drawable.button_zone);
        }

        isBalanced = false;
        btnBalance.setSelected(false);
        scoreBalance.reset();
    }
}
