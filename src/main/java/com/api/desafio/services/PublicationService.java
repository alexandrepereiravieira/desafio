package com.api.desafio.services;

import com.api.desafio.models.PublicationModel;
import com.api.desafio.repositories.PublicationRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
}
