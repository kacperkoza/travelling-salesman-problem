package convexhull

import java.awt.Point


import java.util.*
import kotlin.collections.ArrayList

open class GrahamScanSolver {

    private val alphaThenXComparator = compareBy<Point>({ getAlphaFor(it) }, { it.x })

    private val yThenXComparator = compareBy<Point>({ it.y }, { it.x })

    open fun solve(points: List<Point>): Stack<Point> {
        val sortedByAngleToXAxis = points
                .sortByYThenXAxis()
                .sortByAngleToXAxisExcludingFirstElement()
        val result = initStackWithThreeFirstItems(sortedByAngleToXAxis)

        for (i in 3 until sortedByAngleToXAxis.size) {
            while (isTurningRight(result, sortedByAngleToXAxis[i])) {
                result.pop()
            }
            result.push(sortedByAngleToXAxis[i])
        }
        return result
    }

    private fun List<Point>.sortByYThenXAxis(): List<Point> = this.sortedWith(yThenXComparator)

    private fun List<Point>.sortByAngleToXAxisExcludingFirstElement(): List<Point> {
        val withoutFirstPoint = this.subListWithoutFirst()
        val sortedAlphaXPoints = withoutFirstPoint.sortedWith(alphaThenXComparator)
        return listOf(this.first(), sortedAlphaXPoints)
    }

    private fun <T : Point> listOf(firstItem: T, remainingItems: List<T>): ArrayList<T> {
        val list = ArrayList<T>()
        list.add(firstItem)
        list.addAll(remainingItems)
        return list
    }

    private fun List<Point>.subListWithoutFirst(): List<Point> {
        return this.subList(1, this.size)
    }

    private fun <T : Point> initStackWithThreeFirstItems(points: List<T>): Stack<T> {
        val stack = Stack<T>()
        stack.addAll(listOf(points[0], points[1], points[3]))
        return stack
    }

    private fun isTurningRight(currentSolution: Stack<Point>, nextPoint: Point): Boolean {
        val (secondPoint, firstPoint) = popTwoElements(currentSolution)
        currentSolution.push(firstPoint, secondPoint)
        return determinantOf(firstPoint, secondPoint, nextPoint) <= 0
    }

    private fun popTwoElements(currentSolution: Stack<Point>): Pair<Point, Point> {
        return Pair(currentSolution.pop(), currentSolution.pop())
    }

    private fun Stack<Point>.push(vararg points: Point) {
        points.forEach {
            this.push(it)
        }
    }

    private fun determinantOf(one: Point, two: Point, three: Point): Int {
        return (two.x * three.y) +
                (one.x * two.y) +
                (three.x * one.y) -
                (one.x * three.y) -
                (two.x * one.y) -
                (three.x * two.y)
    }

    private fun getAlphaFor(point: Point): Double {
        val d = (Math.abs(point.x) + Math.abs(point.y)).toDouble()
        return if (point.x >= 0 && point.y >= 0) {
            point.y / d
        } else if (point.x < 0 && point.y >= 0) {
            2 - point.y / d
        } else if (point.x < 0) {
            2 + Math.abs(point.y) / d
        } else  {
            4 - Math.abs(point.y) / d
        }
    }

}
