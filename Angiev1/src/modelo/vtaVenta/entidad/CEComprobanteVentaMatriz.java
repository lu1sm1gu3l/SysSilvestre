/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.vtaVenta.entidad;



/**
 *
 * @author Katya
 */
public class CEComprobanteVentaMatriz extends CEComprobanteVenta
{
    private String Sucursal;
    private String Moneda;
    private String DNI;
    private String Direccion;
    private String Condicion;
    private String TipoDescuento;
    private String Empleado;
    private String Cobrador;
    private String UltimoEstado;
    private String ultimaObservacion;
    private String UltimoUsuario;



    public String getCondicion() {
        return Condicion;
    }

    public String getUltimaObservacion() {
        return ultimaObservacion;
    }

    public void setUltimaObservacion(String ultimaObservacion) {
        this.ultimaObservacion = ultimaObservacion;
    }



    public void setCondicion(String Condicion) {
        this.Condicion = Condicion;
    }



    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getEmpleado() {
        return Empleado;
    }

    public void setEmpleado(String Empleado) {
        this.Empleado = Empleado;
    }

    public String getMoneda() {
        return Moneda;
    }

    public void setMoneda(String Moneda) {
        this.Moneda = Moneda;
    }

    public String getSucursal() {
        return Sucursal;
    }

    public void setSucursal(String Sucursal) {
        this.Sucursal = Sucursal;
    }


    public String getTipoDescuento() {
        return TipoDescuento;
    }

    public void setTipoDescuento(String TipoDescuento) {
        this.TipoDescuento = TipoDescuento;
    }

    public String getUltimoEstado() {
        return UltimoEstado;
    }

    public void setUltimoEstado(String UltimoEstado) {
        this.UltimoEstado = UltimoEstado;
    }

    public String getUltimoUsuario() {
        return UltimoUsuario;
    }

    public void setUltimoUsuario(String UltimoUsuario) {
        this.UltimoUsuario = UltimoUsuario;
    }

    @Override
    public String toString() {
        return getCliente();
    }

    public String getCobrador() {
        return Cobrador;
    }

    public void setCobrador(String Cobrador) {
        this.Cobrador = Cobrador;
    }




    

}
