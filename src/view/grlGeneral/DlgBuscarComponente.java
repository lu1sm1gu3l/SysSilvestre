package view.grlGeneral;


import controller.grlGeneral.CCComponente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import modelo.grlGeneral.entidad.CEComponente;
import modelo.grlGeneral.entidad.CEVinculacionComponenteAccionMatriz;
import table.ArrayListTableModel;
import util.clases.grlGeneral.DialogoPadre;

public class DlgBuscarComponente extends JDialog  implements ActionListener
{
    public DlgBuscarComponente(java.awt.Dialog parent, boolean modal,String parametro)
    {
        super(parent, modal);
        initComponents();
        TxtComponente.setText(parametro);
        buscarComponente();
        TxtComponente.requestFocus();
        this.configurarTablaHistorial();
    }
    private void configurarTablaHistorial()
    {
         TblResultados.getColumnModel().getColumn(0).setMaxWidth(80);
         TblResultados.getColumnModel().getColumn(0).setMinWidth(80);
         TblResultados.getColumnModel().getColumn(1).setMaxWidth(300);
         TblResultados.getColumnModel().getColumn(1).setMinWidth(300);
    }
 
   
    public void sendDatoComponente(String datoComponente)
    {
        TxtComponente.setText(datoComponente);
        buscarComponente();
    }
    public CEComponente getComponente()
    {
        int selectedRow=TblResultados.getSelectedRow();
        CEComponente oCEComponente=null;
        CEVinculacionComponenteAccionMatriz oCEVinculacionComponenteAccionMatriz=null;
        if(selectedRow!=-1)
        {

        oCEComponente=(CEComponente)TblResultados.getValueAt(selectedRow, 1);
        dispose();
        }
        return oCEComponente;
    }
    private void limpiarTabla()
    {
        ArrayListTableModel oModelTbl=(ArrayListTableModel)TblResultados.getModel();
        int i=oModelTbl.getRowCount();
        for(int a=0;a<i;a++)
        {
           oModelTbl.removeRow(0);
        }
    }
    private void cargarTabla(List<CEComponente>  oVctComponente)
    {
        limpiarTabla();
        ArrayList oList;
        if(oVctComponente!=null)
        {
            int size=oVctComponente.size();
            for(int a=0;a<size;a++)
            {
            oList=new ArrayList();
            oList.add(oVctComponente.get(a).getIdComponente());
            oList.add(oVctComponente.get(a));
            oList.add(oVctComponente.get(a).getDescripcion());
            ((ArrayListTableModel)TblResultados.getModel()).addRow(oList);
            }
         }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        TxtComponente = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblResultados = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Búsqueda Docente");
        setResizable(false);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consulta de Componente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 12), new java.awt.Color(102, 0, 0))); // NOI18N

        jLabel12.setFont(new java.awt.Font("Verdana", 1, 12));
        jLabel12.setForeground(new java.awt.Color(0, 51, 102));
        jLabel12.setText("Ingrese los datos:");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 287, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        TxtComponente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtComponenteActionPerformed(evt);
            }
        });
        TxtComponente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtComponenteKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel12)
                .addGap(10, 10, 10)
                .addComponent(TxtComponente, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(50, 50, 50))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(TxtComponente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Resultados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 12), new java.awt.Color(102, 0, 0))); // NOI18N

        TblResultados.setFont(new java.awt.Font("Verdana", 0, 12));
        TblResultados.setModel(new ArrayListTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nombre", "Descripción"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblResultados.setRowHeight(22);
        TblResultados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblResultadosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TblResultados);
        TblResultados.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "ProjSave");
        TblResultados.getActionMap().put("ProjSave", new AbstractAction()
            {
                public void actionPerformed(ActionEvent e)
                {
                    int fila = TblResultados.getSelectedRow();
                    if ((fila > -1))
                    {
                        dispose();
                    }
                }
            });

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
            jPanel3.setLayout(jPanel3Layout);
            jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents
    private void TxtComponenteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtComponenteKeyReleased

        if(evt.getKeyCode()==evt.VK_ENTER)
     {
         buscarComponente();
     }
     if(evt.getKeyCode()==evt.VK_DOWN)
     {
         int size =TblResultados.getRowCount();
         if(size>0)
         {
         TblResultados.requestFocus();
         TblResultados.setRowSelectionInterval(0,0);
         }
     }
    }//GEN-LAST:event_TxtComponenteKeyReleased

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
    if(evt.getClickCount()==2)
    {
        getComponente();
    }
    }//GEN-LAST:event_formMouseClicked

    private void TblResultadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblResultadosMouseClicked
     if(evt.getClickCount()==2)
        {
            int FilaSeleccionada =TblResultados.getSelectedRow();
            if(FilaSeleccionada!=-1)
            {
            dispose();
            }
        }
    }//GEN-LAST:event_TblResultadosMouseClicked

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
      TblResultados.clearSelection();
    }//GEN-LAST:event_formWindowClosed

    private void TxtComponenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtComponenteActionPerformed

    }//GEN-LAST:event_TxtComponenteActionPerformed
    private void buscarComponente()
    {
         String datoComponente=TxtComponente.getText().trim();
         
            
                 
                CCComponente oCCComponente=new CCComponente();
                List<CEComponente> oVctComponente=oCCComponente.consultarComponente(datoComponente);
                cargarTabla(oVctComponente);
                 
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TblResultados;
    private javax.swing.JTextField TxtComponente;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
    private static final String ACTION_CLOSE = "ACTION_CLOSE";
    protected JRootPane createRootPane()
    {
        JRootPane rootPane = new JRootPane();
        KeyStroke esc = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        rootPane.registerKeyboardAction(this, ACTION_CLOSE, esc, JComponent.WHEN_IN_FOCUSED_WINDOW);
        return rootPane;
    }
    public void actionPerformed(ActionEvent e)
    {

        if(ACTION_CLOSE.equals(e.getActionCommand()))
            {
            TblResultados.clearSelection();
            dispose();
            }

    }
}
