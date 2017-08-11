/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author lucasbezerramaia
 * @param <T>
 */
public class AvlNode<T> {
    protected int height;
    public T key;
    protected AvlNode<T> left, right;
    
    public AvlNode(T key){
        this(key, null, null );
    }
    
    public AvlNode(T key, AvlNode<T> left, AvlNode<T> right){
        this.key = key;
        this.left = left;
        this.right = right;
        this.height   = 0;
    }
}
