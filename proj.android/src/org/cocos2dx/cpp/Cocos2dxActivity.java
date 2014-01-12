package org.cocos2dx.cpp;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.NativeActivity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class Cocos2dxActivity extends NativeActivity{
	
	private static final String TAG = Cocos2dxActivity.class.getSimpleName();
	private static Activity sActivity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//For supports translucency
		
		//1.change "attribs" in cocos\2d\platform\android\nativeactivity.cpp
		/*const EGLint attribs[] = {
	            EGL_SURFACE_TYPE, EGL_WINDOW_BIT,
	            EGL_RENDERABLE_TYPE, EGL_OPENGL_ES2_BIT,  
	            //EGL_BLUE_SIZE, 5,   -->delete 
	            //EGL_GREEN_SIZE, 6,  -->delete 
	            //EGL_RED_SIZE, 5,    -->delete 
	            EGL_BUFFER_SIZE, 32,  //-->new field
	            EGL_DEPTH_SIZE, 16,
	            EGL_STENCIL_SIZE, 8,
	            EGL_NONE
	    };*/
		
		//2.Set the format of window
		// getWindow().setFormat(PixelFormat.TRANSLUCENT);
		
		// LocalNotification
		sActivity = this;
	}
	
	public static void showLocalNotification(String message, int interval, int tag) {
		Log.v(TAG, "showLocalNotification");
		PendingIntent sender = getPendingIntent(message, tag);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		calendar.add(Calendar.SECOND, interval);
		
		AlarmManager am = (AlarmManager)sActivity.getSystemService(ALARM_SERVICE);
		am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
	}
	
	public static void cancelLocalNotification(int tag) {
		Log.v(TAG, "cancelLocalNotification");
		PendingIntent sender = getPendingIntent(null, tag);
		AlarmManager am = (AlarmManager)sActivity.getSystemService(ALARM_SERVICE);
		am.cancel(sender);
	}
	
	private static PendingIntent getPendingIntent(String message, int tag) {
		Intent i = new Intent(sActivity.getApplicationContext(), LocalNotificationReceiver.class);
		i.putExtra("notification_id", tag);
		i.putExtra("message", message);
		PendingIntent sender = PendingIntent.getBroadcast(sActivity, 0, i, 0);
		return sender;
	}
}
