## Pi4J MQ2 Gas Sensor Module Test

This Java application demonstrates how to interface with an MQ2 Gas Sensor module using the Pi4J library on a Raspberry Pi.

**Purpose:**

The `App.java` class provides a basic example of detecting gas using an MQ2 sensor module connected to a Raspberry Pi and Pi4J v2. It showcases how to initialize Pi4J, configure a digital input pin to read the digital output (DO) from the MQ2 sensor, and detect when gas concentration exceeds a threshold. This serves as a simple introduction for anyone wanting to use MQ2 sensors for basic gas leak detection or similar applications on a Raspberry Pi with Pi4J.

**Functionality:**

* **Initialization:** Sets up the Pi4J context (`Pi4J.newAutoContext()`), which is essential for interacting with the Raspberry Pi hardware using Pi4J.
* **GPIO Configuration:** Configures a single digital input pin (GPIO 17 in BCM numbering) to read the digital output (DO) from the MQ2 gas sensor. It uses the Pi4J `DigitalInput.Builder` for a clear and structured configuration, including optional pull-up resistor and debounce settings.
* **Gas Detection Logic:** Monitors the digital input state from the MQ2 sensor. The MQ2's digital output is typically LOW when gas is detected and HIGH when no gas is detected (or below the set threshold).
* **Console Output:** Prints "Gas detected!" to the console when the MQ2 sensor's digital output is LOW, and "No gas detected." when it is HIGH.
* **Continuous Monitoring:**  The program runs in an infinite loop (`while(true)`) to continuously monitor the gas sensor's digital output.
* **Shutdown:** Includes a `finally` block to properly shut down the Pi4J context (`pi4j.shutdown()`) when the program is terminated, releasing resources.

**Hardware Requirements:**

* **Raspberry Pi:** Any Raspberry Pi model supported by Pi4J.
* **MQ2 Gas Sensor Module:** A common and inexpensive gas sensor module sensitive to various combustible gases and smoke.
* **Wiring:** Jumper wires to connect the MQ2 module to the Raspberry Pi GPIO pins as follows:
    * **POWER:** 5V (Pin 2)
    * **DIGITAL OUTPUT (DO):** GPIO 17 (Pin 11)
    * **GROUND:** GND (Pin 6 or any other GND pin)

**Software Requirements:**

* **Java Development Kit (JDK):** Required to compile and run the Java code.
* **Pi4J Library:** This project is built using Pi4J v2. You'll need to include Pi4J as a dependency in your project (e.g., using Maven or Gradle).
* **Operating System:** Raspberry Pi OS (or any OS supported by Pi4J).

**How to Use:**

1. **Clone this repository.**
2. **Ensure you have Pi4J v2 set up in your project.** If you are using Maven, include the Pi4J core dependency in your `pom.xml`.
3. **Compile the `App.java` class.**
4. **Connect the MQ2 Gas Sensor module to your Raspberry Pi as described above.**
5. **Run the compiled `App` class on your Raspberry Pi.**

**Important Notes:**

* **GPIO Pin Numbering:** The code uses BCM (Broadcom) GPIO pin numbering. Make sure your wiring is consistent with this numbering scheme.
* **Digital Output (DO) Threshold:** The sensitivity of gas detection via the digital output (DO) is typically adjustable on the MQ2 module itself using a potentiometer. Adjust this to set the gas concentration threshold for detection.
* **Pull-up Resistor (Optional):** The code includes an optional pull-up resistor configuration. While some MQ2 modules may have internal pull-ups, explicitly defining one can ensure a reliable HIGH state when no gas is detected.
* **Debounce (Optional):** Debouncing is included as an option to filter out noise and rapid state changes on the digital output, improving stability.
* **Analog Output (AO) for Precision:** This example uses the digital output (DO) for simple gas detection. For more precise gas concentration measurements, you would need to use the MQ2's analog output (AO) along with an Analog-to-Digital Converter (ADC).
* **Warm-up Time:** MQ2 sensors require a warm-up period after power-up for stable readings. This example does not explicitly include a warm-up delay, but consider adding one in practical applications.
* **Permissions:** Ensure that the user running the Java application has the necessary permissions to access the Raspberry Pi's GPIO pins.

**Keywords:** Raspberry Pi, Pi4J, MQ2, Gas Sensor, Gas Detection, GPIO, Java, IoT, Hardware, Example, Tutorial, Digital Input, Digital Output, Sensor.