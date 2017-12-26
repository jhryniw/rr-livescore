package ca.ftcalberta.rrlivescore;


import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import ca.ftcalberta.rrlivescore.data.SyncedCryptobox;
import ca.ftcalberta.rrlivescore.models.Alliance;
import ca.ftcalberta.rrlivescore.models.Cryptobox;
import ca.ftcalberta.rrlivescore.models.Glyph;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import butterknife.BindView;
import ca.ftcalberta.rrlivescore.models.Jewel;
import ca.ftcalberta.rrlivescore.models.JewelSet;
import ca.ftcalberta.rrlivescore.models.OpMode;
import ca.ftcalberta.rrlivescore.models.Relic;
import ca.ftcalberta.rrlivescore.models.Settings;

public class AutonomousFragment extends Fragment implements
        View.OnClickListener,
        View.OnLongClickListener {

    private Cryptobox mCryptobox;
    private JewelSet mJewelSet;
    private boolean safeZone = false;
    private boolean redJewelOnPlatform = true;
    private boolean blueJewelOnPlatform = true;

    @BindView(R.id.glyph00)
    Button btnGlyph00;
    @BindView(R.id.glyph01)
    Button btnGlyph01;
    @BindView(R.id.glyph02)
    Button btnGlyph02;
    @BindView(R.id.glyph10)
    Button btnGlyph10;
    @BindView(R.id.glyph11)
    Button btnGlyph11;
    @BindView(R.id.glyph12)
    Button btnGlyph12;
    @BindView(R.id.glyph20)
    Button btnGlyph20;
    @BindView(R.id.glyph21)
    Button btnGlyph21;
    @BindView(R.id.glyph22)
    Button btnGlyph22;
    @BindView(R.id.glyph30)
    Button btnGlyph30;
    @BindView(R.id.glyph31)
    Button btnGlyph31;
    @BindView(R.id.glyph32)
    Button btnGlyph32;

    @BindView(R.id.red_jewel)
    ImageButton btnRedJewel;
    @BindView(R.id.blue_jewel)
    ImageButton btnBlueJewel;
    @BindView(R.id.safe_zone)
    ImageButton btnSafeZone;

    Pattern glyphPattern = Pattern.compile("^glyph(\\d)(\\d)$");
    Pattern jewelPattern = Pattern.compile("^jewel_(blue|red)$");

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            return;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_autonomous, container, false);

        Settings appSettings = Settings.getInstance();
        this.mCryptobox = new SyncedCryptobox(appSettings.getAlliance(), OpMode.AUTONOMOUS, appSettings.getCryptoboxId());
        this.mJewelSet = new JewelSet();

        ButterKnife.bind(this, view);
        btnGlyph00.setOnClickListener(this);
        btnGlyph01.setOnClickListener(this);
        btnGlyph02.setOnClickListener(this);
        btnGlyph10.setOnClickListener(this);
        btnGlyph11.setOnClickListener(this);
        btnGlyph12.setOnClickListener(this);
        btnGlyph20.setOnClickListener(this);
        btnGlyph21.setOnClickListener(this);
        btnGlyph22.setOnClickListener(this);
        btnGlyph30.setOnClickListener(this);
        btnGlyph31.setOnClickListener(this);
        btnGlyph32.setOnClickListener(this);

        btnGlyph00.setOnLongClickListener(this);
        btnGlyph01.setOnLongClickListener(this);
        btnGlyph02.setOnLongClickListener(this);
        btnGlyph10.setOnLongClickListener(this);
        btnGlyph11.setOnLongClickListener(this);
        btnGlyph12.setOnLongClickListener(this);
        btnGlyph20.setOnLongClickListener(this);
        btnGlyph21.setOnLongClickListener(this);
        btnGlyph22.setOnLongClickListener(this);
        btnGlyph30.setOnLongClickListener(this);
        btnGlyph31.setOnLongClickListener(this);
        btnGlyph32.setOnLongClickListener(this);

        btnRedJewel.setOnClickListener(this);
        btnBlueJewel.setOnClickListener(this);
        btnSafeZone.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View view) {
        String tag = (String) view.getTag();

        Matcher glyphMatcher = glyphPattern.matcher(tag);
        Matcher jewelMatcher = jewelPattern.matcher(tag);

        if (glyphMatcher.matches()) {
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
        } else if(jewelMatcher.matches()){
            Alliance jewelAlliance = Alliance.fromString(jewelMatcher.group(1));

            if(mJewelSet.isOnPlatform(jewelAlliance)){
                view.setBackgroundColor(Color.TRANSPARENT);
            }
            else {
                if(jewelAlliance.isRed()){
                    view.setBackgroundResource(R.drawable.jewel_red);
                } else {
                    view.setBackgroundResource(R.drawable.jewel_blue);
                }
            }
            mJewelSet.toggleJewel(jewelAlliance);
        } else if(tag.equals("safe_zone")){
            if(safeZone){
                view.setBackgroundResource(R.drawable.safe_zone_blue);
            } else {
                view.setBackgroundResource(R.drawable.safe_zone_blue_robot);
            }
            safeZone = !safeZone;
            //todo: scoring for safezone
        }
    }

    @Override
    public boolean onLongClick(View view) {
        String tag = (String) view.getTag();

        Matcher glyphMatcher = glyphPattern.matcher(tag);
        if (glyphMatcher.matches()) {
            int row = Integer.parseInt(glyphMatcher.group(1));
            int col = Integer.parseInt(glyphMatcher.group(2));

            mCryptobox.removeGlyph(row, col);
            view.setBackgroundResource(R.drawable.glyph_button);
            return true;
        }
        return false;
    }
}
