package br.com.robertoxavier.api.controllers;


import br.com.robertoxavier.api.service.JwtService;
import br.com.robertoxavier.dto.login.LoginRequest;
import br.com.robertoxavier.dto.login.LoginResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements AutenticacaoAPI {

    private final AuthenticationManager authenticationManager;
    private final JwtService tokenProvider;

    public AuthController(AuthenticationManager authenticationManager, JwtService tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public ResponseEntity<?> login(LoginRequest input) {
        // Autenticar o usuário
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.login(),
                        input.password()
                )
        );

        String jwt = tokenProvider.generateToken(authentication);
        String refreshToken = tokenProvider.generateRefreshToken(authentication);
        return ResponseEntity.ok(new LoginResponse(jwt, refreshToken));
    }

    @Override
    public ResponseEntity<?> refreshToken(@RequestHeader("refresh-token") String authorizationHeader, @RequestBody(required = false) Object body) {


        if (!tokenProvider.validateRefreshToken(authorizationHeader)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Refresh token inválido ou expirado.");
        }

        // Extrair o nome do usuário do refresh token
        String username = tokenProvider.getUsernameFromRefreshToken(authorizationHeader);

        // Gerar novos tokens
        String newAccessToken = tokenProvider.generateTokenFromUsername(username);
        String newRefreshToken = tokenProvider.generateRefreshTokenFromUsername(username);

        return ResponseEntity.ok(new LoginResponse(newAccessToken, newRefreshToken));
    }

    public String verificarToken(@RequestHeader("Authorization") String authorizationHeader){

        return tokenProvider.validateToken(authorizationHeader) ? "ok" : "erro";
    }
}
