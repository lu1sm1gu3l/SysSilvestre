/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.cmpCompra.entidad;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Katya
 */
public class CENotaCreditoProveedor // extends CEComprobanteVenta
{
    private long IdNotaCreditoProveedor;
    private int IdConcepto;
    private long IdComprobanteCompraRef;
    private int IdCondicion;
    private int UltimoIdEstado;
    private int IdUsuario;
    private int IdProveedor;
    private int IdSucursal;
    private int IdMoneda;
    private int IdEmpleado;
    private String Fecha;
    private String Observacion;
    private int IdTipoComprobante;
    private float SubtotalSinIGVNuevo;
    private float IGVNuevo;
    private float ISCNuevo;
    private float MontoAcreditar;
    private float MontoPercepcion;
    private float MontoTotalAcreditar;
    private float MontoTotalAcreditarAnterior;
    private String NumeroNotaCreditoProveedor;
    private String Proveedor;
    private String Sucursal;
    private String TipoComprobante;
    private String Moneda;
    private String RUC;
    private String Direccion;
    private String Condicion;
    private String Empleado;
    private String UltimoEstado;
    private Date date;

    public String getProveedor() {
        return Proveedor;
    }

    public void setProveedor(String Proveedor) {
        this.Proveedor = Proveedor;
    }
    private String ultimaObservacion;
    private float TipoCambio;
    private List<CENotaCreditoProveedorDetalle> lstCENotaCreditoProveedorDetalle;
    private float IgvActual;

   

    public String getCondicion() {
        return Condicion;
    }

    public String getUltimaObservacion() {
        return ultimaObservacion;
    }

    public void setUltimaObservacion(String ultimaObservacion) {
        this.ultimaObservacion = ultimaObservacion;
    }

    public String getNumeroNotaCreditoProveedor() {
        return NumeroNotaCreditoProveedor;
    }

    public void setNumeroNotaCreditoProveedor(String NumeroNotaCreditoProveedor) {
        this.NumeroNotaCreditoProveedor = NumeroNotaCreditoProveedor;
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

    public String getRUC() {
        return RUC;
    }

    public void setRUC(String RUC) {
        this.RUC = RUC;
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

    public long getIdNotaCreditoProveedor() {
        return IdNotaCreditoProveedor;
    }

    public void setIdNotaCreditoProveedor(long IdNotaCreditoProveedor) {
        this.IdNotaCreditoProveedor = IdNotaCreditoProveedor;
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

    public float getMontoTotalAcreditar() {
        return MontoTotalAcreditar;
    }

    public void setMontoTotalAcreditar(float MontoTotalAcreditar) {
        this.MontoTotalAcreditar = MontoTotalAcreditar;
    }

    public float getSubtotalSinIGVNuevo() {
        return SubtotalSinIGVNuevo;
    }

    public void setSubtotalSinIGVNuevo(float SubtotalSinIGVNuevo) {
        this.SubtotalSinIGVNuevo = SubtotalSinIGVNuevo;
    }

    public int getIdProveedor() {
        return IdProveedor;
    }

    public void setIdProveedor(int IdProveedor) {
        this.IdProveedor = IdProveedor;
    }


    public long getIdComprobanteCompraRef() {
        return IdComprobanteCompraRef;
    }

    public void setIdComprobanteCompraRef(long IdComprobanteCompraRef) {
        this.IdComprobanteCompraRef = IdComprobanteCompraRef;
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

   
    public List<CENotaCreditoProveedorDetalle> getLstCENotaCreditoProveedorDetalle() {
        return lstCENotaCreditoProveedorDetalle;
    }

    public void setLstCENotaCreditoProveedorDetalle(List<CENotaCreditoProveedorDetalle> lstCENotaCreditoProveedorDetalle) {
        this.lstCENotaCreditoProveedorDetalle = lstCENotaCreditoProveedorDetalle;
    }

    public float getIgvActual() {
        return IgvActual;
    }

    public void setIgvActual(float IgvActual) {
        this.IgvActual = IgvActual;
    }

    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String Observacion) {
        this.Observacion = Observacion;
    }

    @Override
    public String toString() {
        return Proveedor;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getMontoTotalAcreditarAnterior() {
        return MontoTotalAcreditarAnterior;
    }

    public void setMontoTotalAcreditarAnterior(float MontoTotalAcreditarAnterior) {
        this.MontoTotalAcreditarAnterior = MontoTotalAcreditarAnterior;
    }

    public float getMontoAcreditar() {
        return MontoAcreditar;
    }

    public void setMontoAcreditar(float MontoAcreditar) {
        this.MontoAcreditar = MontoAcreditar;
    }

    public float getMontoPercepcion() {
        return MontoPercepcion;
    }

    public void setMontoPercepcion(float MontoPercepcion) {
        this.MontoPercepcion = MontoPercepcion;
    }
    

}
