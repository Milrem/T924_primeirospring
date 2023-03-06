package neves.cristiano.primeirospring.dto;

import lombok.Data;

@Data
public class ComodoResponse {

    private String id;
    private String nome;
    private Float largura;
    private Float comprimento;
    private Float altura;
}
