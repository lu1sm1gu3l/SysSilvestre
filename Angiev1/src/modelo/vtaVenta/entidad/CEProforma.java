package modelo.vtaVenta.entidad;
import java.util.Date;
import java.util.List;

public class CEProforma extends CEPedido {

  private int IdProforma;
  private String FechaVencimiento;
  private Date Date;
  private List<CEProformaDetalle> LstProformaDetalle;

    public int getIdProforma() {
        return IdProforma;
    }

    public void setIdProforma(int IdProforma) {
        this.IdProforma = IdProforma;
    }

    public List<CEProformaDetalle> getLstProformaDetalle() {
        return LstProformaDetalle;
    }

    public void setLstProformaDetalle(List<CEProformaDetalle> LstProformaDetalle) {
        this.LstProformaDetalle = LstProformaDetalle;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }

    public String getFechaVencimiento() {
        return FechaVencimiento;
    }

    public void setFechaVencimiento(String FechaVencimiento) {
        this.FechaVencimiento = FechaVencimiento;
    }

}
