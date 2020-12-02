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
import java.util.Random;

/**
 * Generate binary file with received records.
 * @author USER
 * @version 2020-06-28
 */
public class GenFile {
    
    static final int NumRecs = 512;

    /** Initialize the random variable */
    static private Random value = new Random(); // Hold the Random class object


    static long randLong() {
        return value.nextLong();
    }

    static double randDouble() {
        return value.nextDouble();
    }

    public static void main(String[] args) {
        long val;
        double val2;
        if (args.length != 2){
            System.out.println("Usage: GenFile <filename> <size>");
            System.out.println("  Options");
            System.out.println("    <filename> output filename");
            System.out.println("    <size> number of 8192 byte blocks to generate.");
            System.exit(1);
        }else{
            System.out.println("Generating " + args[1] + " blocks...storing in " + args[0]);
        }

        int filesize = Integer.parseInt(args[1]); // Size of file in blocks
        String filename = args[0];

        try {
            DataOutputStream file = new DataOutputStream(
                    new BufferedOutputStream(new FileOutputStream(filename)));

            for (int i = 0; i < filesize; i++)
                for (int j = 0; j < NumRecs; j++) {
                    val = (long) (randLong());
                    file.writeLong(val);
                    val2 = (double) (randDouble());
                    file.writeDouble(val2);
                }

            file.flush();
            file.close();
        }
        catch(Exception e){
            System.out.println("An error occurred, which generated this not-terribly helpful error message.");
        }
    }
    
}
