package com.api.desafio.repositories;

import com.api.desafio.models.PublicationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PublicationRepository extends JpaRepository<PublicationModel, UUID> {



}
