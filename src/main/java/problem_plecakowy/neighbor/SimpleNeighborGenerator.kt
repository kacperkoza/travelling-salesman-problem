package problem_plecakowy.neighbor

import problem_plecakowy.Item
import problem_plecakowy.Knapsack

class SimpleNeighborGenerator : NeighborGenerator {

    override fun generate(items: MutableList<Item>, knapsack: Knapsack): List<Knapsack> {
        return items
                .map {
                    if (knapsack.willExceedSize(it))
                        null
                    else
                        knapsack.copyWithItem(it)
                }
                .filterNotNull()
    }

}