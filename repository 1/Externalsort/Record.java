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

/**'
 * Receive byte array and setup ID and key of this record
 * 
 * @author USER
 * @version 2020-06-23
 */
public class Record {
    
    private long recordID;
    private double key;
    
    /**
     * Initialize Record()
     */
    Record() {
        this.recordID = 0;
        this.key = 0;
    }
    
    /**
     * Set up ID of this record with 
     * first 8 bytes of received byte array
     * 
     * @param input Received 16 byte array
     */
    public void setID(byte[] input) {
        byte[] temp = new byte[8];
        for (int i = 0; i < 8; i++) {
            temp[i] = input[i];
        }
        ByteBuffer bb = ByteBuffer.wrap(temp);
        this.recordID = bb.getLong();
    }
    
    /**
     * Set up key of this record with 
     * last 8 bytes of received byte array
     * 
     * @param input Received 16 byte array
     */
    public void setKey(byte[] input) {
        byte[] temp = new byte[8];
        for (int i = 0; i < 8; i++) {
            temp[i] = input[i + 8];
        }
        ByteBuffer bb = ByteBuffer.wrap(temp);
        this.key = bb.getDouble();
    }
    
    /**
     * Set up ID and key with received 16 byte array
     * 
     * @param input Received 16 byte array
     */
    public void setTotal(byte[] input) {
        this.setKey(input);
        this.setID(input);
    }
    
    /**
     * Return ID of this record
     * 
     * @return  ID of this record
     */
    public long getID() {
        return this.recordID;
    }
    
    /**
     * Return key of this record
     * 
     * @return  key of this record
     */
    public double getKey() {
        return this.key;
    }
    
    /**
     * Identify whether this record is empty
     * with no key or ID or not
     * 
     * @return  Whether this record is empty or not
     */
    public boolean isEmpty() {
        return this.recordID == 0 && this.key == 0;
    }
}
