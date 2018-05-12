package travellingsalesman

import spock.lang.Specification
import spock.lang.Unroll

import static travellingsalesman.utils.TravelUtilsKt.calculateRouteLength
import static travellingsalesman.utils.TravelUtilsKt.distanceBetweenCities

class TravelUtilsKtTest extends Specification {

    @Unroll
    def 'should calculate route length between two cities'() {
        given:
        def start = new City(0, x1, y1)
        def end = new City(0, x2, y2)

        expect:
        distanceBetweenCities(start, end) == distance

        where:
        x1 | y1 | x2 | y2 || distance
        0  | 0  | 3  | 4  || 5.0d
        0  | 0  | 5  | 5  || 7.0710678118654755d
        0  | 0  | 0  | 10 || 10.0d
        0  | 0  | 11 | 0  || 11.0d
        1  | 1  | 5  | 5  || 5.656854249492381
    }

    @Unroll
    def 'should calculate route length'() {
        given:
        def first = new City(0, x1, y1)
        def second = new City(0, x2, y2)
        def third = new City(0, x3, y3)
        def fourth = new City(0, x4, y4)
        def route = new Route([first, second, third, fourth])

        expect:
        calculateRouteLength(route) == routeLength

        where:
        x1 | y1 | x2 | y2 | x3 | y3 | x4 | y4  || routeLength
        0  | 0  | 3  | 4  | 10 | 4  | 10 | 7   || 15
        1  | 1  | 5  | 5  | 0  | 0  | 0  | 100 || 112.72792206135786
    }


}
