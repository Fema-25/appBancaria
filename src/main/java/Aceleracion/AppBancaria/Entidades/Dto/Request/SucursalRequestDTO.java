package Aceleracion.AppBancaria.Entidades.Dto.Request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SucursalRequestDTO {
    @NotBlank(message = "Ingrese un nombre para la sucursal")
    private String nombreSucursal;
    @NotBlank(message = "ingrese una direcione para la sucursal")
    private String dirrecion;

    @NotNull(message = "por favor selecione un banco al que pertenece la sucursal")
    @Min(value = 1, message = "El id del banco debe ser mayor o igual a 1")
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
