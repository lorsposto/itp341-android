package itp341.sposto.lorraine.a7;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import itp341.sposto.lorraine.a7.models.CoffeeOrder;

/**
 * Created by LorraineSposto on 3/18/16.
 */
public class ViewOrderActivity extends AppCompatActivity {
    public static final String BUNDLE_COFFEE_ORDER = "itp341.sposto.lorraine.a7.bundle.coffee_order";
    private Button mCancelButton, mConfirmButton;
    private TextView mNameText, mDetailText, mSpecialText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);

        mCancelButton = (Button) findViewById(R.id.cancelButton);
        mConfirmButton = (Button) findViewById(R.id.confirmButton);
        mNameText = (TextView) findViewById(R.id.summaryNameText);
        mDetailText = (TextView) findViewById(R.id.summaryDetailText);
        mSpecialText = (TextView) findViewById(R.id.summarySpecialText);

        Intent data = getIntent();
        Bundle bundle = data.getExtras();

        CoffeeOrder coffeeOrder = (CoffeeOrder) bundle.getSerializable(BUNDLE_COFFEE_ORDER);

        if (coffeeOrder != null) {
            String nameText = String.format(getString(R.string.text_summary_name), coffeeOrder.getName());
            mNameText.setText(nameText);
            String hasSugar = coffeeOrder.isHasSugar() ? "with" : "no";
            String hasMilk = coffeeOrder.isHasMilk() ? "with" : "no";
            String detailText = String.format(getString(R.string.text_summary_detail),
                    coffeeOrder.getBrew(), coffeeOrder.getSize(), hasSugar, hasMilk);
            mDetailText.setText(detailText);
            String specialText = String.format(getString(R.string.text_summary_special), coffeeOrder.getSpecialInstructions());
            mSpecialText.setText(specialText);
        } else {
            mNameText.setText("Error summarizing order.");
            mDetailText.setText("");
            mSpecialText.setText("");
        }

        mConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                finish();
            }
        });

        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}
