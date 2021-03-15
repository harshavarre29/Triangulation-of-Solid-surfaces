public class DynamicArray<T>
{
	private Object[]arr;
	public int currentSize;
	public DynamicArray()
	{
		arr=new Object[1];
		currentSize=0;
	}
	public void add(T val)
	{
		if(arr.length==currentSize)
		{
			Object[]temp=new Object[2*arr.length];
			for(int i=0;i<arr.length;i++)
			{
				temp[i]=arr[i];
			}
			arr=temp;
		}
		arr[currentSize]=val;
		currentSize+=1;
	}
	public void insert(T val)
	{
		for(int i=0;i<currentSize;i++)
		{
			//System.out.println(arr[i]+" "+val+" "+arr[i].equals(val));
			if(arr[i].equals(val))
			{
				return;
			}
		}
		this.add(val);
	}
	public String toString()
	{
		String s="[";
		for(int i=0;i<currentSize;i++)
		{
			s+= i==currentSize-1? arr[i].toString():arr[i].toString()+",";
		}
		s+="]";
		return s;
	}
	public T get(int i) {
	return (T) arr[i];
}
	public void append(int j,T t) {
		arr[j]=t;
	}
}
