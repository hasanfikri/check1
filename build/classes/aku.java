import java.io.*;
import java.util.Date;
class anggur
{
	public int aku = 5;
	protected int kamu = 6;
	
	public void info()
	{
		System.out.println("pada class " +this.getClass().getName());
		System.out.println("satu adalah " + aku);
		System.out.println("dua adalah " + kamu);
		double akar= Math.sqrt(aku+kamu);
		System.out.println("nilai akar = " + akar);
	}
}
class apel extends anggur
{
	public int dia = 7;
		public void info()
		{
			
			System.out.println("pada class " + this.getClass().getName());
			System.out.println("satu adalah " + aku);
			System.out.println("dua adalah " + kamu);
			System.out.println("tiga adalah " + dia);
			double kuadrat=(aku+kamu+dia)*(aku+kamu+dia);
			System.out.println("nilai kuadrat total = " + kuadrat);
		}
}
class penentu
{
	public static void main(String[]args)
	{
		Date sekarang=new Date();
		System.out.println("Tanggal sekarang : "+sekarang.getDate()+"/"+(sekarang.getMonth()+1)+"/"+(sekarang.getYear()+1900));
		anggur objX=new anggur();
		objX.info();
		apel objY=new apel();
		objY.info();
	}
}