package knapsack.java;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Knapsack {

    private int capacity = 50;
    private List<Item> items = new LinkedList<>();

    public Knapsack(int capacity) {
        this.capacity = capacity;
    }

    public int calculateValue() {
        return this.items.stream().mapToInt(Item::getValue).sum();
    }

    public int calculateWeight() {
        return this.items.stream().mapToInt(Item::getWeight).sum();
    }

    public double calculateValueDividedByWeight() {
        return calculateValue() / calculateWeight();
    }

    public boolean willExceedSize(Item item) {
        return this.calculateWeight() + item.getWeight() > capacity;
    }

    public void put(Item item) {
        items.add(item);
    }

    public boolean isFull() {
        return this.calculateWeight() == capacity;
    }

    public Knapsack copyWithItem(Item item) {
        Knapsack knapsack = new Knapsack(this.capacity);
        List<Item> newItems = this.items.stream()
                .map(it -> new Item(it.getValue(), it.getWeight()))
                .collect(Collectors.toList());
        newItems.forEach(knapsack::put);
        knapsack.put(item);
        return knapsack;
    }

    public void printSummary() {
        System.out.println("Knapsack with capacity " + capacity + " has items: ");
        this.items.forEach(System.out::println);
    }

    public boolean canBeAddedAnyOf(List<Item> items) {
        return items.stream().anyMatch(it -> !willExceedSize(it));
    }

    public List<Item> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Knapsack{" +
                "capacity=" + capacity +
                ", items=" + items +
                '}';
    }
}
