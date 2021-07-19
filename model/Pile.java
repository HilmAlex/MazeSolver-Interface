package model;

import java.util.Optional;

public class Pile implements IArrays {
    /**
     * @param end Ultimo nodo de la pila.
     * @param size Tamaño de la pila.
     */
    Node end;
    int size;
    int[][] solution;

    /**
     * Clase Nodo anidada en la pila, se crea almacenando un objeto y con un referencia
     * nula al siguiente nodo de la pila
     */
    class Node {
        /**
         * @param element Objeto que se encuentra almacenado en el nodo.
         * @param next Referencia al siguiente nodo de la pila.
         */
        Object element;
        Node next;

        public Node(Object object) {
            this.element = object;
            next = null;
        }

        public Object getElement() {
            return this.element;
        }

        public Node getNext() {
            return next;
        }
    }

    public Pile() {
        end = null;
        size = 0;
    }

    /**
     * Con el metodo push() se inserta un nuevo nodo en la pila
     *
     * @param object El objeto a insertar en la pila
     */
    public void push(Object object) {
        /**
         @param newNodo Es el nuevo nodo a ingresar en la pila.
         Si el nodo final es nulo, el nuevo nodo se convierte en el nodo final
         Si no lo es, el nuevo nodo toma como referencia al nodo final y el nuevo
         nodo se convierte en el nodo final
         */
        Node newNode = new Node(object);
        if (Optional.ofNullable(end).isEmpty()) {
            end = newNode;
        } else {
            newNode.next = end;
            end = newNode;
        }
        size++;
    }

    /**
     * El metodo pop() elimina y devuelve el ultimo elemento de la pila.
     *
     * @return Objeto de la pila eliminado.
     */
    public Object pop() {
        if (Optional.ofNullable(end).isEmpty()) {
            return null;
        } else {
            Object object = end.element;
            end = end.next;
            size--;
            return object;
        }
    }

    /**
     * El metodo peek() devuelve el ultimo elemento de la pila sin eliminarlo.
     *
     * @return Ultimo objeto de la pila.
     */
    public Object peek() {
        if (Optional.ofNullable(end).isEmpty()) {
            return null;
        } else {
            Object object = end.element;
            return object;
        }
    }

    public void showPileAsArray(int rowsNumber, int columnsNumber) {
        int[][] array = getPileAsArray(rowsNumber, columnsNumber);
        IArrays.printArray(array);
    }

    public int[][] getPileAsArray(int rowsNumber, int columnsNumber) {
        Optional<Node> tempNode;
        Position tempPosition;

        int[][] pileAsArray;

        pileAsArray = new int[rowsNumber][columnsNumber];

        IArrays.fillArray(pileAsArray, rowsNumber, columnsNumber);

        //El nodo temporal toma el valor del ultimo nodo en la pila
        tempNode = Optional.ofNullable(this.end);

        while (tempNode.isPresent()) {
            // La posicion temporal toma el objeto Model.Position del nodo temporal
            tempPosition = (Position) tempNode.get().getElement();

            pileAsArray[tempPosition.getRow()][tempPosition.getColumn()] = 1;
            tempNode = Optional.ofNullable(tempNode.get().getNext());
        }

        return pileAsArray;
    }

    public Boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public Object end() {
        if (Optional.ofNullable(end).isEmpty()) {
            return null;
        } else {
            return end.element;
        }
    }

    public Object getEnd() {
        return end.element;
    }
}

