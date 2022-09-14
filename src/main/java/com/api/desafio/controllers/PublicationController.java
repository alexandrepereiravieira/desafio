package com.api.desafio.controllers;

import com.api.desafio.dtos.PublicationDto;
import com.api.desafio.models.PublicationModel;
import com.api.desafio.services.PublicationService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping
public class PublicationController {

    final PublicationService publicationService;

    public PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    @PostMapping("/posts")
    public ResponseEntity<Object> createPublication(@RequestBody @Valid PublicationDto publicationDto){
        var publicationModel = new PublicationModel();
        BeanUtils.copyProperties(publicationDto, publicationModel);
        publicationModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(publicationService.save(publicationModel)) ;

    }
}
