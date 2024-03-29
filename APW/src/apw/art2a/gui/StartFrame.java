/*
 * StartFrame.java
 *
 * Created on 2009-08-07, 00:03:39
 */

package apw.art2a.gui;

import apw.core.Attribute;
import apw.core.Samples;
import apw.core.loader.ARFFLoader;
import apw.art2a.ART2A_Util;
import apw.art2a.Instance;
import apw.art2a.Network;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static apw.art2a.ART2A_Util.*;

/**
 *
 * @author nitric
 */
public class StartFrame extends JFrame {

    private JFileChooser fileChooser = new JFileChooser();
    private File file;
    private ArrayList<Instance> instances;
    private double a, b, t, r;
    private int columns, passes;
    private Network network;
    private ART2A_Util util = new ART2A_Util();
    
    /** Creates new form StartFrame */
    public StartFrame() {
        initComponents();
    }

    private void error(String cause) {
        JOptionPane.showMessageDialog(this, cause);
    }

    private void retrieveParameters(boolean fromScratch) {
        try {
            if (!fromScratch) {
                // passes = Integer.parseInt(jtf_passesOrColumns.getText());
                columns = instances.get(0).getLength();
                System.out.println("columns = " + columns);
            }
            a = Double.parseDouble(jtf_alpha.getText());
            b = Double.parseDouble(jtf_beta.getText());
            r = Double.parseDouble(jtf_rho.getText());
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(
                    "<html><body>Parameters should be real numbers (except <i>passes</i>, which " +
                    "must be integer)!</html></body>");
        }
    }

    private String[] retrieveColumnNames(Samples samples) {
        String[] result = new String[columns];
        int classAtt = samples.getClassAttributeIndex();
        int counter = 0;
        ArrayList<Attribute> atts = samples.getAtts();
        for (int i = 0; i < samples.getAtts().size(); i++)
            if (i != classAtt)
                result[counter++] = atts.get(i).getName();
        return result;
    }

    private void networkFromScratch() {
        try {
            t = Double.parseDouble(jtf_theta.getText());
            columns = Integer.parseInt(jtf_passesOrColumns.getText());
            if (columns < 1) {
                JOptionPane.showMessageDialog(this, "Amount of attributes must be positive!");
                return;
            }
            retrieveParameters(true);
            util.setParameters(a, b, r, t, columns);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Incorect number format!");
            return;
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
            return;
        }
        network = util.createNetworkForOnlineLearning(a, b, r, t, columns);
        // Wait for the column (attributes) names and then continue with method...
        new ColumnNameRetriever(columns, this).setVisible(true);
    }

    private void networkFromScratchContinue(String[] names) {
        network.setColumnNames(names);
        System.out.println("Network initialized successfully.");
        // this.dispose();
        new PrototypesFrame(network).setVisible(true);
    }

