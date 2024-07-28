package ec.edu.uce.interfaz.service;
import ec.edu.uce.interfaz.Interfaces.Serviceable;
import ec.edu.uce.interfaz.repository.UserRepository;
import ec.edu.uce.interfaz.state.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService implements Serviceable<User> {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByName(String name) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }


    @Override
    public void delete(String name) {
    }

    @Override
    public User update(String name, User user) {
        return null;
    }

}