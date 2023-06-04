import org.w3c.dom.Node;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        tree.add(tree.getRoot(), 8);

        tree.add(tree.getRoot(), 4);
        tree.add(tree.getRoot(), 10);
        tree.add(tree.getRoot(), 12);
        tree.add(tree.getRoot(), 1);

        NodeTree<Integer> t = tree.add(tree.getRoot(), 9);
        NodeTree<Integer> t1 = tree.add(tree.getRoot(), 13);
        NodeTree<Integer> t2 = tree.add(tree.getRoot(), 90);
        tree.add(tree.getRoot(), 12);

        tree.remove(t1);

        System.out.println("PreOrden:");
        tree.preOrder(tree.getRoot());

        System.out.println("InOrden");
        tree.inOrder(tree.getRoot());

        System.out.println("PostOrden");
        tree.postOrder(tree.getRoot());

        System.out.println("Altura de root: " + tree.height(tree.getRoot()));
        System.out.println("Profundidad de root: " + tree.depth(tree.getRoot()));

        try {
            NodeTree<Integer> node = tree.getNode(tree.getRoot(), 90);
            System.out.println("getNode: " + node.getElement());
        } catch (Exception e) {
            System.out.println("No se encuentra ese nodo");
        }

        Integer element = tree.getElement(tree.getRoot(), 10);
        System.out.println("getElement: " + element);

        ArrayList<NodeTree<Integer>> nodes = new ArrayList<>();
        tree.getNodes(tree.getRoot(), 12, nodes);

        for (NodeTree<Integer> aux : nodes) {
            System.out.println("Node: " + aux.getElement());
        }

        ArrayList<Integer> elements = tree.getElements(tree.getRoot(), 12);
        for (Integer aux: elements){
            System.out.println("Element: " + aux);
        }
    }
}
