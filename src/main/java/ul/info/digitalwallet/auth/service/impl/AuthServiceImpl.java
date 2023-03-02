package ul.info.digitalwallet.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ul.info.digitalwallet.auth.exceptions.EmailAlreadyExistsException;
import ul.info.digitalwallet.auth.exceptions.UserAlreadyExistsException;
import ul.info.digitalwallet.auth.payload.request.LoginRequest;
import ul.info.digitalwallet.auth.payload.request.SignupRequest;
import ul.info.digitalwallet.auth.payload.response.JwtResponse;
import ul.info.digitalwallet.auth.payload.response.MessageResponse;
import ul.info.digitalwallet.auth.service.AuthService;
import ul.info.digitalwallet.common.models.Role;
import ul.info.digitalwallet.common.models.User;
import ul.info.digitalwallet.common.models.enumeration.ERole;
import ul.info.digitalwallet.common.repository.RoleRepository;
import ul.info.digitalwallet.common.repository.UserRepository;
import ul.info.digitalwallet.common.service.BalanceService;
import ul.info.digitalwallet.common.service.ProfileService;
import ul.info.digitalwallet.common.service.WalletService;
import ul.info.digitalwallet.common.service.dto.ProfileDTO;
import ul.info.digitalwallet.security.jwt.JwtUtils;
import ul.info.digitalwallet.security.services.UserDetailsImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final WalletService walletService;
    private final BalanceService balanceService;
    private final ProfileService profileService;

    @Override
    public void signUp(SignupRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UserAlreadyExistsException(request.getUsername());
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException(request.getEmail());
        }

        // Create new user's account
        User user = new User(request.getUsername(),
                request.getEmail(),
                encoder.encode(request.getPassword()));

        Set<String> strRoles = request.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin" -> {
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                    }
                    case "mod" -> {
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                    }
                    default -> {
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                    }
                }
            });
        }

        user.setRoles(roles);
        User savedUser = userRepository.save(user);

        walletService.save(savedUser);
        balanceService.save("USD", savedUser);
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setFirstName(request.getFirstName());
        profileDTO.setLastName(request.getLastName());
        profileService.save(profileDTO, savedUser);
    }

    @Override
    public JwtResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);
    }
}
