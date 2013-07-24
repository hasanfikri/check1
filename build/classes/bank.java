
public class bank extends perusahaan{
	double t;
    public bank(double p,double l,double t){
    	super(p,l);
    	this.t=t;
    }
    public double luas(){
    	return (super.luas()+t);
    }
}
    
    
