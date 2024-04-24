import java.util.Iterator;
import java.util.function.Consumer;

public class DynamicArray {
    private int size=0;
    private int capacity=8;
    private int[] array;

    public void addList(int element){
        add(size,element);
    }
    public void add(int index,int element){
        checkAndGrow();
        if(index>=0&&index<size){
            System.arraycopy(array,index,array,index+1,size-index);
        }
        array[index]=element;
        size++;
    }
    public void checkAndGrow(){
        if(size==0){
            array=new int[capacity];
        }else if (size==capacity){
            capacity+=capacity>>1;
        }
        int[] newArray=new int[capacity];
        System.arraycopy(array,0,newArray,0,size);
        array=newArray;
    }
    public int remove(int index){
        int removed=array[index];
        if(index<size-1){
            System.arraycopy(array,index+1,array,index,size-1-index);
        }
        size--;
        return removed;
    }
    public void foreach(){
        for(int i=0;i<size;i++){
            System.out.print(array[i]);
        }
        System.out.println();
    }

}
