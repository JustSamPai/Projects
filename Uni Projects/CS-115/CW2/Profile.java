import java.util.ArrayList;

/**
 * The type Profile.
 */
public class Profile {
    private String firstName;
    private String lastName;
    private int dayOfBirth;
    private int monthOfBirth;
    private int yearOfBirth;
    private String email;
    private String dateOfBirth;

    /**
     * The Interests.
     */
    ArrayList<String[]> Interests = new ArrayList<String[]>();
    /**
     * The Activities.
     */
    ArrayList<String[]> Activities = new ArrayList<String[]>();
    /**
     * The Friends.
     */
    ArrayList<Profile> Friends = new ArrayList<>();


    /**
     * Instantiates a new Profile.
     *
     * @param firstName    the first name
     * @param lastName     the last name
     * @param dayOfBirth   the day of birth
     * @param monthOfBirth the month of birth
     * @param yearOfBirth  the year of birth
     * @param email        the email
     * @param interest     the interest
     * @param activities   the activities
     */
    public Profile(String firstName, String lastName, int dayOfBirth, int monthOfBirth,
                   int yearOfBirth, String email, String[] interest, String[] activities) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dayOfBirth = dayOfBirth;
        this.monthOfBirth = monthOfBirth;
        this.yearOfBirth = yearOfBirth;
        this.email = email;
        Interests.add(interest);
        Activities.add(activities);
    }


    /**
     * Gets date of birth.
     *
     * @return the date of birth
     */
    public String getDateOfBirth() {
        dateOfBirth = dayOfBirth + "-" + monthOfBirth + "-" + yearOfBirth;
        return dateOfBirth;
    }

    /**
     * Insert friend.
     *
     * @param p the p
     */
    public void insertFriend(Profile p) {
        Friends.add(p);
    }

    /**
     * Gets friend.
     *
     * @param i the
     * @return the friend
     */
    public Profile getFriend(int i) {
        return Friends.get(i);
    }

    /**
     * Num of friends int.
     *
     * @return the int
     */
    public int numOfFriends() {
        return Friends.size();
    }

    public String toString() {
        return firstName + ", " + lastName + ", " + getDateOfBirth()  +
                ", " + email;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets day of birth.
     *
     * @return the day of birth
     */
    public int getDayOfBirth() {
        return dayOfBirth;
    }

    /**
     * Sets day of birth.
     *
     * @param dayOfBirth the day of birth
     */
    public void setDayOfBirth(int dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    /**
     * Gets month of birth.
     *
     * @return the month of birth
     */
    public int getMonthOfBirth() {
        return monthOfBirth;
    }

    /**
     * Sets month of birth.
     *
     * @param monthOfBirth the month of birth
     */
    public void setMonthOfBirth(int monthOfBirth) {
        this.monthOfBirth = monthOfBirth;
    }

    /**
     * Gets year of birth.
     *
     * @return the year of birth
     */
    public int getYearOfBirth() {
        return yearOfBirth;
    }

    /**
     * Sets year of birth.
     *
     * @param yearOfBirth the year of birth
     */
    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
