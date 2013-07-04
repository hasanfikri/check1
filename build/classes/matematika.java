import java.io.*;
import java.util.Calendar;
class operasi1
{
	void nilai (int A[],int n)
	{
		int C=1;
		for (int i=1;i<=n;i++)
		C=C*A[i];
		System.out.println("Nilai Perkalian:"+C);
	}
}
class operasi2 extends operasi1
{
		void nilai (int A[],int n)
	{
		int C=0;
		for (int i=1;i<=n;i++)
		C=C+A[i];
		System.out.println("Nilai Penjumlahan:"+C);
	}
}
class operasi3 extends operasi1
{
void nilai (int A[],int n)
	{
	
		for (int i=1;i<=n;i++)
		{
			for (int j=i;j<=n;j++)
			{
				if(A[i-1]>A[j])
				{
					int x = A[i-1];
					A[i-1] = A[j];
					A[j]=x;
				}
				}			
	}
	for(int i=1;i<=1;i++)
		System.out.println("Nilai Min:"+A[i]);	
}}
class operasi4 extends operasi1
{
void nilai (int A[],int n)
	{
	
		for (int i=1;i<=n;i++)
		{
			for (int j=i;j<=n;j++)
			{
				if(A[i-1]<A[j])
				{
					int x = A[i-1];
					A[i-1] = A[j];
					A[j]=x;
				}
				}			
	}
	for(int i=1;i<=1;i++)
		System.out.println("Nilai Max:"+A[i-1]);
}	
}	

class main
{
public static void main (String [] args)throws Exception
{
	DataInputStream dis = new DataInputStream(System.in);
	int A[]=new int [100];
	System.out.println("--->Tanggal saat ini<---");
	System.out.println(java.util.Calendar.getInstance().getTime());
	System.out.println();
		
	System.out.print("Masukkan banyaknya data:");
		int n=Integer.parseInt(dis.readLine());
		for (int i=1;i<=n;i++)
		{
			System.out.print("data ke-"+i+":");
			A[i]=Integer.parseInt(dis.readLine());
		}
			System.out.println();
		operasi1 oke = new operasi1();	
 		oke.nilai(A,n);
 		operasi2 ya = new operasi2();	
 		ya.nilai(A,n);
 		operasi3 yuhu = new operasi3();
 		yuhu.nilai(A,n);
 		operasi4 hwaici = new operasi4();
 		hwaici.nilai(A,n);
}
}
