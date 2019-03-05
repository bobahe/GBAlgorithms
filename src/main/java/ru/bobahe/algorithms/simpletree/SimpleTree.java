package ru.bobahe.algorithms.simpletree;

import ru.bobahe.algorithms.stack.BasicStack;

public class SimpleTree<E extends Comparable<E>> implements Tree<E> {
    public static final int MAX_LEVEL = 5;

    private Node<E> rootNode;

    private Node<E> parentNode;
    private Node<E> foundNode;
    private int size;

    // для ограничения уровня добавления
    private int level;

    @Override
    public void add(E value) {
        if (rootNode == null) {
            rootNode = new Node<>(value);
        }

        if (!findNode(value) && level < MAX_LEVEL) {
            addNode(value);
        }

        size++;
    }

    @Override
    public boolean remove(E value) {
        if (findNode(value)) {
            switch (getNodeType(foundNode)) {
                case LEAF:
                    if (parentNode.leftNode == foundNode) {
                        parentNode.leftNode = null;
                    } else {
                        parentNode.rightNode = null;
                    }
                    break;
                case HAS_ONE_CHILD:
                    if (parentNode.leftNode == foundNode) {
                        parentNode.leftNode = foundNode.leftNode != null ? foundNode.leftNode : foundNode.rightNode;
                    } else {
                        parentNode.rightNode = foundNode.leftNode != null ? foundNode.leftNode : foundNode.rightNode;
                    }
                    break;
                case HAS_TWO_CHILDREN:
                    Node<E> successor = findSuccessor(foundNode.rightNode);

                    successor.leftNode = foundNode.leftNode;

                    if (successor != foundNode.rightNode) {
                        successor.rightNode = foundNode.rightNode;
                    }

                    if (parentNode != null) {
                        if (parentNode.leftNode == foundNode) {
                            parentNode.leftNode = successor;
                        } else {
                            parentNode.rightNode = successor;
                        }
                    } else {
                        rootNode = successor;
                    }
                    break;
                default:
                    throw new IllegalStateException("Invalid NodeType " + foundNode);
            }

            // foundNode последняя сылка на удаленный узел - обнулю, чтоб GC быстрее убрал
            foundNode = null;

            size--;

            return true;
        }

        return false;
    }

    @Override
    public boolean contains(E value) {
        return findNode(value);
    }

    @Override
    public boolean isEmpty() {
        return rootNode == null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int getHeight() {
        return getHeight(rootNode);
    }

    @Override
    public boolean isBalanced() {
        return isBalanced(rootNode);
    }

    public void traverse(TraverseMode traverseMode) {
        switch (traverseMode) {
            case IN:
                System.out.println("Inorder traversing");
                inOrder(rootNode);
                break;
            case PRE:
                System.out.println("Preorder traversing");
                preOrder(rootNode);
                break;
            case POST:
                System.out.println("Postorder traversing");
                postOrder(rootNode);
                break;
            default:
                throw new IllegalArgumentException("There is no such traverse mode " + traverseMode);
        }
    }

    private void inOrder(Node<E> node) {
        if (node != null) {
            inOrder(node.leftNode);
            System.out.print(node.toString() + ", ");
            inOrder(node.rightNode);
        }
    }

    private void preOrder(Node<E> node) {
        if (node != null) {
            System.out.print(node.toString() + ", ");
            preOrder(node.leftNode);
            preOrder(node.rightNode);
        }
    }

    private void postOrder(Node<E> node) {
        if (node != null) {
            postOrder(node.leftNode);
            postOrder(node.rightNode);
            System.out.print(node.toString() + ", ");
        }
    }

    private boolean isBalanced(Node<E> node) {
        return (node == null) ||
                        isBalanced(node.leftNode) &&
                        isBalanced(node.rightNode) &&
                        Math.abs(getHeight(node.leftNode) - getHeight(node.rightNode)) <= 1;

        // Какое-то жесткое условие... я бы getHeight() - 1 сравнивал для простоты
        // Кажется, что для такого условия, надо специально балансировать дерево
//         return getHeight() <= Math.ceil(Math.log(size()) / Math.log(2));
    }

    private int getHeight(Node<E> node) {
        // базовый случай
        if (node == null) {
            return 0;
        }

        // рекурсивный случай
        return 1 + Math.max(getHeight(node.leftNode), getHeight(node.rightNode));
    }

    // скопировано из методички
    public void display() {
        BasicStack<Node<E>> stack = new BasicStack<>(32);
        stack.push(rootNode);
        int nBlanks = 64;
        boolean isRowEmpty = false;

        while (!isRowEmpty) {
            BasicStack<Node<E>> localStack = new BasicStack<>(32);
            isRowEmpty = true;
            for (int i = 0; i < nBlanks - 1; i++) {
                System.out.print(" ");
            }
            while (!stack.isEmpty()) {
                Node<E> temp = stack.pop();
                if (temp != null) {
                    System.out.print(temp.toString());
                    localStack.push(temp.leftNode);
                    localStack.push(temp.rightNode);
                    if (temp.leftNode != null || temp.rightNode != null) {
                        isRowEmpty = false;
                    }
                } else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < nBlanks * 2 - 2; j++)
                    System.out.print(" ");
            }
            System.out.println();
            nBlanks = nBlanks / 2;
            while (!localStack.isEmpty()) {
                stack.push(localStack.pop());
            }

            for (int i = 0; i < 128; i++) {
                System.out.print(".");
            }
            System.out.println();
        }

    }

    private boolean findNode(E value) {
        level = 1;

        foundNode = rootNode;
        parentNode = null;

        while (foundNode != null) {
            if (foundNode.value.equals(value)) {
                return true;
            }

            parentNode = foundNode;

            if (foundNode.value.compareTo(value) > 0) {
                foundNode = foundNode.leftNode;
            } else {
                foundNode = foundNode.rightNode;
            }

            level++;
        }

        return false;
    }

    private void addNode(E value) {
        if (parentNode.value.compareTo(value) > 0) {
            parentNode.leftNode = new Node<>(value);
        } else {
            parentNode.rightNode = new Node<>(value);
        }
    }


    private NodeType getNodeType(Node<E> node) {
        if (node.leftNode == null && node.rightNode == null) {
            return NodeType.LEAF;
        }

        if (node.leftNode != null && node.rightNode != null) {
            return NodeType.HAS_TWO_CHILDREN;
        }

        return NodeType.HAS_ONE_CHILD;
    }

    private Node<E> findSuccessor(Node<E> node) {
        Node<E> successor = node;
        Node<E> successorParent = null;

        while (successor.leftNode != null) {
            successorParent = successor;
            successor = successor.leftNode;
        }

        if (successorParent != null) {
            successorParent.leftNode = successor.rightNode;
        }

        return successor;
    }

    static class Node<E> {
        private E value;
        private Node<E> leftNode;
        private Node<E> rightNode;

        Node(E value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }

    enum NodeType {
        LEAF,
        HAS_ONE_CHILD,
        HAS_TWO_CHILDREN
    }

    enum TraverseMode {
        IN,
        PRE,
        POST
    }
}