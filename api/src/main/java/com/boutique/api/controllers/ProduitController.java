package com.boutique.api.controllers;

import com.boutique.api.dtos.boutiques.BoutiqueResponseDto;
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

    @Operation(summary = "Détails d'un produit", description = "Cette methode retourne les détails d'un produit")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Les détails d'un produit sont bien retournés"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{publicId}")
    public ResponseEntity<ProduitResponseDto> getProduit(@PathVariable String publicId) {
        return ResponseEntity.ok(produitService.getProduit(publicId));
    }

    @Operation(summary = "Supprimer un produit", description = "Cette methode supprime un produit")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Le produit a été supprimé"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("{publicId}")
    public ResponseEntity<String> deleteProduit(@PathVariable String publicId) {
        produitService.deleteProduit(publicId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
