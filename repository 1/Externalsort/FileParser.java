import java.io.*;
//On my honor:
//
//- I have not used source code obtained from another student,
//or any other unauthorized source, either modified or
//unmodified.
//
//- All source code and documentation used in my program is
//either my original work, or was derived by me from the
//source code published in the textbook for this course.
//
//- I have not discussed coding details about this project with
//anyone other than my partner (in the case of a joint
//submission), instructor, ACM/UPE tutors or the TAs assigned
//to this course. I understand that I may discuss the concepts
//of this program with other students, and that another student
//may help me debug my program so long as neither of us writes
//anything during the discussion or modifies any computer file
//during the discussion. I have violated neither the spirit nor
//letter of this restriction.
/**
 * Receive binary file
 * @author USER
 * @version 2020-07-01
 */
public class FileParser {
    
    private Record[] recordList;
    
    private Record record;
    
    private static int num = 0;
    
    private DataInputStream inputFile;
//    private DataOutputStream outputFile;
    private DataOutputStream outputFile;
    
    private int len;
    private int counter = 0;
    
    private boolean remain = true;
    
    FileParser (String input) {
        recordList = new Record[512];
        try {
            inputFile = new DataInputStream(new FileInputStream(input));
//          outputFile = new DataOutputStream(new FileOutputStream("expectedOutput.bin"));
        }
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /**
     * Read binary file by one block of Record
     * @return  Block of Record read
     */
    public Record[] readFileBlock() {
        byte[] inputBuffer = new byte[8192];
        byte[] byteArray = new byte[16];
        
        try {
            while ((len = inputFile.read(inputBuffer)) > 0) {
                for (int i = 0; i < len; i=i+16) { 
                    
                    for (int j = 0; j<16; j++) {
                        byteArray[j] = inputBuffer[i+j]; 
                    }                                       
                    
                    record = new Record();
                    record.setTotal(byteArray);
                    recordList[counter] = record;
                    
                    counter++;
                }
                
                if (counter >= 512) {
                    counter = 0;
                    break;
                }
            }
            if (inputFile.available() <= 0) {
                this.remain = false;
            }
            
            if (len < 0) {
                inputFile.close();
            }
            
        }catch (FileNotFoundException e) {
            System.out.println("Cannot open the file ");
            System.out.println(e.getMessage());
        }catch(IOException e) {
            System.out.println(e);
        }
        return recordList;
    }
    
    public boolean remains() {
        return this.remain;
    }
    
    public DataOutputStream writeFileBlock(Record[] input) {
        String name = "run" + num + ".bin";
        //System.out.println(name);
        num++;
        try {
            
            outputFile = new DataOutputStream(new FileOutputStream(name, true));
            int i = 0;
            
            while (i < input.length && input[i] != null) {
                outputFile.writeLong(input[i].getID());
                outputFile.writeDouble(input[i].getKey());
                i++;
            }
            /**
            for (int i = 0; i < input.length; i++) {
//                System.out.print("Record "+i+" : "+input[i].getID() + " ");
//                System.out.print(input[i].getKey() + "\n");
            }
            **/
            outputFile.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return outputFile;
    }
    
    public DataOutputStream writeFileBlock(Record[] input, String name) {
        try {
            
            outputFile = new DataOutputStream(new FileOutputStream(name, true));
            int i = 0;
            
            while (i < input.length && input[i] != null) {
                outputFile.writeLong(input[i].getID());
                outputFile.writeDouble(input[i].getKey());
                i++;
            }
            /**
            for (int i = 0; i < input.length; i++) {
//                System.out.print("Record "+i+" : "+input[i].getID() + " ");
//                System.out.print(input[i].getKey() + "\n");
            }
            **/
            outputFile.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return outputFile;
    }
    
    public int numberOfFiles() {
        return this.num;
    }
    
    public void subtract() {
        this.num = this.num - 1;
    }
}

