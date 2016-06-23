/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.almAlmacen.entidad;

/**
 *
 * @author Elizabeth Manrique
 */
public class CEUnidadMedidaVenta
{
    private int IdUnidadVentaMedida;
    private String Descripcion;
    private String Abreviatura;
    private String ToString;




    /**
     * @return the IdUnidadVentaMedida
     */
    public int getIdUnidadVentaMedida() {
        return IdUnidadVentaMedida;
    }

    /**
     * @param IdUnidadVentaMedida the IdUnidadVentaMedida to set
     */
    public void setIdUnidadVentaMedida(int IdUnidadVentaMedida) {
        this.IdUnidadVentaMedida = IdUnidadVentaMedida;
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

    public String getToString() {
        return ToString;
    }

    public void setToString(String ToString) {
        this.ToString = ToString;
    }

  
    @Override
    public String toString() {

        if(ToString!=null)
        {
            return ToString;
        }
        return Abreviatura+"("+this.Descripcion+")";
    }



}
