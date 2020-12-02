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
 * Test methods of MergeInfo class
 * @author USER
 * @version 2020-06-27
 */
public class MergeInfoTest extends student.TestCase {

    private MergeInfo mInfo;
    private MinHeap heap;
    private Record[] inputRecord1 = new Record[12];
    private Record[] inputRecord2 = new Record[12];
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
    private Record record13 = new Record();
    private Record record14 = new Record();
    private Record record15 = new Record();
    private Record record16 = new Record();
    private Record record17 = new Record();
    private Record record18 = new Record();
    private Record record19 = new Record();
    private Record record20 = new Record();
    private Record record21 = new Record();
    private Record record22 = new Record();
    private Record record23 = new Record();
    private Record record24 = new Record();
    
    public void setUp() {
        heap = new MinHeap(4096);
        mInfo = new MergeInfo(heap, 2, 512);
        byte[] byte1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 
            10, 11, 12, 13, 14, 15, 16}; //1st
        byte[] byte7 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 
            11, 11, 12, 13, 14, 15, 16}; //7th
        byte[] byte2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 
            10, 12, 12, 13, 14, 15, 16}; //2nd
        byte[] byte8 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 
            11, 12, 12, 13, 14, 15, 16}; //8th
        byte[] byte3 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 
            10, 13, 12, 13, 14, 15, 16}; //3rd
        byte[] byte9 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 
            11, 13, 12, 13, 14, 15, 16}; //9th
        byte[] byte4 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 
            10, 14, 12, 13, 14, 15, 16}; //4th
        byte[] byte10 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 
            11, 14, 12, 13, 14, 15, 16}; //10th
        byte[] byte5 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 
            10, 15, 12, 13, 14, 15, 16}; //5th
        byte[] byte11 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 
            11, 15, 12, 13, 14, 15, 16}; //11th
        byte[] byte6 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 
            10, 16, 12, 13, 14, 15, 16}; //6th
        byte[] byte12 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 
            11, 16, 12, 13, 14, 15, 16}; //12th
        
        byte[] byte13 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 
            10, 11, 12, 13, 14, 17, 16}; //1st
        byte[] byte19 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 
            11, 11, 12, 13, 14, 17, 16}; //7th
        byte[] byte14 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 
            10, 12, 12, 13, 14, 17, 16}; //2nd
        byte[] byte20 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 
            11, 12, 12, 13, 14, 17, 16}; //8th
        byte[] byte15 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 
            10, 13, 12, 13, 14, 17, 16}; //3rd
        byte[] byte21 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 
            11, 13, 12, 13, 14, 17, 16}; //9th
        byte[] byte16 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 
            10, 14, 12, 13, 14, 17, 16}; //4th
        byte[] byte22 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 
            11, 14, 12, 13, 14, 17, 16}; //10th
        byte[] byte17 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 
            10, 15, 12, 13, 14, 17, 16}; //5th
        byte[] byte23 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 
            11, 15, 12, 13, 14, 17, 16}; //11th
        byte[] byte18 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 
            10, 16, 12, 13, 14, 17, 16}; //6th
        byte[] byte24 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 
            11, 16, 12, 13, 14, 17, 16}; //12th
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

        record13.setTotal(byte13);
        record14.setTotal(byte14);
        record15.setTotal(byte15);
        record16.setTotal(byte16);
        record17.setTotal(byte17);
        record18.setTotal(byte18);
        record19.setTotal(byte19);
        record20.setTotal(byte20);
        record21.setTotal(byte21);
        record22.setTotal(byte22);
        record23.setTotal(byte23);
        record24.setTotal(byte24);
        
        inputRecord1[0] = record1;
        inputRecord1[1] = record2;
        inputRecord1[2] = record3;
        inputRecord1[3] = record4;
        inputRecord1[4] = record5;
        inputRecord1[5] = record6;
        inputRecord1[6] = record7;
        inputRecord1[7] = record8;
        inputRecord1[8] = record9;
        inputRecord1[9] = record10;
        inputRecord1[10] = record11;
        inputRecord1[11] = record12;
        
        inputRecord2[0] = record13;
        inputRecord2[1] = record14;
        inputRecord2[2] = record15;
        inputRecord2[3] = record16;
        inputRecord2[4] = record17;
        inputRecord2[5] = record18;
        inputRecord2[6] = record19;
        inputRecord2[7] = record20;
        inputRecord2[8] = record21;
        inputRecord2[9] = record22;
        inputRecord2[10] = record23;
        inputRecord2[11] = record24;
        
        mInfo.saveBuffer(inputRecord1);
        mInfo.saveBuffer(inputRecord2);
        mInfo.startingInsert();
    }
    
    public void testStartingInsert() {
        assertEquals(record1, heap.indexRecord(0));
        assertEquals(record13, heap.indexRecord(1));
        assertEquals(2, heap.getSize());
    }
    
    public void testMerge() {
        while(mInfo.merge()) {
            mInfo.merge();
        }
        //Record[] record = mInfo.getMergedRecord();
        //assertEquals(record1, record[0]);
        //assertEquals(record13, record[1]);
        //assertEquals(0, heap.getSize());
        //assertNull(heap.indexRecord(0));
    }
    
    public void testMergeTwoFile() {
        FileParser parser = new FileParser("sampleInput16.bin");
        Externalsort external = new Externalsort();
        MinHeap heap = new MinHeap(4096);
        MergeInfo mInfo = new MergeInfo(heap);
        int i = 0;
        Record[] record1 = new Record[512];
        Record[] record2 = new Record[512];
        record1 = parser.readFileBlock();
        while (external.setUp(record1)) {
            record1 = parser.readFileBlock();
            System.out.println(i);
            i++;
        }
        while (parser.remains()) {
            external.setUpInput(record1);
            record1 = parser.readFileBlock();
            external.sort();
            record2 = external.getOutBuffer();
            parser.writeFileBlock(record2);
        }
        while (!external.getHeap().isEmpty()) {
            //System.out.println(external.getHeap().getSize());
            external.sort();
            record2 = external.getOutBuffer();
            parser.writeFileBlock(record2);
        }
        mInfo.mergeTwoFile("run0.bin", "run1.bin");
        heap = new MinHeap(4096);
        mInfo = new MergeInfo(heap);
        mInfo.mergeTwoFile("run2.bin", "run3.bin");
        heap = new MinHeap(4096);
        mInfo = new MergeInfo(heap);
        mInfo.mergeTwoFile("run4.bin", "run5.bin");
        heap = new MinHeap(4096);
        mInfo = new MergeInfo(heap);
        mInfo.mergeTwoFile("run6.bin", "run7.bin");
        heap = new MinHeap(4096);
        mInfo = new MergeInfo(heap);
        mInfo.mergeTwoFile("run8.bin", "run9.bin");
        heap = new MinHeap(4096);
        mInfo = new MergeInfo(heap);
        mInfo.mergeTwoFile("run10.bin", "run11.bin");
        heap = new MinHeap(4096);
        mInfo = new MergeInfo(heap);
        mInfo.mergeTwoFile("run12.bin", "run13.bin");
        heap = new MinHeap(4096);
        mInfo = new MergeInfo(heap);
        mInfo.mergeTwoFile("run14.bin", "run15.bin");
        heap = new MinHeap(4096);
        mInfo = new MergeInfo(heap);
        mInfo.mergeTwoFile("run16.bin", "run17.bin");
        heap = new MinHeap(4096);
        mInfo = new MergeInfo(heap);
        mInfo.mergeTwoFile("run18.bin", "run19.bin");
        heap = new MinHeap(4096);
        mInfo = new MergeInfo(heap);
        mInfo.mergeTwoFile("run20.bin", "run21.bin");
        heap = new MinHeap(4096);
        mInfo = new MergeInfo(heap);
        mInfo.mergeTwoFile("run22.bin", "run23.bin");
        heap = new MinHeap(4096);
        mInfo = new MergeInfo(heap);
        mInfo.mergeTwoFile("run24.bin", "run25.bin");
        heap = new MinHeap(4096);
        mInfo = new MergeInfo(heap);
        mInfo.mergeTwoFile("run26.bin", "run27.bin");
        heap = new MinHeap(4096);
        mInfo = new MergeInfo(heap);
        mInfo.mergeTwoFile("run28.bin", "run29.bin");
    }
}
