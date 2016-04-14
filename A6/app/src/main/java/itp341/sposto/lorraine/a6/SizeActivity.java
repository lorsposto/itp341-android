package itp341.sposto.lorraine.a6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

/**
 * Created by LorraineSposto on 3/4/16.
 */
public class SizeActivity extends Activity {
    private static final String TAG = "A6.SizeActivity";
    public static final int MY_CODE = 2;
    private Button setChangesButton;
    private Spinner sizeSpinner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.size_layout);
        Log.d(TAG, "onCreate called");
        Log.d(TAG, getCallingActivity().toString());

        Intent data = getIntent();
        final int numComplete = data.getIntExtra(MainActivity.NUM_COMPLETED_KEY, 0);
        final String answer = data.getStringExtra(MainActivity.ANSWER_KEY);

        sizeSpinner = (Spinner) findViewById(R.id.sizeSpinner);
        setChangesButton = (Button) findViewById(R.id.setChangesSize);
        setChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Setting changes");
                Intent data = new Intent();
                data.putExtra(MainActivity.SUBMITTED_ANSWER_KEY, getSetSize()); // return selected size
                data.putExtra(MainActivity.ANSWER_KEY, answer); // return answer from first activity
                data.putExtra(MainActivity.NUM_COMPLETED_KEY, numComplete); // answer num completed + 1
                setResult(MY_CODE, data);
                finish();
            }
        });
    }

    private String getSetSize() {
        return sizeSpinner.getSelectedItem().toString();
    }
}
