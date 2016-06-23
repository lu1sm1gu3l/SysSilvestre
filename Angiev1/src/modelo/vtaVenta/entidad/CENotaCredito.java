/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.vtaVenta.entidad;

import java.util.List;

/**
 *
 * @author Katya
 */
public class CENotaCredito // extends CEComprobanteVenta
{
    private int IdNotaCredito;
    private int IdConcepto;
    private long IdComprobanteVentaRef;
    private int IdCondicion;
    private int UltimoIdEstado;
    private int IdUsuario;
    private long IdCliente;
    private int IdSucursal;
    private int IdMoneda;
    private int IdEmpleado;
    private String Fecha;
    private int IdTipoComprobante;
    private float SubtotalSinIGVNuevo;
    private float IGVNuevo;
    private float ISCNuevo;
    private float MontoAcreditar;
    private float MontoTotalAcreditar;
    private String NumeroNotaCredito;
    private String Cliente;
    private String Sucursal;
    private String TipoComprobante;
    private String Moneda;
    private String DNI;
    private String Direccion;
    private String Condicion;
    private String Empleado;
    private String UltimoEstado;
    private String ultimaObservacion;
    private float TipoCambio;
    private List<CENotaCreditoDetalle> lstCENotaCreditoDetalle;
    private float IgvActual;
    private float MontoPercepcion;
    private float TasaPercepcion;

    public String getCliente() {
        return Cliente;
    }

    public void setCliente(String Cliente) {
        this.Cliente = Cliente;
    }

    public String getCondicion() {
        return Condicion;
    }

    public String getUltimaObservacion() {
        return ultimaObservacion;
    }

    public void setUltimaObservacion(String ultimaObservacion) {
        this.ultimaObservacion = ultimaObservacion;
    }

    public String getNumeroNotaCredito() {
        return NumeroNotaCredito;
    }

    public void setNumeroNotaCredito(String NumeroNotaCredito) {
        this.NumeroNotaCredito = NumeroNotaCredito;
    }



    public void setCondicion(String Condicion) {
        this.Condicion = Condicion;
    }

    public float getTipoCambio() {
        return TipoCambio;
    }

    public void setTipoCambio(float TipoCambio) {
        this.TipoCambio = TipoCambio;
    }

    public int getIdConcepto() {
        return IdConcepto;
    }

    public void setIdConcepto(int IdConcepto) {
        this.IdConcepto = IdConcepto;
    }

    public int getIdEmpleado() {
        return IdEmpleado;
    }

    public void setIdEmpleado(int IdEmpleado) {
        this.IdEmpleado = IdEmpleado;
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

    public int getIdMoneda() {
        return IdMoneda;
    }

    public void setIdMoneda(int IdMoneda) {
        this.IdMoneda = IdMoneda;
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

    public String getTipoComprobante() {
        return TipoComprobante;
    }

    public void setTipoComprobante(String TipoComprobante) {
        this.TipoComprobante = TipoComprobante;
    }

    public String getUltimoEstado() {
        return UltimoEstado;
    }

    public void setUltimoEstado(String UltimoEstado) {
        this.UltimoEstado = UltimoEstado;
    }

    public int getIdNotaCredito() {
        return IdNotaCredito;
    }

    public void setIdNotaCredito(int IdNotaCredito) {
        this.IdNotaCredito = IdNotaCredito;
    }

    public float getIGVNuevo() {
        return IGVNuevo;
    }

    public void setIGVNuevo(float IGVNuevo) {
        this.IGVNuevo = IGVNuevo;
    }

    public float getISCNuevo() {
        return ISCNuevo;
    }

    public void setISCNuevo(float ISCNuevo) {
        this.ISCNuevo = ISCNuevo;
    }



    public float getSubtotalSinIGVNuevo() {
        return SubtotalSinIGVNuevo;
    }

    public void setSubtotalSinIGVNuevo(float SubtotalSinIGVNuevo) {
        this.SubtotalSinIGVNuevo = SubtotalSinIGVNuevo;
    }

    public long getIdComprobanteVentaRef() {
        return IdComprobanteVentaRef;
    }


    public void setIdComprobanteVentaRef(long IdComprobanteVentaRef) {
        this.IdComprobanteVentaRef = IdComprobanteVentaRef;
    }

    public int getIdCondicion() {
        return IdCondicion;
    }

    public void setIdCondicion(int IdCondicion) {
        this.IdCondicion = IdCondicion;
    }

    public int getIdTipoComprobante() {
        return IdTipoComprobante;
    }

    public void setIdTipoComprobante(int IdTipoComprobante) {
        this.IdTipoComprobante = IdTipoComprobante;
    }

    public int getUltimoIdEstado() {
        return UltimoIdEstado;
    }

    public void setUltimoIdEstado(int UltimoIdEstado) {
        this.UltimoIdEstado = UltimoIdEstado;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public long getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(long IdCliente) {
        this.IdCliente = IdCliente;
    }

    public int getIdSucursal() {
        return IdSucursal;
    }

    public void setIdSucursal(int IdSucursal) {
        this.IdSucursal = IdSucursal;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

   
    public List<CENotaCreditoDetalle> getLstCENotaCreditoDetalle() {
        return lstCENotaCreditoDetalle;
    }

    public void setLstCENotaCreditoDetalle(List<CENotaCreditoDetalle> lstCENotaCreditoDetalle) {
        this.lstCENotaCreditoDetalle = lstCENotaCreditoDetalle;
    }

    public float getIgvActual() {
        return IgvActual;
    }

    public void setIgvActual(float IgvActual) {
        this.IgvActual = IgvActual;
    }

    public float getMontoAcreditar() {
        return MontoAcreditar;
    }

    public void setMontoAcreditar(float MontoAcreditar) {
        this.MontoAcreditar = MontoAcreditar;
    }

    public float getMontoTotalAcreditar() {
        return MontoTotalAcreditar;
    }

    public void setMontoTotalAcreditar(float MontoTotalAcreditar) {
        this.MontoTotalAcreditar = MontoTotalAcreditar;
    }

    
    @Override
    public String toString() {
        return Cliente;
    }

    public float getMontoPercepcion() {
        return MontoPercepcion;
    }

    public void setMontoPercepcion(float MontoPercepcion) {
        this.MontoPercepcion = MontoPercepcion;
    }

    public float getTasaPercepcion() {
        return TasaPercepcion;
    }

    public void setTasaPercepcion(float TasaPercepcion) {
        this.TasaPercepcion = TasaPercepcion;
    }
    

}

