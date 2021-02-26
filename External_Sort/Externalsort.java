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
 * Sort Records by using MinHeap
 * @author USER
 * @version 2020-07-01
 */
public class Externalsort {
    
    private MinHeap heap;
    
    private MergeInfo merge;
    
    //private GenFile gene;
    
    private int maxInput = 512; //Maximum size of InputBuffer
    
    private int maxOutput = 512; //Maximum size of OutputBuffer
    
    private int heapMax = 4096; //Maximum size of MinHeap
    
    private Record[] inBuffer; //InputBuffer
    
    private Record[] outBuffer; //OutputBuffer
    
    private int inIndex = 0; //Current index of inputBuffer
    
    private int outIndex = 0; //Current index of outputBuffer
    
    private double prevKey = 0;
    /**
     * Initialize Externalsort class
     */
    Externalsort() {
        this.heap = new MinHeap(this.heapMax);
        this.inBuffer = new Record[this.maxInput];
        this.outBuffer = new Record[this.maxOutput];
        this.merge = new MergeInfo(this.heap);
    }
    
    /**
     * Initialize Externalsort for testcase
     * 
     * @param input     Size of inputBuffer for test
     * @param output    Size of outputBuffer for test
     * @param heapSize  Size of heap for test
     */
    Externalsort(int input, int output, int heapSize) {
        this.maxInput = input;
        this.maxOutput = output;
        this.heapMax = heapSize;
        this.heap = new MinHeap(this.heapMax);
        this.inBuffer = new Record[this.maxInput];
        this.outBuffer = new Record[this.maxOutput];
        this.merge = new MergeInfo(this.heap);
    }
    
    public boolean setUp(Record[] input) {
        if (this.heap.getSize() != this.heapMax) {
            this.setUpHeap(input);
            return true;
        }
        //if (this.inBuffer.length != this.maxInput) {
          //  this.setUpInput(input);
          //  return true;
     //   }
        return false;
    }
    
    /**
     * Set up initial condition of heap
     * @param input Record[] to insert into initial heap
     */
    public void setUpHeap(Record[] input) {
        for (int i = 0; i < input.length; i++) {
            this.heap.insert(input[i]);
        }
    }
    
    /**
     * Set up inBuffer for sort
     * @param input Record[] to use as inBuffer
     */
    public void setUpInput(Record[] input) {
        
        int i = 0;
        
        while (i < this.maxInput && input[i] != null) {
            this.inBuffer[i] = input[i];
            i++;
        }
    }
    
    /**
     * Sort by using MinHeap
     */
    public void sort() {
        if (this.inBuffer[this.inIndex] == null) {
            this.sortNoInput();
        }
        this.sortWithInput();
    }
    
    public void sortWithInput() {
        while (this.inBuffer[this.inIndex] != null) {
            Record temp = this.heap.removeMin();
            if (temp != null) {
                this.outBuffer[this.outIndex] = temp;
                this.prevKey = temp.getKey();
                this.outIndex++;
            }
            
            temp = this.inBuffer[this.inIndex];
            this.inBuffer[this.inIndex] = null;
            this.inIndex++;
            
            if (prevKey <= temp.getKey()) {
                this.heap.insert(temp);
            }
            else {
                this.heap.placeRecord(this.heap.getSize(), temp);
            }
            
            if (this.outIndex == this.maxOutput) {
                this.outIndex = 0;
                this.prevKey = 0;
                break;
            }
        }
        
        if (this.inIndex == this.maxInput) {
            this.inIndex = 0;
        }
    }
    
    /**
     * Start to sort with Record in MinHeap
     * since there is no more inBuffer left.
     */
    public void sortNoInput() {
        Record temp;
        
        while (!this.heap.isEmpty()) {
            temp = this.heap.removeMin();
            if (this.heap.isEmpty()) {
                this.heap.shiftHidden();
            }
            if (temp != null) {
                this.outBuffer[this.outIndex] = temp;
                this.outIndex++;
            }
            if (this.outIndex == this.maxOutput) {
                this.outIndex = 0;
                break;
            }
            /**
            if (this.outIndex == this.maxOutput) {
                this.merge.saveBuffer(this.outBuffer);
                this.outBuffer = new Record[this.maxOutput];
                this.outIndex = 0;
            }
            **/
        }
    }
    
    /**
     * Return heap for test
     * @return  current heap
     */
    public MinHeap getHeap() {
        return this.heap;
    }
    
    /**
     * Return inBuffer for test
     * @return  current inBuffer
     */
    public Record[] getInBuffer() {
        return this.inBuffer;
    }
    
    /**
     * Return outBuffer for test
     * @return  current ourBuffer
     */
    public Record[] getOutBuffer() {
        return this.outBuffer;
    }
    
    public static void main(String[] args) {
        FileParser parser = new FileParser(args[0]);
        Externalsort external = new Externalsort();
        MinHeap heap = new MinHeap(4096);
        MergeInfo mInfo = new MergeInfo(heap);
        if (args[0] == null) {
            return;
        }
        int i = 0;
        Record[] record1 = new Record[512];
        Record[] record2 = new Record[512];
        while (parser.remains() && i < 8) {
            record1 = parser.readFileBlock();
            external.setUpHeap(record1);
            i++;
        }
        while (parser.remains()) {
            record1 = parser.readFileBlock();
            external.setUpInput(record1);
            external.sortWithInput();
            record2 = external.getOutBuffer();
            parser.writeFileBlock(record2);
        }
        while (!external.getHeap().isEmpty()) {
            //System.out.println(external.getHeap().getSize());
            external.sortNoInput();
            record2 = external.getOutBuffer();
            //System.out.println(parser.numberOfFiles());
            parser.writeFileBlock(record2);
        }
        
        
        int num = parser.numberOfFiles();
        int check = 0;
        int checkPoint = parser.numberOfFiles();
        if (num > 1) {
            while (num > 2) {
                for (i = 0; i < num; i = i + 2) {
                    String input1 = "run"+(check + i)+".bin";
                    String input2 = "run"+(check + i+1)+".bin";
                    heap = new MinHeap(4096);
                    mInfo = new MergeInfo(heap);
                    mInfo.mergeTwoFile(input1, input2);
                }
                check = checkPoint;
                checkPoint = parser.numberOfFiles();
                num = num / 2;
            }
            String input1 = "run"+(check)+".bin";
            String input2 = "run"+(check+1)+".bin";
            heap = new MinHeap(4096);
            mInfo = new MergeInfo(heap);
            mInfo.mergeTwoFile(input1, input2);
            parser = new FileParser("run"+checkPoint+".bin");
        }
        else {
            parser = new FileParser("run0.bin");
        }
        
        int j = 0;
        
        Record[] record = new Record[512];
        while (parser.remains()) {
            record = parser.readFileBlock();
            System.out.print(record[0].getID() + " "+ record[0].getKey() + " ");
            j++;
            if (j == 5) {
                System.out.printf("\n");
                j = 0;
            }
        }
    }
}
