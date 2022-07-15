package ru.ilya.springbotproject.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import ru.ilya.springbotproject.entity.User;
import ru.ilya.springbotproject.service.UserService;

@Component
public class SaveThread implements Runnable{

    private UserService userService;
    private Thread t;
    private User users;

    @Autowired
    public SaveThread(UserService userService) {
        this.userService = userService;
    }

    private void save(){
        try {
            userService.saveUsers(users);
        } catch (DataIntegrityViolationException e){}
    }

    public void startSave(User users){
        this.users = users;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        save();
    }
}
