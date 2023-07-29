package Aceleracion.AppBancaria.Entidades.Dto.Request;

import java.math.BigDecimal;

public class TranferenciaRequestDTO {
    private String cbuFinal;
    private String cbuInicio;
    private BigDecimal monto;

    public TranferenciaRequestDTO() {
    }

    public TranferenciaRequestDTO(String cbuFinal, String cbuInicio, BigDecimal monto) {
        this.cbuFinal = cbuFinal;
        this.cbuInicio = cbuInicio;
        this.monto = monto;
    }

    public String getCbuFinal() {
        return cbuFinal;
    }

    public void setCbuFinal(String cbuFinal) {
        this.cbuFinal = cbuFinal;
    }

    public String getCbuInicio() {
        return cbuInicio;
    }

    public void setCbuInicio(String cbuInicio) {
        this.cbuInicio = cbuInicio;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
}
