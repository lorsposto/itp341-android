package itp341.sposto.lorraine.a8;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import itp341.sposto.lorraine.a8.model.Note;
import itp341.sposto.lorraine.a8.model.NoteSingleton;

/**
 * Created by LorraineSposto on 3/26/16.
 */
public class NoteListFragment extends Fragment {

    private static final String TAG = NoteListFragment.class.getSimpleName();

    private Button mButtonAdd;
    private ListView mListView;

    private ArrayList<Note> mNotes;
    private NoteArrayAdapter mNoteAdapter;

    public NoteListFragment() {

    }


    public static NoteListFragment newInstance() {
        Bundle args = new Bundle();

        NoteListFragment f = new NoteListFragment();
        f.setArguments(args);

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_note_list, container, false);

        Log.d(TAG, "onCreateView");

        //find views
        mButtonAdd = (Button) v.findViewById(R.id.button_add);
        mListView = (ListView)v.findViewById(R.id.listView);

        mNotes = NoteSingleton.get(getActivity()).getNotes();
        mNoteAdapter = new NoteArrayAdapter(getContext(), mNotes);
        mListView.setAdapter(mNoteAdapter);


        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "mButtonadd: onClick ");
                Intent i = new Intent(getActivity(), NoteEditActivity.class);
                i.putExtra(NoteEditActivity.EXTRA_POSITION, -1);
                startActivityForResult(i, 0);
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Log.d(TAG, "mListView: onListItemClick");
                Intent i = new Intent(getActivity(), NoteEditActivity.class);
                Log.d(TAG, "List view position: " + position);
                i.putExtra(NoteEditActivity.EXTRA_POSITION, position);
                startActivityForResult(i, 0);
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mNotes = NoteSingleton.get(getActivity()).getNotes();
        Log.d(TAG, "onActivityResult: requestCode: " + requestCode);
        Log.d(TAG, "mNotes size: " + mNotes.size());
        mNoteAdapter.notifyDataSetChanged();
    }
}
