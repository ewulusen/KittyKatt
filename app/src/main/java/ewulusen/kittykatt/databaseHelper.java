package ewulusen.kittykatt;
import android.content.*;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.util.Calendar;

/**
 * Created by diszterhoft.zoltan on 2017.11.21..
 */

public class databaseHelper extends SQLiteOpenHelper{
    public static final String DatabaseName="users.db";
    public static final String tableName="users_table";
    public static final String unitName="unit_table";
    public static final String col1="azon";
    public static final String col2="Name";
    public static final String col3="Password";
    public static final String col4="Money";
    public static final String col5="c1";
    public static final String col6="c2";
    public static final String col7="c3";
    public static final String col8="c4";
    public static final String col9="c5";
    public static final String col10="c6";
    public static final String col11="c7";
    public static final String col12="lvl1";
    public static final String col13="lvl2";
    public static final String col14="lvl3";
    public static final String col15="lvl4";
    public static final String col16="lvl5";
    public static final String col17="p1";
    public static final String col18="p2";
    public static final String col19="p3";
    public static final String col20="p4";
    public static final String col21="p5";
    public static final String col22="p6";
    public static final String col23="p7";
    public static final String col24="gyp1";
    public static final String col25="gyp2";
    public static final String col26="gyp3";
    public static final String col27="gyp4";
    public static final String col28="gyp5";
    public static final String col29="ID";
    public static final String col30="HP";
    public static final String col31="DMG";
    public static final String col32="INI";
    public static final String col33="MOVE";
    public static final String col34="DEF";
    public static final String col35="TYPE";



