package com.example.assignment3

import android.os.Parcelable
import android.util.Log

// com.example.assignment3.City.kt
data class City(
    val name: String,
    val imageResId: Int,
    val continent: String,
    val languageSpoken: String,
    val populationDetails: String,
    val basicInfo: String
)
// com.example.assignment3.CityRepository.kt (assuming a repository to provide city data)
object CityRepository {
    private val allCities = listOf(
        City(
            "Agra",
            R.drawable.city1,
            "Asia",
            "Hindi",
            "1.58 million",
            "A city known for the Taj Mahal and historical significance."
        ),
        City(
            "Jerusalem",
            R.drawable.city2,
            "Asia",
            "Hebrew",
            "0.87 million",
            "A city with religious significance for Judaism, Christianity, and Islam."
        ),
        City(
            "Kathmandu",
            R.drawable.city3,
            "Asia",
            "Nepali",
            "1.4 million",
            "The capital and largest city of Nepal, surrounded by the Himalayas."
        ),
        City(
            "Dublin",
            R.drawable.city6,
            "Europe",
            "Irish",
            "1.9 million",
            "The capital and largest city of Ireland, known for its rich history and literature."
        ),
        City(
            "Athens",
            R.drawable.city7,
            "Europe",
            "Greek",
            "3.15 million",
            "The capital and largest city of Greece, considered the cradle of Western civilization."
        ),
        City(
            "Lisbon",
            R.drawable.city8,
            "Europe",
            "Portuguese",
            "0.55 million",
            "The capital and largest city of Portugal, known for its historic architecture and vibrant culture."
        ),
        City(
            "Atlanta",
            R.drawable.city10,
            "North America",
            "English",
            "0.5 million",
            "The capital and largest city of the state of Georgia, USA, a major cultural and economic hub."
        ),
        City(
            "Boston",
            R.drawable.city11,
            "North America",
            "English",
            "0.69 million",
            "The capital and largest city of the state of Massachusetts, USA, with a rich history and education institutions."
        ),
        City(
            "Philadelphia",
            R.drawable.city12,
            "North America",
            "English",
            "1.58 million",
            "A city with significant historical importance in the United States."
        ),
        City(
            "Brasilia",
            R.drawable.city14,
            "South America",
            "Portuguese",
            "3.1 million",
            "The capital and third-largest city of Brazil, known for modernist architecture."
        ),
        City(
            "Rio de Janeiro",
            R.drawable.city15,
            "South America",
            "Portuguese",
            "6.7 million",
            "A city famous for its carnival, samba, and iconic beaches."
        ),
        City(
            "Salvador",
            R.drawable.city16,
            "South America",
            "Portuguese",
            "2.9 million",
            "A city with a rich Afro-Brazilian cultural heritage."
        ),
        City(
            "Cairo",
            R.drawable.city19,
            "Africa",
            "Arabic",
            "9.5 million",
            "The capital and largest city of Egypt, known for its ancient history and landmarks."
        ),
        City(
            "Johannesburg",
            R.drawable.city20,
            "Africa",
            "English",
            "5.6 million",
            "The largest city in South Africa, an economic and cultural center."
        ),
        City(
            "Accra",
            R.drawable.city21,
            "Africa",
            "English",
            "2.3 million",
            "The capital and largest city of Ghana, known for its vibrant culture and history."
        ),
        City(
            "Canberra",
            R.drawable.city20,
            "Australia",
            "English",
            "0.43 million",
            "The capital city of Australia, known for its planned layout and national institutions."
        ),
        City(
            "Geelong",
            R.drawable.city21,
            "Australia",
            "English",
            "0.28 million",
            "A port city in Victoria, Australia, known for its waterfront and cultural events."
        ),
        City(
            "Newcastle",
            R.drawable.city22,
            "Australia",
            "English",
            "0.32 million",
            "A city in New South Wales, Australia, with a strong industrial history."
        )
    )

    fun getCitiesByContinent(continent: String): List<City> {
        return allCities
            .filter { it.continent == continent }
    }
    fun getCityDetailsByName(cityName: String): City? {
        Log.i("CityRepository", "Searching for city: $cityName")
        val city = allCities.find { it.name.equals(cityName, ignoreCase = true) }
        Log.i("CityRepository", "Found city: $city")
        return city
    }
}
