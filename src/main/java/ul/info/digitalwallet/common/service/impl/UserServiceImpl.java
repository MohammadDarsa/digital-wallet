package ul.info.digitalwallet.common.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ul.info.digitalwallet.common.exceptions.UserNotFoundException;
import ul.info.digitalwallet.common.models.User;
import ul.info.digitalwallet.common.repository.UserRepository;
import ul.info.digitalwallet.common.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User getAuthenticatedUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    }
}
