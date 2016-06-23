package view.vtaVenta.rptVTAReportes.view;

import controller.vtaVenta.CCMoneda;
import controller.vtaVenta.CCSucursal;
import java.awt.CardLayout;
import java.net.URL;
import java.util.Vector;
import javax.swing.JOptionPane;
import modelo.vtaVenta.entidad.CEMoneda;
import modelo.vtaVenta.entidad.CESucursal;
import net.sf.jasperreports.swing.JRViewer;
import util.clases.grlGeneral.CLComboBox;
import util.clases.grlGeneral.DialogoPadre;
import util.clases.reportes.CLReporte;
import util.clases.reportes.ClaseGestionDeReporte;
import util.clases.reportes.ClaseReporte;
import util.clases.reportes.ClaseReporte_Parametro;

public class DlgReporteIngresosPorDia extends DialogoPadre
{
  private int Opcion=0;
    public DlgReporteIngresosPorDia(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        CbxMoneda.setModel(CLComboBox.cargarCombo(CCMoneda.listaAll()));
        cbxsucursal.setModel(CLComboBox.cargarCombo(CCSucursal.getAll()));
        LblCargando.setVisible(false);

     

    }
private void reportar(String url)
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
      CEMoneda oCEMoneda= (CEMoneda)CbxMoneda.getSelectedItem();

    oClaseReporte.setUrl(oUrlC);
    ClaseReporte_Parametro oClaseParametroFechaInicial = new ClaseReporte_Parametro();
    oClaseParametroFechaInicial.setNombre("panio");
    String anio=""+ychAnio.getValue();
    oClaseParametroFechaInicial.setPParametro(""+anio);
    ClaseReporte_Parametro oClaseParametroFechaFinal = new ClaseReporte_Parametro();
    String mes =""+(mChMes.getMonth()+1);
    oClaseParametroFechaFinal.setNombre("pmes");
    oClaseParametroFechaFinal.setPParametro(""+mes);
    
     ClaseReporte_Parametro oClaseParametroIdSucursal = new ClaseReporte_Parametro();
    oClaseParametroIdSucursal.setNombre("pidsucursal");
    oClaseParametroIdSucursal.setPParametro(""+oCESucursal.getIdSucursal());

     ClaseReporte_Parametro oClaseParametroconcepto = new ClaseReporte_Parametro();
    oClaseParametroconcepto.setNombre("pidmoneda");
    oClaseParametroconcepto.setPParametro(""+oCEMoneda.getId_moneda());


    ClaseReporte_Parametro oClaseParametroOpcion = new ClaseReporte_Parametro();
    oClaseParametroOpcion.setNombre("popcion");
    oClaseParametroOpcion.setPParametro(""+Opcion);

    Vector<ClaseReporte_Parametro> oLstParametro = new Vector<ClaseReporte_Parametro>();
    oLstParametro.add(oClaseParametroFechaInicial);
    oLstParametro.add(oClaseParametroFechaFinal);
    oLstParametro.add(oClaseParametroIdSucursal);
    oLstParametro.add(oClaseParametroconcepto);
    oLstParametro.add(oClaseParametroOpcion);


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
        BtnGenerar = new javax.swing.JButton();
        cbxsucursal = new ComboBox.ComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        CbxMoneda = new ComboBox.ComboBox();
        ychAnio = new com.toedter.calendar.JYearChooser();
        mChMes = new com.toedter.calendar.JMonthChooser();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        PnlMostrar = new javax.swing.JPanel();
        PnlReporte = new javax.swing.JScrollPane();
        PnlCargando = new javax.swing.JPanel();
        LblCargando = new javax.swing.JLabel();
        chkBol = new Opcion.CheckBox();
        chkFT = new Opcion.CheckBox();
        chkOtros = new Opcion.CheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reportes Ingresos Mensuales");

