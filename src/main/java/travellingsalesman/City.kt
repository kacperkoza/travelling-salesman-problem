package travellingsalesman

import travellingsalesman.utils.calculateRouteLength
import java.security.SecureRandom


val secureRandom = SecureRandom()

data class City(
        val id: Int,
        val x: Int = secureRandom.nextInt(200),
        val y: Int = secureRandom.nextInt(200)
) {
    override fun toString(): String {
        return "$id"
    }
}


data class Route(
        val cities: MutableList<City>
) {
    override fun toString(): String {
        val s = StringBuilder()
        cities.forEachIndexed { index, city ->
            s.append(city)
            if (index != cities.size - 1) {
                s.append(" - ")
            }
        }
        return s.toString()

    }
}

fun MutableList<Route>.writeAsString() {
    this.forEachIndexed { index, route ->
        println("$index. $route [${calculateRouteLength(route)}]")
    }

}



