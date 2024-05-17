package eduardo.simas.tech.apipagamentopixseguro.seguranca;

import com.sun.net.httpserver.HttpServer;
import eduardo.simas.tech.apipagamentopixseguro.repository.UserRepository;
import eduardo.simas.tech.apipagamentopixseguro.services.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class UserAuthenticationFilter extends OncePerRequestFilter{


    @Autowired
    private JwtTokenService jwtTokenService ;

    @Autowired
    private UserRepository userRepository;


}
