package itp341.sposto.lorraine.a6;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "A6.MainActivity";
    private static final int MY_CODE = 0;
    public static final String ANSWER_KEY = "itp341.sposto.lorraine.A6.answer_key";
    public static final String SUBMITTED_ANSWER_KEY = "itp341.sposto.lorraine.A6.submitted_answer";
    public static final String NUM_COMPLETED_KEY = "itp341.sposto.lorraine.A6.num_completed";
    private int numCompleted = 0;
    private boolean[] gameCompleted = {false, false, false};

    private TextView colorText, sizeText, nameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button room1Button, room2Button, room3Button, solveButton;

        room1Button = (Button) findViewById(R.id.room1Button);
        room2Button = (Button) findViewById(R.id.room2Button);
        room3Button = (Button) findViewById(R.id.room3Button);
        solveButton = (Button) findViewById(R.id.solveButton);
        colorText = (TextView) findViewById(R.id.feelingBlueText);
        sizeText = (TextView) findViewById(R.id.beBigText);
        nameText = (TextView) findViewById(R.id.callMeAlText);

        room1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ColorActivity.class);
                i.putExtra(ANSWER_KEY, getResources().getIntArray(R.array.answer_color));
                i.putExtra(NUM_COMPLETED_KEY, numCompleted);
                startActivityForResult(i, MY_CODE);
            }
        });

        room2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SizeActivity.class);
                i.putExtra(ANSWER_KEY, getString(R.string.answer_size));
                i.putExtra(NUM_COMPLETED_KEY, numCompleted);
                startActivityForResult(i, MY_CODE);
            }
        });

        room3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), NameActivity.class);
                i.putExtra(ANSWER_KEY, getString(R.string.answer_name));
                i.putExtra(NUM_COMPLETED_KEY, numCompleted);
                startActivityForResult(i, MY_CODE);
            }
        });

        solveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), WinLoseActivity.class);
                i.putExtra(NUM_COMPLETED_KEY, numCompleted);
                startActivityForResult(i, MY_CODE);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            Bundle extras = data.getExtras();
            int numCompleted = extras.getInt(NUM_COMPLETED_KEY);
            try {
                if (resultCode == ColorActivity.MY_CODE) {
                    int[] submitted = data.getIntArrayExtra(SUBMITTED_ANSWER_KEY);
                    int[] answer = data.getIntArrayExtra(ANSWER_KEY);

                    if (Arrays.equals(submitted, answer)) { // answer is same as submitted
                        Log.d(TAG, "@@@@@@ Answers are the same!");
                        Log.d(TAG, "@@@@@@ Code comparison: " + resultCode + " vs " + numCompleted);
                        if (resultCode == numCompleted+1) { // if room # completed is the right order
                            gameCompleted[resultCode-1] = true;
                            this.numCompleted = numCompleted + 1;
                        }
                    }
                }
                else {
                    String submitted = extras.getString(SUBMITTED_ANSWER_KEY);
                    String answer = extras.getString(ANSWER_KEY);

                    Log.d(TAG, "@@@@@@ Answer comparison: " + submitted.toString() + ", " + answer.toString());
                    if (submitted.equals(answer)) { // answer is same as submitted
                        Log.d(TAG, "@@@@@@ Answers are the same!");
                        Log.d(TAG, "@@@@@@ Code comparison: " + resultCode + " vs " + numCompleted);
                        if (resultCode == numCompleted+1) { // if room # completed is the right order
                            gameCompleted[resultCode-1] = true;
                            this.numCompleted = numCompleted + 1;
                        }
                    }
                }
            } catch (NullPointerException n) {
                Log.d(TAG, "Null pointer exception" + n.getMessage());
            }
        }
        updateTextColor();
    }

    private void updateTextColor() {
        if (gameCompleted[SizeActivity.MY_CODE-1]) {
            sizeText.setTextColor(Color.GREEN);
        }
        if (gameCompleted[NameActivity.MY_CODE-1]) {
            nameText.setTextColor(Color.GREEN);
        }
        if (gameCompleted[ColorActivity.MY_CODE-1]) {
            colorText.setTextColor(Color.GREEN);
        }
    }
}
