CLASS_PATH?=./build/classes/java/main
JAVA_LIBRARY_PATH?=D:\project\open-source\jvmti-tools\cmake-build-debug\install\bin
test-app:
	java -verbose:jni -Dfile.encoding=UTF-8 \
		-Djava.library.path="$(JAVA_LIBRARY_PATH)" \
		-agentpath:"$(JAVA_LIBRARY_PATH)\agent.dll" \
		-cp $(CLASS_PATH) TestApp
javah:
	@javah -cp $(CLASS_PATH) DataGuard
test:
	java -Djava.library.path=D:\project\open-source\jvmti-tools\cmake-build-debug\install\bin -agentpath:D:\project\open-source\jvmti-tools\cmake-build-debug\install\bin\agent.dll -Dfile.encoding=UTF-8 -Duser.country=CN -Duser.language=zh -Duser.variant -cp D:\project\open-source\jvmti-test\build\classes\java\main TestApp