package Aceleracion.AppBancaria.Entidades.Dto.Request;

public class BancoRequestDTO {
    private String Nombre;

    public BancoRequestDTO() {
    }

    public BancoRequestDTO(String nombre) {
        Nombre = nombre;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }
}
