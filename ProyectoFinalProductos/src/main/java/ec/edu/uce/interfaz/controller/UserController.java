package ec.edu.uce.interfaz.controller;

import ec.edu.uce.interfaz.Interfaces.Controlleable;
import ec.edu.uce.interfaz.service.UserService;
import ec.edu.uce.interfaz.state.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fabric")
public class UserController implements Controlleable {

    private UserService userService;

    @PostMapping
    public User save(@RequestBody User user) {
        return (User) userService.save(user);
    }

    @Override
    @PostMapping
    public Object save(@RequestBody Object object) {
        return   userService.save (object) ;
    }

    @Override
    @GetMapping("/user/{name}")
    public Object findByName(@PathVariable String username) {
        return userService.findByName(username);
    }

    @Override
    @PutMapping("/user/{name}")
    public Object update(String name, @RequestBody Object object) {
        return userService.update(name, object);
    }

    @Override
    @DeleteMapping("/category/del/{name}")
    public void delete(String name) {
        userService.delete(name);
    }


}
