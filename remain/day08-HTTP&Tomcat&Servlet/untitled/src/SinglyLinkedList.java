import java.security.InvalidParameterException;

public class SinglyLinkedList {
    private Node head;

    private static class Node {
        int value;
        Node next;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    public void addFirst(int value) {
        this.head = new Node(value, this.head);
    }

    public void loop() {
        Node cur = this.head;
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public void loop2() {
        for (Node cur = this.head; cur != null; cur = cur.next) {
            System.out.print(cur.value + " ");
        }
        System.out.println();

    }
    public void loop3(){
        recursion(this.head);
    }
    private void recursion(Node curr){
        if(curr==null)
            return;
        //sout
        recursion(curr.next);
    }
    private Node findLast(){
        if(this.head==null)
            return null;
        Node curr;
        for(curr=this.head;curr.next!=null){
            curr=curr.next;
        }
        return curr;
    }
    public void addLast(int value){
        Node last=findLast();
        if(last==null) {
            addFirst(value);
            return;
        }
        last.next=new Node(value,null);
    }
    public void addLast(int first,int ...rest){
        Node sublist=new Node(first,null);
        Node curr=sublist;
        for(int value:rest){
            curr.next=new Node(value,null);
            curr=curr.next;
        }
        Node last=findLast();
        if(last==null){
            this.head=sublist;
            return;
        }
        last.next=sublist;
    }
    private Node findNode(int index){
        int i=0;
        for(Node curr=this.head;curr!=null;curr=curr.next,i++){
            if(index==i){
                return curr;
            }
        }
        return null;
    }
    private IllegalArgumentException illegalindex(int index){
        return new InvalidParameterException(String.format("index [%d] 不合法%n",index));

    }
    public int get(int index){
        Node node=findNode(index);
        if(node!=null)
            return node.value;
        throw illegalindex(index);
    }
    public void insert(int index,int value){
        if(index==0){
            addFirst(value);
            return;
        }
        Node prev=findNode(index-1);
        if(prev==null){
            throw illegalindex(index);
        }
        prev.next=new Node(value,prev.next);

    }
    public void remove(int index){
        if(index==0){
            if(this.head!=null){
                this.head=this.head.next;
                return;
            }else{
                throw illegalindex(index);
            }
        }
        Node prev=findNode(index-1);
        Node curr;
        if(prev!=null&&(curr=prev.next)!=null){
            prev.next=curr.next;
        }else{
            throw illegalindex(index);
        }
    }
}
