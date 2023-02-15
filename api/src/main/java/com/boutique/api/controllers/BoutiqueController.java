package com.boutique.api.controllers;

import com.boutique.api.dtos.boutiques.BoutiqueCreationDto;
import com.boutique.api.dtos.boutiques.BoutiqueResponseDto;
import com.boutique.api.services.BoutiqueService;
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
@RequestMapping("/boutiques")
@AllArgsConstructor
@Api(value = "Contrôleur de boutique")
public class BoutiqueController {

    private BoutiqueService boutiqueService;


    @Operation(summary = "Créer une boutique", description = "Cette methode crée une boutique")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "La boutique a été créé"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PostMapping
    public ResponseEntity<BoutiqueResponseDto> createBoutique(@RequestBody @Valid BoutiqueCreationDto boutiqueCreationDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(boutiqueService.createBoutique(boutiqueCreationDto));
    }

    @Operation(summary = "Lister les boutiques", description = "Cette methode liste les boutiques")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des boutiques bien retournée"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping
    public ResponseEntity<List<BoutiqueResponseDto>> getAllBoutiques() {
        return ResponseEntity.ok(boutiqueService.listeBoutiques());
    }

    @Operation(summary = "Mettre à jour une boutique", description = "Cette methode met à jour une boutique")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "La boutique a été modifiée"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{publicId}")
    public ResponseEntity<BoutiqueResponseDto> updateBoutique(@PathVariable String publicId, @RequestBody @Valid BoutiqueCreationDto boutiqueCreationDto) {
        return ResponseEntity.accepted().body(boutiqueService.updateBoutique(publicId, boutiqueCreationDto));
    }

    @Operation(summary = "Supprimer une boutique", description = "Cette methode supprime une boutique")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "La boutique a été supprimée"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("{publicId}")
    public ResponseEntity<String> deleteBoutique(@PathVariable String publicId) {
        boutiqueService.deleteBoutique(publicId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
