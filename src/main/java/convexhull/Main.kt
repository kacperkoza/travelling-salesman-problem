package convexhull

import java.awt.Point
import java.security.SecureRandom


object Main {

    private val random = SecureRandom()
    private val solver = GrahamScanSolver()

    @JvmStatic
    fun main(args: Array<String>) {
        print("Points:")
        val points = createPoints(10)
        points.print()

        print("Result")
        val result = solver.solve(points)
        result.print()
    }

    private fun createPoints(numberOfPoints: Int): List<Point> =
            (0..numberOfPoints).map { Point(random.nextInt(10), random.nextInt(10) ) }

    private fun List<Point>.print() {
        this.forEach {
            print("[${it.x}, ${it.y}], ")
        }
        println()
        println()
    }
}