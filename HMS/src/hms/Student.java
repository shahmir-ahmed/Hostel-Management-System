/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// sql classes
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

// importing local date class
import java.util.Date;
import java.util.TimeZone;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
        
/**
 *
 * @author HP
 */

// Student class for managing students i.e. craeting new studnet, viewing students in the hostel, updating student record, deleting student record and also its allotment from hostel will be deleted 
class Student extends JFrame implements ActionListener{
    
    JButton save, reset, search, update, delete;
    JLabel ml, sl, l1, l2, l3, l4, l5, l5_1, l6, l7, searchLabel;
    JTextField tf1, tf2, tf3, tf4, tf5, tf6, searchField;
    JComboBox rooms, status;
    JTable jt;
    JScrollPane sp;
    String roomsList[];
    String students[][];
    String columns[]= {"S#", "Name", "Father Name", "Reg No.", "Class", "Contact No."};
    String statusList[] = {"living", "left"};
    
    ImageIcon icon = new ImageIcon("C:\\Users\\HP\\Pictures\\Saved Pictures\\icon-hms.png");
    
    ImageIcon saveIcon = new ImageIcon("hostel_images_&_icon\\save.png");
    ImageIcon resetIcon = new ImageIcon("hostel_images_&_icon\\clear.png");
    ImageIcon searchIcon = new ImageIcon("hostel_images_&_icon\\search.png");
    ImageIcon updateIcon = new ImageIcon("hostel_images_&_icon\\save.png");
    ImageIcon deleteIcon = new ImageIcon("hostel_images_&_icon\\delete.png");
    
    Allotment allotment = new Allotment(); // allotment class object

    
//    Student(){
//        // form to make new allotment for student
//        
//        // 
//    }
    
