class Node {
    String value;
    Node left;
    Node right;
    int index;

    public Node(String value, int index) {
        this.value = value;
        this.index = index;
        this.left=this.right=null;
    }

    public String getValue() {
        return value;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}


public class SymbolTable {
    private Node root;
    private int size=0;

    public void increaseSize(){
        size+=1;
    }

    public SymbolTable() {
        this.root=null;
    }

    // searches value value, returns its position if found, or -1 otherwise
    public int searchValue(String value){
        if(this.root!=null){
            Node currentNode=this.root;
            while(currentNode!=null){
                if(currentNode.getValue().compareTo(value)==0){
                    return currentNode.getIndex();
                }
                else{
                    if(currentNode.getValue().compareTo(value)>0){
                        currentNode=currentNode.getLeft();
                    }
                    else{
                        currentNode=currentNode.getRight();
                    }
                }
            }

        }
        return -1;
    }

    public int insert(String value) {
        // checking if the BST is empty
        if (this.root == null) {
            this.root = new Node(value, 0);
            increaseSize();
            return 0;
        }

        // if BST not empty, search for the value
        int index=searchValue(value);
        if(index!=-1){
            return index;
        }
        else{
            Node currentNode = this.root;
            Node prevNode = null;
            while (true) {
                prevNode=currentNode;
                if (currentNode.getValue().compareTo(value) < 0) {
                    // it has to go on the right side
                    currentNode = currentNode.getRight();

                    // checking if the position of the new value is found
                    if (currentNode == null) {
                        currentNode = new Node(value, size);
                        increaseSize();
                        prevNode.setRight(currentNode);
                        return currentNode.getIndex();
                    }
                } else {
                    // it has to go on the left side
                    currentNode = currentNode.getLeft();

                    // checking if the position of the new value is found
                    if (currentNode == null) {
                        currentNode = new Node(value,size);
                        increaseSize();
                        prevNode.setLeft(currentNode);
                        return currentNode.getIndex();
                    }
                }
            }

        }

    }


}
