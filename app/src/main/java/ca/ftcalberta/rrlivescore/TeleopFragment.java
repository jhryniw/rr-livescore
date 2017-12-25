package ca.ftcalberta.rrlivescore;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import ca.ftcalberta.rrlivescore.data.SyncedCryptobox;
import ca.ftcalberta.rrlivescore.models.Alliance;
import ca.ftcalberta.rrlivescore.models.Cryptobox;
import ca.ftcalberta.rrlivescore.models.Glyph;
import ca.ftcalberta.rrlivescore.models.Settings;

public class TeleopFragment extends Fragment
    implements View.OnClickListener {

    private Cryptobox mCryptobox;

    @BindView(R.id.glyph00)
    Button btnGlyph00;

    public TeleopFragment() {

    }

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
        this.mCryptobox = new SyncedCryptobox(appSettings.getAlliance(), appSettings.getCryptoboxId());

        ButterKnife.bind(this, view);
        btnGlyph00.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.glyph00:
                mCryptobox.toggleGlyph(0, 0);

                Glyph glyph = mCryptobox.getGlyph(0, 0);

                if (glyph != null) {
                    btnGlyph00.setBackgroundColor(glyph.getColor().toColor());
                }
                else {
                    btnGlyph00.setBackgroundColor(Color.WHITE);
                }
        }
    }
}
