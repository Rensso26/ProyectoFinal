package ec.edu.uce.interfaz.service;

import ec.edu.uce.interfaz.state.User;
import ec.edu.uce.interfaz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findByName(String name) {
        return userRepository.findById(name);
    }

    public User update(String name, User user) {
        return userRepository.save(user);
    }

    public void delete(String name) {
        userRepository.deleteById(name);
    }
}
