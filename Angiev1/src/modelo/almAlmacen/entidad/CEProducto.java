package modelo.almAlmacen.entidad;

import java.util.List;

public class CEProducto
{


  private long IdProducto;
  private String CodigoBarra;
  private String Codigo;
  private String Descripcion ;
  private int IdSubFamilia ;
  private int IdFamilia ;
  private  int IdCategoria ;
  private  int IdRubro ;
  private int IdMarca ;
  private boolean SiNoImpuesto;
  private int ultimoNumeroCorrelativo;
  private List<CEEquivalenciaUnidad> oListEquivalencia;
  private List<CEUnidadMedidaProducto> oCEUnidadMedidaProducto;
  private int IdUnidadBase;
  private int IdUnidadMedida;
  private String almacen;
  private int IdUnidadPresentacionVenta;
  private String Marca;
  private String Familia;
  private String Subfamilia;
  private String Rubro;
  private String Categoria;
  private String UMB;
  private String UMP;
  private float precio;
  private float precio_sin_redon;
  private boolean percepcion;

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    public String getFamilia() {
        return Familia;
    }

    public void setFamilia(String Familia) {
        this.Familia = Familia;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String Marca) {
        this.Marca = Marca;
    }

    public String getRubro() {
        return Rubro;
    }

    public void setRubro(String Rubro) {
        this.Rubro = Rubro;
    }

    public String getSubfamilia() {
        return Subfamilia;
    }

    public void setSubfamilia(String Subfamilia) {
        this.Subfamilia = Subfamilia;
    }

    public String getUMB() {
        return UMB;
    }

    public void setUMB(String UMB) {
        this.UMB = UMB;
    }

    public String getUMP() {
        return UMP;
    }

    public void setUMP(String UMP) {
        this.UMP = UMP;
    }
    public String getCogidoBarra(){
        return CodigoBarra;
    }
    public void setCodigoBarra(String codigoBarra){
        this.CodigoBarra = codigoBarra;
    }
    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        this.Codigo = codigo;
    }    

    public int getIdCategoria() {
        return IdCategoria;
    }

    public String getAlmacen() {
        return almacen;
    }

    public void setAlmacen(String almacen) {
        this.almacen = almacen;
    }

    public void setIdCategoria(int id_categoria) {
        this.IdCategoria = id_categoria;
    }

    public int getIdFamilia() {
        return IdFamilia;
    }

    public void setIdFamilia(int id_familia) {
        this.IdFamilia = id_familia;
    }

    public int getIdMarca() {
        return IdMarca;
    }

    public void setIdMarca(int id_marca) {
        this.IdMarca = id_marca;
    }

    public long getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(long id_producto) {
        this.IdProducto = id_producto;
    }

    public void setIdRubro(int id_rubro) {
        this.IdRubro = id_rubro;
    }

    public int getIdSubFamilia() {
        return IdSubFamilia;
    }

    public void setIdSubFamilia(int id_subfamilia) {
        this.IdSubFamilia = id_subfamilia;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String nombre) {
        this.Descripcion = nombre;
    }

    @Override
    public String toString() {
        return Descripcion ;
    }

   
    public List<CEEquivalenciaUnidad> getoListEquivalencia() {
        return oListEquivalencia;
    }

    public void setoListEquivalencia(List<CEEquivalenciaUnidad> oListEquivalencia) {
        this.oListEquivalencia = oListEquivalencia;
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

    /**
     * @return the IdUnidadBase
     */
    public int getIdUnidadBase() {
        return IdUnidadBase;
    }

    /**
     * @param IdUnidadBase the IdUnidadBase to set
     */
    public void setIdUnidadBase(int IdUnidadBase) {
        this.IdUnidadBase = IdUnidadBase;
    }

    /**
     * @return the IdRubro
     */
    public int getIdRubro() {
        return IdRubro;
    }

    /**
     * @return the ultimoNumeroCorrelativo
     */
    public int getUltimoNumeroCorrelativo() {
        return ultimoNumeroCorrelativo;
    }

    /**
     * @param ultimoNumeroCorrelativo the ultimoNumeroCorrelativo to set
     */
    public void setUltimoNumeroCorrelativo(int ultimoNumeroCorrelativo) {
        this.ultimoNumeroCorrelativo = ultimoNumeroCorrelativo;
    }

    public List<CEUnidadMedidaProducto> getoCEUnidadMedidaProducto() {
        return oCEUnidadMedidaProducto;
    }

    public void setoCEUnidadMedidaProducto(List<CEUnidadMedidaProducto> oCEUnidadMedidaProducto) {
        this.oCEUnidadMedidaProducto = oCEUnidadMedidaProducto;
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

    /**
     * @return the precio
     */
    public float getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getPrecio_sin_redon() {
        return precio_sin_redon;
    }

    public void setPrecio_sin_redon(float precio_sin_redon) {
        this.precio_sin_redon = precio_sin_redon;
    }

    public boolean isPercepcion() {
        return percepcion;
    }

    public void setPercepcion(boolean percepcion) {
        this.percepcion = percepcion;
    }

    public int getIdUnidadMedida() {
        return IdUnidadMedida;
    }

    public void setIdUnidadMedida(int IdUnidadMedida) {
        this.IdUnidadMedida = IdUnidadMedida;
    }





}
