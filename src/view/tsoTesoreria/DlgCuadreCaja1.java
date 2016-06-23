/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgCuadreCaja.java
 *
 * Created on 29/09/2011, 09:49:35 AM
 */

package view.tsoTesoreria;

import controller.tsoTesoreria.CCCuadreCaja;
import controller.vtaVenta.CCPuntoVenta;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import modelo.tsoTesoreria.entidad.CECuadreCaja;
import modelo.tsoTesoreria.entidad.CECuadreCajaDetalle;
import modelo.tsoTesoreria.entidad.CECuadreCajaTipoPago;
import modelo.vtaVenta.entidad.CEPuntoVenta;
import util.clases.almAlmacen.JTableX;
import util.clases.grlGeneral.CLComboBox;
import util.clases.grlGeneral.CLConsultarFechaSistema;
import util.clases.grlGeneral.CLRedondear;
import util.clases.grlGeneral.ConvertidorFecha;
import util.clases.grlGeneral.VerificadorDeTxt;
import view.FrmSistemaMenu;
import view.vtaVenta.rptVTAReportes.view.DlgReporteCierreCaja;

/**
 *
 * @author Luiggi
 */
public class DlgCuadreCaja1 extends javax.swing.JDialog {
    private CECuadreCaja oCECuadreCaja;

    /** Creates new form DlgCuadreCaja */
    public DlgCuadreCaja1(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        CbxCaja.setModel(CLComboBox.cargarCombo(CCPuntoVenta.listarCaja(FrmSistemaMenu.getIdSucursal())));
        DtChFecha.setDateFormatString(CLConsultarFechaSistema.consultarFechaYMD());
        CargarCuadreCajaTipoPago();
        jTextFieldNumber.setDocument(new VerificadorDeTxt("Numero",0,jTextFieldNumber));
        TblCuadreCajaDetalle.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        TblCuadreCajaTipoPago.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        TableColumn columnaMtoTeso = TblCuadreCajaTipoPago.getColumnModel().getColumn(colMtoTeso);
        columnaMtoTeso.setCellEditor(new DefaultCellEditor(jTextFieldNumber));
        ((JTableX)TblCuadreCajaTipoPago).setSelectAllForEdit(true);
        TblCuadreCajaTipoPago.setSurrendersFocusOnKeystroke(true);
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        TblCuadreCajaTipoPago.getColumnModel().getColumn(colMtoTeso).setCellRenderer(tcr);
        TblCuadreCajaTipoPago.getColumnModel().getColumn(colMtoDif).setCellRenderer(tcr);
        TblCuadreCajaTipoPago.getColumnModel().getColumn(colMtoCuadre).setCellRenderer(tcr);
        TblCuadreCajaDetalle.getColumnModel().getColumn(3).setCellRenderer(tcr);
        TblCuadreCajaDetalle.getColumnModel().getColumn(4).setCellRenderer(tcr);
        TblCuadreCajaDetalle.getColumnModel().getColumn(5).setCellRenderer(tcr);
    }

