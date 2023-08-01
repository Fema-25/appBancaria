package Aceleracion.AppBancaria.Entidades;

import javax.persistence.*;
import java.util.List;
@Entity
public class Sucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombreSucursal;
    private String dirrecion;
    @OneToMany(mappedBy = "sucursal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Empleado> empleados;
    @OneToMany(mappedBy = "sucursal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cliente>clientes;
    @OneToMany(mappedBy = "sucursal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SolicitudBaja> solicitudesBajas;
    @ManyToOne
    @JoinColumn(name = "banco_id")
    private Banco banco;

    public Sucursal() {
    }

    public Sucursal(long id, String nombreSucursal, String dirrecion, List<Empleado> empleados, List<Cliente> clientes, List<SolicitudBaja> solicitudesBajas, Banco banco) {
        this.id = id;
        this.nombreSucursal = nombreSucursal;
        this.dirrecion = dirrecion;
        this.empleados = empleados;
        this.clientes = clientes;
        this.solicitudesBajas = solicitudesBajas;
        this.banco = banco;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }

    public String getDirrecion() {
        return dirrecion;
    }

    public void setDirrecion(String dirrecion) {
        this.dirrecion = dirrecion;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }
}
