package com.example.violi.therosterprojectupdated;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private EditText inputName;
    private String thisName;
    private CheckBox checkBox;
    public boolean thisCheckBox;

    // Dropdown Spinner
    private Spinner spinnerEyeColor;
    private int EyeColorValue;

    // DatePickerDialog-Calender
    private EditText mDisplayDate;
    private int day, month, year;
    private String thisDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private static final String TAG = "MainActivity";

    // RadioGroup
    private RadioGroup radioGroupShirt;
    private RadioButton radioButtonXS;
    private RadioButton radioButtonS;
    private RadioButton radioButtonM;
    private RadioButton radioButtonL;
    private RadioButton radioButtonXL;
    private RadioButton radioButtonXXL;
    int checkedRadioButtonId;

    // SeekBars
    private SeekBar seekBarPant;
    private SeekBar seekBarShirt;
    private SeekBar seekBarShoe;
    private TextView textViewPant, textViewShirt, textViewShoe;
    private int pantValue, shirtValue, shoeValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 3.2. EditText- for enter your name
        inputName = (EditText) findViewById(R.id.etInputName);

        // 3.3 Checkbox
        checkBox = (CheckBox) findViewById(R.id.checkBox);

        // Load Spinner Method.
        SpinnerEyeColor();

        // Load Calender
        DateCalender();

        // Load RadioGroup and RadioButtons.
        RadioGroup();

        // Load SeekBar Method.
        SeekBarPant();
        SeekBarShirt();
        SeekBarShoe();

        // Load sharedPreferences.
        loadPreferences();
    }

    public void SpinnerEyeColor() {
        // initialize, 3.4 Spinner(dropdown)
        // set String Array at string.xml
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.eyeColor));
        // initializing spinner
        spinnerEyeColor = (Spinner) this.findViewById(R.id.spinnerEyeColor);
        spinnerEyeColor.setAdapter(adapter);
    }


    public void DateCalender() {
        // 3.5 DatePickerDialog
        mDisplayDate = (EditText) findViewById(R.id.etDisplayDate);
        Button btnGoCalender = (Button) findViewById(R.id.btnCalender);
        btnGoCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                year = cal.get(Calendar.YEAR);
                month = cal.get(Calendar.MONTH);
                day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        MainActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        // Set date Calender
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd.yyy:" + month + "/" + dayOfMonth + "/" + year);
                String date = month + "/" + dayOfMonth + "/" + year;
                mDisplayDate.setText(date);
            }
        };
    }

    public void RadioGroup() {
        // 3.6 RadioGroup & RadioButtons
        radioGroupShirt = (RadioGroup) findViewById(R.id.radioGroup_Shirt);
        radioButtonXS = (RadioButton) findViewById(R.id.radioButton_XS);
        radioButtonS = (RadioButton) findViewById(R.id.radioButton_S);
        radioButtonM = (RadioButton) findViewById(R.id.radioButton_M);
        radioButtonL = (RadioButton) findViewById(R.id.radioButton_L);
        radioButtonXL = (RadioButton) findViewById(R.id.radioButton_XL);
    }

    // 3.7 Three sliders
    public void SeekBarPant() {

        seekBarPant = (SeekBar) findViewById(R.id.seekBar_Pant);
        seekBarPant.setMax(16); //Set maximum of pant size
        textViewPant = (TextView) findViewById(R.id.tvPant);
        textViewPant.setText("Your Pant size: " + seekBarPant.getProgress() + " / " + seekBarPant.getMax());

        seekBarPant.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    //   int progress_value1;
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        pantValue = progress;
                        textViewPant.setText("Your Pant size: " + progress + " / " + seekBarPant.getMax());
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                }
        );
    }

    //The method for the Slider of SHIRT
    public void SeekBarShirt() {
        seekBarShirt = (SeekBar) findViewById(R.id.seekBar_Shirt);
        seekBarShirt.setMax(12); //Set maximum of shirt size
        textViewShirt = (TextView) findViewById(R.id.tvShirt);
        textViewShirt.setText("Your Shirt size: " + seekBarShirt.getProgress() + " / " + seekBarShirt.getMax());

        seekBarShirt.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    // int progress_value;
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        shirtValue = progress;
                        textViewShirt.setText("Your Shirt size: " + progress + " / " + seekBarShirt.getMax());
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                }
        );
    }

    //The method for the Slider of SHOE
    public void SeekBarShoe() {
        seekBarShoe = (SeekBar) findViewById(R.id.seekBar_Shoe);
        seekBarShoe.setMax(12);     //Set maximum of shoe size
        seekBarShoe.setProgress(4); //starting from 4 to maximum 12
        textViewShoe = (TextView) findViewById(R.id.tvShoe);
        textViewShoe.setText("Your Shoe size: " + seekBarShoe.getProgress() + " / " + seekBarShoe.getMax());

        seekBarShoe.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        shoeValue = progress;
                        textViewShoe.setText("Your Shoe size: " + progress + " / " + seekBarShoe.getMax());
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                }
        );
    }

    // When user click to Save button.
    public void doSave(View view) {
        // The created file can only be accessed by the calling application
        // (or all applications sharing the same user ID).
        SharedPreferences sharedPreferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE);

        //
        thisName = inputName.getText().toString();
        thisCheckBox = checkBox.isChecked();
        EyeColorValue = spinnerEyeColor.getSelectedItemPosition();
        checkedRadioButtonId = radioGroupShirt.getCheckedRadioButtonId();
        thisDate = mDisplayDate.getText().toString();
        seekBarPant.getProgress();
        seekBarShirt.getProgress();
        seekBarShoe.getProgress();

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("key_name",thisName);              // 1. Input Name
        editor.putBoolean("key_checkbox", thisCheckBox);    // 2. Check Box
        editor.putInt("key_eyeColor", EyeColorValue);       // 3. Eye Color
        editor.putString("key_birthday", thisDate);         // 4.Calender

        // Checked RadioButton ID.
        editor.putInt("checkedRadioButtonId", checkedRadioButtonId);
        editor.putInt("key_pant_size", pantValue);          // 6. Seekbar 1
        editor.putInt("key_shirt_size", shirtValue);        // 6. Seekbar 2
        editor.putInt("key_shoe_size", shoeValue);          // 6. Seekbar 3

        // Save.
        editor.apply();
        Toast.makeText(getApplicationContext(), "Saved Successfully, Thanks!", Toast.LENGTH_SHORT).show();

    }

    public void doClear(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //clear all data
        editor.clear();
        editor.commit();
        loadPreferences();
        Toast.makeText(getApplicationContext(), "successfully reset!", Toast.LENGTH_SHORT).show();
    }

    private void loadPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE);

        if (sharedPreferences != null) {
            //
            String thisName = sharedPreferences.getString("key_name", "");
            boolean thisCheckBox = sharedPreferences.getBoolean("key_checkbox",false);

            int EyeColorValue = sharedPreferences.getInt("key_eyeColor", 0);
            int checkedRadioButtonId = sharedPreferences.getInt("checkedRadioButtonId", 0);

            String thisDate = sharedPreferences.getString("key_birthday", "");
            int pantValue = sharedPreferences.getInt("key_pant_size", 0);
            int shirtValue = sharedPreferences.getInt("key_shirt_size", 0);
            int shoeValue = sharedPreferences.getInt("key_shoe_size", 4);

            // Retrieve
            inputName.setText(thisName);
            checkBox.setChecked(thisCheckBox);
            spinnerEyeColor.setSelection(EyeColorValue);
            mDisplayDate.setText(thisDate);
            radioGroupShirt.check(checkedRadioButtonId);
            seekBarPant.setProgress(pantValue);
            seekBarShirt.setProgress(shirtValue);
            seekBarShoe.setProgress(shoeValue);
        }
    }
}