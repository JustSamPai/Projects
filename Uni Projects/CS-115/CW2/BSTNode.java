/**
 * The type Bst node.
 */
public class BSTNode
{
    private BSTNode l;
    private BSTNode r;
    /**
     * The Profile.
     */
    Profile profile;

    /**
     * Instantiates a new Bst node.
     *
     * @param profile the profile
     */
    public BSTNode(Profile profile)
    {
        this.profile =profile;
    }

    /**
     * Gets profile.
     *
     * @return the profile
     */
    public Profile getProfile()
    {
        String firstName = profile.getFirstName();
        String lastName = profile.getLastName();
        int dayOfBirth = profile.getDayOfBirth();
        int monthOfBirth = profile.getMonthOfBirth();
        int yearOfBirth = profile.getYearOfBirth();
        String email = profile.getEmail();
        Profile defaultProfile = new Profile(firstName, lastName, dayOfBirth,
                monthOfBirth, yearOfBirth, email, null, null);
        return defaultProfile;
    }


    /**
     * Sets left.
     *
     * @param l the l
     */
    public void setLeft (BSTNode l)
    {
        this.l = l;
    }

    /**
     * Sets right.
     *
     * @param r the r
     */
    public void setRight (BSTNode r)
    {
        this.r = r;
    }

    /**
     * Gets left.
     *
     * @return the left
     */
    public BSTNode getLeft ()
    {
        return l;
    }

    /**
     * Gets right.
     *
     * @return the right
     */
    public BSTNode getRight ()
    {
        return r;
    }

}