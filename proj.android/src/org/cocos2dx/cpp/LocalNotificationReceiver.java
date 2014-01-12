package org.cocos2dx.cpp;

import com.MyCompany.AwesomeGame.R;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

public class LocalNotificationReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		int notificationId = intent.getIntExtra("notification_id", 0);
		String message = intent.getStringExtra("message");

		Intent intent2 = new Intent(context, Cocos2dxActivity.class);
		intent2.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent2,
				PendingIntent.FLAG_UPDATE_CURRENT);

		Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.icon);
		NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
		builder.setContentTitle(context.getString(R.string.app_name));
		builder.setContentText(message);
		builder.setSmallIcon(R.drawable.icon);
		builder.setLargeIcon(largeIcon);
		builder.setTicker(message);
		builder.setAutoCancel(true);
		builder.setDefaults(Notification.DEFAULT_ALL);
		builder.setContentIntent(pendingIntent);

		NotificationManager manager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		manager.notify(notificationId, builder.build());
	}

}
