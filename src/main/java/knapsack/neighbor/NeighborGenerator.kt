package knapsack.neighbor

import knapsack.Item
import knapsack.Knapsack

interface NeighborGenerator {

    fun generate(items: MutableList<Item>, knapsack: Knapsack): List<Knapsack>

}