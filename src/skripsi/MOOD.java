/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skripsi;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author king
 */

public class MOOD extends javax.swing.JFrame 
{
    DefaultTableModel tabel_AIF,tabel_MIF, detail_tab_AIF,detail_tabel_MIF;
    private DecimalFormat df = new DecimalFormat("###.##");
    private String att_kosong="Tidak mempunyai Attribute";
    public String[] judul_tabel_AIF={"Classes","Ad(Ci)","Ai(Ci)","AIF Ext for each class","Status"};
    public String[] judul_tabel_MIF={"Classes","Md(Ci)","Mi(Ci)","MIF Ext for each class","Status"};
    private String mtd_kosong="Tidak mempunyai Method";
    public String batas="\n===================================================================================== \n";
    private int total_AIF_diterima;
    private int total_AIF_ditolak;
    private int total_MIF_diterima;
    private int total_MIF_ditolak;
    private String files; 
    File[] ll;
    
    //target file
    String target = "E:/File Skripsi/program/check1/build/classes/";
    JFileChooser fileChooser = new JFileChooser();
    public String laporan=null;
    FileOutputStream tulis;
    private double pembilang_AIF=0.0;
    private double penyebut_AIF=0.0;
    private double hasil_AIF=0.0;
    
    private double pembilang_MIF=0.0;
    private double penyebut_MIF=0.0;
    private double hasil_MIF=0.0;
    
    Method m;
    File selectedFile;
    File destination;
    File[] esteh;
    public MOOD(){
        initComponents();
        }


private void showOpenFileDialog(){
    fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    int result = fileChooser.showOpenDialog(this);
    if (result == JFileChooser.APPROVE_OPTION){
        selectedFile = fileChooser.getSelectedFile();
        File[] ll=selectedFile.listFiles();
        System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        jTextField1.setText(selectedFile.getAbsolutePath());
    }//tutup file chooser
    
}//Tutup Kurung Method showopendialog

