package com.nthokar.spring2023.userauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private CustomUsrDetailsService usrDetailsService;



    record LoginRequest(String username, String password) {};
    record LoginResponse(String message, String access_jwt_token, String refresh_jwt_token) {};
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(request.username, request.password);
        Authentication auth = authManager.authenticate(authenticationToken);

        CustomUsrDetails user = (CustomUsrDetails) usrDetailsService.loadUserByUsername(request.username);
        String access_token = tokenService.generateAccessToken(user);
        String refresh_token = tokenService.generateRefreshToken(user);

        return new LoginResponse("User with email = "+ request.username + " successfully logined!"

                , access_token, refresh_token);
    }

    record RefreshTokenResponse(String access_jwt_token, String refresh_jwt_token) {};
    @GetMapping("/token/refresh")
    public RefreshTokenResponse refreshToken(HttpHeaders request) {
        String headerAuth = request.getFirst("Authorization");
        String refreshToken = headerAuth.substring(7, headerAuth.length());

        String email = tokenService.parseToken(refreshToken).getSubject();
        CustomUsrDetails user = (CustomUsrDetails) usrDetailsService.loadUserByUsername(email);
        String access_token = tokenService.generateAccessToken(user);
        String refresh_token = tokenService.generateRefreshToken(user);

        return new RefreshTokenResponse(access_token, refresh_token);
    }

    @PostMapping("/register")
    public String refreshToken(@RequestBody LoginRequest request) {
        try {
            usrDetailsService.saveUser(request.username, request.password);
            return "ok";
        }
        catch (Exception e){
            return "bad";
        }
    }

    @PostMapping("/validate")
    public String validate(@RequestParam String token){
        return tokenService.parseToken(token).toString();
    }
}