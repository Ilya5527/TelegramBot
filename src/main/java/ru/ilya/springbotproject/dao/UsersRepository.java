package ru.ilya.springbotproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ilya.springbotproject.entity.User;

public interface UsersRepository extends JpaRepository<User, Integer> {
    public void deleteByUserId(String userId);
}
