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
 * Input max 8 blocks of records and merge them into one merged output file.
 * @author USER
 * @version 2020-07-01
 */
public class MergeInfo {
    
    private int flag = 0; // state of the input record data storage space, flag=0 implies EMPTY
    
    private Record[][] records; // Input Record
    
    private MinHeap mHeap; // Use MinHeap class with the name mHeap 
    
    private int maxLength = 512; // Maximum Record Length
    
    private Record[] mergedRecord; // Output Record Merged
    
    private int mIndex; // Output Record Pointer
    
    private boolean stopInsert = false; // 

    private int recordsIndex = 0;
    private int recordIndex = 0;
    /**
     * Initialize MergeInfo
     * @param heap  Heap to merge received runs
     */
    MergeInfo(MinHeap heap) {
        records = new Record[8][this.maxLength]; // Space for Input 8 Records
        this.mHeap = heap;
        this.mergedRecord = new Record[this.maxLength]; // Space for Output Records
    }
    
    /**
     * Initialize MergeInfo just for test purpose
     * 
     * @param heap  Heap to merge received runs
     * @param num   Number of Record[] to receive
     */
    MergeInfo(MinHeap heap, int num, int length) {
        this.maxLength = length;
        this.records = new Record[num][this.maxLength];
        this.mHeap = heap;
        this.mIndex = 0;
        this.mergedRecord = new Record[this.maxLength];
    }
    
    /**
     * Save received 8 Records Input
     * @param output    OutBuffer to save
     */
    public void saveBuffer(Record[] output) {
        if (this.flag == 8) {
            this.flag = 0;
        }
        int i = 0;
        while (i < output.length && output[i] != null) {
            this.records[this.flag][i] = output[i];
            i++;
        }
        this.flag++;
    }
    
    /**
     * Input each first record of input record arrays 
     * into the MinHeap data structure. These records set up
     * the starting condition of the data structure.
     */
    public void startingInsert() {
        int i = 0;
        while (i < this.flag) {
            this.mHeap.insert(this.records[i][0]);
            i++;
        }
    }
    
    /**
     * Insert the smallest record from the input record arrays
     * into MinHeap to continue merge
     */
    public void insert() {
        double temp = Double.MAX_VALUE;
        
        for (int i = 0; i < this.records.length; i++) {
            for (int j = 0; j < this.records[i].length; j++) {
                if (this.records[i][j] != null 
                    && this.records[i][j].getKey() < temp) {
                    temp = this.records[i][j].getKey();
                    this.recordsIndex = i;
                    this.recordIndex = j;
                    //this.mHeap.insert(this.records[i][j]);
                }
            }
        }
        if (this.records[recordsIndex][recordIndex] != null) {
            this.mHeap.insert(this.records[recordsIndex][recordIndex]);
            this.records[recordsIndex][recordIndex] = null;
        }
        else {
            this.stopInsert = true;
        }
    }
    
    /**
     * This method execute two consequent functions. First, it takes out
     * the smallest record from the mHeal data structure, then it save
     * the data into the output file.
     * @return  Whether there in records remain in MinHeap
     */
    public boolean merge() {
        Record record = this.mHeap.removeMin();
        if (record != null && this.mIndex < this.maxLength) {
            //System.out.println(record.getKey() + " " + this.mHeap.getSize());
            this.mergedRecord[this.mIndex] = record;
            this.insert();
            //System.out.println(record.getID() + " " + record.getKey());
            this.mIndex++;
            //System.out.println(this.mIndex);
            //System.out.println(record);
            return true;
        }
        this.mIndex = 0;
        return false;
    }
    
    public void mergeTwoFile(String input1, String input2) {
        
        Record[] record1 = new Record[512];
        Record[] record2 = new Record[512];
        
        FileParser parser1 = new FileParser(input1);
        FileParser parser2 = new FileParser(input2);
        
        int i = 0;
        
        while (parser1.remains() && i < 4) {
            record1 = parser1.readFileBlock();
            this.saveBuffer(record1);
            i++;
        }
        
        i = 0;
        
        while (parser2.remains() && i < 4) {
            record2 = parser2.readFileBlock();
            this.saveBuffer(record2);
            i++;
        }
        
        i = 0;

        String name = "run" + parser1.numberOfFiles() +".bin";
        
        this.startingInsert();
        
        while (this.merge()) {
            this.merge();
        }
        
        parser1.writeFileBlock(this.getMergedRecord());
        
        while (i < this.flag - 1 && i < 3) {
            while (this.merge()) {
                this.merge();
            }
            parser1.writeFileBlock(this.getMergedRecord(), name);
            i++;
        }
        
        while (parser1.remains() && parser2.remains()) {
            
            record1 = parser1.readFileBlock();
            this.saveBuffer(record1);
            record2 = parser2.readFileBlock();
            this.saveBuffer(record2);
            while (this.merge()) {
                this.merge();
            }
            parser1.writeFileBlock(this.getMergedRecord(), name);
            while (this.merge()) {
                this.merge();
            }
            parser1.writeFileBlock(this.getMergedRecord(), name);
        }
        /**
        while (parser1.remains()) {
            record1 = parser1.readFileBlock();
            this.saveBuffer(record1);
            record1 = parser1.readFileBlock();
            this.saveBuffer(record1);
            while (this.merge()) {
                this.merge();
            }
            parser1.writeFileBlock(this.getMergedRecord(), name);
            while (this.merge()) {
                this.merge();
            }
            parser1.writeFileBlock(this.getMergedRecord(), name);
        }
        
        while (parser2.remains()) {
            record1 = parser2.readFileBlock();
            this.saveBuffer(record1);
            record1 = parser2.readFileBlock();
            this.saveBuffer(record1);
            while (this.merge()) {
                this.merge();
            }
            parser1.writeFileBlock(this.getMergedRecord(), name);
            while (this.merge()) {
                this.merge();
            }
            parser1.writeFileBlock(this.getMergedRecord(), name);
        }
        **/
        while (!this.mHeap.isEmpty()) {
            while (this.merge()) {
                this.merge();
            }
            parser1.writeFileBlock(this.getMergedRecord(), name);
        }
    }
    
    /**
     * Get the merged output file 
     */
    public Record[] getMergedRecord() {
        return this.mergedRecord;
    }
    
    /**
     * Inform that the input record arrays space is empty.
     * Ready to get in new records.
     */
    public void resetFlag() {
        this.flag = 0;
    }
}
