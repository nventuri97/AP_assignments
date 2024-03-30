/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package uni.adavanced_programming.the8puzzle;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.VetoableChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author nicola
 */
public class EightBoard extends javax.swing.JFrame implements PropertyChangeListener{
    
    private final static Logger logger=LogManager.getLogger(EightBoard.class);
    private ArrayList<Integer> randomInizialization=new ArrayList<Integer>();
    private VetoableChangeSupport vetos = new VetoableChangeSupport( this );
    private ArrayList<EightTile> tiles=new ArrayList<EightTile>();

    /**
     * Creates new form EightBoard
     */
    public EightBoard() {
        
        for(int i=0;i<9;i++)
            randomInizialization.add(i+1);
        Collections.shuffle(randomInizialization);
        
        logger.debug("Initializing new components with random configuration");
        initComponents();
        
        for(EightTile t: tiles){
            this.addPropertyChangeListener(t);
            t.addVetoableChangeListener(eightController1);
            eightController1.addControllerPropertyChangeListener(t);
        }
        
        // Add property change listener to controller and register it with the controller.
        this.addPropertyChangeListener(eightController1);
        
        // Add vetoable change listener to the controller for managing game state.
        this.vetos.addVetoableChangeListener(eightController1);
        eightController1.setCurrentBoardConfiguration(randomInizialization);
        flipButton1.addVetoableChangeListener(eightController1);
    }
    
