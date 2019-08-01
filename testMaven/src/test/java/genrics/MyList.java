package genrics;

import java.util.ArrayList;

public class MyList<E extends Number> {
	
	private ArrayList<E> arrayList = new ArrayList<>();
	
	
	public void add(E e){
		arrayList.add(e);
	}
	
	public E get(int index){
		return arrayList.get(index);
	}

}
