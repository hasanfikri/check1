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
import java.text.DecimalFormat;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author king
 */
public class NewJFrame extends javax.swing.JFrame 
{
    DefaultTableModel tabel_AIF,tabel_MIF;
    int xx=0;
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
        
        for (int i = 0; i < ll.length; i++) 
        {
            if (ll[i].isFile()) 
            {
                ukuran=ll[i].length();
                jTextArea4.append("Nama File : "+ll[i].getName()+"\t \t"+ukuran+" bytes\n");
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
                        int method=0;
                        for (int n = 0; n < methlist.length;n++){
                            
                            Method m = methlist[n];
                            //Memfilter method yng mengandung string "java."
                            if (m.toString().contains("java.")){
                               continue;
                            }
                            method++;
                            m1=m1+m.getName()+", ";
                        }
                        desc.jum_method_lokal=method;
                        
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
                    
                    //Fungsi Rekursif
                    clspar=cls.getSuperclass();
                    for (int c=1; c<=5;c++){
                        try{
                            String m2a="";
                            cls2 = clspar;
                            //Menghitung Method milik super class
                        if(cls2.getName().contains("java.lang.Object"))
                            {
                                continue;
                            }
                            else
                            jTextArea2.append("Nama Super Kelas dari "+cls.getName() +" adalah "+cls2.getName()+"\n");

                            Method methlist2[]= cls2.getDeclaredMethods();
                            int method_par=0;
                            //method_parent = method_parent + methlist2.length;
                            for (int j=0;j<methlist2.length; j++){
                                
                                Method m2 = methlist2[j];
                                //Memfilter method yng mengandung string "private."
                                if (m2.toString().contains("private")){
                                    continue;
                                }
                                method_par++;
                                m2a=m2a+(m2.getName()+", ");
                            }
                                desc.jum_method_parent= desc.jum_method_parent+method_par;
                            //Menghitung Attribut milik super class
                            
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
                    
                    if(desc.AIF_ext<=0.5){
                        desc.status_AIF="Diterima";
                        xx++;
                        
                    }
                    
                    else
                        desc.status_AIF="Ditolak";
                    
                    
                    if(desc.status_AIF.equals("Diterima")){
                       //desc.
                    }
                            
                    desc.jum_method_parent=desc.jum_method_parent + desc.jum_method_lokal;
                    desc.MIF=desc.jum_method_parent - desc.jum_method_lokal;
                    desc.MIF_ext=desc.MIF/desc.jum_method_parent;
                    if(desc.MIF_ext<=0.5){
                        desc.status_MIF="Diterima";
                    }
                    else
                        desc.status_MIF="Ditolak";
                    descs.add(desc);
                    
                    
                }// tutup IF menghapus .class
jTextArea4.append(String.valueOf("TOTAL= "+xx));
            }
            else
                JOptionPane.showMessageDialog(null, "File Java tidak ditemukan");
                
                // tutup list nama classdatapeg[i][2] = String.valueOf(temp.jum_atribut_parent);
        isi_tabel();    
	     }//tutup get jumlah class
}

public void isi_tabel()
{
   // DefaultTableModel tabel = null;
    String[] judul_tabel_AIF={"Nama Kelas","Atribut Lokal","Atribut Parent","Hasil","Status"};
    String[] judul_tabel_MIF={"Nama Kelas","Method Lokal","Method Parent","Hasil","Status"};
    //tabel = new DefaultTableModel(judul);
    DecimalFormat df = new DecimalFormat("###.##");
    
    String[][] data_AIF = new String[descs.size()][5];
    for(int i=0; i<descs.size(); i++){
        Informasi temp = descs.get(i);
        data_AIF[i][0] = temp.nama_kelas;
        data_AIF[i][1] = String.valueOf(temp.jum_atribut_lokal);
        data_AIF[i][2] = String.valueOf(temp.AIF);
        data_AIF[i][3] = String.valueOf(df.format(temp.AIF_ext));
        data_AIF[i][4] =temp.status_MIF;
    }
    String[][] data_MIF = new String[descs.size()][5];
    for(int i=0; i<descs.size(); i++){
        Informasi temp = descs.get(i);
        data_MIF[i][0] = temp.nama_kelas;
        data_MIF[i][1] = String.valueOf(temp.jum_method_lokal);
        data_MIF[i][2] = String.valueOf(temp.MIF);
        data_MIF[i][3] = String.valueOf(df.format(temp.MIF_ext));
        data_MIF[i][4] =temp.status_MIF;
    }
    tabel_AIF = new DefaultTableModel(data_AIF,judul_tabel_AIF);
    jTable1.setModel(tabel_AIF);
    
    tabel_MIF = new DefaultTableModel(data_MIF,judul_tabel_MIF);
    jTable2.setModel(tabel_MIF);
    
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1375, 760));

        jPanel6.setBackground(new java.awt.Color(0, 153, 51));

        jButton1.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jButton1.setText("Open");
        jButton1.setPreferredSize(new java.awt.Dimension(73, 27));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField1.setToolTipText("Masukkan Project Java");
        jTextField1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        jLabel1.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel1.setText("Alamat");

        jButton2.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jButton2.setText("Proses");
        jButton2.setPreferredSize(new java.awt.Dimension(73, 27));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jButton3.setText("Reset");
        jButton3.setPreferredSize(new java.awt.Dimension(73, 27));

        jTextArea4.setColumns(20);
        jTextArea4.setRows(5);
        jScrollPane4.setViewportView(jTextArea4);

        jLabel5.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel5.setText("Detail Path");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(35, 35, 35)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Kelas Lokal", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Cambria Math", 0, 18))); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Kelas Super", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Cambria Math", 0, 18))); // NOI18N
        jPanel3.setPreferredSize(new java.awt.Dimension(411, 505));

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        jPanel4.setPreferredSize(new java.awt.Dimension(421, 643));

        jLabel4.setFont(new java.awt.Font("Cambria Math", 0, 20)); // NOI18N
        jLabel4.setText("Total Atribut Inheritance Factor Extended");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nama Kelas", "Atribut Lokal", "Atribut Parent", "AIF Ext", "Status"
            }
        ));
        jTable1.setEnabled(false);
        jTable1.setPreferredSize(new java.awt.Dimension(452, 402));
        jScrollPane6.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nama Kelas", "Method Lokal", "Method Parent", "MIF Ext", "Status"
            }
        ));
        jTable2.setPreferredSize(new java.awt.Dimension(452, 402));
        jScrollPane3.setViewportView(jTable2);

        jLabel6.setFont(new java.awt.Font("Cambria Math", 0, 20)); // NOI18N
        jLabel6.setText("Total Method Inheritance Factor Extended");

        jTextField2.setPreferredSize(new java.awt.Dimension(6, 39));

        jLabel7.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel7.setText("Total AIF");

        jLabel8.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel8.setText("Total AIF");

        jTextField3.setPreferredSize(new java.awt.Dimension(6, 39));

        jTextField4.setPreferredSize(new java.awt.Dimension(6, 39));

        jTextField5.setPreferredSize(new java.awt.Dimension(6, 39));

        jLabel11.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel11.setText("Diterima");

        jLabel12.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel12.setText("Ditolak");

        jLabel13.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel13.setText("Ditolak");

        jLabel14.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel14.setText("Total MIF");

        jLabel15.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel15.setText("Total MIF");

        jLabel16.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel16.setText("Diterima");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel6))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel4)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel11))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jLabel12))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(30, 30, 30)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel16))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel14)
                            .addGap(0, 0, 0)
                            .addComponent(jLabel13))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu1.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Open");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenuItem1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jMenuItem1KeyPressed(evt);
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
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
                jMenu4MenuCanceled(evt);
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                jMenu4MenuSelected(evt);
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
                .addGap(0, 10, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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

    private void jMenuItem1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jMenuItem1KeyPressed
        // TODO add your handling code here:
        //jMenuItem1.setAccelerator(KeyEvent.VK_W, ActionEvent.ALT_MASK + ActionEvent.CTRL_MASK ));
        
    }//GEN-LAST:event_jMenuItem1KeyPressed

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
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
}
