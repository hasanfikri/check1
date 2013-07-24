import java.io.*;

public class perusahaanku{
	public static void main (String[] args)throws Exception {
    	DataInputStream y=new DataInputStream(System.in);
        System.out.print("Masukan Nilai a: ");
        double a=Double.parseDouble(y.readLine());
        System.out.print("Masukan Nilai b: ");
        double b=Double.parseDouble(y.readLine());
        System.out.print("Masukan Nilai c: ");
        double c=Double.parseDouble(y.readLine());
        System.out.print("Masukan Nilai d: ");
        double d=Double.parseDouble(y.readLine());
        System.out.print("Masukan Nilai e: ");
        double e=Double.parseDouble(y.readLine());
        System.out.print("Masukan Nilai f: ");
        double f=Double.parseDouble(y.readLine());
        bank z=new bank(a,b,c);
        System.out.println("tambah1 adalah : "+z.luas());
        pabrik w=new pabrik(a,b,d);
        System.out.println("tambah2 adalah : "+w.luas());
        supermarket x=new supermarket(a,b,e);
        System.out.println("tambah3 adalah : "+x.luas());
        cabang aa=new cabang(a,b,c,f);
        System.out.println("tambah4 adalah : "+aa.luas());
}
}