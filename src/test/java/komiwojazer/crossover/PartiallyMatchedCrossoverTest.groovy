package komiwojazer.crossover

import komiwojazer.City
import komiwojazer.DummyRandomPairGenerator
import komiwojazer.Route
import spock.lang.Specification

class PartiallyMatchedCrossoverTest extends Specification {

    RouteCrossover routeCross = new PartiallyMatchedCrossover(new DummyRandomPairGenerator(3, 6))

    def 'should cross two routes'() {
        given:
        def first = buildRoute([1, 2, 3, 4, 5, 6, 7, 8, 9])
        def second = buildRoute([9,3,7,8,2,6,5,1,4])

        when:
        Route route = routeCross.cross(first, second)

        then:
        route.cities.collect { it.id } == [9, 3, 2, 4, 5, 6, 7, 1, 8]
    }

    def buildRoute(List<Integer> ids) {
        new Route(ids.collect { new City(it, 0, 0) })
    }

}
