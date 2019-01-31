import java.util.HashSet;
import java.util.Set;

class CartesianTree<T extends Comparable<T>> {

    class Node<T extends Comparable<T>> {

        Node<T> left;
        Node<T> right;
        int weight;
        T content;

        Node(T content, int weight) {
            this.content = content;
            this.weight = weight;
        }
    }

    private int size;
    private Node<T> root;
    private Set<Integer> weights = new HashSet<>();


    private Node<T> rotateRight(Node<T> currentNode) {
        Node<T> root = currentNode;
        Node<T> rootLeft = currentNode.left;
        Node<T> rootRight = currentNode.right;
        currentNode = rootLeft;
        currentNode.right = root;
        root.left = rootRight;
        return currentNode;
    }

    private Node<T> rotateLeft(Node<T> currentNode) {
        Node<T> root = currentNode;
        Node<T> rootRight = currentNode.right;
        Node<T> rootLeft = rootRight.left;
        currentNode = rootRight;
        currentNode.left = root;

        root.right = rootLeft;
        return currentNode;
    }

    private Node<T> add(Node<T> currentNode, T content, int weight) {
        if (currentNode == null) {
            weights.add(weight);
            size++;
            return new Node<T>(content, weight);
        }

        if (content.compareTo(currentNode.content) > 0) {
            currentNode.right = add(currentNode.right, content, weight);

            if (currentNode.right.weight < currentNode.weight) {
                currentNode = rotateLeft(currentNode);
            }
        }
        if (content.compareTo(currentNode.content) < 0) {
            currentNode.left = add(currentNode.left, content, weight);

            if (currentNode.left.weight < currentNode.weight) {
                currentNode = rotateRight(currentNode);
            }
        }

        return currentNode;
    }

    public void add(T content, int weight) {
        if (!weights.contains(weight)) {
            root = add(root, content, weight);
        }
    }

    public void add(T content) {
        int weight = (int) (Math.random() * Integer.MAX_VALUE);
        weight++;
        while (weights.contains(weight)) {
            weight = (int) (Math.random() * Integer.MAX_VALUE);
            weight++;
        }
        root = add(root, content, weight);
    }

    private Node<T> remove(Node<T> currentNode, T content) {
        if (currentNode == null) return null;

        if (content.compareTo(currentNode.content) > 0) {
            currentNode.right = remove(currentNode.right, content);
        }

        if (content.compareTo(currentNode.content) < 0) {
            currentNode.left = remove(currentNode.left, content);
        }

        if (content.compareTo(currentNode.content) == 0) {
            if (currentNode.right == null && currentNode.left == null) {
                size--;

                weights.remove(currentNode.weight);

                return null;
            }

            if (currentNode.right == null) {
                currentNode = rotateRight(currentNode);

                currentNode.right = remove(currentNode.right, content);
            }

            if (currentNode.left == null) {
                currentNode = rotateLeft(currentNode);

                currentNode.left = remove(currentNode.left, content);
            }

            if (currentNode.left != null && currentNode.right != null) {
                if (currentNode.right.weight > currentNode.left.weight) {
                    currentNode = rotateRight(currentNode);
                    currentNode.right = remove(currentNode, content);
                } else {
                    currentNode = rotateLeft(currentNode);
                    currentNode.left = remove(currentNode.left, content);
                }
            }
        }
        return currentNode;
    }

    public void remove(T content) {
        root = remove(root, content);
    }

    private boolean contains(Node<T> currentNode, T content) {
        if (currentNode == null) return false;
        if (content.compareTo(currentNode.content) > 0) {return contains(currentNode.right, content);
        }

        if (content.compareTo(currentNode.content) < 0) {
            return contains(currentNode.left, content);
        }

        return content.compareTo(currentNode.content) == 0;
    }

    public boolean contains(T content){
        return contains(root, content);
    }

    public int size() {
        return size;
    }
}