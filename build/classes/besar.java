import java.io.*;

class awalan{
	String inputan;
	int besar, kecil;
	char [] arrKarakter;
	public awalan(String inputan){
		this.inputan = inputan;
		this.besar = this.kecil = 0;
		this.arrKarakter = this.inputan.toCharArray();
	}
	public void deteksi(){
		for(int i=0;i<this.arrKarakter.length;i++){
			if((int)this.arrKarakter[i]>96 && (int)this.arrKarakter[i]<123){
				this.kecil++;
			}else if((int)this.arrKarakter[i]>64 && (int)this.arrKarakter[i]<91){
				this.besar++;
			}
		}
	}
	public void getDetails(){
		System.out.println("Jumlah huruf besar\t= "+this.besar);
		System.out.println("Jumlah huruf kecil\t= "+this.kecil);
	}
}

class lanjutan extends awalan{
	int spasi;
	char [] arrKarakter;
	public lanjutan(String inputan){
		super(inputan);
		this.spasi = 0;
		this.arrKarakter = super.arrKarakter;
	}
	public void deteksi(){
		for(int i=0;i<this.arrKarakter.length;i++){
			if((int)this.arrKarakter[i]==' '){
				this.spasi++;
			}
		}
	}
	public void getDetails(){
		System.out.println("Jumlah spasi\t\t= "+this.spasi);
	}
}

public class besar {
    public static void main(String[]args) throws Exception{
    	DataInputStream inputData = new DataInputStream(System.in);
    	String inputAwal;
    	System.out.print("Masukkan kata / kalimat : ");
    	inputAwal = inputData.readLine();

    	awalan awal = new awalan(inputAwal);
    	awalan lanjut = new lanjutan(inputAwal);

    	awal.deteksi();
    	lanjut.deteksi();

    	awal.getDetails();
    	lanjut.getDetails();
    }
}