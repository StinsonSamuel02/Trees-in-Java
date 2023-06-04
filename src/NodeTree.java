public class NodeTree<T extends Comparable<T>> {

    private T element;
    private NodeTree<T> parent;
    private NodeTree<T> left;
    private NodeTree<T> right;

    public NodeTree(T element) {
        this.element = element;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public NodeTree<T> getParent() {
        return parent;
    }

    public void setParent(NodeTree<T> parent) {
        this.parent = parent;
    }

    public NodeTree<T> getLeft() {
        return left;
    }

    public void setLeft(NodeTree<T> left) {
        this.left = left;
    }

    public NodeTree<T> getRight() {
        return right;
    }

    public void setRight(NodeTree<T> right) {
        this.right = right;
    }
}
