package br.com.tdmxp.service;

import br.com.tdmxp.dto.user.UserCreateRequest;
import br.com.tdmxp.dto.user.UserResponse;
import br.com.tdmxp.exception.EmailAlreadyRegisteredException;
import br.com.tdmxp.exception.ResourceNotFoundException;
import br.com.tdmxp.model.User;
import br.com.tdmxp.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserResponse create(UserCreateRequest request) {
        if (repository.existsByEmailIgnoreCase(request.email())) {
            throw new EmailAlreadyRegisteredException("E-mail já cadastrado.");
        }

        User user = new User();
        user.setName(request.name().trim());
        user.setEmail(request.email().trim().toLowerCase());
        user.setPasswordHash(passwordEncoder.encode(request.password()));
        user.setCity(request.city().trim());
        user.setState(request.state().trim().toUpperCase());

        return toResponse(repository.save(user));
    }

    @Transactional(readOnly = true)
    public List<UserResponse> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public UserResponse findById(Long id) {
        return toResponse(getUser(id));
    }

    @Transactional
    public void delete(Long id) {
        User user = getUser(id);
        repository.delete(user);
    }

    private User getUser(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado: " + id));
    }

    private UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getEmail(),
                user.getCity(), user.getState(), user.getCreatedAt());
    }
}
