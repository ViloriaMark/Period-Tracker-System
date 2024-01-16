package com.example.periodtrackersystem;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText pregnantNameEditText;
    private TextView lastPeriodDateTextView;
    private TextView dueDateTextView;
    private Calendar lastPeriodDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pregnantNameEditText = findViewById(R.id.pregnantNameEditText);
        lastPeriodDateTextView = findViewById(R.id.lastPeriodDateTextView);
        dueDateTextView = findViewById(R.id.dueDateTextView);

        Button setLastPeriodDateButton = findViewById(R.id.setLastPeriodDateButton);
        setLastPeriodDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        Button calculateDueDateButton = findViewById(R.id.calculateDueDateButton);
        calculateDueDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateDueDate();
            }
        });
    }

    private void showDatePickerDialog() {
        Calendar currentDate = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        lastPeriodDate = Calendar.getInstance();
                        lastPeriodDate.set(year, monthOfYear, dayOfMonth);
                        updateLastPeriodDateTextView();
                    }
                },
                currentDate.get(Calendar.YEAR),
                currentDate.get(Calendar.MONTH),
                currentDate.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private void calculateDueDate() {
        if (lastPeriodDate != null) {
            Calendar dueDate = Calendar.getInstance();
            dueDate.setTimeInMillis(lastPeriodDate.getTimeInMillis() + 280 * 24 * 60 * 60 * 1000L); // Assuming 280 days pregnancy
            updateDueDateTextView(dueDate);
        }
    }

    private void updateLastPeriodDateTextView() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        lastPeriodDateTextView.setText("Last Period Date: " + dateFormat.format(lastPeriodDate.getTime()));
    }

    private void updateDueDateTextView(Calendar dueDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        dueDateTextView.setText("Due Date: " + dateFormat.format(dueDate.getTime()));
    }
}
