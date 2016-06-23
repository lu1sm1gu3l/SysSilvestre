/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.almAlmacen.entidad;

import java.util.List;

/**
 *
 * @author Morales
 */
public class CETransferenciaAlmacenDetalle extends CEIngresoProductoDetalle{

    private long IdTransferenciaAlmacenDetalle;
    private long IdTransferenciaAlmacen;

    public long getIdTransferenciaAlmacen() {
        return IdTransferenciaAlmacen;
    }

    public void setIdTransferenciaAlmacen(long IdTransferenciaAlmacen) {
        this.IdTransferenciaAlmacen = IdTransferenciaAlmacen;
    }

    public long getIdTransferenciaAlmacenDetalle() {
        return IdTransferenciaAlmacenDetalle;
    }

    public void setIdTransferenciaAlmacenDetalle(long IdTransferenciaAlmacenDetalle) {
        this.IdTransferenciaAlmacenDetalle = IdTransferenciaAlmacenDetalle;
    }




}
