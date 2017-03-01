package com.example.jeffs.lec12_sqlite_demo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jeffs.lec12_sqlite_demo.model.StudentContract;
import com.example.jeffs.lec12_sqlite_demo.model.StudentDbHelper;

import java.util.ArrayList;
import java.util.List;

import static com.example.jeffs.lec12_sqlite_demo.model.StudentContract.StudentEntry.COL_ADDRESS;
import static com.example.jeffs.lec12_sqlite_demo.model.StudentContract.StudentEntry.COL_AGE;
import static com.example.jeffs.lec12_sqlite_demo.model.StudentContract.StudentEntry.COL_DEPT;
import static com.example.jeffs.lec12_sqlite_demo.model.StudentContract.StudentEntry.COL_NAME;
import static com.example.jeffs.lec12_sqlite_demo.model.StudentContract.TABLE_NAME;
import static com.example.jeffs.lec12_sqlite_demo.utils.Utils.setStethoWatch;

public class MainActivity extends AppCompatActivity {

    private EditText nameField;
    private EditText deptField;
    private EditText ageField;
    private EditText addressField;
    private TextView tv;

    StudentDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setStethoWatch(this);
        nameField=(EditText)findViewById(R.id.std_name_ma);
        deptField=(EditText)findViewById(R.id.std_dept_ma);
        ageField=(EditText)findViewById(R.id.std_age_ma);
        addressField=(EditText)findViewById(R.id.std_address_ma);
        tv=(TextView)findViewById(R.id.tv_ma);

        mDbHelper=new StudentDbHelper(this);
    }

    public void addRecord(View view) {

        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(COL_NAME, nameField.getText().toString());
        values.put(COL_DEPT, deptField.getText().toString());
        values.put(COL_AGE, ageField.getText().toString());
        values.put(COL_ADDRESS, addressField.getText().toString());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_NAME, null, values);
    }

    public void checkRecord(View view) {

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                StudentContract.StudentEntry._ID,
                COL_NAME,
                COL_DEPT,
                COL_AGE,
                COL_ADDRESS
        };

        // Filter results WHERE "title" = 'My Title'
        //String selection = FeedEntry.COLUMN_NAME_TITLE + " = ?";
        //String[] selectionArgs = { "My Title" };

        // How you want the results sorted in the resulting Cursor
        //String sortOrder =
                //FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";

        Cursor cursor = db.query(
                TABLE_NAME,                               // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        String tempStr="";
        while(cursor.moveToNext()) {
            tempStr+=cursor.getString(cursor.getColumnIndexOrThrow(COL_NAME)) +" ";
            tempStr+=cursor.getString(cursor.getColumnIndexOrThrow(COL_DEPT)) +" ";
            tempStr+=cursor.getString(cursor.getColumnIndexOrThrow(COL_AGE)) +" ";
            tempStr+=cursor.getString(cursor.getColumnIndexOrThrow(COL_ADDRESS)) +"\n";
        }
        cursor.close();

    }

    public void updateRecord(View view) {

    }

    public void deleteRecord(View view) {

    }
}
