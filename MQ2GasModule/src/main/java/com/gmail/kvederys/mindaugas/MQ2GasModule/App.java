package com.gmail.kvederys.mindaugas.MQ2GasModule;

import com.pi4j.Pi4J; // Import Pi4J library core functionality
import com.pi4j.context.Context; // Import Pi4J context for managing resources
import com.pi4j.io.gpio.digital.*; // Import Pi4J digital GPIO related classes

/**
 * <h2>MQ-2 Gas Sensor Module Test Application</h2>
 * <p>
 * This application demonstrates how to interface with an MQ-2 gas sensor module using the Pi4J library
 * on a Raspberry Pi or similar single-board computer.
 * The MQ-2 sensor is commonly used to detect gas leaks and measure the concentration of combustible gases
 * and smoke. This example focuses on the digital output (DO) of the MQ-2 sensor.
 * </p>
 * <p>
 * This program initializes the Pi4J context, configures a digital input pin for the MQ-2 sensor's digital output,
 * and then continuously monitors the sensor's state.
 * The MQ-2 sensor's digital output typically goes LOW when gas concentration exceeds a certain threshold,
 * and HIGH when gas concentration is below the threshold. This program prints "Gas detected!" to the console
 * when the digital output is LOW, and "No gas detected." otherwise.
 * </p>
 * <p>
 * <b>Hardware Setup (MQ-2 gas sensor module):</b>
 * <ul>
 *     <li><b>5V [Pin 2]</b>      >> POWER - Connect to 5V power supply on Raspberry Pi for MQ-2 module.</li>
 *     <li><b>GPIO17 [Pin 11]</b> >> DIGITAL OUTPUT (DO) - Connect the Digital Output (DO or DOUT) pin of MQ-2 to GPIO pin 17 (or any available GPIO) on Raspberry Pi as input.</li>
 *     <li><b>GND [Pin 6]</b>     >> GROUND - Connect to a common ground on Raspberry Pi for MQ-2 module.</li>
 * </ul>
 * </p>
 * <p>
 * <b>Important Notes:</b>
 * <ul>
 *     <li>Ensure you have correctly wired the MQ-2 gas sensor module to your Raspberry Pi
 *         according to the pinout described above.</li>
 *     <li>Using the proper GPIO pin and reliable power and ground connections is crucial for the application to function correctly.</li>
 *     <li>The sensitivity of the digital output (DO) on the MQ-2 module is typically adjustable via a potentiometer on the sensor board.
 *         You can adjust this potentiometer to set the threshold for gas detection.</li>
 *     <li>This example uses the digital output of the MQ-2 sensor. For more precise gas concentration measurements,
 *         the analog output (AO) of the MQ-2 sensor can be used with an Analog-to-Digital Converter (ADC).</li>
 *     <li>The MQ-2 sensor requires a warm-up period after power-up to stabilize its readings. This example does not explicitly include a warm-up period,
 *         but in real-world applications, it's advisable to include a warm-up time before relying on sensor readings.</li>
 * </ul>
 * </p>
 * <p>
 * <b>Software Dependencies:</b>
 * This application requires the Pi4J library. Ensure that Pi4J is properly installed and configured in your project.
 * Refer to the Pi4J official documentation for installation instructions and setup guidance.
 * </p>
 */
public class App {

    public static void main(String[] args) throws InterruptedException {

        // Initialize Pi4J context.
        // Pi4J.newAutoContext() automatically configures and initializes Pi4J for the detected platform.
    	 final Context pi4j = Pi4J.newAutoContext();

        try {
            // Configure GPIO pin for MQ2 DOUT (Digital Output) using Pi4J DigitalInput Builder.
            // Using BCM pin numbering - GPIO 17 for the digital input from the MQ2 sensor.
            DigitalInputConfig config = DigitalInput.newConfigBuilder(pi4j)
                    .address(17) // BCM pin number (GPIO 17).  Adjust if using a different GPIO pin.
                    .pull(PullResistance.PULL_UP) // Optional: Use pull-up resistor if needed.
                                                  // Some MQ2 modules might have an internal pull-up resistor, but explicitly setting it can ensure a defined state.
                    .debounce((long) 300) // Optional: Debounce to reduce noise and spurious readings (milliseconds).
                                         // Debouncing helps to filter out rapid state changes due to signal bouncing.
                    .name("MQ2 Sensor") // Set a descriptive name for the digital input for easier identification.
                    .build(); // Build the DigitalInputConfig object.

            DigitalInput mq2Sensor = pi4j.digitalInput().create(config); // Create a DigitalInput instance using the configured settings.

            System.out.println("MQ2 Gas Sensor Example using Pi4J"); // Print a message indicating the start of the MQ2 sensor example.

            while (true) { // Main loop to continuously monitor the MQ2 sensor state.
                // Read the digital input state from the MQ2 sensor.
                DigitalState state = mq2Sensor.state(); // Get the current DigitalState (HIGH or LOW) of the MQ2 sensor's digital output pin.

                // MQ2 Digital Output is typically LOW when gas is detected, and HIGH when no gas is detected (or gas concentration is below the threshold).
                if (state == DigitalState.LOW) {
                    System.out.println("Gas detected!"); // Print "Gas detected!" to the console if the sensor state is LOW, indicating gas detection.
                } else {
                    System.out.println("No gas detected."); // Print "No gas detected." if the sensor state is HIGH, indicating no gas detection (or below threshold).
                }

                // Wait for 1 second before the next reading to avoid rapid and potentially overwhelming output.
                Thread.sleep(1000); // Pause for 1000 milliseconds (1 second).
            }

        } catch (Exception e) { // Catch any exceptions that might occur during the program execution.
            e.printStackTrace(); // Print the stack trace of the exception for debugging purposes.
        } finally {
            // Properly shutdown Pi4J context to release resources when the program ends.
            pi4j.shutdown(); // Shutdown the Pi4J context, ensuring proper cleanup and resource release.
        }
    }
}