package nqueens.heuristic

import java.util.ArrayList

class HeuristicProvider {

    fun generatePopulation(n: Int, populationSize: Int): List<IntArray> {
        val population = ArrayList<IntArray>(populationSize)
        for (i in 0 until populationSize) {
            population.add(i, generateRandomStateChromosome(n))
        }
        return population
    }

    fun generateRandomStateChromosome(n: Int): IntArray {
        val array = IntArray(n)
        for (i in array.indices)
            array[i] = (Math.random() * array.size).toInt()
        return array
    }

    fun calculateHeuristicCost(array: IntArray): Int {
        var heuristicCost = 0
        for (i in array.indices)
            for (j in i + 1 until array.size)
                if (isSameRow(array, i, j) || isSameColumn(array, i, j)) {
                    heuristicCost += 1
                }
        return heuristicCost
    }

    private fun isSameColumn(array: IntArray, i: Int, j: Int) = Math.abs(array[i] - array[j]) == j - i

    private fun isSameRow(array: IntArray, i: Int, j: Int) = array[i] == array[j]

}