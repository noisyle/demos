#include "HelloWorld.h"
#include <iostream>
#include <jni.h>

using namespace std;

JNIEXPORT jstring JNICALL Java_com_noisyle_demo_jnidemo_HelloWorld_sayHello
  (JNIEnv *env, jobject, jstring s) {
    const char *str = env->GetStringUTFChars(s, NULL);
    cout << "jni: Hello, " << str << "!" << endl;
    env->ReleaseStringUTFChars(s, str);

    return env->NewStringUTF("Hello from C++");
}