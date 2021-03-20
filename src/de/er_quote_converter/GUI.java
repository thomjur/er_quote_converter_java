package de.er_quote_converter;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.text.*;
import java.awt.event.*;
import java.io.IOException;

public class GUI {
    
    GUI() {
        JFrame main_frame = new JFrame("ER Quote Converter 1.0");
        main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main_frame.setSize(800, 500);
        main_frame.setLocation(250, 250);
        
        
        // Labels
        JLabel title = new JLabel("<html><i>ER Quote Converter v1.0</i></html>");
        title.setBorder(new EmptyBorder(10,50,10,50));
        
        // TextFields
        
        JTextArea textArea = new JTextArea(10,25);
        JScrollPane scroller = new JScrollPane(textArea);
        scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setBorder(new EmptyBorder(10,50,10,50));
        
        // Main Buttons
        
            // Load Text Button
        JButton loadButton = new JButton("Load Text");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String text = FileAccess.readTextFromFile("file.txt");
                    System.out.println(text);
                    textArea.setText(text);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        
            // Save Text Button
        JButton b1 = new JButton("Save");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = "file_new.txt";
                try {
                    FileAccess.writeTextToFile(textArea.getText(), fileName);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        
            // Quit Button
        
        JButton b2 = new JButton("Abort");
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int answer = JOptionPane.showConfirmDialog(main_frame, "Do you really want to quit?");
                if (answer == 0) {
                    System.exit(0);  
                }
            }
        });
        
        // Panels
        
        // separatorPanel
        
        JPanel sepPanel = new JPanel();
        sepPanel.setBorder(new EmptyBorder(10,50,10,50));
        JButton sepButton = new JButton("Select");
        JTextField sepInput = new JTextField(5);
        sepInput.setDocument(new JTextFieldLimit(1));
        JLabel sepLabel = new JLabel("Separator: ");
        sepPanel.add(sepLabel);
        sepPanel.add(sepInput);
        sepPanel.add(sepButton);
        
        
        // buttonPanel
        
        JPanel buttonPanel = new JPanel(new GridLayout(2,2));
        // buttonPanel.setBorder(new EmptyBorder(10,50,10,50));
        buttonPanel.add(loadButton);
        buttonPanel.add(b1);
        buttonPanel.add(b2);
        
        // soutPanel
        JPanel southPanel = new JPanel(new GridLayout(2,1));
        southPanel.setBorder(new EmptyBorder(10,50,10,50));
        southPanel.add(sepPanel);
        southPanel.add(buttonPanel);
        
        
        main_frame.add(title, BorderLayout.NORTH);
        main_frame.add(scroller, BorderLayout.CENTER);
        main_frame.add(southPanel, BorderLayout.SOUTH);
        main_frame.setVisible(true);
    }
    
}

// helper class for input restriction, taken from: http://www.java2s.com/Tutorials/Java/Swing_How_to/JTextField/Limit_JTextField_input_to_a_maximum_length_set_the_max_length.htm

class JTextFieldLimit extends PlainDocument {
    private int limit;
    JTextFieldLimit(int limit) {
      super();
      this.limit = limit;
    }

    JTextFieldLimit(int limit, boolean upper) {
      super();
      this.limit = limit;
    }

    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
      if (str == null)
        return;

      if ((getLength() + str.length()) <= limit) {
        super.insertString(offset, str, attr);
      }
    }
  }
