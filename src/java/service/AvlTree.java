/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.Comparator;

/**
 *
 * @author lucasbezerramaia
 * @param <T>
 */
public class AvlTree<T> {
    private AvlNode<T> root = null;
    private final Comparator<? super T> c;
    
    public AvlTree(Comparator<? super T> c) {
        this.root = null;
        this.c = c;
    }
    
    public void clear() {
        root = null;
    }
    
    public boolean isEmpty() {
        return root == null;
    }
    
    public AvlNode<T> getRootNode (){
        return root;
    }
    
    /** Retorna a altura da árvore */
    private static int height( AvlNode t ) {
        return t == null ? -1 : t.height;
    }
     /**
     * Retorna o maior valor ente lhs e rhs.
     */
    private static int max( int lhs, int rhs ) {
        return lhs > rhs ? lhs : rhs;
    }
    /** Retorna o fator de balanceamento da árvore com raiz t */ 
    private int getFactor (AvlNode t) {
        return height( t.left ) - height( t.right );
    }
    
    public boolean insert (T element) {
        root = insert (element, root);
        return true;
    }
    
    private AvlNode<T> insert (T element, AvlNode<T> n) {
        if( n == null )
            n = new AvlNode<>(element, null, null);
        else if(c.compare(element, n.key) < 0 ) 
            n.left = insert(element, n.left);
        else if(c.compare(element, n.key) > 0) 
            n.right = insert(element, n.right);
        
        n = balance (n);
        return n;
    }
    
    public AvlNode<T> balance (AvlNode<T> n) {
        if ( getFactor(n) == 2 ) {
                if (getFactor (n.left)>0) 
                    n = doRightRotation( n );
                else 
                    n = doDoubleRightRotation( n );
        }
        else if ( getFactor(n) == -2 ) {
                if ( getFactor(n.right)<0 ) 
                    n = doLeftRotation( n );
                else 
                    n = doDoubleLeftRotation( n );
        }
        n.height = max( height( n.left ), height( n.right ) ) + 1;
        return n;
    }
    /** Faz Rotação simples a direita */
    private AvlNode<T> doRightRotation( AvlNode<T> k2 ) {
        AvlNode k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = max( height( k2.left ), height( k2.right ) ) + 1;
        k1.height = max( height( k1.left ), k2.height ) + 1;
        return k1;
    }
    /** Rotação simples à esquerda */
    private AvlNode<T> doLeftRotation( AvlNode<T> k1 ) {
        AvlNode k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = max( height( k1.left ), height( k1.right ) ) + 1;
        k2.height = max( height( k2.right ), k1.height ) + 1;
        return k2;
    }
    /** Rotação dupla à direita */
    private AvlNode<T> doDoubleRightRotation( AvlNode<T> k3 ) {
        k3.left = doLeftRotation( k3.left );
        return doRightRotation( k3 );
   }
    /** Rotação dupla à esquerda */
    private AvlNode<T> doDoubleLeftRotation( AvlNode<T> k1 ) {
        k1.right = doRightRotation( k1.right );
        return doLeftRotation( k1 );
    }
    public T search(T key) {
        if(search(root, key) != null)
            return search(root, key).key;
        return null;
    }
    protected AvlNode<T> search(AvlNode<T> p, T key) {
        while (p != null) {
            /* se valor procuradp == chave do nó retorna referência ao nó */ 
            if (c.compare(key, p.key) == 0) 
                return p;
            /* se valor procurado < chave do nó, procurar na sub-árvore esquerda deste nó */
            else if (c.compare(key, p.key)<0) 
                p = p.left;
            /* se valor procurado > chave do nó, procurar na sub-árvore direita deste nó */
            else p = p.right;
        }
        // caso chave não foi achada, retorna null
        return null;
    }
    public void inorder() {
        inorder(root);
    }
    protected void inorder(AvlNode<T> p) {
        if (p != null) {
             inorder(p.left);
             System.out.print(p.key+" - ");
             inorder(p.right);
        }
    }
    public void preorder() {
        preorder(root);
    }
    protected void preorder(AvlNode<T> p) {
        if (p != null) {
             System.out.print(p.key + " ");
             preorder(p.left);
             preorder(p.right);
        }
    }
    public void postorder() {
        postorder(root);
    }
    protected void postorder(AvlNode<T> p) {
        if (p != null) {
             postorder(p.left);
             postorder(p.right);
             System.out.print(p.key + " ");
        }
    }
protected AvlNode<T> searchFather (T key) {
    AvlNode<T> p = root;
    AvlNode<T> prev = null;
    while (p != null && c.compare(key, p.key) != 0) {  // acha o nó p com a chave el
        prev = p;                           
        if (c.compare(p.key, key) < 0)
              p = p.right;
        else p = p.left;
    }
    if (p!=null && c.compare(p.key, key) == 0) return prev;
    return null;
}
/* método de autoria de Leonardo Zandoná - 2006/2 */
public void displayTree() {
    if (isEmpty()){
            System.out.println("Árvore vazia!");
            return;
    }    		
            String separator = String.valueOf("  |__");
            System.out.println(this.root.key+"("+root.height+")");
            displaySubTree(root.left,  separator);
            displaySubTree(root.right, separator);
    }
    private void displaySubTree(AvlNode<T> node, String separator) {
            if (node != null) {
                    AvlNode<T> father = this.searchFather(node.key);
                    if (node.equals(father.left) == true) {
                            System.out.println(separator+node.key+"("+node.height+")"+" (ESQ)");
                    }else{
                            System.out.println(separator+node.key+"("+node.height+")"+" (DIR)");
                    }			
                    displaySubTree(node.left,  "     "+separator);
                    displaySubTree(node.right, "     "+separator);
            }
    }
}
