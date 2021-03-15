
public class pi implements PointInterface,Comparable<pi> {
	public DynamicArray<pi> adjpoint= new DynamicArray<>();
	public DynamicArray<TI> triarray=new DynamicArray<>();
	float x,y,z;
	public boolean used=false;
	public int no=0;
	float[] cod=new float[3];
	pi(float x,float y,float z){
		used=false;
		this.x=x;
		this.y=y;
		this.z=z;
		cod[0]=x;cod[1]=y;cod[2]=z;
	}
	public float abs(float x)
	{
		if(x<0)return -1*x;
		return x;
	}
	@Override
	public float getX() {
		// TODO Auto-generated method stub
		return this.x;
	}

	@Override
	public float getY() {
		// TODO Auto-generated method stub
		return this.y;
	}

	@Override
	public float getZ() {
		// TODO Auto-generated method stub
		return this.z;
	}

	@Override
	public float[] getXYZcoordinate() {
		// TODO Auto-generated method stub
		return cod;
	}
	public int isGreater(float a,float b)
	{
		if(a-b>0.001)
		{
			return 1;
		}
		else if(a-b<-0.001)
		{
			return -1;
		}
		return 0;
	}
	@Override
	public int compareTo(pi p) {
		if(isGreater(this.x, p.x)!=0)
		{
			//System.out.println("a");
			return isGreater(this.x,p.x);
		}
		else if(isGreater(this.y,p.y)!=0)
		{	//System.out.println("b");
			return isGreater(this.y,p.y);
		}
		else if(isGreater(this.z,p.z)!=0)
		{	//System.out.println("c");
			return isGreater(this.z,p.z);
		}
		return 0;
	}
	@Override
	public boolean equals(Object Z) {
		pi p=(pi)Z;
		//System.out.println(this.compareTo(p)==0);
		return this.compareTo(p)==0;
	}
	@Override
	public String toString()
	{
		return "["+(this.x)+","+this.y+","+this.z+"]";
	}
}
