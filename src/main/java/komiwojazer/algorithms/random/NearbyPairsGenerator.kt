package komiwojazer.algorithms.random

import komiwojazer.secureRandom

class NearbyPairsGenerator : RandomPairGenerator {

    override fun getRandomPair(n: Int): Pair<Int, Int> {
        val random = secureRandom.nextInt(n)
        return if (random == 0) Pair(0, 1) else Pair(n - 1, n)
    }

}