package neves.cristiano.primeirospring.dto;

import lombok.Data;

@Data
public class Area {
    private ComodoRequest comodo;
    public Float getAreaPiso() {
        return comodo.getLargura() * comodo.getComprimento();
    }
    public Float getAreaParede() {
        return (comodo.getLargura() * comodo.getAltura() + comodo.getComprimento() * comodo.getAltura()) * 2;
    }
}
