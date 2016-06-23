package modelo.vtaVenta.entidad;


public class CESucursal 
{
   public int IdSucursal;
   public String Descripcion; 
  
   public CESucursal() 
   {
    
   }
    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.Descripcion = descripcion;
    }

    public int getIdSucursal() {
        return IdSucursal;
    }

    public void setIdSucursal(int IdSucursal) {
        this.IdSucursal = IdSucursal;
    }


   
    public String toString()
    {
        return Descripcion;
    }

 

}
