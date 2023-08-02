package Aceleracion.AppBancaria.Entidades;

import javax.persistence.*;
@Entity
public class SolicitudBaja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private String motivo;

    public SolicitudBaja() {
    }

    public SolicitudBaja(Sucursal sucursal, Cliente cliente, String motivo) {

        this.cliente = cliente;
        this.motivo = motivo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }





    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
