package com.todo.stealthspace.todo;

import android.provider.BaseColumns;

public final class TaskReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private TaskReaderContract() {}

    /* Inner class that defines the table contents */
    public static class TaskEntry implements BaseColumns {
        public static final String TABLE_NAME = "task";
        public static final String COLUMN_NAME_TASK_DESCRIPTION = "description";
        public static final String COLUMN_NAME_TASK_DUE_DATE = "duedate";
        public static final String COLUMN_NAME_TASK_DUE_TIME = "duetime";
    }
}
