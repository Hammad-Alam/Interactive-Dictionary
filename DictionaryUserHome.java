import javax.swing.*;
import java.awt.*;

public class DictionaryUserHome extends javax.swing.JFrame {

    public String text;
    public DictionaryUserHome() {
        initComponents();
    }
    public DictionaryUserHome(String name) {
        initComponents();
        setSize(800, 700);
        setLocationRelativeTo(null);
        text = name;
        jLabel1.setText(text);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        JMenu jMenu1 = new JMenu();
        JMenu jMenu2 = new JMenu();

        Font font = new Font(" ", Font.ROMAN_BASELINE, 20);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dictionary User Home");

        jLabel1.setFont(font);
        jLabel1.setText("jLabel1");

        jMenuBar2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dashboard", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 24), new java.awt.Color(255, 51, 51))); // NOI18N

        jMenu1.setText("Search Word");
        jMenu1.setFont(font);
        jMenu1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenu1.setMargin(new java.awt.Insets(5, 5, 10, 10));
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar2.add(jMenu1);

        jMenu4.setText("View Words");
        jMenu4.setFont(font);
        jMenu4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenu4.setMargin(new java.awt.Insets(5, 5, 10, 10));
        jMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu4MouseClicked(evt);
            }
        });
        jMenuBar2.add(jMenu4);

        jMenu2.setText("History");
        jMenu2.setFont(font);
        jMenu2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenu2.setMargin(new java.awt.Insets(5, 5, 10, 10));
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar2.add(jMenu2);

        jMenu3.setText("Logout");
        jMenu3.setFont(font);
        jMenu3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenu3.setMargin(new java.awt.Insets(5, 5, 10, 10));
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenuBar2.add(jMenu3);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(490, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addGap(51, 51, 51))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addContainerGap(380, Short.MAX_VALUE))
        );

        pack();
    }

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {
        setVisible(false);
        new SearchGUI(text).setVisible(true);
    }
    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {
        setVisible(false);
        new HistoryGUI(text).setVisible(true);
    }
    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {
        // Create a new JFrame for the logout dialog
        JFrame logout = new JFrame();
        logout.setAlwaysOnTop(true); // Set the logout dialog to be always on top

        // Display a confirmation dialog box using JOptionPane
        int i = JOptionPane.showConfirmDialog(logout, "Do you really want to Logout?", "Select", JOptionPane.YES_NO_OPTION);

        // Check the user's choice in the confirmation dialog
        if(i == 0) { // If the user selects "Yes" (option value 0)
            setVisible(false); // Hide the current JFrame (assuming this code is inside a JFrame class)
            new Login().setVisible(true); // Create and display a new instance of the Login JFrame
        }
    }

    private void jMenu4MouseClicked(java.awt.event.MouseEvent evt) {
        setVisible(false);
        new ViewGUI(text).setVisible(true);
    }

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
            java.util.logging.Logger.getLogger(DictionaryUserHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DictionaryUserHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DictionaryUserHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DictionaryUserHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DictionaryUserHome().setVisible(true);
            }
        });
    }

    // Variables declaration
    public javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar2;
    // End of variables declaration
}