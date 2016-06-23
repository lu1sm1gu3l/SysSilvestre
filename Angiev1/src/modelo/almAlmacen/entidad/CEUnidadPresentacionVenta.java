/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.almAlmacen.entidad;

/**
 *
 * @author Elizabeth Manrique
 */
public class CEUnidadPresentacionVenta {

    private int IdUnidadPresentacionVenta;
    private String Descripcion;
    private String Abreviatura;

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
     * @return the Abreviatura
     */
    public String getAbreviatura() {
        return Abreviatura;
    }

    /**
     * @param Abreviatura the Abreviatura to set
     */
    public void setAbreviatura(String Abreviatura) {
        this.Abreviatura = Abreviatura;
    }

    @Override
    public String toString() {
        return Abreviatura+"("+Descripcion+")";
    }

}
