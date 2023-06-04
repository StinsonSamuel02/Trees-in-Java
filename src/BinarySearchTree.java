import java.util.ArrayList;

public class BinarySearchTree<T extends Comparable<T>> {
    private NodeTree<T> root;
    private final int NODE_LEFT = 1;
    private final int NODE_RIGHT = 2;
    private final int NODES = 3;

    public boolean isEmpty() {
        return root == null;
    }

    public NodeTree<T> getRoot() {
        return root;
    }

    public boolean isRoot(NodeTree<T> node) {
        return root == node;
    }

    public boolean isLeaf(NodeTree<T> node) {
        return node.getLeft() == null && node.getRight() == null;
    }

    public boolean isInternal(NodeTree<T> node) {
        return !isLeaf(node);
    }

    /**
     * Metodo add() recurisvo
     */
    public NodeTree<T> add(NodeTree<T> origin, T element) {

        NodeTree<T> node = null;

        if (root == null) {
            root = new NodeTree<>(element);
            node = root;
        } else if (origin == null) {
            System.out.println("Origin is null");
        } else {

            if (origin.getElement().compareTo(element) > 0) {

                if (origin.getLeft() != null) {
                    node = add(origin.getLeft(), element);
                } else {
                    node = new NodeTree<>(element);
                    node.setParent(origin);
                    origin.setLeft(node);
                }
            } else {

                if (origin.getRight() != null) {
                    node = add(origin.getRight(), element);
                } else {
                    node = new NodeTree<>(element);
                    node.setParent(origin);
                    origin.setRight(node);
                }
            }
        }
        return node;
    }

    public void preOrder(NodeTree<T> node) {

        System.out.println(node.getElement().toString());

        if (node.getLeft() != null) {
            preOrder(node.getLeft());
        }

        if (node.getRight() != null) {
            preOrder(node.getRight());
        }
    }

    public void inOrder(NodeTree<T> node) {

        if (node.getLeft() != null) {
            inOrder(node.getLeft());
        }

        System.out.println(node.getElement().toString());

        if (node.getRight() != null) {
            inOrder(node.getRight());
        }
    }

    public void postOrder(NodeTree<T> node) {

        if (node.getLeft() != null) {
            postOrder(node.getLeft());
        }

        if (node.getRight() != null) {
            postOrder(node.getRight());
        }

        System.out.println(node.getElement().toString());
    }

    public int height(NodeTree<T> node) {
        int height = 0;

        if (isInternal(node)) {

            if (node.getLeft() != null) {
                height = Math.max(height, height(node.getLeft()));
            }

            if (node.getRight() != null) {
                height = Math.max(height, height(node.getRight()));
            }

            height++;
        }

        return height;
    }

    public int depth(NodeTree<T> node) {
        int depth = 0;

        if (node != getRoot()) {
            depth = 1 + depth(node.getParent());
        }

        return depth;
    }

    public void remove(NodeTree<T> node) {

        if (root == null) {
            System.out.println("Nothing to delete");
        } else if (isLeaf(node)) {
            removeLeaf(node);
        } else if (node.getRight() != null && node.getLeft() == null) {
            removeWithChild(node, NODE_RIGHT);
        } else if (node.getRight() == null && node.getLeft() != null) {
            removeWithChild(node, NODE_LEFT);
        } else {
            removeWithChild(node, NODES);
        }
    }

    private void removeLeaf(NodeTree<T> node) {

        if (isRoot(node)) {
            root = null;
        } else {

            NodeTree<T> parent = node.getParent();

            if (parent.getLeft() == node) {
                parent.setLeft(null);
            }

            if (parent.getRight() == node) {
                parent.setRight(null);
            }
        }
    }

    private void removeWithChild(NodeTree<T> node, int nodeType) {
        NodeTree<T> next = null;

        switch (nodeType) {
            case NODE_LEFT -> next = node.getLeft();
            case NODE_RIGHT -> next = minSubTree(node.getRight());
            case NODES -> {
                next = minSubTree(node.getRight());
                if (!isRoot(next.getParent())) {

                    node.getLeft().setParent(next);
                    node.getRight().setParent(next);

                    if (next.getParent().getLeft() == next) {
                        next.getParent().setLeft(null);
                    } else if (next.getParent().getRight() == next) {
                        next.getParent().setRight(null);
                    }
                }
            }
        }

        assert next != null;
        next.setParent(node.getParent());

        if (!isRoot(node)) {

            if (node.getParent().getLeft() == node) {
                node.getParent().setLeft(next);
            } else if (node.getParent().getRight() == node) {
                node.getParent().setRight(next);
            }

        } else {
            root = next;
        }

        if (node.getRight() != null && node.getRight() != next) {
            next.setRight(node.getRight());
        }

        if (node.getLeft() != null && node.getLeft() != next) {
            next.setLeft(node.getLeft());
        }
    }

    private NodeTree<T> minSubTree(NodeTree<T> node) {

        if (node != null && node.getLeft() != null) {
            while (!isLeaf(node)) {
                node = minSubTree(node.getLeft());
            }
        }

        return node;
    }

    public NodeTree<T> getNode(NodeTree<T> node, T element) {

        NodeTree<T> aux = null;

        if (node.getElement().compareTo(element) == 0) {
            aux = node;
        } else {

            if (node.getLeft() != null) {
                aux = getNode(node.getLeft(), element);
            }

            if (node.getRight() != null) {
                aux = getNode(node.getRight(), element);
            }

        }

        return aux;
    }

    public T getElement(NodeTree<T> node, T element) {

        NodeTree<T> aux = getNode(node, element);

        if (aux == null) {
            return null;
        }

        return aux.getElement();
    }

    public void getNodes(NodeTree<T> node, T element, ArrayList<NodeTree<T>> nodesList) {

        if (node.getElement().compareTo(element) == 0) {
            nodesList.add(node);
        }

        if (node.getLeft() != null) {
            getNodes(node.getLeft(), element, nodesList);
        }

        if (node.getRight() != null) {
            getNodes(node.getRight(), element, nodesList);
        }

    }

    public ArrayList<T> getElements(NodeTree<T> node, T element) {
        ArrayList<T> elements = new ArrayList<>();
        ArrayList<NodeTree<T>> nodesList = new ArrayList<>();

        getNodes(node, element, nodesList);

        for (NodeTree<T> aux : nodesList) {

            elements.add(aux.getElement());

        }

        return elements;
    }
}
