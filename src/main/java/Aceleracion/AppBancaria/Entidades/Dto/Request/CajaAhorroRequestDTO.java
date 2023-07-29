package Aceleracion.AppBancaria.Entidades.Dto.Request;

import java.math.BigDecimal;

public class CajaAhorroRequestDTO {
    private Long id;
    private BigDecimal saldo;

    public CajaAhorroRequestDTO() {
    }

    public CajaAhorroRequestDTO(Long id, BigDecimal saldo) {
        this.id = id;
        this.saldo = saldo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
}
