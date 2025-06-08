package juanrns.book_phone.controller;

import juanrns.book_phone.domain.user.User;
import juanrns.book_phone.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin/user")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userService.deleteUser(user,id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> getCurrentUser() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
