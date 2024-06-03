package eduardo.simas.tech.apipagamentopixseguro.seguranca;

import eduardo.simas.tech.apipagamentopixseguro.repository.UserRepository;
import eduardo.simas.tech.apipagamentopixseguro.services.JwtTokenService;
import jakarta.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Base64;
import java.util.Objects;

@Component
public abstract class UserAuthenticationFilter extends OncePerRequestFilter{

    private static final int BASIC_LENGTH = 6;
    private static final String USUARIO = "usuario" ;
    private static final String PASSWORD = "123456" ;




    @Override
    protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain) throws IOException, IOException, ServletException {
        var headerAuthorization = request.getHeader("Authorization");

        if(Objects.isNull(headerAuthorization) || !headerAuthorization.startsWith("Basic ")){
            filterChain.doFilter(request, response);
            return;

        }

        var basicToken  = headerAuthorization.substring(BASIC_LENGTH);
        byte[] basicTokenDecode = Base64.getDecoder().decode(basicToken);

        String basicTokenValue = new String(basicTokenDecode);

        String[] basicAuthSplit = basicToken.split(":");

        if(basicAuthSplit[0].equals(USUARIO) && basicAuthSplit[1].equals(PASSWORD)){
            var authToken = new UsernamePasswordAuthenticationToken(basicAuthSplit[0],null , null );
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        filterChain.doFilter(request,response);


    }
}