    int iddetTem=0;
     private void CargarCuadreCajaTipoPago()
    {
         iddetTem=0;
        limpiarControles();
        CEPuntoVenta oCEPuntoVenta=(CEPuntoVenta)CbxCaja.getSelectedItem();
        if(DtChFecha.getDate()!=null&&oCEPuntoVenta!=null){
            ConvertidorFecha ocConvertidorFecha= new ConvertidorFecha();
            ocConvertidorFecha.setFecha(DtChFecha.getCalendar());
            ocConvertidorFecha.setFechaSimpleYMD();
            String pFecha=ocConvertidorFecha.getFechaSimple2();


            oCECuadreCaja=CCCuadreCaja.ObtenerCuadreCajaCierre(oCEPuntoVenta.getIdPuntoVenta(), pFecha);
            if(oCECuadreCaja!=null){
                LblfechaApertura.setText(oCECuadreCaja.getFechaApertura());
                LblfechaCierre.setText(oCECuadreCaja.getFechaCierre());
                LblFechaCuadre.setText(oCECuadreCaja.getFechacuadre());
                LblEstado.setText(oCECuadreCaja.getUltimoEstado());
                int i=0;
                
                for (CECuadreCajaDetalle oCECuadreCajaDetalle : oCECuadreCaja.getLstCECuadreCajaDetalle())
                            {
                                
                                Vector oVector=new Vector();
                                if(oCECuadreCajaDetalle.getIdCuadreCajaDetalle()==0)
                                {
                                    iddetTem --;
                                    oCECuadreCajaDetalle.setIdCuadreCajaDetalle(iddetTem);
                                }
                                i++;
                                oVector.add(i);
                                oVector.add(oCECuadreCajaDetalle);
                                oVector.add(oCECuadreCajaDetalle.getMontoEfectivoInicial());
                                oVector.add(oCECuadreCajaDetalle.getMontoTotalCuadre());
                                oVector.add(oCECuadreCajaDetalle.getMontoTotalTesoreria());
                                oVector.add(oCECuadreCajaDetalle.getMontoTotalDiferencia());
                                ((DefaultTableModel)TblCuadreCajaDetalle.getModel()).addRow(oVector);
                            }

                List<CECuadreCajaTipoPago> listacuadreCajaTipoPago=CCCuadreCaja.listarIngresoTipoPago(pFecha, oCEPuntoVenta.getIdPuntoVenta());
                i=0;
                    for (CECuadreCajaTipoPago oCECuadreCajaTipoPago : listacuadreCajaTipoPago)
                        {
                            oCECuadreCajaTipoPago.setIdCuadreCajaDetalle(ObtenerIdCuadreCajaDetalle(oCECuadreCajaTipoPago.getIdMoneda()));

                            if(oCECuadreCajaTipoPago.getIdTipoPago()!=0)
                                {
                                    i++;
                                    Vector oVector=new Vector();

                                       oVector.add(i);
                                       oVector.add(oCECuadreCajaTipoPago.getMoneda());
                                       oVector.add(oCECuadreCajaTipoPago);
                                       oVector.add(oCECuadreCajaTipoPago.getMontoCuadre());
                                       oVector.add(oCECuadreCajaTipoPago.getMontoTesoreria());
                                       oVector.add(oCECuadreCajaTipoPago.getMontoDiferencia());
                                       ((DefaultTableModel)TblCuadreCajaTipoPago.getModel()).addRow(oVector);
                                }
                        }
                        obtenerMonedas();
            }else{
            LblEstado.setText("NO INICIADO");}
        }
    }
private int colNumTipoPago=0;
private int colMonTipoPago=1;
private int colTipoPago=2;
private int colMtoCuadre=3;
private int colMtoTeso=4;
private int colMtoDif=5;

private long ObtenerIdCuadreCajaDetalle(int idMoneda)
{
                for (int i=0;i<TblCuadreCajaDetalle.getRowCount();i++) {
                CECuadreCajaDetalle oCECuadreCajaDetalle= (CECuadreCajaDetalle)TblCuadreCajaDetalle.getValueAt(i, 1);
                if(idMoneda==oCECuadreCajaDetalle.getIdMoneda())
                {
                 return oCECuadreCajaDetalle.getIdCuadreCajaDetalle();   
                }
                }

    return 0;

}
    private void obtenerMonedas()
    {
            for (int i=0;i<TblCuadreCajaTipoPago.getRowCount();i++) {
                CECuadreCajaTipoPago oCECuadreCajaTipoPago=(CECuadreCajaTipoPago)TblCuadreCajaTipoPago.getValueAt(i, colTipoPago);
                existeIdMoneda(oCECuadreCajaTipoPago);
                }
            for (int i=0;i<TblCuadreCajaDetalle.getRowCount();i++) {
                CECuadreCajaDetalle oCECuadreCajaDetalle= (CECuadreCajaDetalle)TblCuadreCajaDetalle.getValueAt(i, 1);
                    BigDecimal MontoCuadre=new BigDecimal(0);
                    for (int j=0;j<TblCuadreCajaTipoPago.getRowCount();j++) {
                         CECuadreCajaTipoPago oCECuadreCajaTipoPago=(CECuadreCajaTipoPago)TblCuadreCajaTipoPago.getValueAt(j, colTipoPago);
                        if(oCECuadreCajaDetalle.getIdMoneda()==oCECuadreCajaTipoPago.getIdMoneda())
                        {
                         MontoCuadre=MontoCuadre.add(oCECuadreCajaTipoPago.getMontoCuadre());
                        }
                    }
                TblCuadreCajaDetalle.setValueAt(MontoCuadre, i, 3);
                }
    }
    private void existeIdMoneda(CECuadreCajaTipoPago oCECuadreCajaTipoPago)
    {
        boolean existe=false;
        for (int i=0;i<TblCuadreCajaDetalle.getRowCount();i++) {
            CECuadreCajaDetalle oCECuadreCajaDetalle= (CECuadreCajaDetalle)TblCuadreCajaDetalle.getValueAt(i, 1);
            if(oCECuadreCajaDetalle.getIdMoneda()==oCECuadreCajaTipoPago.getIdMoneda()){

                existe= true;
                break;
            }
        }
        CECuadreCajaDetalle oCECuadreCajaDetalle;
        if(!existe)
        {
            Vector oVector=new Vector();
            oCECuadreCajaDetalle= new CECuadreCajaDetalle();
            iddetTem --;
            oCECuadreCajaDetalle.setIdCuadreCajaDetalle(iddetTem);
            actualizarIdCuadreCajaDetalle(oCECuadreCajaTipoPago.getIdMoneda(), iddetTem);
            oCECuadreCajaDetalle.setIdMoneda(oCECuadreCajaTipoPago.getIdMoneda());
            oCECuadreCajaDetalle.setMoneda(oCECuadreCajaTipoPago.getMoneda());
            oVector.add(TblCuadreCajaDetalle.getRowCount()+1);
            oVector.add(oCECuadreCajaDetalle);
            oVector.add(null);
            ((DefaultTableModel)TblCuadreCajaDetalle.getModel()).addRow(oVector);
        }

    }
    private void actualizarIdCuadreCajaDetalle(int idmoneda,int idCuadreCajaDetalle)
    {

        int row=TblCuadreCajaTipoPago.getRowCount();
        if(row!=0){
            for(int i=0;i<row;i++)
            {
                CECuadreCajaTipoPago oCECuadreCajaTipoPago=(CECuadreCajaTipoPago)TblCuadreCajaTipoPago.getValueAt(i, 2);
                if(oCECuadreCajaTipoPago.getIdMoneda()==idmoneda)
                {
                 oCECuadreCajaTipoPago.setIdCuadreCajaDetalle(idCuadreCajaDetalle);
                }
            }
        }
    }
    private void calcularMontoDiferencia(int fila)
    {
         BigDecimal MontoCuadre=new BigDecimal(TblCuadreCajaTipoPago.getValueAt(fila, colMtoCuadre).toString());
         BigDecimal MontoTeso =new BigDecimal(0);
         BigDecimal Montodif =new BigDecimal(0);
         if(TblCuadreCajaTipoPago.getValueAt(fila, colMtoTeso)!=null&&TblCuadreCajaTipoPago.getValueAt(fila, colMtoTeso).toString().trim().length()!=0)
         {
           String cadena =TblCuadreCajaTipoPago.getValueAt(fila, colMtoTeso).toString().trim();
             MontoTeso =new BigDecimal(TblCuadreCajaTipoPago.getValueAt(fila, colMtoTeso).toString());

         }
         Montodif=MontoCuadre.subtract(MontoTeso);
         TblCuadreCajaTipoPago.setValueAt(Montodif,fila, colMtoDif);
    }
    private void CalcularTotalMonedas()
    {


        for (int i=0;i<TblCuadreCajaDetalle.getRowCount();i++){
             CECuadreCajaDetalle oCECuadreCajaDetalle= (CECuadreCajaDetalle)TblCuadreCajaDetalle.getValueAt(i, 1);
                    BigDecimal MontoCuadre=new BigDecimal(0);
                    BigDecimal MontoDif=new BigDecimal(0);
                    for (int j=0;j<TblCuadreCajaTipoPago.getRowCount();j++) {
                         CECuadreCajaTipoPago oCECuadreCajaTipoPago=(CECuadreCajaTipoPago)TblCuadreCajaTipoPago.getValueAt(j, colTipoPago);
                        if(oCECuadreCajaDetalle.getIdMoneda()==oCECuadreCajaTipoPago.getIdMoneda()&&oCECuadreCajaTipoPago.getIdTipoPago()==1)
                        {
                            if(TblCuadreCajaTipoPago.getValueAt(j, colMtoTeso)!=null&&TblCuadreCajaTipoPago.getValueAt(j, colMtoTeso).toString().trim().length()!=0)
                             {
                               MontoCuadre =MontoCuadre.add(new BigDecimal(TblCuadreCajaTipoPago.getValueAt(j, colMtoTeso).toString()));
                             }


                            if(TblCuadreCajaTipoPago.getValueAt(j, colMtoDif)!=null)
                             {
                                 MontoDif =MontoDif.add(new BigDecimal(TblCuadreCajaTipoPago.getValueAt(j, colMtoDif).toString()));
                             }
                        }
                    }
                TblCuadreCajaDetalle.setValueAt(MontoCuadre, i, 4);
                TblCuadreCajaDetalle.setValueAt(MontoDif, i, 5);
                }
    }

