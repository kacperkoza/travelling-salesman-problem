package komiwojazer.algorithms.random

import komiwojazer.secureRandom

class SecureRandomPairGenerator : RandomPairGenerator {

    override fun getRandomPair(n: Int): Pair<Int, Int> {
        val firstRandom = secureRandom.nextInt(n)
        var secondRandom = secureRandom.nextInt(n)
        while (firstRandom == secondRandom) {
            secondRandom = secureRandom.nextInt(n)
        }
        return Pair(firstRandom, secondRandom)
    }

}