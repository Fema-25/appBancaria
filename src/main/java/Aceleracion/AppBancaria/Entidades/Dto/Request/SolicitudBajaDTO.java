package Aceleracion.AppBancaria.Entidades.Dto.Request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class SolicitudBajaDTO {
    @Min(value = 1, message = "El id del banco debe ser mayor o igual a 1")
    private long idCliente;

    @NotBlank(message = "Ingrese unmotivo para la baja")
    private String motivo;

    public SolicitudBajaDTO() {
    }

    public SolicitudBajaDTO(long idCliente, long idSucursal, String motivo) {
        this.idCliente = idCliente;

        this.motivo = motivo;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }





    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
