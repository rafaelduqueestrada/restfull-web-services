package com.in28minutes.rest.webservices.restfulwebservices.user;

import com.in28minutes.rest.webservices.restfulwebservices.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@RestController
public class UserRestController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/users")
    public List<UserBean> getAll() {
        return userDao.findAll();
    }

    @GetMapping("/users/{id}")
    public Resource<UserBean> get(@PathVariable int id) {
        UserBean userBean = userDao.findOne(id);
        if (userBean == null)
            throw new ResourceNotFoundException("Usuário não existe!");

        //"all-users", SERVER_PATH + "/users"
        //retrieveAllUsers
        //HATEOAS - Hypermedia as the Engine of Application State
        Resource<UserBean> resource = new Resource<>(userBean);
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAll());
        resource.add(linkTo.withRel("all-users"));

        return resource;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody UserBean user){
        UserBean savedUser = userDao.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
