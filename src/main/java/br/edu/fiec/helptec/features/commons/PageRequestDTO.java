package br.edu.fiec.helptec.features.commons;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {

    private int page;
    private int size;

    public String getSortBy() {
        return "";
    }

    public int getPageNum() {
        return page;
    }

    public int getPageSize() {
        return size;
    }

    public String getSortOrder() {
        return "";
    }
}
