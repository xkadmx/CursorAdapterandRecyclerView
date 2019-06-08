package com.example.cursoradapterandrecyclerview;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase mDatabase;
    private EditText mEditTextName;
    private TextView mTextViewAmount;
    private int mAmount = 0; // initial amount = 0


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GroceryDBHelper dbHelper = new GroceryDBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();  // writable not readable as initial

        mEditTextName = (EditText) findViewById(R.id.editText_name);
        mTextViewAmount = findViewById(R.id.textView_amount);

        Button buttonIncrease = findViewById(R.id.button_increase);
        Button buttonDecrease = findViewById(R.id.button_decrease);
        Button buttonAdd = findViewById(R.id.button_add);

        buttonIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increase(); //TODO build the method increase
            }
        });
        buttonDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrease(); //TODO build the method decrease
            }
        });
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();   // TODO build the method addItem
            }
        });

        private void increase(){
            mAmount++;
            mTextViewAmount.setText(String.valueOf(mAmount));
        }
        private void decrease(){
            if(mAmount>0){
            mAmount--;
            mTextViewAmount.setText(String.valueOf(mAmount));
        }
        private void addItem(){
            if(mEditTextName.getText().toString().trim().length() == 0 || mAmount == 0){
            return;
            }
            String name = mEditTextName.getText().toString();
                ContentValues cv = new ContentValues();
                cv.put(GroceryContract.GroceryEntry.COLUMN_NAME, name);
                cv.put(GroceryContract.GroceryEntry.COLUMN_AMOUNT, mAmount);

                mDatabase.insert(GroceryContract.GroceryEntry.TABLE_NAME, null, cv);
                mEditTextName.getText().clear(); // to clear the space for the next entry
            }
            private Cursor getAllItems() {
                return mDatabase.query(
                        GroceryContract.GroceryEntry.TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        GroceryContract.GroceryEntry.TIME_STAMP + " DESC");
            }
        }
    }
}


