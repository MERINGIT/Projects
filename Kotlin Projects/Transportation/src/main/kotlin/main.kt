/**
 * Main method: Starting point of the program.
 */
fun main() {

    // Welcome message
    println("*****************************************************")
    println("******* Welcome to the transportation system *********")
    println("*****************************************************")

    // Create instances of each transportation type
    val train = Train("Express", 1, 200, 120.0, "Halifax to Bedford") // Creating the train object
    val bus = Bus("City Bus", 2, 50, "A123") // Creating the bus object
    val taxi = Taxi("Yellow Cab", 3, "TX456", "John Doe") // Creating the taxi object

    // Display information about each transportation type
    train.trainInfo()
    bus.busInfo()
    taxi.taxiInfo()

    // Demonstrate the overridden typeOfTransportation method
    println("The Type of transportation of each object:")
    train.typeOfTransportation()
    bus.typeOfTransportation()
    taxi.typeOfTransportation()

    // Use explicit setters to update information

    // Updating Train information to show the functionality of setters
    train.passengerCapacity = 250 // Updating the passenger capacity
    train.speed = 150.0 // Updating the route
    train.route = "Toronto-Halifax" // Updating the route
    train.name = "Express" // Updating the name
    train.ID = 3 // Updating the ID

    // Updating Bus information to show the functionality of setters
    bus.passengerCapacity = 50 // Updating the passenger capacity
    bus.routeNumber = "xyza" // Updating the route
    bus.ID = 4 // Updating the ID
    bus.name = "Halifax Transit" // Updating the name

    // Updating Taxi information
    taxi.licensePlate = "HZ234" // Updating the license plate
    taxi.driverName = "Mary Das" // Updating the driver name
    taxi.name = "Halifax Local" // Updating the name
    taxi.ID = 3 // Updating the ID

    // Display updated information

    // Display updated Train information
    train.trainInfo()

    // Display updated Bus information
    bus.busInfo()

    // Display updated Taxi information
    taxi.taxiInfo()

    // for simulation
    val trainSimulation = Train("Halifax Express", 101, 200, 120.0, "Halifax to Dartmouth")
    val taxiSimulation = Taxi("Halifax Cab", 201, "ABC123", "Samuel John")
    val busSimulation = Bus("Halifax Bus", 301, 50, "Route 46")


    // Call simulation functions in Train class
    trainSimulation.callTaxiServices(taxiSimulation)
    trainSimulation.connectToBusServices(busSimulation)
    busSimulation.callTaxiServices(taxiSimulation)
}
