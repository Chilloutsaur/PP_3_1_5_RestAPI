package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private RoleServiceImpl roleServiceImpl;

    @PostMapping("/admin/newUser")
    public ResponseEntity<HttpStatus> addUser(@RequestBody User user) {
        userServiceImpl.saveUser(user);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping("admin/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User userById = userServiceImpl.findUserById(id);
        return ResponseEntity.ok(userById);
    }

    @GetMapping("admin/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userServiceImpl.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("admin/roles")
    public ResponseEntity<List<Role>> getRoles() {
        List<Role> roles = roleServiceImpl.getRolesList();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/principal")
    public ResponseEntity<User> getPrincipal() {
        User principal = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return ResponseEntity.ok(principal);
    }

    @GetMapping("admin/principal")
    public ResponseEntity<User> getPrincipalAdmin() {
        User principal = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return ResponseEntity.ok(principal);
    }

    @GetMapping("admin/usernames")
    public ResponseEntity<List<String>> usernamesList() {
        List<String> usernames = new ArrayList<>();
        for (User us : userServiceImpl.getAllUsers()) {
            usernames.add(us.getUsername());
        }
        return ResponseEntity.ok(usernames);
    }

    @PutMapping("admin/user/{id}")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody User user){
       userServiceImpl.saveUser(user);
       return ResponseEntity.ok(HttpStatus.OK);
    }


    @DeleteMapping("admin/user/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id) {
        userServiceImpl.deleteUser(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }










//    @PostMapping("/newUser")
//    public String addUser(Model model, @RequestBody @Valid User user, BindingResult bindingResult) {
//        model.addAttribute("users", userServiceImpl.getAllUsers());
//        List<Role> allRoles = roleServiceImpl.getRolesList();
//        model.addAttribute("allRoles", allRoles);
//        if (bindingResult.hasErrors()) {
//            return "admin/newUser";
//        } else {
//            userServiceImpl.saveUser(user);
//            return "redirect:/admin/";
//        }
//    }

    //    @GetMapping()
//    public String allUsers(Model model) {
//        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        model.addAttribute("principal", principal);
//        model.addAttribute("users", userServiceImpl.getAllUsers());
//        model.addAttribute("user", new User());
//        List<Role> allRoles = roleServiceImpl.getRolesList();
//        model.addAttribute("allRoles", allRoles);
//        return "admin/users";
//    }

    //    @GetMapping("/newUser")
//    public String newUser(Model model) {
//        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        model.addAttribute("principal", principal);
//        model.addAttribute("user", new User());
//        model.addAttribute("allRoles", roleServiceImpl.getRolesList());
//
//        return "admin/newUser";
//    }

    //    @GetMapping("/*/{id}")
//    @ResponseBody
//    public User getUserForModal(@PathVariable("id") Long id) {
//        return userServiceImpl.findUserById(id);
//    }




}