    private void limpiarControles()
    {
        LblfechaApertura.setText("");
        LblfechaCierre.setText("");
        LblFechaCuadre.setText("");
        LblEstado.setText("");
        limpiarTablaDetalle();
        limpiarTablaTipoPago();
    }
    private void limpiarTablaDetalle()
    {
         int size =TblCuadreCajaDetalle.getRowCount();
         if(size!=0){
            for(int i=0;i<size;i++)
            {
              ((DefaultTableModel)TblCuadreCajaDetalle.getModel()).removeRow(0);
            }
         }
     }
    private void limpiarTablaTipoPago()
    {

         int size =TblCuadreCajaTipoPago.getRowCount();
         if(size!=0){
            for(int i=0;i<size;i++)
            {
              ((DefaultTableModel)TblCuadreCajaTipoPago.getModel()).removeRow(0);
            }
         }
     }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextFieldNumber = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblCuadreCajaTipoPago =  new util.clases.almAlmacen.JTableX()
        {
            @Override
            public void setValueAt(Object value,int row,int column)
            {
                try{
                    if(column==colMtoTeso)
                    {
                        if(!value.toString().trim().equals("")&&value!=null&&!value.toString().isEmpty())
                        {
                            super.setValueAt(CLRedondear.RedondearString(Float.parseFloat(value.toString()),2),row,column);
                        }
                        else
                        {
                            super.setValueAt("0.00",row,column);
                        }

                    }
                    else
                    {
                        super.setValueAt(value,row,column);
                    }

                }
                catch(Exception e)
                {
                    super.setValueAt(null,row,column);
                }
            }

        }
        ;
        jPanel1 = new javax.swing.JPanel();
        label2 = new Label.Label();
        label3 = new Label.Label();
        LblfechaApertura = new Label.Label();
        LblfechaCierre = new Label.Label();
        label8 = new Label.Label();
        LblEstado = new Label.Label();
        label10 = new Label.Label();
        LblFechaCuadre = new Label.Label();
        jScrollPane2 = new javax.swing.JScrollPane();
        TblCuadreCajaDetalle = new util.clases.almAlmacen.JTableX()
        {
            @Override
            public void setValueAt(Object value,int row,int column)
            {
                try{
                    if(column==3||column==4||column==5)
                    {
                        if(!value.toString().trim().equals("")&&value!=null&&!value.toString().isEmpty())
                        {
                            super.setValueAt(CLRedondear.RedondearString(Float.parseFloat(value.toString()),2),row,column);
                        }
                        else
                        {
                            super.setValueAt("0.00",row,column);
                        }

                    }
                    else
                    {
                        super.setValueAt(value,row,column);
                    }

                }
                catch(Exception e)
                {
                    super.setValueAt(null,row,column);
                }
            }

        }

