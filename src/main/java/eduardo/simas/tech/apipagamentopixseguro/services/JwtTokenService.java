package eduardo.simas.tech.apipagamentopixseguro.services;



import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import eduardo.simas.tech.apipagamentopixseguro.entidade.Users;
import org.springframework.stereotype.Service;
import java.time.*;


@Service
public class JwtTokenService {
    private static final String SECRET_KEY = "4Z^XrroxR@dWxqf$mTTKwW$!@#qGr4P";

    private static final String ISSUER = "eduardo.simas-tech";


    public String gerarToken(Users users) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withIssuedAt(creationDate())
                    .withExpiresAt(expirationDate())
                    .withSubject(users.getEmail())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar o Token", exception);
        }
    }

    public String validarToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.require(algorithm).withIssuer(ISSUER).build().verify(token).getSubject();
        }catch (JWTCreationException exception){
            throw  new RuntimeException("Token invalido ou expirado.");
        }
    }

    private Instant creationDate() {
        return ZonedDateTime.now(ZoneId.of("America/Recife")).toInstant();
    }

    private Instant expirationDate() {
        return ZonedDateTime.now(ZoneId.of("America/Recife")).plusHours(4).toInstant();
    }

}
