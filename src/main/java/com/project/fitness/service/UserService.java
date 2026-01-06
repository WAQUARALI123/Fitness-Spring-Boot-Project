package com.project.fitness.service;

import com.project.fitness.dto.RegisterRequest;
import com.project.fitness.dto.UserResponse;
import com.project.fitness.model.User;
import com.project.fitness.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse register(RegisterRequest request) {

        User user = User.builder()

                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(request.getPassword())
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

    private UserResponse mapToResponse(User saveduser) {

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



