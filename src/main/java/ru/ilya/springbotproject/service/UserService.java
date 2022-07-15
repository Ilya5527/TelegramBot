package ru.ilya.springbotproject.service;


import ru.ilya.springbotproject.entity.User;

import java.util.List;

public interface UserService {

    public List<User> getAllUsers();

    public void saveUsers (User users);

    public void delByUserId(String userId);
}