        BtnGenerar.setText("Generar");
        BtnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGenerarActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Verdana", 0, 12));
        jLabel5.setForeground(new java.awt.Color(0, 0, 51));
        jLabel5.setText("Suscursal :");

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 12));
        jLabel3.setForeground(new java.awt.Color(0, 0, 51));
        jLabel3.setText("Moneda :");

        jLabel6.setFont(new java.awt.Font("Verdana", 0, 12));
        jLabel6.setForeground(new java.awt.Color(0, 0, 51));
        jLabel6.setText("Año :");

        jLabel7.setFont(new java.awt.Font("Verdana", 0, 12));
        jLabel7.setForeground(new java.awt.Color(0, 0, 51));
        jLabel7.setText("Mes :");

        PnlMostrar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        PnlMostrar.setLayout(new java.awt.CardLayout());
        PnlMostrar.add(PnlReporte, "card2");

        PnlCargando.setLayout(new java.awt.BorderLayout());

        LblCargando.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblCargando.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/retina_waiting.gif"))); // NOI18N
        PnlCargando.add(LblCargando, java.awt.BorderLayout.CENTER);

        PnlMostrar.add(PnlCargando, "card3");

        chkBol.setSelected(true);
        chkBol.setText("BOL");
        chkBol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkBolActionPerformed(evt);
            }
        });

        chkFT.setSelected(true);
        chkFT.setText("FT");

        chkOtros.setSelected(true);
        chkOtros.setText("Otros");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ychAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mChMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxsucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CbxMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkBol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkFT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkOtros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnGenerar)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(PnlMostrar, javax.swing.GroupLayout.DEFAULT_SIZE, 886, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbxsucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(CbxMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BtnGenerar)
                        .addComponent(chkBol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(chkFT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(chkOtros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(mChMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ychAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))))
                .addContainerGap(635, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(47, Short.MAX_VALUE)
                    .addComponent(PnlMostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGenerarActionPerformed

        if(!chkFT.isSelected()&&!chkBol.isSelected()&&!chkOtros.isSelected())
        {
            JOptionPane.showMessageDialog(null,"Seleccione al menos un tipo de comprobante");
        }else{
        BtnGenerar.setEnabled(false);
        CbxMoneda.setEnabled(false);
        chkBol.setEnabled(false);
        chkFT.setEnabled(false);
        chkOtros.setEnabled(false);
        cbxsucursal.setEnabled(false);
        ((CardLayout)(PnlMostrar.getLayout())).show(PnlMostrar,"card3");
        LblCargando.setVisible(true);
        Runnable miRunnable = new Runnable() {
            public void run() {
                try {
                    

                    
                    seleccionarReporte();

                    ((CardLayout)(PnlMostrar.getLayout())).show(PnlMostrar,"card2");
                    LblCargando.setVisible(false);
                    BtnGenerar.setEnabled(true);
                    CbxMoneda.setEnabled(true);
                    cbxsucursal.setEnabled(true);
                    chkBol.setEnabled(true);
                    chkFT.setEnabled(true);
                    chkOtros.setEnabled(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Thread hilo = new Thread(miRunnable);
        hilo.start();
        }
}//GEN-LAST:event_BtnGenerarActionPerformed

    private void seleccionarReporte()
    {
        if(chkOtros.isSelected()&&chkFT.isSelected()&&chkBol.isSelected())
        {
            Opcion=1;
            reportar("/view/vtaVenta/rptVTAReportes/VTARPTVentasPorDia.jasper");
        }
        else if(chkBol.isSelected()&&(!chkFT.isSelected()&&!chkOtros.isSelected()))
        {
            Opcion=2;
            reportar("/view/vtaVenta/rptVTAReportes/VTARPTVentasPorDia1.jasper");
        }
        else if(chkFT.isSelected()&&(!chkBol.isSelected()&&!chkOtros.isSelected()))
        {
            Opcion=3;
            reportar("/view/vtaVenta/rptVTAReportes/VTARPTVentasPorDia1.jasper");
        }
        else if(chkOtros.isSelected()&&(!chkFT.isSelected()&&!chkBol.isSelected()))
        {
            Opcion=4;
            reportar("/view/vtaVenta/rptVTAReportes/VTARPTVentasPorDia1.jasper");
        }
        else if(chkOtros.isSelected()&&chkFT.isSelected()&&!chkBol.isSelected())
        {
            Opcion=5;
            reportar("/view/vtaVenta/rptVTAReportes/VTARPTVentasPorDia2.jasper");
        }
        else if(chkOtros.isSelected()&&!chkFT.isSelected()&&chkBol.isSelected())
        {
            Opcion=6;
            reportar("/view/vtaVenta/rptVTAReportes/VTARPTVentasPorDia2.jasper");
        }
        else if(!chkOtros.isSelected()&&chkFT.isSelected()&&chkBol.isSelected())
        {
            Opcion=7;
            reportar("/view/vtaVenta/rptVTAReportes/VTARPTVentasPorDia2.jasper");
        }
        
    }

    private void chkBolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkBolActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkBolActionPerformed
  
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnGenerar;
    private ComboBox.ComboBox CbxMoneda;
    private javax.swing.JLabel LblCargando;
    private javax.swing.JPanel PnlCargando;
    private javax.swing.JPanel PnlMostrar;
    private javax.swing.JScrollPane PnlReporte;
    private javax.swing.ButtonGroup buttonGroup1;
    private ComboBox.ComboBox cbxsucursal;
    private Opcion.CheckBox chkBol;
    private Opcion.CheckBox chkFT;
    private Opcion.CheckBox chkOtros;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private com.toedter.calendar.JMonthChooser mChMes;
    private com.toedter.calendar.JYearChooser ychAnio;
    // End of variables declaration//GEN-END:variables

}
