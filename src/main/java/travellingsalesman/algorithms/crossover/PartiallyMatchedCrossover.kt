package travellingsalesman.algorithms.crossover

import travellingsalesman.City
import travellingsalesman.Route
import travellingsalesman.algorithms.random.RandomPairGenerator

class PartiallyMatchedCrossover(
        private val randomPairGenerator: RandomPairGenerator
) : RouteCrossover {

    override fun cross(first: Route, second: Route): Route {
        val (firstCities, secondCities) = getCities(first, second)
        val (startIndex, endIndex) = generateRandomPair(first)
        val resultCities = createArrayWithNulls(firstCities.size)
        return resultCities
                .addAllCitiesBetweenIndexes(firstCities, startIndex, endIndex)
                .putFirstCityOnPositionOfSecondCity(firstCities, secondCities, startIndex, endIndex)
                .appendNotPresentCities(secondCities)
                .createRoute()
    }

    private fun getCities(first: Route, second: Route): Pair<MutableList<City>, MutableList<City>> = Pair(first.cities, second.cities)

    private fun generateRandomPair(first: Route) = randomPairGenerator.getRandomPair(first.cities.size)

    private fun createArrayWithNulls(size: Int): Array<City?> = (0 until size).map { null }.toMutableList().toTypedArray()

    private fun Array<City?>.putFirstCityOnPositionOfSecondCity(firstCities: MutableList<City>, secondCities: MutableList<City>,
                                                                startIndex: Int, endIndex: Int
    ): Array<City?> {
        val pairsWithCityFromSecondWithCorrespondingCityFromFirst = findPairs(firstCities, secondCities, startIndex, endIndex)
        pairsWithCityFromSecondWithCorrespondingCityFromFirst.forEach { pair ->
            var pair = pair
            var secondIndex = secondCities.indexOf(pair.second)
            while (secondIndex in startIndex..endIndex) {
                val newCity = firstCities[secondIndex]
                val fromSecond = secondCities.indexOf(newCity)
                secondIndex = fromSecond
                pair = Pair(pair.first, secondCities[fromSecond])
            }
            this[secondIndex] = pair.first
        }
        return this
    }

    private fun findPairs(firstCities: MutableList<City>, secondCities: MutableList<City>, start: Int, end: Int): List<Pair<City, City>> {
        val secondNotInFirstAndBetweenIndexes = find(firstCities, start, end, secondCities)
        return secondNotInFirstAndBetweenIndexes.map { city -> Pair(city, firstCities.find { it == city }!!) }
    }

    private fun find(firstCities: MutableList<City>, start: Int, end: Int, secondCities: MutableList<City>): List<City> {
        val firstSection = firstCities.filterIndexed { index, _ -> index in start..end }
        val secondSection = secondCities.filterIndexed { index, _ -> index in start..end }
        return secondSection.filter { it !in firstSection }
    }

    private fun Array<City?>.appendNotPresentCities(cities: List<City>): Array<City?> {
        cities.forEachIndexed { index, city ->
            if (city !in this) {
                this[index] = city
            }
        }
        return this
    }

    private fun Array<City?>.addAllCitiesBetweenIndexes(firstCities: MutableList<City>, startIndex: Int, endIndex: Int): Array<City?> {
        (startIndex..endIndex).forEach {
            this[it] = firstCities[it]
        }
        return this
    }

    private fun Array<City?>.createRoute(): Route {
        val notNull = this.filterNotNull()
        return Route(notNull.toMutableList())
    }

}