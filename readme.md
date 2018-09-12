### What is this?
This is a minimal [snake game](https://en.wikipedia.org/wiki/Snake_(video_game_genre)) 
with [ncurses UI](https://en.wikipedia.org/wiki/Ncurses) written in Kotlin 
using [GraalVM](https://www.graalvm.org) for interop with C.
This is re-implementation of [Kotlin/Native snake](https://github.com/dkandalov/kotlin-native-snake)
to be able to compare Kotlin/Native and GraalVM C interop. 

### How to use?
 - [download](https://github.com/oracle/graal/releases) and unpack GraalVM
 - in the `makefile` update:
    - `graal-native-image` to specify path to `native-image` in GraalVM directory
    - `kotlin-jars-classpath` to specify path to Kotlin jars
    - `ncurses-lib` to specify path to ncurses binary
 - use `make` to compile project and generate binary via GraalVM
 - run `snake` from the project folder
