//Source file: E:\\Felix Gilberto cama Beltran\\CACHUELOS\\DCL\\CETipoComprobante.java

package modelo.vtaVenta.entidad;


public class CETipoDescuento 
{
   public int IdTipoDescuento;
   public String descripcion;
 
   
   /**
   @roseuid 4DE96BC20178
    */
   public CETipoDescuento() 
   {
    
   }

    public int getIdTipoDescuento() {
        return IdTipoDescuento;
    }

    public void setIdTipoDescuento(int IdTipoDescuento) {
        this.IdTipoDescuento = IdTipoDescuento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public String toString()
    {
        return descripcion;
    }

 

}
