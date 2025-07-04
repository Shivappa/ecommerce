package com.techloom.EcomProductService.userService.service;

import com.techloom.EcomProductService.dto.SessionStatus;
import com.techloom.EcomProductService.userService.dto.UserDto;
import com.techloom.EcomProductService.userService.exception.InvalidCredentialException;
import com.techloom.EcomProductService.exception.InvalidTokenException;
import com.techloom.EcomProductService.userService.exception.UserNotFoundException;
import com.techloom.EcomProductService.userService.mapper.UserEntityDTOMapper;
import com.techloom.EcomProductService.userService.model.Session;
import com.techloom.EcomProductService.userService.model.User;
import com.techloom.EcomProductService.userService.repository.SessionRepository;
import com.techloom.EcomProductService.userService.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMapAdapter;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.util.*;

@Service
public class AuthService {
    private UserRepository userRepository;
    private SessionRepository sessionRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthService(UserRepository userRepository, SessionRepository sessionRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public ResponseEntity<List<Session>> getAllSession(){
        List<Session> sessions = sessionRepository.findAll();
        return ResponseEntity.ok(sessions);
    }

    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userRepository.findAll());
    }

    public ResponseEntity<UserDto> login(String email, String password) {
        //Get user details from DB
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User for the given email id does not exist");
        }
        User user = userOptional.get();
        //Verify the user password given at the time of login
        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialException("Invalid Credentials");
        }
        //token generation
        //String token = RandomStringUtils.randomAlphanumeric(30);
        MacAlgorithm alg = Jwts.SIG.HS256; // HS256 algo added for JWT
        SecretKey key = alg.key().build(); // generating the secret key

        //start adding the claims
        Map<String, Object> jsonForJWT = new HashMap<>();
        jsonForJWT.put("userId", user.getId());
        jsonForJWT.put("roles", user.getRoles());
        jsonForJWT.put("createdAt", new Date());
        jsonForJWT.put("expiryAt", new Date(LocalDate.now().plusDays(3).toEpochDay()));

        String token = Jwts.builder()
                .claims(jsonForJWT) // added the claims
                .signWith(key, alg) // added the algo and key
                .compact(); //building the token


        //session creation
        Session session = new Session();
        session.setSessionStatus(SessionStatus.ACTIVE);
        session.setToken(token);
        session.setUser(user);
        session.setLoginAt(new Date());
        sessionRepository.save(session);
        //generating the response
        UserDto userDto = UserEntityDTOMapper.getUserDTOFromUserEntity(user);
        //setting up the headers
        MultiValueMapAdapter<String, String> headers = new MultiValueMapAdapter<>(new HashMap<>());
        headers.add(HttpHeaders.SET_COOKIE, token);
        return new ResponseEntity<>(userDto, headers, HttpStatus.OK);
    }

    public ResponseEntity<Void> logout(String token, Long userId) {
        // validations -> token exists, token is not expired, user exists else throw an exception
        Optional<Session> sessionOptional = sessionRepository.findByTokenAndUser_Id(token, userId);
        if (sessionOptional.isEmpty()) {
            return null; //TODO throw exception here
        }
        Session session = sessionOptional.get();
        session.setSessionStatus(SessionStatus.ENDED);
        sessionRepository.save(session);
        return ResponseEntity.ok().build();
    }

    public UserDto signUp(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        User savedUser = userRepository.save(user);
        return UserDto.from(savedUser);
    }

    public SessionStatus validate(String token, Long userId) {
        //TODO check expiry // Jwts Parser -> parse the encoded JWT token to read the claims

        //verifying from DB if session exists
        Optional<Session> sessionOptional = sessionRepository.findByTokenAndUser_Id(token, userId);
        if (sessionOptional.isEmpty() || sessionOptional.get().getSessionStatus().equals(SessionStatus.ENDED)) {
            throw new InvalidTokenException("token is invalid");
        }
        return SessionStatus.ACTIVE;
    }

}
/*
    MultiValueMapAdapter is map with single key and multiple values
    Headers
    Key     Value
    Token   """
    Accept  application/json, text, images
 */
