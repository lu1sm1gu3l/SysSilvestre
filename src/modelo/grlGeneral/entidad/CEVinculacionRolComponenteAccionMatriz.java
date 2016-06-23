/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.grlGeneral.entidad;

/**
 *
 * @author Administrador
 *//*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Administrador
 */
public class CEVinculacionRolComponenteAccionMatriz extends CEVinculacionRolComponenteAccion

{
            private String rol;
            private String componente;
            private String accion;

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }



    public String getComponente() {
        return componente;
    }

    public void setComponente(String componente) {
        this.componente = componente;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }




    public String toString()
    {
            return this.componente;
    }


}
