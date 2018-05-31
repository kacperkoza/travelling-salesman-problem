package knapsack.java;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class NeighborGenerator {

    List<Knapsack> generate(List<Item> items, Knapsack knapsack) {
        return items.stream()
                .map(it -> {
                    if (knapsack.willExceedSize(it))
                        return null;
                    else
                        return knapsack.copyWithItem(it);

                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

}
