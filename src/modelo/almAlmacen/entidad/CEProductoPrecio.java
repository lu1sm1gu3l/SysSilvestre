package modelo.almAlmacen.entidad;

public class CEProductoPrecio
{
    private long IdProductoPrecio;
    private long IdUnidadMedidaProducto;
    private int IdUnidadMedidaVenta;
    private long IdProducto;
    private float RangoInicial;
    private float RangoFinal;
    private String RangoFaltante;
    private float PrecioUnitario;
    private String TipoUnidad;
    private int abm;


    /**
     * @return the IdProductoPrecio
     */
    public long getIdProductoPrecio() {
        return IdProductoPrecio;
    }

    /**
     * @param IdProductoPrecio the IdProductoPrecio to set
     */
    public void setIdProductoPrecio(long IdProductoPrecio) {
        this.IdProductoPrecio = IdProductoPrecio;
    }

    /**
     * @return the IdUnidadMedidaProducto
     */
    public long getIdUnidadMedidaProducto() {
        return IdUnidadMedidaProducto;
    }

    /**
     * @param IdUnidadMedidaProducto the IdUnidadMedidaProducto to set
     */
    public void setIdUnidadMedidaProducto(long IdUnidadMedidaProducto) {
        this.IdUnidadMedidaProducto = IdUnidadMedidaProducto;
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
     * @return the RangoInicial
     */
    public float getRangoInicial() {
        return RangoInicial;
    }

    public void setRangoInicial(float RangoInicial) {
        this.RangoInicial = RangoInicial;
    }

    public float getRangoFinal() {
        return RangoFinal;
    }

    public void setRangoFinal(float RangoFinal) {
        this.RangoFinal = RangoFinal;
    }

    public String getRangoFaltante() {
        return RangoFaltante;
    }

    public void setRangoFaltante(String RangoFaltante) {
        this.RangoFaltante = RangoFaltante;
    }


    public float getPrecioUnitario() {
        return PrecioUnitario;
    }


    public void setPrecioUnitario(float PrecioUnitario) {
        this.PrecioUnitario = PrecioUnitario;
    }

    /**
     * @return the TipoUnidad
     */
    public String getTipoUnidad() {
        return TipoUnidad;
    }

    /**
     * @param TipoUnidad the TipoUnidad to set
     */
    public void setTipoUnidad(String TipoUnidad) {
        this.TipoUnidad = TipoUnidad;
    }

    /**
     * @return the abm
     */
    public int getAbm() {
        return abm;
    }

    /**
     * @param abm the abm to set
     */
    public void setAbm(int abm) {
        this.abm = abm;
    }

}
