package cz.microshop.webui.service;

import cz.microshop.webui.model.User;
import cz.microshop.webui.security.UserRole;

import java.util.List;
import java.util.Set;


public interface UserService {
	User findByUsername(String username);

    User findByEmail(String email);

    boolean checkUsernameExists(String username);

    boolean checkEmailExists(String email);
    
    void save (User User);
    
    User createUser(User User, Set<UserRole> userRoles);
    
    User saveUser (User User);
    
    List<User> findUserList();

    void enableUser (String username);

    void disableUser (String username);

	boolean checkPhoneNumberExists(String phone);

	void createPasswordResetTokenForUser(User User, String token);
	
	public void updateUserPassword(User User);

	void checkEqualityOfPasswords(User User, List<String> errorMessages);
}