        ;
        label6 = new Label.Label();
        jSeparator1 = new javax.swing.JSeparator();
        label7 = new Label.Label();
        jPanel2 = new javax.swing.JPanel();
        label12 = new Label.Label();
        DtChFecha =  new com.toedter.calendar.JDateChooser("dd/MM/yyyy","##/##/####",'_');
        jLabel7 = new javax.swing.JLabel();
        CbxCaja = new ComboBox.ComboBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        TblCuadreCajaTipoPago1 =  new util.clases.almAlmacen.JTableX()
        {
            @Override
            public void setValueAt(Object value,int row,int column)
            {
                try{
                    if(column==colMtoTeso)
                    {
                        if(!value.toString().trim().equals("")&&value!=null&&!value.toString().isEmpty())
                        {
                            super.setValueAt(CLRedondear.RedondearString(Float.parseFloat(value.toString()),2),row,column);
                        }
                        else
                        {
                            super.setValueAt("0.00",row,column);
                        }

                    }
                    else
                    {
                        super.setValueAt(value,row,column);
                    }

                }
                catch(Exception e)
                {
                    super.setValueAt(null,row,column);
                }
            }

        }
        ;
        btnAceptar1 = new BotonesABM.BtnAceptar();
        BtnReportar1 = new BotonesABM.BtnEditar();
        btnCancelar1 = new BotonesABM.BtnCancelar();
        jSeparator2 = new javax.swing.JSeparator();
        label9 = new Label.Label();

