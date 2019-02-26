package com.todo.stealthspace.todo;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Locale;

public class AddTaskActivity extends FragmentActivity {

    private Intent _repeatDialogIntent;
    private Repeat _repeat;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override

    //region Srartup

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);
        ((RadioGroup) findViewById(R.id.tgTaskRepeatOptions)).setOnCheckedChangeListener(ToggleListener);
        setDefaultValues();
        //If your picker needs to have text as items :

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setDefaultValues(){
        // This will be called even in the edit mode by passing the task object
        // needs to be decided how the task to be passed
        boolean isPriority = true;
        @SuppressLint({"NewApi", "LocalSuppress"})
        LocalDateTime now = LocalDateTime.now();

        _repeat = new Repeat();
        _repeat.setRepeatType(RepeatTypeFormat.WEEKLY);
//        CustomRepeat cr = new CustomRepeat();
//        cr.setCustomRepeatType(CustomRepeatTypeFormat.WEEK);
//        _repeat.setCustom(cr);

        ShowPriority(isPriority);
        ShowDate(now);
        ShowTime(now);
        ShowRepeat();
    }


    static final RadioGroup.OnCheckedChangeListener ToggleListener = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(final RadioGroup radioGroup, final int i) {
            for (int j = 0; j < radioGroup.getChildCount(); j++) {
                final ToggleButton view = (ToggleButton) radioGroup.getChildAt(j);
                view.setChecked(view.getId() == i);
            }
        }
    };

    //endregion

    //region AddTaskPriority

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void ShowPriority(boolean isPriority){
        // Fetch default time value from settings
        adjustPriority(isPriority);
    }

    public void adjustPriority(boolean isPriority){

        ImageButton imgBtnAddTaskPriority = findViewById(R.id.imgBtnAddTaskPriority);
        if(isPriority) {
            imgBtnAddTaskPriority.setImageResource(R.drawable.high_priority_dark);
            imgBtnAddTaskPriority.setTag(this.getResources().getString(R.string.dark));
        }
        else {
            imgBtnAddTaskPriority.setImageResource(R.drawable.high_priority_light);
            imgBtnAddTaskPriority.setTag(this.getResources().getString(R.string.light));
        }
    }

    public void onImgBtnAddTaskPriorityClicked(View v){
        adjustPriority(findViewById(R.id.imgBtnAddTaskPriority).getTag().equals(this.getResources().getString(R.string.light)));
    }

    //endregion

    //region AddTaskDueDate

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void ShowDate(LocalDateTime now){

        // Fetch default date value from settings

        //get DateTypeFormat from settings
        DateTypeFormat dateTypeFormat= DateTypeFormat.UK;


        String dateString = Utility.getDate(now, dateTypeFormat);
        if(dateString.equals("")){
            adjustDateBasedFields("", View.GONE);
        }
        else {
            adjustDateBasedFields(dateString, View.VISIBLE);
        }
    }

    public void adjustDateBasedFields(String dateString, int imageButtonVisibility){

        ImageButton imgBtnAddTaskDueDateDelete = findViewById(R.id.imgBtnAddTaskDueDateDelete);
        EditText editTxtAddTaskDate = findViewById(R.id.editTxtAddTaskDate);

        TableRow.LayoutParams param = new TableRow.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        param.weight = imageButtonVisibility == View.VISIBLE ? 3.0f : 4.0f;
        param.width =0;
        editTxtAddTaskDate.setText(dateString);
        editTxtAddTaskDate.setLayoutParams(param);
        imgBtnAddTaskDueDateDelete.setVisibility(imageButtonVisibility);
        findViewById(R.id.trTaskDueTime).setVisibility(imageButtonVisibility);
        findViewById(R.id.trTaskRepeatFirst).setVisibility(imageButtonVisibility);
        findViewById(R.id.trTaskRepeatSecond).setVisibility(imageButtonVisibility);
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new AddTaskDueDatePickerFragment(this);
        newFragment.show(getFragmentManager(), "datePicker");
    }

    public void onImgBtnAddTaskDueDateCancelClicked(View v){
        adjustDateBasedFields("", View.GONE);
    }

    //endregion

    //region AddTaskDueTime

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void ShowTime(LocalDateTime now){

        // Fetch default time value from settings

        //get TimeTypeFormat from settings
        TimeTypeFormat timeTypeFormat = TimeTypeFormat.TwelveHour;


        String timeString = Utility.getTime(now, timeTypeFormat);
        //timeString="";
        if(timeString.equals("")){
            adjustTimeFields("", View.GONE);
        }
        else {
            adjustTimeFields(timeString, View.VISIBLE);
        }
    }

    public void adjustTimeFields(String timeString, int imageButtonVisibility){

        ImageButton imgBtnAddTaskDueTimeDelete = findViewById(R.id.imgBtnAddTaskDueTimeDelete);
        EditText editTxtAddTaskTime = findViewById(R.id.editTxtAddTaskTime);

        TableRow.LayoutParams param = new TableRow.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        param.weight = imageButtonVisibility == View.VISIBLE ? 3.0f : 4.0f;
        param.width =0;
        editTxtAddTaskTime.setText(timeString);
        editTxtAddTaskTime.setLayoutParams(param);
        imgBtnAddTaskDueTimeDelete.setVisibility(imageButtonVisibility);
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new AddTaskDueTimePickerFragment(this);
        newFragment.show(getFragmentManager(), "timePicker");
    }

    public void onImgBtnAddTaskDueTimeCancelClicked(View v){
        adjustTimeFields("", View.GONE);
    }

    //endregion

    //region AddTaskRepeat

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void ShowRepeat(){
        setRepeatedOptions();
    }


    @SuppressLint("ResourceType")
    private void setRepeatedOptions(){

        RepeatTypeFormat repeatTypeFormat = _repeat.getRepeatType();
        RadioGroup tgTaskRepeatOptions = findViewById(R.id.tgTaskRepeatOptions);
        TextView tvAddTaskRepeatStatus =  findViewById(R.id.tvAddTaskRepeatStatus);
        int index=-1;
        switch (repeatTypeFormat){
            case DAILY:
                index=0;
                break;
            case WEEKDAYS:
                index=1;
                break;
            case WEEKLY:
                index=2;
                break;
            case MONTHLY:
                index=3;
                break;
            case YEARLY:
                index=4;
                break;
            case CUSTOM:
                index=5;
                tvAddTaskRepeatStatus.setText(_repeat.getCustom().getCustomString());
                break;
        }
        if(index!=-1){
            ((ToggleButton)tgTaskRepeatOptions.getChildAt(index)).setChecked(true);
        }
    }

    public void onToggle(View view) {
        ((RadioGroup)view.getParent()).check(view.getId());
        ToggleButton tb= ((ToggleButton)view);
        final TextView tvAddTaskRepeatStatus =  findViewById(R.id.tvAddTaskRepeatStatus);
        tvAddTaskRepeatStatus.setText("");
        if(tb.isChecked()){
            if(tb.getText().equals(getResources().getString(R.string.repeat_options_custom))) {
                showRepeatDialog();
            }
        }
    }

    public void showRepeatDialog() {
        Log.e("showRepeatDialog", "Trinadh Main Activity showRepeatDialog repeat= "+_repeat);
        Intent repeatDialogIntent = getRepeatTypeIntent();
        repeatDialogIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        repeatDialogIntent.putExtra("repeatType", _repeat);
        startActivity(repeatDialogIntent);
    }

    private Intent getRepeatTypeIntent(){
        if(_repeatDialogIntent==null){
            _repeatDialogIntent = new Intent(AddTaskActivity.this, RepeatOptionsPopupActivity.class);
        }
        return _repeatDialogIntent;
    }

    public void onSaveBtnClicked(View view) {

        Tasks t= new Tasks();

        EditText taskDescription = findViewById(R.id.eidtTxtAddTaskTaskName);
        t.setTaskDescription(taskDescription.getText().toString());

        String[] taskDueDate = ((EditText)findViewById(R.id.editTxtAddTaskDate)).getText().toString().split(" ");
        int date = Integer.parseInt(taskDueDate[1]);
        int month = getMonth(taskDueDate[2].substring(0,3));
        int year = Integer.parseInt(taskDueDate[3]);
        Calendar cal = Calendar.getInstance();
        cal.set( Calendar.YEAR, year );
        cal.set( Calendar.MONTH, month );
        cal.set( Calendar.DATE, date );
        java.sql.Date sqlDate = new java.sql.Date( cal.getTimeInMillis() );

        String taskTimeString = ((EditText) findViewById(R.id.editTxtAddTaskTime)).getText().toString();
        t.setTaskDate(sqlDate);

        String actualTime = getTime(taskTimeString);
        t.setTaskTime(java.sql.Time.valueOf(actualTime));

        addTask(t);
        Intent homeTaskActivity = new Intent(AddTaskActivity.this, HomeTask.class);
        //addTaskActivity.putExtra("myTask", value); //Optional parameters
        AddTaskActivity.this.startActivity(homeTaskActivity);
    }

    private void addTask(Tasks task)
    {
        TaskReaderDbHelper dbHelper = new TaskReaderDbHelper (this);
        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(TaskReaderContract.TaskEntry.COLUMN_NAME_TASK_DESCRIPTION, task.getTaskDescription());
        values.put(TaskReaderContract.TaskEntry.COLUMN_NAME_TASK_DUE_DATE, task.getTaskDate().toString());
        values.put(TaskReaderContract.TaskEntry.COLUMN_NAME_TASK_DUE_TIME, task.getTaskTime().toString());
        // Insert the new row, returning the primary key value of the new row
        db.insert(TaskReaderContract.TaskEntry.TABLE_NAME, null, values);

    }

    private int getMonth(String month) {
        try
        {
            java.util.Date date = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(month);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int monthNumber = cal.get(Calendar.MONTH);
            return monthNumber;
        }
        catch (Exception ex)
        {
            return 0;
        }
    }

    private String getTime(String inputTime){
        inputTime = inputTime.trim();
        String timeOnly = inputTime.subSequence(0,inputTime.length()-2).toString();
        String[] inputs = timeOnly.split(":");
        int hrs = Integer.parseInt(inputs[0].trim());
        int mts = Integer.parseInt(inputs[1].trim());
        if(inputTime.endsWith("PM"))
            hrs+=12;
        String outputTime =""+hrs+":"+mts+":00";
        return outputTime;
    }
    //endregion
}


