package komiwojazer.crossover

import komiwojazer.City
import komiwojazer.Route
import komiwojazer.random.RandomPairGenerator

class PartiallyMatchedCrossover(
        val randomPairGenerator: RandomPairGenerator
) : RouteCrossover {

    override fun cross(first: Route, second: Route): Route {
        val (firstCities, secondCities) = getCities(first, second)
        val (start, end) = randomPairGenerator.getRandomPair(first.cities.size)
        val list = mutableListOf(*secondCities.toTypedArray())
        (start..end).forEach {
            list[it] = firstCities[it]
        }

        val p = findPairs(firstCities, secondCities, start, end)
//        val pairs: List<Pair<Int, Int>> =
        p.forEach { pair -> {
            val secondIndex = pair.second
        } }

        //tu koda
        return Route(emptyList<City>().toMutableList())
    }

    private fun findPairs(firstCities: MutableList<City>, secondCities: MutableList<City>, start: Int, end: Int): List<Pair<City, City>> {
        val firstSection = firstCities.filterIndexed { index, _ -> index in start..end }
        val secondSection = secondCities.filterIndexed { index, _ -> index in start..end }
        val secondNotInCopied = secondSection.filter { it !in firstSection }
        return secondNotInCopied.map { city ->
            val cityIndex = secondCities.indexOf(city)
            val indexInFirst = firstCities[cityIndex]
            Pair(city, indexInFirst)
//            val indexInFirstCities = firstCities.indexOfFirst { it == city }
//            val indexInSecondCities = secondCities.indexOfFirst { it == city }
//            Pair(indexInSecondCities, indexInFirstCities)

        }

    }

    private fun getCities(first: Route, second: Route): Pair<MutableList<City>, MutableList<City>> = Pair(first.cities, second.cities)

}