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

import java.io.*;
/**
 * Test methods of FileParser
 * @author USER
 * @version 2020-07-01
 */
public class FileParserTest extends student.TestCase {
    
    private FileParser parser;
    private Externalsort external;
    
    
    public void setUp() {
        parser = new FileParser("sampleInput16.bin");
        external = new Externalsort();
    }
    
    public void testReadFileBlock() {
        Record[] record = new Record[512];
        
        record = parser.readFileBlock();
        System.out.println(record[511].getKey());
        record = parser.readFileBlock();
        System.out.println(record[511].getKey());
    }
    
    public void testWriteFileBlock1() {
        Record[] record = new Record[512];
        
        record = parser.readFileBlock();
        //System.out.println(record.length);
        external.setUpHeap(record);
        //MinHeap heap = external.getHeap();
        //System.out.println(heap.getSize());
        external.sort();
        record = external.getOutBuffer();
        for (int i = 0; i < record.length - 1; i++) {
            assertTrue(record[i].getKey() < record[i + 1].getKey());
        }
        //parser.writeFileBlock(record);
        record = parser.readFileBlock();
        parser = new FileParser("expectedOutput.bin");
        //record = parser.readFileBlock();
        //System.out.println(record[511].getKey());
    }
    
    public void testWriteFileBlockCont() {
        Record[] record1 = new Record[512];
        Record[] record2 = new Record[512];
        
        record1 = parser.readFileBlock();
        System.out.println(record1[0].getKey());
        //parser.writeFileBlock(record1);
        
        record2 = parser.readFileBlock();
        System.out.println(record2[0].getKey());
        //parser.writeFileBlock(record2);

        parser = new FileParser("expectedOutput.bin");
        
        record1 = parser.readFileBlock();
        System.out.println(record1[0].getKey());
        
        record2 = parser.readFileBlock();
        System.out.println(record2[0].getKey());
    }
    
    public void testWriteFileBlock2() {
        Record[] record1 = new Record[512];
        Record[] record2 = new Record[512];
        Record[] record3 = new Record[512];
        MinHeap heap = new MinHeap(4096);
        MergeInfo mInfo = new MergeInfo(heap, 2, 512);
        
        record1 = parser.readFileBlock();
        external.setUpHeap(record1);
        
        record1 = parser.readFileBlock();
        external.setUpHeap(record1);
        
        external.sort();
        record3 = external.getOutBuffer();
        System.out.println(record3[0].getKey());
        mInfo.saveBuffer(record3);

        external.sort();
        record2 = external.getOutBuffer();
        System.out.println(record2[0].getKey());
        mInfo.saveBuffer(record2);
        
        mInfo.startingInsert();
        
        while (mInfo.merge()) {
            mInfo.merge();
        }
        Record[] record = mInfo.getMergedRecord();
        for (int i = 0; i < record.length - 1; i++) {
            System.out.println("Record(key) "+i+" : "+record[i].getKey() + " " + (i +1) + " : "+ record[i+1].getKey());
            assertTrue(record[i].getKey() <= record[i + 1].getKey());
        }

        while (mInfo.merge()) {
            mInfo.merge();
        }
        record = mInfo.getMergedRecord();
        for (int i = 0; i < record.length - 1; i++) {
            System.out.println("Record(key) "+i+" : "+record[i].getKey() + " " + (i +1) + " : "+ record[i+1].getKey());
            assertTrue(record[i].getKey() <= record[i + 1].getKey());
        }
        //parser.writeFileBlock(record);
    }
    
    public void testWriteFileBlock4() {
        Record[] record1 = new Record[512];
        Record[] record2 = new Record[512];
        MinHeap heap = new MinHeap(4096);
        MergeInfo mInfo = new MergeInfo(heap, 4, 512);
        
        for (int i = 0; i < 4; i++) {
            record1 = parser.readFileBlock();
            external.setUpHeap(record1);
        }
        
        for (int i = 0; i < 4; i++) {
            external.sort();
            record2 = external.getOutBuffer();
            mInfo.saveBuffer(record2);
        }
        
        mInfo.startingInsert();
        
            while (mInfo.merge()) {
                mInfo.merge();
            }
        
            Record[] record = mInfo.getMergedRecord();
            for (int j = 0; j < record.length - 1; j++) {
                //System.out.println("Record1(key) "+ j+" : "+record[j].getKey() + " " + (j +1) + " : "+ record[j+1].getKey());
                assertTrue(record[j].getKey() <= record[j + 1].getKey());
            }
        
            while (mInfo.merge()) {
                mInfo.merge();
            }
        
            record = mInfo.getMergedRecord();
            for (int j = 0; j < record.length - 1; j++) {
                //System.out.println("Record2(key) "+ j+" : "+record[j].getKey() + " " + (j +1) + " : "+ record[j+1].getKey());
                assertTrue(record[j].getKey() <= record[j + 1].getKey());
            }
        
            while (mInfo.merge()) {
                mInfo.merge();
            }
        
            record = mInfo.getMergedRecord();
            for (int j = 0; j < record.length - 1; j++) {
                //System.out.println("Record3(key) "+ j+" : "+record[j].getKey() + " " + (j +1) + " : "+ record[j+1].getKey());
                assertTrue(record[j].getKey() <= record[j + 1].getKey());
            }
        
            while (mInfo.merge()) {
                mInfo.merge();
            }
        
            record = mInfo.getMergedRecord();
            for (int j = 0; j < record.length - 1; j++) {
                //System.out.println("Record4(key) "+ j+" : "+record[j].getKey() + " " + (j +1) + " : "+ record[j+1].getKey());
                assertTrue(record[j].getKey() <= record[j + 1].getKey());
            }
    }
    
