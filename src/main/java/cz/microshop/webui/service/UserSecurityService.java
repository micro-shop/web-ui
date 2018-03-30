package cz.microshop.webui.service;

import cz.microshop.webui.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserSecurityService implements UserDetailsService {

    /** The application logger */
    private static final Logger LOG = LoggerFactory.getLogger(UserSecurityService.class);

/*    @Autowired
    private UserDao userDao;*/

    @Autowired
    private UserRestService userRestService;

    /*@Autowired
	private PasswordTokenDao passwordResetTokenDao;*/

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user2 = userRestService.find(username);
        if (null == user2) {
            LOG.warn("Username {} not found", username);
            throw new UsernameNotFoundException("Username " + username + " not found");
        }
        return user2;
    }
    
    @Transactional
    public String validatePasswordResetToken(long id, String token) {
  /*      PasswordResetToken passToken =
          passwordResetTokenDao.findByToken(token);
        if ((passToken == null) || (passToken.getUser2()
            .getUserId() != id)) {
            return "invalidToken";
        }
     
        Calendar cal = Calendar.getInstance();
        if ((passToken.getExpiryDate()
            .getTime() - cal.getTime()
            .getTime()) <= 0) {
        	passwordResetTokenDao.delete(passToken);
            return "expired";
        }
     
        User2 user2 = passToken.getUser2();
        Authentication auth = new UsernamePasswordAuthenticationToken(
                user2, null, Arrays.asList(
          new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE")));
        SecurityContextHolder.getContext().setAuthentication(auth);
        passwordResetTokenDao.delete(passToken);*/
        return null;
    }
}
