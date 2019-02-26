package com.todo.stealthspace.todo;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.TableLayout;

public class ZFutureUseCommentedCodeDontDelete {

    //region Animated Expand and Collapse Code

    public static void expand(final View v) {
        v.measure(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
        final int targetHeight = v.getMeasuredHeight();

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        v.getLayoutParams().height = 1;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? TableLayout.LayoutParams.WRAP_CONTENT
                        : (int) (targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int) (targetHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    public static void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    v.setVisibility(View.GONE);
                } else {
                    v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int) (initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    //endregion

    //region carousel
    //private ActivityAnimatedTextCarouselSampleBinding binding;
    //        CarouselPicker carouselPicker = findViewById(R.id.carousel);
//        List<CarouselPicker.PickerItem> textItems = new ArrayList<>();
//        textItems.add(new CarouselPicker.TextItem("Mani1", 7));
//        textItems.add(new CarouselPicker.TextItem("Siri1", 7));
//        textItems.add(new CarouselPicker.TextItem("Trin1", 7));
//        textItems.add(new CarouselPicker.TextItem("Mani2", 7));
//        textItems.add(new CarouselPicker.TextItem("Siri2", 7));
//        textItems.add(new CarouselPicker.TextItem("Trin2", 7));
//        textItems.add(new CarouselPicker.TextItem("Mani3", 7));
//        textItems.add(new CarouselPicker.TextItem("Siri3", 7));
//        textItems.add(new CarouselPicker.TextItem("Trin3", 7));
//        CarouselPicker.CarouselViewAdapter textAdapter = new CarouselPicker.CarouselViewAdapter(this, textItems, 0);
//        textAdapter.setTextColor(getResources().getColor(R.color.colorWhite));

//        carouselPicker.setAdapter(textAdapter);

//        CarouselPicker carouselPicker = findViewById(R.id.carousel);
//        List<CarouselPicker.PickerItem> textItems = new ArrayList<>();
//        //20 here represents the textSize in dp, change it to the value you want.
//        textItems.add(new CarouselPicker.TextItem("Never   ", 8));
//        textItems.add(new CarouselPicker.TextItem("Daily   ", 8));
//        textItems.add(new CarouselPicker.TextItem("WeekDays", 8));
//        textItems.add(new CarouselPicker.TextItem("Weekly  ", 8));
//        textItems.add(new CarouselPicker.TextItem("Monthly ", 8));
//        textItems.add(new CarouselPicker.TextItem("Yearly  ", 8));
//        textItems.add(new CarouselPicker.TextItem("Custom  ", 8));
//        CarouselPicker.CarouselViewAdapter textAdapter = new CarouselPicker.CarouselViewAdapter(this, textItems, 0);
//        carouselPicker.setAdapter(textAdapter);

    //endregion

    // region Horizontal Picker

    //    private void setRepeatedOptions(){
//        HorizontalPicker hpText =  findViewById(R.id.hpicker);
//        final TextView tvAddTaskRepeatStatus =  findViewById(R.id.tvAddTaskRepeatStatus);
//        List<HorizontalPicker.PickerItem> textHorizItems = new ArrayList<>();
//        textHorizItems.add(new HorizontalPicker.TextItem("Never"));
//        textHorizItems.add(new HorizontalPicker.TextItem("Daily"));
//        textHorizItems.add(new HorizontalPicker.TextItem("Weekly"));
//        textHorizItems.add(new HorizontalPicker.TextItem("WeekDays"));
//        textHorizItems.add(new HorizontalPicker.TextItem("Monthly"));
//        textHorizItems.add(new HorizontalPicker.TextItem("Yearly"));
//        textHorizItems.add(new HorizontalPicker.TextItem("Custom"));
//        hpText.setItems(textHorizItems,0);
//        HorizontalPicker.OnSelectionChangeListener listener = new HorizontalPicker.OnSelectionChangeListener() {
//            @Override
//            public void onItemSelect(HorizontalPicker picker, int index) {
//                HorizontalPicker.PickerItem selected = picker.getSelectedItem();
//                tvAddTaskRepeatStatus.setText(status);
//            }
//        };
//        hpText.setChangeListener(listener);
//    }


//region Xml

    //region xml

    //region tab view

    //    <TabHost
//    android:id="@+id/tabHost"
//    android:layout_width="wrap_content"
//    android:layout_height="match_parent"
//    android:layout_alignParentTop="true"
//    android:layout_centerHorizontal="true">
//
//                    <TableLayout
//    android:layout_width="match_parent"
//    android:layout_height="match_parent"
//    android:orientation="vertical">
//
//                        <TabWidget
//    android:id="@android:id/tabs"
//    android:layout_width="wrap_content"
//    android:layout_height="wrap_content"></TabWidget>
//
//                        <FrameLayout
//    android:id="@android:id/tabcontent"
//    android:layout_width="match_parent"
//    android:layout_height="wrap_content">
//
//                            <TableLayout
//    android:id="@+id/tab1"
//    android:layout_width="match_parent"
//    android:layout_height="wrap_content">
//
//                                <TableRow
//    android:id="@+id/trAddTaskRepeatOptionsPopupWeekFirstToggleButton"
//    style="@style/AddTaskRepeatOptionsPopupDailyTableRowLayout">
//
//                                    <ToggleButton
//    style="@style/AddTaskRepeatOptionsPopupWeekdayToggleButtonFirst"
//    android:text="@string/Mon" />
//
//                                    <ToggleButton
//    style="@style/AddTaskRepeatOptionsPopupWeekdayToggleButtonFirst"
//    android:text="@string/Tue" />
//
//                                    <ToggleButton
//    style="@style/AddTaskRepeatOptionsPopupWeekdayToggleButtonFirst"
//    android:text="@string/Wed" />
//
//                                    <ToggleButton
//    style="@style/AddTaskRepeatOptionsPopupWeekdayToggleButtonFirst"
//    android:text="@string/Thu" />
//                                </TableRow>
//
//                                <TableRow
//    android:id="@+id/trAddTaskRepeatOptionsPopupWeekSecondToggleButton"
//    style="@style/AddTaskRepeatOptionsPopupDailyTableRowLayout">
//
//                                    <ToggleButton
//    style="@style/AddTaskRepeatOptionsPopupWeekdayToggleButtonSecond"
//    android:text="@string/Fri" />
//
//                                    <ToggleButton
//    style="@style/AddTaskRepeatOptionsPopupWeekdayToggleButtonSecond"
//    android:text="@string/Sat" />
//
//                                    <ToggleButton
//    style="@style/AddTaskRepeatOptionsPopupWeekdayToggleButtonSecond"
//    android:text="@string/Sun" />
//                                </TableRow>
//
//                            </TableLayout>
//
//                            <TableLayout
//    android:id="@+id/tab2"
//    android:layout_width="match_parent"
//    android:layout_height="match_parent"
//    android:background="#da8200"
//    android:orientation="vertical">
//
//                            </TableLayout>
//
//                            <TableLayout
//    android:id="@+id/tab3"
//    android:layout_width="match_parent"
//    android:layout_height="match_parent"
//    android:background="#5b89ff"
//    android:orientation="vertical">
//                                <TableRow>
//
//                                </TableRow>
//                            </TableLayout>
//
//                        </FrameLayout>
//                    </TableLayout>
//                </TabHost>

    //end region


    //endregion

    //endregion
}