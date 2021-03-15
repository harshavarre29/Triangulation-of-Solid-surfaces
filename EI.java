
public class EI implements EdgeInterface,Comparable<EI> {
	float length;
	DynamicArray<pi> adjp=new DynamicArray();
	public PointInterface [] p=new PointInterface[2];
	EI(pi p1,pi p2) {
		this.length=(p1.x-p2.x)*(p1.x-p2.x)+(p1.y-p2.y)*(p1.y-p2.y)+(p1.z-p2.z)*(p1.z-p2.z);
		p[0]=(PointInterface)p1;
		p[1]=(PointInterface)p2;
	}
	@Override
	public PointInterface[] edgeEndPoints() {
		// TODO Auto-generated method stub
		return p;
	}
	@Override
	public int compareTo(EI e) {
		if((this.give(0).compareTo(e.give(0))==0 && this.give(1).compareTo(e.give(1))==0)|| (this.give(0).compareTo(e.give(1))==0 && this.give(1).compareTo(e.give(0))==0))
			return 0;
		return 1;
	}
	public float getL() {
		return this.length;
	}
	public pi give(int i) {
		return (pi)p[i];
	}
//	@Override
	public int compare(EI e) {
		if(this.length>e.length) return 1;
		else if(this.length<e.length) return -1;
		else return 0;
	}
	public String toString() {
		return this.give(0)+" "+this.give(1);
	}
}
