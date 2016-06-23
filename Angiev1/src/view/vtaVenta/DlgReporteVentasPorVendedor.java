package view.vtaVenta;

import controller.cmrComercial.CCEmpleado;
import java.awt.CardLayout;
import java.net.URL;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import modelo.cmrComercial.entidad.CEEmpleado;
import net.sf.jasperreports.swing.JRViewer;
import util.clases.grlGeneral.CLComboBox;
import util.clases.grlGeneral.ConvertidorFecha;
import util.clases.grlGeneral.DialogoPadre;
import util.clases.reportes.CLReporte;
import util.clases.reportes.ClaseGestionDeReporte;
import util.clases.reportes.ClaseReporte;
import util.clases.reportes.ClaseReporte_Parametro;

public class DlgReporteVentasPorVendedor extends DialogoPadre
{
 
    public DlgReporteVentasPorVendedor(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        dChFecInicial.setDate(new Date());
        dChFecFinal.setDate(new Date());

         CbxVendedor.setModel(CLComboBox.cargarCombo(CCEmpleado.ListarCNSEmpleado(1,"")));
     

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        dChFecFinal = new com.toedter.calendar.JDateChooser("dd/MM/yyyy","##/##/####",'_');
        dChFecInicial = new com.toedter.calendar.JDateChooser("dd/MM/yyyy","##/##/####",'_');
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        BtnGenerar = new BotonesABM.BtnAceptar();
        jLabel4 = new javax.swing.JLabel();
        cbxEstado = new ComboBox.ComboBox();
        CbxVendedor = new ComboBox.ComboBox();
        PnlMostrar = new javax.swing.JPanel();
        PnlReporte = new javax.swing.JScrollPane();
        PnlCargando = new javax.swing.JPanel();
        LblCargando = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Listado de Pedidos Anulados Por Fecha Exacta");

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 12));
        jLabel1.setForeground(new java.awt.Color(0, 0, 51));
        jLabel1.setText("Fecha Inicial:");

        dChFecInicial.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dChFecInicialPropertyChange(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 12));
        jLabel2.setForeground(new java.awt.Color(0, 0, 51));
        jLabel2.setText("Fecha Final:");

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 12));
        jLabel3.setForeground(new java.awt.Color(0, 0, 51));
        jLabel3.setText("Vendedor");

        BtnGenerar.setText("");
        BtnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGenerarActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Verdana", 0, 12));
        jLabel4.setForeground(new java.awt.Color(0, 0, 51));
        jLabel4.setText("Estado");

        cbxEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos", "Cancelados" }));

        PnlMostrar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        PnlMostrar.setLayout(new java.awt.CardLayout());
        PnlMostrar.add(PnlReporte, "card2");

        PnlCargando.setLayout(new java.awt.BorderLayout());

        LblCargando.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblCargando.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/retina_waiting.gif"))); // NOI18N
        PnlCargando.add(LblCargando, java.awt.BorderLayout.CENTER);

        PnlMostrar.add(PnlCargando, "card3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dChFecInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dChFecFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CbxVendedor, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(PnlMostrar, javax.swing.GroupLayout.DEFAULT_SIZE, 917, Short.MAX_VALUE)
                    .addGap(11, 11, 11)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel3)
                        .addComponent(CbxVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dChFecFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(dChFecInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(BtnGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(582, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(62, Short.MAX_VALUE)
                    .addComponent(PnlMostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dChFecInicialPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dChFecInicialPropertyChange

      //  dChFecFinal.setDate(dChFecInicial.getDate());
     //   dChFecFinal.setMinSelectableDate(dChFecInicial.getDate());
    }//GEN-LAST:event_dChFecInicialPropertyChange

    private void BtnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGenerarActionPerformed


         BtnGenerar.setEnabled(false);
        dChFecInicial.setEnabled(false);
        dChFecFinal.setEnabled(false);
        ((CardLayout)(PnlMostrar.getLayout())).show(PnlMostrar,"card3");
        LblCargando.setVisible(true);
        Runnable miRunnable = new Runnable() {
            public void run() {
                try {



                                ClaseReporte oClaseReporte = new ClaseReporte();
                                String oUrlC = null;
                                CLReporte oCLReporte=new CLReporte();
                                URL oUrlRC=null;
                                
                                oUrlRC=getClass().getResource("/view/vtaVenta/rptVTAReportes/VTARPTVentasPorVendedor.jasper");
                                
                                oCLReporte.setUrlReport(oUrlRC.toString());
                                oUrlC = oCLReporte.getUrlReport().replace('\'','/');
                                if(oCLReporte!=null)
                                {
                                oClaseReporte.setUrl(oUrlC);
                                ClaseReporte_Parametro oClaseParametroFechaInicial = new ClaseReporte_Parametro();
                                oClaseParametroFechaInicial.setNombre("pfechainicial");
                                ConvertidorFecha oCEConvertidorFecha= new ConvertidorFecha();
                                oCEConvertidorFecha.setFecha(dChFecInicial.getCalendar());
                                oClaseParametroFechaInicial.setPParametro(oCEConvertidorFecha.getFechaYMD());

                                ClaseReporte_Parametro oClaseParametroFechafinal = new ClaseReporte_Parametro();
                                oClaseParametroFechafinal.setNombre("pfechafinal");
                                 oCEConvertidorFecha= new ConvertidorFecha();
                                oCEConvertidorFecha.setFecha(dChFecFinal.getCalendar());
                                oClaseParametroFechafinal.setPParametro(oCEConvertidorFecha.getFechaYMD());
                                Vector<ClaseReporte_Parametro> oLstParametro = new Vector<ClaseReporte_Parametro>();

                                ClaseReporte_Parametro oClasePEmpleado = new ClaseReporte_Parametro();
                                oClasePEmpleado.setNombre("pidempleado");
                                CEEmpleado oCEEmpleado=(CEEmpleado)CbxVendedor.getSelectedItem();
                                oClasePEmpleado.setPParametro(oCEEmpleado.getIdEmpleado()+"");

                                ClaseReporte_Parametro oClasePestatao = new ClaseReporte_Parametro();
                                oClasePestatao.setNombre("pidestado");
                                oClasePestatao.setPParametro(cbxEstado.getSelectedIndex()+"");

                                oLstParametro.add(oClaseParametroFechaInicial);
                                oLstParametro.add(oClaseParametroFechafinal);
                                oLstParametro.add(oClasePEmpleado);
                                oLstParametro.add(oClasePestatao);

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


                    ((CardLayout)(PnlMostrar.getLayout())).show(PnlMostrar,"card2");
                    LblCargando.setVisible(false);
                    BtnGenerar.setEnabled(true);
                    dChFecInicial.setEnabled(true);
                    dChFecFinal.setEnabled(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Thread hilo = new Thread(miRunnable);
        hilo.start();


    }//GEN-LAST:event_BtnGenerarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private BotonesABM.BtnAceptar BtnGenerar;
    private ComboBox.ComboBox CbxVendedor;
    private javax.swing.JLabel LblCargando;
    private javax.swing.JPanel PnlCargando;
    private javax.swing.JPanel PnlMostrar;
    private javax.swing.JScrollPane PnlReporte;
    private ComboBox.ComboBox cbxEstado;
    private com.toedter.calendar.JDateChooser dChFecFinal;
    private com.toedter.calendar.JDateChooser dChFecInicial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables

}