        jTextFieldNumber.setFont(new java.awt.Font("Verdana", 0, 12));
        jTextFieldNumber.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldNumber.setBorder(null);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cuadre de Caja");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        TblCuadreCajaTipoPago.setFont(new java.awt.Font("Verdana", 0, 12));
        TblCuadreCajaTipoPago.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº", "Moneda", "Tipo Pago", "Monto Cuadre", "Monto Tesoreria", "Monto Diferencia"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Float.class, java.lang.Object.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblCuadreCajaTipoPago.setRowHeight(20);
        TblCuadreCajaTipoPago.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        TblCuadreCajaTipoPago.getTableHeader().setReorderingAllowed(false);
        TblCuadreCajaTipoPago.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                TblCuadreCajaTipoPagoPropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(TblCuadreCajaTipoPago);
        TblCuadreCajaTipoPago.getColumnModel().getColumn(0).setResizable(false);
        TblCuadreCajaTipoPago.getColumnModel().getColumn(0).setPreferredWidth(10);
        TblCuadreCajaTipoPago.getColumnModel().getColumn(1).setResizable(false);
        TblCuadreCajaTipoPago.getColumnModel().getColumn(1).setPreferredWidth(75);
        TblCuadreCajaTipoPago.getColumnModel().getColumn(2).setResizable(false);
        TblCuadreCajaTipoPago.getColumnModel().getColumn(2).setPreferredWidth(95);
        TblCuadreCajaTipoPago.getColumnModel().getColumn(3).setResizable(false);
        TblCuadreCajaTipoPago.getColumnModel().getColumn(3).setPreferredWidth(95);
        TblCuadreCajaTipoPago.getColumnModel().getColumn(4).setResizable(false);
        TblCuadreCajaTipoPago.getColumnModel().getColumn(4).setPreferredWidth(95);
        TblCuadreCajaTipoPago.getColumnModel().getColumn(5).setResizable(false);
        TblCuadreCajaTipoPago.getColumnModel().getColumn(5).setPreferredWidth(95);

        jPanel1.setBackground(java.awt.SystemColor.info);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Datos Generales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N

        label2.setForeground(new java.awt.Color(153, 0, 0));
        label2.setText("Fecha Apertura :");
        label2.setFont(new java.awt.Font("Verdana", 1, 12));

        label3.setForeground(new java.awt.Color(153, 0, 0));
        label3.setText("Fecha Cierre :");
        label3.setFont(new java.awt.Font("Verdana", 1, 12));

        LblfechaApertura.setText("27/09/2011 05:26:32 p.m");

        LblfechaCierre.setText("27/09/2011 05:26:32 p.m");

        label8.setForeground(new java.awt.Color(153, 0, 0));
        label8.setText("Estado           :");
        label8.setFont(new java.awt.Font("Verdana", 1, 12));

        LblEstado.setText("ABIERTO");
        LblEstado.setFont(new java.awt.Font("Verdana", 1, 12));

        label10.setForeground(new java.awt.Color(153, 0, 0));
        label10.setText("Fecha Cuadre    :");
        label10.setFont(new java.awt.Font("Verdana", 1, 12));

