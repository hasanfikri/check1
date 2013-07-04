import java.io.*;

class bilangan {
    static int pencacah=0;
    private int nilai;
    int a,b,c,d,e;
    public bilangan(int nilai){
        this.nilai=nilai;
        this.pencacah++;
    }
    public void getbilangan()
    {
    	System.out.println("-----------------------------");
    	System.out.println("Nilai ke - "+this.pencacah+ " = " +this.nilai);
    }
    public double satu()
    {
        return (a+b+c)*(d-e);
    }
}

class cacah extends bilangan{
    int nilai;
   
    public cacah(int nilai)
    {
        super(nilai);
    }
    public void getpencacah()
    {
    	System.out.println("Pencacah : "+this.pencacah);
    	System.out.println("");
    } 
    public void tambahan()
    {
    	super.getbilangan();
    }
    public double satu()
    {
       return (super.satu());       
    }
 }
class luas_lingkaran {

    public static void main(String[] args)throws Exception
    	{
        DataInputStream dis=new DataInputStream(System.in);
        System.out.println("-- Program Mengurutkan Nilai --");
       	System.out.println("========================================\n");
        System.out.print("Masukan Bilangan ke-satu : ");
        int a=Integer.parseInt(dis.readLine());
        System.out.print("Masukan Bilangan ke-dua : ");
        int b=Integer.parseInt(dis.readLine());
        System.out.print("Masukan Bilangan ke-tiga : ");
        int c=Integer.parseInt(dis.readLine());
        System.out.print("Masukan Bilangan ke-empat : ");
        int d=Integer.parseInt(dis.readLine());
        System.out.print("Masukan Bilangan ke-lima : ");
        int e=Integer.parseInt(dis.readLine());
        cacah f=new cacah(a);
        f.tambahan();
        cacah g=new cacah(b);
        g.tambahan();
        cacah h=new cacah(c);
        h.tambahan();
        cacah i=new cacah(d);
        i.tambahan();
        cacah j=new cacah(e);
        j.tambahan();
        double total=(a+b+c)*(d-e);
        double average=total/5;
        double akar=Math.sqrt(total);
        System.out.println("\nJadi,total nilai keseluruhan : "+total);
        System.out.println("Jadi,nilai rata-rata : "+average);
        System.out.println("Jadi,nilai akar dari total : "+akar);
        
     
    }

}