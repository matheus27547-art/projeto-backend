package br.edu.fiec.helptec.features.salas;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "tb_salas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Salas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idSala;

    private String nome;
}
