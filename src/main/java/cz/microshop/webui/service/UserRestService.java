package cz.microshop.webui.service;

import cz.microshop.webui.model.PasswordResetToken;
import cz.microshop.webui.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xnovm on 28.03.2018.
 */
@Service
public class UserRestService extends RestService {

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
        return getRestTemplate().getForObject(url+"/find?username="+username, User.class);
    }

    public PasswordResetToken resetPassword(String email)   {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        Map headersMap = new HashMap<String, String>();
        headersMap.put("Content-Type", "application/json");

        headers.setAll(headersMap);
        MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap<String, String>();
        bodyMap.add("email", email);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(bodyMap, headers);
        return getRestTemplate().postForObject(url+"/createPasswordResetToken", request, PasswordResetToken.class);
    }
}
