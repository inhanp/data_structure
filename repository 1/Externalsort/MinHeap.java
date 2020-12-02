// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

/**
 * Create min-heap
 * @author USER
 * @version 2020-07-01
 */
public class MinHeap {
    

    private int maxSize;
    private int size;
    private Record[] heap;
    private int hidden = 0;
    
    /**
     * Initialize MinHeap
     * @param max   Maximum size of heap
     */
    MinHeap(int max) {
        this.maxSize = max;
        this.size = 0;
        this.heap = new Record[maxSize];
    }
    
    /**
     * Get parent of received index
     * @param index Index to get parent
     * @return  Parent index
     */
    private int parent(int index) {
        if (index == 0) {
            return index;
        }
        return (index - 1) / 2;
    }
    
    /**
     * Identify whether received index is leaf
     * @param index Index to identify
     * @return  Return true if it is leaf
     */
    private boolean isLeaf(int index) {
        return index >= size / 2 && index <= size;
    }
    
    /**
     * Return left child index of current index
     * @param index Index to find left child
     * @return  index of left child
     */
    private int leftChild(int index) {
        return (index * 2) + 1;
    }
    /**
     * Return right child index of current index
     * @param index Index to find lerightft child
     * @return  index of right child
     */
    private int rightChild(int index) {
        return index*2 + 2;
    }
    /**
     * Swap contents at received index
     * @param first Index to swap
     * @param second    Index to swap
     */
    private void swap(int first, int second) {
        Record temp = heap[first];
        heap[first] = heap[second];
        heap[second] = temp;
    }
    /**
     * Insert record into current point
     * @param input Record to insert
     */
    public void insert(Record input) {
        if (size >= maxSize) {
            return;
        }
        heap[size] = input;
        int current = size;
        size++;
        while (heap[current].getKey() < heap[this.parent(current)].getKey()) {
            swap(current, this.parent(current));
            current = this.parent(current);
        }
    }

    /**
     * Sort MinHeap so smaller contents come to up
     * @param index Current index to sort
     */
    private void sort(int index) {
        if (!this.isLeaf(index)) {
            double current = heap[index].getKey();
            double left = heap[this.leftChild(index)].getKey();
            double right = heap[this.rightChild(index)].getKey();
            if (current > left || current > right) {
                if (left < right) {
                    this.swap(index, this.leftChild(index));
                    this.sort(this.leftChild(index));
                }
                else {
                    this.swap(index, this.rightChild(index));
                    this.sort(this.rightChild(index));
                }
            }
        }
    }
    
    /**
     * Return record at that index
     * @param index Index to return record
     * @return  Record at that index
     */
    public Record indexRecord(int index) {
        if (index < this.maxSize) {
            return heap[index];
        }
        return null;
    }
    
    /**
     * Place received record into received position
     * @param index Position to put record
     * @param input Record to insert
     */
    public void placeRecord(int index, Record input) {
        heap[index] = input;
        this.hidden++;
    }
    
    /**
     * Remove record at top
     * @return  Record at top
     */
    public Record removeMin() {
        if (this.size <= 0) {
            return null;
        }
        Record temp = this.heap[0];
        this.size--;
        if (size == 0) {
            this.heap[0] = null;
        }
        else {
            this.heap[0] = this.heap[this.size];
            this.sort(0);
        }
        return temp;
    }
    
    /**
     * Get current size of heap
     * 
     * @return  Current size of heap
     */
    public int getSize() {
        return this.size;
    }
    
    /**
     * Get number of hidden values in heap
     * 
     * @return  Number of hidden values
     */
    public int getHidden() {
        return this.hidden;
    }
    
    /**
     * Shift all hidden values to front
     * 
     * @param num   Number of hidden values in heap
     */
    public void shiftHidden() {
        int shift = this.maxSize - this.hidden;
        this.size = this.hidden;
        this.hidden = 0;
        
        for (int i = 0; i < this.hidden; i++) {
            this.heap[i] = this.heap[shift + i];
        }
    }
    
    public boolean isEmpty() {
        //return this.getSize() != 0 || this.getHidden() != 0;
        return this.getSize() == 0;
    }
    public void print() 
    { 
        for (int i = 1; i <= size / 2; i++) { 
            System.out.print(" PARENT : " + this.heap[i] 
                             + " LEFT CHILD : " + this.heap[2 * i] 
                             + " RIGHT CHILD :" + this.heap[2 * i + 1]); 
            System.out.println(); 
        } 
    }
}
