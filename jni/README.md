### Generate .h file
javah -classpath ./target/classes -jni -o ./src/main/native/HelloWorld.h com.noisyle.demo.jnidemo.HelloWorld

### Compile (need c++ compiler, such as TDM-GCC)
g++ -shared -Wl,--kill-at, -v -o ./target/HelloWorld.dll ./src/main/native/HelloWorld.cpp
