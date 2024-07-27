package ec.edu.uce.interfaz.service;
import ec.edu.uce.interfaz.Interfaces.Serviceable;
import ec.edu.uce.interfaz.repository.UserRepository;
import ec.edu.uce.interfaz.state.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService implements Serviceable {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public Object save(Object object) {
        return userRepository.save((User)object);
    }

    @Override
    public User findByName(String name) {
            return null;
    }

    @Override
    public List<Object> findAll() {
        return null;
    }


    @Override
    public void delete(String name) {
    }

    @Override
    public Object update(String name, Object object) {
            return null;
    }

}
