/**
 * This is the abstract base class for transportation entities.
 * It includes properties for the name and ID, along with explicit getters and setters.
 * The class also declares an abstract method typeOfTransportation() that needs to be implemented by subclasses.
 */
abstract class Transportation(var _name: String, var _ID: Int) {//primary constructor

    // Explicit getters and setters for name
    var name: String
        get() = _name
        set(value) {
            _name = value.trim()  // Trim leading and trailing spaces from the input name.
        }

    // Explicit getters and setters for ID
    var ID: Int
        get() = _ID
        set(value) {
            _ID = value  // Set the ID to the provided value.
        }

    /**
     * This is an abstract method that needs to be implemented by subclasses.
     * It represents the type of transportation and should be overridden to provide specific details.
     */
    abstract fun typeOfTransportation();


}
