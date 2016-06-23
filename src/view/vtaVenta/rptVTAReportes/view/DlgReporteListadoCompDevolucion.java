package view.vtaVenta.rptVTAReportes.view;

import controller.grlGeneral.CCEstado;
import controller.vtaVenta.CCConcepto;
import controller.vtaVenta.CCSucursal;
import java.awt.CardLayout;
import java.net.URL;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import modelo.grlGeneral.entidad.CEEstado;
import modelo.vtaVenta.entidad.CEConcepto;
import modelo.vtaVenta.entidad.CESucursal;
import net.sf.jasperreports.swing.JRViewer;
import util.clases.grlGeneral.CLComboBox;
import util.clases.grlGeneral.ConvertidorFecha;
import util.clases.grlGeneral.DialogoPadre;
import util.clases.reportes.CLReporte;
import util.clases.reportes.ClaseGestionDeReporte;
import util.clases.reportes.ClaseReporte;
import util.clases.reportes.ClaseReporte_Parametro;

public class DlgReporteListadoCompDevolucion extends DialogoPadre
{
 
    public DlgReporteListadoCompDevolucion(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        dchDe.setDate(new Date());
        dchA.setDate(new Date());
        CbxConcepto.setModel(CLComboBox.cargarComboConceptoConPivot(CCConcepto.getAll()));
        CbxEstado.setModel(CLComboBox.cargarCombo(CCEstado.ListadoEstado(0)));
        cbxsucursal.setModel(CLComboBox.cargarCombo(CCSucursal.getAll()));
        habilitarPorRbn();
     

    }
private void reportar(String fecha1,String fecha2,String url)
    {
    ClaseReporte oClaseReporte = new ClaseReporte();
    String oUrlC = null;
    CLReporte oCLReporte=new CLReporte();
    URL oUrlRC=getClass().getResource(url);
    oCLReporte.setUrlReport(oUrlRC.toString());
    oUrlC = oCLReporte.getUrlReport().replace('\'','/');
    if(oCLReporte!=null)
    {

      CESucursal oCESucursal = (CESucursal)cbxsucursal.getSelectedItem();
      CEEstado oCEEstado=(CEEstado)CbxEstado.getSelectedItem();
      CEConcepto oCEConcepto= (CEConcepto)CbxConcepto.getSelectedItem();

    oClaseReporte.setUrl(oUrlC);
    ClaseReporte_Parametro oClaseParametroFechaInicial = new ClaseReporte_Parametro();
    oClaseParametroFechaInicial.setNombre("pFechaIn");
    oClaseParametroFechaInicial.setPParametro(fecha1);
    ClaseReporte_Parametro oClaseParametroFechaFinal = new ClaseReporte_Parametro();
    oClaseParametroFechaFinal.setNombre("pFechaFin");
    oClaseParametroFechaFinal.setPParametro(fecha2);

     ClaseReporte_Parametro oClaseParametroIdSucursal = new ClaseReporte_Parametro();
    oClaseParametroIdSucursal.setNombre("pIdSucursal");
    oClaseParametroIdSucursal.setPParametro(""+oCESucursal.getIdSucursal());

    
     ClaseReporte_Parametro oClaseParametroOpcion = new ClaseReporte_Parametro();
    oClaseParametroOpcion.setNombre("pOpcion");
    oClaseParametroOpcion.setPParametro(""+consultaId());
    
     ClaseReporte_Parametro oClaseParametroEstado = new ClaseReporte_Parametro();
    oClaseParametroEstado.setNombre("pEstado");
    oClaseParametroEstado.setPParametro(""+oCEEstado.getmIntIdEstado());
    
     ClaseReporte_Parametro oClaseParametroconcepto = new ClaseReporte_Parametro();
    oClaseParametroconcepto.setNombre("pIdConcepto");
    oClaseParametroconcepto.setPParametro(""+oCEConcepto.getIdConcepto());


    Vector<ClaseReporte_Parametro> oLstParametro = new Vector<ClaseReporte_Parametro>();
    oLstParametro.add(oClaseParametroFechaInicial);
    oLstParametro.add(oClaseParametroFechaFinal);
    oLstParametro.add(oClaseParametroIdSucursal);
    oLstParametro.add(oClaseParametroOpcion);
    oLstParametro.add(oClaseParametroEstado);
    oLstParametro.add(oClaseParametroconcepto);

    oClaseReporte.setoLstParametros(oLstParametro);

    ClaseGestionDeReporte oGestionDeReporte = new ClaseGestionDeReporte();
    JRViewer oJRViewer=new JRViewer(oGestionDeReporte.ejecutarReporte(oClaseReporte));
    {
            PnlReporte.setViewportView(oJRViewer);
            oJRViewer.setVisible(true);
    }
    }
    else
    {
    JOptionPane.showMessageDialog(null,"El reporte no se encuentra");
    }


    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        dchA = new com.toedter.calendar.JDateChooser("dd/MM/yyyy","##/##/####",'_');
        RbtFecha = new javax.swing.JRadioButton();
        dchDe = new com.toedter.calendar.JDateChooser("dd/MM/yyyy","##/##/####",'_');
        BtnGenerar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        RbtIntervalo = new javax.swing.JRadioButton();
        PnlMostrar = new javax.swing.JPanel();
        PnlReporte = new javax.swing.JScrollPane();
        PnlCargando = new javax.swing.JPanel();
        LblCargando = new javax.swing.JLabel();
        cbxsucursal = new ComboBox.ComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        CbxConcepto = new ComboBox.ComboBox();
        CbxEstado = new ComboBox.ComboBox();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Listado de Comprobantes de Devolución");

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 12));
        jLabel1.setForeground(new java.awt.Color(0, 0, 51));
        jLabel1.setText("DEL:");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        dchA.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dchAPropertyChange(evt);
            }
        });

        buttonGroup1.add(RbtFecha);
        RbtFecha.setFont(new java.awt.Font("Verdana", 0, 12));
        RbtFecha.setForeground(new java.awt.Color(102, 0, 0));
        RbtFecha.setSelected(true);
        RbtFecha.setText("Por Fecha");
        RbtFecha.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        RbtFecha.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        RbtFecha.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                RbtFechaItemStateChanged(evt);
            }
        });

        dchDe.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dchDePropertyChange(evt);
            }
        });

        BtnGenerar.setText("Generar");
        BtnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGenerarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 12));
        jLabel2.setForeground(new java.awt.Color(0, 0, 51));
        jLabel2.setText("AL:");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        buttonGroup1.add(RbtIntervalo);
        RbtIntervalo.setFont(new java.awt.Font("Verdana", 0, 12));
        RbtIntervalo.setForeground(new java.awt.Color(102, 0, 0));
        RbtIntervalo.setText("Por Intervalo Fecha");
        RbtIntervalo.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        RbtIntervalo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        RbtIntervalo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                RbtIntervaloItemStateChanged(evt);
            }
        });
        RbtIntervalo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RbtIntervaloActionPerformed(evt);
            }
        });

        PnlMostrar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        PnlMostrar.setLayout(new java.awt.CardLayout());
        PnlMostrar.add(PnlReporte, "card2");

        PnlCargando.setLayout(new java.awt.BorderLayout());

        LblCargando.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblCargando.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/retina_waiting.gif"))); // NOI18N
        PnlCargando.add(LblCargando, java.awt.BorderLayout.CENTER);

        PnlMostrar.add(PnlCargando, "card3");

        jLabel5.setFont(new java.awt.Font("Verdana", 0, 12));
        jLabel5.setForeground(new java.awt.Color(0, 0, 51));
        jLabel5.setText("Sucursal :");

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 12));
        jLabel3.setForeground(new java.awt.Color(0, 0, 51));
        jLabel3.setText("Concepto :");

        jLabel4.setFont(new java.awt.Font("Verdana", 0, 12));
        jLabel4.setForeground(new java.awt.Color(0, 0, 51));
        jLabel4.setText("Estado:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PnlMostrar, javax.swing.GroupLayout.DEFAULT_SIZE, 970, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxsucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CbxConcepto, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(RbtFecha)
                                .addGap(18, 18, 18)
                                .addComponent(RbtIntervalo, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dchDe, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dchA, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CbxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
                        .addComponent(BtnGenerar)
                        .addGap(23, 23, 23))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(RbtIntervalo, 0, 0, Short.MAX_VALUE)
                    .addComponent(RbtFecha, 0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(4, 4, 4)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel1)))
                        .addComponent(dchA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(dchDe, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                    .addComponent(cbxsucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(CbxConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                    .addComponent(CbxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnGenerar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PnlMostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGenerarActionPerformed

        
        BtnGenerar.setEnabled(false);
        dchDe.setEnabled(false);
        dchA.setEnabled(false);
        RbtFecha.setEnabled(false);
        RbtIntervalo.setEnabled(false);
        CbxConcepto.setEnabled(false);
        CbxEstado.setEnabled(false);
        cbxsucursal.setEnabled(false);
        ((CardLayout)(PnlMostrar.getLayout())).show(PnlMostrar,"card3");
        LblCargando.setVisible(true);
        Runnable miRunnable = new Runnable() {
            public void run() {
                try {
                    
                        ConvertidorFecha oCEConvertidorFecha= new ConvertidorFecha();
                        oCEConvertidorFecha.setFecha(dchDe.getCalendar());

                        ConvertidorFecha oCEConvertidorFecha2= new ConvertidorFecha();


                        if(dchA.getCalendar()!=null){
                        oCEConvertidorFecha2.setFecha(dchA.getCalendar());
                        }

                        reportar(oCEConvertidorFecha.getFechaYMD(),oCEConvertidorFecha2.getFechaYMD(),"/view/vtaVenta/rptVTAReportes/VTARPTComprobantesDevolucion.jasper");

                    ((CardLayout)(PnlMostrar.getLayout())).show(PnlMostrar,"card2");
                    LblCargando.setVisible(false);
                    BtnGenerar.setEnabled(true);
                    dchDe.setEnabled(true);
                    dchA.setEnabled(true);
                    RbtFecha.setEnabled(true);
                    RbtIntervalo.setEnabled(true);
                    CbxConcepto.setEnabled(true);
                    CbxEstado.setEnabled(true);
                    cbxsucursal.setEnabled(true);
                    habilitarPorRbn();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Thread hilo = new Thread(miRunnable);
        hilo.start();
}//GEN-LAST:event_BtnGenerarActionPerformed
    private int consultaId()
    {
        if(RbtFecha.isSelected())
        {
            return 1;
        }
        else
        {
            return 2;
        }
    }
    private void RbtIntervaloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RbtIntervaloActionPerformed

}//GEN-LAST:event_RbtIntervaloActionPerformed

    private void dchDePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dchDePropertyChange
     if(evt.getPropertyName().equals("date"))
     {
         dchA.setMinSelectableDate(dchDe.getDate());
     }
    }//GEN-LAST:event_dchDePropertyChange

    private void dchAPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dchAPropertyChange

     if(evt.getPropertyName().equals("date"))
     {
         dchDe.setMaxSelectableDate(dchA.getDate());
     }

    }//GEN-LAST:event_dchAPropertyChange

    private void RbtFechaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_RbtFechaItemStateChanged

        habilitarPorRbn();
        
    }//GEN-LAST:event_RbtFechaItemStateChanged

    private void RbtIntervaloItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_RbtIntervaloItemStateChanged

        habilitarPorRbn();
    }//GEN-LAST:event_RbtIntervaloItemStateChanged

    private void habilitarPorRbn()
    {
        if(RbtFecha.isSelected())
            dchA.setEnabled(false);
        else
            dchA.setEnabled(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnGenerar;
    private ComboBox.ComboBox CbxConcepto;
    private ComboBox.ComboBox CbxEstado;
    private javax.swing.JLabel LblCargando;
    private javax.swing.JPanel PnlCargando;
    private javax.swing.JPanel PnlMostrar;
    private javax.swing.JScrollPane PnlReporte;
    private javax.swing.JRadioButton RbtFecha;
    private javax.swing.JRadioButton RbtIntervalo;
    private javax.swing.ButtonGroup buttonGroup1;
    private ComboBox.ComboBox cbxsucursal;
    private com.toedter.calendar.JDateChooser dchA;
    private com.toedter.calendar.JDateChooser dchDe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables

}
