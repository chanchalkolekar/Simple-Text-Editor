 import java.awt.*;
 import javax.print.PrintException;
 import javax.swing.*;
 import java.awt.print.PrinterException;
 import java.io.*;
import java.awt.event.*;
import javax.swing.plaf.metal.*;
import javax.swing.text.*;

//Jframe is the plane window where we will bind both Jmenubar and Jtextbar
class editor extends JFrame implements  ActionListener {
   JFrame f;


   JTextArea t;


   editor(){
       //initializing the frame with the tittle
       f = new JFrame( " Notepad"); // this show tittle above JMenbar


       //seting the overall theme of the application
       try{

           UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookandFeel");

           MetalLookAndFeel.setCurrentTheme(new OceanTheme()); //it will set theme for menus and bar
       }
       catch (Exception e){


       }

       //create the text compomnent
       t = new JTextArea();


       //crete the menubar
       JMenuBar menu = new JMenuBar();

       //file menu
       JMenu file = new JMenu( "File");

       //menuitems for file menu
       JMenuItem m1 = new JMenuItem("New");
       JMenuItem m2 = new JMenuItem("Open");
       JMenuItem m3 = new JMenuItem("Save");
       JMenuItem m4 = new JMenuItem("Print");

       //adding actionlisner to the menu bar
       m1.addActionListener(this);
       m2.addActionListener(this);
       m3.addActionListener(this);
       m4.addActionListener(this);

       file.add(m1);
       file.add(m2);
       file.add(m3);
       file.add(m4);


       JMenu edit = new JMenu("Edit");

       JMenuItem m5 = new JMenuItem("Cut");
       JMenuItem m6 = new JMenuItem("Copy");
       JMenuItem m7 = new JMenuItem("Paste");


       //adding actionlisner to the menu bar
       m5.addActionListener(this);
       m6.addActionListener(this);
       m7.addActionListener(this);

      //adding menu items to edit menu
       edit.add(m5);
       edit.add(m6);
       edit.add(m7);

       //create jitem for close option
       JMenuItem close = new JMenuItem("Close");
       close.addActionListener(this);

       //adding the menu to the menu bar
       menu.add(file);
       menu.add(edit);
       menu.add(close);

       //adding the menubar and the text area to frame
       f.setJMenuBar(menu);
       //adding the text area to frame so that user can write text
       f.add(t);
       f.setSize(1600,900); // seting size of your text size


       f.show();  //it show your updation in output window

   }


   //function for adding the functionality to each of the menuitems
    public void actionPerformed(ActionEvent e) {
      //extractig the button pressed

       String s = e.getActionCommand();

       //these conditions are applied for checking what user given the input or which user clicked option
       if(s.equals("New")){
           t.setText("");
       }
       else if(s.equals("Open")) {

           //initializing the Jfile chooser with desiered directory
           JFileChooser j = new JFileChooser("D: ");
           //invoking the opendailoge box with an integer
           int r = j.showOpenDialog(null);  //when box open r becomes 1 and when box is closed then 0

           if (r == JFileChooser.APPROVE_OPTION) {

               //set the label to the path of the slelected file location
               File fi = new File(j.getSelectedFile().getAbsolutePath()); // this get us the whole path of file

               //string to copy the data from the chossen file;
               try {
                   String s1 = "", s2 = " ";

                   //store the whole file in fr
                   FileReader fr = new FileReader(fi);

                   // buffer the fr variable charracter by char so that it can be stored
                   BufferedReader br = new BufferedReader(fr);

                   //storing the first char inside the s2
                   s2 = br.readLine();

                   while ((s1 = br.readLine()) != null) {
                       s2 = s2 + '\n' + s1; //appending s1 to the back of s2
                   }
                   //adding to the tJtextbar
                   t.setText(s2);

               } catch (Exception et) {
                   JOptionPane.showMessageDialog(f, et.getMessage());

               }

           }
       }
       else if(s.equals("Save")){
          JFileChooser j = new JFileChooser("D: ");

          int r = j.showSaveDialog(null);
          if(r == JFileChooser.APPROVE_OPTION){
              File fi = new File(j.getSelectedFile().getAbsolutePath());

              try{
                  FileWriter fw = new FileWriter(fi , false);

                  BufferedWriter bw = new BufferedWriter(fw);

                  bw.write(t.getText());

                  //after writing is finish we need to flash the stream
                  bw.flush();
                  bw.close();

              }
               catch(Exception et){
                  JOptionPane.showMessageDialog(f , et.getMessage());
               }
              }
          }
       else if(s.equals("Print")){
           try {
               t.print();
           }
           catch(PrinterException  ex){
               throw new RuntimeException(ex);
           }
       }
       else if(s.equals("Cut")){
            t.cut();
       }
       else if(s.equals("Copy")){
           t.copy();
       }
       else if(s.equals("Paste")){
           t.paste();
       }
       else if(s.equals("Close")){
         f.setVisible(false);
       }
    }


    public static void main(String[] args) {
        editor e = new editor();

    }
}