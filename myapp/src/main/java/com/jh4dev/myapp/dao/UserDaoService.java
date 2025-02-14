package com.jh4dev.myapp.dao;

import com.jh4dev.myapp.bean.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoService {

    private static List<User> userList = new ArrayList<>();

    private static int userCounts = 3;

    static {
        userList.add(new User(1, "Ahn", new Date(), "1234", "900101-1010101"));
        userList.add(new User(2, "Park", new Date(), "5678", "000202-2020202"));
        userList.add(new User(3, "Kim", new Date(), "1111", "930215-1111111"));
    }

    public List<User> findAll() {
        return userList;
    }

    public User save(User user) {
        if(user.getId() == null) {
            user.setId(++userCounts);
        }

        if(user.getJoinDate() == null) {
            user.setJoinDate(new Date());
        }

        userList.add(user);

        return user;
    }

    public User findOne(int id) {
        for(User u : userList) {
            if(u.getId() == id) {
                return u;
            }
        }

        return null;
    }

    public User deleteById(int id) {

        Iterator<User> iter = userList.iterator();
        User user;
        while(iter.hasNext()) {
            user = iter.next();

            if(user.getId() == id) {
                iter.remove();
                return user;
            }
        }

        return null;
    }
}
