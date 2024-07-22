package ec.edu.uce.interfaz.controller;

import ec.edu.uce.interfaz.service.UserService;
import ec.edu.uce.interfaz.state.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fabric")
public class UserController {

    private UserService userService;

    @PostMapping
    public User save(@RequestBody User user) {
        return (User) userService.save(user);
    }

    @GetMapping("user/{name}")
    public User findByName(@PathVariable String username) {
        return userService.findByName(username);
    }

    @PutMapping("/user/{name}")
    public User update(@PathVariable String name, @RequestBody User user) {
        return (User) userService.update(name, user);
    }
}
