package komiwojazer.crossover

import komiwojazer.City
import komiwojazer.Route
import spock.lang.Specification

class SimpleRouteCrossoverTest extends Specification {

    RouteCrossover routeCross = new SimpleRouteCrossover()

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
