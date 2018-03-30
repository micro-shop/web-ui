package cz.microshop.webui.service;

import cz.microshop.webui.model.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by xnovm on 28.03.2018.
 */
@Service
public class UserRestService {

    RestTemplate restTemplate = new RestTemplate();

    public User save(User user)   {
        User user1 = restTemplate.postForObject("http://localhost:8092/user/save", user, User.class);
        return user1;
    }

    public void deleteAll()   {
        restTemplate.getForObject("http://localhost:8092/user/deleteAll", HttpStatus.class);
    }

    public List<User> findAll()  {
        ResponseEntity<List<User>> usersResponse =
                restTemplate.exchange("http://localhost:8092/user/findAll",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
                        });
        List<User> users = usersResponse.getBody();
        return users;
    }

    public User find(String username)   {
        return restTemplate.getForObject("http://localhost:8092/user/find?username="+username, User.class);
    }

    /*public ResponseEntity<ArrayList<User>> create(@RequestBody ArrayList<User> userList)   {
        return new ResponseEntity<ArrayList<User>>((ArrayList<User>) usersService.create(userList), HttpStatus.OK);

    }

    public ResponseEntity<HttpStatus> authenticateUser(@RequestBody User user) {
        if(usersService.authenticateCustomer(user))
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        else
            return new ResponseEntity<HttpStatus>(HttpStatus.UNAUTHORIZED);

    }

    public ResponseEntity<User> login(@Param("username") String username, @Param("password") String password) {
        User u = new User();
        u.setUsername(username);
        u.setPassword(password);
        if (usersService.authenticateCustomer(u)) {
            return new ResponseEntity<User>(usersService.findByUsername(username), HttpStatus.OK);
        } else {
            return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
        }
    }*/
}