    /**
     *
     * @param pce
     */
    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        if(!pce.getPropertyName().equals("restart")){
            logger.error("");
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

        jPanel1 = new javax.swing.JPanel();
        eightTile1 = new uni.adavanced_programming.the8puzzle.EightTile(1,this.randomInizialization.get(0).toString());
        eightTile2 = new uni.adavanced_programming.the8puzzle.EightTile(2, this.randomInizialization.get(1).toString());
        eightTile3 = new uni.adavanced_programming.the8puzzle.EightTile(3, this.randomInizialization.get(2).toString());
        eightTile4 = new uni.adavanced_programming.the8puzzle.EightTile(4, this.randomInizialization.get(3).toString());
        eightTile5 = new uni.adavanced_programming.the8puzzle.EightTile(5, this.randomInizialization.get(4).toString());
        eightTile6 = new uni.adavanced_programming.the8puzzle.EightTile(6,this.randomInizialization.get(5).toString());
        eightTile7 = new uni.adavanced_programming.the8puzzle.EightTile(7,this.randomInizialization.get(6).toString());
        eightTile8 = new uni.adavanced_programming.the8puzzle.EightTile(8, this.randomInizialization.get(7).toString());
        eightTile9 = new uni.adavanced_programming.the8puzzle.EightTile(9,this.randomInizialization.get(8).toString());
        eightController1 = new uni.adavanced_programming.the8puzzle.EightController();
        flipButton1 = new uni.adavanced_programming.the8puzzle.FlipButton();
        restartButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("The 8 puzzle");

        tiles.add(eightTile1);
        eightTile1.setMaximumSize(new java.awt.Dimension(72, 80));
        eightTile1.setMinimumSize(new java.awt.Dimension(72, 80));
        eightTile1.setPreferredSize(new java.awt.Dimension(72, 80));
        eightTile1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eightTile1ActionPerformed(evt);
            }
        });

        tiles.add(eightTile2);
        eightTile2.setMaximumSize(new java.awt.Dimension(72, 80));
        eightTile2.setMinimumSize(new java.awt.Dimension(72, 80));
        eightTile2.setPreferredSize(new java.awt.Dimension(72, 80));
        eightTile2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eightTile2ActionPerformed(evt);
            }
        });

        tiles.add(eightTile3);
        eightTile3.setMaximumSize(new java.awt.Dimension(72, 80));
        eightTile3.setMinimumSize(new java.awt.Dimension(72, 80));
        eightTile3.setPreferredSize(new java.awt.Dimension(72, 80));
        eightTile3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eightTile3ActionPerformed(evt);
            }
        });

        tiles.add(eightTile4);
        eightTile4.setMaximumSize(new java.awt.Dimension(72, 80));
        eightTile4.setMinimumSize(new java.awt.Dimension(72, 80));
        eightTile4.setPreferredSize(new java.awt.Dimension(72, 80));
        eightTile4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eightTile4ActionPerformed(evt);
            }
        });

        tiles.add(eightTile5);
        eightTile5.setMaximumSize(new java.awt.Dimension(72, 80));
        eightTile5.setMinimumSize(new java.awt.Dimension(72, 80));
        eightTile5.setPreferredSize(new java.awt.Dimension(72, 80));
        eightTile5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eightTile5ActionPerformed(evt);
            }
        });

        tiles.add(eightTile6);
        eightTile6.setMaximumSize(new java.awt.Dimension(72, 80));
        eightTile6.setMinimumSize(new java.awt.Dimension(72, 80));
        eightTile6.setPreferredSize(new java.awt.Dimension(72, 80));
        eightTile6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eightTile6ActionPerformed(evt);
            }
        });

        tiles.add(eightTile7);
        eightTile7.setMaximumSize(new java.awt.Dimension(72, 80));
        eightTile7.setMinimumSize(new java.awt.Dimension(72, 80));
        eightTile7.setPreferredSize(new java.awt.Dimension(72, 80));
        eightTile7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eightTile7ActionPerformed(evt);
            }
        });

        tiles.add(eightTile8);
        eightTile8.setMaximumSize(new java.awt.Dimension(72, 80));
        eightTile8.setMinimumSize(new java.awt.Dimension(72, 80));
        eightTile8.setPreferredSize(new java.awt.Dimension(72, 80));
        eightTile8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eightTile8ActionPerformed(evt);
            }
        });

        tiles.add(eightTile9);
        eightTile9.setMaximumSize(new java.awt.Dimension(72, 80));
        eightTile9.setMinimumSize(new java.awt.Dimension(72, 80));
        eightTile9.setPreferredSize(new java.awt.Dimension(72, 80));
        eightTile9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eightTile9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(eightTile7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eightTile8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eightTile9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(eightTile1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(eightTile4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(eightTile5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(eightTile6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(eightTile2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(eightTile3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(eightTile3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eightTile1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(eightTile2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(eightTile4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(eightTile6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(eightTile5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eightTile8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eightTile7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eightTile9, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        eightController1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        eightController1.setText("START");
        eightController1.setMaximumSize(new java.awt.Dimension(112, 18));
        eightController1.setMinimumSize(new java.awt.Dimension(112, 18));
        eightController1.setPreferredSize(new java.awt.Dimension(112, 18));

        flipButton1.setLabel("FLIP");
        flipButton1.setPreferredSize(new java.awt.Dimension(100, 24));
        flipButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                flipButton1ActionPerformed(evt);
            }
        });

        restartButton.setText("RESTART");
        restartButton.setPreferredSize(new java.awt.Dimension(100, 24));
        restartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restartButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(eightController1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(restartButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(flipButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(flipButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(restartButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(eightController1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 
    private void eightTile3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eightTile3ActionPerformed
        PropertyChangeEvent event=new PropertyChangeEvent(eightTile3, "click", eightTile3.getLabel(), "9");
        eightTile3.propertyChange(event);
        //this.firePropertyChange("click", eightTile3.getLabel(), "9");
    }//GEN-LAST:event_eightTile3ActionPerformed

    private void eightTile1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eightTile1ActionPerformed
        PropertyChangeEvent event=new PropertyChangeEvent(eightTile1, "click", eightTile1.getLabel(), "9");
        eightTile1.propertyChange(event);
        //this.firePropertyChange("click", eightTile1.getLabel(), "9");
    }//GEN-LAST:event_eightTile1ActionPerformed

    private void eightTile2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eightTile2ActionPerformed
        PropertyChangeEvent event=new PropertyChangeEvent(eightTile2, "click", eightTile2.getLabel(), "9");
        eightTile2.propertyChange(event);
        //this.firePropertyChange("click", eightTile2.getLabel(), "9");
    }//GEN-LAST:event_eightTile2ActionPerformed

    private void eightTile4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eightTile4ActionPerformed
        PropertyChangeEvent event=new PropertyChangeEvent(eightTile4, "click", eightTile4.getLabel(), "9");
        eightTile4.propertyChange(event);
        //this.firePropertyChange("click", eightTile4.getLabel(), "9");
    }//GEN-LAST:event_eightTile4ActionPerformed

    private void eightTile5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eightTile5ActionPerformed
        PropertyChangeEvent event=new PropertyChangeEvent(eightTile5, "click", eightTile5.getLabel(), "9");
        eightTile5.propertyChange(event);
        //this.firePropertyChange("click", eightTile5.getLabel(), "9");
    }//GEN-LAST:event_eightTile5ActionPerformed

    private void eightTile7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eightTile7ActionPerformed
        PropertyChangeEvent event=new PropertyChangeEvent(eightTile7, "click", eightTile7.getLabel(), "9");
        eightTile7.propertyChange(event);
        //this.firePropertyChange("click", eightTile7.getLabel(), "9");
    }//GEN-LAST:event_eightTile7ActionPerformed

    private void eightTile8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eightTile8ActionPerformed
        PropertyChangeEvent event=new PropertyChangeEvent(eightTile8, "click", eightTile8.getLabel(), "9");
        eightTile8.propertyChange(event);
        //this.firePropertyChange("click", eightTile8.getLabel(), "9");
    }//GEN-LAST:event_eightTile8ActionPerformed

    private void eightTile9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eightTile9ActionPerformed
        PropertyChangeEvent event=new PropertyChangeEvent(eightTile9, "click", eightTile9.getLabel(), "9");
        eightTile9.propertyChange(event);
        //this.firePropertyChange("click", eightTile9.getLabel(), "9");
    }//GEN-LAST:event_eightTile9ActionPerformed

    private void eightTile6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eightTile6ActionPerformed
        PropertyChangeEvent event=new PropertyChangeEvent(eightTile6, "click", eightTile6.getLabel(), "9");
        eightTile6.propertyChange(event);
        //this.firePropertyChange("click", eightTile6.getLabel(), "9");
    }//GEN-LAST:event_eightTile6ActionPerformed

    private void flipButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_flipButton1ActionPerformed
        PropertyChangeEvent event=new PropertyChangeEvent(flipButton1, "flip",eightTile1.getLabel(), eightTile2.getLabel());
        flipButton1.propertyChange(event);
    }//GEN-LAST:event_flipButton1ActionPerformed

    private void restartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restartButtonActionPerformed
        Collections.shuffle(randomInizialization);
        
        logger.debug("Fired a new restart event to generate new board configuration");  
        this.firePropertyChange("restart", null, randomInizialization);
    }//GEN-LAST:event_restartButtonActionPerformed

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
            java.util.logging.Logger.getLogger(EightBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EightBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EightBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EightBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        PropertyConfigurator.configure("src/main/java/uni/adavanced_programming/the8puzzle/log4j.properties");
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EightBoard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private uni.adavanced_programming.the8puzzle.EightController eightController1;
    private uni.adavanced_programming.the8puzzle.EightTile eightTile1;
    private uni.adavanced_programming.the8puzzle.EightTile eightTile2;
    private uni.adavanced_programming.the8puzzle.EightTile eightTile3;
    private uni.adavanced_programming.the8puzzle.EightTile eightTile4;
    private uni.adavanced_programming.the8puzzle.EightTile eightTile5;
    private uni.adavanced_programming.the8puzzle.EightTile eightTile6;
    private uni.adavanced_programming.the8puzzle.EightTile eightTile7;
    private uni.adavanced_programming.the8puzzle.EightTile eightTile8;
    private uni.adavanced_programming.the8puzzle.EightTile eightTile9;
    private uni.adavanced_programming.the8puzzle.FlipButton flipButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton restartButton;
    // End of variables declaration//GEN-END:variables

}
