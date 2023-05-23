// my biggest issue with my previous code was how poor it was presented with wrong loops and messy code,
// in the current coursework I used methods as well as classes to make my code easier to read
//additionally to this adding new methods prevents me from repeating my code so I don't have repetitive code
import java.util.ArrayList;
public class SolarSystem {
    // initializes all variables
    private final double luminosity;
    private final String solarName;
    private String isHabitable = "";
    private final double roundTo3DP = 1000.0;
    // these are constants to remove magic numbers
    private static final double LOWER_HABITABLE_LUMINOSITY = 0.75;
    private static final double LOWER_PLANET_DISTANCE = 0.6;
    private static final double UPPER_PLANET_DISTANCE = 7;
    //stores planets and other such assigned values
    private final ArrayList<Planet> planetList = new ArrayList<>();
    // sets from AutoTest.java system name and luminosity as variables for later use
    public SolarSystem(String ourSystem, double luminosity) {
        this.solarName = ourSystem;
        this.luminosity = luminosity;
    }

    // sets variables given from AutoTest.java then adds them to an object
    public void addPlanet(String planetName, double mass, double radii, double distance) {
        Planet pl = new Planet();
        pl.setPlanetName(planetName);
        pl.setMass(mass);
        pl.setRadii(radii);
        pl.setDistance(distance);
        planetList.add(pl);
    }

    public String toString() {
        // creates an object
        StringBuilder planetResult = new StringBuilder();
        // allows me to print title of solar system without having it print on the wrong line by saving it as a variable
        String solarSystemTitle = solarName + " has luminosity: " + luminosity;
        // loops so that all planets get their calculated period gravity and calculated
        for (Planet planet : planetList) {
            double period = Math.sqrt(planet.getDistance() * planet.getDistance() * planet.getDistance());
            double roundPeriod = Math.round(period * roundTo3DP) / roundTo3DP;
            double surfaceGravity = (planet.getMass() / (planet.getRadii() * planet.getRadii()));
            double roundSurfaceGravity = Math.round(surfaceGravity * roundTo3DP) / roundTo3DP;
            double luminosityFactor = Math.sqrt(luminosity);
            // checks if planet is habitable
            if ((((LOWER_HABITABLE_LUMINOSITY * luminosityFactor) <= planet.getDistance())
                    && (planet.getDistance() <= (2.0 * luminosityFactor)))
                    && ((LOWER_PLANET_DISTANCE <= planet.getMass()) && planet.getMass() <= UPPER_PLANET_DISTANCE)){
                isHabitable = "yes";
            } else {
                isHabitable = "no";
            }

            // outputs details of planet such as name orbit ect in the return statement
            planetResult.append(planet.getPlanetName()).
                    append(" has a mass of ").append(planet.getMass()).append(
                    " Earths with a surface gravity of ").append(roundSurfaceGravity).append(
                    "g, is ").append(planet.getDistance()).append(
                    "AU from its star, and orbits in ").append(roundPeriod).append(
                    " years: could be habitable? ").append(isHabitable).append("\n");
            //outputs the two variables to show all relevant information in correct format
        }return solarSystemTitle +"\n" + planetResult.toString();
    }
}
