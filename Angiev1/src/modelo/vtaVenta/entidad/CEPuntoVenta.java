/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.vtaVenta.entidad;

/**
 *
 * @author Luiggi
 */
public class CEPuntoVenta {

    private int IdPuntoVenta;
    private int IdSucursal;
    private int IdAlmacen;
    private String Descrpcion;
    private String Sucursal;
    private String IP;
    private boolean sinocaja;
    

    public String getDescrpcion() {
        return Descrpcion;
    }

    public void setDescrpcion(String Descrpcion) {
        this.Descrpcion = Descrpcion;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public int getIdPuntoVenta() {
        return IdPuntoVenta;
    }

    public void setIdPuntoVenta(int IdPuntoVenta) {
        this.IdPuntoVenta = IdPuntoVenta;
    }

    public int getIdSucursal() {
        return IdSucursal;
    }

    public void setIdSucursal(int IdSucursal) {
        this.IdSucursal = IdSucursal;
    }

    public boolean isSinocaja() {
        return sinocaja;
    }

    public void setSinocaja(boolean sinocaja) {
        this.sinocaja = sinocaja;
    }

    public String getSucursal() {
        return Sucursal;
    }

    public void setSucursal(String Sucursal) {
        this.Sucursal = Sucursal;
    }

 
    @Override
    public String toString() {
        return Descrpcion;
    }

    public int getIdAlmacen() {
        return IdAlmacen;
    }

    public void setIdAlmacen(int IdAlmacen) {
        this.IdAlmacen = IdAlmacen;
    }



}
