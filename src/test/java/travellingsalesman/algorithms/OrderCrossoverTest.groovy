package travellingsalesman.algorithms

import travellingsalesman.City
import travellingsalesman.Route
import travellingsalesman.algorithms.crossover.OrderCrossover
import travellingsalesman.algorithms.crossover.RouteCrossover
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
