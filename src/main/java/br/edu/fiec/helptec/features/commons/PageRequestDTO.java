package br.edu.fiec.helptec.features.commons;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageRequestDTO {

    private int pageNum = 0;
    private int pageSize = 10;
    private String sortBy = "id";
    private String sortOrder = "asc";
}