        LblFechaCuadre.setText("27/09/2011 05:26:32 p.m");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(label10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblFechaCuadre, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
                    .addComponent(LblfechaApertura, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(LblEstado, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
                        .addGap(2, 2, 2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(LblfechaCierre, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LblfechaApertura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LblFechaCuadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LblfechaCierre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        TblCuadreCajaDetalle.setFont(new java.awt.Font("Verdana", 0, 12));
        TblCuadreCajaDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº", "Moneda", "Efectivo Inicial", "Mto Total Cuadre", "Mto Total Tesoreria", "Mto Total Diferencia"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblCuadreCajaDetalle.setRowHeight(20);
        jScrollPane2.setViewportView(TblCuadreCajaDetalle);
        TblCuadreCajaDetalle.getColumnModel().getColumn(0).setResizable(false);
        TblCuadreCajaDetalle.getColumnModel().getColumn(0).setPreferredWidth(10);
        TblCuadreCajaDetalle.getColumnModel().getColumn(1).setResizable(false);
        TblCuadreCajaDetalle.getColumnModel().getColumn(1).setPreferredWidth(100);
        TblCuadreCajaDetalle.getColumnModel().getColumn(2).setResizable(false);
        TblCuadreCajaDetalle.getColumnModel().getColumn(2).setPreferredWidth(100);
        TblCuadreCajaDetalle.getColumnModel().getColumn(3).setResizable(false);
        TblCuadreCajaDetalle.getColumnModel().getColumn(3).setPreferredWidth(95);
        TblCuadreCajaDetalle.getColumnModel().getColumn(4).setResizable(false);
        TblCuadreCajaDetalle.getColumnModel().getColumn(4).setPreferredWidth(95);
        TblCuadreCajaDetalle.getColumnModel().getColumn(5).setResizable(false);
        TblCuadreCajaDetalle.getColumnModel().getColumn(5).setPreferredWidth(95);

        label6.setText("[MONTOS TOTALES POR MONEDA EN EFECTIVO]");

        label7.setText("[INGRESOS POR TIPO DE PAGO ]");

        jPanel2.setBackground(java.awt.SystemColor.info);
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Busqueda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N

        label12.setForeground(new java.awt.Color(153, 0, 0));
        label12.setText("Caja    : ");
        label12.setFont(new java.awt.Font("Verdana", 1, 12));

        DtChFecha.setAutoscrolls(true);
        DtChFecha.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        DtChFecha.setDate(new Date());
        DtChFecha.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                DtChFechaPropertyChange(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 12));
        jLabel7.setText("Fecha :");

        CbxCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxCajaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CbxCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(DtChFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(571, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(CbxCaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))
                    .addComponent(label12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DtChFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TblCuadreCajaTipoPago1.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        TblCuadreCajaTipoPago1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº", "Moneda", "Tipo Pago", "Monto Cuadre", "Monto Tesoreria", "Monto Diferencia"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Float.class, java.lang.Object.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblCuadreCajaTipoPago1.setRowHeight(20);
        TblCuadreCajaTipoPago1.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        TblCuadreCajaTipoPago1.getTableHeader().setReorderingAllowed(false);
        TblCuadreCajaTipoPago1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                TblCuadreCajaTipoPago1PropertyChange(evt);
            }
        });
        jScrollPane3.setViewportView(TblCuadreCajaTipoPago1);
        TblCuadreCajaTipoPago1.getColumnModel().getColumn(0).setResizable(false);
        TblCuadreCajaTipoPago1.getColumnModel().getColumn(0).setPreferredWidth(10);
        TblCuadreCajaTipoPago1.getColumnModel().getColumn(1).setResizable(false);
        TblCuadreCajaTipoPago1.getColumnModel().getColumn(1).setPreferredWidth(75);
        TblCuadreCajaTipoPago1.getColumnModel().getColumn(2).setResizable(false);
        TblCuadreCajaTipoPago1.getColumnModel().getColumn(2).setPreferredWidth(95);
        TblCuadreCajaTipoPago1.getColumnModel().getColumn(3).setResizable(false);
        TblCuadreCajaTipoPago1.getColumnModel().getColumn(3).setPreferredWidth(95);
        TblCuadreCajaTipoPago1.getColumnModel().getColumn(4).setResizable(false);
        TblCuadreCajaTipoPago1.getColumnModel().getColumn(4).setPreferredWidth(95);
        TblCuadreCajaTipoPago1.getColumnModel().getColumn(5).setResizable(false);
        TblCuadreCajaTipoPago1.getColumnModel().getColumn(5).setPreferredWidth(95);

        btnAceptar1.setText("Guardar Cuadre");
        btnAceptar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptar1ActionPerformed(evt);
            }
        });

