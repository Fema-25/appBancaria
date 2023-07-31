package Aceleracion.AppBancaria.Entidades.Dto.Request;

public class SucursalRequestDTO {
    private String nombreSucursal;
    private String dirrecion;
    private long idBanco;

    public SucursalRequestDTO() {
    }

    public SucursalRequestDTO(String nombreSucursal, String dirrecion, long idBanco) {
        this.nombreSucursal = nombreSucursal;
        this.dirrecion = dirrecion;
        this.idBanco = idBanco;
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

    public long getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(long idBanco) {
        this.idBanco = idBanco;
    }
}
