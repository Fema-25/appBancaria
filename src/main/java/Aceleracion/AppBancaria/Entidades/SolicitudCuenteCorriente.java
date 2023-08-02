package Aceleracion.AppBancaria.Entidades;

import javax.persistence.*;

@Entity
public class SolicitudCuenteCorriente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public SolicitudCuenteCorriente() {
    }

    public SolicitudCuenteCorriente(long id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
