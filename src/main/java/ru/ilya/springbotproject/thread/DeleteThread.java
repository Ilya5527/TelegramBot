package ru.ilya.springbotproject.thread;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.ilya.springbotproject.entity.User;
import ru.ilya.springbotproject.service.UserService;

import java.util.concurrent.TimeUnit;

@Component
public class DeleteThread implements Runnable{

    private UserService userService;
    private Thread t;
    private User users;
    private String userId;

    @Autowired
    public DeleteThread(UserService userService) {
        this.userService = userService;

    }
    @SneakyThrows
    private void delete() {
        userService.delByUserId(userId);
        TimeUnit.MILLISECONDS.sleep(200);
    }

    public void startDelete(String userId){
        this.userId = userId;
        t = new Thread(this);
        t.start();

    }

    @Override
    public void run() {
        delete();
    }
}
