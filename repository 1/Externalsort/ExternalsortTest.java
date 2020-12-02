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
 * Test methods of Externalsort class
 * @author USER
 * @version 2020-06-26
 */
public class ExternalsortTest extends student.TestCase {
    
    private Externalsort external;
    private Record[] heapRecord = new Record[7];
    private Record[] inputRecord = new Record[5];
    private Record record1 = new Record();
    private Record record2 = new Record();
    private Record record3 = new Record();
    private Record record4 = new Record();
    private Record record5 = new Record();
    private Record record6 = new Record();
    private Record record7 = new Record();
    private Record record8 = new Record();
    private Record record9 = new Record();
    private Record record10 = new Record();
    private Record record11 = new Record();
    private Record record12 = new Record();
    
    /**
     * Setup initial condition to test.
     * InputBuffer size : 5
     * OutputBuffer size : 5
     * MinHeap size : 7
     */
    public void setUp() {
        external = new Externalsort(5, 5, 7);
        byte[] byte1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 
            10, 11, 12, 13, 14, 15, 16}; //1st
        byte[] byte2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 
            11, 11, 12, 13, 14, 15, 16}; //7th
        byte[] byte3 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 
            10, 12, 12, 13, 14, 15, 16}; //2nd
        byte[] byte4 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 
            11, 12, 12, 13, 14, 15, 16}; //8th
        byte[] byte5 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 
            10, 13, 12, 13, 14, 15, 16}; //3rd
        byte[] byte6 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 
            11, 13, 12, 13, 14, 15, 16}; //9th
        byte[] byte7 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 
            10, 14, 12, 13, 14, 15, 16}; //4th
        
        byte[] byte8 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 
            11, 14, 12, 13, 14, 15, 16}; //10th
        byte[] byte9 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 
            10, 15, 12, 13, 14, 15, 16}; //5th
        byte[] byte10 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 
            11, 15, 12, 13, 14, 15, 16}; //11th
        byte[] byte11 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 
            10, 16, 12, 13, 14, 15, 16}; //6th
        byte[] byte12 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 
            11, 16, 12, 13, 14, 15, 16}; //12th
        record1.setTotal(byte1);
        record2.setTotal(byte2);
        record3.setTotal(byte3);
        record4.setTotal(byte4);
        record5.setTotal(byte5);
        record6.setTotal(byte6);
        record7.setTotal(byte7);
        
        record8.setTotal(byte8);
        record9.setTotal(byte9);
        record10.setTotal(byte10);
        record11.setTotal(byte11);
        record12.setTotal(byte12);
        
        heapRecord[0] = record1;
        heapRecord[1] = record2;
        heapRecord[2] = record3;
        heapRecord[3] = record4;
        heapRecord[4] = record5;
        heapRecord[5] = record6;
        heapRecord[6] = record7;
        
        inputRecord[0] = record8;
        inputRecord[1] = record9;
        inputRecord[2] = record10;
        inputRecord[3] = record11;
        inputRecord[4] = record12;
    }
    
    /**
     * Test setUpHeap() method
     */
    public void testSetUpHeap() {
        external.setUpHeap(heapRecord);
        MinHeap heap = external.getHeap();
        assertEquals(this.record1.getKey(), 
            heap.indexRecord(0).getKey(), 0.001);
        assertEquals(this.record2.getKey(), 
            heap.indexRecord(4).getKey(), 0.001);
        assertEquals(this.record3.getKey(), 
            heap.indexRecord(1).getKey(), 0.001);
        assertEquals(this.record4.getKey(), 
            heap.indexRecord(5).getKey(), 0.001);
        assertEquals(this.record5.getKey(), 
            heap.indexRecord(2).getKey(), 0.001);
        assertEquals(this.record6.getKey(), 
            heap.indexRecord(6).getKey(), 0.001);
        assertEquals(this.record7.getKey(), 
            heap.indexRecord(3).getKey(), 0.001);
    }
    
    /**
     * Test setUpInput() method
     */
    public void testSetUpInput() {
        external.setUpInput(inputRecord);
        Record[] testInput = external.getInBuffer();
        assertEquals(this.record8.getKey(), 
            testInput[0].getKey(), 0.001);
        assertEquals(this.record9.getKey(), 
            testInput[1].getKey(), 0.001);
        assertEquals(this.record10.getKey(), 
            testInput[2].getKey(), 0.001);
        assertEquals(this.record11.getKey(), 
            testInput[3].getKey(), 0.001);
        assertEquals(this.record12.getKey(), 
            testInput[4].getKey(), 0.001);
    }
    
    /**
     * Test setUpInput() method in a case input is empty
     */
    public void testSetUpInputEmpty() {
        inputRecord = new Record[5];
        external.setUpInput(inputRecord);
        Record[] testInput = external.getInBuffer();
        assertNull(testInput[0]);
        assertNull(testInput[1]);
        assertNull(testInput[2]);
        assertNull(testInput[3]);
        assertNull(testInput[4]);
    }
    
    /**
     * Tet sort() method
     */
    public void testSort() {
        external.setUpHeap(heapRecord);
        external.setUpInput(inputRecord);
        
        external.sort();
        external.sort();
        external.sort();
        
        Record[] testOutput = external.getOutBuffer();
        Record[] testInput = external.getInBuffer();
        
        assertEquals(this.record1.getKey(), testOutput[0].getKey(), 0.0001);
        assertEquals(this.record3.getKey(), testOutput[0].getKey(), 0.0001);
        assertEquals(this.record5.getKey(), testOutput[0].getKey(), 0.0001);
        assertNull(testInput[0]);
        assertNull(testInput[1]);
        assertNull(testInput[2]);
    }
    
    /**
     * Test sort() method in a case received record is less than
     * recently added outputBuffer record.
     */
    public void testSortWithLessInput() {
        heapRecord[0] = record8;
        inputRecord[0] = record1;
        external.setUpHeap(heapRecord);
        external.setUpInput(inputRecord);
        Record[] testInput = external.getInBuffer();
        
        assertEquals(this.record1.getKey(), 
            testInput[0].getKey(), 0.0000000001);
        
        external.sort();
        external.sort();
        
        Record[] testOutput = external.getOutBuffer();
        MinHeap heap = external.getHeap();
        
        assertEquals(this.record3.getKey(), testOutput[0].getKey(), 0.0001);
        assertEquals(this.record5.getKey(), testOutput[1].getKey(), 0.0001);
        external.sort();
        assertEquals(this.record1.getKey(), 
            heap.indexRecord(6).getKey(), 0.0000001);
        
        assertEquals(6, heap.getSize());
    }
    
    /**
     * Test sort() method in a case it has
     * full outBuffer
     */
    public void testSortFullOutPut() {
        external.setUpHeap(heapRecord);
        external.setUpInput(inputRecord);
        
        external.sort();
        external.sort();
        external.sort();
        external.sort();
        external.sort();
        
        Record[] testOutput = external.getOutBuffer();
        MinHeap heap = external.getHeap();
        
        assertNull(testOutput[0]);
        assertEquals(this.record6.getKey(), 
            heap.indexRecord(0).getKey(), 0.00001);
        assertEquals(this.record7.getKey(), 
            heap.indexRecord(1).getKey(), 0.00001);
        assertEquals(this.record8.getKey(), 
            heap.indexRecord(2).getKey(), 0.00001);
        assertEquals(this.record9.getKey(), 
            heap.indexRecord(3).getKey(), 0.00001);
        assertEquals(this.record10.getKey(), 
            heap.indexRecord(4).getKey(), 0.00001);
        assertEquals(this.record11.getKey(), 
            heap.indexRecord(5).getKey(), 0.00001);
        assertEquals(this.record12.getKey(), 
            heap.indexRecord(6).getKey(), 0.00001);
    }
}
