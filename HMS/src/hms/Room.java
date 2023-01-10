/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hms;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.Cursor;
//import javax.swing.table.DefaultTableModel;

// sql classes
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author HP
 */

// Room class to manage rooms
class Room extends JFrame implements ActionListener{
    // attributes
    
//    JPanel p1;
    JLabel ml1, ml2, ml3;
    JLabel l1, l2, l3, l4;
    JLabel s1, s2; // seperators
    JTextField tf1, tf2;
    JRadioButton rb1, rb2, rb3, rb4;
    ButtonGroup bg1, bg2;
    JButton save, update, delete, search;
    JTable jt;
    JScrollPane sp;
    
    ImageIcon icon = new ImageIcon("C:\\Users\\HP\\Pictures\\Saved Pictures\\icon-hms.png");
    ImageIcon saveIcon = new ImageIcon("hostel_images_&_icon\\save.png");
    ImageIcon searchIcon = new ImageIcon("hostel_images_&_icon\\search.png");
    ImageIcon updateIcon = new ImageIcon("hostel_images_&_icon\\save.png");
    ImageIcon deleteIcon = new ImageIcon("hostel_images_&_icon\\delete.png");
    
//    String data[];
    String data[][]=new String[10][20];
    int columns = 3; // because column numbers are known
        
    String column[]={"Room Number","Room Status","Total Students"}; // table headers
    
//    boolean GLOBALfound=false;// used so that functions can check if user clicks the update or delete button directly without searching
    
    // Main Frame Opens
    Room(){
        setTitle("Manage Rooms");
        
//        JSeparator s = new JSeparator();
//        p1 = new JPanel();
        
        // Add new room section
        // labels
        ml1 = new JLabel("Add New Room");
        ml1.setBounds(50, 50, 270, 40);
        ml1.setFont(new Font("Prosto One", Font.BOLD, 25));
        
        l1 = new JLabel("Room no. ");
        l1.setBounds(50, 100, 170, 40);
        l1.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        
        // textfield
        tf1 = new JTextField();
        tf1.setBounds(140, 105, 100, 30);
        tf1.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        
        tf1.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        
        // radio button
        rb1 = new JRadioButton("Avalaible");
        rb1.setBounds(250, 110, 100, 20);
        rb1.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        rb1.setBackground(new Color(252, 219, 3));
        
        rb2 = new JRadioButton("Not Avalaible");
        rb2.setBounds(350, 110, 150, 20);
        rb2.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        rb2.setBackground(new Color(252, 219, 3));
        
        bg1 = new ButtonGroup();
        bg1.add(rb1);
        bg1.add(rb2);
        
        // button
        save = new JButton(saveIcon);
        save.setBounds(540, 105, 120, 30);
        save.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        save.setText("Save");
        save.addActionListener(this);
        
        // seperator
        s1 = new JLabel("------------------------------------------------------------------------------------------");
        s1.setBounds(0, 170, 900, 10);
        s1.setForeground(Color.gray);
        s1.setFont(new Font("Prosto One", Font.BOLD, 30));
        
        
        // Update or delete room section
        
        //labels
        l2 = new JLabel("Update or Delete Rooms");
        l2.setBounds(50, 200, 570, 40);
        l2.setFont(new Font("Prosto One", Font.BOLD, 25));
        
        l3 = new JLabel("Room no. ");
        l3.setBounds(50, 250, 170, 40);
        l3.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        
        
        // textfield
        tf2 = new JTextField();
        tf2.setBounds(150, 255, 100, 30);
        tf2.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        
        // search button
        search = new JButton(searchIcon);
        search.setText("Search");
        search.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        search.setBounds(270, 255, 110, 30);
        search.addActionListener(this);
        
        // radio button
        rb3 = new JRadioButton("Avalaible");
        rb3.setBounds(410, 260, 100, 20);
        rb3.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        rb3.setBackground(new Color(252, 219, 3));
        
        rb4 = new JRadioButton("Not Avalaible");
        rb4.setBounds(510, 260, 150, 20);
        rb4.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        rb4.setBackground(new Color(252, 219, 3));
        
        bg2 = new ButtonGroup();
        bg2.add(rb3);
        bg2.add(rb4);
        
        // update & delete button
        update = new JButton(updateIcon);
        update.setText("Update");
        update.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        update.setBounds(620, 300, 110, 30);
        update.addActionListener(this);
        
        delete = new JButton(deleteIcon);
        delete.setText("Delete");
        delete.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        delete.setBounds(750, 300, 100, 30);
        delete.addActionListener(this);
        
        // seperator
        s2 = new JLabel("------------------------------------------------------------------------------------------");
        s2.setBounds(0, 370, 900, 10);
        s2.setForeground(Color.gray);
        s2.setFont(new Font("Prosto One", Font.BOLD, 30));
        
        // All Rooms section
        ml3 = new JLabel("All Rooms");
        ml3.setBounds(360, 410, 270, 40);
        ml3.setFont(new Font("Prosto One", Font.BOLD, 25));
        
        // room details table 
        
        viewRooms();
        
          
        
        // adding components
        add(ml1);
        add(l1);
        add(tf1);
        add(rb1);
        add(rb2);
        add(save);
        
        add(s1);// seperator
        
        add(l2);
        add(l3);
        add(tf2);
        add(search);
        add(rb3);
        add(rb4);
        
        add(update);
        add(delete);
        
        add(s2);
        
        add(ml3);
        add(sp);
        
        // panel
//        p1.setSize(900,400);
//        p1.setLayout(null);
//        p1.setVisible(true);
//        
//        add(p1);
        
        // frame configs
        setSize(900,800);
        setLayout(null);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(252, 219, 3));
        setIconImage(icon.getImage());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
        
