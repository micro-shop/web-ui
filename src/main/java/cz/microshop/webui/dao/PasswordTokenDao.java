package cz.microshop.webui.dao;

import cz.microshop.webui.security.PasswordResetToken;
import org.springframework.data.repository.CrudRepository;


public interface PasswordTokenDao extends CrudRepository<PasswordResetToken, Integer>{

	PasswordResetToken findByToken(String token);
}
