package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UserDao {

    private static List<UserBean> users = new ArrayList<>();

    static {
        users.add(new UserBean(1, "Adam", new Date()));
        users.add(new UserBean(2, "Eve", new Date()));
        users.add(new UserBean(3, "Jack", new Date()));
    }

    public List<UserBean> findAll() {
        return users;
    }

    public UserBean save(UserBean user) {
        if (user.getId() == null) {
            int countUsers = users.size();
            user.setId(++countUsers);
        }
        users.add(user);
        return user;
    }

    public UserBean findOne(int id) {
        for (UserBean user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }
}
