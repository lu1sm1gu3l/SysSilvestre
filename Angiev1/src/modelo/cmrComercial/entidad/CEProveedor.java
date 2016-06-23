/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.cmrComercial.entidad;

import java.util.Date;

/**
 *
 * @author Luiggi
 */
public class CEProveedor {
   private int IdProveedor;
   private String RazonSocial;//2
   private boolean Estado;//3
   private String RUC;//4
   private String Direccion;//5
   private String Telefono;//11

   private String Correo;//13
   private String EstadoStr;//16
   private String Fax;
   private int IdSucursal;

    @Override
    public String toString() {
        return this.RazonSocial;
    }

    public int getIdSucursal() {
        return IdSucursal;
    }

    public void setIdSucursal(int IdSucursal) {
        this.IdSucursal = IdSucursal;
    }

    public int getIdProveedor() {
        return IdProveedor;
    }

    public void setIdProveedor(int IdProveedor) {
        this.IdProveedor = IdProveedor;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public boolean isEstado() {
        return Estado;
    }

    public void setEstado(boolean Estado) {
        this.Estado = Estado;
    }

    public String getEstadoStr() {
        return EstadoStr;
    }

    public void setEstadoStr(String EstadoStr) {
        this.EstadoStr = EstadoStr;
    }

    public String getFax() {
        return Fax;
    }

    public void setFax(String Fax) {
        this.Fax = Fax;
    }

    public String getRUC() {
        return RUC;
    }

    public void setRUC(String RUC) {
        this.RUC = RUC;
    }

    public String getRazonSocial() {
        return RazonSocial;
    }

    public void setRazonSocial(String RazonSocial) {
        this.RazonSocial = RazonSocial;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    

}
