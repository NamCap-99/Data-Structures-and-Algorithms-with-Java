public class SkipNode<E extends Comparable<E>> implements Comparable<E> {

    int numLevel;
    public E element;
    SkipNode<E>[] next = new SkipNode[6];

    public SkipNode(E o){
        this.element = o;
    }

    SkipNode<E> getNext(int level){
        return this.next[level];
    }

    void setNext(SkipNode<E> next, int level){
        this.next[level] = next;
    }

    void add(SkipNode<E> skipNode, int level){

        SkipNode<E> current = this.getNext(level);
        if (current == null) {
            this.setNext(skipNode, level);
            return;
        }

        //So sánh skipNode.element với current element
        if(skipNode.element.compareTo(current.element) < 0){
            //skipNode.element < current.element
            this.setNext(skipNode, level);
            skipNode.setNext(current, level);
            return;
        }

        while (current.getNext(level) != null && current.element.compareTo(skipNode.element) < 0 &&
                                                current.getNext(level).element.compareTo(skipNode.element) < 0 ) {
            current = current.getNext(level);
            
        }

        SkipNode<E> successNode = current.getNext(level);
        current.setNext(skipNode, level);
        skipNode.setNext(successNode, level);
    }

    SkipNode<E> search(Object obj, int level){
        SkipNode<E> result = null;
        SkipNode<E> current = this.getNext(level);

        while (current != null && current.element.equals(obj)) {
            if(current.element.equals(obj)){
                result = current;
                break;
            }

            current = current.getNext(level);
        }

        return result;
    }

    void refreshAfterDelete(int level) {
        SkipNode<E> current = this.getNext(level);
        while (current != null && current.getNext(level) != null) {
            if (current.getNext(level).element == null) {
                SkipNode<E> successor = current.getNext(level).getNext(level);
                current.setNext(successor, level);
                return;
            }

            current = current.getNext(level);
        }
    }
    
    void printLevel(int level){
        System.out.print("level_" + level + " = [ null " );
        int length = 0;

        SkipNode<E> current = this.getNext(level);
        while (current != null) {
            length++;
            if(current.element != null){
                System.out.print(current.element.toString() + " ");
            }
            current = current.getNext(level);
        }

        System.out.print(" null ]");
    }

    @Override
    public int compareTo(E o) {
        // TODO Auto-generated method stub
        return 0;
    }

   
    
}