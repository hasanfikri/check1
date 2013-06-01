package browse;

import java.io.IOException; 
//import org.apache.commons.io.//
import org.junit.*;
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
import java.io.*;
import java.lang.*;
import java.lang.Class;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author King Fikri
 */
public class sk1 extends javax.swing.JFrame 
{
    
    int Mcount=0, MthdLen=0;
    int mmod=0, mlen=0, jumtemp;
    String files;
    String target = "D:/File Kuliah/Skripsi & proposal/Revisi";
    JFileChooser fileChooser = new JFileChooser();
    StringBuilder s = new StringBuilder();
    Formatter fm = new Formatter(s);
	
    
private String txttemp="";

    /**
     * Creates new form sk1
     */
    public sk1() {
        initComponents();
        setSize(1300, 700);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton2.setText("Load");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Choose File");

        jTextField1.setEditable(false);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 861, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(58, 58, 58)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 20, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void showOpenFileDialog()
    {
    	fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showOpenDialog(this);
	        if (result == JFileChooser.APPROVE_OPTION) 
	        {
                            
	        	    File selectedFile = fileChooser.getSelectedFile();
	        	    File[] ll=selectedFile.listFiles();
           			System.out.println("Selected file: " + selectedFile.getAbsolutePath());
           			jTextField1.setText(selectedFile.getAbsolutePath());

	        	for (int i = 0; i < ll.length; i++) 
				  {
				   if (ll[i].isFile()) 
				   {
				   	  	files = ll[i].getName();
                                                
				    if (files.endsWith(".class"))
				       {
				       	StringTokenizer st=new StringTokenizer(files, ".");
				       	String nama=st.nextToken();
				       						
				       	if(nama.contains("sk1"))
				       		{
				       			continue;
				       		}
                                        /*File awal =new File(files); 
                                        
                                        File targetFile = new File(target+files);
                                        System.out.println("Copying file : " + files+" from Java Program");
                                        FileUtils.copyFile(awal, targetFile);*/
                                        //copy file from one location to other

     
                                        /*try
				    		{
								dest = dest.replace("/", "\\");
								Process p = Runtime.getRuntime().exec("cmd.exe copy \"" + selectedFile.toString() + "\\" + files + "\" \"" + dest + "\"");
								System.out.println("cmd.exe copy \"" + selectedFile.toString() + "\\" + files + "\" \"" + dest + "\"");
								//String[] command = { "cmd.exe /c", "copy",selectedFile, dest };
								//Process p = Runtime.getRuntime().exec(command);
							}
							catch (IOException e)
							{
								 e.printStackTrace();
							}*/
					System.out.println();
				       	System.out.println("Nama Kelas: "+nama);
				       	jTextArea1.append("Nama Kelas = "+nama + "\n");
				       	 
				       	try
				       	{
				       		
				       	String m1="";
				       	Class cls = Class.forName(nama);
				       	Method methlist[]= cls.getDeclaredMethods();
				       	for (int n = 0; n < methlist.length;n++)
					        {  
						         Method m = methlist[n];
						         //MthdLen=MthdLen+(m.getName().length()); //Panjang Nama Method Keseluruhan
						         m1=m1+(m.getName()+", ");
						         //break;
						    }
						    
						Field[] fields = cls.getDeclaredFields();
				        
				        for (Field field : fields) 
				        	{
						        //Cetak Nama Attribute
						        fm.format(" %s %s %s ", Modifier.toString(field.getModifiers()),
						        field.getType().getSimpleName(),field.getName());
						        
						        //Cetak Nama Attribute
						        /*jTextArea1.append("Nama Attribut = "+"%s %s %s %n", Modifier.toString(field.getModifiers()),
						        field.getType().getSimpleName(),field.getName());*/
					        }
					         /*if (type.getSuperclass() != null) 
					         	{
							        fields = getAllFields(fields, type.getSuperclass());
							    }*/
							if(methlist.length==0)
					        {
					        	m1="Tidak Ada";
					        }
						jTextArea1.append("Jumlah Method = " + methlist.length+"\n");
						jTextArea1.append("Nama Method = " + m1+"\n");
						jTextArea1.append("Jumlah Attribut = "+fields.length+"\n");
						jTextArea1.append("Nama Attribute = "+s.toString()+"\n \n");
						s.setLength(0);
						
						
						
						System.out.printf("%d fields:%n", fields.length);
						System.out.println("Jumlah Method = " + methlist.length);
				        System.out.println("Nama Method = " + m1);   
				        //System.out.println("Total Method = " + MthdLen);   //Panjang Nama Method Keseluruhan
				        
				        }
				       	catch (Throwable e) 
				         {
				           System.err.println(e);
				         } 
				        
						
					   }
				     }
				  }
	        }
        
    }
    
    /*private void copy()throws IOException
    {
     FileUtils.copyFile(selectedFile, targetFile);
    }*/
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) 
    {
        showOpenFileDialog();
        /*try 
    {
            //GEN-FIRST:event_jButton2ActionPerformed
// TODO add your handling code here:
        showOpenFileDialog();
        } catch (IOException ex) {
            Logger.getLogger(sk1.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) 
    {
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
            java.util.logging.Logger.getLogger(sk1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(sk1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(sk1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(sk1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                new sk1().setVisible(true);
                
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}

