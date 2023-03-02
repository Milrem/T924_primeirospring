package neves.cristiano.primeirospring.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Comodo {
    @Id
    private String id;
    private String nome;
    private Float largura;
    private Float comprimento;
    private Float altura;
    private LocalDateTime ultimoUpdate;
}
