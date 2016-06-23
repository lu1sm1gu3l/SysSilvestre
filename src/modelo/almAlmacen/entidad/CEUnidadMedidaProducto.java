package modelo.almAlmacen.entidad;

public class CEUnidadMedidaProducto
{
    private long IdUnidadMedidaProducto;
    private long IdProducto;
    private int IdUnidadMedidaVenta;
    private int SiNoUnidadMasComercial;
    private int Correlativo;
    private int SiNoUnidadBase;
    private float EquivalenciaConBase;
    private String Descripcion;
    private String TipoUnidad;
    private float NumUnidades;
    private int IdUnidadPresentacionVenta;
    private int abm;
    private float PrecioReferencia;
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
     * @return the Correlativo
     */
    public int getCorrelativo() {
        return Correlativo;
    }

    /**
     * @param Correlativo the Correlativo to set
     */
    public void setCorrelativo(int Correlativo) {
        this.Correlativo = Correlativo;
    }

    /**
     * @return the SiNoUnidadBase
     */
    public int getSiNoUnidadBase() {
        return SiNoUnidadBase;
    }

    /**
     * @param SiNoUnidadBase the SiNoUnidadBase to set
     */
    public void setSiNoUnidadBase(int SiNoUnidadBase) {
        this.SiNoUnidadBase = SiNoUnidadBase;
    }

    /**
     * @return the EquivalenciaConBase
     */
    public float getEquivalenciaConBase() {
        return EquivalenciaConBase;
    }

    /**
     * @param EquivalenciaConBase the EquivalenciaConBase to set
     */
    public void setEquivalenciaConBase(float EquivalenciaConBase) {
        this.EquivalenciaConBase = EquivalenciaConBase;
    }

    /**
     * @return the Descripcion
     */
    public String getDescripcion() {
        return Descripcion;
    }

    /**
     * @param Descripcion the Descripcion to set
     */
    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
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

    @Override
    public String toString(){
        return TipoUnidad ;
    }

    /**
     * @return the SiNoUnidadMasComercial
     */
    public int getSiNoUnidadMasComercial() {
        return SiNoUnidadMasComercial;
    }

    /**
     * @param SiNoUnidadMasComercial the SiNoUnidadMasComercial to set
     */
    public void setSiNoUnidadMasComercial(int SiNoUnidadMasComercial) {
        this.SiNoUnidadMasComercial = SiNoUnidadMasComercial;
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

    /**
     * @return the NumUnidades
     */
    public float getNumUnidades() {
        return NumUnidades;
    }

    /**
     * @param NumUnidades the NumUnidades to set
     */
    public void setNumUnidades(float NumUnidades) {
        this.NumUnidades = NumUnidades;
    }

    /**
     * @return the IdUnidadPresentacionVenta
     */
    public int getIdUnidadPresentacionVenta() {
        return IdUnidadPresentacionVenta;
    }

    /**
     * @param IdUnidadPresentacionVenta the IdUnidadPresentacionVenta to set
     */
    public void setIdUnidadPresentacionVenta(int IdUnidadPresentacionVenta) {
        this.IdUnidadPresentacionVenta = IdUnidadPresentacionVenta;
    }

    public float getPrecioReferencia() {
        return PrecioReferencia;
    }

    public void setPrecioReferencia(float PrecioReferencia) {
        this.PrecioReferencia = PrecioReferencia;
    }



   

}
