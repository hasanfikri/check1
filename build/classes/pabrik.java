
public class pabrik extends perusahaan  {
	double s;
 public pabrik(double p,double l,double s) {
    super(p,l);
    this.s=s;
    }
    public double luas(){
    	return(super.luas()+s);
    }
    
    
}