package com.api.desafio.services;

import com.api.desafio.models.UserModel;
import com.api.desafio.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserModel save(UserModel userModel) {
        return userRepository.save(userModel);
    }

    public boolean existByNickName(String nickName) {
        return userRepository.existsByNickName(nickName);
    }

    public boolean existByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

     public Page<UserModel> findAllUser(Pageable pageable){
        return userRepository.findAll(pageable);
     }

     public Optional<UserModel> findById (UUID id){
        return userRepository.findById(id);
     }
}
