package cz.microshop.webui.dao;

import cz.microshop.webui.security.Role;
import org.springframework.data.repository.CrudRepository;


public interface RoleDao extends CrudRepository<Role, Integer> {
    Role findByName(String name);
}
