package itp341.sposto.lorraine.a8.model;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by LorraineSposto on 3/26/16.
 */
public class NoteSingleton {

    private ArrayList<Note> mNotes;
    private Context mContext;
    private static NoteSingleton mNoteSingleton;

    private NoteSingleton(Context context) {
        mContext = context;
        mNotes = new ArrayList<>();
    }

    public static NoteSingleton get(Context c) {
        if (mNoteSingleton == null) {
            mNoteSingleton = new NoteSingleton(c.getApplicationContext());
        }
        return mNoteSingleton;
    }

    //TODO getCoffeeShops (all items)
    public ArrayList<Note> getNotes() {
        return mNotes;
    }

    //TODO getCoffeeShop (single item)
    public Note getNote(int index) {
        if (index > -1 && index < mNotes.size()) {
            return mNotes.get(index);
        }
        return null;
    }


    //TODO addCoffeeShop
    public void addNote(Note n) {
        mNotes.add(n);
    }

    //TODO removeCoffeeShop
    public void removeNote(int position) {
        if (position >= 0 && position < mNotes.size()) {
            mNotes.remove(position);
        }
    }

    //TODO updateCoffeeShop
    public void updateNote(int position, Note n) {
        if (position >= 0 && position < mNotes.size()) {
            mNotes.set(position, n);
        }
    }
}
