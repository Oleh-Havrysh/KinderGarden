package ua.nure.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.nure.controller.request.LoginRequest;
import ua.nure.exception.BadRequestException;
import ua.nure.exception.ResourceNotFoundException;
import ua.nure.model.Human;
import ua.nure.security.jwt.JwtAuthenticationResponse;
import ua.nure.security.jwt.JwtTokenProvider;
import ua.nure.service.HumanService;
import ua.nure.util.ImageHelper;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@MultipartConfig(maxFileSize = 10000000)
@RestController
public class HumanController {

    private static final Logger LOG = LoggerFactory.getLogger(HumanController.class);
    @Autowired
    private HumanService humanService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public JwtAuthenticationResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
        return new JwtAuthenticationResponse(token);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public JwtAuthenticationResponse register(@Valid @RequestBody Human userToRegister) {
        LOG.info("In register method.....................");
        if (humanService.findByEmail(userToRegister.getEmail()) != null) {
            throw new BadRequestException("Such username is already taken!");
        }
        Human user = new Human(userToRegister.getName(), userToRegister.getSurname(), userToRegister.getAddress(),
                userToRegister.getPhone(), userToRegister.getEmail(), userToRegister.getRole(),
                userToRegister.getLogin(), userToRegister.getPassword());

        humanService.register(user);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(userToRegister.getEmail());
        loginRequest.setPassword(userToRegister.getPassword());
        return login(loginRequest);
    }

    @PostMapping("/saveUser")
    @ResponseStatus(HttpStatus.OK)
    public void saveUser(@Valid @RequestBody Human user) {
        humanService.save(user);
    }

    @GetMapping("/currentUser")
    @ResponseStatus(HttpStatus.OK)
    public Human getCurrentUser(HttpServletRequest request) {
        String jwt = jwtTokenProvider.getJwtFromRequest(request);
        String userId = jwtTokenProvider.getUserIdFromJwt(jwt);
        Human user = humanService.findById(userId);
        if (user == null) {
            throw new ResourceNotFoundException("User", "id", userId);
        }
        return user;
    }

    @GetMapping("/findByRole/{role}")
    @ResponseStatus(HttpStatus.OK)
    public List<Human> findUsersByRole(@PathVariable("role") int role) {
        return humanService.findByRole(role);
    }

    @PostMapping(value = "/image/{path}")
    public String uploadImage(@RequestParam("img") MultipartFile img, @PathVariable("path") String path) throws IOException {
        ImageHelper.save(path, img.getBytes());
        return "OK";
    }
    @GetMapping(value = "/image/{path}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImage(@PathVariable("path") String path) throws IOException {
        return ImageHelper.load(path);
    }

    @GetMapping("/humans/{id}")
    public Human getHuman(@PathVariable("id") String id) {
        return humanService.findById(id);
    }
}
