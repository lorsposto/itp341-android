package itp341.sposto.lorraine.a6;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by LorraineSposto on 3/4/16.
 */
public class ColorActivity extends Activity {
    private static final String TAG = "A6.ColorActivity";
    public static final int MY_CODE = 1;
    private SeekBar seekR, seekG, seekB;
    private TextView colorBox;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.color_layout);
        Log.d(TAG, "OnCreate called Color");

        Intent data = getIntent();
        final int numComplete = data.getIntExtra(MainActivity.NUM_COMPLETED_KEY, 0);
        final int[] answer = data.getIntArrayExtra(MainActivity.ANSWER_KEY);

        seekR = (SeekBar) findViewById(R.id.rSeekBar);
        seekG = (SeekBar) findViewById(R.id.gSeekBar);
        seekB = (SeekBar) findViewById(R.id.bSeekBar);
        colorBox = (TextView) findViewById(R.id.colorBoxTextView);

        seekR.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                TextView tv = (TextView) findViewById(R.id.seekRTextView);
                tv.setText(Integer.toString(progress));
                updateColor();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekG.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                TextView tv = (TextView) findViewById(R.id.seekGTextView);
                tv.setText(Integer.toString(progress));
                updateColor();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                TextView tv = (TextView) findViewById(R.id.seekBTextView);
                tv.setText(Integer.toString(progress));
                updateColor();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Button setChangesButton = (Button) findViewById(R.id.setChangesColor);
        setChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra(MainActivity.ANSWER_KEY, answer);
                data.putExtra(MainActivity.NUM_COMPLETED_KEY, numComplete);
                data.putExtra(MainActivity.SUBMITTED_ANSWER_KEY, getRGB());
                setResult(MY_CODE, data);
                finish();
            }
        });
    }

    private int[] getRGB() {
        int[] rgb = {0, 0, 0};
        rgb[0] = seekR.getProgress();
        rgb[1] = seekG.getProgress();
        rgb[2] = seekB.getProgress();
        return rgb;
    }

    private void updateColor() {
        int[] rgb = getRGB();
        colorBox.setBackgroundColor(Color.rgb(rgb[0], rgb[1], rgb[2]));
    }
}
