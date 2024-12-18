package com.example.eshop.db.services;

import com.example.eshop.db.entities.User;
import com.example.eshop.db.repositories.UserRepository;
import com.example.eshop.rest.common.AbstractCrudService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends AbstractCrudService<User, Long> {

    public UserService(UserRepository repository) {
        super(repository);
    }

    public Optional<User> findByEmail(String email) {
        return ((UserRepository) repository).findByEmail(email);
    }
}