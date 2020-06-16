/*
 * Author     - Aryan Kukreja | Student #: 100651838
 * Date Made  - Tuesday, November 26, 2019 @ 8:07 PM
 * Course     - Design and Analysis of Algorithms | SOFE 3770
 *
 * Purpose:
 *      - Major Project | Plagiarism-Detection Algorithm
 *
 * About:
 *      - This program is a tester class for the plagiarism-checker program. It checks 100 sample files and writes the
 *        output to a CSV file.
 *
 * Input      - Read all files from a project
 * Output     - Write plagiarism scores for all programs to the CSV file
 *
 * Extra Info - This class has a main() method.
 */

// Import the packages for reading and writing files to.
import java.io.File;
import java.io.PrintWriter;


public class Test {
    // the directory to where all files are stored
    public static final String FOLDER_PATH = "C:\\Users\\steve\\Desktop\\file";
    // defined the path to result file
    public static final String RESULT_PATH = "C:\\Users\\steve\\Desktop\\output.csv";
    // threshold to trigger warning
    public static final double THRESHOLD = 0.4;

    /**
     * main() Implementation Method:
     * - Used to implement the method
     *
     * @throws Exception Exception during read/writing operations from the program
     */
    public static void main(String[] args) throws Exception {
        // Declare n array to store all the files in the folders
        File[] fileList = new File(FOLDER_PATH).listFiles();
        assert fileList != null;
        // Declare a variable to write to the output CSV file
        PrintWriter output = new PrintWriter(RESULT_PATH);
        // add csv header
        output.print("file name" + ",");
        for (File file : fileList) {
            output.print(file.getName() + ",");
        }
        output.print("\n");
        int currentIndex = 0; // record current file index, to print progress bar
        StringBuilder feedback = new StringBuilder(); // store all feedback
        for (File checkFile : fileList) {
            // print current progress
            progressPercentage(++currentIndex, fileList.length);
            // If the duplicate is a file
            if (checkFile.isFile()) {
                // Output the file-name to the csv file
                output.print(checkFile.getName() + ",");
                // For every file in the origin-list
                for (File origin : fileList) {
                    // skip checking current file
                    if (origin.equals(checkFile)) {
                        output.print("N/A" + ",");
                        continue;
                    }
                    // If it is a file, then calculate the duplicate's plagiarism against this one and write it to the CSV
                    if (origin.isFile()) {
                        // get similarity and keep 3 digits
                        double value = (double) Math.round(DriverProgram.getSimilarity(origin, checkFile) * 1000) / 1000;
                        // if exceeds threshold, update feedback
                        if (value >= THRESHOLD) {
                            feedback.append("===============\n")
                                    .append("Suspect ")
                                    .append(checkFile.getName())
                                    .append(" plagiarize ")
                                    .append(origin.getName())
                                    .append("\nSimilarity: ")
                                    .append(value)
                                    .append("\n===============\n");
                        }
                        output.print(value + ",");
                    }
                }
                // Move to the next line.
                output.print("\n");
            }
        }
        // Close the output file
        output.close();
        // print feedback
        System.out.println(feedback.toString());
    }

    /**
     * print out progress bar.
     * This function is COMPLETELY CITED FROM
     * https://stackoverflow.com/questions/852665/command-line-progress-bar-in-java
     * @param current current progress
     * @param total total amount.
     */
    public static void progressPercentage(int current, int total) {
        if (current > total) {
            throw new IllegalArgumentException();
        }
        int maxBareSize = 10; // 10unit for 100%
        int remainProcent = ((100 * current) / total) / maxBareSize;
        char defaultChar = '-';
        String icon = "*";
        String bare = new String(new char[maxBareSize]).replace('\0', defaultChar) + "]";
        String bareRemain = bare.substring(remainProcent);
        String bareDone = "["
                + icon.repeat(Math.max(0, remainProcent));
        System.out.print("\r" + bareDone + bareRemain + " " + remainProcent * 10 + "%");
        if (current == total) {
            System.out.print("\n");
        }
    }
}
