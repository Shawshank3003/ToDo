package com.todo.stealthspace.todo;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TimePicker;

import java.util.Calendar;


public class AddTaskDueTimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    AddTaskActivity activity;

    @SuppressLint("ValidFragment")
    public AddTaskDueTimePickerFragment(AddTaskActivity activity){
        this.activity=activity;
    }

    public AddTaskDueTimePickerFragment(){

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), R.style.AppDialogTheme,this, hour, minute, DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user
        this.activity.adjustTimeFields(Utility.getTime(hourOfDay, minute,TimeTypeFormat.TwelveHour), View.VISIBLE);
    }
}