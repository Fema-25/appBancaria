package Aceleracion.AppBancaria.Entidades;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Empleado {
    @Id
    private long id;
    private String nombre;
    private String apellido;
    private String dni;
    @ManyToOne
    @JoinColumn(name = "sucursal_id")
    private Sucursal sucursal;

    public Empleado(String nombre, String apellido,String dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }
}
