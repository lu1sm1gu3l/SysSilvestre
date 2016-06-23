package modelo.cmpCompra.entidad;


public class CEComprobanteCompraDetalle
{
    private long IdComprobanteCompraDetalle;
    private long IdComprovanteCompra;
    private long IdOrdenCompraDetalleRef;
    private long IdProducto;
    private String UnidadMedida;
    private String Producto;
    private String CodigoProducto;
    private String TipoDescuento;
    private int IdAlmacen;
    private int IdUnidadMedidaCompra;
    private float Cantidad;
    private float Precio;
    private float SubTotal;
    private int IdTipoDescuento;
    private float Descuento;
    private float ImporteSinDescuento;
    private float ImporteSinDescuentoConIgv;
    private float DescuentoEnValores;
    private float ImporteConDescuento;
    private float Exonerado;
    private boolean SiNoImpuesto;
    private float SaldoCantidad;
    private float PrecioConDesc;
    private int IdAbm;
    private int IdNotaCreditoProvAux;

    /**
     * @return the IdComprobanteCompraDetalle
     */
    public long getIdComprobanteCompraDetalle() {
        return IdComprobanteCompraDetalle;
    }

    /**
     * @param IdComprobanteCompraDetalle the IdComprobanteCompraDetalle to set
     */
    public void setIdComprobanteCompraDetalle(long IdComprobanteCompraDetalle) {
        this.IdComprobanteCompraDetalle = IdComprobanteCompraDetalle;
    }

    /**
     * @return the IdComprovanteCompra
     */
    public long getIdComprovanteCompra() {
        return IdComprovanteCompra;
    }

    /**
     * @param IdComprovanteCompra the IdComprovanteCompra to set
     */
    public void setIdComprovanteCompra(long IdComprovanteCompra) {
        this.IdComprovanteCompra = IdComprovanteCompra;
    }

    /**
     * @return the IdOrdenCompraDetalleRef
     */
    public long getIdOrdenCompraDetalleRef() {
        return IdOrdenCompraDetalleRef;
    }

    /**
     * @param IdOrdenCompraDetalleRef the IdOrdenCompraDetalleRef to set
     */
    public void setIdOrdenCompraDetalleRef(long IdOrdenCompraDetalleRef) {
        this.IdOrdenCompraDetalleRef = IdOrdenCompraDetalleRef;
    }

    /**
     * @return the IdProducto
     */
    public long getIdProducto() {
        return IdProducto;
    }

    /**
     * @param IdProducto the IdProducto to set
     */
    public void setIdProducto(long IdProducto) {
        this.IdProducto = IdProducto;
    }

    /**
     * @return the IdUnidadMedidaCompra
     */
    public int getIdUnidadMedidaCompra() {
        return IdUnidadMedidaCompra;
    }

    /**
     * @param IdUnidadMedidaCompra the IdUnidadMedidaCompra to set
     */
    public void setIdUnidadMedidaCompra(int IdUnidadMedidaCompra) {
        this.IdUnidadMedidaCompra = IdUnidadMedidaCompra;
    }

    /**
     * @return the Cantidad
     */
    public float getCantidad() {
        return Cantidad;
    }

    /**
     * @param Cantidad the Cantidad to set
     */
    public void setCantidad(float Cantidad) {
        this.Cantidad = Cantidad;
    }

    /**
     * @return the Precio
     */
    public float getPrecio() {
        return Precio;
    }

    public String getTipoDescuento() {
        return TipoDescuento;
    }

    public void setTipoDescuento(String TipoDescuento) {
        this.TipoDescuento = TipoDescuento;
    }

    /**
     * @param Precio the Precio to set
     */
    public void setPrecio(float Precio) {
        this.Precio = Precio;
    }

    /**
     * @return the SubTotal
     */
    public float getSubTotal() {
        return SubTotal;
    }

    /**
     * @param SubTotal the SubTotal to set
     */
    public void setSubTotal(float SubTotal) {
        this.SubTotal = SubTotal;
    }

    /**
     * @return the IdTipoDescuento
     */
    public int getIdTipoDescuento() {
        return IdTipoDescuento;
    }

    /**
     * @param IdTipoDescuento the IdTipoDescuento to set
     */
    public void setIdTipoDescuento(int IdTipoDescuento) {
        this.IdTipoDescuento = IdTipoDescuento;
    }

