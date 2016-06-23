package view.almAlmacen.invInventarios;

import java.awt.CardLayout;
import util.clases.grlGeneral.ConvertidorFecha;
import util.clases.grlGeneral.DialogoPadre;
import util.clases.vtaVenta.JTreeTableJerarquia.JTreeTable;

public class DlgVentasPorJerarquiaDeProductos extends DialogoPadre
{
    public DlgVentasPorJerarquiaDeProductos(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        LblCargando.setVisible(false);
        ((CardLayout)(PnlMostrar.getLayout())).show(PnlMostrar,"card2");
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        BtnGenerar = new javax.swing.JButton();
        PnlMostrar = new javax.swing.JPanel();
        PnlCargando = new javax.swing.JPanel();
        LblCargando = new javax.swing.JLabel();
        PnlTree = new javax.swing.JPanel();
        scpTreeTable = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser("dd/MM/yyyy","##/##/####",'_');
        jDateChooser2 = new com.toedter.calendar.JDateChooser("dd/MM/yyyy","##/##/####",'_');
        jLabel2 = new javax.swing.JLabel();
        LblLeyendaRubro2 = new javax.swing.JLabel();
        LblLeyendaRubro3 = new javax.swing.JLabel();
        LblLeyendaRubro = new javax.swing.JLabel();
        LblLeyendaRubro1 = new javax.swing.JLabel();
        LblLeyendaRubro5 = new javax.swing.JLabel();
        RbtIntervalo = new javax.swing.JRadioButton();
        RbtFecha = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        BtnGenerar.setText("Generar");
        BtnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGenerarActionPerformed(evt);
            }
        });

        PnlMostrar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        PnlMostrar.setLayout(new java.awt.CardLayout());

        PnlCargando.setLayout(new java.awt.BorderLayout());

        LblCargando.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblCargando.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/retina_waiting.gif"))); // NOI18N
        PnlCargando.add(LblCargando, java.awt.BorderLayout.CENTER);

        PnlMostrar.add(PnlCargando, "card3");

        PnlTree.setLayout(new java.awt.BorderLayout());
        PnlTree.add(scpTreeTable, java.awt.BorderLayout.CENTER);

        PnlMostrar.add(PnlTree, "card2");

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 12));
        jLabel1.setForeground(new java.awt.Color(0, 0, 51));
        jLabel1.setText("De:");

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 12));
        jLabel2.setForeground(new java.awt.Color(0, 0, 51));
        jLabel2.setText("A:");

        LblLeyendaRubro2.setFont(new java.awt.Font("Arial", 1, 12));
        LblLeyendaRubro2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Categoria.gif"))); // NOI18N
        LblLeyendaRubro2.setText("Categoria");

        LblLeyendaRubro3.setFont(new java.awt.Font("Arial", 1, 12));
        LblLeyendaRubro3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Subfamilia.gif"))); // NOI18N
        LblLeyendaRubro3.setText("SubFamilia");

        LblLeyendaRubro.setFont(new java.awt.Font("Arial", 1, 12));
        LblLeyendaRubro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Rubro.gif"))); // NOI18N
        LblLeyendaRubro.setText("Rubro");

        LblLeyendaRubro1.setFont(new java.awt.Font("Arial", 1, 12));
        LblLeyendaRubro1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/Familia.gif"))); // NOI18N
        LblLeyendaRubro1.setText("Familia");

        LblLeyendaRubro5.setFont(new java.awt.Font("Arial", 1, 12));
        LblLeyendaRubro5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/product.png"))); // NOI18N
        LblLeyendaRubro5.setText("Producto");

        buttonGroup1.add(RbtIntervalo);
        RbtIntervalo.setFont(new java.awt.Font("Verdana", 0, 12));
        RbtIntervalo.setForeground(new java.awt.Color(102, 0, 0));
        RbtIntervalo.setText("Por Intervalo Fecha");
        RbtIntervalo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RbtIntervaloActionPerformed(evt);
            }
        });

        buttonGroup1.add(RbtFecha);
        RbtFecha.setFont(new java.awt.Font("Verdana", 0, 12));
        RbtFecha.setForeground(new java.awt.Color(102, 0, 0));
        RbtFecha.setSelected(true);
        RbtFecha.setText("Por Fecha");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PnlMostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 988, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LblLeyendaRubro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LblLeyendaRubro2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LblLeyendaRubro1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LblLeyendaRubro3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LblLeyendaRubro5))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnGenerar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(RbtFecha)
                        .addGap(18, 18, 18)
                        .addComponent(RbtIntervalo)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RbtFecha)
                    .addComponent(RbtIntervalo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(BtnGenerar))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LblLeyendaRubro)
                    .addComponent(LblLeyendaRubro2)
                    .addComponent(LblLeyendaRubro1)
                    .addComponent(LblLeyendaRubro3)
                    .addComponent(LblLeyendaRubro5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PnlMostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGenerarActionPerformed
    BtnGenerar.setEnabled(false);
    jDateChooser1.setEnabled(false);
    jDateChooser2.setEnabled(false);
    RbtFecha.setEnabled(false);
    RbtIntervalo.setEnabled(false);
    ((CardLayout)(PnlMostrar.getLayout())).show(PnlMostrar,"card3");
    LblCargando.setVisible(true);
    Runnable miRunnable = new Runnable()
       {
          public void run()
          {
             try
             {
              JTreeTable oJTreeTable=new JTreeTable();
              ConvertidorFecha oCEConvertidorFecha= new ConvertidorFecha();
              oCEConvertidorFecha.setFecha(jDateChooser1.getCalendar());
              String fecha2=null;
              if(consultaId()==2)
              {
              ConvertidorFecha oCEConvertidorFecha2= new ConvertidorFecha();
              oCEConvertidorFecha2.setFecha(jDateChooser2.getCalendar());
              fecha2=oCEConvertidorFecha2.getFechaYMD();
              }
              oJTreeTable.generarReporte(oCEConvertidorFecha.getFechaYMD(),fecha2, consultaId(),PnlMostrar,LblCargando);
              scpTreeTable.setViewportView(oJTreeTable);
              ((CardLayout)(PnlMostrar.getLayout())).show(PnlMostrar,"card2");
              LblCargando.setVisible(false);
                  BtnGenerar.setEnabled(true);
    jDateChooser1.setEnabled(true);
    jDateChooser2.setEnabled(true);
    RbtFecha.setEnabled(true);
    RbtIntervalo.setEnabled(true);
             }
             catch (Exception e)
             {
                e.printStackTrace();
             }
          }
       };

       Thread hilo = new Thread (miRunnable);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnGenerar;
    private javax.swing.JLabel LblCargando;
    private javax.swing.JLabel LblLeyendaRubro;
    private javax.swing.JLabel LblLeyendaRubro1;
    private javax.swing.JLabel LblLeyendaRubro2;
    private javax.swing.JLabel LblLeyendaRubro3;
    private javax.swing.JLabel LblLeyendaRubro5;
    private javax.swing.JPanel PnlCargando;
    private javax.swing.JPanel PnlMostrar;
    private javax.swing.JPanel PnlTree;
    private javax.swing.JRadioButton RbtFecha;
    private javax.swing.JRadioButton RbtIntervalo;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane scpTreeTable;
    // End of variables declaration//GEN-END:variables

}
