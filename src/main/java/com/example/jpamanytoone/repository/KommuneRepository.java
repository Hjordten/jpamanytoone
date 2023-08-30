package com.example.jpamanytoone.repository;

import com.example.jpamanytoone.model.Kommune;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KommuneRepository extends JpaRepository<Kommune, String> {

    List<Kommune> findByNavnContaining(String partialNavn);

    List<Kommune> findByKode(String kode);


}
