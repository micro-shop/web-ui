package cz.microshop.webui.service;

import cz.microshop.webui.model.User;
import cz.microshop.webui.security.PasswordResetToken;
import cz.microshop.webui.security.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
//@Transactional
public class UserServiceImpl implements UserService {

	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	
/*	@Autowired
	private UserDao userDao;
	
	@Autowired
    private RoleDao roleDao;*/

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

/*    @Autowired
	private PasswordTokenDao passwordTokenDao;*/

    @Autowired
    private UserRestService userRestService;
	
   // @Transactional
	public void save(User user2) {
        User u = new User();
        //u.setId(user2.getUserId());
        u.setUsername(user2.getUsername());
        u.setPassword(user2.getPassword());
        userRestService.save(u);
    }

    //@Transactional
	public void updateUserPassword(User user2) {
		String encryptedPassword = passwordEncoder.encode(user2.getPassword());
		user2.setPassword(encryptedPassword);
        userRestService.save(user2);
	}
	
    //@Transactional
    public User findByUsername(String username) {
        return userRestService.find(username);
    }

    //@Transactional
    public User findByEmail(String email) {
        return null;
        //return userRestService.findByEmail(email);
    }
        
   // @Transactional
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
    
    //@Transactional
    public boolean checkEmailExists(String email) {
        if (null != findByEmail(email)) {
            return true;
        }

        return false;
    }

    //@Transactional
    public User saveUser (User user2) {
        return userRestService.save(user2);
    }
    
    public List<User> findUserList() {
        return (List<User>) (userRestService.findAll());
    }

    //@Transactional
    public void enableUser (String username) {
        User user2 = findByUsername(username);
        user2.setEnabled(true);
        userRestService.save(user2);
    }

   // @Transactional
    public void disableUser (String username) {
        User user2 = findByUsername(username);
        user2.setEnabled(false);
        System.out.println(user2.isEnabled());
        userRestService.save(user2);
        System.out.println(username + " is disabled.");
    }

	@Override
	//@Transactional
	public boolean checkPhoneNumberExists(String phone) {
		if (null != findByPhone(phone)) {
			return true;
		}
		return false;
	}

	//@Transactional
	public User findByPhone(String phone) {
        return null;
		//return userRestService.findByPhone(phone);
	}
	
	//@Transactional
	public void createPasswordResetTokenForUser(User user2, String token) {
	    PasswordResetToken myToken = new PasswordResetToken(token, user2);
	    //passwordTokenDao.save(myToken);
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

    /*private User map(User user2) {
        if (user2 == null) {
            return null;
        }
        User user = new User();
        user.setUsername(user2.getUsername());
        user.setPassword(user2.getPassword());
        user.setEmail(user2.getEmail());
        return user;
    }

    private User map(User user) {
        if (user == null) {
            return null;
        }
        User user2 = new User();
        user2.setUsername(user.getUsername());
        user2.setPassword(user.getPassword());
        user2.setEmail(user.getEmail());
        return user2;
    }

    private List<User> map(List<User> user2) {
        List<User> users = new ArrayList<>();
        for (User user21 : user2) {
            users.add(map(user21));
        }
        return users;
    }

    private List<User> map_(List<User> users) {
        List<User> users2 = new ArrayList<>();
        for (User u : users) {
            users2.add(map(u));
        }
        return users2;
    }*/
}
