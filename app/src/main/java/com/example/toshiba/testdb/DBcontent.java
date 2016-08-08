package com.example.toshiba.testdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Toshiba on 2016/7/20.
 */
public class DBcontent {
    public static final String TABLE_NAME_WALK = "walk";
    public static final String WALK_KEY_ID = "_id";// 編號表格欄位名稱，固定不變
    public static final String WALK_DATETIME_COLUMN = "datetime";// 其它表格欄位名稱
    public static final String WALK_STEP_COLUMN = "step";
    public static final String WALK_CALORIES_COLUMN = "calories";


    public static final String TABLE_NAME_BMI = "bmi";
    public static final String BMI_KEY_ID = "_id";
    public static final String BMI_DATETIME_COLUMN = "datetime";// 其它表格欄位名稱
    public static final String BMI_HEIGHT_COLUMN = "height";
    public static final String BMI_WEIGHT_COLUMN = "weight";
    public static final String BMI_BMIVALUE_COLUMN = "bmivalue";

    // 使用上面宣告的變數建立表格的SQL指令
    public static final String CREATE_TABLE_WALK =
            "CREATE TABLE " + TABLE_NAME_WALK + " (" +
                    WALK_KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    WALK_DATETIME_COLUMN + " INTEGER NOT NULL, " +
                    WALK_STEP_COLUMN + " INTEGER NOT NULL, " +
                    WALK_CALORIES_COLUMN + " INTEGER NOT NULL)";

    public static final String CREATE_TABLE_BMI =
            "CREATE TABLE " + TABLE_NAME_BMI + " (" +
                    BMI_KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    BMI_DATETIME_COLUMN + " INTEGER NOT NULL, " +
                    BMI_HEIGHT_COLUMN + " REAL NOT NULL, " +
                    BMI_WEIGHT_COLUMN + " REAL NOT NULL, " +
                    BMI_BMIVALUE_COLUMN + " REAL NOT NULL)" ;
    // 資料庫物件
    private SQLiteDatabase db;
    // 建構子
    public DBcontent(Context context) {
        db=MyDBHelper.getDatabase(context);
    }

    // 關閉資料庫，一般的應用都不需要修改
    public void close() {
        db.close();
    }

    public void insert_walk(int datetime, int step, int calories) {
        // 建立準備新增資料的ContentValues物件
        ContentValues cv = new ContentValues();
        // 加入ContentValues物件包裝的新增資料
        // 第一個參數是欄位名稱， 第二個參數是欄位的資料

        cv.put(BMI_HEIGHT_COLUMN, datetime);
        cv.put(BMI_WEIGHT_COLUMN, step);
        cv.put(BMI_BMIVALUE_COLUMN, calories);

        // 新增一筆資料並取得編號
        // 第一個參數是表格名稱
        // 第二個參數是沒有指定欄位值的預設值
        // 第三個參數是包裝新增資料的ContentValues物件
        long id = db.insert(TABLE_NAME_WALK, null, cv);
    }

    public void insert_bmi(int datetime,float height, float weight) {
        float bmivalue=0;
        // 建立準備新增資料的ContentValues物件
        ContentValues cv = new ContentValues();
        // 加入ContentValues物件包裝的新增資料
        // 第一個參數是欄位名稱， 第二個參數是欄位的資料
        cv.put(BMI_DATETIME_COLUMN, datetime);
        cv.put(BMI_HEIGHT_COLUMN, height);
        cv.put(BMI_WEIGHT_COLUMN, weight);
        bmivalue=weight/((height/100)*(height/100));
        cv.put(BMI_BMIVALUE_COLUMN, bmivalue);
        Log.e("2", String.valueOf(height));
        Log.e("3",String.valueOf(weight));
        Log.e("ano",String.valueOf((height/100)));
        Log.e("4",String.valueOf(bmivalue));

        // 新增一筆資料並取得編號
        // 第一個參數是表格名稱
        // 第二個參數是沒有指定欄位值的預設值
        // 第三個參數是包裝新增資料的ContentValues物件
        long id = db.insert(TABLE_NAME_BMI, null, cv);
    }

    public boolean update_walk(long id,int datetime, int step, int calories) {
        // 建立準備修改資料的ContentValues物件
        ContentValues cv = new ContentValues();

        // 加入ContentValues物件包裝的修改資料
        // 第一個參數是欄位名稱， 第二個參數是欄位的資料
        cv.put(BMI_HEIGHT_COLUMN, datetime);
        cv.put(BMI_WEIGHT_COLUMN, step);
        cv.put(BMI_BMIVALUE_COLUMN, calories);

        // 設定修改資料的條件為編號
        // 格式為「欄位名稱＝資料」
        String where = WALK_KEY_ID + "=" + id;

        // 執行修改資料並回傳修改的資料數量是否成功
        return db.update(TABLE_NAME_WALK, cv, where, null) > 0;
    }

    public boolean update_bmi(long id,int datetime,float height, float weight) {
        float bmivalue=0;
        // 建立準備修改資料的ContentValues物件
        ContentValues cv = new ContentValues();

        // 加入ContentValues物件包裝的修改資料
        // 第一個參數是欄位名稱， 第二個參數是欄位的資料
        cv.put(BMI_DATETIME_COLUMN, datetime);
        cv.put(BMI_HEIGHT_COLUMN, height);
        cv.put(BMI_WEIGHT_COLUMN, weight);
        bmivalue=weight/((height/100)*(height/100));
        cv.put(BMI_BMIVALUE_COLUMN, bmivalue);

        // 設定修改資料的條件為編號
        // 格式為「欄位名稱＝資料」
        String where = BMI_KEY_ID + "=" + id;

        // 執行修改資料並回傳修改的資料數量是否成功
        return db.update(TABLE_NAME_BMI, cv, where, null) > 0;
    }

    public boolean delete_walk(long id){
        // 設定條件為編號，格式為「欄位名稱=資料」
        String where = WALK_KEY_ID + "=" + id;
        // 刪除指定編號資料並回傳刪除是否成功
        return db.delete(TABLE_NAME_WALK, where, null) > 0;
    }

    public boolean delete_bmi(long id){
        // 設定條件為編號，格式為「欄位名稱=資料」
        String where = BMI_KEY_ID + "=" + id;
        // 刪除指定編號資料並回傳刪除是否成功
        return db.delete(TABLE_NAME_BMI, where, null) > 0;
    }
    // 取得資料數量
    public int getCount_walk() {
        int result = 0;
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME_WALK, null);

        if (cursor.moveToNext()) {
            result = cursor.getInt(0);
        }
        return result;
    }
    // 取得資料數量
    public int getCount_bmi() {
        int result = 0;
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME_BMI, null);
        if (cursor.moveToNext()) {
            result = cursor.getInt(0);
        }
        return result;
    }

    // 取得指定編號的資料物件
    public float get_bmi(long id) {
        // 使用編號為查詢條件
        int a=1;
        // 執行查詢
        Cursor cur;

        cur =db.rawQuery("SELECT * FROM " + TABLE_NAME_BMI, null);
        if(cur.getCount()!=0) {
            if(cur.moveToFirst()) {
                do {
                    if(cur.getLong(0)==id){
                        // 關閉Cursor物件
                        //cur.close();
                        // 回傳結果
                        break;
                    }
                    else a++;
                } while(cur.moveToNext());
            }
        }
        return cur.getFloat(4);
    }
    public void sample() {
        insert_bmi(20160721, 150, 45);
        insert_bmi(20160721, 160, 50);
    }

}
