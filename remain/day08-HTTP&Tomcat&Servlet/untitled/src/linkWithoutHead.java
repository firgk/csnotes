public class linkWithoutHead {
    private static class Node {
        int value;
        Node next;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node head=new Node(Integer.MIN_VALUE,null);
    private Node findNode(int index){
        int i=-1;
        for(Node curr=this.head;curr!=null; curr=curr.next,i++){
            if(i==index){
                return curr;
            }
        }
        return null;
    }
    private Node findLast(){
        Node curr;
        for(curr=this.head;curr.next!=null;){
            curr=curr.next;
        }
        return curr;
    }



}
