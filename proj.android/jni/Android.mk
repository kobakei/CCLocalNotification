LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE := cocos2dcpp_shared

LOCAL_MODULE_FILENAME := libcocos2dcpp

LOCAL_SRC_FILES := hellocpp/main.cpp \
                   ../../Classes/AppDelegate.cpp \
                   ../../Classes/HelloWorldScene.cpp \
                   ../../Classes/LocalNotification_Android.cpp

LOCAL_C_INCLUDES := $(LOCAL_PATH)/../../Classes
LOCAL_C_INCLUDES += $(LOCAL_PATH)/../../cocos2d
LOCAL_C_INCLUDES += $(LOCAL_PATH)/../../cocos2d/extensions

LOCAL_WHOLE_STATIC_LIBRARIES := cocos2dx_static
LOCAL_WHOLE_STATIC_LIBRARIES := cocos_extension_static
LOCAL_WHOLE_STATIC_LIBRARIES += cocosdenshion_static


include $(BUILD_SHARED_LIBRARY)

$(call import-module,2d)
$(call import-module,extensions)
$(call import-module,audio/android)
