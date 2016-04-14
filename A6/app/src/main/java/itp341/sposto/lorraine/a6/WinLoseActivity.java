package itp341.sposto.lorraine.a6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by LorraineSposto on 3/4/16.
 */
public class WinLoseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.winlose_layout);

        Intent data = getIntent();
        int numCompleted = data.getIntExtra(MainActivity.NUM_COMPLETED_KEY, 0);

        String text = getString(R.string.lose_text);
        if (numCompleted == 3) {
            text = getString(R.string.win_text);
        }
        TextView winLoseText = (TextView) findViewById(R.id.winLoseText);
        winLoseText.setText(text);
    }
}
