package eduardo.simas.tech.apipagamentopixseguro.services;

import eduardo.simas.tech.apipagamentopixseguro.dto.UserRecordDto;
import eduardo.simas.tech.apipagamentopixseguro.dto.UserResponse;
import eduardo.simas.tech.apipagamentopixseguro.entidade.Users;
import eduardo.simas.tech.apipagamentopixseguro.repository.UserRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Objects;


@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;




    public UserResponse registerUser(Users user) throws UnsupportedEncodingException, MessagingException {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("This email is already in use");
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setSenha(encodedPassword);
        String randomCode = "546465465";
        user.setCodigoVerificacao(randomCode);
        user.setAtivo(false);

        Users savedUser = userRepository.save(user);

        return new UserResponse(savedUser.getId(), savedUser.getNome(), savedUser.getEmail(), savedUser.getPassword());

    }

}