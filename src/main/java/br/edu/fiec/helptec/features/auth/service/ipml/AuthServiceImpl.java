package br.edu.fiec.helptec.features.auth.service.ipml;

import br.edu.fiec.helptec.config.JwtService;
import br.edu.fiec.helptec.features.auth.models.AuthResponse;
import br.edu.fiec.helptec.features.auth.models.LoginRequest;
import br.edu.fiec.helptec.features.auth.models.RegisterRequest;
import br.edu.fiec.helptec.features.auth.service.AuthService;
import br.edu.fiec.helptec.features.usuario.model.entity.UserRole;
import br.edu.fiec.helptec.features.usuario.model.entity.UsuarioEntity;
import br.edu.fiec.helptec.features.usuario.repository.UsuarioRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UsuarioRepository usuarioRepository,
                           PasswordEncoder passwordEncoder,
                           JwtService jwtService,
                           AuthenticationManager authenticationManager) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void register(RegisterRequest request) {
        // Verifica se o e-mail já está cadastrado
        usuarioRepository.findByEmail(request.email()).ifPresent(u -> {
            throw new IllegalArgumentException("E-mail já cadastrado no sistema.");
        });

        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setNome(request.nome());
        usuarioEntity.setEmail(request.email());
        usuarioEntity.setPassword(passwordEncoder.encode(request.password())); // Criptografa a senha
        usuarioEntity.setFcmToken(request.fcmToken());
        usuarioEntity.setRole(UserRole.USER); // Define o perfil padrão como USER

        usuarioRepository.save(usuarioEntity);
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        // Autentica as credenciais com o AuthenticationManager
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        // Busca o usuário no banco para carregar as informações e ID
        UsuarioEntity usuario = usuarioRepository.findByEmail(request.email())
                .map(u -> (UsuarioEntity) u)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));

        // Gera o token JWT contendo ID e e-mail
        String token = jwtService.generateTokenWithClaims(usuario);

        return new AuthResponse(token);
    }
}
