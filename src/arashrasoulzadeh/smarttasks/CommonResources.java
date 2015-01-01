package arashrasoulzadeh.smarttasks;

import ir.adad.Adad;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.text.format.Time;
import android.util.Log;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;

public class CommonResources {
	public static Bitmap photoFinishBitmap = null;
	public static String cid = null;
	public static SharedPreferences settings;
	public static SharedPreferences.Editor editor;
	public static Boolean Loged = false;
	public static int pinentlast = 0;
	private static MyDatabase mydb;
	private static SQLiteDatabase db;
	static Context ctx;

	public static void ReturnCity(int num) {

	}

	public static void Initialize(Context c, ActionBar actionBar) {
		settings = c.getSharedPreferences("prefs", 0);
		editor = settings.edit();
		mydb = new MyDatabase(c);
		ctx = c;
		actionBar.setBackgroundDrawable(new ColorDrawable(c.getResources()
				.getColor(R.color.ForGround)));
		HandleAds();
	}

	public static void Initialize(Context c) {
		settings = c.getSharedPreferences("prefs", 0);
		editor = settings.edit();
		mydb = new MyDatabase(c);
		ctx = c;
		HandleAds();
	}

	public static void SafeInvoke(java.lang.reflect.Method cmd) {
		Log("Safe Invoke Called.Tricky !");

		try {
			cmd.invoke(null);
		} catch (Exception err) {
			try {
				Log(err.getMessage().toString());
			} catch (Exception e) {
			}

		}
	}

	public static void HandleAds() {
		if (Premium()) {
			Adad.setDisabled(true);

		} else {

		}
	}

	public static Boolean Premium() {
		if (settings.getBoolean("PREMIUM", false)) {
			return true;
		} else {

			return false;
		}
	}

	public static int GetLastInsertedRowID(String table) {
		db = mydb.getWritableDatabase();

		String sql = "SELECT * from " + table + " ORDER BY _id asc";
		String temp = "1";
		Cursor allrows = db.rawQuery(sql, null);
		int index = 0;
		if (allrows.moveToFirst()) {
			do {
				temp = allrows.getString(0).toString();

			} while (allrows.moveToNext());
		}
		allrows.close();
		int a = Integer.valueOf(temp);
		return a;
	}

	public static String GetText(int column, String table, String id) {
		db = mydb.getWritableDatabase();

		String sql = "SELECT * from " + table + " WHERE _id='" + id + "'";
		String temp = "1";
		Cursor allrows = db.rawQuery(sql, null);
		int index = 0;
		if (allrows.moveToFirst()) {
			do {
				temp = allrows.getString(column).toString();

			} while (allrows.moveToNext());
		}
		allrows.close();
		return temp;
	}

	public static String GetTime() {
		Time now = new Time();
		now.setToNow();

		String ret = Time.YEAR + "/" + Time.MONTH + "/" + Time.MONTH_DAY + "-"
				+ Time.HOUR + ":" + Time.MINUTE + ":" + Time.SECOND;
		return ret;
	}

	public static void PostToTimeline(int type, int refrence, String extra) {
		db = mydb.getWritableDatabase();
		String date = GetTime();

		String sql = "INSERT INTO timeline VALUES(null,'" + type + "','"
				+ refrence + "','" + date + "')";
		db.execSQL(sql);
		Toast.makeText(ctx, sql, 1).show();
	}

	public static void Log(String message) {
		// TODO Auto-generated method stub
		Time now = new Time();
		now.setToNow();

		writeToFile("\n\n-----------------------------------\nLoged at "
				+ now.year + "/" + now.month + "/" + now.monthDay + "- "
				+ now.hour + ":" + now.minute + ":" + now.second
				+ "\n\nReport : \n" + message);
	}

	static void writeToFile(String text) {
		File logFile = new File("sdcard/mohafezlog.txt");
		if (!logFile.exists()) {
			try {
				logFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			// BufferedWriter for performance, true to set append to file flag
			BufferedWriter buf = new BufferedWriter(new FileWriter(logFile,
					true));
			buf.append(text);
			buf.newLine();
			buf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}