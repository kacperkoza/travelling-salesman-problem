package knapsack

import java.util.*

data class Knapsack(
        private val capacity: Int = 50
) {
    val items: MutableList<Item> = LinkedList()

    fun calculateValue() = items.sumBy { it.value }

    fun calculateValueDividedByWeight() = calculateValue() / calculateWeight()

    fun calculateWeight() = items.sumBy { it.weight }

    fun willExceedSize(item: Item) = this.calculateWeight() + item.weight > capacity

    fun put(item: Item) = items.add(item)

    fun isFull() = this.calculateWeight() == capacity

    fun copyWithItem(item: Item): Knapsack {
        val knapsack = Knapsack(capacity)
        this.items.forEach { knapsack.put(it) }
        knapsack.put(item)
        return knapsack
    }

    fun printSummary() {
        println("Knapsack with capacity = $capacity has items:")
        val sorted = items.sortedByDescending { it.value }
        sorted.forEach {
            println("Item: v=${it.value} w=${it.weight}")
        }
    }

    fun canBeAddedAnyOf(items: MutableList<Item>): Boolean {
        return items.any { !willExceedSize(it) }
    }
}

data class Item(
        val value: Int,
        val weight: Int
)

