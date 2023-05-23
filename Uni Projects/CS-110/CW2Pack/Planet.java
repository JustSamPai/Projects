public class Planet {
    private double mass;
    private double distance;
    private double radii;
    private String planetName;

    public double getMass() {
        return mass;
    }
    public void setMass(double planetMass) {
        mass = planetMass;
    }
    public double getDistance() {
        return distance;
    }
    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getRadii() {
        return radii;
    }
    public void setRadii(double radii) {
        this.radii = radii;
    }
    public void setPlanetName(String planets) {
        this.planetName = planets;
    }
    public String getPlanetName() {
        return planetName;
    }
    public static void main(String[]args){
    }
}
