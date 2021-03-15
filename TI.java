
public class TI implements TriangleInterface,Comparable<TI> {
	public PointInterface [] p=new PointInterface[3];
	boolean isvisited;
	public int dist=0;
	DynamicArray<TI> adjtri=new DynamicArray<>();
	EI e1,e2,e3;
	TI(pi p1,pi p2, pi p3) {
		this.isvisited=false;
		this.e1=new EI(p1,p2);
		this.e2=new EI(p2,p3);
		this.e3=new EI(p3,p1);
		p[0]=(PointInterface)p1;
		p[1]=(PointInterface)p2;
		p[2]=(PointInterface)p3;
	}
	@Override
	public PointInterface[] triangle_coord() {
		// TODO Auto-generated method stub
		return p;
	}
	@Override
	public int compareTo(TI t) {
		int a=0;
		if(this.give(0).compareTo(t.give(0))==0 || this.give(0).compareTo(t.give(1))==0 || this.give(0).compareTo(t.give(2))==0)
			a++;
		if(this.give(1).compareTo(t.give(0))==0 || this.give(1).compareTo(t.give(1))==0 || this.give(1).compareTo(t.give(2))==0)
			a++;
		if(this.give(2).compareTo(t.give(0))==0 || this.give(2).compareTo(t.give(1))==0 || this.give(2).compareTo(t.give(2))==0)
			a++;
		if(a==3)
			return 0;
		return 1;
	}
	public pi give(int i) {
		return (pi)p[i];
	}
	public int isadj(TI t) {
		int a=0;
		if(this.give(0).compareTo(t.give(0))==0 || this.give(0).compareTo(t.give(1))==0 || this.give(0).compareTo(t.give(2))==0)
			a++;
		if(this.give(1).compareTo(t.give(0))==0 || this.give(1).compareTo(t.give(1))==0 || this.give(1).compareTo(t.give(2))==0)
			a++;
		if(this.give(2).compareTo(t.give(0))==0 || this.give(2).compareTo(t.give(1))==0 || this.give(2).compareTo(t.give(2))==0)
			a++;
		return a;
	}
	public String toString() {
		return this.give(0)+" "+this.give(1)+" "+this.give(2);
	}
}
