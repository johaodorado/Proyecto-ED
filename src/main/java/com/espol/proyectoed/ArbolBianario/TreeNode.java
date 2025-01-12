/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.proyectoed.ArbolBianario;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author genzo
 */
public class TreeNode<E> {
   private E content;
    private List<Tree<E>> children;
    private int capacity = 1;

    public TreeNode(E content) {
        this.content = content;
        this.children = new ArrayList<>();
    }

    public E getContent() {
        return content;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public List<Tree<E>> getChildren() {
        return children;
    }

    public void setChildren(int index, Tree<E> treeHijo) {
        this.children.get(index).setRoot(treeHijo.getRoot());
    }

}
  
  
  
