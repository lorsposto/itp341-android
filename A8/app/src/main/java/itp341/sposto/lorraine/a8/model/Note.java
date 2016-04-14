package itp341.sposto.lorraine.a8.model;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by LorraineSposto on 3/26/16.
 */
public class Note {
    private String title;
    private String content;
    private Calendar date;

    public Note() {
        this.date = Calendar.getInstance();
    }

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
        this.date = Calendar.getInstance();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        this.date = Calendar.getInstance();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        this.date = Calendar.getInstance();
    }

    public Calendar getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Note{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                '}';
    }
}
