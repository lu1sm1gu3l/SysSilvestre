/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.grlGeneral.entidad;

/**
 *
 * @author Administrador
 */
public class CEVinculacionRolComponenteAccion

{
  private int IdRolCompoenteAccion;
  private int IdRol;
  private int IdComponenteAccion;
  private int IdComponente;
  private int IdAccion;
  private String Descripcion;

    public int getIdRol() {
        return IdRol;
    }

    public void setIdRol(int IdRol) {
        this.IdRol = IdRol;
    }

    public int getIdRolCompoenteAccion() {
        return IdRolCompoenteAccion;
    }

    public void setIdRolCompoenteAccion(int IdRolCompoenteAccion) {
        this.IdRolCompoenteAccion = IdRolCompoenteAccion;
    }


    public int getIdComponenteAccion() {
        return IdComponenteAccion;
    }

    public void setIdComponenteAccion(int IdComponenteAccion) {
        this.IdComponenteAccion = IdComponenteAccion;
    }

    public int getIdAccion() {

        return IdAccion;
    }

    public void setIdAccion(int IdAccion) {
        this.IdAccion = IdAccion;
    }

    public int getIdComponente() {
        return IdComponente;
    }

    public void setIdComponente(int IdComponente) {
        this.IdComponente = IdComponente;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String toString()
        {
            return this.Descripcion;
        }
}
