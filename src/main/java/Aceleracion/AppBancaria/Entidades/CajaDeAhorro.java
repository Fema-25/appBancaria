package Aceleracion.AppBancaria.Entidades;

import javax.persistence.*;
import java.math.BigDecimal;
@Entity
public class CajaDeAhorro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal saldo;
    private String cbu;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public CajaDeAhorro() {
        this.saldo = BigDecimal.valueOf(0.00);
    }

    public CajaDeAhorro(String cbu, Cliente cliente) {

        this.cbu = cbu;
        this.cliente = cliente;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
