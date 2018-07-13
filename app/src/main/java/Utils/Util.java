package Utils;

/**
 * Created by Martin Agnar Dahl on 25.01.2018.
 */

public class Util {

    //DB Version
    public static final int DATABASE_VERSION = 1;
    public static final int DATABASE_VERSION2 = 1;
    public static final int DATABASE_VERSION3 = 1;

    //db names
    public static final String DATABASE_NAME = "pillsDB";
    public static final String DATABASE2_NAME = "status";
    public static final String DATABASE3_NAME = "alarm";

    //tables
    public static final String TABLE_NAME = "pills";
    public static final String START_TABLE_NAME = "status";
    public static final String ALARM_TABLE_NAME = "alarm_status";

    //db1
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_DOSES = "doses";
    public static final String KEY_STATUS = "status";

    //db2
    public static final String KEY_DATE = "date";

    //db3
    public static final String KEY_ALARM_STATUS = "alarm_status";
}
