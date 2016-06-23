/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.cmpCompra.entidad;



/**
 *
 * @author Katya
 */
public class CEComprobanteCompraMatriz extends CEComprobanteCompra
{

    private String Proveedor;
    private String Sucursal;
    private String TipoComprobante;
    private String Moneda;
    private String Direccion;
    private String Condicion;
    private String TipoDescuento;
    private String UltimoEstado;
    private String ultimaObservacion;
    private String UltimoUsuario;
    private String EstadoInventario;
    private int IdEstadoInventario;


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

    public String getTipoComprobante() {
        return TipoComprobante;
    }

    public void setTipoComprobante(String TipoComprobante) {
        this.TipoComprobante = TipoComprobante;
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

    public String getEstadoInventario() {
        return EstadoInventario;
    }

    public void setEstadoInventario(String EstadoInventario) {
        this.EstadoInventario = EstadoInventario;
    }

    public int getIdEstadoInventario() {
        return IdEstadoInventario;
    }

    public void setIdEstadoInventario(int IdEstadoInventario) {
        this.IdEstadoInventario = IdEstadoInventario;
    }

   

    @Override
    public String toString() {
        return getProveedor();
    }



    

}
