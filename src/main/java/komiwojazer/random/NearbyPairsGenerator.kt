package komiwojazer.random

import komiwojazer.secureRandom

class NearbyPairsGenerator : RandomPairGenerator {

    override fun getRandomPair(n: Int): Pair<Int, Int> {
        var random = 0
        while (random == 0) {
            random = secureRandom.nextInt(n)
        }
        return Pair(n - 1, n)
    }

}