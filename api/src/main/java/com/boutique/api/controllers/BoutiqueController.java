package com.boutique.api.controllers;

import com.boutique.api.dtos.boutiques.BoutiqueCreationDto;
import com.boutique.api.dtos.boutiques.BoutiqueResponseDto;
import com.boutique.api.dtos.categories.CategorieResponseDto;
import com.boutique.api.services.BoutiqueService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/boutiques")
@AllArgsConstructor
@Tag(name = "Controlleur d'une boutique", description = "La gestion des boutiques")
public class BoutiqueController {

    private BoutiqueService boutiqueService;

    @PostMapping
    @ApiOperation(value = "Créer une boutique", response = CategorieResponseDto.class, tags = "createBoutique")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La boutique a été créé"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public ResponseEntity<BoutiqueResponseDto> createBoutique(@RequestBody @Valid BoutiqueCreationDto boutiqueCreationDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(boutiqueService.createBoutique(boutiqueCreationDto));
    }

}
