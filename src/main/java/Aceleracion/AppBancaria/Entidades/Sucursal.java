package Aceleracion.AppBancaria.Entidades;

import javax.persistence.*;
import java.util.List;
@Entity
public class Sucursal {
    @Id
    private long id;

    private String nombreSucursal;
    private String dirrecion;
    @OneToMany(mappedBy = "sucursal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Empleado> empleados;
    @OneToMany(mappedBy = "sucursal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cliente>clientes;
    @ManyToOne
    @JoinColumn(name = "banco_id")
    private Banco banco;


}
