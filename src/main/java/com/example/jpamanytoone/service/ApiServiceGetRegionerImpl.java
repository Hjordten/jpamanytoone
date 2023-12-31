package com.example.jpamanytoone.service;

import com.example.jpamanytoone.model.Kommune;
import com.example.jpamanytoone.model.Region;
import com.example.jpamanytoone.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ApiServiceGetRegionerImpl implements ApiServiceGetRegioner {

    private final RestTemplate restTemplate;

    @Autowired
    RegionRepository regionRepository;


    public ApiServiceGetRegionerImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    String regionUrl = "https://api.dataforsyningen.dk/regioner";


    private void saveRegioner(List<Region> regioner) {
        regioner.forEach(reg -> regionRepository.save(reg));
    }

    @Override
    public List<Region> getRegioner() {
        List<Region> lst = new ArrayList<>();
        ResponseEntity<List<Region>> regionResponse =
                restTemplate.exchange(regionUrl,
                        HttpMethod.GET, null, new      ParameterizedTypeReference<List<Region>>(){
                        });
        List<Region> regioner = regionResponse.getBody();
        saveRegioner(regioner);
        return regioner;
    }

    @Override
    public List<String> findKommuner(String id) {
        Set<Kommune> kommuneSet = regionRepository.findById(id).get().getKommuner();
        List<String> stringList = new ArrayList<>();

        kommuneSet.forEach(kommuneNavn -> stringList.add(kommuneNavn.getNavn()));

        return stringList;
    }

    @Override
    public List<Region> findUsingKodeAsList(String kode) {
        List<Region> regions = regionRepository.findUsingKodeAsList(kode);
        return regions;
    }

    @Override
    public Region save(Region region) {
        regionRepository.save(region);
        return region;
    }

    @Override
    public List<Region> findUsingNavnAsList(String navn) {
        List<Region> regionList = regionRepository.findRegionAsList(navn);
        return regionList;
    }

    @Override
    public Region findUsingkodeAsInstance(String kode) {
        Region region = regionRepository.findByKode(kode);
        return region;
    }

    @Override
    public Region delete(Region region) {
        regionRepository.delete(region);
        return region;
    }

    @Override
    public Region findUsingNavnAsInstance(String navn) {
        Region region = regionRepository.findByNavn(navn);
        return region;
    }

}
