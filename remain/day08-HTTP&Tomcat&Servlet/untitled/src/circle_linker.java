public class circle_linker {


    class Node{
        Node prev;
        int value;
        Node next;
        public Node(Node prev,int value,Node next){
            this.prev=prev;
            this.value=value;
            this.next=next;
        }
    }
    private final Node senetinel=new Node(null,-1,null);

    public circle_linker(){
        senetinel.next=senetinel;
        senetinel.prev=senetinel;
    }
    public void addFirst(int value){
        Node next =senetinel.next;
        Node prec=senetinel.prev;
        Node added=new Node(prec,value,next);
        new prev()
    }
}
