package modelo.vtaVenta.entidad;


public class CEComprobanteVentaDetalle extends CEComprobanteVentaMatriz
{
    private long IdComprobanteVentaDetalle;
    private long IdComprovanteVenta;
    private long IdPedidoDetalleRef;
    private int IdProducto;
    private String UnidadMedida;
    private String Producto;
    private String CodigoProducto;
    private String TipoDescuento;
    private int IdAlmacen;
    private int IdUnidadMedidaVenta;
    private float Cantidad;
    private float EquivalenciaBase;
    private float Precio;
    private float PrecioconDesc;
    private float SubTotal;
    private int IdTipoDescuento;
    private float Descuento;
    private float ImporteSinDescuento;
    private float DescuentoEnValores;
    private float ImporteConDescuento;
    private float Exonerado;
    private boolean SiNoImpuesto;

    /**
     * @return the IdComprobanteVentaDetalle
     */
    public long getIdComprobanteVentaDetalle() {
        return IdComprobanteVentaDetalle;
    }

    /**
     * @param IdComprobanteVentaDetalle the IdComprobanteVentaDetalle to set
     */
    public void setIdComprobanteVentaDetalle(long IdComprobanteVentaDetalle) {
        this.IdComprobanteVentaDetalle = IdComprobanteVentaDetalle;
    }

    /**
     * @return the IdComprovanteVenta
     */
    public long getIdComprovanteVenta() {
        return IdComprovanteVenta;
    }

    /**
     * @param IdComprovanteVenta the IdComprovanteVenta to set
     */
    public void setIdComprovanteVenta(long IdComprovanteVenta) {
        this.IdComprovanteVenta = IdComprovanteVenta;
    }

    /**
     * @return the IdPedidoDetalleRef
     */
    public long getIdPedidoDetalleRef() {
        return IdPedidoDetalleRef;
    }

    /**
     * @param IdPedidoDetalleRef the IdPedidoDetalleRef to set
     */
    public void setIdPedidoDetalleRef(long IdPedidoDetalleRef) {
        this.IdPedidoDetalleRef = IdPedidoDetalleRef;
    }

    /**
     * @return the IdProducto
     */
    public int getIdProducto() {
        return IdProducto;
    }

    /**
     * @param IdProducto the IdProducto to set
     */
    public void setIdProducto(int IdProducto) {
        this.IdProducto = IdProducto;
    }

    /**
     * @return the IdUnidadMedidaVenta
     */
    public int getIdUnidadMedidaVenta() {
        return IdUnidadMedidaVenta;
    }

    /**
     * @param IdUnidadMedidaVenta the IdUnidadMedidaVenta to set
     */
    public void setIdUnidadMedidaVenta(int IdUnidadMedidaVenta) {
        this.IdUnidadMedidaVenta = IdUnidadMedidaVenta;
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

    public float getEquivalenciaBase() {
        return EquivalenciaBase;
    }

    public void setEquivalenciaBase(float EquivalenciaBase) {
        this.EquivalenciaBase = EquivalenciaBase;
    }

    public float getPrecioconDesc() {
        return PrecioconDesc;
    }

    public void setPrecioconDesc(float PrecioconDesc) {
        this.PrecioconDesc = PrecioconDesc;
    }


}

