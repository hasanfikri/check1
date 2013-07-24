
public class cabang extends bank {
	double v;
    public cabang(double p, double l, double t, double v) {
    	super(p,l,t);
    	this.v=v;
    }
    public double luas(){
    	return(super.luas()-v);
    }
    
    
}