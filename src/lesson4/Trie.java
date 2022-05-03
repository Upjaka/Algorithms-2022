package lesson4;

import java.util.*;

import kotlin.NotImplementedError;
import kotlin.Pair;
import lesson3.BinarySearchTree;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Префиксное дерево для строк
 */
public class Trie extends AbstractSet<String> implements Set<String> {

    private static class Node {
        SortedMap<Character, Node> children = new TreeMap<>();
    }

    private final Node root = new Node();

    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        root.children.clear();
        size = 0;
    }

    private String withZero(String initial) {
        return initial + (char) 0;
    }

    @Nullable
    private Node findNode(String element) {
        Node current = root;
        for (char character : element.toCharArray()) {
            if (current == null) return null;
            current = current.children.get(character);
        }
        return current;
    }

    @Override
    public boolean contains(Object o) {
        String element = (String) o;
        return findNode(withZero(element)) != null;
    }

    @Override
    public boolean add(String element) {
        Node current = root;
        boolean modified = false;
        for (char character : withZero(element).toCharArray()) {
            Node child = current.children.get(character);
            if (child != null) {
                current = child;
            } else {
                modified = true;
                Node newChild = new Node();
                current.children.put(character, newChild);
                current = newChild;
            }
        }
        if (modified) {
            size++;
        }
        return modified;
    }

    @Override
    public boolean remove(Object o) {
        String element = (String) o;
        Node current = findNode(element);
        if (current == null) return false;
        if (current.children.remove((char) 0) != null) {
            size--;
            return true;
        }
        return false;
    }

    /**
     * Итератор для префиксного дерева
     * <p>
     * Спецификация: {@link Iterator} (Ctrl+Click по Iterator)
     * <p>
     * Сложная
     */
    @NotNull
    @Override
    public Iterator<String> iterator() {
        return new TrieIterator();
    }

    public class TrieIterator implements Iterator<String> {
        private String lastString = null;
        private final Queue<String> strings;

        private TrieIterator() {
            strings = new ArrayDeque<>();
            subtreeTraversal(root, "");
        }

        private void subtreeTraversal(Node current, String str) {
            for (char character : current.children.keySet()) {
                if (character == (char) 0)
                    strings.add(str);
                else
                    subtreeTraversal(current.children.get(character), str + character);
            }
        }

        @Override
        public boolean hasNext() {
            // T = O(1) операции с очередью выполняются за О(1)
            // R = O(1) дополнительной памяти не используется
            return !strings.isEmpty();
        }

        @Override
        public String next() {
            // T = O(1) операции с очередью выполняются за О(1)
            // R = O(1) дополнительной памяти не используется
            String str = strings.poll();
            if (str == null)
                throw new NoSuchElementException();
            else {
                lastString = str;
                return str;
            }
        }


        @Override
        public void remove() {
            // T = O(maxLength) - поиск узла в худшем случае это maxLength операций и удаление "лишних" узлов также
            //                    maxLength операций
            // R = O(maxLength) - стек имеет в худшем случае maxLength элементов
            if (lastString == null) {
                throw new IllegalStateException();
            }
            Node currentNode = root;
            Stack<Pair<Character, Node>> path = new Stack<>();
            path.push(new Pair<>((char) 0, currentNode));
            for (char character : lastString.toCharArray()) {
                currentNode = currentNode.children.get(character);
                path.push(new Pair<>(character, currentNode));
            }
            currentNode.children.remove((char) 0);
            lastString = null;
            size--;

            Pair<Character, Node> pair;
            while (path.peek().getSecond() != root) {
                pair = path.pop();
                if (pair.getSecond().children.size() == 0) {
                    // Удаление "лишних" узлов, которые не являются частью никакой строки
                    path.peek().getSecond().children.remove(pair.getFirst());
                }
            }
        }
    }
}