package com.example.jpamanytoone.controller;

import com.example.jpamanytoone.model.Kommune;
import com.example.jpamanytoone.repository.KommuneRepository;
import com.example.jpamanytoone.service.ApiServiceGetKommuner;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class KommuneRestController {

    @Autowired
    ApiServiceGetKommuner apiServiceGetKommuner;
    @Autowired
    KommuneRepository kommuneRepository;

    @GetMapping("/kommuner")
    public List<Kommune> getKommuner() {
        List<Kommune> listKommuner = apiServiceGetKommuner.getKommuner();
        return listKommuner;
    }

    @GetMapping("/kommune/kode/{kode}")
    public List<Kommune> searchKommuneByKode(@PathVariable String kode) {
        List<Kommune> kommuner = kommuneRepository.findByKode(kode);
        return kommuner;
    }

    @GetMapping("/kommune/navn/{navn}")
    public List<Kommune> searchKommuneByPartialNavn(@PathVariable String navn) {
        List<Kommune> kommuner = kommuneRepository.findByNavnContaining(navn);
        return kommuner;
    }

    @PutMapping("/kommune/{id}")
    public Kommune updateKommune(@PathVariable String id, @RequestBody Kommune updatedKommune) {
        Kommune existingKommune = kommuneRepository.findById(id).orElseGet(() -> {
            throw new EntityNotFoundException("Kommune with ID " + id + " not found");
        });

        return kommuneRepository.save(existingKommune);
    }

    @PostMapping("/kommune")
    @ResponseStatus(HttpStatus.CREATED)
    public Kommune postKommune(@RequestBody Kommune kommune) {
        System.out.println(kommune);
        return kommuneRepository.save(kommune);
    }

}
