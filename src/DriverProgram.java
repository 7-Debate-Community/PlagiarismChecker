/*
 * Author     - Aryan Kukreja | Student #: 100651838
 * Date Made  - Saturday, November 23, 2019 @ 11:08 AM
 * Course     - Design and Analysis of Algorithms | SOFE 3770
 *
 * Purpose:
 *      - Major Project | Plagiarism Detector (Similarity Checker)
 *
 * About:
 *      - This is the driver code for the similarity checker program
 *
 * Input      - 2 text files
 * Output     - Similarity Report
 *
 * Extra Info - This is only the tester program
 */

// Import the following packages
import SimilarityChecker.Jaccard;

import java.io.*;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 * DriverProgram Class
 * - Used to get data from 2 entire files and save it as a String
 *
 * @version 1.0.0
 */
class DriverProgram {
    /**
     * driverProgram() Static Method
     * - The method that reads in 2 files and passes it to the wholeFileChecker as Strings.
     *
     * @param src  The origin file
     * @param plag The plagiarized file
     * @return The plagiarism value computed for the 2 files
     * @throws Exception For file-processing related errors
     */
    public static double getSimilarity(File checkFile, File originFile) throws Exception {
        /*
        // Declare a bufferReader object fr the source and plagiarized file, and read them in
        BufferedReader srcRead = new BufferedReader(new FileReader(src));
        BufferedReader plagRead = new BufferedReader(new FileReader(plag));

        // Declare a string builder to read the line from the file into that string
        // Also declare a temporary string used to read from the files
        StringBuilder source = new StringBuilder(), plagiarized = new StringBuilder();
        String temp;

        // Read every line from the source file and add it to the Stringbuilder
        while ((temp = srcRead.readLine()) != null) {
            source.append(temp);
        }
        // Do the same for the plagiarized file
        while ((temp = plagRead.readLine()) != null) {
            plagiarized.append(temp);
        }

         */

        // Return the plagiarism level for the entire file system
        return checkWholeFile(extractPdfText(checkFile), extractPdfText(originFile));
    }

    /**
     * checkWholeFile() method:
     * - Used to check plagiarism between 2 entire files.
     *
     * @param origin The original file
     * @param copied The file to check against the original
     * @return The plagiarism value
     */
    private static double checkWholeFile(String origin, String copied) {
        // Split the string
        String[] copiedArray = copied.split("\\. ");

        // Declare a variable to track the total number of variables.
        // Get the number of words in the copied string.
        int totalPlagiarized = 0;
        int totalWords = copied.split("\\s+").length;

        // Declare an instance of the Jaccard algorithm
        Jaccard useJaccard = new Jaccard(origin);

        // For every string in the list of copied sentences
        for (String sentence : copiedArray) {
            // Sum up its plagiarized count.
            totalPlagiarized += useJaccard.jaccardImplementation(sentence);
        }

        double plagiarizedValue = (double) totalPlagiarized / totalWords;

        if (plagiarizedValue >= 1) {
            // Return their percentage of plagiarism.
            return 1;
        } else {
            return plagiarizedValue;
        }
    }

    private static String extractPdfText(File file) throws Exception {
        PDDocument document = PDDocument.load(file);
        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        pdfTextStripper.setStartPage(1);
        pdfTextStripper.setEndPage(5);
        String text  = pdfTextStripper.getText(document);
        document.close();
        return text;
    }
}
