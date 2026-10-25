package vn.hoidanit.laptopshop.service;

import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.UserRepository;

@Service 
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String hanlderHello() {
        return "hello from service";
    }

    public User hanldeSaveUser(User user) {
        User savedUser = this.userRepository.save(user);
        return savedUser;
    }
}
