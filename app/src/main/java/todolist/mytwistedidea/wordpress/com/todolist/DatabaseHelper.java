package todolist.mytwistedidea.wordpress.com.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;

import static javax.xml.datatype.DatatypeConstants.DATETIME;

/**
 * Created by Nishant on 22-02-2017.
 */

public class DatabaseHelper {

    static MyHelper helper;

    public DatabaseHelper(Context context){
        helper = new MyHelper(context);
    }

    public long insertTodo(String assignment, String date, String time){
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyHelper.ASSIGNMENT,assignment);
        contentValues.put(MyHelper.DATES,date);
        contentValues.put(MyHelper.TIMEHOUR,time);

        Long id = sqLiteDatabase.insert(MyHelper.TABLE_NAME_TODOLIST,null,contentValues);
        sqLiteDatabase.close();
        return id;

    }

    public static ArrayList<String> getStudent(String date){

        SQLiteDatabase sqLiteDatabase = helper.getReadableDatabase();
        ArrayList<Integer> previousRoll = new ArrayList<Integer>();
        String columns[] = {MyHelper.DATES};

        String query = "SELECT * FROM "+ MyHelper.TABLE_NAME_TODOLIST+" WHERE roll='" + date;

        Cursor  cursor = sqLiteDatabase.rawQuery(query,null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        ArrayList<String> arrayList = new ArrayList<String>();

        arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.ASSIGNMENT)));
        arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.DATES)));
        arrayList.add(cursor.getString(cursor.getColumnIndex(MyHelper.TIMEHOUR)));

        return arrayList;
        /*
        Cursor cursor = sqLiteDatabase.query(MyHelper.TABLE_NAME_STUDENT,columns,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                previousRoll.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex(MyHelper.ROLL))));
            }while (cursor.moveToNext());
        }if(previousRoll.size() == 0){
            previousRoll.add(0,0);
            return previousRoll;
        }
        return previousRoll;*/
    }

    public static ArrayList<String> getAllAssignments(){

        SQLiteDatabase sqLiteDatabase = helper.getReadableDatabase();
        ArrayList<String> previousStudent = new ArrayList<String>();
        String columns[] = {MyHelper.ASSIGNMENT,MyHelper.DATES, MyHelper.TIMEHOUR};

        Cursor cursor = sqLiteDatabase.query(MyHelper.TABLE_NAME_TODOLIST,columns,null,null,null,null, MyHelper.DATES+" ASC");
        if(cursor.moveToFirst()){
            do{
                previousStudent.add(cursor.getString(cursor.getColumnIndex(MyHelper.ASSIGNMENT)));
                previousStudent.add(cursor.getString(cursor.getColumnIndex(MyHelper.DATES)));
                previousStudent.add(cursor.getString(cursor.getColumnIndex(MyHelper.TIMEHOUR)));
            }while (cursor.moveToNext());
        }if(previousStudent.size() == 0){
            previousStudent.add(0," ");
            return previousStudent;
        }
        return previousStudent;
    }


    // in Database get Roll Field
    public static ArrayList<String> getAssignmentAs(){

        SQLiteDatabase sqLiteDatabase = helper.getReadableDatabase();
        ArrayList<String> previousRoll = new ArrayList<String>();
        String columns[] = {MyHelper.ASSIGNMENT};

        Cursor cursor = sqLiteDatabase.query(MyHelper.TABLE_NAME_TODOLIST,columns,null,null,null,null, MyHelper.DATES+" ASC");
        if(cursor.moveToFirst()){
            do{
                previousRoll.add(cursor.getString(cursor.getColumnIndex(MyHelper.ASSIGNMENT)));
            }while (cursor.moveToNext());
        }if(previousRoll.size() == 0){
            previousRoll.add(0," ");
            return previousRoll;
        }
        return previousRoll;
    }
    // in Database get Name Field
    public static ArrayList<String> getAssignmentDates(){
        SQLiteDatabase sqLiteDatabase = helper.getReadableDatabase();
        ArrayList<String> previousName = new ArrayList<String>();
        String columns[] = {MyHelper.DATES};

        Cursor cursor = sqLiteDatabase.query(MyHelper.TABLE_NAME_TODOLIST,columns,null,null,null,null, MyHelper.DATES+" ASC");
        if(cursor.moveToFirst()){
            do{
                previousName.add(cursor.getString(cursor.getColumnIndex(MyHelper.DATES)));
            }while (cursor.moveToNext());
        }if(previousName.size() == 0){
            previousName.add(0," ");
            return previousName;
        }
        return previousName;

    }

    // in Database get Attendence Field

    public static ArrayList<String> getAssignmentTimeHour(){
        SQLiteDatabase sqLiteDatabase = helper.getReadableDatabase();
        ArrayList<String> previousAttendence = new ArrayList<String>();
        String columns[] = {MyHelper.TIMEHOUR};

        Cursor cursor = sqLiteDatabase.query(MyHelper.TABLE_NAME_TODOLIST,columns,null,null,null,null, MyHelper.DATES+" ASC");
        if(cursor.moveToFirst()){
            do{
                previousAttendence.add(cursor.getString(cursor.getColumnIndex(MyHelper.TIMEHOUR)));
            }while (cursor.moveToNext());
        }if(previousAttendence.size() == 0){
            previousAttendence.add(0," ");
            return previousAttendence;
        }
        return previousAttendence;

    }


    class MyHelper extends SQLiteOpenHelper{
        private static final String DATABASE_NAME = "mytodolist.db";
        private static final String TABLE_NAME_TODOLIST = "todolist";
        private static final int DATABASE_VERSION = 1;

        private static final String ASSIGNMENT = "assignment";
        private static final String UID = "uid";
        private static final String DATES = "date";
        private static final String TIMEHOUR = "time";

        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME_TODOLIST+
                " ("+UID+" INTEGER PRIMARY KEY ,"+
                ASSIGNMENT+" VARCHAR(255),"+
                DATES+" VARCHAR(255),"+
                TIMEHOUR+" VARCHAR(255));";

        private static final String DROP_TODOLIST = "DROP TABLE IF EXISTS "+TABLE_NAME_TODOLIST;

        private Context context;

        public MyHelper(Context context){
            super(context,DATABASE_NAME,null,DATABASE_VERSION);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            if(db != null){
                try{
                db.execSQL(CREATE_TABLE);
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try{
                db.execSQL(DROP_TODOLIST);
                onCreate(db);
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try{
                db.execSQL(DROP_TODOLIST);
                onCreate(db);
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
