package server;

/**
 * Breaks a cookie down into its separate elements
 * @author Kevin
 *
 */
public interface CookieParserInterface {
	
	/**
	 * Breaks a cookie down into its separate elements
	 * @param cookie The encoded cookie to be parsed
	 */
	public void parseCookie(String cookie);
	
	/**
	 * @return The username provided in the given cookie. Returns null if no cookie has been parsed yet, or a username was not included
	 */
	public String getUserName();
	
	/**
	 * @return The password provided in the given cookie. Returns null if no cookie has been parsed, or a password was not included
	 */
	public String getPassword();
	
	/**
	 * @return The game ID provided in the given cookie. Returns -1 If no ID is provided
	 */
	public int getGameID();
}
