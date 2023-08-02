package Aceleracion.AppBancaria.Entidades;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class CuentaCorriente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private BigDecimal saldo = BigDecimal.valueOf(0.00);
    private String cbu;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public CuentaCorriente() {
    }

    public CuentaCorriente(long id, BigDecimal saldo, String cbu, Cliente cliente) {
        this.id = id;
        this.saldo = saldo;
        this.cbu = cbu;
        this.cliente = cliente;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
