package Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import Utils.Util;

/**
 * Created by Martin Agnar Dah on 13.07.2018.
 */
public class DbAlarmHandler extends SQLiteOpenHelper {

    public DbAlarmHandler(Context context) {
        super(context, Util.DATABASE3_NAME, null, Util.DATABASE_VERSION3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + Util.ALARM_TABLE_NAME + " (" + Util.KEY_ALARM_STATUS +
                " INTEGER)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + Util.ALARM_TABLE_NAME);
        onCreate(db);
    }

    //add
    public void addStatus(int status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(Util.KEY_ALARM_STATUS, status);
        db.insert(Util.ALARM_TABLE_NAME, null, value);
        db.close();
    }

    //get
    public int getStatus() {

        SQLiteDatabase db = this.getReadableDatabase();
        String selectAll = "SELECT * FROM " + Util.ALARM_TABLE_NAME;

        Cursor cursor = db.rawQuery(selectAll, null);

        cursor.moveToLast();
        if (cursor.getCount()==0){
            return 0;
        }
        else {
            int stat = cursor.getInt(0);
            cursor.close();
            db.close();
            return stat;
        }
    }
}