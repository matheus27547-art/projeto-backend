package br.edu.fiec.helptec.features.equipamento.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipamentoDTO {
    private Long id;
    private String nome;
    private String marca;
    private String modelo;
    private String numeroSérie;
    private Double preco;

}
