package com.sp.p2032203assignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class DeliveryHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "deliveryList.db";
    private static final int SCHEMA_VERSION = 1;  //a formal declaration of how the database is organized

    public DeliveryHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Will be called once when the database is not created
        db.execSQL("CREATE TABLE package_table ( _id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " packageId TEXT, address TEXT, photo BLOB, status TEXT);");
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
        // Will not be called until SCHEMA_VERSION increases
        // Here we can upgrade the database e.g. add more tables
    }

    /* Read all records from package_table */
    public Cursor getAll() {
        return (getReadableDatabase().rawQuery(
                "SELECT _id, packageId, address, photo," +
                        " status FROM package_table ORDER BY packageId", null));
    }

    /* Write a record into package_table */
    public void insert (String packageId, String address,
                        byte[] photo, String status) {
        ContentValues cv = new ContentValues();

        cv.put("packageId", packageId );
        cv.put("address", address );
        cv.put("photo", photo );
        cv.put("status", status );

        getWritableDatabase().insert("package_table", "packageId", cv);
    }

    public void update (String packageId, String address,
                        byte[] photo, String status) {
        ContentValues cv = new ContentValues();

        cv.put("packageId", packageId );
        cv.put("address", address );
        cv.put("photo", photo );
        cv.put("status", status );

        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of our course which is stored in original name variable.
        getWritableDatabase().update("package_table", cv, "_id = ?", new String[]{packageId});
    }

    public String getCompletedPercent() {
        Cursor c = getReadableDatabase().rawQuery(
            "SELECT status,ROUND((count(*)*100)/(SELECT count(*) FROM package_table)) as percentage " +
                "FROM package_table GROUP BY status", null);
//        return c.getColumnName(0);
        c.moveToPosition(0);
        return c.getString(1);
    }

    public String getPackageId(Cursor c) {
        return (c.getString(1));
    }

    public String getAddress(Cursor c) {
        return (c.getString(2));
    }

    public Bitmap getPhoto(Cursor c) {
        byte[] byteArray = c.getBlob(3);
        Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0 ,byteArray.length);

        return bm;
    }

    public String getStatus(Cursor c) {
        return (c.getString(4));
    }

}

