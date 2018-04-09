package komiwojazer

import spock.lang.Specification

class RouteMutatorKtTest extends Specification {

    def 'should swap elements in list'() {
        given:
        def list = [1, 2]

        when:
        list = RouteMutatorKt.swap(list, 0, 1)

        then:
        list[0] == 2
        list[1] == 1
    }

    def 'should swap two cities in route'() {
        given:
        DummyRandomPairGenerator generator = new DummyRandomPairGenerator(0, 1)
        def routeMutator = new RouteMutator(generator)
        def route = new Route([new City(0, 0, 0), new City(1, 0, 0)])

        when:
        route = routeMutator.swapTwoCities(route)

        then:
        route.cities.collect { it.id } == [1, 0]

    }

}