        BtnReportar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/agt_print.png"))); // NOI18N
        BtnReportar1.setText("Reportar");
        BtnReportar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnReportar1ActionPerformed(evt);
            }
        });

        btnCancelar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/exit.png"))); // NOI18N
        btnCancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar1ActionPerformed(evt);
            }
        });

        label9.setText("[EGRESOS POR TIPO DE PAGO]");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jSeparator1))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(label9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)))))
                            .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(218, 218, 218)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAceptar1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BtnReportar1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(343, 343, 343))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAceptar1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnReportar1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptar1ActionPerformed

        btnAceptar1.requestFocus();
        if(oCECuadreCaja!=null){
        if(oCECuadreCaja.getUltimoIdEstado()!=7){
               int res = JOptionPane.showConfirmDialog(this, "¿Esta seguro que desea Guardar?", "Dialogo de Confirmación", JOptionPane.YES_NO_OPTION);
                if (res == JOptionPane.YES_OPTION)
                {
                    List<CECuadreCajaDetalle> listaCuadreCajaMoneda=new ArrayList<CECuadreCajaDetalle>();
                    CECuadreCajaDetalle oCECuadreCajaDetalle;
                    for(int i=0; i<TblCuadreCajaDetalle.getRowCount();i++)
                    {
                         CECuadreCajaDetalle oCECuadreCajaDetTemp= (CECuadreCajaDetalle)TblCuadreCajaDetalle.getValueAt(i, 1);
                         
                         BigDecimal MontoCuadre=new BigDecimal(0);
                         BigDecimal MontoTeso =new BigDecimal(0);
                         BigDecimal Montodif =new BigDecimal(0);
                         BigDecimal Montoinicial =new BigDecimal(0);

                         if(TblCuadreCajaDetalle.getValueAt(i, 2)!=null)
                         {
                             Montoinicial =new BigDecimal(TblCuadreCajaDetalle.getValueAt(i, 2).toString());
                         }
                         if(TblCuadreCajaDetalle.getValueAt(i, 3)!=null)
                         {
                             MontoCuadre =new BigDecimal(TblCuadreCajaDetalle.getValueAt(i, 3).toString());
                         }
                         if(TblCuadreCajaDetalle.getValueAt(i, 4)!=null)
                         {
                             MontoTeso =new BigDecimal(TblCuadreCajaDetalle.getValueAt(i, 4).toString());
                         }
                         if(TblCuadreCajaDetalle.getValueAt(i, 5)!=null)
                         {
                             Montodif =new BigDecimal(TblCuadreCajaDetalle.getValueAt(i, 5).toString());
                         }
                         oCECuadreCajaDetalle= new CECuadreCajaDetalle();
                         oCECuadreCajaDetalle.setIdMoneda(oCECuadreCajaDetTemp.getIdMoneda());
                         oCECuadreCajaDetalle.setMontoTotalCuadre(MontoCuadre);
                         oCECuadreCajaDetalle.setMontoEfectivoInicial(Montoinicial);
                         oCECuadreCajaDetalle.setMontoTotalDiferencia(Montodif);
                         oCECuadreCajaDetalle.setMontoTotalTesoreria(MontoTeso);
                         oCECuadreCajaDetalle.setIdCuadreCajaDetalle(oCECuadreCajaDetTemp.getIdCuadreCajaDetalle());

                          List<CECuadreCajaTipoPago> listaCuadreCajaTipoPago=new ArrayList<CECuadreCajaTipoPago>();
                         for(int j=0; j<TblCuadreCajaTipoPago.getRowCount();j++)
                        {

                         CECuadreCajaTipoPago oCECuadreCajaTipoPago=(CECuadreCajaTipoPago)TblCuadreCajaTipoPago.getValueAt(j, colTipoPago);

                                 if(oCECuadreCajaTipoPago.getIdCuadreCajaDetalle()==oCECuadreCajaDetalle.getIdCuadreCajaDetalle())
                                 {
                                     BigDecimal MontoCuadreTemp=new BigDecimal(TblCuadreCajaTipoPago.getValueAt(j, colMtoCuadre).toString());
                                     BigDecimal MontoTesoTemp =new BigDecimal(0);
                                     BigDecimal MontodifTemp =new BigDecimal(0);
                                     if(TblCuadreCajaTipoPago.getValueAt(j, 3)!=null)
                                     {
                                         MontoCuadreTemp =new BigDecimal(TblCuadreCajaTipoPago.getValueAt(j, colMtoCuadre).toString());
                                     }
                                     if(TblCuadreCajaTipoPago.getValueAt(j, 4)!=null)
                                     {
                                         MontoTesoTemp =new BigDecimal(TblCuadreCajaTipoPago.getValueAt(j, colMtoTeso).toString());
                                     }
                                     if(TblCuadreCajaTipoPago.getValueAt(j, 5)!=null)
                                     {
                                         MontodifTemp =new BigDecimal(TblCuadreCajaTipoPago.getValueAt(j, colMtoDif).toString());
                                     }
                                     oCECuadreCajaTipoPago.setMontoCuadre(MontoCuadreTemp);
                                     oCECuadreCajaTipoPago.setMontoTesoreria(MontoTesoTemp);
                                     oCECuadreCajaTipoPago.setMontoDiferencia(MontodifTemp);
                                     listaCuadreCajaTipoPago.add(oCECuadreCajaTipoPago);
                                }
                        }
                         oCECuadreCajaDetalle.setLstCECuadreCajaTipoPago(listaCuadreCajaTipoPago);
                         listaCuadreCajaMoneda.add(oCECuadreCajaDetalle);

                    }
                    oCECuadreCaja.setLstCECuadreCajaDetalle(listaCuadreCajaMoneda);

                     if(CCCuadreCaja.UPDCuadreCaja(oCECuadreCaja)==1)
                    {
                        JOptionPane.showMessageDialog(null,"Se Guardo con exito");
                        LblEstado.setText("CERRADO");
                        oCECuadreCaja.setUltimoIdEstado(7);
                    }else
                    {
                        JOptionPane.showMessageDialog(null,"No se realizo la operación");
                    }
                    

                }
                    
                    


                }
         else
             {
                JOptionPane.showMessageDialog(null,"No se puede realizar la operación, la Caja ya se cerró");
             }
        }
        else
             {
                JOptionPane.showMessageDialog(null,"No se puede realizar la operación, la Caja no esta aperturada");
             }


    }//GEN-LAST:event_btnAceptar1ActionPerformed
 private boolean validar()
 {

     return false;
 }
    private void DtChFechaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_DtChFechaPropertyChange

         if(!iscerrando){
            CargarCuadreCajaTipoPago();
        }
        
    }//GEN-LAST:event_DtChFechaPropertyChange

    private void BtnReportar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnReportar1ActionPerformed
    DlgReporteCierreCaja dialogo=new DlgReporteCierreCaja(null, true);
    dialogo.setLocationRelativeTo(null);
    dialogo.setVisible(true);
    }//GEN-LAST:event_BtnReportar1ActionPerformed

    private void CbxCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxCajaActionPerformed
      CargarCuadreCajaTipoPago();
    }//GEN-LAST:event_CbxCajaActionPerformed

    private void btnCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar1ActionPerformed
      dispose();
    }//GEN-LAST:event_btnCancelar1ActionPerformed

    private void TblCuadreCajaTipoPagoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_TblCuadreCajaTipoPagoPropertyChange
       if(!iscerrando){
           
                int fila=TblCuadreCajaTipoPago.getSelectedRow();
                if(fila!=-1){
                    calcularMontoDiferencia(fila);
                    CalcularTotalMonedas();
                }
        }

    }//GEN-LAST:event_TblCuadreCajaTipoPagoPropertyChange
    boolean iscerrando=false;
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
         iscerrando=true;

    }//GEN-LAST:event_formWindowClosing

    private void TblCuadreCajaTipoPago1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_TblCuadreCajaTipoPago1PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_TblCuadreCajaTipoPago1PropertyChange

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private BotonesABM.BtnEditar BtnReportar1;
    private ComboBox.ComboBox CbxCaja;
    private com.toedter.calendar.JDateChooser DtChFecha;
    private Label.Label LblEstado;
    private Label.Label LblFechaCuadre;
    private Label.Label LblfechaApertura;
    private Label.Label LblfechaCierre;
    private javax.swing.JTable TblCuadreCajaDetalle;
    private javax.swing.JTable TblCuadreCajaTipoPago;
    private javax.swing.JTable TblCuadreCajaTipoPago1;
    private BotonesABM.BtnAceptar btnAceptar1;
    private BotonesABM.BtnCancelar btnCancelar1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextFieldNumber;
    private Label.Label label10;
    private Label.Label label12;
    private Label.Label label2;
    private Label.Label label3;
    private Label.Label label6;
    private Label.Label label7;
    private Label.Label label8;
    private Label.Label label9;
    // End of variables declaration//GEN-END:variables

}
