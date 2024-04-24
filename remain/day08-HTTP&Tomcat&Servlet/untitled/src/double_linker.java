import java.security.InvalidParameterException;

public class double_linker{
    public static void main(String[] args) {

    }
    static class Node {
        Node prev;
        int value;
        Node next;

        public Node(Node prev,int value,Node next){
            this.prev=prev;
            this.value=value;
            this.next=next;
        }
    }
    private final Node head;
    private final Node tail;
    public double_linker(){
        head=new Node(null,666,null);
        tail=new Node(null,888,null);
        head.next=tail;
        head.prev=head;
    }
    private Node findNode(int index){
        int i=-1;
        for(Node p=head;p!=tail;p=p.next,i++){
            if(i==index){
                return p;
            }
        }
        return null;
    }
    public void addFirst(int value){
        insert(0,value);
    }
    public void addLast(int value){
        Node prev=tail.prev;
        Node added=new Node(prev,value,tail);
        prev.next=added;
        tail.prev=added;
    }

    public void insert(int index,int value){
        Node prev=findNode(index-1);
        if(prev==null)
            throw illegalindex(index);
        Node next=prev.next;
        Node inserted=new Node(prev,value,next);
        prev.next=inserted;
        next.prev=inserted;
    }

    public void remove(int index){
        Node prev=findNode(index-1);
        if(prev==null)
            throw illegalindex(index);
        Node removed=prev.next;
        if(removed==tail){
            throw illegalindex(index);
        }
        Node next=removed.next;

        prev.next=next;
        next.prev=prev;
    }

    private IllegalArgumentException illegalindex(int index){
        return new InvalidParameterException(String.format("index [%d] 不合法%n",index));
    }
}






































