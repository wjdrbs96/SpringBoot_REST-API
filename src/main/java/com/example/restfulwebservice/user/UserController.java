package com.example.restfulwebservice.user;

import com.example.restfulwebservice.helloworld.Message;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class UserController {
    private UserDaoService userDaoService;

    public UserController(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userDaoService.findAll();
    }

    // GET /users/1 or /users/10 -> 뒤에 붙은 숫자는 int 가 아니라 String 형태임
    // String 이어도 Converter 되기 때문에 int id 로 매게변수로 받아도 됨
    @GetMapping("/users/{id}")
    public Resource<User> retrieveUser(@PathVariable int id) {
        User user = userDaoService.findOne(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("Id[%s] not found", id));
        }

        // HATEOAS
        Resource<User> resource = new Resource<>(user);
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkTo.withRel("all-users"));
        return resource;
    }

    // 매게 변수로 Object 타입의 형태로 받기 위해서는 @RequestBody 어노테이션이 필요함
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User createUser = userDaoService.addUser(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "/test", produces = "application/json")
    public ResponseEntity<Message> test() {
        User user = new User();
        Message message = new Message("200", "success", user);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        User user = userDaoService.deleteById(id);
        if (user == null) {
            throw new UserNotFoundException(String.format("Id[%s] not found", id));
        }
    }

}
