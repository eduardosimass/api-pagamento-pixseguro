package eduardo.simas.tech.apipagamentopixseguro.repository;

import eduardo.simas.tech.apipagamentopixseguro.entidade.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;


public interface UserRepository  extends JpaRepository<Users, Long> {

    UserDetails findByEmail(String email);

    User findByCodigoDeVerificacao(String codigoVerificacao);


}

