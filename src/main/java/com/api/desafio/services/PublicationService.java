package com.api.desafio.services;

import com.api.desafio.models.PublicationModel;
import com.api.desafio.repositories.PublicationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class PublicationService {

    final PublicationRepository publicationRepository;

    public PublicationService (PublicationRepository publicationRepository){
        this.publicationRepository = publicationRepository;
    }


    @Transactional
    public PublicationModel save(PublicationModel publicationModel) {
        return publicationRepository.save(publicationModel);
    }

    public Page<PublicationModel> findAll(Pageable pageable) {
        return publicationRepository.findAll(pageable);
    }

    public Optional<PublicationModel> findById(UUID id) {
        return publicationRepository.findById(id);
    }
    @Transactional
    public void delete(PublicationModel publicationModel) {
        publicationRepository.delete(publicationModel);
    }

}
