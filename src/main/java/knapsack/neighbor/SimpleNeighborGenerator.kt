package knapsack.neighbor

import knapsack.Item
import knapsack.Knapsack

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