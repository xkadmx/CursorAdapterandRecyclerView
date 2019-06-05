package com.example.cursoradapterandrecyclerview;

import android.provider.BaseColumns;

public class GroceryContract {

    private GroceryContract(){} // instanceof the class that we will never need

    public static final class GroceryEntry implements BaseColumns{
        public static final String TABLE_NAME = "groceryList";    // name of the whole table
        public static final String COLUMN_NAME = "name";          // name of the first column of the table
        public static final String COLUMN_AMOUNT = "amount";
        public static final String TIME_STAMP = "timestamp";
    }
}
