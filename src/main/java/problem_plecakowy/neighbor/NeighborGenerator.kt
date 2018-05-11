package problem_plecakowy.neighbor

import problem_plecakowy.Item
import problem_plecakowy.Knapsack

interface NeighborGenerator {

    fun generate(items: MutableList<Item>, knapsack: Knapsack): List<Knapsack>

}