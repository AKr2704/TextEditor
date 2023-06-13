import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    JFrame frame;

    JMenuBar menuBar;

    JMenu file, edit;

    JMenuItem newFile, openFile, saveFile;

    JMenuItem cut, copy, paste, selectAll, close;

    JTextArea textArea;
    TextEditor(){
        //Initializing Frame
        frame = new JFrame();

        //Initializing Menu Bar
        menuBar = new JMenuBar();

        //Initializing text area;
        textArea = new JTextArea();

        //Initializing Menus
        file = new JMenu("File");
        edit = new JMenu("Edit");

        //Initializing file menu items
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open");
        saveFile = new JMenuItem("Save");

        //Add action listeners to file menu items
        newFile.addActionListener(this); //this keyword is referring to the object of the text editor class
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        // Add menu items to the File Menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //Inititalize edit menu items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        //Adding action listener to edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);


        //Adding to the Edit Menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);


        //Add menus to Menu Bar
        menuBar.add(file);
        menuBar.add(edit);

        frame.setJMenuBar(menuBar); //there is a particularly dedicated function specially for menuBar, hence we
        //Create Content Pane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setLayout(new BorderLayout(0, 0)); //hgap = horizontal gap between borders, vertical gap
        //Add text area to panel
        panel.add(textArea, BorderLayout.CENTER);
        //Create a Scroll Pane
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //Add Scroll Pane to Panel
        panel.add(scrollPane);
        //Add panel to frame
        frame.add(panel);
        frame.setBounds(0, 0, 720, 540);  // 0,0 tells where the windows will appear on screen
        frame.setTitle("Text Editor ~ by Adarsh");
        frame.setVisible(true);
        frame.setLayout(null);


    }
    @Override
    public void actionPerformed(ActionEvent actionevent){
        if(actionevent.getSource()==cut) {
            //Perform cut operation
            textArea.cut();
        }
        if(actionevent.getSource()==copy) {
            //Perform copy operation
            textArea.copy();
        }
        if(actionevent.getSource()==paste) {
            //Perform paste operation
            textArea.paste();
        }
        if(actionevent.getSource()==selectAll) {
            //Perform selectAll operation
            textArea.selectAll();
        }
        if(actionevent.getSource()==close){
            //Perform close operation
            System.exit(0);
        }

        if(actionevent.getSource()==openFile) {
            //Open a file chooser
            JFileChooser fileChooser = new JFileChooser("C:"); //this will always open in C drive
            int chooseOption = fileChooser.showOpenDialog(null);
            // if we have clicked on Open button
            if(chooseOption==JFileChooser.APPROVE_OPTION) {
                //Getting selected file
                File file = fileChooser.getSelectedFile();
                //Get the path of selected file
                String filePath = file.getPath();
                try{
                    //Initialize file reader
                    FileReader fileReader = new FileReader(filePath);
                    //Intialzie Buffered Reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "", output = "";
                    //Read contents of file line by line
                    while((intermediate= bufferedReader.readLine())!=null) {
                        output += intermediate+"\n"; //Concatenating the current line to the complete text, reading a line => pasting it to output
                    }
                    //Set the output string to text area
                    textArea.setText(output);
                }

                catch(FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }

                catch(IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
        if(actionevent.getSource()==saveFile) {
            //Initialize File Picker
            JFileChooser fileChooser = new JFileChooser("C:");
            //Get choose option from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);
            //Check if we clicked on save Button
            if(chooseOption==JFileChooser.APPROVE_OPTION) {
                //Create a new file with chosen directory path abd file name
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try {
                    //Initialize file writer
                    FileWriter fileWriter = new FileWriter(file);
                    //Initialize Buffered Writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    //Write contents of text area to file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch(IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }

        if(actionevent.getSource()==newFile) {
            TextEditor newtextEditor = new TextEditor();
        }
    }
    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
    }
}