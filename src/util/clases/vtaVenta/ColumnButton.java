package util.clases.vtaVenta;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

public class ColumnButton extends javax.swing.JPanel {

   private JTable tbl;
   private itemColumn[] oListItem;
    public ColumnButton() {
        initComponents();
        jButton1.getAccessibleContext().setAccessibleName("0");
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenuColumna = new javax.swing.JPopupMenu();
        jButton1 = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/util/icono/view_icon.jpg"))); // NOI18N
        jButton1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton1ActionPerformed
    {//GEN-HEADEREND:event_jButton1ActionPerformed
        jPopupMenuColumna.show(jButton1,jButton1.getX(),jButton1.getY()+jButton1.getHeight());
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPopupMenu jPopupMenuColumna;
    // End of variables declaration//GEN-END:variables


    public JTable getTbl() {
        return tbl;
    }

    public void setTbl(JTable tbl)
    {
        this.tbl = tbl;
        int sizeColumn=tbl.getColumnCount();
        oListItem=new itemColumn[sizeColumn];
        for(int i=0;i<sizeColumn;i++)
        {
            itemColumn oColumn;
            TableColumn oTableColumn=tbl.getColumnModel().getColumn(i);
            if(oTableColumn.getPreferredWidth()!=0)
            {
                if(oTableColumn.getMinWidth()==0)
                {
                    oColumn=new itemColumn(tbl.getColumnName(i),false);
                    oColumn.setPreferedWidth(oTableColumn.getPreferredWidth());
                    oColumn.setMinWidth(oTableColumn.getMinWidth());
                    oColumn.setMaxWidth(oTableColumn.getMaxWidth());
                    oTableColumn.setMaxWidth(0);
                    oTableColumn.setMinWidth(0);
                    oTableColumn.setPreferredWidth(0);
                    oColumn.setoColumn(oTableColumn);
                }
                else
                {
                    oColumn=new itemColumn(tbl.getColumnName(i),true);
                    oColumn.setPreferedWidth(oTableColumn.getPreferredWidth());
                    oColumn.setMinWidth(oTableColumn.getMinWidth());
                    oColumn.setMaxWidth(oTableColumn.getMaxWidth());
                    oColumn.setoColumn(oTableColumn);
            }
            jPopupMenuColumna.add(oColumn);
            oListItem[i]=oColumn;
            }
        }
    }
    class itemColumn extends JCheckBox
    {
        private int preferedWidth;
        private int minWidth;
        private int maxWidth;
        private TableColumn oColumn;

        public itemColumn(String text, boolean b)
        {
            super(text, b);
            this.addItemListener(new ItemListener() {

                public void itemStateChanged(ItemEvent e) {
                   if(e.getStateChange()==e.SELECTED)
                   {
                        oColumn.setMinWidth(minWidth);
                        oColumn.setMaxWidth(maxWidth);
                        oColumn.setPreferredWidth(preferedWidth);
                   }
                   else
                   {
                        oColumn.setMinWidth(0);
                        oColumn.setMaxWidth(0);
                        oColumn.setPreferredWidth(0);
                   }
                }
            });
        }

        /**
         * @return the preferedWidth
         */
        public int getPreferedWidth() {
            return preferedWidth;
        }

        /**
         * @param preferedWidth the preferedWidth to set
         */
        public void setPreferedWidth(int preferedWidth) {
            this.preferedWidth = preferedWidth;
        }

        /**
         * @return the minWidth
         */
        public int getMinWidth() {
            return minWidth;
        }

        /**
         * @param minWidth the minWidth to set
         */
        public void setMinWidth(int minWidth) {
            this.minWidth = minWidth;
        }

        /**
         * @return the maxWidth
         */
        public int getMaxWidth() {
            return maxWidth;
        }

        /**
         * @param maxWidth the maxWidth to set
         */
        public void setMaxWidth(int maxWidth) {
            this.maxWidth = maxWidth;
        }

        /**
         * @return the oColumn
         */
        public TableColumn getoColumn() {
            return oColumn;
        }

        /**
         * @param oColumn the oColumn to set
         */
        public void setoColumn(TableColumn oColumn) {
            this.oColumn = oColumn;
        }
    }

}
