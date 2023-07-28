package Aceleracion.AppBancaria.Entidades.Dto.Response;

import java.math.BigDecimal;

public class CajaAhorroDTO {
    private Long id;
    private BigDecimal saldo;
    private String cbu;

    public CajaAhorroDTO() {
    }

    public CajaAhorroDTO(Long id, BigDecimal saldo, String cbu) {
        this.id = id;
        this.saldo = saldo;
        this.cbu = cbu;
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

    public String getCbu() {
        return cbu;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }
}
