package Authentication;

/**
 * The UserDetails class represents user authentication details, including the username, password, and captcha.
 */
public class UserDetails {

    private String username;
    private String password;
    private String captcha;

    /**
     * Constructs a new UserDetails object with the provided username, password, and captcha.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @param captcha  The captcha associated with the user.
     */
    public UserDetails(String username, String password, String captcha) {
        this.username = username;
        this.password = password;
        this.captcha = captcha;
    }

    /**
     * Get the username of the user.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the username of the user.
     *
     * @param username The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the password of the user.
     *
     * @return The password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the password of the user.
     *
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get the captcha associated with the user.
     *
     * @return The captcha.
     */
    public String getCaptcha() {
        return captcha;
    }

    /**
     * Set the captcha associated with the user.
     *
     * @param captcha The captcha to set.
     */
    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    /**
     * Returns a string representation of the UserDetails object.
     *
     * @return A string containing the username, password, and captcha.
     */
    @Override
    public String toString() {
        return "UserDetails{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", captcha='" + captcha + '\'' +
                '}';
    }
}