    public void testWriteFileBlock8() {
        Record[] record1 = new Record[512];
        Record[] record2 = new Record[512];
        MinHeap heap = new MinHeap(4096);
        MergeInfo mInfo = new MergeInfo(heap);
        
        for (int i = 0; i < 8; i++) {
            record1 = parser.readFileBlock();
            external.setUpHeap(record1);
        }
        
        for (int i = 0; i < 8; i++) {
            external.sort();
            record2 = external.getOutBuffer();
            mInfo.saveBuffer(record2);
        }
        
        mInfo.startingInsert();
        
        for (int i = 0; i < 8; i++) {
            while (mInfo.merge()) {
                mInfo.merge();
            }
        
            Record[] record = mInfo.getMergedRecord();
            for (int j = 0; j < record.length - 1; j++) {
                //System.out.println("Record1(key) "+ j+" : "+record[j].getKey() + " " + (j +1) + " : "+ record[j+1].getKey());
                assertTrue(record[j].getKey() <= record[j + 1].getKey());
            }
        }
    }
    
    public void testWriteFileBlock16() {
        Record[] record1 = new Record[512];
        Record[] record2 = new Record[512];
        MinHeap heap = new MinHeap(4096);
        MergeInfo mInfo = new MergeInfo(heap, 16, 512);
        
        record1 = parser.readFileBlock();
        while (external.setUp(record1)) {
            //System.out.println(record1[0].getKey());
            record1 = parser.readFileBlock();
            //System.out.println(external.getHeap().getSize());
        }
        record1 = parser.readFileBlock();
        external.setUpInput(record1);
        external.sort();
        record2 = external.getOutBuffer();
        mInfo.saveBuffer(record2);
        System.out.println("Merge : " + record2[0].getKey());
        System.out.println("Merge : " + record2[511].getKey());
        while (parser.remains()) {
            record1 = parser.readFileBlock();
            external.setUpInput(record1);
            //System.out.println(record1[0].getKey());
            external.sort();
            //System.out.println("Size : " + external.getHeap().getSize());
            record2 = external.getOutBuffer();
            //System.out.println("Record2 : "+ record2[0].getKey());
            //System.out.println("Size : " + external.getHeap().getSize());
            mInfo.saveBuffer(record2);
            System.out.println("Merge : " + record2[0].getKey());
            System.out.println("Merge : " + record2[511].getKey());
        }
        external.sort();
        record2 = external.getOutBuffer();
        mInfo.saveBuffer(record2);
        System.out.println("Merge : " + record2[0].getKey());
        System.out.println("Merge : " + record2[511].getKey());
        external.sort();
        external.sort();
        external.sort();
        external.sort();
        external.sort();
        external.sort();
        external.sort();
        System.out.println(external.getHeap().getSize());
        external.sort();
        while (!external.getHeap().isEmpty()) {
            external.sort();
            //System.out.println("Size : " + external.getHeap().getSize());
            record2 = external.getOutBuffer();
            mInfo.saveBuffer(record2);
            //System.out.println("Record2 : "+ record2[0].getKey());
            //System.out.println(k);
            //System.out.println(external.getHeap().getSize());
        }
        mInfo.startingInsert();
        
        for (int i = 0; i < 16; i++) {
            while (mInfo.merge()) {
                mInfo.merge();
            }
        
            Record[] record = mInfo.getMergedRecord();
            //System.out.println(record[90].getKey());
            for (int j = 0; j < record.length - 1; j++) {
                System.out.println("Record(key) " + i + " "+ j+" : "+record[j].getKey() + " " + (j +1) + " : "+ record[j+1].getKey());
                assertTrue(record[j].getKey() <= record[j + 1].getKey());
            }
        }
    }
    
    public void testRemain() {

        Record[] record = new Record[512];
        try {
            PrintStream writer = new PrintStream("result.txt");
            System.setOut(writer);
            while (parser.remains()) {
                record = parser.readFileBlock();
                for (int i = 0; i < 512; i++) {
                    System.out.println(record[i]);
                }
            }
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void testRemain1() {
        
        FileParser parser = new FileParser("run24.bin");

        Record[] record = new Record[512];
        try {
            PrintStream writer = new PrintStream("temp24.txt");
            System.setOut(writer);
            while (parser.remains()) {
                record = parser.readFileBlock();
                for (int i = 0; i < 512; i++) {
                    System.out.println(record[i].getID() + " " + record[i].getKey());
                }
            }
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
