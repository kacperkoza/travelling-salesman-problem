package travellingsalesman.algorithms

import travellingsalesman.City
import travellingsalesman.DummyRandomPairGenerator
import travellingsalesman.Route
import travellingsalesman.algorithms.crossover.PartiallyMatchedCrossover
import travellingsalesman.algorithms.crossover.RouteCrossover
import spock.lang.Specification
import spock.lang.Unroll

class PartiallyMatchedCrossoverTest extends Specification {

    @Unroll
    def 'should cross two routes'() {
        given:
        RouteCrossover routeCross = new PartiallyMatchedCrossover(new DummyRandomPairGenerator(firstIndex, secondIndex))
        def firstRoute = buildRoute(first)
        def secondRoute = buildRoute(second)

        when:
        Route route = routeCross.cross(firstRoute, secondRoute)

        then:
        route.cities.collect { it.id } == cross

        where:
        firstIndex <<   [3, 0, 1, 0]
        secondIndex <<  [6, 4, 4, 4]
        first << [  [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5], [8, 7, 6, 5, 4, 3, 2, 1], [1, 2, 3, 4, 5, 6, 7, 8] ]
        second << [ [9, 3, 7, 8, 2, 6, 5, 1, 4], [5, 1, 2, 4, 3], [4, 2, 6, 5, 3, 1, 8, 7], [7, 8, 1, 2, 3, 4, 6, 5] ]
        cross << [  [9, 3, 2, 4, 5, 6, 7, 1, 8], [1, 2, 3, 4, 5], [3, 7, 6, 5, 4, 1, 8, 2], [1, 2, 3, 4, 5, 8, 6, 7] ]

    }

    def buildRoute(List<Integer> ids) {
        new Route(ids.collect { new City(it, 0, 0) })
    }

}
