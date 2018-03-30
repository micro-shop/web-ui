package cz.microshop.webui.security;

import cz.microshop.webui.model.User;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class PasswordResetToken {
  
    public PasswordResetToken(String token, User user2) {
    	this.token = token;
    	this.user2 = user2;
    	this.expiryDate = Date.from(Instant.now().plus(Duration.ofDays(1)));
	}

	private static final int EXPIRATION = 60 * 24;

    private Long id;

    private String token;

    private User user2;
    private Date expiryDate;

    public PasswordResetToken() {}
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user2;
	}

	public void setUser(User user2) {
		this.user2 = user2;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public static int getExpiration() {
		return EXPIRATION;
	}
}
