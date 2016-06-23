/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.almAlmacen.entidad;

/**
 *
 * @author Elizabeth Manrique
 */
public class CETipoIngresoSalida
{
    private int IdTipoIngresoSalida;
    private String Descripcion;
    private String Abreviatura;

    public int getIdTipoIngresoSalida() {
        return IdTipoIngresoSalida;
    }

    public void setIdTipoIngresoSalida(int IdTipoIngresoSalida) {
        this.IdTipoIngresoSalida = IdTipoIngresoSalida;
    }

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

    return Descripcion;
  
    }



}
