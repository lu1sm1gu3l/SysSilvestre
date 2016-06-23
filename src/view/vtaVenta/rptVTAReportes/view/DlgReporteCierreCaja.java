package view.vtaVenta.rptVTAReportes.view;

import controller.vtaVenta.CCPuntoVenta;
import java.awt.CardLayout;
import java.net.URL;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import modelo.grlGeneral.entidad.CEImpresora;
import modelo.vtaVenta.entidad.CEPuntoVenta;
import net.sf.jasperreports.swing.JRViewer;
import util.clases.grlGeneral.CLComboBox;
import util.clases.grlGeneral.ConvertidorFecha;
import util.clases.grlGeneral.DialogoPadre;
import util.clases.reportes.CLReporte;
import util.clases.reportes.ClaseGestionDeReporte;
import util.clases.reportes.ClaseReporte;
import util.clases.reportes.ClaseReporte_Parametro;
import view.FrmSistemaMenu;

public class DlgReporteCierreCaja extends DialogoPadre
{
 
    public DlgReporteCierreCaja(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        CbxCaja.setModel(CLComboBox.cargarCombo(CCPuntoVenta.listarCaja(FrmSistemaMenu.getIdSucursal())));
        
        dchFecha.setDate(new Date());
    }
public void reportar()
    {
        BtnGenerar.setEnabled(false);
        dchFecha.setEnabled(false);
        ChBkDetalle.setEnabled(false);
        CbxCaja.setEnabled(false);
        ((CardLayout)(PnlMostrar.getLayout())).show(PnlMostrar,"card3");
        LblCargando.setVisible(true);
        Runnable miRunnable = new Runnable() {
            public void run() {
                try {

                                CEPuntoVenta oCEPuntoVenta=(CEPuntoVenta)CbxCaja.getSelectedItem();

                                ClaseReporte oClaseReporte = new ClaseReporte();
                                String oUrlC = null;
                                CLReporte oCLReporte=new CLReporte();
                                URL oUrlRC=null;
                                if(ChBkDetalle.isSelected())
                                {
                                oUrlRC=getClass().getResource("/view/vtaVenta/rptVTAReportes/VTARPTCierreCaja.jasper");
                                }
                                else
                                {
                                    oUrlRC=getClass().getResource("/view/vtaVenta/rptVTAReportes/VTARPTCierreCajaCabecera.jasper");
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
                                URL oUrlRCdir=getClass().getResource("/view/vtaVenta/rptVTAReportes/");
                                oCLSubReporte.setUrlReport(oUrlRCdir.toString());
                                oUrlCdir = oCLSubReporte.getUrlReport().replace('\'','/');
                                oClaseParametroDir.setPParametro(oUrlCdir);

                                  
                                ClaseReporte_Parametro oClaseParametroIdPuntoVenta = new ClaseReporte_Parametro();
                                oClaseParametroIdPuntoVenta.setNombre("pidpuntoventa");
                                oClaseParametroIdPuntoVenta.setPParametro(oCEPuntoVenta.getIdPuntoVenta()+"");
                                ClaseReporte_Parametro oClaseParametroFechaExacta = new ClaseReporte_Parametro();
                                oClaseParametroFechaExacta.setNombre("pfecha");
                                ConvertidorFecha oCEConvertidorFecha= new ConvertidorFecha();
                                oCEConvertidorFecha.setFecha(dchFecha.getCalendar());
                                oClaseParametroFechaExacta.setPParametro(oCEConvertidorFecha.getFechaYMD());
                                Vector<ClaseReporte_Parametro> oLstParametro = new Vector<ClaseReporte_Parametro>();
                                oLstParametro.add(oClaseParametroFechaExacta);
                                oLstParametro.add(oClaseParametroIdPuntoVenta);
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
                    dchFecha.setEnabled(true);
                    ChBkDetalle.setEnabled(true);
                    CbxCaja.setEnabled(true);

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
        dchFecha = new com.toedter.calendar.JDateChooser("dd/MM/yyyy","##/##/####",'_');
        BtnGenerar = new javax.swing.JButton();
        PnlMostrar = new javax.swing.JPanel();
        PnlReporte = new javax.swing.JScrollPane();
        PnlCargando = new javax.swing.JPanel();
        LblCargando = new javax.swing.JLabel();
        ChBkDetalle = new javax.swing.JCheckBox();
        CbxCaja = new ComboBox.ComboBox();
        label12 = new Label.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reporte Cierre de Caja");

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 11));
        jLabel1.setForeground(new java.awt.Color(0, 0, 51));
        jLabel1.setText("Fecha :");

        dchFecha.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dchFechaPropertyChange(evt);
            }
        });

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

        ChBkDetalle.setFont(new java.awt.Font("Tahoma", 1, 11));
        ChBkDetalle.setText("Detalle Cobro por Tipo Pago");
        ChBkDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChBkDetalleActionPerformed(evt);
            }
        });

        CbxCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxCajaActionPerformed(evt);
            }
        });

        label12.setText("Caja    : ");
        label12.setFont(new java.awt.Font("Verdana", 1, 12));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dchFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(label12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(CbxCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ChBkDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(BtnGenerar)
                .addGap(184, 184, 184))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PnlMostrar, javax.swing.GroupLayout.DEFAULT_SIZE, 934, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(dchFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CbxCaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ChBkDetalle)
                        .addComponent(BtnGenerar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PnlMostrar, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGenerarActionPerformed
        reportar();
}//GEN-LAST:event_BtnGenerarActionPerformed
  
    private void dchFechaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dchFechaPropertyChange
     if(evt.getPropertyName().equals("date"))
     {
         //dchFecha.setMinSelectableDate(dchFecha.getDate());
     }
    }//GEN-LAST:event_dchFechaPropertyChange

    private void ChBkDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChBkDetalleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChBkDetalleActionPerformed

    private void CbxCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxCajaActionPerformed
        
}//GEN-LAST:event_CbxCajaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnGenerar;
    private ComboBox.ComboBox CbxCaja;
    private javax.swing.JCheckBox ChBkDetalle;
    private javax.swing.JLabel LblCargando;
    private javax.swing.JPanel PnlCargando;
    private javax.swing.JPanel PnlMostrar;
    private javax.swing.JScrollPane PnlReporte;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser dchFecha;
    private javax.swing.JLabel jLabel1;
    private Label.Label label12;
    // End of variables declaration//GEN-END:variables

}
