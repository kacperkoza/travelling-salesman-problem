package komiwojazer

import komiwojazer.algorithms.mutator.TwoCityRouteMutator
import komiwojazer.algorithms.mutator.TwoCityRouteMutatorKt
import spock.lang.Specification

class TwoCityRouteMutatorKtTest extends Specification {

    def 'should swap elements in list'() {
        given:
        def list = [1, 2]

        when:
        list = TwoCityRouteMutatorKt.swap(list, 0, 1)

        then:
        list[0] == 2
        list[1] == 1
    }

    def 'should swap two cities in route'() {
        given:
        DummyRandomPairGenerator generator = new DummyRandomPairGenerator(0, 1)
        def routeMutator = new TwoCityRouteMutator(generator)
        def route = new Route([new City(0, 0, 0), new City(1, 0, 0)])

        when:
        route = routeMutator.mutate(route)

        then:
        route.cities.collect { it.id } == [1, 0]

    }

}
