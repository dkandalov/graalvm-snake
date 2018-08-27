package snake

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import snake.Direction.*
import java.util.*

class SnakeTests {
    private val snake = Snake(
        cells = listOf(Cell(2, 0), Cell(1, 0), Cell(0, 0)),
        direction = right
    )

    @Test fun `snake moves right`() {
        assertThat(snake.move(), equalTo(Snake(
            cells = listOf(Cell(3, 0), Cell(2, 0), Cell(1, 0)),
            direction = right
        )))
    }

    @Test fun `snake changes direction`() {
        assertThat(snake.turn(down).move(), equalTo(Snake(
            cells = listOf(Cell(2, 1), Cell(2, 0), Cell(1, 0)),
            direction = down
        )))
        assertThat(snake.turn(left).move(), equalTo(Snake(
            cells = listOf(Cell(3, 0), Cell(2, 0), Cell(1, 0)),
            direction = right
        )))
    }

    @Test fun `snake eats an apple`() {
        val apples = Apples(20, 10, cells = listOf(Cell(2, 0)))
        val (newSnake, newApples) = snake.eat(apples)

        assertThat(newApples.cells, equalTo(emptyList()))
        assertThat(newSnake.eatenApples, equalTo(1))
        assertThat(newSnake.move().cells, equalTo(listOf(Cell(3, 0), Cell(2, 0), Cell(1, 0), Cell(0, 0))))
    }
}

class ApplesTests {
    @Test fun `apples grow at random locations`() {
        val apples = Apples(20, 10, random = Random(42))
        assertThat(
            apples.grow().grow().grow().cells,
            equalTo(listOf(Cell(x = 8, y = 4), Cell(x = 5, y = 5)))
        )
    }
}