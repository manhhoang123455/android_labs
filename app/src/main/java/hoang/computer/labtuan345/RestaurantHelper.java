package hoang.computer.labtuan345;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class RestaurantHelper  extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "lunchlist.db";
    private static final int SCHEMA_VERSION = 1;
    public RestaurantHelper()
    {
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
    }
    public RestaurantHelper(Context context, String name,
                            CursorFactory factory, int version) {
        super(context, name, factory, version);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE restaurants (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, address TEXT, type TEXT);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    public void insert(String name, String address, String type)
    {
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("address",address);
        cv.put("type", type);
        getWritableDatabase().insert("restaurants", "name", cv);
    }
    public Cursor getAll()
    {
        Cursor cur;
        cur = getReadableDatabase().rawQuery("SELECT _id, name, address, type FROM restaurants ORDER BY name", null);
        return (cur);
    }
    public String getName(Cursor c)
    {
        return (c.getString(1));
    }
    public String getAddress(Cursor c)
    {
        return (c.getString(2));
    }
    public String getType(Cursor c)
    {
        return (c.getString(3));
    }
}