    @SuppressWarnings("empty-statement")
    private void proses(){
    ll=selectedFile.listFiles();
    esteh=selectedFile.listFiles();;
        int method_parent = 0;int method_local=0;
        int atr_parent = 0;int atr_local=0;
        double ukuran;
        
        //Copy File        
        for (int i = 0; i < ll.length; i++) 
        {
            if (ll[i].isFile()) 
            {
                files = ll[i].getName();
                File source = new File(ll[i].getPath());
                destination = new File(target + files);
                try
                {
                    FileUtils.copyFile(source, destination);
                }
                catch(Exception aa){
                    System.out.println("salah");
                }
                        
             }
          }
        int nama_file=0;
        for (int i = 0; i < ll.length; i++) 
        {
            if (ll[i].isFile()) 
            {
                ukuran=ll[i].length();
                jTextArea4.append("Nama File : "+ll[i].getName()+"\t \t"+ukuran+" bytes\n");
                files = ll[i].getName();
                nama_file++;
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
                    Class<?> clspar = null;
                    
                    //kelas lokal
                    try{
                        String m1="";
                        cls = Class.forName(nama);
                        Method methlist[]= cls.getDeclaredMethods();
                        method_local = method_local + methlist.length;
                        int method=0;
                        for (int n = 0; n < methlist.length;n++){
                            
                           m = methlist[n];
                            //Memfilter method yng mengandung string "java" dan "abstract"
                            if (m.toString().contains("java.")||m.toString().contains("abstract")){
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
                    
                    for (int c=1; c<=10;c++){
                        try{
   
                            String m2a="";
                            
                            cls2 = clspar;
                            //Menghitung Method milik super class
                            if(cls2.getName().contains("java.lang.Object")){
                                continue;
                            }
                            else {
                                jTextArea2.append("Nama Super Kelas dari "+cls.getName() +" adalah "+cls2.getName()+"\n");
                            }

                            Method methlist2[]= cls2.getDeclaredMethods();
                            int method_par=0;
                            
                            //method_parent = method_parent + methlist2.length;
                            int jumlah_method2=0;
                            double a=0.0,b=0.0;
                            for (int j=0;j<methlist2.length; j++){
                                
                                Method m2 = methlist2[j];
                                //Memfilter method yng mengandung string "private."
                                if (in_array(cls2.getDeclaredMethods(), m.getName())) {
                                    continue;
                                }
                                //if(m2.getName().equals(m.getName())){
                                    //continue;
                                //}
                                if (m2.toString().contains("private")||m2.toString().contains("abstract")){
                                    continue;
                                    //b++;
                                }
                                jumlah_method2++;
                                method_par++;
                                
                                m2a=m2a+(m2.getName()+", ");
                            }
                                desc.jum_method_parent= desc.jum_method_parent+jumlah_method2;
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
                            jTextArea2.append("Jumlah Method Yang Di wariskan = " + jumlah_method2+"\n");
                            jTextArea2.append("Nama Method Yang Di wariskan = " + m2a+"\n");
                            jTextArea2.append("Jumlah Attribut Yang diwariskan = "+con+"\n");
                            jTextArea2.append(batas);
                        }
                        catch (Exception e){
                            jTextArea2.append(att_kosong+"\n"+mtd_kosong+batas);
                        }
                       clspar=cls2.getSuperclass();
                    }
                    
                    desc.jum_method_parent=desc.jum_method_parent + desc.jum_method_lokal;
                    
                    //method khusus dari super kelas
                    desc.MIF=desc.jum_method_parent - desc.jum_method_lokal;
                    
                    desc.MIF_ext=desc.MIF/desc.jum_method_parent;
                    
                    
                    //MIF
                    if(desc.jum_method_lokal==0&&desc.jum_method_parent==0){
                        desc.MIF_ext=0;
                    }
                    if(desc.MIF_ext<=0.5){
                        desc.status_MIF="Diterima";
                        total_MIF_diterima++;
                    }
                    
                    
                    else {
                        desc.status_MIF="Ditolak";
                        total_MIF_ditolak++;
                    }
                        
                        descs.add(desc);
                    
                    pembilang_MIF+=desc.MIF;
                    penyebut_MIF+=desc.jum_method_parent;
                    
                    desc.jum_atribut_parent= desc.jum_atribut_parent + desc.jum_atribut_lokal;
                    desc.AIF= desc.jum_atribut_parent - desc.jum_atribut_lokal;
                    
                    desc.AIF_ext=desc.AIF/desc.jum_atribut_parent;
                    
                    //AIF
                    if(desc.AIF_ext<=0.5){
                        desc.status_AIF="Diterima";
                        total_AIF_diterima++;
                    }
                    else {
                        desc.status_AIF="Ditolak";
                    }
                        total_AIF_ditolak++;
                        
                    pembilang_AIF+=desc.AIF;
                    penyebut_AIF+=desc.jum_atribut_parent;
                    
                    
                    
                }// tutup IF menghapus .class
                        //JOptionPane.showMessageDialog(null, "Hasil "+ c);
            
            }
            else {
                JOptionPane.showMessageDialog(null, "File Java tidak ditemukan");
            }
            //jTextArea4.append(String.valueOf("TOTAL= "+xx));    
            //fix_AIF=att_kos
            jTextField2.setText(String.valueOf(total_AIF_diterima));
            jTextField3.setText(String.valueOf(total_AIF_ditolak-total_AIF_diterima));
            jTextField4.setText(String.valueOf(total_MIF_diterima));
            jTextField5.setText(String.valueOf(total_AIF_ditolak-total_MIF_diterima));
           // jTextField6.setText(String.valueOf(c));
                // tutup list nama classdatapeg[i][2] = String.valueOf(temp.jum_atribut_parent);
        isi_tabel();    
        
        
	     }
        jTextArea4.append(String.valueOf("Jumlah File dalam Direktori"+selectedFile.getAbsolutePath()+": "+nama_file));
        //tutup get jumlah class
        Date dt= new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        String tgl=sdf.format(dt);
        //JOptionPane.showMessageDialog(null,"Hasilnya "+ a);
        laporan="Tanggal Anlisa"+tgl+"\n"+"Path Project\t\t : "+jTextField1.getText()+"\n"+"Detail Path\t\t : "+jTextArea4.getText()+"\n"+jTextArea1.getText()+"\n"+jTextArea2.getText();
}// tutup kurung method proses

    private boolean in_array(Method[] haystack, String needle) {
        for(int i=0;i<haystack.length;i++) {
            if(haystack[i].getName().toString().equals(needle)) {
                return true;
            }
        }
        return false;
    }

public String[][] data_MIF=null;
public String[][] data_AIF=null;

private void isi_tabel(){
    data_AIF = new String[descs.size()][5];
    ArrayList<String> colValues = new ArrayList<>();
    for(int i=0; i<descs.size(); i++){
        Informasi temp = descs.get(i);
        data_AIF[i][0] = temp.nama_kelas;
        data_AIF[i][1] = String.valueOf(temp.jum_atribut_lokal);
        
        data_AIF[i][2] = String.valueOf(temp.AIF);
        data_AIF[i][3] = String.valueOf(df.format(temp.AIF_ext));
        data_AIF[i][4] =temp.status_AIF;
        
    }
    data_MIF = new String[descs.size()][5];
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
 
}// Tutup Kurung method isi tabel

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
        jButton5 = new javax.swing.JButton();
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
        jLabel9 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel6.setBackground(new java.awt.Color(51, 204, 0));

        jPanel1.setBackground(new java.awt.Color(153, 255, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jButton1.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/open-file-icon.png"))); // NOI18N
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
        jButton2.setEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jButton3.setText("Reset");
        jButton3.setPreferredSize(new java.awt.Dimension(73, 27));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jButton5.setText("Save");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jTextArea4.setColumns(20);
        jTextArea4.setRows(5);
        jScrollPane4.setViewportView(jTextArea4);

        jLabel5.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel5.setText("Detail Path");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(73, 73, 73)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane4)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 14, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(23, 23, 23)))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20))))
        );

        jPanel2.setBackground(new java.awt.Color(153, 255, 153));
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

        jPanel3.setBackground(new java.awt.Color(153, 255, 153));
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

        jPanel4.setBackground(new java.awt.Color(153, 255, 153));
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
        jTable1.getColumnModel().getColumn(3).setMinWidth(30);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(3).setMaxWidth(60);

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
        jTable2.getColumnModel().getColumn(3).setMinWidth(30);
        jTable2.getColumnModel().getColumn(3).setPreferredWidth(50);
        jTable2.getColumnModel().getColumn(3).setMaxWidth(60);

        jLabel6.setFont(new java.awt.Font("Cambria Math", 0, 20)); // NOI18N
        jLabel6.setText("Total Method Inheritance Factor Extended");

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(255, 255, 255));
        jTextField2.setPreferredSize(new java.awt.Dimension(6, 39));

        jLabel7.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel7.setText("Total AIF");

        jLabel8.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel8.setText("Total AIF");

        jTextField3.setEditable(false);
        jTextField3.setBackground(new java.awt.Color(255, 255, 255));
        jTextField3.setPreferredSize(new java.awt.Dimension(6, 39));

        jTextField4.setEditable(false);
        jTextField4.setBackground(new java.awt.Color(255, 255, 255));
        jTextField4.setPreferredSize(new java.awt.Dimension(6, 39));

        jTextField5.setEditable(false);
        jTextField5.setBackground(new java.awt.Color(255, 255, 255));
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

        jLabel9.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel9.setText("Total AIF Extended");

        jTextField6.setEditable(false);
        jTextField6.setBackground(new java.awt.Color(255, 255, 255));
        jTextField6.setPreferredSize(new java.awt.Dimension(6, 39));

        jLabel10.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        jLabel10.setText("Total MIF Extended");

        jTextField7.setEditable(false);
        jTextField7.setBackground(new java.awt.Color(255, 255, 255));
        jTextField7.setPreferredSize(new java.awt.Dimension(6, 39));

        jTextField8.setEditable(false);
        jTextField8.setBackground(new java.awt.Color(255, 255, 255));
        jTextField8.setPreferredSize(new java.awt.Dimension(6, 39));

        jTextField9.setEditable(false);
        jTextField9.setBackground(new java.awt.Color(255, 255, 255));
        jTextField9.setPreferredSize(new java.awt.Dimension(6, 39));

        jTextField10.setEditable(false);
        jTextField10.setBackground(new java.awt.Color(255, 255, 255));
        jTextField10.setPreferredSize(new java.awt.Dimension(6, 39));

        jTextField11.setEditable(false);
        jTextField11.setBackground(new java.awt.Color(255, 255, 255));
        jTextField11.setPreferredSize(new java.awt.Dimension(6, 39));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel4))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel6)))
                .addContainerGap(29, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27))))))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel12))
                    .addComponent(jLabel8)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel14)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16)))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE))))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jMenu1.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon("E:\\File Skripsi\\program\\check1\\src\\image\\open-file-icon.png")); // NOI18N
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

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Save-icon.png"))); // NOI18N
        jMenuItem3.setText("Save");
        jMenu1.add(jMenuItem3);
        jMenu1.add(jSeparator1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem2.setText("Exit");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu4.setText("MOOD Metrics Suite");
        jMenu4.setEnabled(false);
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

        jMenuItem4.setText("View Report");
        jMenu4.add(jMenuItem4);

        jMenuItem5.setText("Detail Atribut Inheritance Factor");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem5);

        jMenuItem6.setText("Detail Method Inheritance Factor");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem6);

        jMenuBar1.add(jMenu4);

        jMenu3.setText("Detail Method Inheritance Factor");
        jMenu3.setEnabled(false);
        jMenu3.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                jMenu3MenuSelected(evt);
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
        });
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
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    public ArrayList<Informasi> descs =new ArrayList<>(15);

    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
    fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION){
            selectedFile = fileChooser.getSelectedFile();
            //File[] ll=selectedFile.listFiles();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            jTextField1.setText(selectedFile.getAbsolutePath());
            
        }
        jButton2.setEnabled(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        proses();
       // jMenu2.setEnabled(true);
        jMenu3.setEnabled(true);
        jMenu4.setEnabled(true);
        hasil_AIF=pembilang_AIF/penyebut_AIF;
        //hasil_MIF=pembilang_MIF/penyebut_MIF;           
        double xx=penyebut_MIF+1.0;
        hasil_MIF=pembilang_MIF/xx;
        jTextField10.setText(""+xx);
        
        jTextField6.setText(""+pembilang_AIF);
        jTextField7.setText(""+penyebut_AIF);
        jTextField8.setText(String.valueOf(df.format(hasil_AIF)));
        jTextField9.setText(""+pembilang_MIF);
        //jTextField10.setText(""+penyebut_MIF);
        jTextField11.setText(String.valueOf(df.format(hasil_MIF)));
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        jButton1ActionPerformed(evt);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(new JFrame(),
                "Apakah anda yakin ingin keluar ?", "Konfirmasi !!",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu3ActionPerformed
        // TODO add your handling code here:
        Detail_MIF obj_Detail_MIF=new Detail_MIF(data_MIF,total_MIF_diterima,total_MIF_ditolak-total_MIF_diterima,hasil_MIF);
        obj_Detail_MIF.setVisible(true);
    }//GEN-LAST:event_jMenu3ActionPerformed

    private void jMenu4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu4ActionPerformed
        // TODO add your handling code here:       
    }//GEN-LAST:event_jMenu4ActionPerformed

    private void jMenu4MenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_jMenu4MenuSelected
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu4MenuSelected

    private void jMenu4MenuCanceled(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_jMenu4MenuCanceled
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu4MenuCanceled

    private void jMenuItem1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jMenuItem1KeyPressed
        // TODO add your handling code here:
        //jMenuItem1.setAccelerator(KeyEvent.VK_W, ActionEvent.ALT_MASK + ActionEvent.CTRL_MASK ));
        
    }//GEN-LAST:event_jMenuItem1KeyPressed

    
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        ExtensionFileFilter filter1 = new ExtensionFileFilter("Text Documents", new String[] { ".docx", ".txt" });
        fileChooser.setFileFilter(filter1);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        
        int result = fileChooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION){
                
                File file = fileChooser.getSelectedFile();
            // save the file.
                
                BufferedWriter bw;
            try{
                bw = new BufferedWriter(new FileWriter(file));
                bw.write(laporan);
                bw.flush();
            }               
            catch (IOException e1){
                e1.printStackTrace();
            }
            }else{
                JOptionPane.showMessageDialog(null, "Report Belum Disimpan","Info", JOptionPane.INFORMATION_MESSAGE);
            }
                
            
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        jButton2.setEnabled(false);
        jTable1.setModel(new DefaultTableModel());
        jTable2.setModel(new DefaultTableModel());
        jTextArea1.setText(null);jTextArea2.setText(null);
        
        jTextArea4.setText(null);jTextField1.setText(null);
        jTextField2.setText(null);jTextField3.setText(null);
        jTextField4.setText(null);jTextField5.setText(null);    
        jTextField6.setText(null);jTextField7.setText(null);
        jTextField8.setText(null);jTextField9.setText(null);
        jTextField10.setText(null); jTextField11.setText(null); 
        
        for(int i=0;i<ll.length;i++)
        {
            JOptionPane.showMessageDialog(rootPane, target+ll[i].getName());
           File b=new File(target+ll[i].getName());
            b.delete();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jMenu3MenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_jMenu3MenuSelected
        // TODO add your handling code here:
        Detail_MIF obj_Detail_MIF=new Detail_MIF(data_MIF,total_MIF_diterima,total_MIF_ditolak-total_MIF_diterima,hasil_MIF);
        obj_Detail_MIF.setVisible(true);
    }//GEN-LAST:event_jMenu3MenuSelected

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        Detail_AIF cb = new Detail_AIF(data_AIF,total_AIF_diterima,total_AIF_ditolak-total_AIF_diterima,hasil_AIF);
        cb.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        Detail_MIF obj_Detail_MIF=new Detail_MIF(data_MIF,total_MIF_diterima,total_MIF_ditolak-total_MIF_diterima,hasil_MIF);
        obj_Detail_MIF.setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MOOD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MOOD().setVisible(true);
                
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}

/*public void loading(){
getGlassPane().setVisible(true);
        
                new Thread(new Runnable() 
            {

                public void run() 
                {
                    try 
                    {
                        Thread.sleep(5000);
                    } 
                    catch (InterruptedException ex) 
                    {
                        Logger.getLogger(class.getName()).log(Level.SEVERE, null, ex);
                    } 
                    finally 
                    {
                        getGlassPane().setVisible(false);
                        
                            JOptionPane.showMessageDialog(null, "Login Diterima\nSelamat Datang");
                            
                            setVisible(false);
                            new home().setVisible(true);
                        }
                        
                        
                    }
                }
            }
                    ).start();
}*/