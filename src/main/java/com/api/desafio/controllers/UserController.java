package com.api.desafio.controllers;

import com.api.desafio.dtos.UserDto;
import com.api.desafio.models.UserModel;
import com.api.desafio.repositories.UserRepository;
import com.api.desafio.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.UUID;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping
public class UserController {

    final UserService userService;
    private final PasswordEncoder encoder;

    public UserController(UserService userService, PasswordEncoder encoder, UserRepository userRepository) {
        this.userService = userService;
        this.encoder = encoder;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> saveUser(@RequestBody @Valid UserDto userDto){
        if (userService.existByNickName(userDto.getNickName())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: NickName já está em uso!");
        }
        if (userService.existByEmail(userDto.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: e-mail já possui cadastro!");
        }

        var userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
        userModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        userModel.setPassword(encoder.encode(userModel.getPassword()));                         // encriptando a senha
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userModel));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<UserModel>> findUsers (@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAllUser(pageable));
    }

    /*testando busca
    @GetMapping("/find/{id}")
    public ResponseEntity<Object> findById (@PathVariable(value = "id") UUID id){
        Optional<UserModel> user = userService.findById(id);
        if (!user.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fudeu Bahia!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(user.get());
    }*/




}
