/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.tsoTesoreria.entidad;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Luiggi
 */
public class CECuadreCaja {

    private long       IdCuadreCaja;
    private String     FechaApertura;
    private String     FechaCierre;
    private String     Fechacuadre;
    private int        IdPuntoVenta;
    private int        IdSucursal;
    private int        UltimoIdEstado;
    private String     UltimoEstado;
    private int        UltimoIdUsuario;
    private String     Observacion;
    private List<CECuadreCajaDetalle> LstCECuadreCajaDetalle;
    


    public String getFechaApertura() {
        return FechaApertura;
    }

    public void setFechaApertura(String FechaApertura) {
        this.FechaApertura = FechaApertura;
    }

    public String getFechaCierre() {
        return FechaCierre;
    }

    public void setFechaCierre(String FechaCierre) {
        this.FechaCierre = FechaCierre;
    }

    public String getFechacuadre() {
        return Fechacuadre;
    }

    public void setFechacuadre(String Fechacuadre) {
        this.Fechacuadre = Fechacuadre;
    }

    public long getIdCuadreCaja() {
        return IdCuadreCaja;
    }

    public void setIdCuadreCaja(long IdCuadreCaja) {
        this.IdCuadreCaja = IdCuadreCaja;
    }


    public int getIdPuntoVenta() {
        return IdPuntoVenta;
    }

    public void setIdPuntoVenta(int IdPuntoVenta) {
        this.IdPuntoVenta = IdPuntoVenta;
    }

    public int getIdSucursal() {
        return IdSucursal;
    }

    public void setIdSucursal(int IdSucursal) {
        this.IdSucursal = IdSucursal;
    }

    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String Observacion) {
        this.Observacion = Observacion;
    }

    public int getUltimoIdEstado() {
        return UltimoIdEstado;
    }

    public void setUltimoIdEstado(int UltimoIdEstado) {
        this.UltimoIdEstado = UltimoIdEstado;
    }

    public int getUltimoIdUsuario() {
        return UltimoIdUsuario;
    }

    public void setUltimoIdUsuario(int UltimoIdUsuario) {
        this.UltimoIdUsuario = UltimoIdUsuario;
    }

    public List<CECuadreCajaDetalle> getLstCECuadreCajaDetalle() {
        return LstCECuadreCajaDetalle;
    }

    public void setLstCECuadreCajaDetalle(List<CECuadreCajaDetalle> LstCECuadreCajaDetalle) {
        this.LstCECuadreCajaDetalle = LstCECuadreCajaDetalle;
    }


    public String getUltimoEstado() {
        return UltimoEstado;
    }

    public void setUltimoEstado(String UltimoEstado) {
        this.UltimoEstado = UltimoEstado;
    }


   




}
