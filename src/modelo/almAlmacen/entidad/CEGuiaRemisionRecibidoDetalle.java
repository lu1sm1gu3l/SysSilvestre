/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.almAlmacen.entidad;

/**
 *
 * @author Luiggi
 */
public class CEGuiaRemisionRecibidoDetalle {



  private int IdGuiaRemisionRecibidoDetalle;
  private int IdGuiaRemisionRecibido;
  private long IdProducto;
  private int IdUnidadMedida;
  private float cantidad;
  private float saldoCantidad;
  private String producto;
  private String UnidadMedida;
  private String CodigoProducto;
  private int opcionAbm;

    public int getIdGuiaRemisionRecibido() {
        return IdGuiaRemisionRecibido;
    }

    public void setIdGuiaRemisionRecibido(int IdGuiaRemisionRecibido) {
        this.IdGuiaRemisionRecibido = IdGuiaRemisionRecibido;
    }

    public int getIdGuiaRemisionRecibidoDetalle() {
        return IdGuiaRemisionRecibidoDetalle;
    }

    public void setIdGuiaRemisionRecibidoDetalle(int IdGuiaRemisionRecibidoDetalle) {
        this.IdGuiaRemisionRecibidoDetalle = IdGuiaRemisionRecibidoDetalle;
    }

    public long getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(long IdProducto) {
        this.IdProducto = IdProducto;
    }

    public int getIdUnidadMedida() {
        return IdUnidadMedida;
    }

    public void setIdUnidadMedida(int IdUnidadMedida) {
        this.IdUnidadMedida = IdUnidadMedida;
    }

  

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }



    public String getUnidadMedida() {
        return UnidadMedida;
    }

    public void setUnidadMedida(String UnidadMedida) {
        this.UnidadMedida = UnidadMedida;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getCodigoProducto() {
        return CodigoProducto;
    }

    public void setCodigoProducto(String CodigoProducto) {
        this.CodigoProducto = CodigoProducto;
    }

    public int getOpcionAbm() {
        return opcionAbm;
    }

    public void setOpcionAbm(int opcionAbm) {
        this.opcionAbm = opcionAbm;
    }

    public float getSaldoCantidad() {
        return saldoCantidad;
    }

    public void setSaldoCantidad(float saldoCantidad) {
        this.saldoCantidad = saldoCantidad;
    }




}
