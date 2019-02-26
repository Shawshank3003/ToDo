package com.todo.stealthspace.todo;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;

public class AddTaskDueDatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    AddTaskActivity activity;

    @SuppressLint("ValidFragment")
    public AddTaskDueDatePickerFragment(AddTaskActivity activity){
        this.activity=activity;
    }

    public AddTaskDueDatePickerFragment(){

    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), R.style.AppDialogTheme,this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        this.activity.adjustDateBasedFields(Utility.getDate(day, month+1, year, DateTypeFormat.UK),View.VISIBLE);
    }
}