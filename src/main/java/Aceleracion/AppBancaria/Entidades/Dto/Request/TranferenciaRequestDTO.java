package Aceleracion.AppBancaria.Entidades.Dto.Request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

public class TranferenciaRequestDTO {
    @NotBlank(message = "Ingrese el CBU destino")
    private String cbuFinal;
    @NotBlank(message = "Seleceione una de sus cuenta")
    private String cbuInicio;
    @Min(value = 1, message = "ingrese un monto")
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
