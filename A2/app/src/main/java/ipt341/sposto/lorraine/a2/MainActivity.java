package ipt341.sposto.lorraine.a2;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    ImageButton imgJapanese,
            imgIndian,
            imgSpanish,
            imgThai,
            imgChinese,
            imgFrench;

    private void initImageButton(ImageButton button) {
        Resources res = getResources();
        String[] tokens = res.getResourceName(button.getId()).split("/");
        String name = tokens[tokens.length-1];
        int id = res.getIdentifier(name, "string", this.getPackageName());
        String url = res.getString(id);

        Glide.with(getApplicationContext())
                .load(url)
                .placeholder(R.drawable.empty)
                .into(button);

        button.setOnClickListener(new View.OnClickListener() {
            public int clicks = 0;

            @Override
            public void onClick(View v) {
                clickHandler(v, ++clicks);
            }
        });
    }

    private void clickHandler(View view, int clicks) {
        Resources res = getResources();
        String template = getResources().getString(R.string.toast_template);
        String[] tokens = getResources().getResourceName(view.getId()).split("/");
        String name = tokens[tokens.length-1].replaceFirst("image", "text");
        int id = res.getIdentifier(name, "string", this.getPackageName());
        String label = res.getString(id);
        Toast.makeText(this, String.format(template, label, clicks), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgJapanese = (ImageButton) findViewById(R.id.image_japanese);
        imgIndian = (ImageButton) findViewById(R.id.image_indian);
        imgSpanish = (ImageButton) findViewById(R.id.image_spanish);
        imgThai = (ImageButton) findViewById(R.id.image_thai);
        imgChinese = (ImageButton) findViewById(R.id.image_chinese);
        imgFrench = (ImageButton) findViewById(R.id.image_french);

        initImageButton(imgJapanese);
        initImageButton(imgThai);
        initImageButton(imgIndian);
        initImageButton(imgChinese);
        initImageButton(imgSpanish);
        initImageButton(imgFrench);
    }
}
