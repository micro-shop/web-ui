package cz.microshop.webui.dao;

import cz.microshop.webui.security.Role;


public interface RoleDao { //extends CrudRepository<Role, Integer> {
    Role findByName(String name);
}
