gradle-cache = $(HOME)/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/

create-binary:
	./gradlew compileKotlin compileJava && ~/graalvm-ce-1.0.0-rc5/Contents/Home/bin/native-image  -H:Class=snake.MainKt -H:Name=snake -cp build/classes/kotlin/main:build/classes/java/main:$(gradle-cache)/kotlin-stdlib/1.2.61/5bc44acc4b3f0d19166ae3e50454b41e8ff29335/kotlin-stdlib-1.2.61.jar:$(gradle-cache)/kotlin-stdlib-common/1.2.61/772de03e12d932f489e41aef997d26c20a4ebee6/kotlin-stdlib-common-1.2.61.jar:$(gradle-cache)/kotlin-stdlib-jdk7/1.2.61/bc77c34ff80df88b4d9b0418ea4ae758544573f3/kotlin-stdlib-jdk7-1.2.61.jar -H:CLibraryPath=/usr/lib/libncurses.dylib

valgrind-massif:
	valgrind --tool=massif --massif-out-file=massif.out --time-unit=B ./snake && ms_print massif.out > massif.out.printed
