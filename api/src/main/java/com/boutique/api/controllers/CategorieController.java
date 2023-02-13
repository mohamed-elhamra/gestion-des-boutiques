package com.boutique.api.controllers;

import com.boutique.api.dtos.categories.CategorieCreationDto;
import com.boutique.api.dtos.categories.CategorieResponseDto;
import com.boutique.api.services.CategorieService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
@Api(value = "Controlleur de catégorie")
public class CategorieController {

    private CategorieService categorieService;


    @Operation(summary = "Créer une catégorie", description = "Cette methode crée une catégorie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La catégorie a été créé"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PostMapping
    public ResponseEntity<CategorieResponseDto> createCategorie(@RequestBody @Valid CategorieCreationDto categorieCreationDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categorieService.createCategorie(categorieCreationDto));
    }

    @Operation(summary = "Mette à jour une catégorie", description = "Cette methode met à jour une catégorie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La catégorie a été modifiée"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("{publicId}")
    public ResponseEntity<CategorieResponseDto> updateCategorie(
            @PathVariable String publicId,
            @RequestBody @Valid CategorieCreationDto categorieCreationDto
    ) {
        return ResponseEntity.ok(categorieService.updateCategorie(publicId, categorieCreationDto));
    }

    @Operation(summary = "Supprimer une catégorie", description = "Cette methode supprime une catégorie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "La catégorie a été supprimée"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("{publicId}")
    public ResponseEntity<String> deleteCategorie(@PathVariable String publicId) {
        categorieService.deleteCategorie(publicId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Lister les catégories", description = "Cette methode liste les catégories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des catégories bien retournée"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping
    public ResponseEntity<List<CategorieResponseDto>> getAllCategories() {
        return ResponseEntity.ok(categorieService.getAllCategories());
    }

}
