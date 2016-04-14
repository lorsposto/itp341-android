package itp341.sposto.lorraine.a9.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import itp341.sposto.lorraine.a9.database.DbHelper;
import itp341.sposto.lorraine.a9.database.DbSchema;

/**
 * Created by LorraineSposto on 4/13/16.
 */
public class HeroSingleton {

    private static final String TAG = HeroSingleton.class.getSimpleName();

    private static HeroSingleton sHeroSingleton;
    private Context mContext;

    private ArrayList<Hero> mHeros;

    SQLiteDatabase mDatabase;

    private HeroSingleton(Context appContext) {
        mContext = appContext;
        mHeros = new ArrayList<Hero>();

        mDatabase = new DbHelper(mContext).getWritableDatabase();
//        ArrayList<String[]> res = getDbTableDetails();
//        for(int i=0; i < res.size(); ++i) {
//            for (int j=0; j < res.get(i).length; ++j) {
//                Log.d(TAG, res.get(i)[j]);
//            }
//        }
//        Log.d(TAG, getDbTableDetails().toString());
    }

    public ArrayList<String[]> getDbTableDetails() {
        Cursor c = mDatabase.rawQuery(
                "SELECT name FROM heroes", null);
        ArrayList<String[]> result = new ArrayList<String[]>();
        int i = 0;
        result.add(c.getColumnNames());
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            String[] temp = new String[c.getColumnCount()];
            for (i = 0; i < temp.length; i++) {
                temp[i] = c.getString(i);
            }
            result.add(temp);
        }

        return result;
    }

    public static HeroSingleton get(Context c) {
        if (sHeroSingleton == null) {
            sHeroSingleton = new HeroSingleton(c.getApplicationContext());
        }
        return sHeroSingleton;
    }

    public Cursor getUniquePowers() {
        String[] projection = {
                DbSchema.TABLE_POWERS.KEY_ID,
                DbSchema.TABLE_POWERS.KEY_OWN_POWER,
//                DbSchema.TABLE_POWERS.KEY_OPPOSING_POWER,
//                DbSchema.TABLE_POWERS.KEY_WINNING_POWER
        };
        String sortOrder = DbSchema.TABLE_POWERS.NAME + " asc";

        Cursor c  = mDatabase.query(
                true,
                DbSchema.TABLE_POWERS.NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder,
                null
        );

        return c;
    }

    public void addHero(Hero hero) {
        ContentValues cv = new ContentValues();
        cv.put(DbSchema.TABLE_HEROES.KEY_NAME, hero.getName());
        cv.put(DbSchema.TABLE_HEROES.KEY_POWER1, hero.getPower1());
        cv.put(DbSchema.TABLE_HEROES.KEY_POWER2, hero.getPower2());
        cv.put(DbSchema.TABLE_HEROES.KEY_NUM_WINS, hero.getNumWins());
        cv.put(DbSchema.TABLE_HEROES.KEY_NUM_LOSSES, hero.getNumLosses());
        cv.put(DbSchema.TABLE_HEROES.KEY_NUM_TIES, hero.getNumTies());

        mDatabase.insert(DbSchema.TABLE_HEROES.NAME, null, cv);
    }

    public Cursor getRankings() {
        Log.d(TAG, "getRankings()");
        String[] projection = {
                DbSchema.TABLE_HEROES.KEY_ID,
                DbSchema.TABLE_HEROES.KEY_NAME
//                DbSchema.TABLE_HEROES.KEY_POWER1,
//                DbSchema.TABLE_HEROES.KEY_POWER2,
//                DbSchema.TABLE_HEROES.KEY_NUM_WINS,
//                DbSchema.TABLE_HEROES.KEY_NUM_LOSSES,
//                DbSchema.TABLE_HEROES.KEY_NUM_TIES,
        };
        String sortOrder = DbSchema.TABLE_HEROES.KEY_NUM_WINS + " desc";

        Cursor c  = mDatabase.query(
                DbSchema.TABLE_HEROES.NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        return c;
    }

    public Cursor getHeroes() {
        Log.d(TAG, "getHeroes()");
        String[] projection = {
                DbSchema.TABLE_HEROES.KEY_ID,
                DbSchema.TABLE_HEROES.KEY_NAME
//                DbSchema.TABLE_HEROES.KEY_POWER1,
//                DbSchema.TABLE_HEROES.KEY_POWER2,
//                DbSchema.TABLE_HEROES.KEY_NUM_WINS,
//                DbSchema.TABLE_HEROES.KEY_NUM_LOSSES,
//                DbSchema.TABLE_HEROES.KEY_NUM_TIES,
        };
        String sortOrder = DbSchema.TABLE_HEROES.KEY_NAME + " asc";

        Cursor c  = mDatabase.query(
                DbSchema.TABLE_HEROES.NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        return c;
    }

    public int getPowerResult(String power1, String power2) {
        String[] projection = {
                DbSchema.TABLE_POWERS.KEY_ID,
                DbSchema.TABLE_POWERS.KEY_WINNING_POWER
        };

        String selection = "own_power=? AND opposing_power=?";
        String[] selectionArgs = {
                power1,
                power2
        };
        Cursor c  = mDatabase.query(
                DbSchema.TABLE_POWERS.NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c.moveToFirst()) {
            return c.getInt(DbSchema.TABLE_POWERS.COLUMN_WINNING_POWER);
        }
        c.close();
        return -1;
    }

    public void addBattleResult(Hero hero, int result) {
        // TODO
    }
}
