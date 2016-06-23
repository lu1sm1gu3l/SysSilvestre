/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.almAlmacen.entidad;

/**
 *
 * @author Luiggi
 */
public class CEAlmacenProducto extends CEProducto{

  private int IdAlmacenProducto;
  private float StockMinimo;
  private float StockMaximo;
  private float StockReal;
  private float CostoUnitario ;
 private float CostoRef ;
  private float Inventario;
  private int IdAlmacen;
  private String Almacen;
  private String Sucursal;
  private float Cantidad;
  private String Unidad_medida;
  private float equivalenciaUMB;
  private float ultimoCostocompra;
    public float getCostoUnitario() {
        return CostoUnitario;
    }

    public void setCostoUnitario(float CostoUnitario) {
        this.CostoUnitario = CostoUnitario;
    }

    public int getIdAlmacen() {
        return IdAlmacen;
    }

    public void setIdAlmacen(int IdAlmacen) {
        this.IdAlmacen = IdAlmacen;
    }

    public int getIdAlmacenProducto() {
        return IdAlmacenProducto;
    }

    public void setIdAlmacenProducto(int IdAlmacenProducto) {
        this.IdAlmacenProducto = IdAlmacenProducto;
    }


    public float getInventario() {
        return Inventario;
    }

    public void setInventario(float Inventario) {
        this.Inventario = Inventario;
    }

    public float getStockMaximo() {
        return StockMaximo;
    }

    public void setStockMaximo(float StockMaximo) {
        this.StockMaximo = StockMaximo;
    }

    public float getStockMinimo() {
        return StockMinimo;
    }

    public void setStockMinimo(float StockMinimo) {
        this.StockMinimo = StockMinimo;
    }

    public float getStockReal() {
        return StockReal;
    }

    public void setStockReal(float StockReal) {
        this.StockReal = StockReal;
    }

    public String getSucursal() {
        return Sucursal;
    }

    public void setSucursal(String Sucursal) {
        this.Sucursal = Sucursal;
    }

    public float getCantidad() {
        return Cantidad;
    }

    public void setCantidad(float Cantidad) {
        this.Cantidad = Cantidad;
    }

    public String getUnidad_medida() {
        return Unidad_medida;
    }

    public void setUnidad_medida(String Unidad_medida) {
        this.Unidad_medida = Unidad_medida;
    }

    public float getEquivalenciaUMB() {
        return equivalenciaUMB;
    }

    public void setEquivalenciaUMB(float equivalenciaUMB) {
        this.equivalenciaUMB = equivalenciaUMB;
    }

    public float getCostoRef() {
        return CostoRef;
    }

    public void setCostoRef(float CostoRef) {
        this.CostoRef = CostoRef;
    }

    public float getUltimoCostocompra() {
        return ultimoCostocompra;
    }

    public void setUltimoCostocompra(float ultimoCostocompra) {
        this.ultimoCostocompra = ultimoCostocompra;
    }


    @Override
    public String toString()
    {
      return getDescripcion();
    }

}
