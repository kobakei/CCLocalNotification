CCLocalNotification
===================


## About

Local notification sample on cocos2d-x 3.x

## Files

### common
* Classes/LocalNotification.h

### iOS
* Classes/LocalNotification_iOS.mm

### Android
* Classes/LocalNotification_Android.cpp
* proj.android/src/org/cocos2dx/cpp/Cocos2dxActivity.java
* proj.android/src/org/cocos2dx/cpp/LocalNotificationReceiver.java
* proj.android/AndroidManifest.xml

and you need Android Support Library v4.

## Usage
```cpp
// Show notification after 15 seconds
LocalNotification::show("Hello world!", 15, 1);

// Cancel notification with tag 1
LocalNotification::cancel(1);
```

## License
This software is licensed under Apache License 2.0
