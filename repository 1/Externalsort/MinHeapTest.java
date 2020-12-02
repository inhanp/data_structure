
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
import java.nio.ByteBuffer;

/**
 * Test methods of MinHeap class
 * @author USER
 * @version 2020-06-26
 */
public class MinHeapTest extends student.TestCase {

    private MinHeap heap;
    private ByteBuffer buffer;
    private byte[] byteInput;
    private byte[] tempByte;
    private Record input1;
    
    /**
     * Set up initial condition to test
     */
    public void setUp() {
        heap = new MinHeap(10);
        buffer = ByteBuffer.allocate(Double.BYTES);
        byteInput = new byte[16];
        tempByte = new byte[8];
        input1 = new Record();
        buffer.putLong(10);
        tempByte = buffer.array();
        for (int i = 0; i < 8; i++) {
            byteInput[i] = tempByte[i];
        }
        input1.setID(byteInput);
        buffer = ByteBuffer.allocate(Double.BYTES);
        buffer.putDouble(20);
        tempByte = buffer.array();
        for (int i = 0; i < 8; i++) {
            byteInput[i + 8] = tempByte[i];
        }
        input1.setKey(byteInput);
    }
    
    /**
     * Test insert() method
     */
    public void testInsert1() {
        heap.insert(input1);
        assertEquals(input1.getKey(), heap.indexRecord(0).getKey(), 0.001);
        assertEquals(input1.getID(), heap.indexRecord(0).getID());
    }
    
