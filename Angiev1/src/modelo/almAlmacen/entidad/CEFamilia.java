/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.almAlmacen.entidad;

public class CEFamilia
{

    private int IdFamilia;
    private int IdCategoria;
    private String Descripcion;
    private String Codigo;
    public String getDescripcion()
    {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion)
    {
        this.Descripcion = Descripcion;
    }

    public int getIdCategoria()
    {
        return IdCategoria;
    }

    public void setIdCategoria(int id_categoria)
    {
        this.IdCategoria = id_categoria;
    }

    public int getIdFamilia() {
        return IdFamilia;
    }

    public void setIdFamilia(int IdFamilia) {
        this.IdFamilia = IdFamilia;
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
