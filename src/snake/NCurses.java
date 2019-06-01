package snake;

import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.function.CFunction;
import org.graalvm.nativeimage.c.function.CLibrary;
import org.graalvm.nativeimage.c.type.CCharPointer;
import org.graalvm.nativeimage.c.type.CIntPointer;

import java.util.Collections;
import java.util.List;

@SuppressWarnings("unused")
@CContext(NCurses.Headers.class)
@CLibrary("ncurses")
public class NCurses {
    static class Headers implements CContext.Directives {
        @Override public List<String> getHeaderFiles() {
            return Collections.singletonList("\"/Library/Developer/CommandLineTools/SDKs/MacOSX.sdk/usr/include/ncurses.h\"");
        }
    }
    @CFunction public static native void initscr();
    @CFunction public static native int curs_set(int visibility);
    @CFunction public static native void noecho();
    @CFunction public static native void halfdelay(int tenths);
    @CFunction public static native int endwin();
    @CFunction public static native CIntPointer newwin(int nlines, int ncols, int begin_y, int begin_x);
    @CFunction public static native int delwin(CIntPointer win);
    @CFunction public static native int box(CIntPointer win, int verch, int horch);
    @CFunction public static native int mvprintw(int y, int x, CCharPointer message);
    @CFunction public static native int mvwprintw(CIntPointer win, int y, int x, CCharPointer message);
    @CFunction public static native int clear();
    @CFunction public static native int wclear(CIntPointer win);
    @CFunction public static native int wrefresh(CIntPointer win);
    @CFunction public static native int getch();
    @CFunction public static native int wgetch(CIntPointer win);
}
