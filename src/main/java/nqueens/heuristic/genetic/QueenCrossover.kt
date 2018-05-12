package nqueens.heuristic.genetic

import java.security.SecureRandom

class QueenCrossover {

    private val secureRandom = SecureRandom()

    fun makeCrossovers(population: List<IntArray>): List<IntArray> {
        var i = 0
        val offspringSize = population[0].size
        val populationSize = population.size
        while (i < populationSize) {
            val crossoverPos = secureRandom.nextInt(offspringSize)
            for (j in 0 until crossoverPos) {
                val tmp = population[i][j]
                population[i][j] = population[i + 1][j]
                population[i + 1][j] = tmp
            }
            i += 2
        }
        return population
    }


}