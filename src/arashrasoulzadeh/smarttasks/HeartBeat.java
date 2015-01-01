package arashrasoulzadeh.smarttasks;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.provider.Settings.SettingNotFoundException;
import android.widget.Toast;

public class HeartBeat extends Service {
	float curBrightnessValue;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		CommonResources.Initialize(this);
		try {
			  curBrightnessValue = android.provider.Settings.System.getInt(
					getContentResolver(),
					android.provider.Settings.System.SCREEN_BRIGHTNESS);
		} catch (SettingNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
		CommonResources.editor.putString("brightness", curBrightnessValue+"");
 
		return super.onStartCommand(intent, flags, startId);

	}

}
