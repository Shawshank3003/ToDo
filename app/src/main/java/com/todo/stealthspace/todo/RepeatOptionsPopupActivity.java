package com.todo.stealthspace.todo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.aigestudio.wheelpicker.WheelPicker;

import java.util.ArrayList;
import java.util.List;


public class RepeatOptionsPopupActivity extends Activity {

    private Repeat _repeat;
     @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repeat_options_popup);
         _repeat = (Repeat) this.getIntent().getSerializableExtra("repeatType");
        setRepeatOptions();
    }

     private void setRepeatOptions(){

         final CustomRepeatTypeFormat customRepeatType = (_repeat==null || _repeat.getCustom()==null
                    ? CustomRepeatTypeFormat.WEEK
                    : _repeat.getCustom().getCustomRepeatType());

         switch (customRepeatType){
             case DAY:
                 onDailyBtnClicked(findViewById(R.id.btnAddTaskRepeatOptionsPopupDaily));
                 break;
             case WEEK:
             default:
                 onWeeklyBtnClicked(findViewById(R.id.btnAddTaskRepeatOptionsPopupWeekly));
                 break;
             case MONTH:
                 onMonthlyBtnClicked(findViewById(R.id.btnAddTaskRepeatOptionsPopupMonthly));
                 break;
             case YEAR:
                 onYearlyBtnClicked(findViewById(R.id.btnAddTaskRepeatOptionsPopupYearly));
                 break;
         }

    }

    private void setWheelOnEveryData(int iterationsUpperLimit, int iterationDefaultValue){
        List<Integer> data = new ArrayList<>();
        for (int i = 1; i <= iterationsUpperLimit; i++)
            data.add(i);
        WheelPicker wheelPicker = findViewById(R.id.wpOnEvery);
        wheelPicker.setData(data);
        wheelPicker.setSelectedItemPosition(iterationDefaultValue-1);
    }

    private void setYearlyRepeatedText(){

    }

    private void setWheelYearDateData(int iterationDefaultValue){
        List<String> data = new ArrayList<>();
        data.add("31st");
        for (int i = 1; i <= 30; i++) {
            String suffix = "th";
            if(i==1 || i==21 ||  i==31) {
                suffix = "st";
            }
            else if(i==2 || i==22) {
                suffix = "nd";
            }
            else if(i==3 || i==23) {
                suffix = "rd";
            }
            data.add("" + i + suffix);
        }
        WheelPicker wheelPicker = findViewById(R.id.wp_year_date);
        wheelPicker.setData(data);
        wheelPicker.setSelectedItemPosition(iterationDefaultValue-1);
    }

    private void setWheelYearDayData(int iterationDefaultValue){
        List<String> data = new ArrayList<>();
        data.add("Sunday");
        data.add("Day");
        data.add("Monday");
        data.add("Tuesday");
        data.add("Wednesday");
        data.add("Thursdday");
        data.add("Friday");
        data.add("Saturday");
        WheelPicker wheelPicker = findViewById(R.id.wp_year_day);
        wheelPicker.setData(data);
        wheelPicker.setSelectedItemPosition(iterationDefaultValue-1);
    }

    private void setWheelYearMonthData(int iterationDefaultValue){
        List<String> data = new ArrayList<>();
        data.add("December");
        data.add("January");
        data.add("February");
        data.add("March");
        data.add("April");
        data.add("May");
        data.add("June");
        data.add("July");
        data.add("August");
        data.add("September");
        data.add("October");
        data.add("November");
        WheelPicker wheelPicker = findViewById(R.id.wp_year_year);
        wheelPicker.setData(data);
        wheelPicker.setSelectedItemPosition(iterationDefaultValue-1);
    }

    private void changeSpinnerBehavior(int iterationsUpperLimit, int iterationDefaultValue, int spinnerSiffix){
        TextView tvAddTaskRepeatOptionsPopupOnEverySufix = findViewById(R.id.tvAddTaskRepeatOptionsPopupOnEverySufix);
        tvAddTaskRepeatOptionsPopupOnEverySufix.setText(spinnerSiffix);
        setWheelOnEveryData(iterationsUpperLimit, iterationDefaultValue);
    }

    public void onDailyBtnClicked(View view) {
        changeSpinnerBehavior(30, 2, R.string.days);
        HideAllOptionTableRows();
    }

    public void onWeeklyBtnClicked(View view) {
        changeSpinnerBehavior(4, 2, R.string.weeks);
        HideAllOptionTableRows();
        findViewById(R.id.trAddTaskRepeatOptionsPopupWeekFirstToggleButton).setVisibility(View.VISIBLE);
        findViewById(R.id.trAddTaskRepeatOptionsPopupWeekSecondToggleButton).setVisibility(View.VISIBLE);
    }

    public void onMonthlyBtnClicked(View view) {
        changeSpinnerBehavior(12, 1, R.string.months);
        HideAllOptionTableRows();
        findViewById(R.id.trAddTaskRepeatOptionsPopupMonthFirstToggleButton).setVisibility(View.VISIBLE);
        findViewById(R.id.trAddTaskRepeatOptionsPopupMonthSecondToggleButton).setVisibility(View.VISIBLE);
    }

    public void onYearlyBtnClicked(View view) {
        changeSpinnerBehavior(100, 1, R.string.years);
        setWheelYearDateData(2);
        setWheelYearDayData(2);
        setWheelYearMonthData(2);
        HideAllOptionTableRows();
        findViewById(R.id.trAddTaskRepeatOptionsPopupYearFirstToggleButton).setVisibility(View.VISIBLE);
        findViewById(R.id.trAddTaskRepeatOptionsPopupYearSecondToggleButton).setVisibility(View.VISIBLE);
    }

    private void HideAllOptionTableRows(){
        findViewById(R.id.trAddTaskRepeatOptionsPopupWeekFirstToggleButton).setVisibility(View.GONE);
        findViewById(R.id.trAddTaskRepeatOptionsPopupWeekSecondToggleButton).setVisibility(View.GONE);
        findViewById(R.id.trAddTaskRepeatOptionsPopupMonthFirstToggleButton).setVisibility(View.GONE);
        findViewById(R.id.trAddTaskRepeatOptionsPopupMonthSecondToggleButton).setVisibility(View.GONE);
        findViewById(R.id.trAddTaskRepeatOptionsPopupYearFirstToggleButton).setVisibility(View.GONE);
        findViewById(R.id.trAddTaskRepeatOptionsPopupYearSecondToggleButton).setVisibility(View.GONE);
    }
}
