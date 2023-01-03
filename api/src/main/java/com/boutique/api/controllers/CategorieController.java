package com.boutique.api.controllers;

import com.boutique.api.dtos.categories.CategorieCreationDto;
import com.boutique.api.dtos.categories.CategorieDto;
import com.boutique.api.services.CategorieService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
@Tag(name = "Controlleur de catégorie", description = "La gestion des catégories")
public class CategorieController {

    private CategorieService categorieService;

    @PostMapping
    @ApiOperation(value = "Créer une catégorie", response = CategorieDto.class, tags = "createCategorie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La catégorie a été créé"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public ResponseEntity<CategorieDto> createCategorie(@RequestBody @Valid CategorieCreationDto categorieCreationDto) {
        return ResponseEntity.ok(categorieService.createCategorie(categorieCreationDto));
    }

    @PatchMapping("{publicId}")
    @ApiOperation(value = "Mette à jour une catégorie", response = CategorieDto.class, tags = "createCategorie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La catégorie a été bien modifiée"),
            @ApiResponse(responseCode = "400", description = "BadRequest"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public ResponseEntity<CategorieDto> updateCategorie(
            @PathVariable String publicId,
            @RequestBody @Valid CategorieCreationDto categorieCreationDto
    ) {
        return ResponseEntity.ok(categorieService.updateCategorie(publicId, categorieCreationDto));
    }

}
