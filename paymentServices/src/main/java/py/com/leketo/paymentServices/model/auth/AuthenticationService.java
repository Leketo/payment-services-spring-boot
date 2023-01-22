package py.com.leketo.paymentServices.model.auth;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import py.com.leketo.paymentServices.config.JwtService;
import py.com.leketo.paymentServices.exceptions.custom.EmailDuplicateException;
import py.com.leketo.paymentServices.exceptions.enums.APIExceptionType;
import py.com.leketo.paymentServices.exceptions.errors.APIException;
import py.com.leketo.paymentServices.model.user.Role;
import py.com.leketo.paymentServices.model.user.User;
import py.com.leketo.paymentServices.model.user.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(RegisterRequest request) throws APIException {
    User emailUser = repository.findByEmail(request.getEmail()).orElse(null);
    if (emailUser != null){
      throw new EmailDuplicateException(APIExceptionType.APPLICATION, "0001", "Email ya existe");
    }

    var user = User.builder()
        .email(request.getEmail())
        .pin(request.getPin())
        .nombre(request.getNombre())
        .numeroDocumento(request.getNumeroDocumento())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(Role.CLIENT)
        .build();
    repository.save(user);
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) throws APIException {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    var user = repository.findByEmail(request.getEmail())
        .orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }
}