package com.todo.stealthspace.todo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class HomeTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_task);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        displayTasks();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addTaskActivity = new Intent(HomeTask.this, AddTaskActivity.class);
                //addTaskActivity.putExtra("myTask", value); //Optional parameters
                HomeTask.this.startActivity(addTaskActivity);
                }
        });
        /// Call sasui's api
    }

    private void displayTasks(){
        ArrayList<Tasks> allDatea = getDatabaseData();
        StringBuilder sb= new StringBuilder();
        TableLayout tlHome = findViewById(R.id.tlHomeTasks);
        int i=0;
        for (Tasks t : allDatea) {
            sb.append(t.getTaskDescription());
            TableRow row= new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);
            CheckBox checkBox = new CheckBox(this);

            TextView tv = new TextView(this);
            tv.setText(t.getTaskDescription());
            checkBox.setText("\n"+t.getTaskDescription()+"\n" + Utility.getDisplayDate(t.getTaskDate())+ "  " + Utility.getDisplayTime(t.getTaskTime()) +"\n");
            row.addView(checkBox);

            // Adding delete button and perform action to delete task

            //ImageButton deleteBtn = new ImageButton(this);
            //deleteBtn.setImageResource(R.drawable.delete_light);
            //row.addView(deleteBtn);

            // Here you can add events for the delete button like delete the record etc..


            tlHome.addView(row,i++);
        }
    }
    private ArrayList<Tasks> getDatabaseData(){
        TaskReaderDbHelper dbHelper = new TaskReaderDbHelper (this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        //db.execSQL("delete from tasks");
        //db.close();
        //if(true) return null;
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                TaskReaderContract.TaskEntry.COLUMN_NAME_TASK_DESCRIPTION,
                TaskReaderContract.TaskEntry.COLUMN_NAME_TASK_DUE_DATE,
                TaskReaderContract.TaskEntry.COLUMN_NAME_TASK_DUE_TIME
        };

        // How you want the results sorted in the resulting Cursor
        String sortOrder = TaskReaderContract.TaskEntry.COLUMN_NAME_TASK_DUE_DATE+ " DESC";

        Cursor cursor = db.query(
                TaskReaderContract.TaskEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        ArrayList<Tasks> itemData = new ArrayList<Tasks>();
        while(cursor.moveToNext()) {
            Tasks t = new Tasks();
            String taskDescription = cursor.getString(
                    cursor.getColumnIndex(TaskReaderContract.TaskEntry.COLUMN_NAME_TASK_DESCRIPTION));
            t.setTaskDescription(taskDescription);

            String taskDueDateString = cursor.getString(
                    cursor.getColumnIndex(TaskReaderContract.TaskEntry.COLUMN_NAME_TASK_DUE_DATE));
            try{
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date parsed = format.parse(taskDueDateString);
                Date sqlDate = new Date(parsed.getTime());
                t.setTaskDate(sqlDate);
            }
            catch (Exception ex){

            }

            String taskDueTimeString = cursor.getString(
                    cursor.getColumnIndex(TaskReaderContract.TaskEntry.COLUMN_NAME_TASK_DUE_TIME));
            try{
                SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
                java.util.Date parsed = format.parse(taskDueTimeString);
                Time sqlTime = new Time(parsed.getTime());
                t.setTaskTime(sqlTime);
            }
            catch (Exception ex){

            }


            itemData.add(t);
        }
        cursor.close();
        return itemData;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_task, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
