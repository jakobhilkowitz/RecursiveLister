import java.io.File;

/**
 * Utility class to list files and directories recursively.
 */
public class FileLister {

    /**
     * Recursively lists all files and directories starting from the given file/directory and appends their paths to the output.
     * @param file The starting file or directory to list.
     * @param output The StringBuilder to which the file paths will be appended.
     */
    public static void listFiles(File file, StringBuilder output) {
        if (file == null || !file.exists()) {
            return;
        }

        // If it's a file, append its path to the output and return
        if (file.isFile()) {
            output.append(file.getAbsolutePath()).append("\n");
            return;
        }

        output.append("[DIR] ").append(file.getAbsolutePath()).append("\n");

        File[] files = file.listFiles();
        if (files != null) {
            for (File currentFile : files) {
                listFiles(currentFile, output);
            }
        }
    }
}