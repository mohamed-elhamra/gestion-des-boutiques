package com.boutique.api.controllers;

import com.boutique.api.dtos.produits.ProduitCreationDto;
import com.boutique.api.dtos.produits.ProduitResponseDto;
import com.boutique.api.services.ProduitService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/produits")
@AllArgsConstructor
@Api(value = "Contrôleur du produit")
public class ProduitController {

    private ProduitService produitService;

    @Operation(summary = "Créer un produit", description = "Cette methode crée un produit")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Le produit a été créé"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PostMapping
    public ResponseEntity<ProduitResponseDto> createProduit(@RequestBody @Valid ProduitCreationDto produitCreationDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(produitService.createProduit(produitCreationDto));
    }

    @Operation(summary = "Mettre à jour un produit", description = "Cette methode met à jour un produit")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Le produit a été modifié"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{publicId}")
    public ResponseEntity<ProduitResponseDto> updateProduit(@PathVariable String publicId, @RequestBody @Valid ProduitCreationDto produitCreationDto) {
        return ResponseEntity.accepted().body(produitService.updateProduit(publicId, produitCreationDto));
    }

}
