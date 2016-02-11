package ipt341.sposto.lorraine.a4;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    private String TAG = MainActivity.class.getName();

    private double billAmount, tipPercent, calculatedTip, calculatedTotal, splitWays, perPerson;

    // UI Elements
    private EditText mBillAmountEditText;
    private SeekBar mTipPercentSeekBar;
    private TextView mTipPercentText;
    private TextView mCalculatedTipText;
    private TextView mCalculatedTotalText;
    private Spinner mSplitSpinner;
    private TextView mPerPerson;
    private LinearLayout mPerPersonLayout;

    // Keys
    private final String BILLKEY = "BILL";
    private final String PERCENTKEY = "PERCENT";
    private final String CALCTIPKEY = "CALCTIP";
    private final String CALCTOTALKEY = "CALCTOTAL";
    private final String SPLITWAYSKEY = "SPLITWAYS";
    private final String PERPERSONKEY = "PERPERSON";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            billAmount = savedInstanceState.getDouble(BILLKEY);
            tipPercent = savedInstanceState.getDouble(PERCENTKEY);
            calculatedTip = savedInstanceState.getDouble(CALCTIPKEY);
            calculatedTotal = savedInstanceState.getDouble(CALCTOTALKEY);
            splitWays = savedInstanceState.getDouble(SPLITWAYSKEY);
            perPerson = savedInstanceState.getDouble(PERPERSONKEY);
        } else {
            billAmount = 0;
            calculatedTotal = 0;
            calculatedTip = 0;
            splitWays = 0;
            perPerson = 0;
        }

        setContentView(R.layout.activity_main);

        mBillAmountEditText = (EditText) findViewById(R.id.billAmountInput);
        mTipPercentSeekBar = (SeekBar) findViewById(R.id.tipPercentSeekBar);
        mTipPercentText = (TextView) findViewById(R.id.tipPercentText);
        mCalculatedTipText = (TextView) findViewById(R.id.calculatedTipText);
        mCalculatedTotalText = (TextView) findViewById(R.id.calculatedTotalText);
        mSplitSpinner = (Spinner) findViewById(R.id.splitSpinner);
        mPerPerson = (TextView) findViewById(R.id.perPersonText);
        mPerPersonLayout = (LinearLayout) findViewById(R.id.perPersonLayout);

        // and set Default Values
        String tempBillAmt = NumberFormat.getCurrencyInstance().format(billAmount/100);
        mBillAmountEditText.setText(tempBillAmt);
        mBillAmountEditText.setSelection(tempBillAmt.length());
        mCalculatedTipText.setText(NumberFormat.getCurrencyInstance().format(calculatedTip / 100));
        mCalculatedTotalText.setText(NumberFormat.getCurrencyInstance().format(calculatedTotal / 100));
        mPerPerson.setText(NumberFormat.getCurrencyInstance().format(perPerson/100));

        tipPercent = mTipPercentSeekBar.getProgress();
        mTipPercentText.setText(tipPercent + "%");

        // Set spinner choices
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.split_array, R.layout.support_simple_spinner_dropdown_item);
        mSplitSpinner.setAdapter(adapter);
        splitWays = getResources().getIntArray(R.array.split_values_array)[mSplitSpinner.getSelectedItemPosition()];

        // Set Bill Amount Listener
        mBillAmountEditText.addTextChangedListener(new TextWatcher() {
            private String current = "";

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { /* do nothing */ }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(current)) {
                    mBillAmountEditText.removeTextChangedListener(this);
                    String cleanString = s.toString().replaceAll("[$,.]", "");
                    try {
                        double parsed = Double.parseDouble(cleanString);
                        billAmount = parsed;
                        String formatted = NumberFormat.getCurrencyInstance().format(parsed/100);
                        current = formatted;
                    }
                    catch (NumberFormatException n) {
                        Log.d(TAG, "Invalid characters entered into bill amount.");
                    }
                    mBillAmountEditText.setText(current);
                    mBillAmountEditText.setSelection(current.length());
                    mBillAmountEditText.addTextChangedListener(this);

                    // do tip calculation
                    calculateTipAndSetValues();
                }
            }

            @Override
            public void afterTextChanged(Editable s) { /* do nothing */ }
        });

        // Set Seek Bar listener
        mTipPercentSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tipPercent = progress;
                String text = progress + "%";
                mTipPercentText.setText(text);

                // do tip calculation
                calculateTipAndSetValues();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { /* do nothing */ }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { /* do nothing */ }
        });

        // Spinner listen
        mSplitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                splitWays = getResources().getIntArray(R.array.split_values_array)[position];
                Log.d(TAG, "Split ways: " + splitWays);
                if (splitWays > 1) {
                    mPerPersonLayout.setVisibility(View.VISIBLE);
                }
                else {
                    mPerPersonLayout.setVisibility(View.GONE);
                }
                calculateTipAndSetValues();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { /* do nothing */ }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putDouble(BILLKEY, billAmount);
        savedInstanceState.putDouble(PERCENTKEY, tipPercent);
        savedInstanceState.putDouble(CALCTIPKEY, calculatedTip);
        savedInstanceState.putDouble(CALCTOTALKEY, calculatedTotal);
        savedInstanceState.putDouble(SPLITWAYSKEY, splitWays);
        savedInstanceState.putDouble(PERPERSONKEY, perPerson);
        super.onSaveInstanceState(savedInstanceState);
    }

    private void calculateTipAndSetValues() {
        calculatedTip = billAmount * (tipPercent/100);
        calculatedTotal = calculatedTip + billAmount;
        perPerson = calculatedTotal/splitWays;
        String formattedTipValue = NumberFormat.getCurrencyInstance().format(calculatedTip / 100);
        String formattedTotalValue = NumberFormat.getCurrencyInstance().format(calculatedTotal / 100);
        String formattedPerPersonValue = NumberFormat.getCurrencyInstance().format(perPerson / 100);
        mCalculatedTipText.setText(formattedTipValue);
        mCalculatedTotalText.setText(formattedTotalValue);
        mPerPerson.setText(formattedPerPersonValue);
    }
}
