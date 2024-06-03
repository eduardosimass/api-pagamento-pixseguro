package eduardo.simas.tech.apipagamentopixseguro.controller;
import eduardo.simas.tech.apipagamentopixseguro.dto.UserResponse;
import eduardo.simas.tech.apipagamentopixseguro.entidade.Users;
import eduardo.simas.tech.apipagamentopixseguro.services.UserService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
public class UserController {

    @Autowired
    private UserService userServices;

    @PostMapping("/registrar")
    public ResponseEntity<UserResponse> registrarUsuario(@RequestBody Users users ) throws UnsupportedEncodingException, MessagingException {
        UserResponse userSaved = this.userServices.registerUser(users);
        return ResponseEntity.ok().body(userSaved);

    }

    @GetMapping("/privada")
    public ResponseEntity<String> rotaPrivada() {
        return ResponseEntity.ok("Rota privada");
    }


}
