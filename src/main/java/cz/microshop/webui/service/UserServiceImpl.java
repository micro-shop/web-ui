package cz.microshop.webui.service;

import cz.microshop.webui.model.PasswordResetToken;
import cz.microshop.webui.model.User;
import cz.microshop.webui.security.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRestService userRestService;

	public void save(User user2) {
        User u = new User();
        //u.setId(user2.getUserId());
        u.setUsername(user2.getUsername());
        u.setPassword(user2.getPassword());
        userRestService.save(u);
    }

	public void updateUserPassword(User user2) {
		String encryptedPassword = passwordEncoder.encode(user2.getPassword());
		user2.setPassword(encryptedPassword);
        userRestService.save(user2);
	}

    public User findByUsername(String username) {
        return userRestService.find(username);
    }

    public User findByEmail(String email) {
        return null;
        //return userRestService.findByEmail(email);
    }

    @Override
    public cz.microshop.webui.model.PasswordResetToken resetPassword(String email) {
        return null;
    }

    public User createUser(User user2, Set<UserRole> userRoles) {
        //User localUser = map(userRestService.find(user2.getUsername()));
        User localUser = userRestService.find(user2.getUsername());

        if (localUser != null) {
            LOG.info("User with username {} already exist. Nothing will be done. ", user2.getUsername());
        } else {
            String encryptedPassword = passwordEncoder.encode(user2.getPassword());
            user2.setPassword(encryptedPassword);

/*            for (UserRole ur : userRoles) {
                userDao.save(ur.getRole());
            }

            user2.getUserRoles().addAll(userRoles);*/

            //localUser = map(userRestService.save(map(user2)));
            user2.setUserRoles(userRoles);
            localUser = userRestService.save(user2);
        }

        return localUser;
    }

    //@Transactional
    public boolean checkUsernameExists(String username) {
        if (null != findByUsername(username)) {
            return true;
        }

        return false;
    }

    public boolean checkEmailExists(String email) {
        if (null != findByEmail(email)) {
            return true;
        }

        return false;
    }

    public User saveUser (User user2) {
        return userRestService.save(user2);
    }
    
    public List<User> findUserList() {
        return (List<User>) (userRestService.findAll());
    }

    public void enableUser (String username) {
        User user2 = findByUsername(username);
        user2.setEnabled(true);
        userRestService.save(user2);
    }

    public void disableUser (String username) {
        User user2 = findByUsername(username);
        user2.setEnabled(false);
        System.out.println(user2.isEnabled());
        userRestService.save(user2);
        System.out.println(username + " is disabled.");
    }

	@Override
	public boolean checkPhoneNumberExists(String phone) {
		if (null != findByPhone(phone)) {
			return true;
		}
		return false;
	}

    @Override
    public PasswordResetToken createPasswordResetTokenForUser(User user, String token) {
        return userRestService.resetPassword(user.getEmail());
    }

    public User findByPhone(String phone) {
        return null;
		//return userRestService.findByPhone(phone);
	}

	@Override	
	public void checkEqualityOfPasswords(User user2, List<String> errorMessages) {
		/*if(!checkPasswordsAreEqual(user2.getPassword(), user2.getPasswordConfirmation())) {
			errorMessages.add("Passwords are not equal.");
		}*/
	}

	private boolean checkPasswordsAreEqual(String password, String passwordConfirmation) {
		return password.equals(passwordConfirmation);
	}
}
