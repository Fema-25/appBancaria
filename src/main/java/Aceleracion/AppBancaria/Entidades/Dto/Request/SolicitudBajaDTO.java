package Aceleracion.AppBancaria.Entidades.Dto.Request;

public class SolicitudBajaDTO {
    private long idCliente;
    private long idSucursal;
    private String motivo;

    public SolicitudBajaDTO() {
    }

    public SolicitudBajaDTO(long idCliente, long idSucursal, String motivo) {
        this.idCliente = idCliente;
        this.idSucursal = idSucursal;
        this.motivo = motivo;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public long getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(long idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
