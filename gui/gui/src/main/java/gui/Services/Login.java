package gui.Services;

public class Login {
	
	private String userName;
	
	private boolean authenticated;
	
	private String create;
	
	private String expiration;
	
	private String accessToken;
	
	private String refleshToken;
	
	public String getUserName() {
		return userName;
	}
	public boolean isAuthenticated() {
		return authenticated;
	}
	public String getCreate() {
		return create;
	}
	public String getExpiration() {
		return expiration;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public String getRefleshToken() {
		return refleshToken;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}
	public void setCreate(String create) {
		this.create = create;
	}
	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public void setRefleshToken(String refleshToken) {
		this.refleshToken = refleshToken;
	}
	
	
	
	

}
