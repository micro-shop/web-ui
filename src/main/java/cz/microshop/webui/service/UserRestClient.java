package cz.microshop.webui.service;

import cz.microshop.webui.model.PasswordResetToken;
import cz.microshop.webui.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xnovm on 28.03.2018.
 */
@Service
public class UserRestClient extends RestClient {

    @Value("${url.restserviceurl.users}")
    private String url;

    public User save(User user)   {
        User user1 = getRestTemplate().postForObject(url+"/save", user, User.class);
        return user1;
    }

    public void deleteAll()   {
        getRestTemplate().getForObject(url+"/deleteAll", HttpStatus.class);
    }

    public List<User> findAll()  {
        ResponseEntity<List<User>> usersResponse =
                getRestTemplate().exchange(url+"/findAll",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
                        });
        List<User> users = usersResponse.getBody();
        return users;
    }

    public User find(String username)   {
        return getRestTemplate().getForObject(url+"/findByUsername?username="+username, User.class);
    }

    public User find(Long id)   {
        return getRestTemplate().getForObject(url+"/find?id="+id, User.class);
    }

    public PasswordResetToken resetPassword(String email)   {
        return getRestTemplate().getForObject(url+"/createPasswordResetToken?email="+email, PasswordResetToken.class);
    }

    public User findByEmail(String email) {
        return getRestTemplate().getForObject(url+"/findByEmail?email="+email, User.class);
    }

    public Boolean validatePasswordResetToken(long id, String token) {
        return getRestTemplate().getForObject(url+ "/validatePasswordResetToken?userId="+id+"&token="+token, Boolean.class);
    }
}
