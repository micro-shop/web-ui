package cz.microshop.webui.dao;


import cz.microshop.webui.model.User2;
import cz.microshop.webui.security.UserRole;

public interface UserRoleDao { //extends CrudRepository<UserRole, Long>{
	UserRole findByUser(User2 user2);
}
