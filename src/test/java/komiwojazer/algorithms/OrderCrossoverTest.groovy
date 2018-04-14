package komiwojazer.algorithms

import komiwojazer.City
import komiwojazer.Route
import komiwojazer.algorithms.crossover.OrderCrossover
import komiwojazer.algorithms.crossover.RouteCrossover
import spock.lang.Specification

class OrderCrossoverTest extends Specification {

    RouteCrossover routeCross = new OrderCrossover()

    def 'should cross two routes'() {
        given:
        def first = buildRoute([1, 2, 3, 4])
        def second = buildRoute([2, 1, 4, 3])

        when:
        Route route = routeCross.cross(first, second)

        then:
        route.cities.collect { it.id } == [1, 2, 4, 3]
    }

    def buildRoute(List<Integer> ids) {
        new Route(ids.collect { new City(it, 0, 0) })
    }

}
