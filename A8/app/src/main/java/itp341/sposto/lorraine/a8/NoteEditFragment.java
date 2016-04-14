package itp341.sposto.lorraine.a8;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import itp341.sposto.lorraine.a8.model.Note;
import itp341.sposto.lorraine.a8.model.NoteSingleton;

/**
 * Created by LorraineSposto on 3/26/16.
 */
public class NoteEditFragment extends Fragment {
    private static final String TAG = NoteEditFragment.class.getSimpleName();
    public static final String ARGS_POSITION = "args_position";

    EditText mEditTitle;
    EditText mEditContent;
    Button mButtonSave;
    Button mButtonDelete;

    int mPosition;

    public NoteEditFragment() {}

    public static NoteEditFragment newInstance() {
        Bundle args = new Bundle();

        NoteEditFragment f = new NoteEditFragment();
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        mPosition = -1;
        if (args != null) {
            mPosition = args.getInt(ARGS_POSITION, -1);
        }
        Log.d(TAG, "Got mPosition: " + mPosition);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_edit_list, container, false);

        mEditTitle = (EditText) v.findViewById(R.id.noteTitle);
        mEditContent = (EditText) v.findViewById(R.id.noteContent);
        mButtonSave = (Button) v.findViewById(R.id.noteSave);
        mButtonDelete = (Button) v.findViewById(R.id.noteDelete);


        //button listeners
        mButtonDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                deleteAndClose();
            }
        });
        mButtonSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                saveAndClose();
            }
        });

        if (mPosition != -1) {
            Note n = NoteSingleton.get(getActivity()).getNote(mPosition);
            if (n != null) {
                loadData(n);
            }
        }

        return v;
    }

    private void loadData(Note n) {
        mEditTitle.setText(n.getTitle());
        mEditContent.setText(n.getContent());
    }

    private void saveAndClose() {
        Log.d(TAG, "saveAndClose");
        Note n = new Note();

        n.setTitle(mEditTitle.getText().toString());
        n.setContent(mEditContent.getText().toString());

        if (mPosition == -1) {
            Log.d(TAG, "Adding note");
            NoteSingleton.get(getActivity()).addNote(n);
        }
        else {
            Log.d(TAG, "Updating note");
            NoteSingleton.get(getActivity()).updateNote(mPosition, n);
        }
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    //TODO Listing should be deleted (only it was an existing entry, not if it was new))
    private void deleteAndClose() {
        Log.d(TAG, "deleteAndClose");
        if (mPosition != -1) {
            NoteSingleton.get(getActivity()).removeNote(mPosition);
        }
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }
}