    /**
     * @return the Descuento
     */
    public float getDescuento() {
        return Descuento;
    }

    /**
     * @param Descuento the Descuento to set
     */
    public void setDescuento(float Descuento) {
        this.Descuento = Descuento;
    }


    /**
     * @return the ImporteSinDescuento
     */
    public float getImporteSinDescuento() {
        return ImporteSinDescuento;
    }

    /**
     * @param ImporteSinDescuento the ImporteSinDescuento to set
     */
    public void setImporteSinDescuento(float ImporteSinDescuento) {
        this.ImporteSinDescuento = ImporteSinDescuento;
    }

    /**
     * @return the ImporteConDescuento
     */
    public float getDescuentoEnValores() {
        return DescuentoEnValores;
    }

    /**
     * @param ImporteConDescuento the ImporteConDescuento to set
     */
    public void setDescuentoEnValores(float ImporteConDescuento) {
        this.DescuentoEnValores = ImporteConDescuento;
    }

    /**
     * @return the Producto
     */
    public String getProducto() {
        return Producto;
    }

    /**
     * @param Producto the Producto to set
     */
    public void setProducto(String Producto) {
        this.Producto = Producto;
    }

    /**
     * @return the CodigoProducto
     */
    public String getCodigoProducto() {
        return CodigoProducto;
    }

    /**
     * @param CodigoProducto the CodigoProducto to set
     */
    public void setCodigoProducto(String CodigoProducto) {
        this.CodigoProducto = CodigoProducto;
    }

    /**
     * @return the IdAlmacen
     */
    public int getIdAlmacen() {
        return IdAlmacen;
    }

    /**
     * @param IdAlmacen the IdAlmacen to set
     */
    public void setIdAlmacen(int IdAlmacen) {
        this.IdAlmacen = IdAlmacen;
    }

    /**
     * @return the UnidadMedida
     */
    public String getUnidadMedida() {
        return UnidadMedida;
    }

    /**
     * @param UnidadMedida the UnidadMedida to set
     */
    public void setUnidadMedida(String UnidadMedida) {
        this.UnidadMedida = UnidadMedida;
    }

    /**
     * @return the ImporteConDescuento
     */
    public float getImporteConDescuento() {
        return ImporteConDescuento;
    }

    /**
     * @param ImporteConDescuento the ImporteConDescuento to set
     */
    public void setImporteConDescuento(float ImporteConDescuento) {
        this.ImporteConDescuento = ImporteConDescuento;
    }

    /**
     * @return the Exonerado
     */
    public float getExonerado() {
        return Exonerado;
    }

    /**
     * @param Exonerado the Exonerado to set
     */
    public void setExonerado(float Exonerado) {
        this.Exonerado = Exonerado;
    }

    /**
     * @return the SiNoImpuesto
     */
    public boolean isSiNoImpuesto() {
        return SiNoImpuesto;
    }

    /**
     * @param SiNoImpuesto the SiNoImpuesto to set
     */
    public void setSiNoImpuesto(boolean SiNoImpuesto) {
        this.SiNoImpuesto = SiNoImpuesto;
    }

    public float getSaldoCantidad() {
        return SaldoCantidad;
    }

    public void setSaldoCantidad(float SaldoCantidad) {
        this.SaldoCantidad = SaldoCantidad;
    }

    public float getPrecioConDesc() {
        return PrecioConDesc;
    }

    public void setPrecioConDesc(float PrecioConDesc) {
        this.PrecioConDesc = PrecioConDesc;
    }

    public float getImporteSinDescuentoConIgv() {
        return ImporteSinDescuentoConIgv;
    }

    public void setImporteSinDescuentoConIgv(float ImporteSinDescuentoConIgv) {
        this.ImporteSinDescuentoConIgv = ImporteSinDescuentoConIgv;
    }

    public int getIdAbm() {
        return IdAbm;
    }

    public void setIdAbm(int IdAbm) {
        this.IdAbm = IdAbm;
    }

@Override
    public String toString() {
        return ""+CodigoProducto;
    }

    public int getIdNotaCreditoProvAux() {
        return IdNotaCreditoProvAux;
    }

    public void setIdNotaCreditoProvAux(int IdNotaCreditoProvAux) {
        this.IdNotaCreditoProvAux = IdNotaCreditoProvAux;
    }


}
