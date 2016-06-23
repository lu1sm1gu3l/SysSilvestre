package modelo.almAlmacen.entidad;

public class CEEquivalenciaUnidad {

    private long IdEquivalenciaUnidad;
    private int IdProducto;
    private int IdUnidadBase;
    private float CantidadBase;
    private int IdUnidadPedido;
    private float CantidadPedido;
    private float precioBase;
    private String UnidadBase;
    private String UnidadPedido;

    public long getIdEquivalenciaUnidad() {
        return IdEquivalenciaUnidad;
    }

    public void setIdEquivalenciaUnidad(long IdEquivalencia) {
        this.IdEquivalenciaUnidad = IdEquivalencia;
    }

    public int getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(int IdProducto) {
        this.IdProducto = IdProducto;
    }

    public float getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(float precioBase) {
        this.precioBase = precioBase;
    }

    public int getIdUnidadBase() {
        return IdUnidadBase;
    }

    public void setIdUnidadBase(int IdUnidadBase) {
        this.IdUnidadBase = IdUnidadBase;
    }

    public float getCantidadBase() {
        return CantidadBase;
    }

    public void setCantidadBase(float CantidadBase) {
        this.CantidadBase = CantidadBase;
    }

    /**
     * @return the IdUnidadPedido
     */
    public int getIdUnidadPedido() {
        return IdUnidadPedido;
    }

    /**
     * @param IdUnidadPedido the IdUnidadPedido to set
     */
    public void setIdUnidadPedido(int IdUnidadPedido) {
        this.IdUnidadPedido = IdUnidadPedido;
    }

    /**
     * @return the CantidadPedido
     */
    public float getCantidadPedido() {
        return CantidadPedido;
    }

    /**
     * @param CantidadPedido the CantidadPedido to set
     */
    public void setCantidadPedido(float CantidadPedido) {
        this.CantidadPedido = CantidadPedido;
    }

    /**
     * @return the UnidadBase
     */
    public String getUnidadBase() {
        return UnidadBase;
    }

    /**
     * @param UnidadBase the UnidadBase to set
     */
    public void setUnidadBase(String UnidadBase) {
        this.UnidadBase = UnidadBase;
    }

    /**
     * @return the UnidadEquivalencia
     */
    public String getUnidadEquivalencia() {
        return UnidadPedido;
    }

    /**
     * @param UnidadEquivalencia the UnidadEquivalencia to set
     */
    public void setUnidadEquivalencia(String UnidadEquivalencia) {
        this.UnidadPedido = UnidadEquivalencia;
    }

    @Override
    public String toString() {
        return  UnidadPedido ;
    }



}
