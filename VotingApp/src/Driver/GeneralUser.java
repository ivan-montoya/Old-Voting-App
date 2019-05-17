package Driver;

public class GeneralUser
{
	protected String userName;
	protected String email;
	protected String password;
	protected String name;
	protected String birthday;
	
	public GeneralUser(String userName, String email, String password, String name, String birthday)
	{
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.name = name;
		this.birthday = birthday;
	}
	
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	
	public String getUser() {
		return this.userName;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setBirthday(String birthday)
	{
		this.birthday = birthday;
	}
	
	public String getBirthday() {
		return this.birthday;
	}
	
	public void registerAccount(String myUserName, String myEmail, String myPassword, String myName, String myBirthday)
	{
		setUserName(myUserName);
		setEmail(myEmail);
		setPassword(myPassword);
		setName(myName);
		setBirthday(myBirthday);
	}
}
