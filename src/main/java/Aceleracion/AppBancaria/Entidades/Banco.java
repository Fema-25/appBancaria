package Aceleracion.AppBancaria.Entidades;

import javax.persistence.*;
import java.util.List;

@Entity
public class Banco {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String nombre;
    @OneToMany(mappedBy = "banco", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sucursal> sucursales;
    public Banco(String nombre, List<Sucursal> sucursales) {
        this.nombre = nombre;
        this.sucursales = sucursales;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @OneToMany(mappedBy = "banco", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Sucursal> getSucursales() {
        return sucursales;
    }

    public void setSucursales(List<Sucursal> sucursales) {
        this.sucursales = sucursales;
    }
}