    /**
     * Test insert() method
     */
    public void testInsert2() {
        Record input2 = new Record();
        buffer = ByteBuffer.allocate(Double.BYTES);
        buffer.putLong(13);
        tempByte = buffer.array();
        for (int i = 0; i < 8; i++) {
            byteInput[i] = tempByte[i];
        }
        input2.setID(byteInput);
        buffer = ByteBuffer.allocate(Double.BYTES);
        buffer.putDouble(15);
        tempByte = buffer.array();
        for (int i = 0; i < 8; i++) {
            byteInput[i + 8] = tempByte[i];
        }
        input2.setKey(byteInput);
        heap.insert(input1);
        heap.insert(input2);
        assertEquals(input1.getKey(), heap.indexRecord(1).getKey(), 0.001);
        assertEquals(input1.getID(), heap.indexRecord(1).getID());
    }
    /**
     * Test insert() method
     */
    public void testInsert3() {
        Record input2 = new Record();
        Record input3 = new Record();
        Record input4 = new Record();
        
        buffer = ByteBuffer.allocate(Double.BYTES);
        buffer.putLong(13);
        tempByte = buffer.array();
        for (int i = 0; i < 8; i++) {
            byteInput[i] = tempByte[i];
        }
        input2.setID(byteInput);
        buffer = ByteBuffer.allocate(Double.BYTES);
        buffer.putDouble(15);
        tempByte = buffer.array();
        for (int i = 0; i < 8; i++) {
            byteInput[i + 8] = tempByte[i];
        }
        input2.setKey(byteInput);
        
        buffer = ByteBuffer.allocate(Double.BYTES);
        buffer.putLong(12);
        tempByte = buffer.array();
        for (int i = 0; i < 8; i++) {
            byteInput[i] = tempByte[i];
        }
        input3.setID(byteInput);
        buffer = ByteBuffer.allocate(Double.BYTES);
        buffer.putDouble(16);
        tempByte = buffer.array();
        for (int i = 0; i < 8; i++) {
            byteInput[i + 8] = tempByte[i];
        }
        input3.setKey(byteInput);
        
        buffer = ByteBuffer.allocate(Double.BYTES);
        buffer.putLong(11);
        tempByte = buffer.array();
        for (int i = 0; i < 8; i++) {
            byteInput[i] = tempByte[i];
        }
        input4.setID(byteInput);
        buffer = ByteBuffer.allocate(Double.BYTES);
        buffer.putDouble(8);
        tempByte = buffer.array();
        for (int i = 0; i < 8; i++) {
            byteInput[i + 8] = tempByte[i];
        }
        input4.setKey(byteInput);
        
        heap.insert(input1); //key : 20
        heap.insert(input2); //key : 15
        heap.insert(input3); //key : 16
        heap.insert(input4); //key : 8
        

        assertEquals(input1.getKey(), heap.indexRecord(3).getKey(), 0.001);
        assertEquals(input1.getID(), heap.indexRecord(3).getID());
        assertEquals(input2.getKey(), heap.indexRecord(1).getKey(), 0.001);
        assertEquals(input2.getID(), heap.indexRecord(1).getID());
        assertEquals(input3.getKey(), heap.indexRecord(2).getKey(), 0.001);
        assertEquals(input3.getID(), heap.indexRecord(2).getID());
        assertEquals(input4.getKey(), heap.indexRecord(0).getKey(), 0.001);
        assertEquals(input4.getID(), heap.indexRecord(0).getID());
        assertEquals(input4, heap.removeMin());
        assertEquals(input2, heap.removeMin());
        assertEquals(input3, heap.removeMin());
        assertEquals(input1, heap.removeMin());
    }
    /**
     * Test removeMin() method
     */
    public void testRemoveMin1() {
        Record input2 = new Record();
        buffer = ByteBuffer.allocate(Double.BYTES);
        buffer.putLong(13);
        tempByte = buffer.array();
        for (int i = 0; i < 8; i++) {
            byteInput[i] = tempByte[i];
        }
        input2.setID(byteInput);
        buffer = ByteBuffer.allocate(Double.BYTES);
        buffer.putDouble(15);
        tempByte = buffer.array();
        for (int i = 0; i < 8; i++) {
            byteInput[i + 8] = tempByte[i];
        }
        input2.setKey(byteInput);
        heap.insert(input1);
        heap.insert(input2);
        Record temp = heap.removeMin();
        assertEquals(input2.getKey(), temp.getKey(), 0.001);
        assertEquals(input2.getID(), temp.getID());
    }
    /**
     * Test removeMin() method
     */
    public void testRemoveMin2() {
        Record input2 = new Record();
        Record input3 = new Record();
        Record input4 = new Record();
        
        buffer = ByteBuffer.allocate(Double.BYTES);
        buffer.putLong(13);
        tempByte = buffer.array();
        for (int i = 0; i < 8; i++) {
            byteInput[i] = tempByte[i];
        }
        input2.setID(byteInput);
        buffer = ByteBuffer.allocate(Double.BYTES);
        buffer.putDouble(15);
        tempByte = buffer.array();
        for (int i = 0; i < 8; i++) {
            byteInput[i + 8] = tempByte[i];
        }
        input2.setKey(byteInput);
        
        buffer = ByteBuffer.allocate(Double.BYTES);
        buffer.putLong(12);
        tempByte = buffer.array();
        for (int i = 0; i < 8; i++) {
            byteInput[i] = tempByte[i];
        }
        input3.setID(byteInput);
        buffer = ByteBuffer.allocate(Double.BYTES);
        buffer.putDouble(16);
        tempByte = buffer.array();
        for (int i = 0; i < 8; i++) {
            byteInput[i + 8] = tempByte[i];
        }
        input3.setKey(byteInput);
        
        buffer = ByteBuffer.allocate(Double.BYTES);
        buffer.putLong(11);
        tempByte = buffer.array();
        for (int i = 0; i < 8; i++) {
            byteInput[i] = tempByte[i];
        }
        input4.setID(byteInput);
        buffer = ByteBuffer.allocate(Double.BYTES);
        buffer.putDouble(8);
        tempByte = buffer.array();
        for (int i = 0; i < 8; i++) {
            byteInput[i + 8] = tempByte[i];
        }
        input4.setKey(byteInput);
        
        heap.insert(input1); //key : 20
        heap.insert(input2); //key : 15
        heap.insert(input3); //key : 16
        heap.insert(input4); //key : 8
        
        Record temp = heap.removeMin();

        assertEquals(input4.getKey(), temp.getKey(), 0.001);
        assertEquals(input4.getID(), temp.getID());
        
        temp = heap.removeMin();

        assertEquals(input2.getKey(), temp.getKey(), 0.001);
        assertEquals(input2.getID(), temp.getID());
        
        temp = heap.removeMin();

        assertEquals(input3.getKey(), temp.getKey(), 0.001);
        assertEquals(input3.getID(), temp.getID());
        
        temp = heap.removeMin();

        assertEquals(input1.getKey(), temp.getKey(), 0.001);
        assertEquals(input1.getID(), temp.getID());
    }
}
