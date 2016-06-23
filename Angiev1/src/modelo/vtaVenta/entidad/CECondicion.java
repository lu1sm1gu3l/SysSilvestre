package modelo.vtaVenta.entidad;


public class CECondicion 
{
   public int IdCondicion;
   public String Descripcion; 
  
   public CECondicion() 
   {
    
   }
    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.Descripcion = descripcion;
    }

    public int getIdCondicion() {
        return IdCondicion;
    }

    public void setIdCondicion(int id_condicion) {
        this.IdCondicion = id_condicion;
    }
   
    public String toString()
    {
        return Descripcion;
    }

 

}
