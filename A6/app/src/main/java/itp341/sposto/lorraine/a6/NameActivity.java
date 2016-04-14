package itp341.sposto.lorraine.a6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by LorraineSposto on 3/4/16.
 */
public class NameActivity extends Activity {
    private static final String TAG = "A6.NameActivity";
    public static final String SUBMITTED_ANSWER = "itp341.sposto.lorraine.A6.NameActivity.answer_key";
    public static final int MY_CODE = 3;

    private EditText nameInput;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.name_layout);
        Log.d(TAG, "On Create called Name");

        Intent data = getIntent();
        final int numComplete = data.getIntExtra(MainActivity.NUM_COMPLETED_KEY, 0);
        final String answer = data.getStringExtra(MainActivity.ANSWER_KEY);

        nameInput = (EditText) findViewById(R.id.nameEditText);
        Button saveChangesButton = (Button) findViewById(R.id.setChangesName);
        saveChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra(MainActivity.ANSWER_KEY, answer);
                data.putExtra(MainActivity.NUM_COMPLETED_KEY, numComplete);
                data.putExtra(MainActivity.SUBMITTED_ANSWER_KEY, getNameInput());
                setResult(MY_CODE, data);
                finish();
            }
        });

    }

    private String getNameInput() {
        return nameInput.getText().toString();
    }
}
