package com.boutique.api.controllers;

import com.boutique.api.dtos.categories.CategorieCreationDto;
import com.boutique.api.dtos.categories.CategorieDto;
import com.boutique.api.services.CategorieService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
        return ResponseEntity.status(HttpStatus.CREATED).body(categorieService.createCategorie(categorieCreationDto));
    }

    @PatchMapping("{publicId}")
    @ApiOperation(value = "Mette à jour une catégorie", response = CategorieDto.class, tags = "updateCategorie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La catégorie a été modifiée"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public ResponseEntity<CategorieDto> updateCategorie(
            @PathVariable String publicId,
            @RequestBody @Valid CategorieCreationDto categorieCreationDto
    ) {
        return ResponseEntity.ok(categorieService.updateCategorie(publicId, categorieCreationDto));
    }

    @DeleteMapping("{publicId}")
    @ApiOperation(value = "Supprimer une catégorie", tags = "deleteCategorie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "La catégorie a été supprimée"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public ResponseEntity<String> deleteCategorie(@PathVariable String publicId) {
        categorieService.deleteCategorie(publicId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    @ApiOperation(value = "Créer une catégorie", response = CategorieDto.class, responseContainer = "List", tags = "getAllCategories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des catégories bien retournée"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    public ResponseEntity<List<CategorieDto>> getAllCategories() {
        return ResponseEntity.ok(categorieService.getAllCategories());
    }

}
