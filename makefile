graal-native-image = ~/graalvm-ce-19.0.0/Contents/Home/bin/native-image

gradle-cache = $(HOME)/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/
kotlin-jars-classpath = $(gradle-cache)/kotlin-stdlib/1.3.31/11289d20fd95ae219333f3456072be9f081c30cc/kotlin-stdlib-1.3.31.jar:$(gradle-cache)/kotlin-stdlib-common/1.3.31/20c34a04ea25cb1ef0139598bd67c764562cb170/kotlin-stdlib-common-1.3.31.jar:$(gradle-cache)/kotlin-stdlib-jdk7/1.3.31/e652770b6416c6d85934086899ffed3eccd35813/kotlin-stdlib-jdk7-1.3.31.jar

ncurses-lib = /usr/lib/libncurses.dylib

create-binary:
	./gradlew compileKotlin compileJava && $(graal-native-image) -H:Class=snake.MainKt -H:Name=snake \
	-cp build/classes/kotlin/main:build/classes/java/main:$(kotlin-jars-classpath) -H:CLibraryPath=$(ncurses-lib)

valgrind-massif:
	valgrind --tool=massif --massif-out-file=massif.out --time-unit=B ./snake && ms_print massif.out > massif.out.printed
