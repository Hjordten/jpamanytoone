package com.example.jpamanytoone.service;

import com.example.jpamanytoone.model.Kommune;
import com.example.jpamanytoone.repository.KommuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ApiServiceGetKommunerimpl implements ApiServiceGetKommuner {

    private final RestTemplate restTemplate;

    public ApiServiceGetKommunerimpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    String kommuneUrl = "https://api.dataforsyningen.dk/kommuner";
    @Autowired
    KommuneRepository kommuneRepository;


    private void saveKommuner(List<Kommune> kommuner) {
        kommuneRepository.saveAll(kommuner);
    }

    @Override
    public List<Kommune> getKommuner() {
        ResponseEntity<List<Kommune>> kommuneResponse =
                restTemplate.exchange(kommuneUrl,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Kommune>>() {
                        });
        List<Kommune> kommuner = kommuneResponse.getBody();
        saveKommuner(kommuner);
        return kommuner;
    }

    @Override
    public List<Kommune> findUsingKodeAsList(String kode) {
        List<Kommune> kommuneList = kommuneRepository.findUsingKodeAsList(kode);
        return kommuneList;
    }

    @Override
    public List<Kommune> findUsingNavnAsList(String navn) {
        List<Kommune> kommuneList = kommuneRepository.findUsingNavnAsList(navn);
        return kommuneList;
    }

    @Override
    public Kommune findUsingKodeAsInstance(String kode) {
        Kommune kommune = kommuneRepository.findUsingKodeAsInstance(kode);
        return kommune;
    }

    @Override
    public Kommune findUsingNavnAsInstance(String navn) {
        Kommune kommune = kommuneRepository.findUsingNavnAsInstance(navn);
        return kommune;
    }

    @Override
    public Kommune save(Kommune kommune) {
        kommuneRepository.save(kommune);
        return kommune;
    }

    @Override
    public Kommune delete(Kommune kommune) {
        kommuneRepository.delete(kommune);
        return kommune;
    }
}