    // methods
    // creating new room in DB
    public void addRoom(){
        
        String roomNo = tf1.getText();
        
        roomNo = roomNo.trim();
        
        String roomStatus = null;
        
        if(rb1.isSelected()){
            roomStatus = "Avalaible";
        }
        if(rb2.isSelected()){
            roomStatus = "Not Avalaible";
        }
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
                   
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hostel_management_system", "root", "");
                    
            Statement stmt = con.createStatement();
            
            // check if room already exists or not
            ResultSet rs = stmt.executeQuery("SELECT room_no from rooms where room_no='"+roomNo+"'");
            
            // if room not exists
            if(!rs.isBeforeFirst()){
                stmt.executeUpdate("INSERT into rooms "+"(room_no, room_status, room_students) "+"values('"+roomNo+"','"+roomStatus+"','0')");
                // refresh table
                viewRooms();
                JOptionPane.showMessageDialog(null, "Room saved successfully!");
                tf1.setText("");
                bg1.clearSelection();
            }
            // if room exists
            else{
                tf1.setText("");
                bg1.clearSelection();
                JOptionPane.showMessageDialog(null, "Room already exists!");
            }
                    
            con.close();
            
           
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        
                            
    }
    
    // founds and sets rooms from Db in data array
    public void viewRooms(){
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
                   
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hostel_management_system", "root", "");
                    
            Statement stmt = con.createStatement();
            
            // counting number of rows in table to initialize 2d array
            ResultSet rs1 = stmt.executeQuery("SELECT COUNT(room_id) AS total_rooms FROM rooms");
            
            rs1.next();
            
//            System.out.println("here");
            
            String r = rs1.getString("total_rooms");
            
//            System.out.println(r);
            
            int rows = Integer.parseInt(r);
            
            data = new String[rows][columns];
            
            // retrieve all the rooms from database
            ResultSet rs2 = stmt.executeQuery("SELECT * FROM rooms");
            
            int i=0; // for row
            int j=0;// for column

            while(rs2.next()){
                
                // taking data
                String roomNo = rs2.getString("room_no");
                String roomStatus = rs2.getString("room_status");
                String roomStudents = rs2.getString("room_students");
            
                //setting data
                data[i][j] = roomNo;
                j++;
                data[i][j] = roomStatus;
                j++;
                data[i][j] = roomStudents;
                
//                DefaultTableModel tblModel = (DefaultTableModel) jt.getModel();
//            
//                tblModel.addRow(data);
                
                  j = 0; // reset column
                  i++;// goto next row
                
            }
            
        }
        catch(Exception e){
            System.out.println(e);
        }
//        System.out.println(data.length);
//        for(int i=0; i<data.length; i++){
//            for(int j=0; j<data[i].length; j++){
//               System.out.println(data[i][j]); 
//            }
//        }
        
