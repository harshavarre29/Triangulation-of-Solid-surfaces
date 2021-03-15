
public class queue<T> {
	class Node<K> {
		K data;
		Node<K> next;
		Node(){
			data=null;
			next=null;
		}
		Node(K x){
			this.data=x;
		}
	}
	Node<T> front,back;
	int size=0;
	queue(){
		front=null;
		back=null;
	}
	public void insert(T t) {
		if(size==0) {
			front=back=new Node<>(t);
			
			size++;
		}
		else {
			back.next=new Node<>(t);
			back=back.next;
			size++;
		}
	}
	public T get() {
		T t=front.data;
		front=front.next;
		size--;
		return t;
	}
	public boolean isEmpty() {
		return this.size == 0;
	}
}
