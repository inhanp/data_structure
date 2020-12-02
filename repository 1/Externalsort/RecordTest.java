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
 * Test methods of Record class
 * @author USER
 * @version 2020-06-26
 */
public class RecordTest extends student.TestCase {
    
    private Record record;
    private ByteBuffer byteBuffer;
    private byte[] input;
    private byte[] tempByte;
    
    /**
     * Set up initial condition to test
     */
    public void setUp() {
        record = new Record();
        byteBuffer = ByteBuffer.allocate(Double.BYTES);
        input = new byte[16];
        tempByte = new byte[8];
    }
    
    /**
     * Test setID() method
     */
    public void testSetID() {
        byteBuffer.putLong(10);
        tempByte = byteBuffer.array();
        for (int i = 0; i < 8; i++) {
            input[i] = tempByte[i];
        }
        record.setID(input);
        assertEquals(10, record.getID());
    }
    /**
     * Test setKet() method
     */
    public void testSetKey() {
        byteBuffer.putDouble(56);
        tempByte = byteBuffer.array();
        for (int i = 0; i < 8; i++) {
            input[i + 8] = tempByte[i];
        }
        record.setKey(input);
        assertEquals(56.0, record.getKey(), 0.001);
    }
    /**
     * Test isEmpty() method
     */
    public void testIsEmpty() {
        assertTrue(record.isEmpty());
    }
}
