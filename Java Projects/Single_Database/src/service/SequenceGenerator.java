package service;

/**
 * The SequenceGenerator class is responsible for generating unique log sequence numbers.
 */
public class SequenceGenerator {
    /**
     * Generates a unique log sequence number.
     *
     * @return An integer representing a unique log sequence number.
     */
    public int generateLogSequenceNumber() {
        // Logic to generate a unique log sequence number
        // This can involve incrementing a counter or using a unique identifier
        // For simplicity, we'll return a random number between 1000 and 9999 in this example
        return (int) (Math.random() * 9000) + 1000;
    }
}
