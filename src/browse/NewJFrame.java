/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package browse;

import java.io.IOException; 
import java.io.FileInputStream;

import org.apache.commons.io.FileUtils;
import javax.swing.*;
import java.util.*;
import java.lang.reflect.*;
import java.util.StringTokenizer;
import java.util.Scanner;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.lang.String.*;
import java.lang.*;
import java.lang.Class;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.nio.channels.FileChannel;
/**
 *
 * @author king
 */
public class NewJFrame extends javax.swing.JFrame 
{
    public String att_kosong="Tidak mempunyai Attribute";
    public String mtd_kosong="Tidak mempunyai Method";
    public String batas="\n===================================================================================== \n";
    int Mcount=0, MthdLen=0, var=0;
    int mmod=0, mlen=0, jumtemp;
    int a=0;
    String files;
    //target file
    String target = "D:/File Kuliah/Skripsi & proposal/Revisi";
    JFileChooser fileChooser = new JFileChooser();
    
    
    File selectedFile;
    
    public NewJFrame() 
    {
        initComponents();
        
    }
    private void showOpenFileDialog() 
    	  	{
    	fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) 
            {
                selectedFile = fileChooser.getSelectedFile();
                File[] ll=selectedFile.listFiles();
                System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                jTextField1.setText(selectedFile.getAbsolutePath());
        	for (int i = 0; i < ll.length; i++) 
                {
                    if (ll[i].isFile()) 
                    {
                        files = ll[i].getName();
                        
                        double filesize = files.length();
                        double filesizeInKB = filesize / 1024;
                        double fileinbyte = filesizeInKB/ 1024;
                        
                        //jTextArea3.append(files+"\t"+filesizeInKB+" KB\n");
                           if (files.endsWith(".class"))
			    {
                                StringTokenizer st=new StringTokenizer(files, ".");
                                String nama=st.nextToken();
                                
                                System.out.println();
                                System.out.println("Nama Kelas: "+nama);
                                //System.out.println("Size of File is: "+nama +" "+filesizeInKB + " KB");
                                //System.out.println("Size of File is: "+nama +" "+fileinbyte + " KB");
                                jTextArea1.append("Nama Kelas = "+nama + "\n");
                                
                                try
                                {
                                String m1="";
                                String m2a="";
                                String m3a="";
                                StringBuilder s = new StringBuilder();
                                Formatter fm = new Formatter(s);

                                StringBuilder s2 = new StringBuilder();
                                Formatter fm2 = new Formatter(s2);

                                StringBuilder s3 = new StringBuilder();
                                Formatter fm3 = new Formatter(s3);
                                
                                Class cls = Class.forName(nama);
                                Class<?> cls2= cls.getSuperclass();
                                Class<?> cls3=cls2.getSuperclass();
                                
                                
                                Method methlist[]= cls.getDeclaredMethods();
                                
                                Method methlist3[]= cls3.getDeclaredMethods();
                                
                                
                                for (int n = 0; n < methlist.length;n++)
                                {  
                                    Method m = methlist[n];
                                    //Memfilter method yng mengandung string "java."
                                    if (m.toString().contains("java."))
                                    {
                                       continue;
                                    }
                                    m1=m1+m.getName()+", ";
                                }
                                
                                Field[] fields = cls.getDeclaredFields();
                                for (Field field : fields) 
                                {
                                        //Cetak Nama Attribute Kelas Lokal
                                        //System.out.println("Nama Attribute = "+field.getName());
                                        jTextArea1.append("Nama Attribute = "+field.getName()+"\n");
                                }
                                
                                         
                                /*try
                                {
                                    
                                }
                                catch(Exception e)
                                {
                                    
                                }*/
                                Method methlist2[]= cls2.getDeclaredMethods();
                                for (int j=0;j<methlist2.length; j++)
                                {
                                    Method m2 = methlist2[j];
                                    //Memfilter method yng mengandung string "private."
                                    if (m2.toString().contains("private"))
                                    {
                                        continue;
                                    }
                                    m2a=m2a+(m2.getName()+", ");
                                }   
                                jTextArea2.append("Nama Super Kelas dari "+cls.getName() +" adalah "+cls2.getName()+"\n");
                                
                                Field[] fields2 = cls2.getDeclaredFields();
                                
                                for (Field field : fields2) 
                                {
                                    if (field.toString().contains("private"))
                                        {
                                            continue;
                                            //jTextArea2.append("INI PRIVATE");
                                        }
                                    jTextArea2.append("Nama Attribute yang diwariskan= "+field.getName()+"\n");
                                    
                                 }
                                jTextArea2.append("Jumlah Attribut Yang diwariskan = "+fields2.length+"\n");
                                
                                //list method kelas nenek moyang
                                for (int k=0;k<methlist3.length; k++)
                                {
                                    Method m3 = methlist3[k];
                                    if (m3.getName().contains("private"))
                                    {
                                       continue;
                                    }
                                    else
                                    {
                                    m3a=m3a+(m3.getName()+" ");
                                    }
                                }
                                if(m3a.length()==0)
                                {
                                    
                                }
                                Field[] fields3 = cls3.getDeclaredFields();
                                for (Field field : fields3) 
                                {
                                        jTextArea3.append("Nama Attribute = "+field.getName()+"\n");
                                }
                                if(fields3.length==0)
                                {
                                    //continue;
                                }
                                
                            //   if(cls2.g)
                            jTextArea1.append("Jumlah Attribut = "+fields.length+"\n");
                            jTextArea1.append("Jumlah Method = " + methlist.length+"\n");
                            jTextArea1.append("Nama Method = " + m1+"\n");
                            jTextArea1.append(batas);
                            
                            if(cls2.getName().contains("java.lang.Object"))
                                {
                                    continue;
                                }
                                    //jTextArea2.append("Jumlah Attribut Yang diwariskan = "+fields2.length+"\n");
                                    jTextArea2.append("Jumlah Method Yang Di wariskan = " + methlist2.length+"\n");
                                    jTextArea2.append("Nama Method Yang Di wariskan = " + m2a+"\n");
                                    jTextArea2.append(batas);
                            
                            if(cls3.getName().contains("java.lang.Object"))
                                {
                                    continue;
                                }
                            
                                    jTextArea3.append("Nama Kelas = "+cls3.getName()+"\n");
                                    jTextArea3.append("Jumlah Method = " + methlist3.length+"\n");
                                    jTextArea3.append("Nama Method = " + m3a+"\n");
                                    jTextArea3.append("Jumlah Attribut Yang diwariskan = "+fields3.length+"\n");
                                    jTextArea3.append("Nama Attribute Yang diwariskan = "+s3.toString());
                                    jTextArea3.append(batas);
                                
                                    
                            
                            s2.setLength(0);
                            s3.setLength(0);

                            //System.out.println("Nama Attribute " + fields.toString());
                            System.out.println("Julmah Attribute = "+fields.length);
                            System.out.println("Nama Method = " + m1);
                            System.out.println("Jumlah Method = " + methlist.length);
                            
                            
                            //System.out.println("Nama Attribute = "+field.getN);
                            
				        //System.out.println("Total Method = " + MthdLen);   //Panjang Nama Method Keseluruhan
				        
				}
                                catch (Exception e) 
                                 {
                                   //System.err.println(e.toString());
                                    jTextArea1.append(att_kosong+"\n"+mtd_kosong+batas);
                                    System.out.println("jml met kosong");
                                    System.out.println("jml att kosong");
                                 } 
			}
	     }
	  }
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
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Alamat");

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton2.setText("Proses");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jButton1)
                .addGap(31, 31, 31)
                .addComponent(jButton2)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel2.setText("Kelas Lokal");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel3.setText("Kelas Super");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(596, 596, 596))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane3.setViewportView(jTextArea3);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel4.setText("Kelas Nenek Moyang");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(75, 75, 75))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(325, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(155, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    /*private void kopi()
    {
        FileUtils.copyFileToDirectory(files, target);
        
    }*/
    
    private void cetak_super_kosong()
    {
        
        jTextArea2.append("Nama Kelas = "+"Tidak Mempunyai Super Kelas\n");
        jTextArea2.append("Jumlah Method Yang diwariskan = "+var+"\n");
        jTextArea2.append("Nama Method Yang diwariskan = "+var+"\n");
        jTextArea2.append("Jumlah Attribut Yang diwariskan = "+var+"\n");
        jTextArea2.append("Nama Attribute Yang diwariskan = "+var+"\n"+"\n===================================================================================== \n");
    }
    
    
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        showOpenFileDialog();
        
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
                
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