    private void networkFromSamples() {
        Samples samples;
        try {
            file = new File(jtf_filePath.getText());
            // retrieving samples:
            samples = new ARFFLoader(file).getSamples();
            t = Double.parseDouble(jtf_theta.getText());
            // converting to instances:
            instances = util.shuffleInstances(util.convertSamples(samples, t));
            retrieveParameters(false);
            util.setParameters(a, b, r, t, columns);
            network = util.createAndLearnNetwork(a, b, r, t, passes, instances);
            if (network == null)                    // Learning can be unsuccesful!
                return;
            network.setColumnNames(retrieveColumnNames(samples));
            network.setSamples(samples);
            System.out.println("Network created successfully.");
            showStats(network, instances, samples);
            // this.dispose();
            new PrototypesFrame(network).setVisible(true);
        } catch (FileNotFoundException ex) {
            error("Cannot find specified file.");
        } catch (IOException ex) {
            error("Couldn't open specified file.");
        } catch (NumberFormatException ex) {
            error("Please type all the parameters!");
        } catch (ParseException ex) {
            error("Error while parsing data file, offset: " + ex.getErrorOffset());
        } catch (IllegalArgumentException ex) {
            error(ex.getMessage());
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jtf_filePath = new javax.swing.JTextField();
        jb_choose = new javax.swing.JButton();
        jp_parameters = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtf_alpha = new javax.swing.JTextField();
        jtf_beta = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jtf_theta = new javax.swing.JTextField();
        jtf_rho = new javax.swing.JTextField();
        jl_passesOrColumns = new javax.swing.JLabel();
        jtf_passesOrColumns = new javax.swing.JTextField();
        jb_cancel = new javax.swing.JButton();
        jb_ok = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jcb_learningMode = new javax.swing.JComboBox();
        jb_help = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Data set:");

        jtf_filePath.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtf_filePathFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtf_filePathFocusLost(evt);
            }
        });

        jb_choose.setText("Choose");
        jb_choose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_chooseActionPerformed(evt);
            }
        });

        jp_parameters.setBorder(javax.swing.BorderFactory.createTitledBorder("Parameters"));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("alpha:");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("beta:");

        jtf_alpha.setText("0.1");

        jtf_beta.setText("0.05");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("theta:");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("vigilance:");

        jtf_theta.setText("0");

        jtf_rho.setText("0.992");

        jl_passesOrColumns.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jl_passesOrColumns.setText("amount of attributes:");

        jtf_passesOrColumns.setEnabled(false);

        javax.swing.GroupLayout jp_parametersLayout = new javax.swing.GroupLayout(jp_parameters);
        jp_parameters.setLayout(jp_parametersLayout);
        jp_parametersLayout.setHorizontalGroup(
            jp_parametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_parametersLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_parametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_parametersLayout.createSequentialGroup()
                        .addGroup(jp_parametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jp_parametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtf_beta)
                            .addComponent(jtf_alpha, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jp_parametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jl_passesOrColumns, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_parametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jtf_passesOrColumns)
                    .addComponent(jtf_rho, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtf_theta, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
                .addContainerGap())
        );
        jp_parametersLayout.setVerticalGroup(
            jp_parametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_parametersLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jp_parametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtf_alpha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jtf_theta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jp_parametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtf_beta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jtf_rho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jp_parametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl_passesOrColumns)
                    .addComponent(jtf_passesOrColumns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jb_cancel.setText("Cancel");
        jb_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_cancelActionPerformed(evt);
            }
        });

        jb_ok.setText("OK");
        jb_ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_okActionPerformed(evt);
            }
        });

        jLabel8.setText("Learning mode:");

        jcb_learningMode.setModel(new javax.swing.DefaultComboBoxModel(LearningMode.values()));
        jcb_learningMode.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcb_learningModeItemStateChanged(evt);
            }
        });

        jb_help.setText("Help");
        jb_help.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_helpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jp_parameters, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jb_help, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jb_ok, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jb_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtf_filePath, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jb_choose, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jcb_learningMode, 0, 210, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jcb_learningMode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtf_filePath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jb_choose))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jp_parameters, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jb_ok)
                        .addComponent(jb_help))
                    .addComponent(jb_cancel))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jb_chooseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_chooseActionPerformed
        int wynik = fileChooser.showOpenDialog(this);
        if (wynik == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            jtf_filePath.setText(file.getAbsolutePath());
        }
    }//GEN-LAST:event_jb_chooseActionPerformed

    private void jb_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_cancelActionPerformed
        this.dispose();
        System.exit(0);
    }//GEN-LAST:event_jb_cancelActionPerformed

    private void jb_okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_okActionPerformed
        if (jcb_learningMode.getSelectedItem().equals(LearningMode.INTERACTIVE))
            networkFromScratch();
        else
            networkFromSamples();
    }//GEN-LAST:event_jb_okActionPerformed

    private void jcb_learningModeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcb_learningModeItemStateChanged
        boolean mode = evt.getItem().equals((LearningMode.INTERACTIVE));
        jtf_filePath.setEnabled(!mode);
        jb_choose.setEnabled(!mode);
        jtf_passesOrColumns.setEnabled(mode);
    }//GEN-LAST:event_jcb_learningModeItemStateChanged

    private void jtf_filePathFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtf_filePathFocusGained
        jtf_filePath.selectAll();
    }//GEN-LAST:event_jtf_filePathFocusGained

    private void jtf_filePathFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtf_filePathFocusLost
        jtf_filePath.select(0, 0);
    }//GEN-LAST:event_jtf_filePathFocusLost

    private void jb_helpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_helpActionPerformed
        new JD_ART2A_Help(this, false).setVisible(true);
    }//GEN-LAST:event_jb_helpActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StartFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JButton jb_cancel;
    private javax.swing.JButton jb_choose;
    private javax.swing.JButton jb_help;
    private javax.swing.JButton jb_ok;
    private javax.swing.JComboBox jcb_learningMode;
    private javax.swing.JLabel jl_passesOrColumns;
    private javax.swing.JPanel jp_parameters;
    private javax.swing.JTextField jtf_alpha;
    private javax.swing.JTextField jtf_beta;
    private javax.swing.JTextField jtf_filePath;
    private javax.swing.JTextField jtf_passesOrColumns;
    private javax.swing.JTextField jtf_rho;
    private javax.swing.JTextField jtf_theta;
    // End of variables declaration//GEN-END:variables

    void setColumnNames(ArrayList<String> list) {
        String[] result = list.toArray(new String[] { });
        networkFromScratchContinue(result);
    }
}
