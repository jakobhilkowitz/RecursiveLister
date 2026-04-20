import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * GUI application to list files and directories recursively.
 */
public class RecursiveListerGUI extends JFrame {

    private JLabel titleLabel;
    private JTextArea outputArea;
    private JScrollPane scrollPane;
    private JButton startButton;
    private JButton quitButton;
    private JPanel buttonPanel;

    /**
     * Constructor to set up the GUI components and event listeners.
     */
    public RecursiveListerGUI() {
        setTitle("Recursive File Lister");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        titleLabel = new JLabel("Recursive File Lister", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        outputArea = new JTextArea();
        outputArea.setEditable(false);

        scrollPane = new JScrollPane(outputArea);

        startButton = new JButton("Start");
        quitButton = new JButton("Quit");

        buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(quitButton);

        setLayout(new BorderLayout());
        add(titleLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        startButton.addActionListener(e -> chooseDirectoryAndListFiles());
        quitButton.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    /**
     * Opens a directory chooser dialog, and if a directory is selected, lists all files and directories recursively in
     * the output area.
     */
    private void chooseDirectoryAndListFiles() {
        String projectPath = System.getProperty("user.dir");

        JFileChooser chooser = new JFileChooser(new File(projectPath));
        chooser.setDialogTitle("Select a Directory");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        int result = chooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedDirectory = chooser.getSelectedFile();

            StringBuilder output = new StringBuilder();
            output.append("Listing files for:\n");
            output.append(selectedDirectory.getAbsolutePath()).append("\n\n");

            FileLister.listFiles(selectedDirectory, output);

            outputArea.setText(output.toString());
        }
    }
}