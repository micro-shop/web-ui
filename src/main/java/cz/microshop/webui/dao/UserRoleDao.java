package cz.microshop.webui.dao;


import cz.microshop.webui.model.User;
import cz.microshop.webui.security.UserRole;
import org.springframework.data.repository.CrudRepository;

public interface UserRoleDao extends CrudRepository<UserRole, Long>{
	UserRole findByUser(User user);
}
