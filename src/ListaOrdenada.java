// Lista simplemente ordenada (ascendente), genérica
public class ListaOrdenada<T extends Comparable<T>> {
    private static class Node<T> {
        T value;
        Node<T> next;
        Node(T value) { this.value = value; }
    }

    private Node<T> head;
    private int size = 0;

    public ListaOrdenada() { }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }

    // Inserta manteniendo orden ascendente. Permite duplicados (se añaden después de iguales).
    public void add(T value) {
        if (value == null) throw new NullPointerException("Null no permitido");
        Node<T> nuevo = new Node<>(value);
        if (head == null || value.compareTo(head.value) < 0) {
            nuevo.next = head;
            head = nuevo;
            size++;
            return;
        }
        Node<T> actual = head;
        while (actual.next != null && actual.next.value.compareTo(value) <= 0) {
            actual = actual.next;
        }
        nuevo.next = actual.next;
        actual.next = nuevo;
        size++;
    }

    // Elimina la primera ocurrencia, devuelve true si se eliminó.
    public boolean remove(T value) {
        if (head == null) return false;
        if (head.value.equals(value)) {
            head = head.next;
            size--;
            return true;
        }
        Node<T> actual = head;
        while (actual.next != null && !actual.next.value.equals(value)) {
            actual = actual.next;
        }
        if (actual.next == null) return false;
        actual.next = actual.next.next;
        size--;
        return true;
    }

    public boolean contains(T value) {
        Node<T> actual = head;
        while (actual != null) {
            if (actual.value.equals(value)) return true;
            actual = actual.next;
        }
        return false;
    }

    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> actual = head;
        while (actual != null) {
            sb.append(actual.value);
            if (actual.next != null) sb.append(", ");
            actual = actual.next;
        }
        sb.append("]");
        return sb.toString();
    }
}

