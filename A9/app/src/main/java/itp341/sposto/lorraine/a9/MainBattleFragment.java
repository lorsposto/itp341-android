package itp341.sposto.lorraine.a9;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

import itp341.sposto.lorraine.a9.database.DbSchema;
import itp341.sposto.lorraine.a9.model.Hero;
import itp341.sposto.lorraine.a9.model.HeroSingleton;

/**
 * Created by LorraineSposto on 4/11/16.
 */
public class MainBattleFragment extends Fragment {
    public static final String TAG = MainBattleFragment.class.getSimpleName();

    private ArrayList<Hero> mHeros;
    private ArrayAdapter<Hero> mHeroAdapter;
    private ArrayList<String> mPowers;
    private ArrayAdapter<String> mPowerAdapter;
    private SimpleCursorAdapter mRankingsCursorAdapter;
    private SimpleCursorAdapter mHeroesCursorAdapter;

    private Cursor mCursor;

    //--- View Elements ---//
    private Button mButtonAddHero;
    private ListView mListView;
    private Spinner mSpinner1, mSpinner2;

    public MainBattleFragment() {}

    public static MainBattleFragment newInstance() {
        Log.d(TAG, "newInstance()");
        Bundle args = new Bundle();


        MainBattleFragment fc = new MainBattleFragment();
        fc.setArguments(args);

        return fc;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView()");
        View v = inflater.inflate(R.layout.fragment_main_battle, container, false);

        mButtonAddHero = (Button) v.findViewById(R.id.buttonAddHero);
        mListView = (ListView) v.findViewById(R.id.listViewRankings);
        mSpinner1 = (Spinner) v.findViewById(R.id.spinnerHero1);
        mSpinner2 = (Spinner) v.findViewById(R.id.spinnerHero2);

        loadRankings();
        loadHeroes();

        mButtonAddHero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity().getApplicationContext(),
                        AddHeroActivity.class);
                startActivityForResult(i, 0);
            }
        });

        //TODO  DB - modify KEY
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                Log.d(TAG, "onListItemClick: clicked id: " + id);
//                Toast.makeText(getActivity().getApplicationContext(),
//                        "Id: " + id + ", position: " + position, Toast.LENGTH_SHORT)
//                        .show();
//
//                Intent i = new Intent(getActivity().getApplicationContext(),
//                        DetailActivity.class);
//                i.putExtra(DetailActivity.EXTRA_ID, id);
//
//                startActivityForResult(i, 0);
//
//            }
//        });
        return v;
    }

    private void loadRankings() {
        Log.d(TAG, "loadRankings()");
        String[] from = {
                DbSchema.TABLE_HEROES.KEY_NAME
        };
        int[] to = new int[] {android.R.id.text1};
        mCursor = HeroSingleton.get(getActivity()).getRankings();
        mRankingsCursorAdapter = new SimpleCursorAdapter(
                getActivity(),
                android.R.layout.simple_list_item_1,
                mCursor,
                from,
                to,
                0);

        mListView.setAdapter(mRankingsCursorAdapter);
    }

    private void loadHeroes() {
        Log.d(TAG, "loadHeroes()");
        String[] from = {
                DbSchema.TABLE_HEROES.KEY_NAME
        };
        int[] to = new int[] {android.R.id.text1};

        mCursor = HeroSingleton.get(getActivity()).getHeroes();
        mHeroesCursorAdapter = new SimpleCursorAdapter(
                getActivity(),
                android.R.layout.simple_list_item_1,
                mCursor,
                from,
                to,
                0);

        mSpinner1.setAdapter(mHeroesCursorAdapter);
        mSpinner2.setAdapter(mHeroesCursorAdapter);
    }
}
