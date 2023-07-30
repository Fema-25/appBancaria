package Aceleracion.AppBancaria.Entidades;

import javax.persistence.Entity;
import java.math.BigDecimal;


public class CuentaCorriente {
    private long id;
    private BigDecimal saldo;
    private String cbu;
}
