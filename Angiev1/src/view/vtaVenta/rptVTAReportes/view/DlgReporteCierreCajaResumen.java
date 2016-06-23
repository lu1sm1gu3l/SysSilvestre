package view.vtaVenta.rptVTAReportes.view;

import controller.vtaVenta.CCSucursal;
import java.awt.CardLayout;
import java.net.URL;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import modelo.vtaVenta.entidad.CESucursal;
import net.sf.jasperreports.swing.JRViewer;
import util.clases.grlGeneral.CLComboBox;
import util.clases.grlGeneral.ConvertidorFecha;
import util.clases.grlGeneral.DialogoPadre;
import util.clases.reportes.CLReporte;
import util.clases.reportes.ClaseGestionDeReporte;
import util.clases.reportes.ClaseReporte;
import util.clases.reportes.ClaseReporte_Parametro;

public class DlgReporteCierreCajaResumen extends DialogoPadre
{
 
    public DlgReporteCierreCajaResumen(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        CbxSucursal.setModel(CLComboBox.cargarCombo(CCSucursal.getAll()));
        dchFecha.setDate(new Date());
    }
public void reportar()
    {
        BtnGenerar.setEnabled(false);
        dchFecha.setEnabled(false);
        CbxSucursal.setEnabled(false);
        ((CardLayout)(PnlMostrar.getLayout())).show(PnlMostrar,"card3");
        LblCargando.setVisible(true);
        Runnable miRunnable = new Runnable() {
            public void run() {
                try {

                                CESucursal oCESucursal=(CESucursal)CbxSucursal.getSelectedItem();

                                ClaseReporte oClaseReporte = new ClaseReporte();
                                String oUrlC = null;
                                CLReporte oCLReporte=new CLReporte();
                                URL oUrlRC=null;

                                oUrlRC=getClass().getResource("/view/vtaVenta/rptVTAReportes/VTARPTResumenCierreCaja.jasper");

                                oCLReporte.setUrlReport(oUrlRC.toString());
                                oUrlC = oCLReporte.getUrlReport().replace('\'','/');
                                if(oCLReporte!=null)
                                {
                                oClaseReporte.setUrl(oUrlC);

                                  
                                ClaseReporte_Parametro oClaseParametroIdSucursal = new ClaseReporte_Parametro();
                                oClaseParametroIdSucursal.setNombre("pidsucursal");
                                oClaseParametroIdSucursal.setPParametro(oCESucursal.getIdSucursal()+"");
                                ClaseReporte_Parametro oClaseParametroFechaExacta = new ClaseReporte_Parametro();
                                oClaseParametroFechaExacta.setNombre("pfecha");
                                ConvertidorFecha oCEConvertidorFecha= new ConvertidorFecha();
                                oCEConvertidorFecha.setFecha(dchFecha.getCalendar());
                                oClaseParametroFechaExacta.setPParametro(oCEConvertidorFecha.getFechaYMD());
                                Vector<ClaseReporte_Parametro> oLstParametro = new Vector<ClaseReporte_Parametro>();
                                oLstParametro.add(oClaseParametroFechaExacta);
                                oLstParametro.add(oClaseParametroIdSucursal);
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
                    CbxSucursal.setEnabled(true);

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
        CbxSucursal = new ComboBox.ComboBox();
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

        CbxSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxSucursalActionPerformed(evt);
            }
        });

        label12.setText("Sucursal    : ");
        label12.setFont(new java.awt.Font("Verdana", 1, 12));

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
                        .addComponent(dchFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(label12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CbxSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 388, Short.MAX_VALUE)
                        .addComponent(BtnGenerar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CbxSucursal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dchFecha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(1, 1, 1))
                    .addComponent(BtnGenerar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PnlMostrar, javax.swing.GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE)
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

    private void CbxSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxSucursalActionPerformed

}//GEN-LAST:event_CbxSucursalActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnGenerar;
    private ComboBox.ComboBox CbxSucursal;
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