    public databaseHelper(Context context) {
        super(context, DatabaseName, null, 12    );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE IF NOT EXISTS " + tableName + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " Name TEXT, Password TEXT, Money TEXT DEFAULT 0,\n" +
                "  c1 TEXT DEFAULT '0',\n" +
                "  c2 TEXT DEFAULT '0',\n" +
                "  c3 TEXT DEFAULT '0',\n" +
                "  c4 TEXT DEFAULT '0',\n" +
                "  c5 TEXT DEFAULT '0',\n" +
                "  c6 TEXT DEFAULT '0',\n" +
                "  c7 TEXT DEFAULT '0',\n" +
                "  lvl1 TEXT DEFAULT '1',\n" +
                "  lvl2 TEXT DEFAULT '0',\n" +
                "  lvl3 TEXT DEFAULT '0',\n" +
                "  lvl4 TEXT DEFAULT '0',\n" +
                "  lvl5 TEXT DEFAULT '0',\n" +
                "  p1 TEXT DEFAULT '10000',\n" +
                "  p2 TEXT DEFAULT '100000',\n" +
                "  p3 TEXT DEFAULT '1000000',\n" +
                "  p4 TEXT DEFAULT '10000000',\n" +
                "  p5 TEXT DEFAULT '100000000',\n" +
                "  p6 TEXT DEFAULT '1000000000',\n" +
                "  p7 TEXT DEFAULT '10000000000',\n" +
                "  gyp1 TEXT DEFAULT '1',\n" +
                "  gyp2 TEXT DEFAULT '3',\n" +
                "  gyp3 TEXT DEFAULT '5',\n" +
                "  gyp4 TEXT DEFAULT '10',\n" +
                "  gyp5 TEXT DEFAULT '25'," +
                " azon TEXT)";
        sqLiteDatabase.execSQL(createTable);
       createTable = "CREATE TABLE IF NOT EXISTS " + unitName +
               "( ID TEXT , HP TEXT,  DMG TEXT, INI TEXT,"+
  "MOVE TEXT, DEF TEXT,TYPE TEXT)";
        sqLiteDatabase.execSQL(createTable);
        android.content.ContentValues contentValues = new android.content.ContentValues();
        contentValues.put(col29,"1");
        contentValues.put(col30,"3");
        contentValues.put(col31,"1");
        contentValues.put(col32,"2");
        contentValues.put(col33,"4");
        contentValues.put(col34,"1");
        contentValues.put(col35,"A");//Archer
        sqLiteDatabase.insert(unitName, null, contentValues);
        contentValues.clear();
        contentValues.put(col29,"2");
        contentValues.put(col30,"6");
        contentValues.put(col31,"3");
        contentValues.put(col32,"2");
        contentValues.put(col33,"3");
        contentValues.put(col34,"1");
        contentValues.put(col35,"C");//Knight
        sqLiteDatabase.insert(unitName, null, contentValues); contentValues.clear();
        contentValues.put(col29,"3");
        contentValues.put(col30,"5");
        contentValues.put(col31,"6");
        contentValues.put(col32,"6");
        contentValues.put(col33,"6");
        contentValues.put(col34,"3");
        contentValues.put(col35,"B");//Centaur
        sqLiteDatabase.insert(unitName, null, contentValues); contentValues.clear();
        contentValues.put(col29,"4");
        contentValues.put(col30,"10");
        contentValues.put(col31,"10");
        contentValues.put(col32,"5");
        contentValues.put(col33,"4");
        contentValues.put(col34,"2");
        contentValues.put(col35,"C");
        sqLiteDatabase.insert(unitName, null, contentValues); contentValues.clear();
        contentValues.put(col29,"5");
        contentValues.put(col30,"5");
        contentValues.put(col31,"5");
        contentValues.put(col32,"9");
        contentValues.put(col33,"8");
        contentValues.put(col34,"2");
        contentValues.put(col35,"C");
        sqLiteDatabase.insert(unitName, null, contentValues); contentValues.clear();
        contentValues.put(col29,"6");
        contentValues.put(col30,"13");
        contentValues.put(col31,"7");
        contentValues.put(col32,"7");
        contentValues.put(col33,"5");
        contentValues.put(col34,"2");
        contentValues.put(col35,"C");
        sqLiteDatabase.insert(unitName, null, contentValues); contentValues.clear();
        contentValues.put(col29,"7");
        contentValues.put(col30,"20");
        contentValues.put(col31,"12");
        contentValues.put(col32,"9");
        contentValues.put(col33,"5");
        contentValues.put(col34,"1");
        contentValues.put(col35,"C");
        sqLiteDatabase.insert(unitName, null, contentValues); contentValues.clear();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE if EXISTS " + tableName);
        onCreate(sqLiteDatabase);
        sqLiteDatabase.execSQL("DROP TABLE if EXISTS " + unitName);
        onCreate(sqLiteDatabase);
    }
    public boolean addData(String name, String password,String money) {
        SQLiteDatabase db = this.getWritableDatabase();
        android.content.ContentValues contentValues = new android.content.ContentValues();
        String id=name+"_sajtosrud";
        contentValues.put(col1,id);
        contentValues.put(col2,name);
        contentValues.put(col3,password);
        contentValues.put(col4,money);

        long result  = db.insert(tableName, null, contentValues);

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor login(String name,String psw){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql="SELECT * FROM " + tableName+ " where Name='"+name+"' and Password='"+psw+"'";
        //Log.d("SQL",sql);
        Cursor data = db.rawQuery(sql, null);
        return data;
    }
    public Cursor backLogin(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql="SELECT * FROM " + tableName+ " where ID="+id+"";
        //Log.d("SQL",sql);
        Cursor data = db.rawQuery(sql, null);
        return data;
    }
    public boolean saveData(String[] datas)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col4, datas[2]);
        contentValues.put(col5, datas[3]);
        contentValues.put(col6, datas[4]);
        contentValues.put(col7, datas[5]);
        contentValues.put(col8, datas[6]);
        contentValues.put(col9, datas[7]);
        contentValues.put(col10, datas[8]);
        contentValues.put(col11, datas[9]);
        contentValues.put(col12, datas[10]);
        contentValues.put(col13, datas[11]);
        contentValues.put(col14, datas[12]);
        contentValues.put(col15, datas[13]);
        contentValues.put(col16, datas[14]);
        contentValues.put(col17, datas[15]);
        contentValues.put(col18, datas[16]);
        contentValues.put(col19, datas[17]);
        contentValues.put(col20, datas[18]);
        contentValues.put(col21, datas[19]);
        contentValues.put(col22, datas[20]);
        contentValues.put(col23, datas[21]);
        contentValues.put(col24, datas[22]);
        contentValues.put(col25, datas[23]);
        contentValues.put(col26, datas[24]);
        contentValues.put(col27, datas[25]);
        contentValues.put(col28, datas[26]);
        db.update(tableName, contentValues, "ID ="+datas[0],null);
        Log.d("update","kész");
        return true;

    }
    public Cursor getUnit(String ID)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql="SELECT * FROM " + unitName+ " where ID='"+ID+"'";

        Cursor data = db.rawQuery(sql, null);
        return data;
    }
}

