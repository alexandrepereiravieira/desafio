package com.api.desafio.services;

import com.api.desafio.data.DetailUserData;
import com.api.desafio.models.UserModel;
import com.api.desafio.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DetailUserServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    public DetailUserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModel> user = repository.findByNickName(username);
        if (user.isEmpty()){
            throw new UsernameNotFoundException("Usuário [ " + username + "] não encontrado.");
        }


        return new DetailUserData(user);
    }


}
