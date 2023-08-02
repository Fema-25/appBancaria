package Aceleracion.AppBancaria.Entidades.Dto.Response;

import Aceleracion.AppBancaria.Entidades.Cliente;

public class SolicitudBajaResponseDTO {
    private Long id;
    private ClienteResponseDTO cliente;
    private String motivo;

    public SolicitudBajaResponseDTO() {
    }

    public SolicitudBajaResponseDTO(Long id, ClienteResponseDTO cliente, String motivo) {
        this.id = id;
        this.cliente = cliente;
        this.motivo = motivo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClienteResponseDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteResponseDTO cliente) {
        this.cliente = cliente;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
