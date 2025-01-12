/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.proyectoed.arbol;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class Tree<E> {
    
    private TreeNode<E> root;
    
    public Tree (E e) {
        this.root = new TreeNode<>(e); 
    }
    
    public boolean isEmpty () {
        return this.root == null;
    }

    public E getRoot() {
        return root.getContent();
    }
    
    public void setRoot (E content) {
        this.root.setContent(content);
    }
    
    public List<Tree<E>> getChildren() {
        return root.getChildren();
    }
    
   // Método para establecer un hijo en un índice específico de la lista de hijos
public void setChildren(int index, Tree<E> treeHijo) {
    if (index < 0) {
      
        throw new IndexOutOfBoundsException("El índice no puede ser negativo.");
    }
    List<Tree<E>> children = root.getChildren(); // Obtiene la lista de hijos del nodo raíz
    while (children.size() <= index) {
        // Asegura que la lista tenga suficientes espacios hasta alcanzar el índice
        while (children.size() <= index) {
            children.add(null); // Agrega elementos nulos si la lista es demasiado pequeña
        }
    }
    // Establece el árbol hijo en el índice indicado
    children.set(index, treeHijo);
}

// Método para verificar si el nodo raíz es una hoja (sin hijos)
public boolean isLeaf() {
    // Un nodo es hoja si su lista de hijos está vacía
    return this.root.getChildren().isEmpty();
}

// Método para agregar un nuevo hijo al nodo raíz
public void addChildren(E e) {
    if (e == null) {
        // Lanza una excepción si se intenta agregar un hijo con contenido nulo
        throw new IllegalArgumentException("No se puede agregar un hijo nulo.");
    }
    Tree<E> arbol = new Tree<>(e); // Crea un nuevo árbol hijo con el contenido proporcionado
    if (!root.getChildren().contains(arbol)) {
        // Agrega el nuevo árbol hijo solo si no existe ya en la lista de hijos
        root.getChildren().add(arbol);
    }
}

// Método para realizar un recorrido en amplitud (nivel por nivel) del árbol
    public void recorrido() {
    Queue<TreeNode<E>> pq = new ArrayDeque<>(); // Cola para gestionar el recorrido
    pq.add(root); // Agregar el nodo raíz a la cola
    TreeNode<E> nodoTmp;

    // Mientras la cola no esté vacía
    while (!pq.isEmpty()) {
        nodoTmp = pq.remove(); // Saca el primer nodo de la cola
        System.out.println(nodoTmp); // Imprime el nodo (debería ser su contenido)

        // Itera sobre los hijos del nodo actual
        for (int i = 0; i < nodoTmp.getChildren().size(); i++) {
            // Agrega cada hijo del nodo actual a la cola
            pq.add(nodoTmp.getChildren().get(i).root);
        }
    }
}
}
