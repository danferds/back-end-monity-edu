package com.pi2.monity_edu.controller;

import com.pi2.monity_edu.dto.LoginRequestDTO;
import com.pi2.monity_edu.dto.LoginResponseDTO;
import com.pi2.monity_edu.factory.ResponseFactory;
import com.pi2.monity_edu.model.Aluno;
import com.pi2.monity_edu.model.Usuario;
import com.pi2.monity_edu.response.ApiResponse;
import com.pi2.monity_edu.security.TokenService;
import com.pi2.monity_edu.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponseDTO>> login(@RequestBody @Valid LoginRequestDTO loginRequest) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getSenha());

        Authentication auth = authenticationManager.authenticate(usernamePassword);
        String token = tokenService.generateToken(auth);

        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        Usuario usuarioAutenticado = userDetails.getUsuario();
        String userType = (usuarioAutenticado instanceof Aluno) ? "ALUNO" : "MONITOR";

        LoginResponseDTO responsePayload = new LoginResponseDTO(
                token,
                usuarioAutenticado.getId(),
                usuarioAutenticado.getNome(),
                usuarioAutenticado.getEmail(),
                userType,
                tokenService.getExpirationDate(token),
                usuarioAutenticado.getStatus());

        return ResponseFactory.success(responsePayload);
    }
}