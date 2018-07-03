package Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import Model.Medicine;
import Utils.Util;

/**
 * Created by Martin Agnar Dahl on 25.01.2018.
 */

public class DbHand extends SQLiteOpenHelper {

    public DbHand(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + Util.TABLE_NAME + " (" + Util.KEY_ID +
                " INTEGER PRIMARY KEY, " + Util.KEY_NAME + " TEXT, " + Util.KEY_DOSES
                + " INTEGER, " + Util.KEY_STATUS + " INTEGER" + ")";
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_NAME);
        onCreate(db);
    }

    /**CRUD - Create, Read, Update, Delete**/

    //ADD
    public boolean addPill(Medicine medicine) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put(Util.KEY_NAME, medicine.getName());
        value.put(Util.KEY_DOSES, medicine.getDoses());
        value.put(Util.KEY_STATUS, medicine.getStatus());

        //row
        db.insert(Util.TABLE_NAME, null, value);
        db.close();
        return true;
    }

    //RETRIEVE ONE

    // ID, NAME, DOSES, STATUS
    public Medicine getOneMed(int id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Util.TABLE_NAME, new String[]{Util.KEY_ID, Util.KEY_NAME,
                Util.KEY_DOSES, Util.KEY_STATUS}, Util.KEY_ID + "=?",
                new String[] {String.valueOf(id)}, null, null,
                null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        Medicine medicine = new Medicine(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), Integer.parseInt(cursor.getString(2)),
                Integer.parseInt(cursor.getString(3)));
        cursor.close();
        db.close();
        return medicine;
    }

    public Medicine getOneMed(String name) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Util.TABLE_NAME, new String[]{Util.KEY_ID, Util.KEY_NAME,
                        Util.KEY_DOSES, Util.KEY_STATUS}, Util.KEY_NAME + "=?",
                new String[] {String.valueOf(name)}, null, null,
                null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        Medicine medicine = new Medicine(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), Integer.parseInt(cursor.getString(2)),
                Integer.parseInt(cursor.getString(3)));
        cursor.close();
        db.close();
        return medicine;
    }

    // RETRIEVE ALL

    public ArrayList<Medicine> getAllMed() {

        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<Medicine> medicineList = new ArrayList<>();

        //Select all

        String selectAll = "SELECT * FROM " + Util.TABLE_NAME;

        Cursor cursor = db.rawQuery(selectAll, null);

        //Loop through

        if (cursor.moveToFirst()) {
            do{
                Medicine medicine = new Medicine();
                medicine.setId(Integer.parseInt(cursor.getString(0)));
                medicine.setName(cursor.getString(1));
                medicine.setDoses(Integer.parseInt(cursor.getString(2)));
                medicine.setStatus(Integer.parseInt(cursor.getString(3)));

                medicineList.add(medicine);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return medicineList;

    }

    //Delete
    public void deletePill(Medicine medicine){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Util.TABLE_NAME, Util.KEY_ID + "=?",
                new String[] {String.valueOf(medicine.getId())});
        db.close();
    }

    public void deleteTable(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_NAME);
        db.close();
    }

    //Update status
    public int updateStatus(Medicine medicine){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Util.KEY_NAME, medicine.getName());
        values.put(Util.KEY_DOSES, medicine.getDoses());
        values.put(Util.KEY_STATUS, medicine.getStatus());
        int res = db.update(Util.TABLE_NAME, values, Util.KEY_ID + "=?",
                new String[]{String.valueOf(medicine.getId())});

        db.close();
        return res;
    }

}
