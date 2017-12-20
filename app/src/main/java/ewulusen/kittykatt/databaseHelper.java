package ewulusen.kittykatt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.sql.Timestamp;
import java.time.Instant;


public class databaseHelper
        extends SQLiteOpenHelper
{
    public static final String DatabaseName = "users.db";
    public static final String col1 = "azon";
    public static final String col10 = "c6";
    public static final String col11 = "c7";
    public static final String col12 = "lvl1";
    public static final String col13 = "lvl2";
    public static final String col14 = "lvl3";
    public static final String col15 = "lvl4";
    public static final String col16 = "lvl5";
    public static final String col17 = "p1";
    public static final String col18 = "p2";
    public static final String col19 = "p3";
    public static final String col2 = "Name";
    public static final String col20 = "p4";
    public static final String col21 = "p5";
    public static final String col22 = "p6";
    public static final String col23 = "p7";
    public static final String col24 = "gyp1";
    public static final String col25 = "gyp2";
    public static final String col26 = "gyp3";
    public static final String col27 = "gyp4";
    public static final String col28 = "gyp5";
    public static final String col29 = "ID";
    public static final String col3 = "Password";
    public static final String col30 = "HP";
    public static final String col31 = "DMG";
    public static final String col32 = "INI";
    public static final String col33 = "MOVE";
    public static final String col34 = "DEF";
    public static final String col35 = "TYPE";
    public static final String col4 = "Money";
    public static final String col5 = "c1";
    public static final String col6 = "c2";
    public static final String col7 = "c3";
    public static final String col8 = "c4";
    public static final String col9 = "c5";
    public static final String tableName = "users_table";
    public static final String unitName = "unit_table";

    public databaseHelper(Context paramContext)
    {
        super(paramContext, "users.db", null, 12);
    }

    public boolean addData(String paramString1, String paramString2, String paramString3)
    {
        SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("Name", paramString1);
        localContentValues.put("Password", paramString2);
        localContentValues.put("Money", paramString3);
        return localSQLiteDatabase.insert("users_table", null, localContentValues) != -1L;
    }

    public Cursor backLogin(String paramString)
    {
        return getReadableDatabase().rawQuery("SELECT * FROM users_table where ID=" + paramString + "", null);
    }

    public Cursor getUnit(String paramString)
    {
        return getReadableDatabase().rawQuery("SELECT * FROM unit_table where ID='" + paramString + "'", null);
    }

    public Cursor login(String paramString1, String paramString2, String paramString3)
    {
        SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
        Log.d("SQL", paramString2);
        String str1 = "SELECT * FROM users_table where Password='" + paramString2 + "'";
        Log.d("SQL", str1);
        Cursor localCursor = localSQLiteDatabase.rawQuery(str1, null);
        Cursor localCursor2 = localSQLiteDatabase.rawQuery(str1, null);
        if (localCursor.getCount() == 0)
        {
            addData(paramString1, paramString2, "0");
            String str2 = "SELECT * FROM users_table where Password='" + paramString2 + "'";
            //Log.d("SQL", str2);
            localCursor = localSQLiteDatabase.rawQuery(str2, null);
        }
       /* else
        {
            localCursor2.moveToNext();
            ContentValues localContentValues = new ContentValues();
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            localContentValues.put("azon", timestamp.toString());
            localSQLiteDatabase.update("users_table", localContentValues, "ID =" + localCursor2.getString(0), null);
            String str2 = "SELECT * FROM users_table where Password='" + paramString2 + "'";
            localCursor = localSQLiteDatabase.rawQuery(str2, null);
        }*/
        return localCursor;
    }

    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
        paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS users_table (ID INTEGER PRIMARY KEY AUTOINCREMENT,  Name TEXT, Password TEXT, Money TEXT DEFAULT 0,\n  c1 TEXT DEFAULT '0',\n  c2 TEXT DEFAULT '0',\n  c3 TEXT DEFAULT '0',\n  c4 TEXT DEFAULT '0',\n  c5 TEXT DEFAULT '0',\n  c6 TEXT DEFAULT '0',\n  c7 TEXT DEFAULT '0',\n  lvl1 TEXT DEFAULT '1',\n  lvl2 TEXT DEFAULT '0',\n  lvl3 TEXT DEFAULT '0',\n  lvl4 TEXT DEFAULT '0',\n  lvl5 TEXT DEFAULT '0',\n  p1 TEXT DEFAULT '10000',\n  p2 TEXT DEFAULT '100000',\n  p3 TEXT DEFAULT '1000000',\n  p4 TEXT DEFAULT '10000000',\n  p5 TEXT DEFAULT '100000000',\n  p6 TEXT DEFAULT '1000000000',\n  p7 TEXT DEFAULT '10000000000',\n  gyp1 TEXT DEFAULT '1',\n  gyp2 TEXT DEFAULT '3',\n  gyp3 TEXT DEFAULT '5',\n  gyp4 TEXT DEFAULT '10',\n  gyp5 TEXT DEFAULT '25', azon DATETIME DEFAULT CURRENT_TIMESTAMP)");
        paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS unit_table( ID TEXT , HP TEXT,  DMG TEXT, INI TEXT,MOVE TEXT, DEF TEXT,TYPE TEXT)");
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("ID", "1");
        localContentValues.put("HP", "3");
        localContentValues.put("DMG", "1");
        localContentValues.put("INI", "2");
        localContentValues.put("MOVE", "4");
        localContentValues.put("DEF", "1");
        localContentValues.put("TYPE", "A");
        paramSQLiteDatabase.insert("unit_table", null, localContentValues);
        localContentValues.clear();
        localContentValues.put("ID", "2");
        localContentValues.put("HP", "6");
        localContentValues.put("DMG", "3");
        localContentValues.put("INI", "2");
        localContentValues.put("MOVE", "3");
        localContentValues.put("DEF", "1");
        localContentValues.put("TYPE", "C");
        paramSQLiteDatabase.insert("unit_table", null, localContentValues);
        localContentValues.clear();
        localContentValues.put("ID", "3");
        localContentValues.put("HP", "5");
        localContentValues.put("DMG", "6");
        localContentValues.put("INI", "6");
        localContentValues.put("MOVE", "6");
        localContentValues.put("DEF", "3");
        localContentValues.put("TYPE", "B");
        paramSQLiteDatabase.insert("unit_table", null, localContentValues);
        localContentValues.clear();
        localContentValues.put("ID", "4");
        localContentValues.put("HP", "10");
        localContentValues.put("DMG", "10");
        localContentValues.put("INI", "5");
        localContentValues.put("MOVE", "4");
        localContentValues.put("DEF", "2");
        localContentValues.put("TYPE", "C");
        paramSQLiteDatabase.insert("unit_table", null, localContentValues);
        localContentValues.clear();
        localContentValues.put("ID", "5");
        localContentValues.put("HP", "5");
        localContentValues.put("DMG", "5");
        localContentValues.put("INI", "9");
        localContentValues.put("MOVE", "8");
        localContentValues.put("DEF", "2");
        localContentValues.put("TYPE", "C");
        paramSQLiteDatabase.insert("unit_table", null, localContentValues);
        localContentValues.clear();
        localContentValues.put("ID", "6");
        localContentValues.put("HP", "13");
        localContentValues.put("DMG", "7");
        localContentValues.put("INI", "7");
        localContentValues.put("MOVE", "5");
        localContentValues.put("DEF", "2");
        localContentValues.put("TYPE", "C");
        paramSQLiteDatabase.insert("unit_table", null, localContentValues);
        localContentValues.clear();
        localContentValues.put("ID", "7");
        localContentValues.put("HP", "20");
        localContentValues.put("DMG", "12");
        localContentValues.put("INI", "9");
        localContentValues.put("MOVE", "5");
        localContentValues.put("DEF", "1");
        localContentValues.put("TYPE", "C");
        paramSQLiteDatabase.insert("unit_table", null, localContentValues);
        localContentValues.clear();
    }

    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
        paramSQLiteDatabase.execSQL("DROP TABLE if EXISTS users_table");
        onCreate(paramSQLiteDatabase);
        paramSQLiteDatabase.execSQL("DROP TABLE if EXISTS unit_table");
        onCreate(paramSQLiteDatabase);
    }

    public boolean saveData(String[] paramArrayOfString)
    {
        SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
        ContentValues localContentValues = new ContentValues();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        localContentValues.put("Money", paramArrayOfString[2]);
        localContentValues.put("c1", paramArrayOfString[3]);
        localContentValues.put("c2", paramArrayOfString[4]);
        localContentValues.put("c3", paramArrayOfString[5]);
        localContentValues.put("c4", paramArrayOfString[6]);
        localContentValues.put("c5", paramArrayOfString[7]);
        localContentValues.put("c6", paramArrayOfString[8]);
        localContentValues.put("c7", paramArrayOfString[9]);
        localContentValues.put("lvl1", paramArrayOfString[10]);
        localContentValues.put("lvl2", paramArrayOfString[11]);
        localContentValues.put("lvl3", paramArrayOfString[12]);
        localContentValues.put("lvl4", paramArrayOfString[13]);
        localContentValues.put("lvl5", paramArrayOfString[14]);
        localContentValues.put("p1", paramArrayOfString[15]);
        localContentValues.put("p2", paramArrayOfString[16]);
        localContentValues.put("p3", paramArrayOfString[17]);
        localContentValues.put("p4", paramArrayOfString[18]);
        localContentValues.put("p5", paramArrayOfString[19]);
        localContentValues.put("p6", paramArrayOfString[20]);
        localContentValues.put("p7", paramArrayOfString[21]);
        localContentValues.put("gyp1", paramArrayOfString[22]);
        localContentValues.put("gyp2", paramArrayOfString[23]);
        localContentValues.put("gyp3", paramArrayOfString[24]);
        localContentValues.put("gyp4", paramArrayOfString[25]);
        localContentValues.put("gyp5", paramArrayOfString[26]);
        localSQLiteDatabase.update("users_table", localContentValues, "ID =" + paramArrayOfString[0], null);
        Log.d("update", "kész");
        return true;
    }
    public boolean destroyData(String[] paramArrayOfString)
    {
        SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
        ContentValues localContentValues = new ContentValues();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        localContentValues.put("Money", paramArrayOfString[2]);
        localContentValues.put("c1", paramArrayOfString[3]);
        localContentValues.put("c2", paramArrayOfString[4]);
        localContentValues.put("c3", paramArrayOfString[5]);
        localContentValues.put("c4", paramArrayOfString[6]);
        localContentValues.put("c5", paramArrayOfString[7]);
        localContentValues.put("c6", paramArrayOfString[8]);
        localContentValues.put("c7", paramArrayOfString[9]);
        localContentValues.put("lvl1", paramArrayOfString[10]);
        localContentValues.put("lvl2", paramArrayOfString[11]);
        localContentValues.put("lvl3", paramArrayOfString[12]);
        localContentValues.put("lvl4", paramArrayOfString[13]);
        localContentValues.put("lvl5", paramArrayOfString[14]);
        localContentValues.put("p1", paramArrayOfString[15]);
        localContentValues.put("p2", paramArrayOfString[16]);
        localContentValues.put("p3", paramArrayOfString[17]);
        localContentValues.put("p4", paramArrayOfString[18]);
        localContentValues.put("p5", paramArrayOfString[19]);
        localContentValues.put("p6", paramArrayOfString[20]);
        localContentValues.put("p7", paramArrayOfString[21]);
        localContentValues.put("gyp1", paramArrayOfString[22]);
        localContentValues.put("gyp2", paramArrayOfString[23]);
        localContentValues.put("gyp3", paramArrayOfString[24]);
        localContentValues.put("gyp4", paramArrayOfString[25]);
        localContentValues.put("gyp5", paramArrayOfString[26]);
        localContentValues.put("azon", timestamp.toString());
        localSQLiteDatabase.update("users_table", localContentValues, "ID =" + paramArrayOfString[0], null);
        Log.d("update", "kész");
        return true;
    }
}
