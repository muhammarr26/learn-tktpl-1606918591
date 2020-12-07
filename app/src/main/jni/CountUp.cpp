#include <string.h>
#include <jni.h>

extern "C" JNIEXPORT jint JNICALL
Java_id_ac_ui_cs_muhammarr_testapp_MainActivity_countUp(
        JNIEnv* env,
        jobject /* this */,
        jint counter) {

    return counter+1;
}


