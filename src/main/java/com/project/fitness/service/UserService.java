package com.project.fitness.service;

import com.project.fitness.dto.RegisterRequest;
import com.project.fitness.dto.UserResponse;
import com.project.fitness.model.User;
import com.project.fitness.model.UserRole;
import com.project.fitness.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse register(RegisterRequest request) {

        UserRole role = request.getRole() != null ? request.getRole()
                :UserRole.USER;

        User user = User.builder()

                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(passwordEncoder.encode(request.getPassword()))
                 .role(role)
                .build();

//        User user = new User(
//
//                null,
//                request.getEmail(),
//                request.getPassword(),
//                request.getFirstName(),
//                request.getLastName(),
//           null,
//
//            null,
//                List.of(),
//                List.of()
//        );

        User saveduser = userRepository.save(user);

        return mapToResponse(saveduser);


    }

    public UserResponse mapToResponse(User saveduser) {

        UserResponse response = new UserResponse();
        response.setId(saveduser.getId());
        response.setEmail(saveduser.getEmail());
        response.setPassword(saveduser.getPassword());
        response.setFirstName(saveduser.getFirstName());
        response.setLastName(saveduser.getLastName());
        response.setCreatedAt(saveduser.getCreatedAt());
        response.setUpdatedAt(saveduser.getUpdatedAt());

        return response;

    }

}



