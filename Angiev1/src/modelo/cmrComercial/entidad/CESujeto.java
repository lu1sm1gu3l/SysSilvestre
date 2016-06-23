package modelo.cmrComercial.entidad;

import java.util.Date;


public class CESujeto 
{
   private String NombreCompleto;//2 
   private boolean Estado;//3
   private String DNI;//4
   private String Direccion;//5
   private String Codigo;//6
   private String ApellidoPaterno;//7
   private String ApellidoMaterno;//8
   private String Nombres;//9
   private String Sexo;//10
  
   private String Telefono;//11
   private String RUC;//12
  
   private String Correo;//13
   private int IdTipoPersona;//15
   private String EstadoStr;//16
   private String FechaNacimiento;//17
   private Date FechaNacimientoDate;//18
   private String Celular;

    public String getCelular() {
        return Celular;
    }

    public void setCelular(String Celular) {
        this.Celular = Celular;
    }

   

    public Date getFechaNacimientoDate() {
        return FechaNacimientoDate;
    }

    public void setFechaNacimientoDate(Date FechaNacimientoDate) {
        this.FechaNacimientoDate = FechaNacimientoDate;
    }

    public String getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(String FechaNacimiento) {
        this.FechaNacimiento = FechaNacimiento;
    }

 

    public String getEstadoStr() {
        return EstadoStr;
    }

    public void setEstadoStr(String EstadoStr) {
        this.EstadoStr = EstadoStr;
    }



    public boolean isEstado() {
        return Estado;
    }

    public void setEstado(boolean Estado) {
        this.Estado = Estado;
    }
    
   /**
   @roseuid 4DEE714200B3
    */
   public CESujeto()
   {

   }
    public String getApellidoMaterno() {
        return ApellidoMaterno;
    }

    public void setApellidoMaterno(String ApellidoMaterno) {
        this.ApellidoMaterno = ApellidoMaterno;
    }

    public String getApellidoPaterno() {
        return ApellidoPaterno;
    }

    public void setApellidoPaterno(String ApellidoPaterno) {
        this.ApellidoPaterno = ApellidoPaterno;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

 

 
    public String getNombreCompleto() {
        return NombreCompleto;
    }
    public void setNombreCompleto(String NombreCompleto) {
        this.NombreCompleto = NombreCompleto;
    }


    public int getIdTipoPersona() {
        return IdTipoPersona;
    }

    public void setIdTipoPersona(int IdTipoPersona) {
        this.IdTipoPersona = IdTipoPersona;
    }



    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String Nombres) {
        this.Nombres = Nombres;
    }

    public String getRUC() {
        return RUC;
    }

    public void setRUC(String RUC) {
        this.RUC = RUC;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String Sexo) {
        this.Sexo = Sexo;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    @Override
    public String toString() {
        return  NombreCompleto ;
    }

   


    

   
}
