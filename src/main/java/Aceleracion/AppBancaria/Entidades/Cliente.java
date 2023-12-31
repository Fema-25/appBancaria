package Aceleracion.AppBancaria.Entidades;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cliente{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String password;
    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    private Boolean alta;
    @ManyToOne
    @JoinColumn(name = "sucursal_id")
    private Sucursal sucursal;
    @OneToMany(mappedBy = "cliente",cascade = CascadeType.ALL)
    private List<CajaDeAhorro>cajaDeAhorro;
    @OneToMany(mappedBy = "cliente",cascade = CascadeType.ALL)
    private List<CuentaCorriente>cuentaCorriente;

    public Cliente() {
        this.cajaDeAhorro = new ArrayList<>();
        this.cuentaCorriente = new ArrayList<>();
    }

    public Cliente(String email, String password, String nombre, String apellido, String dni, String telefono, List<CajaDeAhorro> cajaDeAhorro,List<CuentaCorriente>cuentaCorriente) {
        this.email = email;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
        this.cajaDeAhorro = cajaDeAhorro;
        this.cuentaCorriente = cuentaCorriente;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<CajaDeAhorro> getCajaDeAhorro() {
        return cajaDeAhorro;
    }

    public void setCajaDeAhorro(List<CajaDeAhorro> cajaDeAhorro) {
        this.cajaDeAhorro = cajaDeAhorro;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }
}
