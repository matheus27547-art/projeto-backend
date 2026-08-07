package br.edu.fiec.helptec.features.equipamento.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipamentoFilterDTO {

    private String termo;
    private String nome;
    private String marca;
    private String modelo;
    private String numeroSérie;
    private Double precoMin;
    private Double precoMax;
}
