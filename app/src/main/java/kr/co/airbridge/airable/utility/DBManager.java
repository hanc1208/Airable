package kr.co.airbridge.airable.utility;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import kr.co.airbridge.airable.Shop;
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

            Process process = new Process(no, name, time, placeName, description, state);
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

        cursor.close();
        db.close();
        Process process = new Process(no, name, time, placeName, description, state);

        return process;
    }

    public void changeProcessState(int no, int state) {
        String query = "UPDATE process SET state=" + state + "WHERE no=" + no;

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL(query);

        db.close();
    }


    public ArrayList<Shop> getShopList() {
        String query = "SELECT * FROM shop";

        ArrayList<Shop> shopList = new ArrayList<Shop>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            int no = cursor.getInt(0);
            String title = cursor.getString(1);
            String info = cursor.getString(2);
            String location = cursor.getString(3);
            int floor = cursor.getInt(4);
            String time = cursor.getString(5);
            String tel = cursor.getString(6);
            String image = cursor.getString(7);
            int mark = cursor.getInt(8);

            Shop tempShop = new Shop (no, title, info, location, floor, time, tel, image, mark);
            shopList.add(tempShop);
        }

        cursor.close();
        db.close();
        return shopList;
    }

    public Shop getShop(int src_no) {
        String query = "SELECT FROM shop WHERE no=" + src_no;

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        cursor.moveToNext();

        int no = cursor.getInt(0);
        String title = cursor.getString(1);
        String info = cursor.getString(2);
        String location = cursor.getString(3);
        int floor = cursor.getInt(4);
        String time = cursor.getString(5);
        String tel = cursor.getString(6);
        String image = cursor.getString(7);
        int mark = cursor.getInt(8);

        cursor.close();
        db.close();
        Shop tempShop = new Shop (no, title, info, location, floor, time, tel, image, mark);

        return tempShop;
    }

    public void updateShopMark(int upt_no, int upt_mark) {
        String query = "UPDATE shop SET mark=" + upt_mark + "WHERE no=" + upt_no;

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL(query);

        db.close();
    }
}
