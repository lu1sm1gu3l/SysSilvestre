/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.cmpCompra.entidad;

/**
 *
 * @author Katya
 */
public class CENotaCreditoProveedorDetalle {

    private int IdNotaCreditoProveedorDetalle;
    private int IdNotaCreditoProveedor;
    private long IdComprobanteDetalleRef;
    private long IdProducto;
    private int IdAlmacen;
    private int IdUnidadMedidaVenta;
    private float PrecioNuevo;
    private float ImporteNuevo;
    private float cantidadNueva;
    private float ImporteAnterior;
    private float ProporcionDescuentoTotal;
    private float Exonerado;
    private boolean sinoImpuesto;
    private int accionAbm;
    public int getIdNotaCreditoProveedor() {
        return IdNotaCreditoProveedor;
    }

    public void setIdNotaCreditoProveedor(int IdNotaCreditoProveedor) {
        this.IdNotaCreditoProveedor = IdNotaCreditoProveedor;
    }

    public int getIdNotaCreditoProveedorDetalle() {
        return IdNotaCreditoProveedorDetalle;
    }

    public void setIdNotaCreditoProveedorDetalle(int IdNotaCreditoProveedorDetalle) {
        this.IdNotaCreditoProveedorDetalle = IdNotaCreditoProveedorDetalle;
    }

    public float getImporteNuevo() {
        return ImporteNuevo;
    }

    public float getProporcionDescuentoTotal() {
        return ProporcionDescuentoTotal;
    }

    public void setProporcionDescuentoTotal(float ProporcionDescuentoTotal) {
        this.ProporcionDescuentoTotal = ProporcionDescuentoTotal;
    }

    public float getImporteAnterior() {
        return ImporteAnterior;
    }

    public int getIdAlmacen() {
        return IdAlmacen;
    }

    public void setIdAlmacen(int IdAlmacen) {
        this.IdAlmacen = IdAlmacen;
    }

    public long getIdComprobanteDetalleRef() {
        return IdComprobanteDetalleRef;
    }

    public void setIdComprobanteDetalleRef(long IdComprobanteDetalleRef) {
        this.IdComprobanteDetalleRef = IdComprobanteDetalleRef;
    }

    public long getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(long IdProducto) {
        this.IdProducto = IdProducto;
    }

    public int getIdUnidadMedidaVenta() {
        return IdUnidadMedidaVenta;
    }

    public void setIdUnidadMedidaVenta(int IdUnidadMedidaVenta) {
        this.IdUnidadMedidaVenta = IdUnidadMedidaVenta;
    }


    public void setImporteAnterior(float ImporteAnterior) {
        this.ImporteAnterior = ImporteAnterior;
    }

    public void setImporteNuevo(float ImporteNuevo) {
        this.ImporteNuevo = ImporteNuevo;
    }

    public float getPrecioNuevo() {
        return PrecioNuevo;
    }

    public void setPrecioNuevo(float PrecioNuevo) {
        this.PrecioNuevo = PrecioNuevo;
    }

    public float getCantidadNueva() {
        return cantidadNueva;
    }

    public void setCantidadNueva(float cantidadNueva) {
        this.cantidadNueva = cantidadNueva;
    }

    public float getExonerado() {
        return Exonerado;
    }

    public void setExonerado(float Exonerado) {
        this.Exonerado = Exonerado;
    }

    public boolean isSinoImpuesto() {
        return sinoImpuesto;
    }

    public void setSinoImpuesto(boolean sinoImpuesto) {
        this.sinoImpuesto = sinoImpuesto;
    }

    public int getAccionAbm() {
        return accionAbm;
    }

    public void setAccionAbm(int accionAbm) {
        this.accionAbm = accionAbm;
    } 


}
