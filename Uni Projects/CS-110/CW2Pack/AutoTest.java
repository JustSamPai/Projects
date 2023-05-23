

/*This is the automatic test class for CS-110 coursework 2. The output of the student's program
 * under test should match the string TARGET_OUTPUT_SUN and TARGET_OUTPUT_TRAPPIST1
 */
public class AutoTest {

    static final String TARGET_OUTPUT_SUN = "Our System has luminosity: 1.0\n"
            + "Mercury has a mass of 0.06 Earths with a surface gravity of 0.416g, is 0.39AU from its star, and orbits in 0.244 years: could be habitable? no\n"
            + "Venus has a mass of 0.82 Earths with a surface gravity of 0.909g, is 0.72AU from its star, and orbits in 0.611 years: could be habitable? no\n"
            + "Earth has a mass of 1.0 Earths with a surface gravity of 1.0g, is 1.0AU from its star, and orbits in 1.0 years: could be habitable? yes\n"
            + "Mars has a mass of 0.1 Earths with a surface gravity of 0.37g, is 1.52AU from its star, and orbits in 1.874 years: could be habitable? no\n"
            + "Jupiter has a mass of 317.83 Earths with a surface gravity of 2.641g, is 5.2AU from its star, and orbits in 11.858 years: could be habitable? no\n"
            + "Saturn has a mass of 95.2 Earths with a surface gravity of 1.14g, is 9.58AU from its star, and orbits in 29.652 years: could be habitable? no\n"
            + "Uranus has a mass of 14.5 Earths with a surface gravity of 0.915g, is 19.2AU from its star, and orbits in 84.13 years: could be habitable? no\n"
            + "Neptune has a mass of 17.1 Earths with a surface gravity of 1.142g, is 30.05AU from its star, and orbits in 164.728 years: could be habitable? no\n";

    static final String TARGET_OUTPUT_TRAPPIST1 = "Trappist 1 has luminosity: 0.00128\n"
            + "Trappist1b has a mass of 1.02 Earths with a surface gravity of 0.813g, is 0.01AU from its star, and orbits in 0.001 years: could be habitable? no\n"
            + "Trappist1c has a mass of 1.16 Earths with a surface gravity of 0.959g, is 0.02AU from its star, and orbits in 0.003 years: could be habitable? no\n"
            + "Trappist1d has a mass of 0.3 Earths with a surface gravity of 0.493g, is 0.02AU from its star, and orbits in 0.003 years: could be habitable? no\n"
            + "Trappist1e has a mass of 0.77 Earths with a surface gravity of 0.93g, is 0.03AU from its star, and orbits in 0.005 years: could be habitable? yes\n"
            + "Trappist1f has a mass of 0.93 Earths with a surface gravity of 0.844g, is 0.04AU from its star, and orbits in 0.008 years: could be habitable? yes\n"
            + "Trappist1g has a mass of 1.15 Earths with a surface gravity of 0.87g, is 0.05AU from its star, and orbits in 0.011 years: could be habitable? yes\n"
            + "Trappist1h has a mass of 0.33 Earths with a surface gravity of 0.557g, is 0.06AU from its star, and orbits in 0.015 years: could be habitable? no\n";

    public static void main(String[] args) {

        //Create our solar system
        SolarSystem ourSystem = new SolarSystem("Our System", 1.0);

        //Add planets in our solar system
        ourSystem.addPlanet("Mercury", 0.06, 0.38, 0.39);
        ourSystem.addPlanet("Venus", 0.82, 0.95, 0.72);
        ourSystem.addPlanet("Earth", 1.0, 1.0, 1.0);
        ourSystem.addPlanet("Mars", 0.10, 0.52, 1.52);
        ourSystem.addPlanet("Jupiter", 317.83, 10.97, 5.20);
        ourSystem.addPlanet("Saturn", 95.2, 9.140, 9.58);
        ourSystem.addPlanet("Uranus", 14.5, 3.98, 19.20);
        ourSystem.addPlanet("Neptune", 17.1, 3.87, 30.05);

        //Check the output for our solar system
        if (ourSystem.toString().equals(TARGET_OUTPUT_SUN)) {
            System.out.println("Solar System: Pass!");
        } else {
            System.out.println("Solar System: Fail!\n*****");
            System.out.println("Expected output:\n");
            System.out.println(TARGET_OUTPUT_SUN);
            System.out.println("\n\nActual output:\n");
            System.out.println(ourSystem.toString());
        }

        System.out.println("\n");//blank line to separate output

        //Create the Trappist1 system - a much dimmer star with closer planets
        SolarSystem trappist1 = new SolarSystem("Trappist 1", 0.00128);

        //Add planets in Trappist 1 system
        trappist1.addPlanet("Trappist1b", 1.02, 1.12, 0.01);
        trappist1.addPlanet("Trappist1c", 1.16, 1.1, 0.02);
        trappist1.addPlanet("Trappist1d", 0.30, 0.78, 0.02);
        trappist1.addPlanet("Trappist1e", 0.77, 0.91, 0.03);
        trappist1.addPlanet("Trappist1f", 0.93, 1.05, 0.04);
        trappist1.addPlanet("Trappist1g", 1.15, 1.15, 0.05);
        trappist1.addPlanet("Trappist1h", 0.33, 0.77, 0.06);

        //Check the output for trappist1
        if (trappist1.toString().equals(TARGET_OUTPUT_TRAPPIST1)) {
            System.out.println("Trappist1: Pass!");
        } else {
            System.out.println("Trappist1: Fail!\n*****");
            System.out.println("Expected output:\n");
            System.out.println(TARGET_OUTPUT_TRAPPIST1);
            System.out.println("\n\nActual output:\n");
            System.out.println(trappist1.toString());
        }
    }
}
