package com.example.jpamanytoone.service;

import com.example.jpamanytoone.model.Region;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApiServiceGetRegioner {

    List<Region> getRegioner();
}
