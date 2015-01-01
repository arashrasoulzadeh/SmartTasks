package arashrasoulzadeh.smarttasks;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;

public class MainActivity extends SherlockActivity {
	Intent heartbeatservice;
	ImageView icon;
	RelativeLayout back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(R.style.Theme_Sherlock_NoActionBar);
		setContentView(R.layout.activity_main);
		icon = (ImageView) findViewById(R.id.imageView1);
		back = (RelativeLayout) findViewById(R.id.relativelayout1);

		Animation popup = AnimationUtils.loadAnimation(this, R.anim.popupanim);

		icon.startAnimation(popup);
		AlphaAnimation animation1 = new AlphaAnimation(0.0f, 1.0f);
		animation1.setDuration(1000);
 		animation1.setFillAfter(true);
		back.startAnimation(animation1);

		
		
		
		heartbeatservice = new Intent(this, HeartBeat.class);
		this.startService(heartbeatservice);
		CommonResources.Initialize(this);

 	}
}
