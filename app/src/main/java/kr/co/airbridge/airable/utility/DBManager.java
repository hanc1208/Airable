package kr.co.airbridge.airable.utility;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import kr.co.airbridge.airable.model.Process;

/**
 * Created by dajung han on 2016-01-11.
 */
public class DBManager {
    private Context context;
    private DBHelper dbHelper;

    public DBManager(Context context) {
        this.context = context;
        dbHelper = new DBHelper(this.context);
    }

    public ArrayList<Process> getProcessList() {
        String query = "SELECT * FROM process";

        ArrayList<Process> processList = new ArrayList<Process>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            int no = cursor.getInt(0);
            String name = cursor.getString(1);
            int time = cursor.getInt(2);
            String placeName = cursor.getString(3);
            String description = cursor.getString(4);
            int state = cursor.getInt(5);
            int vertexid = cursor.getInt(6);

            Process process = new Process(no, name, time, placeName, description, state, vertexid);
            processList.add(process);
        }

        cursor.close();
        db.close();
        return processList;
    }

    public Process getProcess(int no) {
        String query = "SELECT FROM process WHERE no=" + no;

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        cursor.moveToNext();

        no = cursor.getInt(0);
        String name = cursor.getString(1);
        int time = cursor.getInt(2);
        String placeName = cursor.getString(3);
        String description = cursor.getString(4);
        int state = cursor.getInt(5);
        int vertexid = cursor.getInt(6);

        cursor.close();
        db.close();
        Process process = new Process(no, name, time, placeName, description, state, vertexid);

        return process;
    }

    public void changeProcessState(int no, int state) {
        String query = "UPDATE process SET state=" + state + " WHERE no=" + no;

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL(query);

        db.close();
    }


}