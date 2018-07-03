package Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import Utils.Util;

/**
 * Created by marti on 22.06.2018.
 */
public class DbStartHandler extends SQLiteOpenHelper  {

    public DbStartHandler(Context context) {
        super(context, Util.DATABASE2_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + Util.START_TABLE_NAME + " (" + Util.KEY_DATE +
                " INTEGER)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + Util.START_TABLE_NAME);
        onCreate(db);
    }

    //add
    public boolean addDate(int date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(Util.KEY_DATE, date);
        db.insert(Util.START_TABLE_NAME, null, value);
        db.close();
        return true;
    }

    //get
    public int getDate() {

        SQLiteDatabase db = this.getReadableDatabase();
        String selectAll = "SELECT * FROM " + Util.START_TABLE_NAME;

        Cursor cursor = db.rawQuery(selectAll, null);

        cursor.moveToLast();
        if (cursor.getCount()==0){
            return 0;
        }
        else {
            int date = Integer.parseInt(cursor.getString(0));
            cursor.close();
            db.close();
            return date;
        }
    }
}
