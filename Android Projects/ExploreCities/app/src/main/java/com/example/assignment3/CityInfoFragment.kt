// CityInfoFragment.kt

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.assignment3.CityRepository
import com.example.assignment3.R

class CityInfoFragment : Fragment() {

    // Override onCreateView to inflate the fragment layout
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment and return the view
        return inflater.inflate(R.layout.fragment_city_info, container, false)
    }

    // Override onViewCreated to perform actions after the view has been created
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve data from arguments (city and continent names)
        val city = arguments?.getString(ARG_CITY) ?: "Unknown City"
        val continent = arguments?.getString(ARG_CONTINENT) ?: "Unknown Continent"

        // Get detailed city information from the CityRepository
        val cityInfo = CityRepository.getCityDetailsByName(city)

        // Set up views with city data
        val cityImageView: ImageView = view.findViewById(R.id.cityImageView)
        val populationTextView: TextView = view.findViewById(R.id.populationTextView)
        val languageTextView: TextView = view.findViewById(R.id.languageTextView)
        val basicInfoTextView: TextView = view.findViewById(R.id.additionalDataTextView)

        // Check if cityInfo is not null using let extension function
        cityInfo?.let {
            // Assume city has properties like imageResId, populationDetails, languageSpoken, and basicInfo
            // Set ImageView resource, and update TextViews with population, language, and basic information
            cityImageView.setImageResource(it.imageResId)
            populationTextView.text = "Population: ${it.populationDetails}"
            languageTextView.text = "Language: ${it.languageSpoken}"
            basicInfoTextView.text = "About: ${it.basicInfo}"
        }
    }

    // Companion object to provide a factory method for creating instances of CityInfoFragment
    companion object {
        const val ARG_CITY = "city"
        const val ARG_CONTINENT = "continent"

        // Factory method to create a new instance of CityInfoFragment with specified city and continent arguments
        fun newInstance(city: String, continent: String): CityInfoFragment {
            val fragment = CityInfoFragment()
            val args = Bundle()
            args.putString(ARG_CITY, city)
            args.putString(ARG_CONTINENT, continent)
            fragment.arguments = args
            return fragment
        }
    }
}
