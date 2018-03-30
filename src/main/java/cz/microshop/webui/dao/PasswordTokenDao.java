package cz.microshop.webui.dao;

import cz.microshop.webui.security.PasswordResetToken;


public interface PasswordTokenDao  { //extends CrudRepository<PasswordResetToken, Integer>{

	PasswordResetToken findByToken(String token);
}