    public void createStudentForm(){
        // form to make new allotment for student
        setTitle("New Student Allotment");
        
        // main heading
        ml = new JLabel("New Allotment");
        ml.setBounds(310, 10, 500, 80);
        ml.setFont(new Font("Monospace", Font.BOLD, 36));
//        ml.setForeground(Color.gray);
        
        // sub heading
        sl = new JLabel("Please fill the form below to allot room to student:");
        sl.setBounds(20, 130, 900, 20);
        sl.setFont(new Font("Sans Serif", Font.BOLD, 22));
//        sl.setForeground(Color.gray);
        
        // labels
        l1 = new JLabel("Name");
        l1.setBounds(140, 230, 200, 20);
        l1.setFont(new Font("Sans Serif", Font.BOLD, 19));
        
        l2 = new JLabel("Father Name");
        l2.setBounds(140, 310, 200, 20);
        l2.setFont(new Font("Sans Serif", Font.BOLD, 19));
        
        l3 = new JLabel("Registration No.");
        l3.setBounds(140, 380, 200, 40);
        l3.setFont(new Font("Sans Serif", Font.BOLD, 19));
        
        l4 = new JLabel("Class");
        l4.setBounds(140, 460, 200, 20);
        l4.setFont(new Font("Sans Serif", Font.BOLD, 19));
        
        l5 = new JLabel("Contact Number");
        l5.setBounds(140, 540, 200, 20);
        l5.setFont(new Font("Sans Serif", Font.BOLD, 19));
        
        l5_1 = new JLabel("Contact Number Format: 03211234567");
        l5_1.setBounds(380, 575, 300, 20);
        l5_1.setFont(new Font("Sans Serif", Font.ITALIC, 16));
        
        l6 = new JLabel("Room Number");
        l6.setBounds(140, 620, 200, 20);
        l6.setFont(new Font("Sans Serif", Font.BOLD, 19));
        
        // textfields
        tf1 = new JTextField();
        tf1.setBounds(320, 227, 350, 30);
        tf1.setFont(new Font("Sans Serif", Font.PLAIN, 19));
        
        tf2 = new JTextField();
        tf2.setBounds(320, 307, 350, 30);
        tf2.setFont(new Font("Sans Serif", Font.PLAIN, 19));
        
        tf3 = new JTextField();
        tf3.setBounds(320, 377, 350, 30);
        tf3.setFont(new Font("Sans Serif", Font.PLAIN, 19));
        
        tf4 = new JTextField();
        tf4.setBounds(320, 457, 350, 30);
        tf4.setFont(new Font("Sans Serif", Font.PLAIN, 19));
        
        tf5 = new JTextField();
        tf5.setBounds(320, 537, 350, 30);
        tf5.setFont(new Font("Sans Serif", Font.PLAIN, 19));
        
        setRooms();
        
        // if no rooms are avalaible
        if(roomsList.length==0){
            JOptionPane.showMessageDialog(null, "All rooms are allotted!");
            setVisible(false);// making frame invisible
            dispose(); //Destroy the JFrame object
        }
        
        rooms = new JComboBox(roomsList);
        rooms.setBounds(320, 617, 350, 30);
        rooms.setFont(new Font("Sans Serif", Font.PLAIN, 19));
        
        // buttons
        save = new JButton(saveIcon);
        save.setBounds(300, 690, 125, 35);
        save.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        save.setText("Save");
        save.addActionListener(this);
        
        reset = new JButton(resetIcon);
        reset.setBounds(550, 690, 125, 35);
        reset.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        reset.setText("Reset");
        reset.addActionListener(this);
        
        // adding components to frame
        add(ml);
        add(sl);
        
        add(l1);
        add(l2);
        add(l3);
        add(l4);
        add(l5);
        add(l5_1);
        add(l6);
        
        add(tf1);
        add(tf2);
        add(tf3);
        add(tf4);
        add(tf5);
        add(rooms);
        
        add(save);
        add(reset);
//        add(status);
        
        // frame configs
        setSize(880,850);
        setLayout(null);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(252, 219, 3));
        setIconImage(icon.getImage());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    // setting rooms list 
    public void setRooms(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hostel_management_system", "root", "");
                    
            Statement stmt = con.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT room_no from rooms WHERE room_status='Avalaible' AND room_students<'5'");
            
            int roomsCount = 0;
            
            // count rooms for array initialization
            while(rs.next()){
                roomsCount++;
            }
            
            rs.beforeFirst(); //reseting pointer
      
            // intializing array
            roomsList = new String[roomsCount+1];
            
//            // selecting all rooms from table
//            ResultSet rs1 = stmt.executeQuery("SELECT room_no FROM rooms");
            
            int i = 0;
            
            roomsList[i] = "----Please select room----";
            
            while(rs.next()){
                     roomsList[++i] = rs.getString("room_no");
            }
            
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void createStudent(){ // create student record in db
        
            // taking data from form
            String stdName = tf1.getText().trim();
            
            String stdFName = tf2.getText().trim();
            
            String stdRegNo = tf3.getText().trim();
            
            String stdClass = tf4.getText().trim();
            
            String stdContact = tf5.getText().trim();
            
            String stdRoomNo = (String) rooms.getSelectedItem();
            
        try{
            Class.forName("com.mysql.jdbc.Driver");
                   
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hostel_management_system", "root", "");
                    
            Statement stmt = con.createStatement();
            
            // check if student with this registration number already exists or not who is currently living beacuse left student re allotment can be done again
            ResultSet rs = stmt.executeQuery("SELECT s.student_reg_no from students s, allotments a where s.student_reg_no='"+stdRegNo+"' AND a.allotment_student_id = s.student_id AND a.allotment_status='living'");
//            ResultSet rs = stmt.executeQuery("SELECT student_reg_no from students where student_reg_no='"+stdRegNo+"'");
            
            
            // if student not exists
            if(!rs.isBeforeFirst()){
                
                // getting key of newly inserted student record for allotment table
                PreparedStatement ps = con.prepareStatement("INSERT into students "+"(student_name, student_f_name, student_reg_no, student_class, student_contact_no) "+"VALUES('"+stdName+"','"+stdFName+"','"+stdRegNo+"','"+stdClass+"','"+stdContact+"')", Statement.RETURN_GENERATED_KEYS);
                
                ps.executeUpdate();
                
                // getting newly generated key for student record to enter in allotment table
                ResultSet rs1  = ps.getGeneratedKeys();
                
                int stdId = 0;
                
                // assigning key to variable if returned by function
                if(rs1.next()){
                    stdId = rs1.getInt(1);
                }
                
                // searching roomid, room students count in DB
                ResultSet rs2 = stmt.executeQuery("SELECT room_id, room_students from rooms WHERE room_no='"+stdRoomNo+"'");
                
                rs2.next();// moving pointer
                
                int roomNo = rs2.getInt("room_id"); // getting the selected room id
                int roomStudents = Integer.parseInt(rs2.getString("room_students")); // getting the count of studnets in the selected room
                
                roomStudents++; // +1 student count
                
                // increasing selected room student count
                stmt.executeUpdate("UPDATE rooms set room_students='"+roomStudents+"' WHERE room_id="+roomNo); 
                
                // creating allotment
                allotment.createAllotment(roomNo, stdId);  // creating allotment
                
                // reseting all fields
                tf1.setText("");
                tf2.setText("");
                tf3.setText("");
                tf4.setText("");
                tf5.setText("");
                rooms.setSelectedIndex(0);
                JOptionPane.showMessageDialog(null, "Allotment created successfully!");
            }
            // if room exists
            else{
                // reseting all fields
                tf1.setText("");
                tf2.setText("");
                tf3.setText("");
                tf4.setText("");
                tf5.setText("");
                rooms.setSelectedIndex(0);
                JOptionPane.showMessageDialog(null, "Student with the Reg No. '"+stdRegNo+"' already exists!");
            }
                    
            con.close();
            
           
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public void viewStudents(){ // view all students record i.e. show table of studnets record
        try{
            Class.forName("com.mysql.jdbc.Driver");
                   
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hostel_management_system", "root", "");
                    
            Statement stmt = con.createStatement();
            
            // counting number of rows in table to initialize 2d array
            ResultSet rs1 = stmt.executeQuery("SELECT COUNT(student_id) AS total_students FROM students");
            
            rs1.next();// moving pointer
            
            String r = rs1.getString("total_students");
            
            int noOfColumns = 6; // table columns count
            
            int rows = Integer.parseInt(r);
            
            students = new String[rows][noOfColumns];
            
            // retrieve all the students from database
            ResultSet rs2 = stmt.executeQuery("SELECT * FROM students");
            
            int i=0; // for row
            int j=0;// for column

            int s = 1; // serial number
            while(rs2.next()){
            
                // taking and setting data
                
                students[i][j] = String.valueOf(s++);
                j++;
                students[i][j] = rs2.getString("student_name");
                j++;
                students[i][j] = rs2.getString("student_f_name");
                j++;
                students[i][j] = rs2.getString("student_reg_no");
                j++;
                students[i][j] = rs2.getString("student_class");
                j++;
                students[i][j] = rs2.getString("student_contact_no");
                
                  j = 0; // reset column
                  i++;// goto next row
                
            }
            
            con.close();
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
            // creating a new frame for all the students
        
            JFrame frame = new JFrame("All Students");
        
            // setting table
            JTable jt = new JTable(students, columns);

            jt.setFillsViewportHeight(true);

            jt.setFont(new Font("Sans Serif", Font.PLAIN, 18));
            
            // creating a new scrollpane for with the table
            sp = new JScrollPane(jt);   

            sp.setBounds(50, 50, 900, 650);
            
            frame.add(sp);
            
            // frame configs
            frame.setSize(1000,800);
            frame.setLayout(null);
            frame.setResizable(false);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
            frame.getContentPane().setBackground(new Color(252, 219, 3));
            frame.setIconImage(icon.getImage());
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            
    }
    
    public void studentEditFormByRegNo(){
        // dispose jframe object if any created already i.e. if add stduent from is opened already
//        dispose();
        
        // form to edit or update student with room
        
        setTitle("Manage and Search Students");
        
        // main heading
        ml = new JLabel("Manage Student");
        ml.setBounds(310, 10, 500, 80);
        ml.setFont(new Font("Monospace", Font.BOLD, 36));
        
        // sub heading
        sl = new JLabel("Please search the student by registration number:");
        sl.setBounds(20, 130, 900, 40);
        sl.setFont(new Font("Sans Serif", Font.BOLD, 22));
        
        // search label
        searchLabel = new JLabel("Student Reg. no.");
        searchLabel.setBounds(140, 225, 200, 40);
        searchLabel.setFont(new Font("Sans Serif", Font.BOLD, 19));
        
        
        // labels
        l1 = new JLabel("Name");
        l1.setBounds(140, 310, 200, 20);
        l1.setFont(new Font("Sans Serif", Font.BOLD, 19));
        
        l2 = new JLabel("Father Name");
        l2.setBounds(140, 375, 200, 40);
        l2.setFont(new Font("Sans Serif", Font.BOLD, 19));
        
        l3 = new JLabel("Registration No.");
        l3.setBounds(140, 455, 200, 40);
        l3.setFont(new Font("Sans Serif", Font.BOLD, 19));
        
        l4 = new JLabel("Class");
        l4.setBounds(140, 540, 200, 20);
        l4.setFont(new Font("Sans Serif", Font.BOLD, 19));
        
        l5 = new JLabel("Contact Number");
        l5.setBounds(140, 625, 200, 20);
        l5.setFont(new Font("Sans Serif", Font.BOLD, 19));
        
            l5_1 = new JLabel("Contact Number Format: 03xxxxxxxxx");
            l5_1.setBounds(380, 655, 300, 20);
            l5_1.setFont(new Font("Sans Serif", Font.ITALIC, 16));
        
        l6 = new JLabel("Room Number");
        l6.setBounds(140, 700, 200, 20);
        l6.setFont(new Font("Sans Serif", Font.BOLD, 19));
        
        l7 = new JLabel("Living Status");
        l7.setBounds(140, 782, 200, 40);
        l7.setFont(new Font("Sans Serif", Font.BOLD, 19));
        
        // textfields
        searchField = new JTextField();
        searchField.setBounds(320, 227, 200, 30);
        searchField.setFont(new Font("Sans Serif", Font.PLAIN, 19));
        
        tf1 = new JTextField();
        tf1.setBounds(320, 307, 350, 30);
        tf1.setFont(new Font("Sans Serif", Font.PLAIN, 19));
        
        tf2 = new JTextField();
        tf2.setBounds(320, 380, 350, 30);
        tf2.setFont(new Font("Sans Serif", Font.PLAIN, 19));
        
        tf3 = new JTextField();
        tf3.setBounds(320, 457, 350, 30);
        tf3.setFont(new Font("Sans Serif", Font.PLAIN, 19));
        
        tf4 = new JTextField();
        tf4.setBounds(320, 537, 350, 30);
        tf4.setFont(new Font("Sans Serif", Font.PLAIN, 19));
        
        tf5 = new JTextField();
        tf5.setBounds(320, 617, 350, 30);
        tf5.setFont(new Font("Sans Serif", Font.PLAIN, 19));
        
        // rooms list
        
//        roomsList = new String[1]; // creating room array of 1 beacuse initially selection list of rooms is only size of student room only
        rooms = new JComboBox();
        rooms.setBounds(320, 697, 350, 30);
        rooms.setFont(new Font("Sans Serif", Font.PLAIN, 19));
        
        // allotement status
        status = new JComboBox(statusList);
        status.setBounds(320, 787, 350, 30);
        status.setFont(new Font("Sans Serif", Font.PLAIN, 19));
        
        // buttons
        search = new JButton(searchIcon);
        search.setBounds(550, 225, 115, 35);
        search.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        search.setText("Search");
        search.addActionListener(this);
        
        update = new JButton(updateIcon);
        update.setBounds(300, 870, 125, 35);
        update.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        update.setText("Update");
        update.addActionListener(this);
        
        delete = new JButton(deleteIcon);
        delete.setBounds(550, 870, 125, 35);
        delete.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        delete.setText("Delete");
        delete.addActionListener(this);
        
        // adding components to frame
        add(ml);
        add(sl);
        
        add(searchLabel);
        add(l1);
        add(l2);
        add(l3);
        add(l4);
        add(l5);
        add(l5_1);
        add(l6);
        add(l7);
        
        add(searchField);
        add(tf1);
        add(tf2);
        add(tf3);
        add(tf4);
        add(tf5);
        add(rooms);
        add(status);
        
        add(search);
        add(update);
        add(delete);
        
        // frame configs
        setSize(880,1000);
        setLayout(null);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(252, 219, 3));
        setIconImage(icon.getImage());
    }
    
    // edit form for student by searching by registartion number
//    public void studentEditFormByRegNo(){
//        // form to edit or update student with room
//        JFrame frame = new JFrame("Manage Student");
//        
//        // main heading
//        ml = new JLabel("Manage Student");
//        ml.setBounds(310, 10, 500, 80);
//        ml.setFont(new Font("Monospace", Font.BOLD, 36));
//        
//        // sub heading
//        sl = new JLabel("Please enter the student's registartion number:");
//        sl.setBounds(20, 130, 900, 20);
//        sl.setFont(new Font("Sans Serif", Font.BOLD, 22));
//        
//        // search label
//        searchLabel = new JLabel("Student Reg. number");
//        searchLabel.setBounds(140, 230, 200, 20);
//        searchLabel.setFont(new Font("Sans Serif", Font.BOLD, 19));
//                
//        
//        // labels
//        l1 = new JLabel("Name");
//        l1.setBounds(140, 230, 200, 20);
//        l1.setFont(new Font("Sans Serif", Font.BOLD, 19));
//        
//        l2 = new JLabel("Father Name");
//        l2.setBounds(140, 310, 200, 20);
//        l2.setFont(new Font("Sans Serif", Font.BOLD, 19));
//        
//        l3 = new JLabel("Registration No.");
//        l3.setBounds(140, 380, 200, 40);
//        l3.setFont(new Font("Sans Serif", Font.BOLD, 19));
//        
//        l4 = new JLabel("Class");
//        l4.setBounds(140, 460, 200, 20);
//        l4.setFont(new Font("Sans Serif", Font.BOLD, 19));
//        
//        l5 = new JLabel("Contact Number");
//        l5.setBounds(140, 540, 200, 20);
//        l5.setFont(new Font("Sans Serif", Font.BOLD, 19));
//        
//        l5_1 = new JLabel("Contact Number Format: 03211234567");
//        l5_1.setBounds(380, 575, 300, 20);
//        l5_1.setFont(new Font("Sans Serif", Font.ITALIC, 16));
//        
//        l6 = new JLabel("Room Number");
//        l6.setBounds(140, 620, 200, 20);
//        l6.setFont(new Font("Sans Serif", Font.BOLD, 19));
//        
//        // textfields
//        
//        
//        tf1 = new JTextField();
//        tf1.setBounds(320, 227, 350, 30);
//        tf1.setFont(new Font("Sans Serif", Font.PLAIN, 19));
//        
//        tf2 = new JTextField();
//        tf2.setBounds(320, 307, 350, 30);
//        tf2.setFont(new Font("Sans Serif", Font.PLAIN, 19));
//        
//        tf3 = new JTextField();
//        tf3.setBounds(320, 377, 350, 30);
//        tf3.setFont(new Font("Sans Serif", Font.PLAIN, 19));
//        
//        tf4 = new JTextField();
//        tf4.setBounds(320, 457, 350, 30);
//        tf4.setFont(new Font("Sans Serif", Font.PLAIN, 19));
//        
//        tf5 = new JTextField();
//        tf5.setBounds(320, 537, 350, 30);
//        tf5.setFont(new Font("Sans Serif", Font.PLAIN, 19));
//        
//        // rooms list
//        roomsList = new String[1];
//        rooms = new JComboBox(roomsList);
//        rooms.setBounds(320, 617, 350, 30);
//        rooms.setFont(new Font("Sans Serif", Font.PLAIN, 19));
//        
//        // buttons
//        search = new JButton(saveIcon);
//        search.setBounds(300, 690, 125, 35);
//        search.setFont(new Font("Sans Serif", Font.PLAIN, 16));
//        search.setText("Save");
//        search.addActionListener(this);
//        
//        update = new JButton(saveIcon);
//        update.setBounds(300, 690, 125, 35);
//        update.setFont(new Font("Sans Serif", Font.PLAIN, 16));
//        update.setText("Save");
//        update.addActionListener(this);
//        
//        delete = new JButton(resetIcon);
//        delete.setBounds(550, 690, 125, 35);
//        delete.setFont(new Font("Sans Serif", Font.PLAIN, 16));
//        delete.setText("Reset");
//        delete.addActionListener(this);
//        
//        // adding components to frame
//        frame.add(ml);
//        frame.add(sl);
//        
//        frame.add(l1);
//        frame.add(l2);
//        frame.add(l3);
//        frame.add(l4);
//        frame.add(l5);
//        frame.add(l5_1);
//        frame.add(l6);
//        
//        frame.add(tf1);
//        frame.add(tf2);
//        frame.add(tf3);
//        frame.add(tf4);
//        frame.add(tf5);
//        frame.add(rooms);
//        
//        frame.add(update);
//        frame.add(delete);
//        
//        // frame configs
//        frame.setSize(880,850);
//        frame.setLayout(null);
//        frame.setResizable(false);
//        frame.setVisible(true);
//        frame.setLocationRelativeTo(null);
//        frame.getContentPane().setBackground(new Color(252, 219, 3));
//        frame.setIconImage(icon.getImage());
//    }
    
    // searching the student
    public void searchStudent(){
       String regNo = searchField.getText().trim();
       
       try{
           Class.forName("com.mysql.jdbc.Driver");
           
           Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hostel_management_system", "root", "");
           
           Statement stmt = con.createStatement();
           
           //searching student by registration number
           
           String query = "SELECT s.student_name, s.student_f_name, s.student_reg_no, s.student_class, s.student_contact_no, a.allotment_room_id, a.allotment_status FROM students s, allotments a WHERE s.student_reg_no = '"+regNo+"' AND a.allotment_student_id = s.student_id AND a.allotment_status = 'living'";
           
           ResultSet rs = stmt.executeQuery(query);
           
           // if student found
           if(rs.isBeforeFirst()){
               rs.next(); //moving pointer
               
               // getting and setting student data in form from resultset
               tf1.setText(rs.getString("student_name"));
               
               tf2.setText(rs.getString("student_f_name"));
               
               tf3.setText(rs.getString("student_reg_no"));
               
               tf4.setText(rs.getString("student_class"));
               
               tf5.setText(rs.getString("student_contact_no"));
               
//               tf6.setText(rs.getString("allotment_status"));
               
               // saving allotment room id of studnet
               int roomId = Integer.parseInt(rs.getString("allotment_room_id"));
//               System.out.println(roomId);
               // searching for current room number of student
               ResultSet rs2 = stmt.executeQuery("SELECT room_no from rooms WHERE room_id = "+roomId);
               
               rs2.next(); // moving pointer
               
               String stdRoomNo = rs2.getString("room_no"); // getting student's current room no to not include in list of updating student room
//               System.out.println(stdRoomNo);
//               roomsList[0] = stdRoomNo;// setting the studnet current room number on list first element               
               
               // searching available and not full rooms
               
               ResultSet rs1 = stmt.executeQuery("SELECT room_no from rooms WHERE room_status='Avalaible' AND room_students<'5' AND room_no<>'"+stdRoomNo+"'");
            
                int roomsCount = 0;

                // count rooms for array initialization
                while(rs1.next()){
//                    System.out.println(rs1.getString("room_no"));
                    roomsCount++;
                }

                rs1.beforeFirst(); //reseting pointer

                // intializing array
                roomsList = new String[roomsCount+2]; // +2 because current room, tag also add
                
                roomsList[0] = stdRoomNo; // current student rrom
                
                roomsList[1] = "------Select new room from below------"; // message to choose

                int i = 2;
                
                while(rs1.next()){
//                    String roomNo = rs1.getString("room_no");
//                    System.out.println(rs1.getString("room_no"));
//                    if(!roomNo.equals(stdRoomNo)){
//                        System.out.println(rs1.getString("room_no"));
                            roomsList[i++] = rs1.getString("room_no");
//                            System.out.println(roomsList[--i]);
//                    }
                }

//               rooms = new JComboBox(roomsList);
                
                rooms.setModel(new DefaultComboBoxModel(roomsList)); // updating combo box model
               
//               add(rooms);
               
               
               JOptionPane.showMessageDialog(null, "Student found!");  
               
           }
           // if student not found
           else{
               // reseting all the fields
            
                tf1.setText("");

                tf2.setText("");

                tf3.setText("");

                tf4.setText("");

                tf5.setText("");
                
                roomsList = new String[0];

                rooms.setModel(new DefaultComboBoxModel(roomsList));

                status.setSelectedIndex(0);
                
               JOptionPane.showMessageDialog(null, "Student not found!");  
           }
           
       }
            catch(Exception e){
                    e.printStackTrace();
                }
       
    }
    
    // check student without searching
    
    public boolean studentExist(){
        boolean found=false;
        
        String regNo = searchField.getText().trim();
        
        // search in db and return found or not found
        try{
            Class.forName("com.mysql.jdbc.Driver");
                   
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hostel_management_system", "root", "");
                    
            Statement stmt = con.createStatement();
            
            // checking only that student to update who is living
            ResultSet rs = stmt.executeQuery("SELECT s.student_name from students s, allotments a WHERE s.student_reg_no='"+regNo+"' AND a.allotment_status = 'living' AND s.student_id = a.allotment_student_id");
            
            // record is present 
            if (rs.isBeforeFirst()) {   
                     
                    found=true;// if student is found
                } 
            else{
                found=false;// if student is not found
            }
            
            con.close();

            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        return found;
    }
    
    public void updateStudent(){ // update student record in db
        // double check if student reg. is changed after searching in case
        boolean found = studentExist();
        
//        System.out.println("HERE");
        
        if(found==true){
            
            // getting all the data from form
            String regNo = searchField.getText().trim();
            
            String name = tf1.getText().trim();
            
            String fName = tf2.getText().trim();
            
            String stdRegNo = tf3.getText().trim();
            
            String stdClass = tf4.getText().trim();
            
            String contact = tf5.getText().trim();
            
            String roomNo = (String) rooms.getSelectedItem();
            
            String alloStatus = (String) status.getSelectedItem();
            
            
            // executing queries
            try{
                Class.forName("com.mysql.jdbc.Driver");
                   
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hostel_management_system", "root", "");

                Statement stmt = con.createStatement();
                
                // searchin student_id, // searching the student id to update room number in allotment table
                ResultSet rs0 = stmt.executeQuery("SELECT s.student_id FROM students s, allotments a WHERE s.student_reg_no = '"+regNo+"' AND s.student_id = a.allotment_student_id AND a.allotment_status = 'living'");
                
                rs0.next(); //moving pointer
                
                int stdId = rs0.getInt("student_id"); // getting the studnet id
                
                // updating student record
                String query = "UPDATE students SET student_name = '"+name+"', student_f_name = '"+fName+"', student_reg_no = '"+stdRegNo+"', student_class = '"+stdClass+"', student_contact_no = '"+contact+"' WHERE student_id  = '"+stdId+"'";

                stmt.executeUpdate(query);

                // rooms updation check
                // also searching the room id of the room number to update student allotment room id in allotment table
                ResultSet rs1 = stmt.executeQuery("SELECT room_id FROM rooms WHERE room_no = '"+roomNo+"'");
                
                rs1.next(); // moving pointer
                
                int roomId = rs1.getInt("room_id"); // getting the room id

                // checking student selected room number is same as currently allocated or different
                String query1 = "SELECT * FROM allotments WHERE allotment_room_id = "+roomId+" AND allotment_student_id = "+stdId;
                
                ResultSet rs2 = stmt.executeQuery(query1);

                // checking if student allotment status selected is changed
                
                // if status is set to living then check if selected room is changed from current allocated room or not
                if("living".equals((String)status.getSelectedItem())){
                    
                    // if selected room is currently allocated
                    if(rs2.isBeforeFirst()){
                        // do nothing
                    }
                    // if different room selected
                    else{
                        // decrement current room count
                        // getting room id of currently allocated room to student from allotments table using studnet id 
                        stmt.executeUpdate("UPDATE rooms SET room_students = room_students-1 WHERE room_id = (SELECT allotment_room_id FROM allotments WHERE allotment_student_id = "+stdId+")");

                        // increment new room count
                        stmt.executeUpdate("UPDATE rooms SET room_students = room_students+1 WHERE room_id="+roomId);
                        
                        //  updating allotment
                        allotment.updateAllotment(stdId, roomId);
                        
                        // changing room
//                        stmt.executeUpdate("UPDATE allotments SET allotment_room_id = "+roomId+" WHERE allotment_student_id = "+stdId);
                    }
                }
                // if status is set to left
                else{
                    
                    TimeZone tz = TimeZone.getTimeZone("Asia/Karachi");
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); // Quoted "Z" to indicate UTC, no timezone offset
                    df.setTimeZone(tz);
                    String nowAsISO = df.format(new Date());
                    
//                    System.out.println(nowAsISO);
                    
                    stmt.executeUpdate("UPDATE allotments SET allotment_status = '"+alloStatus+"', allotment_left_date ='"+nowAsISO +"' WHERE allotment_student_id = "+stdId);
                    
                    // getting currently allocated room to student
                    ResultSet rs3 = stmt.executeQuery("SELECT allotment_room_id FROM allotments WHERE allotment_student_id = "+stdId);
                    
                    rs3.next();//moving pointer
                    
                    // getting studnet current room id if student status is set to leaved and to be used there
                    int stdRoomId = rs3.getInt("allotment_room_id");
                    
                    // reducing the number of students from the left student's current room
                    stmt.executeUpdate("UPDATE rooms SET room_students = room_students-1 WHERE room_id = "+stdRoomId);
                    
                    JOptionPane.showMessageDialog(null, "Allotment ended!");
                }
                
                
                con.close(); // closing connection
                
                // reseting all the fields
                searchField.setText("");
            
                tf1.setText("");

                tf2.setText("");

                tf3.setText("");

                tf4.setText("");

                tf5.setText("");
                
                roomsList = new String[0];

                rooms.setModel(new DefaultComboBoxModel(roomsList));

                status.setSelectedIndex(0);
                
                JOptionPane.showMessageDialog(null, "Student record updated!");
                
//                JOptionPane.showMessageDialog(null, "Student allotment record updated!");
            
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Student does not exist!");
        }
        
    }
    
    public void deleteStudent(){ // delete student record from db (also the allotment is dleeted that is cascade on delete)
        // double check if student reg. is changed after searching in case
        boolean found = studentExist();
     
        
        if(found==true){
            
            // getting student searched registration number
            String regNo = searchField.getText().trim();

            // executing query
            try{
                Class.forName("com.mysql.jdbc.Driver");
                   
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hostel_management_system", "root", "");

                Statement stmt = con.createStatement();
                
                // decrementing room count of studnet room before deleteing student
                stmt.executeUpdate("UPDATE rooms SET room_students = room_students - 1 WHERE room_id = (SELECT allotment_room_id FROM allotments WHERE allotment_student_id = (SELECT student_id FROM students WHERE student_reg_no = '"+regNo+"'))");
                
                // delete student
                String query = "DELETE FROM students WHERE student_reg_no = '"+regNo+"'";
                
                stmt.executeUpdate(query);
                
                con.close(); // closing connection
                // 
                tf1.setText("");

                tf2.setText("");

                tf3.setText("");

                tf4.setText("");

                tf5.setText("");
                
                roomsList = new String[0];

                rooms.setModel(new DefaultComboBoxModel(roomsList));

                status.setSelectedIndex(0);
                
                JOptionPane.showMessageDialog(null, "Student record deleted!");
                
            
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Student does not exist!");
        }
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==save){
            String name = tf1.getText().trim();
            
            String fName = tf2.getText().trim();
            
            String regNo = tf3.getText().trim();
            
            String stdClass = tf4.getText().trim();
            
            String contact = tf5.getText().trim();
            int contactLength = contact.length();
            
            String roomNo = (String) rooms.getSelectedItem();
            
            // validating
            if(name.isEmpty() || fName.isEmpty() || regNo.isEmpty() || stdClass.isEmpty() || contact.isEmpty() || "----Please select room----".equals(roomNo)){
                JOptionPane.showMessageDialog(null, "Please fill out all the fields!");             
            }
            // checking contact number length
            else if(contactLength>11){
                    JOptionPane.showMessageDialog(null, "Please follow contact number format!");
             }
            // if form is valid then create student
            else{
                createStudent();
//                JOptionPane.showMessageDialog(null, "Allotment created successfully!");
            }
            
        }
        
        // reset button
        if(e.getSource()==reset){
//            int res = JOptionPane.showConfirmDialog(reset, "Reset form?");
//            
//            // yes is pressed
//            if(res==0){
                tf1.setText("");
                tf2.setText("");
                tf3.setText("");
                tf4.setText("");
                tf5.setText("");
                rooms.setSelectedIndex(0);
//            }
//            // no or cancel is pressed
//            else{
//                // do nothing
//            }
            
        }
        
        // search button
        if(e.getSource()==search){
            String regNo = searchField.getText().trim();
            
            // validating
            if(regNo.isEmpty()){
                JOptionPane.showMessageDialog(null, "Please enter contact number!");
            }
            else{
                searchStudent();
            }
        }
        
        // update button
        if(e.getSource()==update){
            String regNo = searchField.getText().trim();
            
            String name = tf1.getText().trim();
            
            String fName = tf2.getText().trim();
            
            String stdRegNo = tf3.getText().trim();
            
            String stdClass = tf4.getText().trim();
            
            String contact = tf5.getText().trim();
            int contactLength = contact.length();
            
            String roomNo = (String) rooms.getSelectedItem();
            
            // validating
            if(regNo.isEmpty()){
                JOptionPane.showMessageDialog(null, "Please fill the search field!");
            }            
            // validating
            else if(name.isEmpty() || fName.isEmpty() || stdRegNo.isEmpty() || stdClass.isEmpty() || contact.isEmpty() || "------Select new room from below------".equals(roomNo)){
                JOptionPane.showMessageDialog(null, "Please fill out all the fields!");             
            }
            // checking contact number length
            else if(contactLength>11){
                    JOptionPane.showMessageDialog(null, "Contact number should be of 11 digits!");
             }
            else{
                updateStudent();
            }
        }
        
        // delete button
        if(e.getSource()==delete){
            String regNo = searchField.getText().trim();
            
            if(regNo.isEmpty()){
                JOptionPane.showMessageDialog(null, "Please fill the search field!");
            }
            else{
                deleteStudent();
            }
        }
    }
}
