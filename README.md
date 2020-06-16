# Plagiarism-Detector Program
The program is expected to take in 2 text files, and look for similarities between the 2 of them. The project is build on the base of `https://github.com/ABusyProgrammer/PlagiarismDetector.git`

# Setup
* put all **txt** files into a single directory and copy the directory path to here

  ```java
  // the directory to where all files are stored
  public static final String FOLDER_PATH = "DIRECTORY PATH HERE";
  ```

- Edit where the result csv file will be generated

  ```java
  // defined the path to result file
  public static final String RESULT_PATH = "RESULT PATH DIRECTORY HERE\\output.csv";
  ```

## TODO ##

- Currently only check for absolute similarity
- support other file type like docx, pdf, markdown, etc.
- need to check for synonyms