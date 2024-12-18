package com.example.eshop.db.services;

import com.example.eshop.db.entities.User;
import com.example.eshop.db.repositories.UserRepository;
import com.example.eshop.common.AbstractService;
import com.example.eshop.rest.mappers.UserMapper;
import com.example.eshop.rest.model.UserDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends AbstractService<User, Long> {

    private final UserMapper userMapper;

    public UserService(UserRepository repository, UserMapper userMapper) {
        super(repository);
        this.userMapper = userMapper;
    }

    public Optional<User> findByEmail(String email) {
        return ((UserRepository) repository).findByEmail(email);
    }
}