/**
 * This is the Train class, representing a type of transportation.
 * It inherits from the Transportation class and adds specific properties and methods for a train.
 *
 * @param name The name of the train.
 * @param ID The ID of the train.
 * @param _passengerCapacity The passenger capacity of the train.
 * @param _speed The speed of the train.
 * @param _route The route of the train.
 */
class Train(
    name: String,
    ID: Int,
    private var _passengerCapacity: Int,
    private var _speed: Double,
    private var _route: String
) : Transportation(name, ID) {//primary constructor

    /**
     * This is the constructor for the Train class.
     * It initializes the train with the provided name, ID, passenger capacity, speed, and route.
     * It includes validation to ensure that passenger capacity and speed are greater than 0.
     */
    init {
        require(_passengerCapacity > 0) { "Passenger capacity must be greater than 0" }
        require(_speed > 0.0) { "Speed must be greater than 0" }
    }

    // Explicit getters and setters for passengerCapacity
    var passengerCapacity: Int
        get() = _passengerCapacity
        set(value) {
            _passengerCapacity = if (value > 0) value else throw IllegalArgumentException("Passenger capacity must be greater than 0")
        }

    // Explicit getters and setters for speed
    var speed: Double
        get() = _speed
        set(value) {
            _speed = if (value > 0.0) value else throw IllegalArgumentException("Speed must be greater than 0")
        }

    // Explicit getters and setters for route
    var route: String
        get() = _route
        set(value) {
            _route = value.trim()  // Trim leading and trailing spaces from the input route.
        }



    /**
     * This method overrides the typeOfTransportation method from the base class.
     * It prints out the type of transportation, specifically stating that it is a train.
     */
    override fun typeOfTransportation() {
        println("The type of transportation is Train")
    }

    /**
     * This method provides information about the train.
     * It prints out details such as the name, ID, route, passenger capacity, and speed.
     */
    fun trainInfo() {
        println("Train with name $name and with ID $ID is starting from route $_route. It has a passenger" +
                " capacity of $_passengerCapacity and has a speed of $_speed.")
    }
    /**
     * Simulates a scenario where a train calls taxi services.
     * It prints information about the provided Taxi object.
     *
     * @param taxi The Taxi object to be simulated.
     */
    fun callTaxiServices(taxi: Taxi) {
        taxi.taxiInfo()
    }

    /**
     * Simulates a scenario where a train connects to bus services.
     * It prints information about the provided Bus object.
     *
     * @param bus The Bus object to be simulated.
     */
    fun connectToBusServices(bus: Bus) {
        bus.busInfo()
    }

}
