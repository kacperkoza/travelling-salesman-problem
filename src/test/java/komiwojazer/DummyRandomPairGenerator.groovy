package komiwojazer

import komiwojazer.random.RandomPairGenerator
import kotlin.Pair

class DummyRandomPairGenerator implements RandomPairGenerator {

    private int firstElement
    private int secondElement


    DummyRandomPairGenerator(int firstElement, int secondElement) {
        this.firstElement = firstElement
        this.secondElement = secondElement
    }

    @Override
    Pair<Integer, Integer> getRandomPair(int n) {
        return new Pair(firstElement, secondElement)
    }

}
