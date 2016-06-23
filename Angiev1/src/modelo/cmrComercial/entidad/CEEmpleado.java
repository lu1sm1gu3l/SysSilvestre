/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.cmrComercial.entidad;

/**
 *
 * @author Luiggi
 */
public class CEEmpleado extends CESujeto{

    private String Sucursal;
    private int IdEmpleado;
    private int IdSucursal;
    public int getIdEmpleado() {
        return IdEmpleado;
    }

    public void setIdEmpleado(int IdEmpleado) {
        this.IdEmpleado = IdEmpleado;
    }

    public int getIdSucursal() {
        return IdSucursal;
    }

    public void setIdSucursal(int IdSucursal) {
        this.IdSucursal = IdSucursal;
    }

    /**
     * @return the Sucursal
     */
    public String getSucursal() {
        return Sucursal;
    }

    /**
     * @param Sucursal the Sucursal to set
     */
    public void setSucursal(String Sucursal) {
        this.Sucursal = Sucursal;
    }



}
