package cz.microshop.webui.dao;

import cz.microshop.webui.model.User2;


public interface UserDao { //extends  CrudRepository<User2, Long> {
	User2 findByUsername(String username);
	User2 findByEmail(String email);
	User2 findByPhone(String phone);
}
