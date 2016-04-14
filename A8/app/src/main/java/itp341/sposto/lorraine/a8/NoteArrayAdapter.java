package itp341.sposto.lorraine.a8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import itp341.sposto.lorraine.a8.model.Note;

/**
 * Created by LorraineSposto on 3/26/16.
 */
public class NoteArrayAdapter extends ArrayAdapter<Note> {

    public NoteArrayAdapter(Context context, ArrayList<Note> notes) {
        super(context, 0, notes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        return super.getView(position, convertView, parent);
        Note note = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.note_list_item, null);
        }

        TextView title = (TextView) convertView.findViewById(R.id.noteTitle);
        TextView date = (TextView) convertView.findViewById(R.id.noteDate);

        title.setText(note.getTitle());

        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        String dateFormatted = format.format(note.getDate().getTime());
        date.setText(dateFormatted);
        return convertView;
    }
}