        // updating table
            JTable jt = new JTable(data, column);

//            jt.setRowSelectionAllowed(false);
//
//            jt.setCellSelectionEnabled(false);

            jt.setFillsViewportHeight(true);

            jt.setFont(new Font("Sans Serif", Font.PLAIN, 18));

            sp = new JScrollPane(jt);   

            sp.setBounds(50, 480, 800, 250);
            
            add(sp);

    }
    
    // check room without searching
    
    public boolean roomExist(){
        boolean found=false;
        
        String roomNo = tf2.getText();
        
        // search in db and return found or not found
        try{
            Class.forName("com.mysql.jdbc.Driver");
                   
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hostel_management_system", "root", "");
                    
            Statement stmt = con.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT room_no from rooms WHERE room_no='"+roomNo+"'");
            
            // record is present 
            if (rs.isBeforeFirst()) {   
                    
                    // if rooom exists then
                    rs.next(); // move pointer
//                    bg2.clearSelection();// clear any initial selection
//                    tf2.setForeground(Color.blue);
//                    roomStatus = rs.getString("room_status");
//                    
//                    if("Avalaible".equals(roomStatus)){
//                        // if room status is avalaible then select that radio button other wise other
//                        rb3.setSelected(true);
//                    }
//                    else{
//                        rb4.setSelected(true);
//                    }
                     
                    found=true;// if room is found
                } 
            else{
//                tf2.setForeground(Color.red);
                found=false;// if room is not found
            }
            
            con.close();

            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        return found;
    }
    
    // when room status is updated
    public void updateRoom(){
//        // check only if room is found
        boolean found = roomExist();
        if(found==true){
        
        String roomNo = tf2.getText();
        roomNo = roomNo.trim();
        
        String roomStatus = null;
        
        if(rb3.isSelected()){
            roomStatus = "Avalaible";
        }
         if(rb4.isSelected()){
             roomStatus = "Not Avalaible";
         }
        
        // make connection to db and update that room status record in db 
        try{
            Class.forName("com.mysql.jdbc.Driver");
                   
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hostel_management_system", "root", "");
                    
            Statement stmt = con.createStatement();
            
            stmt.executeUpdate("UPDATE rooms set room_status= '"+roomStatus+"' WHERE room_no='"+roomNo+"'");
            
            viewRooms(); // refreshing rooms
            
            JOptionPane.showMessageDialog(null, "Room updated successfully!");
            
            tf2.setText(""); // clear roomNo field
            bg2.clearSelection();//clear election if any
            
        }
        catch(Exception e){
            System.out.println(e);
        }
        }
        else{
//           tf2.setText(""); // clear roomNo field
//           bg2.clearSelection();//clear election if any
//            tf2.setForeground(Color.red);
           JOptionPane.showMessageDialog(null, "Room does not exist!"); 
        }
    }
    
    // room will be deleted only if it has no students
    public void deleteRoom(){
        
        //check if room exist if clicked without searching
        boolean found = roomExist();
        
        if(found==true){
            
        String roomNo = tf2.getText();
        roomNo = roomNo.trim();
        
        // make connection to db and check that students exists in the room
        try{
            Class.forName("com.mysql.jdbc.Driver");
                   
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hostel_management_system", "root", "");
                    
            Statement stmt = con.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT room_students AS students from rooms WHERE room_no='"+roomNo+"'");
            
            // if student exists
            rs.next();
            String students = rs.getString("students");
            int studentCount = Integer.parseInt(students);
            
            if (studentCount > 0) {      
                JOptionPane.showMessageDialog(null, "Room already has student(s) in it! Please transfer student(s) to another room first from student section...");
            } 
            else{
                stmt.executeUpdate("DELETE FROM rooms WHERE room_no='"+roomNo+"'");
                viewRooms();// refreshing rooms
                JOptionPane.showMessageDialog(null, "Room deleted successfully!");
            }
            
            con.close();
            
        }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else{
//           tf2.setText(""); // clear roomNo field
//           bg2.clearSelection();//clear election if any
//            tf2.setForeground(Color.red);
           JOptionPane.showMessageDialog(null, "Room does not exist!"); 
        }
    }
    
    
    
    // search room
    public void searchRoom(){
        String roomNo = tf2.getText();
        
        String roomStatus=null;
        
        // search in db and get its status
        try{
            Class.forName("com.mysql.jdbc.Driver");
                   
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hostel_management_system", "root", "");
                    
            Statement stmt = con.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT room_status from rooms WHERE room_no='"+roomNo+"'");
            
            // record is present 
            if (rs.isBeforeFirst()) {   
                    
                    // if rooom exists then
                    rs.next(); // move pointer
                    bg2.clearSelection();// clear all the initial selection
//                    tf2.setForeground(Color.blue);
                    roomStatus = rs.getString("room_status");
                    
                    if("Avalaible".equals(roomStatus)){
                        // if room status is avalaible then select that radio button other wise other
                        rb3.setSelected(true);
                    }
                    else{
                        rb4.setSelected(true);
                    }
                    
                    JOptionPane.showMessageDialog(null, "Room number '"+roomNo +"' found!");
                     
//                    found=true;// if room is found
                } 
            else{
                // if room not exists
//                tf2.setText(""); // clear roomNo field
                bg2.clearSelection();//clear selection if any
                JOptionPane.showMessageDialog(null, "Room number '"+roomNo +"' not found!");
//                found=false;// if room is not found
            }
            
            con.close();
            
            
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
//        return found;
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        // if save button is clicked
        if(e.getSource()==save){
            String roomNo = tf1.getText();
            roomNo = roomNo.trim();
            
            // if room no and status is selected
            if(!roomNo.isEmpty() && (rb1.isSelected() || rb2.isSelected())){
                addRoom();
            }
            else if(roomNo.isEmpty()){
                JOptionPane.showMessageDialog(null, "Please enter room number!");
            }
            else if(!(rb1.isSelected() || rb2.isSelected())){
                JOptionPane.showMessageDialog(null, "Please select room status!");
            }
            
        }
        
        // if search button is clicked
        if(e.getSource()==search){
            String roomNo = tf2.getText();
            roomNo = roomNo.trim();
            
            if(!roomNo.isEmpty()){
            // check the room data and return its status in form if it exists
            searchRoom();
            }
            else{
                JOptionPane.showMessageDialog(null, "Please enter room number!");
            }
        }
        
        // if update button is clicked
        if(e.getSource()==update){
            // update the selected room record in textfield
            String roomNo = tf2.getText();
            roomNo = roomNo.trim();
            
            // if all the fields are filled
            if(!(roomNo.isEmpty()) && (rb3.isSelected() || rb4.isSelected())){
                updateRoom();
            }
            // now if room is not entered
            else if(roomNo.isEmpty()){
                JOptionPane.showMessageDialog(null, "Please enter room number!");
            }
            // none of the room update stauts to be updated rb is selected
            else if(!(rb3.isSelected() || rb4.isSelected())){
                JOptionPane.showMessageDialog(null, "Please select room status!");
            }
            
        }
        
        if(e.getSource()==delete){
            // delete the selected room record in textfield
            String roomNo = tf2.getText();
            roomNo = roomNo.trim();
            
            // if all the fields are filled
            if(!(roomNo.isEmpty())){
                // first check if any student is present in room, if present the show message otherwsie delete room
                deleteRoom();
            }
            // now if room is not entered
            else if(roomNo.isEmpty()){
                JOptionPane.showMessageDialog(null, "Please enter room number!");
            }
            
        }
    }
}
