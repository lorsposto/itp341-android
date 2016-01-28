package ipt341.sposto.lorraine.a1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mButton1;
    private Button mButton2;

    private static final int BUTTON1 = 1;
    private static final int BUTTON2 = 2;

    View.OnClickListener mButtonListener1 = new View.OnClickListener() {
        private int clicks = 0;
        private int which = BUTTON1;
        @Override
        public void onClick(View v) {
            buttonHandler(v, which, ++clicks);
        }
    };

    View.OnClickListener mButtonListener2 = new View.OnClickListener() {
        private int clicks = 0;
        private int which = BUTTON2;
        @Override
        public void onClick(View v) {
            buttonHandler(v, which, ++clicks);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set listeners
        mButton1 = (Button) findViewById(R.id.button);
        mButton1.setOnClickListener(mButtonListener1);

        mButton2 = (Button) findViewById(R.id.button2);
        mButton2.setOnClickListener(mButtonListener2);
    }

    public void buttonHandler(View view, int which, int clicks) {
        String text = "";
        switch (which) {
            case BUTTON1:
                text = getResources().getString(R.string.toast_button1);
                break;
            case BUTTON2:
                text = getResources().getString(R.string.toast_button2);
                break;
            default:
                text = getResources().getString(R.string.toast_button1);
                break;
        }
        Toast.makeText(this, String.format(text, clicks), Toast.LENGTH_SHORT).show();
    }
}
