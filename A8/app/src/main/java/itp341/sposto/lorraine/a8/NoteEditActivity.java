package itp341.sposto.lorraine.a8;

/**
 * Created by LorraineSposto on 3/26/16.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;


public class NoteEditActivity extends FragmentActivity {

    public static final String TAG = NoteEditActivity.class.getSimpleName();

    public static final String EXTRA_POSITION = "extra_position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        Intent i = getIntent();
        int position = -1;
        if (i != null) {
            position = i.getIntExtra(EXTRA_POSITION, -1);
            Log.d(TAG, "Detail activity got position: " + position);
        }

        FragmentManager fm = getSupportFragmentManager();
        Fragment f = fm.findFragmentById(R.id.fragment_container);

        if (f == null ) {
            f = new NoteEditFragment();
        }
        Bundle bundle = new Bundle();
        bundle.putInt(NoteEditFragment.ARGS_POSITION, position);
        f.setArguments(bundle);
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, f);
        fragmentTransaction.commit();
    }


}


