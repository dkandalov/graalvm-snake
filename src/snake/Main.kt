package snake

import org.graalvm.nativeimage.c.type.CTypeConversion.toCString
import snake.Direction.right
import snake.NCurses.*


fun main(args: Array<String>) {
    initscr()
    curs_set(0)
    noecho()

    var snake = Snake(
        cells = listOf(Cell(2, 0), Cell(1, 0), Cell(0, 0)),
        direction = right
    )

    val height = 10
    val width = 20
    val window = newwin(height, width, 0, 0)

    var c = 0
    while (c.toChar() != 'q') {
        wclear(window)
        box(window, 0, 0)
        toCString("ooooQ").use { mvwprintw(window, 3, snake.head.x, it.get()) }
        wrefresh(window)

        c = wgetch(window)

        snake = snake.move()
    }

    delwin(window)
    endwin()
}

data class Snake(val cells: List<Cell>, val direction: Direction) {
    val head = cells.first()

    fun move(): Snake {
        return copy(
            cells = listOf(head.move(direction)) + cells.dropLast(1)
        )
    }
}

data class Cell(val x: Int, val y: Int) {
    fun move(direction: Direction): Cell {
        return Cell(x + direction.dx, y + direction.dy)
    }
}

enum class Direction(val dx: Int, val dy: Int) {
    up(0, -1), down(0, 1), left(-1, 0), right(1, 0)
}