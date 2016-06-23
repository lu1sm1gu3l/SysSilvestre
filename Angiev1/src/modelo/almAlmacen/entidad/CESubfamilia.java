/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.almAlmacen.entidad;

/**
 *
 * @author Katya
 */
public class CESubfamilia {

    private int IdSubFamilia;
    private int IdFamilia;
    private String Descripcion;
    private String Codigo;
    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public int getIdFamilia() {
        return IdFamilia;
    }

    public void setIdFamilia(int IdFamilia) {
        this.IdFamilia = IdFamilia;
    }

  

    public int getIdSubFamilia() {
        return IdSubFamilia;
    }

    public void setIdSubFamilia(int IdSubFamilia) {
        this.IdSubFamilia = IdSubFamilia;
    }

  

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }
public String toString() {
        return Descripcion ;
    }

}
