package com.example.demo.controller;
import java.util.ArrayList;
import java.util.List;
import com.example.demo.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsersController {

    List<User> listUser = new ArrayList<User>();

    public UsersController() {
        User u1 = new User(1, "hong phuc", "phuc@donga.edu.vn");
        User u2 = new User(2, "Thu Thao", "Thao@donga.edu.vn");
        listUser.add(u1);
        listUser.add(u2);
    }

    @GetMapping("/users")
    @ResponseBody
    public List<User> getListUser() {
        return listUser;

    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
        for (User user : listUser) {
            if (user.getId() == id) {
                return ResponseEntity.status(200).body(user);
            }
        }

        return ResponseEntity.status(404).body(null);
    }

    @DeleteMapping("user/{id}")
    @ResponseBody
    public List<User> deleteUser(@PathVariable("id") int userId) {
        for (User user : listUser) {
            if (user.getId() == userId) {
                listUser.remove(user);
                break;
            }
        }
        return listUser;
    }
    @PostMapping("/user")
    public  ResponseEntity<User> createUsers(@RequestBody User newuser){
        listUser.add(newuser);
        return ResponseEntity.status(201).body(newuser);
    }
    @PutMapping("users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id")String userId, @RequestBody User updateUser){
        for (User user: listUser){
            if(user.getId() == updateUser.getId()) {
            user.setName(updateUser.getName());
            user.setEmail(updateUser.getEmail());
            return ResponseEntity.status(200).body(user);
        }
        }
        return ResponseEntity.status(404).body(null);
    }
}


