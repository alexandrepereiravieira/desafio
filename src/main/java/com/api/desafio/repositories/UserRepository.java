package com.api.desafio.repositories;

import com.api.desafio.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {


    boolean existsByNickName(String nickName);
    boolean existsByEmail(String email);

    Optional<UserModel> findByNickName(String nickName);
}
