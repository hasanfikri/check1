import java.io.*;
import javax.swing.*;
class bangun
{
	public String input;
	int a,g,k;
	public bangun(String input)
	{
		this.input=input;
	}
	public void cetak()
	{
		char c;
		int a=input.length()/2;
		int g=a;
		int k=a+1;
		int i,j;
		for(i=0;i<k;i++)
		{
			for(j=0;j<input.length();j++)
			{
				c=input.charAt(j);
				if(j<=g&&j>=a)
				{
					System.out.print(c+" ");
				}else
				{
					System.out.print("  ");
				}
			}
			System.out.println();
			a--;g++;
		}
	}
}
class ketupat extends bangun
{
	String kk;
	int a,g,i,j,k;
	public ketupat(String a)
	{
		super(a);
		this.input = super.input;

	}
	public void cetak()
	{
		char c;
		a=input.length()/2;
		k=a+1;
		g=a;
		for(i=0;i<k;i++)
		{
			a--;g++;
		}
		k=k-1;
		a+=2;g-=2;
		for(i=0;i<k;i++)
		{
			for(j=0;j<input.length();j++)
			{
				c=input.charAt(j);
				if(j<=g&&j>=a)
				{
					System.out.print(c+" ");
				}else
				{
					System.out.print("  ");
				}
			}
			System.out.println();
			a++;g--;
		}
	}
}
	class bangun_ketupat
	{
		public static void main(String[] args)throws Exception
    	{
        DataInputStream dis=new DataInputStream(System.in);
        System.out.println("-- Program Teks Belah Ketupat --");
       	System.out.println("========================================\n");
        System.out.print("Masukan Kata : ");
        String input=dis.readLine();
        bangun x=new bangun(input);
        ketupat b = new ketupat(input);
        x.cetak();
        b.cetak();
       
	}
		
		
	}
