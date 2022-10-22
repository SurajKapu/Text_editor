import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor extends JPanel implements ActionListener {
    private JFrame mainFrame;
    private TextArea textArea;
    private JMenuItem open, save, print, newFile;
    private JMenuItem cut, copy, paste, close;

    TextEditor(){
        initializeFrame();
        buildMenuBar();
        buildTextArea();
        addActionListners();
        mainFrame.setVisible(true);
    }

    private void buildMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        //creating menus and adding to menuBar
        JMenu fileMenu = new JMenu("File Menu");
        JMenu editMenu = new JMenu("Edit Menu");
        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        //creating fileMenu items
        open = new JMenuItem("Open File");
        save = new JMenuItem("Save File");
        print = new JMenuItem("Print File");
        newFile = new JMenuItem("New File");

        //Adding fileMenu items to fileMenu
        fileMenu.add(open);
        fileMenu.add(print);
        fileMenu.add(save);
        fileMenu.add(newFile);

        //Creating editMenu items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        close = new JMenuItem("Close");
        //Adding editMenu items to editMenu
        editMenu.add(cut);
        editMenu.add(copy);
        editMenu.add(paste);
        editMenu.add(close);

        mainFrame.setJMenuBar(menuBar);
    }

    private void addActionListners() {
        open.addActionListener(this);
        save.addActionListener(this);
        print.addActionListener(this);
        newFile.addActionListener(this);

        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        close.addActionListener(this);
    }

    private void buildTextArea() {
        textArea = new TextArea();
        textArea.setBounds(20,30,600,700);
        textArea.setBackground(Color.WHITE);
        mainFrame.add(textArea);
    }

    private void initializeFrame() {
        mainFrame = new JFrame("Editor");
        mainFrame.setSize(800,800);
        mainFrame.getContentPane().setBackground(Color.BLACK);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String source = e.getActionCommand();
        if(source.equals("Open File")) {
            openSelectedFile();
        }
        else if (source.equals("Print File")) {
//            TextArea.print();
        }
        else if (source.equals("Save File")) {
            saveFile();
        }
        else if (source.equals("New File")) {
            textArea.setText("");
        }
        else if (source.equals("Cut")) {
//            textArea.cut();
        }
        else if (source.equals("Copy")) {
//            textArea.copy();
        }
        else if (source.equals("Paste")) {
//            textArea.paste();
        }
        else if (source.equals("Close")) {
            mainFrame.setVisible(false);
        }
    }

    private void openSelectedFile() {
        //fileChooser --> (get selected file) --> pass file to fileReader
        // --> fileReader reads file data to bufferedReader --> bufferedReader reads line by line.
        JFileChooser fileChooser = new JFileChooser();
        int status = fileChooser.showOpenDialog(null);
        if(status == JFileChooser.APPROVE_OPTION) {
            String path = fileChooser.getSelectedFile().getPath();
            File selectedFile = new File(path);
            try {
                FileReader fileReader = new FileReader(selectedFile);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String newLine = "";
                StringBuilder completeFile = new StringBuilder();
                newLine = bufferedReader.readLine();
                while(newLine != null) {
                    completeFile.append(newLine).append("\n");
                    newLine = bufferedReader.readLine();
                }
                textArea.setText(completeFile.toString());
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void saveFile() {
        //1.get text from textArea
        //2.pass text to bufferedWriter
        //3.bufferedWriter passes text to file writer
        //4.file writer writes into selected file
        //fileChooser --> (get selected file)

        JFileChooser fileChooser = new JFileChooser("c:");
        int status = fileChooser.showOpenDialog(null);

        if(status == JFileChooser.APPROVE_OPTION) {
            String path = fileChooser.getSelectedFile().getPath();
            File selectedFile = new File(path);
            try {
                FileWriter fileWriter = new FileWriter(selectedFile, false);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(textArea.getText());
                bufferedWriter.flush();
                bufferedWriter.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
