package pe.com.carlosh.tallyapi.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.carlosh.tallyapi.user.dto.LoginResponseDTO;
import pe.com.carlosh.tallyapi.user.dto.UserRequestDTO;
import pe.com.carlosh.tallyapi.user.dto.UserResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<LoginResponseDTO> register(@Valid @RequestBody UserRequestDTO req) {
        LoginResponseDTO response = userService.register(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }
}