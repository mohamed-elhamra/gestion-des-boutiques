package com.boutique.api.controllers;

import com.boutique.api.dtos.CategorieDto;
import com.boutique.api.services.CategorieService;
import io.swagger.annotations.Api;
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
            @ApiResponse(responseCode = "200", description = "Opération réussite"),
            @ApiResponse(responseCode= "400", description = "BadRequest") }
    )
    public ResponseEntity<CategorieDto> createCategorie(@RequestBody @Valid CategorieDto categorieDto){
        return ResponseEntity.ok(categorieService.createCategorie(categorieDto));
    }

}
