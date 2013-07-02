/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package browse;
import browse.Informasi;
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
import java.io.FileOutputStream;
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
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author king
 */
public class NewJFrame extends javax.swing.JFrame 
{
    DefaultTableModel tabel ;
    public double ukuran;
    private String att_kosong="Tidak mempunyai Attribute";
    private String mtd_kosong="Tidak mempunyai Method";
    public String batas="\n===================================================================================== \n";
    int Mcount=0, MthdLen=0, var=0;
    int mmod=0, mlen=0, jumtemp;
    int a=0;
    public String files; 
    String laporan = "laporan.txt";
    //target file
    String target = "E:/File Skripsi/program/check1/build/classes/";
    
    JFileChooser fileChooser = new JFileChooser();
    
    FileOutputStream tulis;

    File selectedFile;
    
    public NewJFrame() 
    {
        initComponents();
        
        
    }
private void showOpenFileDialog(){
    fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    int result = fileChooser.showOpenDialog(this);
    if (result == JFileChooser.APPROVE_OPTION) 
    {
        selectedFile = fileChooser.getSelectedFile();
        File[] ll=selectedFile.listFiles();
        System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        jTextField1.setText(selectedFile.getAbsolutePath());

        //Copy File
        int method_parent = 0;
        int method_local=0;
        int atr_parent = 0;
        int atr_local=0;
                
        for (int i = 0; i < ll.length; i++) 
        {
            if (ll[i].isFile()) 
            {
                files = ll[i].getName();
                File source = new File(ll[i].getPath());
                File destination = new File(target + files);
                try
                {
                    FileUtils.copyFile(source, destination);
                }
                catch(Exception aa){
                    System.out.println("salah");
                }
                        
             }
          }
        
        //ArrayList<Informasi> descs=new ArrayList();
        for (int i = 0; i < ll.length; i++) 
        {
            if (ll[i].isFile()) 
            {
                files = ll[i].getName();
                if (files.endsWith(".class"))
                {
                    Informasi desc = new Informasi();
                    
                    StringTokenizer st=new StringTokenizer(files, ".");
                    String nama=st.nextToken();
                    desc.nama_kelas=nama;
                    
                    System.out.println();
                    jTextArea1.append("Nama Kelas = "+nama + "\n");
                    
                    //Deklarasi pencarian kelas
                    Class cls = null;
                    Class<?> cls2 = null;
                    Class<?> cls3 = null;
                    Class<?> cls4 = null;
                    Class<?> cls5 = null;
                    Class<?> cls6 = null;
                    Class<?> cls7 = null;
                    Class<?> cls8 = null;
                    Class<?> cls9 = null;
                    Class<?> cls10 = null;
                    Class<?> clspar = null;
                    
 
                    //kel
                    try
                    {
                        String m1="";
                        cls = Class.forName(nama);
                        Method methlist[]= cls.getDeclaredMethods();
                        method_local = method_local + methlist.length;
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
                        
                        jTextArea1.append("Nama Method = " + m1+"\n");
                        jTextArea1.append("Jumlah Method = " + methlist.length+"\n");

                        Field[] fields = cls.getDeclaredFields();
                        int att_1=0;
                        for (Field field : fields) 
                        {
                            //Cetak Nama Attribute Kelas Lokal
                            att_1++;
                            jTextArea1.append("Nama Attribute = "+field.getName()+"\n");
                        }
                        
                        desc.jum_atribut_lokal=att_1;
                        jTextArea1.append("Jumlah Attribut = "+fields.length+"\n");   
                        jTextArea1.append(batas);
                    }
                    catch (Exception e) 
                    {
                       jTextArea1.append(att_kosong+"\n"+mtd_kosong+batas);
                    } 
                    
                    clspar=cls.getSuperclass();
                    
                    for (int c=1; c<=5;c++)
                        //for (int c=1; c<=ll.length;c++)
                    {
                
                        try{
                            String m2a="";
                            cls2 = clspar;
                            Method methlist2[]= cls2.getDeclaredMethods();
                            method_parent = method_parent + methlist2.length;
                            for (int j=0;j<methlist2.length; j++){
                                Method m2 = methlist2[j];
                                //Memfilter method yng mengandung string "private."
                                if (m2.toString().contains("private")){
                                    continue;
                                }
                                m2a=m2a+(m2.getName()+", ");
                            }   
                            if(cls2.getName().contains("java.lang.Object")){
                            }
                            else
                            jTextArea2.append("Nama Super Kelas dari "+cls.getName() +" adalah "+cls2.getName()+"\n");

                            Field[] fields2 = cls2.getDeclaredFields();
                            int con=0;
                            for (Field field : fields2){
                                if (field.toString().contains("private")){
                                        continue;
                                    }
                                con++;
                                jTextArea2.append("Nama Attribute yang diwariskan= "+field.getName()+"\n");
                            }
                           desc.jum_atribut_parent= desc.jum_atribut_parent + con;
                           //System.out.print(desc.jum_atribut_parent);

                            if(cls2.getName().contains("java.lang.Object")){
                                continue;
                            }
                            jTextArea2.append("Jumlah Method Yang Di wariskan = " + methlist2.length+"\n");
                            jTextArea2.append("Nama Method Yang Di wariskan = " + m2a+"\n");
                            jTextArea2.append("Jumlah Attribut Yang diwariskan = "+con+"\n");
                            jTextArea2.append(batas);
                           // descs.add(desc);
                        }
                        catch (Exception e){
                            jTextArea2.append(att_kosong+"\n"+mtd_kosong+batas);
                        }
                       clspar=cls2.getSuperclass();
                    }
                    desc.jum_atribut_parent= desc.jum_atribut_parent + desc.jum_atribut_lokal;
                    descs.add(desc);
                    int u =0;
                    
                }// tutup IF menghapus .class

            }// tutup list nama class
        isi_tabel();    
	     }//tutup get jumlah class
        
        
        
        
            String total_method_local=String.valueOf("Total Method Local = "+method_local);
            String total_method=String.valueOf("Total Method Parent= "+method_parent);
            jTextArea4.append(total_method_local+"\n");
            jTextArea4.append(total_method+"\n");
	  }//tutup file chooser
    
}//Tutup Kurung Method showopendialog

protected void proses(){
    File[] ll=selectedFile.listFiles();
    /*System.out.println("Selected file: " + selectedFile.getAbsolutePath());
    jTextField1.setText(selectedFile.getAbsolutePath());*/

        //Copy File
        int method_parent = 0;
        int method_local=0;
        int atr_parent = 0;
        int atr_local=0;
                
        for (int i = 0; i < ll.length; i++) 
        {
            if (ll[i].isFile()) 
            {
                files = ll[i].getName();
                File source = new File(ll[i].getPath());
                File destination = new File(target + files);
                try
                {
                    FileUtils.copyFile(source, destination);
                }
                catch(Exception aa){
                    System.out.println("salah");
                }
                        
             }
          }
        
        
        //ArrayList<Informasi> descs=new ArrayList();
        for (int i = 0; i < ll.length; i++) 
        {
            if (ll[i].isFile()) 
            {
                ukuran=ll[i].length();
                jTextArea4.append("Nama File : "+ll[i].getName()+"\t \t"+ukuran+"\n");
                files = ll[i].getName();
                if (files.endsWith(".class"))
                {
                    Informasi desc = new Informasi();
                    
                    StringTokenizer st=new StringTokenizer(files, ".");
                    String nama=st.nextToken();
                    desc.nama_kelas=nama;
                    
                    System.out.println();
                    jTextArea1.append("Nama Kelas = "+nama + "\n");
                    
                    //Deklarasi pencarian kelas
                    Class cls = null;
                    Class<?> cls2 = null;
                    Class<?> cls3 = null;
                    Class<?> cls4 = null;
                    Class<?> cls5 = null;
                    Class<?> cls6 = null;
                    Class<?> cls7 = null;
                    Class<?> cls8 = null;
                    Class<?> cls9 = null;
                    Class<?> cls10 = null;
                    Class<?> clspar = null;
                    
                    //kelas lokal
                    try{
                        String m1="";
                        cls = Class.forName(nama);
                        Method methlist[]= cls.getDeclaredMethods();
                        method_local = method_local + methlist.length;
                        for (int n = 0; n < methlist.length;n++){  
                            Method m = methlist[n];
                            //Memfilter method yng mengandung string "java."
                            if (m.toString().contains("java.")){
                               continue;
                            }
                            m1=m1+m.getName()+", ";
                        }
                        
                        jTextArea1.append("Nama Method = " + m1+"\n");
                        jTextArea1.append("Jumlah Method = " + methlist.length+"\n");

                        Field[] fields = cls.getDeclaredFields();
                        int att_1=0;
                        for (Field field : fields){
                            //Cetak Nama Attribute Kelas Lokal
                            att_1++;
                            jTextArea1.append("Nama Attribute = "+field.getName()+"\n");
                        }
                        
                        desc.jum_atribut_lokal=att_1;
                        jTextArea1.append("Jumlah Attribut = "+fields.length+"\n");   
                        jTextArea1.append(batas);
                    }
                    catch (Exception e){
                       jTextArea1.append(att_kosong+"\n"+mtd_kosong+batas);
                    } 
                    
                    clspar=cls.getSuperclass();
                    for (int c=1; c<=5;c++){
                        try{
                            String m2a="";
                            cls2 = clspar;
                            Method methlist2[]= cls2.getDeclaredMethods();
                            method_parent = method_parent + methlist2.length;
                            for (int j=0;j<methlist2.length; j++){
                                Method m2 = methlist2[j];
                                //Memfilter method yng mengandung string "private."
                                if (m2.toString().contains("private")){
                                    continue;
                                }
                                m2a=m2a+(m2.getName()+", ");
                            }   
                            if(cls2.getName().contains("java.lang.Object")){
                            }
                            else
                            jTextArea2.append("Nama Super Kelas dari "+cls.getName() +" adalah "+cls2.getName()+"\n");

                            Field[] fields2 = cls2.getDeclaredFields();
                            int con=0;
                            for (Field field : fields2){
                                if (field.toString().contains("private")){
                                        continue;
                                    }
                                con++;
                                jTextArea2.append("Nama Attribute yang diwariskan= "+field.getName()+"\n");
                            }
                           desc.jum_atribut_parent= desc.jum_atribut_parent + con;
                           
                           if(cls2.getName().contains("java.lang.Object")){
                                continue;
                            }
                            jTextArea2.append("Jumlah Method Yang Di wariskan = " + methlist2.length+"\n");
                            jTextArea2.append("Nama Method Yang Di wariskan = " + m2a+"\n");
                            jTextArea2.append("Jumlah Attribut Yang diwariskan = "+con+"\n");
                            jTextArea2.append(batas);
                        }
                        catch (Exception e){
                            jTextArea2.append(att_kosong+"\n"+mtd_kosong+batas);
                        }
                       clspar=cls2.getSuperclass();
                    }
                    desc.jum_atribut_parent= desc.jum_atribut_parent + desc.jum_atribut_lokal;
                    desc.AIF= desc.jum_atribut_parent - desc.jum_atribut_lokal;
                    desc.AIF_ext=desc.AIF/desc.jum_atribut_parent;
                    descs.add(desc);
                    
                    
                }// tutup IF menghapus .class

            }// tutup list nama classdatapeg[i][2] = String.valueOf(temp.jum_atribut_parent);
        isi_tabel();    
	     }//tutup get jumlah class
}

public void isi_tabel()
{
   // DefaultTableModel tabel = null;
    String[] judul={"Nama Kelas","Atribut Lokal","Atribut Parent","AIF","AIF EXT"};
    //tabel = new DefaultTableModel(judul);
    
    String[][] datapeg = new String[descs.size()][5];
    for(int i=0; i<descs.size(); i++){
        Informasi temp = descs.get(i);
        datapeg[i][0] = temp.nama_kelas;
        datapeg[i][1] = String.valueOf(temp.jum_atribut_lokal);
        datapeg[i][2] = String.valueOf(temp.AIF);
        datapeg[i][3] = String.valueOf(temp.jum_atribut_parent);
        datapeg[i][4] = String.valueOf(temp.AIF_ext);
        
    }
    tabel = new DefaultTableModel(datapeg,judul);
    jTable1.setModel(tabel);
    
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
        jLabel4 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1329, 900));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setText("Open");
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
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jButton1)
                .addGap(31, 31, 31)
                .addComponent(jButton2)
                .addGap(10, 10, 10))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addGap(92, 92, 92)
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
                .addComponent(jScrollPane2)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(jLabel3)
                .addContainerGap(117, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setText("Total AIF dan MIF pada Sistem ");

        jTable1.setEnabled(false);
        jScrollPane6.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jLabel4)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTextArea4.setColumns(20);
        jTextArea4.setRows(5);
        jScrollPane4.setViewportView(jTextArea4);

        jMenu1.setText("File");

        jMenuItem1.setText("Open");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Exit");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu4.setText("Detail Direktori");
        jMenu4.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
                jMenu4MenuCanceled(evt);
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                jMenu4MenuSelected(evt);
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
        });
        jMenu4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu4ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu4);

        jMenu2.setText("Detail Atribut Inheritance Factor");
        jMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Detail Method Inheritance Factor");
        jMenu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu3ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(398, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    /*private void kopi()
    {
        FileUtils.copyFileToDirectory(files, target);
        
    }*/
    
    ArrayList<Informasi> descs =new ArrayList<>(15);
    
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
    fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    int result = fileChooser.showOpenDialog(this);
    if (result == JFileChooser.APPROVE_OPTION) 
    {
        selectedFile = fileChooser.getSelectedFile();
        //File[] ll=selectedFile.listFiles();
        System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        jTextField1.setText(selectedFile.getAbsolutePath());
    }
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        proses();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        jButton1ActionPerformed(evt);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(new JFrame(),
                "Apakah anda yakin ingin keluar ?", "Konfirmasi !!",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            System.exit(0);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Maaf form masih dalam perbaikan");
    }//GEN-LAST:event_jMenu2ActionPerformed

    private void jMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu3ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Maaf form masih dalam perbaikan");
    }//GEN-LAST:event_jMenu3ActionPerformed

    private void jMenu4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu4ActionPerformed
        // TODO add your handling code here:
        
        //jTextArea4.append("Nama File : "+ll[i].getName()+"\t \t"+ukuran+"\n");
    }//GEN-LAST:event_jMenu4ActionPerformed

    private void jMenu4MenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_jMenu4MenuSelected
        // TODO add your handling code here:
        //JOptionPane.showMessageDialog(null, "Nama File : "+files+"\t \t"+ukuran+"\n");
    }//GEN-LAST:event_jMenu4MenuSelected

    private void jMenu4MenuCanceled(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_jMenu4MenuCanceled
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu4MenuCanceled

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
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
