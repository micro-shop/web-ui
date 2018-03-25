package cz.microshop.webui.dao;

import cz.microshop.webui.model.User;
import org.springframework.data.repository.CrudRepository;


public interface UserDao extends CrudRepository<User, Long> {
	User findByUsername(String username);
	User findByEmail(String email);
	User findByPhone(String phone);
}
