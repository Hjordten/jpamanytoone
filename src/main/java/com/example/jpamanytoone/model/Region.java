package com.example.jpamanytoone.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Region {
    @Id
    private String kode;
    private String navn;
    private String href;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<Kommune> kommuner = new HashSet<>();


    public void setKommuner(Set<Kommune> kommuner) {
        this.kommuner = kommuner;
    }

    public Set<Kommune> getKommuner() {
        return kommuner;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getKode() {
        return kode;
    }

    public String getNavn() {
        return navn;
    }

    public String getHref() {
        return href;
    }

    public void setId(String id) {
    }
}
