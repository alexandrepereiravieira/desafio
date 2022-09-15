package com.api.desafio.controllers;

import com.api.desafio.dtos.PublicationDto;
import com.api.desafio.models.PublicationModel;
import com.api.desafio.models.UserModel;
import com.api.desafio.services.PublicationService;
import com.api.desafio.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping
public class PublicationController {


    final PublicationService publicationService;
    final UserService userService;

    public PublicationController(UserService userService, PublicationService publicationService) {
        this.publicationService = publicationService;
        this.userService = userService;
    }


    @PostMapping("/posts")
    public ResponseEntity<Object> createPublication(@RequestBody @Valid PublicationDto publicationDto){
        var publicationModel = new PublicationModel();
        BeanUtils.copyProperties(publicationDto, publicationModel);
        var user = userService.findById(publicationDto.getAuthor());
        if (user.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found: Usuário não encontrado!");
        }
        publicationModel.setAuthor(user.get());
        publicationModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(publicationService.save(publicationModel));
    }
/*  Buscando todas as publicações sem padronização [fora do escopo do desafio]
    @GetMapping("/posts")
    public ResponseEntity<Object> findPublication(){
        return ResponseEntity.status(HttpStatus.OK).body(publicationService.findAllPublication());
    }*/

    /*Buscando publicação por data ascendete e descendente (OK)
    * Faltando organizar buscar apenas do Usuario em questão*/
    @GetMapping("/posts")
    public Page<PublicationModel> findPublication(
        @PageableDefault(sort = "registrationDate", direction = Sort.Direction.DESC) Pageable pageable){
        return publicationService.findAll(pageable);
    }

    /*Alterando a Publicação (ok)*/
    @PutMapping("/posts/{id}")
    public ResponseEntity<Object> updatePublication(@PathVariable UUID id, @RequestBody @Valid PublicationDto publicationDto){
        Optional<PublicationModel> publicationModel = publicationService.findById(id);
        if (!publicationModel.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found: Publicação não encontrada!");
        }
        BeanUtils.copyProperties(publicationDto, publicationModel.get());
        return ResponseEntity.status(HttpStatus.OK).body(publicationService.save(publicationModel.get()));
    }

    /*Deletar posts criados, verificando se o post existe (ok)*/
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Object> deletePublication(@PathVariable UUID id){
        Optional<PublicationModel> publicationModel = publicationService.findById(id);
        if (!publicationModel.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found: Publicação não encontrada!");
        }
        publicationService.delete(publicationModel.get());
        return ResponseEntity.status(HttpStatus.OK).body("Publicação deletada com sucesso!");
    }

    /*listar usuarios (não faz parte do desafio, só para consultar os dados*/
    @GetMapping("/listUsers")
    public Page<UserModel> listUsers (Pageable pageable){
        return userService.findAllUser(pageable);
    }

}
