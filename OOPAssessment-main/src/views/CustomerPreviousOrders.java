/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Map;
import javax.swing.JOptionPane;

import javax.swing.table.DefaultTableModel;

import models.Customer;
import models.Order;

/**
 *
 * @author zeddu
 */
public class CustomerPreviousOrders extends javax.swing.JFrame {

    private Customer loggedInCustomer;
    
    /**
     * Creates new form CustomerPreviousOrders
     * @param customer
     */
    public CustomerPreviousOrders(Customer customer) {
        loggedInCustomer = customer;
        initComponents();
        this.getContentPane().setBackground(Color.lightGray); //set colour to light grey

        DefaultTableModel orderTable = (DefaultTableModel)tblOrders.getModel();

        //this will loop through all of the orders the customer has made and 
        //place them in the tblOrders table
        for(Map.Entry<Integer, Order> oEntry : customer.getOrders().entrySet())
        {
            Order order = oEntry.getValue();

            orderTable.addRow(new Object[]
            {
                order.getOrderId(),
                new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(order.getOrderDate()),
                "£" + order.getOrderTotal()
            });
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnBack = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblOrders = new javax.swing.JTable();
        btnOrderDetails = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        tblOrders.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order ID", "Date", "Total Cost"
            }
        ));
        jScrollPane1.setViewportView(tblOrders);

        btnOrderDetails.setText("View Order Details");
        btnOrderDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderDetailsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBack)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnOrderDetails))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBack)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnOrderDetails)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        CustomerHome cmenu = new CustomerHome(loggedInCustomer);
        this.dispose();
        cmenu.setVisible(true);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnOrderDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderDetailsActionPerformed
        //gets the currently selected order and gets the orderId
        if (tblOrders.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "No Order Selected");

        }
        int orderId = Integer.parseInt(String.valueOf(tblOrders.getValueAt(tblOrders.getSelectedRow(), 0)));

        //using the orderId from the selected Order, pass the orderId in to the OrderLines page
        ViewOrderDetails detailsMenu = new ViewOrderDetails(loggedInCustomer, orderId);
        this.dispose();
        detailsMenu.setVisible(true);
    }//GEN-LAST:event_btnOrderDetailsActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CustomerPreviousOrders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CustomerPreviousOrders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CustomerPreviousOrders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustomerPreviousOrders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new CustomerPreviousOrders().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnOrderDetails;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblOrders;
    // End of variables declaration//GEN-END:variables
}