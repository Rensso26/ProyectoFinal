    package ec.edu.uce.interfaz.controller;

    import ec.edu.uce.interfaz.service.UserService;
    import ec.edu.uce.interfaz.state.User;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/fabric")
    public class UserController {

        @Autowired
        private UserService userService;


        @PostMapping("/user/")
        public Object save(@RequestBody User object) {
            return userService.save(object);
        }

        @GetMapping("/user/{name}")
        public Object findByName(@PathVariable String username) {
            return userService.findByName(username);
        }

        @PutMapping("/user/{name}")
        public Object update(String name, @RequestBody Object object) {
            return userService.update(name, (User) object);
        }


        @DeleteMapping("/user/del/{name}")
        public void delete(String name) {
            userService.delete(name);
        }


    }
