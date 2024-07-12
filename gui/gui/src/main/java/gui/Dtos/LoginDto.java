package gui.Dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class LoginDto {
	
	private String userName;
	
	private boolean authenticated;
	
	private LocalDateTime create;
	
	private LocalDateTime expiration;
	
	private String accessToken;
	
	private String refleshToken;
	
	
	public LoginDto() {

	}

	public LoginDto(LoginDto login) {
		
	}
	
	
	public String getUserName() {
		return userName;
	}
	public boolean isAuthenticated() {
		return authenticated;
	}
	public LocalDateTime getCreate() {
		return create;
	}
	public LocalDateTime getExpiration() {
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
	public void setCreate(LocalDateTime create) {
		this.create = create;
	}
	public void setExpiration(LocalDateTime expiration) {
		this.expiration = expiration;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public void setRefleshToken(String refleshToken) {
		this.refleshToken = refleshToken;
	}
}
