package travellingsalesman.algorithms.random

interface RandomPairGenerator {

    fun getRandomPair(n: Int): Pair<Int, Int>

}