package travellingsalesman.utils

import travellingsalesman.City
import travellingsalesman.Route
import java.util.*

object RouteGenerator {

    private const val NUMBER_OF_CITIES = 10
    private const val NUMBER_OF_ROUTES = 10
    private val IDS = (0..10).toList()

    fun generateCitiesWithRandomCoordinates() = (1..NUMBER_OF_CITIES).map { City(IDS[it - 1]) }

    fun generateCitiesWithSpecifiedCoordinates() =
            listOf(
                    City(0, 0, 120),
                    City(1, 50, 35),
                    City(2, 100, 15),
                    City(3, 33, 178),
                    City(4, 173, 55),
                    City(5, 77, 200),
                    City(6, 77, 34),
                    City(7, 51, 122),
                    City(8, 97, 200),
                    City(9, 30, 192),
                    City(10, 112, 49),
                    City(11, 6, 88),
                    City(12, 28, 167),
                    City(13, 178, 93),
                    City(14, 192, 178),
                    City(15, 99, 199),
                    City(16, 3, 73),
                    City(17, 6, 90),
                    City(18, 50, 32),
                    City(19, 139, 150),
                    City(20, 190, 72),
                    City(21, 180, 13)
            )

    fun generateRoutes(): MutableList<Route> {
        val routes: MutableList<Route> = mutableListOf()
        val cities = generateCitiesWithSpecifiedCoordinates()
        (1..NUMBER_OF_ROUTES).forEach {
            Collections.shuffle(cities)
            val route = Route(cities.toMutableList())
            routes.add(route)
        }
        return routes
    }

}