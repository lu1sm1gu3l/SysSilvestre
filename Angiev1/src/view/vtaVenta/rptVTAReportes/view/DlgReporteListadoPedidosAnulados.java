package view.vtaVenta.rptVTAReportes.view;

import java.awt.CardLayout;
import java.net.URL;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import net.sf.jasperreports.swing.JRViewer;
import util.clases.grlGeneral.ConvertidorFecha;
import util.clases.grlGeneral.DialogoPadre;
import util.clases.reportes.CLReporte;
import util.clases.reportes.ClaseGestionDeReporte;
import util.clases.reportes.ClaseReporte;
import util.clases.reportes.ClaseReporte_Parametro;


public class DlgReporteListadoPedidosAnulados extends DialogoPadre
{
 
    public DlgReporteListadoPedidosAnulados(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        dchDe.setDate(new Date());
     

    }
private void reportar(String fecha1,String fecha2,String p1,String p2,String url)
    {
    ClaseReporte oClaseReporte = new ClaseReporte();
    String oUrlC = null;
    CLReporte oCLReporte=new CLReporte();
    URL oUrlRC=getClass().getResource(url);
    oCLReporte.setUrlReport(oUrlRC.toString());
    oUrlC = oCLReporte.getUrlReport().replace('\'','/');
    if(oCLReporte!=null)
    {
    oClaseReporte.setUrl(oUrlC);
    ClaseReporte_Parametro oClaseParametroFechaExacta = new ClaseReporte_Parametro();
    oClaseParametroFechaExacta.setNombre(p1);
    oClaseParametroFechaExacta.setPParametro(fecha1);
     ClaseReporte_Parametro oClaseParametroIdSucursal = new ClaseReporte_Parametro();
    oClaseParametroIdSucursal.setNombre("pIdSucursal");
    oClaseParametroIdSucursal.setPParametro("1");
    ClaseReporte_Parametro oClaseParametroFechaExacta2 = new ClaseReporte_Parametro();
    oClaseParametroFechaExacta2.setNombre(p2);
    oClaseParametroFechaExacta2.setPParametro(fecha2);
    Vector<ClaseReporte_Parametro> oLstParametro = new Vector<ClaseReporte_Parametro>();
    oLstParametro.add(oClaseParametroFechaExacta);
    oLstParametro.add(oClaseParametroIdSucursal);
    oLstParametro.add(oClaseParametroFechaExacta2);
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Listado de Pedidos Anulados");

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 12));
        jLabel1.setForeground(new java.awt.Color(0, 0, 51));
        jLabel1.setText("De:");

        buttonGroup1.add(RbtFecha);
        RbtFecha.setFont(new java.awt.Font("Verdana", 0, 12));
        RbtFecha.setForeground(new java.awt.Color(102, 0, 0));
        RbtFecha.setSelected(true);
        RbtFecha.setText("Por Fecha");

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
        jLabel2.setText("A:");

        buttonGroup1.add(RbtIntervalo);
        RbtIntervalo.setFont(new java.awt.Font("Verdana", 0, 12));
        RbtIntervalo.setForeground(new java.awt.Color(102, 0, 0));
        RbtIntervalo.setText("Por Intervalo Fecha");
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PnlMostrar, javax.swing.GroupLayout.DEFAULT_SIZE, 901, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(RbtFecha)
                        .addGap(18, 18, 18)
                        .addComponent(RbtIntervalo))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dchDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dchA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnGenerar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RbtFecha)
                    .addComponent(RbtIntervalo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dchDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dchA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(BtnGenerar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PnlMostrar, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
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
        ((CardLayout)(PnlMostrar.getLayout())).show(PnlMostrar,"card3");
        LblCargando.setVisible(true);
        Runnable miRunnable = new Runnable() {
            public void run() {
                try {
                    
                    ConvertidorFecha oCEConvertidorFecha= new ConvertidorFecha();
                    oCEConvertidorFecha.setFecha(dchDe.getCalendar());
                    String fecha2=null;
                    if(consultaId()==2)
                    {
                        ConvertidorFecha oCEConvertidorFecha2= new ConvertidorFecha();
                        oCEConvertidorFecha2.setFecha(dchA.getCalendar());
                        fecha2=oCEConvertidorFecha2.getFechaYMD();
                        reportar(oCEConvertidorFecha.getFechaYMD(),fecha2,"pDel","pAl","/view/vtaVenta/rptVTAReportes/VTARPTPedidosAnuladosPorIntervalosFecha.jasper");

                    }
                    else
                    {
                    reportar(oCEConvertidorFecha.getFechaYMD(),null,"pFechaExacta","","/view/vtaVenta/rptVTAReportes/VTARPTPedidosAnuladosPorFechaExacta.jasper");
                    }
                    
                    ((CardLayout)(PnlMostrar.getLayout())).show(PnlMostrar,"card2");
                    LblCargando.setVisible(false);
                    BtnGenerar.setEnabled(true);
                    dchDe.setEnabled(true);
                    dchA.setEnabled(true);
                    RbtFecha.setEnabled(true);
                    RbtIntervalo.setEnabled(true);
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnGenerar;
    private javax.swing.JLabel LblCargando;
    private javax.swing.JPanel PnlCargando;
    private javax.swing.JPanel PnlMostrar;
    private javax.swing.JScrollPane PnlReporte;
    private javax.swing.JRadioButton RbtFecha;
    private javax.swing.JRadioButton RbtIntervalo;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser dchA;
    private com.toedter.calendar.JDateChooser dchDe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables

}
