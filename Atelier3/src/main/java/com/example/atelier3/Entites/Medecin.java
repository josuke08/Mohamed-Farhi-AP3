package com.example.atelier3.Entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Medecin {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Size(min=4, max=40)
    private String nom;
    @Column(unique = true)
    private String cin;
    @NotEmpty
    private String specialite;
    @OneToMany(fetch = FetchType.LAZY)
    private List<RendezVous> rendezVous = new ArrayList<>();
}
