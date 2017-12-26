package ca.ftcalberta.rrlivescore;


//import android.graphics.Color;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import ca.ftcalberta.rrlivescore.data.SyncedCryptobox;
import ca.ftcalberta.rrlivescore.models.Alliance;
import ca.ftcalberta.rrlivescore.models.Cryptobox;
import ca.ftcalberta.rrlivescore.models.Glyph;
import ca.ftcalberta.rrlivescore.models.OpMode;
import ca.ftcalberta.rrlivescore.models.Settings;

public class TeleopFragment extends Fragment implements
        View.OnClickListener,
        View.OnLongClickListener {

    private Cryptobox mCryptobox;

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

    Pattern glyphPattern = Pattern.compile("^glyph(\\d)(\\d)$");

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
        View view = inflater.inflate(R.layout.fragment_teleop, container, false);

        Settings appSettings = Settings.getInstance();
        this.mCryptobox = new SyncedCryptobox(appSettings.getAlliance(), OpMode.TELEOP, appSettings.getCryptoboxId());

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

        return view;
    }


    @Override
    public void onClick(View view) {
        String tag = (String)view.getTag();

        Matcher glyphMatcher = glyphPattern.matcher(tag);
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
}
