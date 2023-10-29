package racingcar

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RacingGameRecorderTest {
    @Test
    fun `자동차의 위치를 기록할 수 있다`() {
        val recorder = RacingGameRecorder()
        val car = Car()
        val cars = listOf(car)
        val carPositions = ArrayList<Int>()

        recorder.recordRound(cars)
        carPositions.add(car.position)

        car.tryMove(OnlyTrueMoveCondition())

        recorder.recordRound(cars)
        carPositions.add(car.position)

        val actual = recorder.gameResultRecord.raceResults.map { it.positions.first() }

        assertThat(actual).isEqualTo(carPositions)
    }

    @Test
    fun `여러대의 자동차를 기록할 수 있다`() {
        val recorder = RacingGameRecorder()

        val cars = listOf(Car(), Car())

        recorder.recordRound(cars)

        val recordedCarSize = recorder.gameResultRecord.raceResults.first().positions.size

        assertThat(recordedCarSize).isEqualTo(cars.size)
    }
}