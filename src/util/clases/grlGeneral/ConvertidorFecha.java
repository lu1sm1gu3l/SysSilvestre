

package util.clases.grlGeneral;

import java.util.Calendar;
import javax.swing.JOptionPane;

public class ConvertidorFecha
{
private Calendar fecha;
private String fechaConvertida;
private String fechaSimple2;
private String fechaYMDHM;

  public String getFechaConvertida()
  {
    return fechaConvertida;
  }
  public void setFechaConvertida()
  {
    this.fechaConvertida=fecha.get(Calendar.YEAR)+
                         "/"+(fecha.get(Calendar.MONTH)+1)+
                         "/"+fecha.get(Calendar.DAY_OF_MONTH);
  }
  public Calendar getFecha()
  {
    return fecha;
  }
  public void setFecha(Calendar fecha)
  {
    
    this.fecha = fecha;
    this.setFechaConvertida();
    this.setFechaSimpleYMD();
    this.setFechaSimpleYMDHM();    
  }

  public String getFechaSimple2()
  {
    return fechaSimple2;
  }
  public String getFechaYMDHM()
  {
    return fechaYMDHM;
  }
    public String getFechaYMD()
  {
    return fechaSimple2;
  }
  public String getFechaSimpleDMY()
  {
      return fechaSimple2;
  }
  public void setFechaSimpleDMY()
  {
    if(Integer.toString(fecha.get(Calendar.DAY_OF_MONTH)).length()==1)
    {
    this.fechaSimple2 = "0"+fecha.get(Calendar.DAY_OF_MONTH)+"/"+(fecha.get(Calendar.MONTH)+1)+"/"+fecha.get(Calendar.YEAR);
    }
    else
    {
      this.fechaSimple2 = fecha.get(Calendar.DAY_OF_MONTH)+"/"+(fecha.get(Calendar.MONTH)+1)+"/"+fecha.get(Calendar.YEAR);
    }
   
    }
public void setFechaSimpleYMD()
  {
    if(Integer.toString(fecha.get(Calendar.DAY_OF_MONTH)).length()==1)
    {
    this.fechaSimple2 = fecha.get(Calendar.YEAR)+"/"+(fecha.get(Calendar.MONTH)+1)+"/"+"0"+fecha.get(Calendar.DAY_OF_MONTH);
    }
    else
    {
      this.fechaSimple2 = fecha.get(Calendar.YEAR)+"/"+(fecha.get(Calendar.MONTH)+1)+"/"+fecha.get(Calendar.DAY_OF_MONTH);
    }

    }


public void setFechaSimpleYMDHM()
  {
    if(Integer.toString(fecha.get(Calendar.DAY_OF_MONTH)).length()==1)
    {
    this.fechaYMDHM = fecha.get(Calendar.YEAR)+"/"+(fecha.get(Calendar.MONTH)+1)+"/"+"0"+fecha.get(Calendar.DAY_OF_MONTH)+
                        "/"+fecha.get(Calendar.HOUR)+":"+fecha.get(Calendar.MINUTE);
    }
    else
    {
      this.fechaYMDHM = fecha.get(Calendar.YEAR)+"/"+(fecha.get(Calendar.MONTH)+1)+"/"+fecha.get(Calendar.DAY_OF_MONTH)
                          +"/"+fecha.get(Calendar.HOUR)+":"+fecha.get(Calendar.MINUTE);
    }

  }

}
