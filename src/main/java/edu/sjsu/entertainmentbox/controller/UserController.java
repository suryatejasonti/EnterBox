package edu.sjsu.entertainmentbox.controller;

import edu.sjsu.entertainmentbox.component.LoginComponent;
import edu.sjsu.entertainmentbox.model.Customer;
import edu.sjsu.entertainmentbox.model.User;
import edu.sjsu.entertainmentbox.service.CustomerServiceImpl;
import edu.sjsu.entertainmentbox.service.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpSession;
import java.net.URI;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    public UserService userService;
    @Autowired
    CustomerServiceImpl customerService;


    @PostMapping(path="/login",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody String user, HttpSession session) throws JSONException
    {
        JSONObject jsonObject = new JSONObject(user);
       // session.setAttribute("name",jsonObject.getString("username"));
        LoginComponent loginComponent = userService.login(jsonObject.getString("username"),jsonObject.getString("password"));
        session.setAttribute("username", loginComponent.getUser().getEmailAddress());
        session.setAttribute("customerId", loginComponent.getCustomerId());
        session.setAttribute("userType", loginComponent.getUser().getUserType());
        return new ResponseEntity(loginComponent,HttpStatus.OK);
    }

    @PostMapping(value = "/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> logout(HttpSession session) throws JSONException{
        System.out.println(session.getAttribute("name"));
        session.invalidate();
        return  new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("users/{username}")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<User> getUser(@PathVariable String username) throws Exception{
        User user = userService.getUser(username);
        return new ResponseEntity<User>(user, HttpStatus.FOUND);
    }

    @DeleteMapping("/users/{username}")
    public void deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
    }

    @PostMapping(path = "/registerUser")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> createUser(@RequestBody User user, HttpSession session) {
        User savedUser = userService.addUser(user);

      Customer customer =  customerService.createCustomer(user.getEmailAddress());
      session.setAttribute("username", user.getEmailAddress());
        session.setAttribute("customerId", customer.getCustomerId());
        session.setAttribute("userType", user.getUserType());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}")
                .buildAndExpand(savedUser.getEmailAddress()).toUri();

        System.out.println(location);

        ResponseEntity.created(location).build();

        return new ResponseEntity<String>("SUCCESS", HttpStatus.FOUND);

    }

}
