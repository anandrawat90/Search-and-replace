import java.awt.event.*;
import java.awt.*;
import java.io.File;
import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.*;

class Search extends JFrame implements ActionListener {

    FileReader fr;
    FileWriter fw;
    static File file1, file2;
    JFrame q;
    JLabel label1, label2;
    JTextField t1, t2;
    JButton b1, b2;
    String fn, ln;

    public void user() {
        q = new JFrame("Find and Replace");
        q.setSize(400, 100);
        q.setVisible(true);
        q.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        q.setResizable(false);
        q.setBackground(Color.BLACK);
        q.setLocationRelativeTo(null);
        q.setLayout(new FlowLayout());

        b2 = new JButton("Change Source File");
        b1 = new JButton("Execute");
        label1 = new JLabel("Enter the word: ", SwingConstants.CENTER);
        label2 = new JLabel("Replace with: ", SwingConstants.CENTER);

        t1 = new JTextField(10);
        t2 = new JTextField(10);
        t1.addActionListener(this);
        t2.addActionListener(this);
        b1.setActionCommand("Execute");
        b1.addActionListener(this);

        b2.setActionCommand("Change");
        b2.addActionListener(this);
        label1.setBorder(BorderFactory.createLineBorder(Color.black));
        label2.setBorder(BorderFactory.createLineBorder(Color.black));

        q.setLayout(new GridLayout(3, 2));
        q.add(label1);
        q.add(t1);
        q.add(label2);
        q.add(t2);
        q.add(b1);
        q.add(b2);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "Execute") {
            fn = " " + t1.getText() + " ";
            ln = " " + t2.getText() + " ";

            try {
                task(fn, ln);
            } catch (Exception ee) {
               ee.printStackTrace();
            }
        }
        if (e.getActionCommand() == "Change") {
            JFileChooser fileopen = new JFileChooser();

            FileNameExtensionFilter filter = new FileNameExtensionFilter("text files", "txt");
            fileopen.setFileFilter(filter);

            int ret = fileopen.showDialog(null, "Open File");

            if (ret == JFileChooser.APPROVE_OPTION) {
                Search.file1 = fileopen.getSelectedFile();

            }
        }
    }

    public void task(String source, String replacement) throws Exception {

        JFileChooser fileclose = new JFileChooser();
        fileclose.showSaveDialog(null);
        file2 = fileclose.getSelectedFile();

        fr = new FileReader(file1);
        fw = new FileWriter(file2);
        BufferedReader br = new BufferedReader(fr);
        String s, b;
        while ((s = br.readLine()) != null) {

            b = s.replaceAll(source, replacement);
            fw.write(b);
        }
        br.close();
        fr.close();
        fw.close();
    }
}

public class SearchAndReplace {

    public static void main(String args[]) {
        Search s = new Search();
        JFileChooser fileopen = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter("text files", "txt");
        fileopen.setFileFilter(filter);

        int ret = fileopen.showDialog(null, "Open File");

        if (ret == JFileChooser.APPROVE_OPTION) {
            Search.file1 = fileopen.getSelectedFile();

            s.user();
        }
    }
}
