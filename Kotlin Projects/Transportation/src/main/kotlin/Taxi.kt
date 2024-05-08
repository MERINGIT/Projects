/**
 * This is the Taxi class, representing a type of transportation.
 * It inherits from the Transportation class and adds specific properties and methods for a taxi.
 *
 * @param name The name of the taxi.
 * @param ID The ID of the taxi.
 * @param _licensePlate The license plate of the taxi.
 * @param _driverName The name of the driver of the taxi.
 */
class Taxi(
    name: String,
    ID: Int,
    var _licensePlate: String,
    var _driverName: String
) : Transportation(name, ID) {//primary constructor

    /**
     * This is the constructor for the Taxi class.
     * It initializes the taxi with the provided name, ID, license plate, and driver name.
     * It also includes validation to ensure that the license plate and driver name are not empty strings.
     */
    init {
        require(_licensePlate != "") { "License number shouldn't be an empty string" }
        require(_driverName != "") { "Driver name shouldn't be an empty string" }
    }


    // Explicit getters and setters for licensePlate
    var licensePlate: String
        get() = _licensePlate
        set(value) {
            _licensePlate = value.trim()  // Trim leading and trailing spaces from the input license plate.
        }

    // Explicit getters and setters for driverName
    var driverName: String
        get() = _driverName
        set(value) {
            _driverName = value.trim()  // Trim leading and trailing spaces from the input driver name.
        }

    /**
     * This method overrides the typeOfTransportation method from the base class.
     * It prints out the type of transportation, specifically stating that it is a taxi.
     */
    override fun typeOfTransportation() {
        println("The type of transportation is Taxi")
    }

    /**
     * This method provides information about the taxi.
     * It prints out details such as the name, ID, license plate, and driver name.
     */
    fun taxiInfo() {
        println("Taxi with name $name with ID $ID has a license number: $_licensePlate and the driver" +
                " name is $_driverName")
    }
}
