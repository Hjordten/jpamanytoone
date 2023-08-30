package com.example.jpamanytoone.service;

import com.example.jpamanytoone.model.Region;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApiServiceGetRegioner {

    //@Query("SELECT p FROM Region p WHERE p.navn LIKE CONCAT('%', :query, '%')")
    List<Region> getRegioner();
}
