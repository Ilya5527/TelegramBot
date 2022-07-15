package ru.ilya.springbotproject.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ilya.springbotproject.entity.User;
import ru.ilya.springbotproject.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetAllUsersThread implements Runnable{

    private UserService userService;
    private Thread t;
    private List<User> usersList = new ArrayList<>();;

    public GetAllUsersThread(@Autowired UserService userService) {
        this.userService = userService;
    }

    public void startThreadForGetUsers() {
        t = new Thread(this);
        t.start();
    }

    public List<User> getAllUser(){
       return userService.getAllUsers();

    }

    @Override
    public void run() {
        getAllUser();
    }
}
