package org.server.negocio;

import java.util.*;

public class FilaNegocio extends PriorityQueue {

    public FilaNegocio() {
    }

    public FilaNegocio(int initialCapacity) {
        super(initialCapacity);
    }

    public FilaNegocio(Comparator comparator) {
        super(comparator);
    }

    public FilaNegocio(int initialCapacity, Comparator comparator) {
        super(initialCapacity, comparator);
    }

    public FilaNegocio(Collection c) {
        super(c);
    }

    public FilaNegocio(PriorityQueue c) {
        super(c);
    }

    public FilaNegocio(SortedSet c) {
        super(c);
    }

    @Override
    public boolean add(Object o) {
        return super.add(o);
    }

    @Override
    public Object peek() {
        return super.peek();
    }

    @Override
    public boolean remove(Object o) {
        return super.remove(o);
    }

    @Override
    public boolean contains(Object o) {
        return super.contains(o);
    }

    @Override
    public Object[] toArray() {
        return super.toArray();
    }

    @Override
    public Iterator iterator() {
        return super.iterator();
    }

    @Override
    public int size() {
        return super.size();
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public Object poll() {
        return super.poll();
    }

    @Override
    public Comparator comparator() {
        return super.comparator();
    }

    //Parte logica aqui abaixo




}
