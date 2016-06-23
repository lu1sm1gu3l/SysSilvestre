/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.cmrComercial.entidad;

/**
 *
 * @author Luiggi
 */
public class CECliente extends CESujeto{
  private long IdCliente;
  private String Codigo;
  private int IdSucursal;

    public long getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(long id_cliente) {
        this.IdCliente = id_cliente;
    }

    public int getIdSucursal() {
        return IdSucursal;
    }

    public void setIdSucursal(int id_sucursal) {
        this.IdSucursal = id_sucursal;
    }

    

    @Override
    public String toString() {
        return getNombreCompleto();
    }

    /**
     * @return the Codigo
     */
    public String getCodigo() {
        return Codigo;
    }

    /**
     * @param Codigo the Codigo to set
     */
    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }



}
