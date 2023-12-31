package com.blog1.controller;

import com.blog1.entity.Role;
import com.blog1.entity.User;
import com.blog1.paylod.JWTAuthResponse;
import com.blog1.paylod.LoginDto;
import com.blog1.paylod.SignUpDto;
import com.blog1.repository.RoleRepository;
import com.blog1.repository.UserRepository;
import com.blog1.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


//    @PostMapping("/signin")
//    public ResponseEntity<String> authenticateUser (@RequestBody LoginDto loginDto){
//        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//                loginDto.getUsernameOrEmail(),loginDto.getPassword());
//
//
//        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
//
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        return new ResponseEntity<>("USER SIGNin SUCCESSfULLy!!", HttpStatus.OK);
//
//    }
    @PostMapping("/signin")
    public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody LoginDto
                                                                    loginDto){
        Authentication authentication = authenticationManager.authenticate(new
                UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // get token form tokenProvider
        String token = jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JWTAuthResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){

        if (userRepository.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<>("Email id Exixts:"+signUpDto.getEmail(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (userRepository.existsByUsername(signUpDto.getUsername())){
            return new ResponseEntity<>("UserName already exixts:"+signUpDto.getUsername(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

        User user = new User();
        user.setName(signUpDto.getName());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        user.setUsername(signUpDto.getUsername());

        Role role = roleRepository.findByRoleName("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(role));

        User savedUser = userRepository.save(user);

        return new ResponseEntity<>("User REgister Successfully", HttpStatus.OK);


    }




}
