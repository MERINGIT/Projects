/**
 * This is the Bus class, representing a type of transportation.
 * It inherits from the Transportation class and adds specific properties and methods for a bus.
 *
 * @param name The name of the bus.
 * @param ID The ID of the bus.
 * @param _passengerCapacity The passenger capacity of the bus.
 * @param _routeNumber The route number of the bus.
 */
class Bus(
    name: String,
    ID: Int,
    var _passengerCapacity: Int,
    var _routeNumber: String
) : Transportation(name, ID) {//primary constructor

    /**
     * This is the constructor for the Bus class.
     * It initializes the bus with the provided name, ID, passenger capacity, and route number.
     * It also includes validation to ensure that the passenger capacity is greater than 0.
     */
    init {
        require(_passengerCapacity > 0) { "Passenger capacity must be greater than 0" }
    }

    // Explicit getters and setters for passengerCapacity
    var passengerCapacity: Int
        get() = _passengerCapacity
        set(value) {
            _passengerCapacity = if (value > 0) value else throw IllegalArgumentException("Passenger capacity must be greater than 0")
        }

    // Explicit getters and setters for routeNumber
    var routeNumber: String
        get() = _routeNumber
        set(value) {
            _routeNumber = value.trim()  // Trim leading and trailing spaces from the input route number.
        }



    /**
     * This method overrides the typeOfTransportation method from the base class.
     * It prints out the type of transportation, specifically stating that it is a bus.
     */
    override fun typeOfTransportation() {
        println("The type of transportation is Bus")
    }

    /**
     * This method provides information about the bus.
     * It prints out details such as the name, ID, route number, and passenger capacity.
     */
    fun busInfo() {
        println("Bus with name $name and with ID $ID is starting route $_routeNumber, with a passenger capacity" +
                " of $_passengerCapacity.")
    }
    /**
     * This method simulates a scenario where a train calls taxi services.
     * It prints information about the provided Taxi object.
     *
     * @param taxi The Taxi object to be simulated.
     */
    fun callTaxiServices(taxi: Taxi) {
        taxi.taxiInfo()
    }
}
