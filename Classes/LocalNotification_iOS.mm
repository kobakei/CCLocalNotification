#if (CC_TARGET_PLATFORM == CC_PLATFORM_IOS)

#include "LocalNotification.h"

using namespace std;

void LocalNotification::show(std::string message, int interval, int tag)
{
  // 通知を作成する
  UILocalNotification *notification = [[UILocalNotification alloc] init];
  
  notification.fireDate = [[NSDate date] dateByAddingTimeInterval:interval];
  notification.timeZone = [NSTimeZone defaultTimeZone];
  notification.alertBody = [NSString stringWithCString:message.c_str()
                                              encoding:[NSString defaultCStringEncoding]];
  notification.alertAction = @"Open";
  notification.soundName = UILocalNotificationDefaultSoundName;
  
  NSNumber* tag1 = [NSNumber numberWithInteger:tag];
  NSDictionary *infoDict = [NSDictionary dictionaryWithObject:tag1 forKey:@"ID"];
  notification.userInfo = infoDict;
  
  // 通知を登録する
  [[UIApplication sharedApplication] scheduleLocalNotification:notification];
  
  [notification release];
}

void LocalNotification::cancel(int tag)
{
  for(UILocalNotification *notification in [[UIApplication sharedApplication] scheduledLocalNotifications]) {
    if([[notification.userInfo objectForKey:@"ID"] integerValue] == tag) {
      [[UIApplication sharedApplication] cancelLocalNotification:notification];
    }
  }
}

#endif // CC_TARGET_PLATFORM == CC_PLATFORM_IOS