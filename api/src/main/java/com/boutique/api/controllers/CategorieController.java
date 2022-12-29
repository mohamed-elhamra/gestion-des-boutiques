package com.boutique.api.controllers;

import com.boutique.api.dtos.CategorieDto;
import com.boutique.api.services.CategorieService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategorieController {

    private CategorieService categorieService;

    @PostMapping
    public ResponseEntity<CategorieDto> createCategorie(@RequestBody @Valid CategorieDto categorieDto){
        return ResponseEntity.ok(categorieService.createCategorie(categorieDto));
    }

}
