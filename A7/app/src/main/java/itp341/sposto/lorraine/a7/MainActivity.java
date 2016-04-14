package itp341.sposto.lorraine.a7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Switch;
import android.widget.Toast;

import itp341.sposto.lorraine.a7.models.CoffeeOrder;

public class MainActivity extends AppCompatActivity {
    private static final String PREF_TAG = "itp341.sposto.lorraine.a7.shared_preferences";
    private static final String PREF_SIZE = "itp341.sposto.lorraine.a7.size_preference";
    private static final String PREF_BREW = "itp341.sposto.lorraine.a7.brew_preference";
    private static final String PREF_SUGAR = "itp341.sposto.lorraine.a7.sugar_preference";
    private static final String PREF_MILK = "itp341.sposto.lorraine.a7.milk_preference";
    private static final String PREF_SPECIAL = "itp341.sposto.lorraine.a7.special_preference";
    private static final String PREF_NAME = "itp341.sposto.lorraine.a7.name_preference";

    private RadioGroup mSizeGroup;
    private RadioButton mSmallButton, mMediumButton, mLargeButton;
    private Spinner mBrewSpinner;
    private Switch mSugarSwitch;
    private CheckBox mMilkCheckBox;
    private EditText mSpecialText, mNameText;
    private Button mLoadButton, mSaveButton, mClearButton, mOrderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSizeGroup = (RadioGroup) findViewById(R.id.sizeButtonGroup);
        mSmallButton = (RadioButton) findViewById(R.id.sizeSmall);
        mMediumButton = (RadioButton) findViewById(R.id.sizeMedium);
        mLargeButton = (RadioButton) findViewById(R.id.sizeLarge);
        mBrewSpinner = (Spinner) findViewById(R.id.brewSpinner);
        mSugarSwitch = (Switch) findViewById(R.id.sugarSwitch);
        mMilkCheckBox = (CheckBox) findViewById(R.id.milkCheckBox);
        mNameText = (EditText) findViewById(R.id.nameText);
        mSpecialText = (EditText) findViewById(R.id.specialInstText);
        mLoadButton = (Button) findViewById(R.id.loadButton);
        mSaveButton = (Button) findViewById(R.id.saveButton);
        mClearButton = (Button) findViewById(R.id.clearButton);
        mOrderButton = (Button) findViewById(R.id.orderButton);

        // Set array adapter for brew types
        ArrayAdapter<String> brewAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, CoffeeOrder.getBrews());
        brewAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mBrewSpinner.setAdapter(brewAdapter);

        // save preferences listener
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences(PREF_TAG, MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(PREF_SIZE, getSize());
                editor.putString(PREF_BREW, getBrew());
                editor.putBoolean(PREF_SUGAR, getSugar());
                editor.putBoolean(PREF_MILK, getMilk());
                editor.putString(PREF_SPECIAL, getSpecialInstructions());
                editor.putString(PREF_NAME, getName());
                editor.apply();

                Toast.makeText(getApplicationContext(), getString(R.string.toast_save), Toast.LENGTH_SHORT).show();
            }
        });

        mLoadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences(PREF_TAG, MODE_PRIVATE);
                String size = preferences.getString(PREF_SIZE, null);
                String brew = preferences.getString(PREF_BREW, null);
                boolean sugar = preferences.getBoolean(PREF_SUGAR, false);
                boolean milk = preferences.getBoolean(PREF_MILK, false);
                String special = preferences.getString(PREF_SPECIAL, null);
                String name = preferences.getString(PREF_NAME, null);

                if (size != null) setSize(size);
                if (brew != null) setBrew(brew);
                setSugar(sugar);
                setMilk(milk);
                if (special != null) setSpecialInstructions(special);
                if (name != null) setName(name);

                Toast.makeText(getApplicationContext(), getString(R.string.toast_load), Toast.LENGTH_SHORT).show();
            }
        });

        mClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearForm();
            }
        });

        mOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CoffeeOrder coffeeOrder = new CoffeeOrder(
                    getName(), getSize(), getBrew(), getSugar(), getMilk(), getSpecialInstructions()
                );

                Intent i = new Intent(getApplicationContext(), ViewOrderActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(ViewOrderActivity.BUNDLE_COFFEE_ORDER, coffeeOrder);
                i.putExtras(bundle);
                startActivityForResult(i, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {
            case RESULT_CANCELED:
                Toast.makeText(getApplicationContext(), getString(R.string.toast_cancel), Toast.LENGTH_SHORT).show();
                break;
            case RESULT_OK:
                Toast.makeText(getApplicationContext(), getString(R.string.toast_confirm), Toast.LENGTH_SHORT).show();
                clearForm();
                break;
            default:
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
        }
    }

    private String getSize() {
        int sizeId = mSizeGroup.getCheckedRadioButtonId();
        String size = getString(R.string.text_small);
        switch (sizeId) {
            case R.id.sizeSmall:
                break;
            case R.id.sizeMedium:
                size = getString(R.string.text_medium);
                break;
            case R.id.sizeLarge:
                size = getString(R.string.text_large);
                break;
            default:
                break;
        }
        return size;
    }

    private String getName() {
        return mNameText.getText().toString();
    }

    private String getBrew() {
//        int index = mBrewSpinner.getSelectedItemPosition();
        return (String) mBrewSpinner.getSelectedItem();
    }

    private boolean getSugar() {
        return mSugarSwitch.isChecked();
    }

    private boolean getMilk() {
        return mMilkCheckBox.isChecked();
    }

    private String getSpecialInstructions() {
        return mSpecialText.getText().toString();
    }

    private void setSize(String size) {
        if (getString(R.string.text_small).equals(size)) {
            mSmallButton.setChecked(true);
            mMediumButton.setChecked(false);
            mLargeButton.setChecked(false);
        } else if (getString(R.string.text_medium).equals(size)) {
            mMediumButton.setChecked(true);
            mSmallButton.setChecked(false);
            mLargeButton.setChecked(false);
        } else if (getString(R.string.text_large).equals(size)) {
            mLargeButton.setChecked(true);
            mSmallButton.setChecked(false);
            mMediumButton.setChecked(false);
        } else {
            mSmallButton.setChecked(false);
            mMediumButton.setChecked(false);
            mLargeButton.setChecked(false);
        }
    }

    private void setBrew(String brew) {
        mBrewSpinner.setSelection(CoffeeOrder.getBrewIndex(brew));
    }

    private void setSugar(boolean sugar) {
        mSugarSwitch.setChecked(sugar);
    }

    private void setMilk(boolean milk) {
        mMilkCheckBox.setChecked(milk);
    }

    private void setSpecialInstructions(String instr) {
        mSpecialText.setText(instr);
    }

    private void setName(String name) {
        mNameText.setText(name);
    }

    private void clearForm() {
        setSize(getString(R.string.text_small));
        setBrew(CoffeeOrder.getBrew(0));
        setSugar(false);
        setMilk(false);
        setSpecialInstructions("");
        setName("");
    }

}
