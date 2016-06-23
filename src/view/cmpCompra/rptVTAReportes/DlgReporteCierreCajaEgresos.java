package view.cmpCompra.rptVTAReportes;

import java.awt.CardLayout;
import java.net.URL;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import net.sf.jasperreports.swing.JRViewer;
import util.clases.grlGeneral.ConvertidorFecha;
import util.clases.reportes.CLReporte;
import util.clases.reportes.ClaseGestionDeReporte;
import util.clases.reportes.ClaseReporte;
import util.clases.reportes.ClaseReporte_Parametro;

public class DlgReporteCierreCajaEgresos extends javax.swing.JDialog
{
 
    public DlgReporteCierreCajaEgresos(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        dchDel.setDate(new Date());
        dchAl.setDate(new Date());
    }
public void reportar()
    {
        BtnGenerar.setEnabled(false);
        dchDel.setEnabled(false);
        dchAl.setEnabled(false);
        ChBkDetalle.setEnabled(false);
        ((CardLayout)(PnlMostrar.getLayout())).show(PnlMostrar,"card3");
        LblCargando.setVisible(true);
        Runnable miRunnable = new Runnable() {
            public void run() {
                try {


                                ClaseReporte oClaseReporte = new ClaseReporte();
                                String oUrlC = null;
                                CLReporte oCLReporte=new CLReporte();
                                URL oUrlRC=null;
                                if(ChBkDetalle.isSelected())
                                {
                                oUrlRC=getClass().getResource("/view/cmpCompra/rptVTAReportes/CMPRPTCierreCajaEgresos.jasper");
                                }
                                else
                                {
                                    oUrlRC=getClass().getResource("/view/cmpCompra/rptVTAReportes/CMPRPTCierreCajaEgresosCabecera.jasper");
                                }
                                oCLReporte.setUrlReport(oUrlRC.toString());
                                oUrlC = oCLReporte.getUrlReport().replace('\'','/');
                                if(oCLReporte!=null)
                                {
                                oClaseReporte.setUrl(oUrlC);
                                ClaseReporte_Parametro oClaseParametroDir = new ClaseReporte_Parametro();
                                oClaseParametroDir.setNombre("SUBREPORT_DIR");
                                CLReporte oCLSubReporte=new CLReporte();
                                String oUrlCdir = null;
                                URL oUrlRCdir=getClass().getResource("/view/cmpCompra/rptVTAReportes/");
                                oCLSubReporte.setUrlReport(oUrlRCdir.toString());
                                oUrlCdir = oCLSubReporte.getUrlReport().replace('\'','/');
                                oClaseParametroDir.setPParametro(oUrlCdir);

                                  
                                ClaseReporte_Parametro oClaseParametroFechaIni = new ClaseReporte_Parametro();
                                oClaseParametroFechaIni.setNombre("pfechaini");
                                ConvertidorFecha oCEConvertidorFecha= new ConvertidorFecha();
                                oCEConvertidorFecha.setFecha(dchDel.getCalendar());
                                oClaseParametroFechaIni.setPParametro(oCEConvertidorFecha.getFechaYMD());

                                ClaseReporte_Parametro oClaseParametroFechaFin = new ClaseReporte_Parametro();
                                oClaseParametroFechaFin.setNombre("pfechafin");
                                oCEConvertidorFecha= new ConvertidorFecha();
                                oCEConvertidorFecha.setFecha(dchAl.getCalendar());
                                oClaseParametroFechaFin.setPParametro(oCEConvertidorFecha.getFechaYMD());

                                Vector<ClaseReporte_Parametro> oLstParametro = new Vector<ClaseReporte_Parametro>();
                                oLstParametro.add(oClaseParametroFechaIni);
                                oLstParametro.add(oClaseParametroFechaFin);
                                oLstParametro.add(oClaseParametroDir);
                                oClaseReporte.setoLstParametros(oLstParametro);
                                ClaseGestionDeReporte oGestionDeReporte = new ClaseGestionDeReporte();
                                JRViewer oJRViewer=new JRViewer(oGestionDeReporte.ejecutarReporte(oClaseReporte));
                                {
                                        PnlReporte.setViewportView(oJRViewer);
                                      
                                }
                                }
                                else
                                {
                                JOptionPane.showMessageDialog(null,"El reporte no se encuentra");
                                }


                    ((CardLayout)(PnlMostrar.getLayout())).show(PnlMostrar,"card2");
                    LblCargando.setVisible(false);
                    BtnGenerar.setEnabled(true);
                    dchDel.setEnabled(true);
                    dchAl.setEnabled(true);
                    ChBkDetalle.setEnabled(true);

                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Thread hilo = new Thread(miRunnable);
        hilo.start();

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        BtnGenerar = new javax.swing.JButton();
        PnlMostrar = new javax.swing.JPanel();
        PnlReporte = new javax.swing.JScrollPane();
        PnlCargando = new javax.swing.JPanel();
        LblCargando = new javax.swing.JLabel();
        ChBkDetalle = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        dchDel = new com.toedter.calendar.JDateChooser("dd/MM/yyyy","##/##/####",'_');
        dchAl = new com.toedter.calendar.JDateChooser("dd/MM/yyyy","##/##/####",'_');

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reporte Lista de Pagos");

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 51));
        jLabel1.setText("Del :");

        BtnGenerar.setText("Generar");
        BtnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGenerarActionPerformed(evt);
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

        ChBkDetalle.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChBkDetalle.setText("Detalle Cobro por Tipo Pago");
        ChBkDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChBkDetalleActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 51));
        jLabel2.setText("Al :");

        dchDel.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dchDelPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PnlMostrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 934, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dchDel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dchAl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 421, Short.MAX_VALUE)
                        .addComponent(ChBkDetalle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnGenerar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnGenerar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dchAl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dchDel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ChBkDetalle, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PnlMostrar, javax.swing.GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGenerarActionPerformed
        reportar();
}//GEN-LAST:event_BtnGenerarActionPerformed
  
    private void ChBkDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChBkDetalleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChBkDetalleActionPerformed

    private void dchDelPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dchDelPropertyChange

        //  dChFecFinal.setDate(dChFecInicial.getDate());
        dchAl.setMinSelectableDate(dchDel.getDate());
}//GEN-LAST:event_dchDelPropertyChange


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnGenerar;
    private javax.swing.JCheckBox ChBkDetalle;
    private javax.swing.JLabel LblCargando;
    private javax.swing.JPanel PnlCargando;
    private javax.swing.JPanel PnlMostrar;
    private javax.swing.JScrollPane PnlReporte;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser dchAl;
    private com.toedter.calendar.JDateChooser dchDel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables

}

