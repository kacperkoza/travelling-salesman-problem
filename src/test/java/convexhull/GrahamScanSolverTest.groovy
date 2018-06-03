package convexhull

import spock.lang.Specification
import java.awt.Point

class GrahamScanSolverTest extends Specification {

    GrahamScanSolver convexHullSolver = new GrahamScanSolver()

    def 'should create convex hull over the points'() {
        given:
        def leftBottom = new Point(0,0)
        def rightBottom = new Point(5,0)
        def leftTop = new Point(0,5)
        def rightTop = new Point(5,5)
        def points = generatePoints(leftBottom, rightBottom, rightTop, leftTop)

        when:
        List<Point> result = convexHullSolver.solve(points)

        then:
        result == [leftBottom, rightBottom, rightTop, leftTop ]
    }

    List<Point> generatePoints(leftBottom, rightBottom, leftTop, rightTop) {
        return [
                leftTop,                                                                                        rightTop,
                                        new Point(1, 4),                            new Point(4,4),
                                                        new Point(2,2), new Point(3,2),

                leftBottom,                                                                                     rightBottom,
        ]
    }
}
