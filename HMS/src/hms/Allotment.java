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

/**
 *
 * @author HP
 */

// Allotment class to manage allotments
class Allotment{
//    Student student = new Student();
    
    String allotments[][];
    String columns[]= {"S#", "Name", "Reg No.", "Room No.", "Room mates", "Allotment Date"};
    String columns1[]= {"S#", "Name", "Father Name", "Past Room No.", "Allotment Date", "Leaving Date"};
    
    ImageIcon icon = new ImageIcon("C:\\Users\\HP\\Pictures\\Saved Pictures\\icon-hms.png");

    
    String DB_URL = "jdbc:mysql://localhost:3306/hostel_management_system";
    String DB_USERNAME = "root";
    String DB_PASSWORD = "";
    
    public void createAllotment(int roomId, int stdId){ // called after studnet is created then the room and studnet id is passed here
        // craeting new allotment in DB
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            
            Statement stmt = con.createStatement();
            
            String query = "INSERT INTO allotments (allotment_room_id, allotment_student_id, allotment_status) VALUES("+roomId+", "+stdId+", 'living')";
            
            stmt.executeUpdate(query);
            
            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void viewActiveAllotments(){ // view all allotments in hostel
        // show a frame with table of all alotments
        try{
            Class.forName("com.mysql.jdbc.Driver");
                   
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hostel_management_system", "root", "");
                    
            Statement stmt = con.createStatement();
            
            // counting number of rows in table to initialize 2d array
            ResultSet rs1 = stmt.executeQuery("SELECT COUNT(allotment_id) AS total_allotments FROM allotments WHERE allotment_status='living'");
            
            rs1.next();
            
            String r = rs1.getString("total_allotments");
            
            int noOfColumns = columns.length; // table columns count
            
            int rows = Integer.parseInt(r);
            
            allotments = new String[rows][noOfColumns];
            
            // retrieve all the allotments with student and room details from database
            ResultSet rs2 = stmt.executeQuery("SELECT s.student_name, s.student_reg_no, r.room_no, r.room_students, a.allotment_date FROM students s, rooms r, allotments a WHERE  a.allotment_status='living' AND a.allotment_student_id = s.student_id AND a.allotment_room_id = r.room_id ORDER BY a.allotment_date DESC");
            
            int i=0; // for row
            int j=0;// for column

            int s = 1; // serial number
            while(rs2.next()){
            
                // taking and setting data
                
                allotments[i][j] = String.valueOf(s++);
                j++;
                allotments[i][j] = rs2.getString("student_name");
                j++;
                allotments[i][j] = rs2.getString("student_reg_no");
                j++;
                allotments[i][j] = rs2.getString("room_no");
                j++;
                
                // decrease room mates count beacuse exluding self
                int roomMates = (Integer.parseInt(rs2.getString("room_students") ) - 1);
                
                // if decreasing goes less than 0 then set it to 0
                if(roomMates<0){
                   roomMates = 0; 
                }
                
                allotments[i][j] = String.valueOf(roomMates);
                j++;
                allotments[i][j] = rs2.getString("allotment_date");
                
                  j = 0; // reset column
                  i++;// goto next row
                
            }
            
            con.close();
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
            JFrame frame = new JFrame("All Active Allotments");
        
        // setting table
            JTable jt = new JTable(allotments, columns);

            jt.setFillsViewportHeight(true);

            jt.setFont(new Font("Sans Serif", Font.PLAIN, 18));

            JScrollPane sp = new JScrollPane(jt);   

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
    
    // table of all inactive allotments that is past allotments of studnet that left
    public void viewInActiveAllotments(){
         // show a frame with table of all inactive alotments
        try{
            Class.forName("com.mysql.jdbc.Driver");
                   
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hostel_management_system", "root", "");
                    
            Statement stmt = con.createStatement();
            
            // counting number of rows in table to initialize 2d array
            ResultSet rs1 = stmt.executeQuery("SELECT COUNT(allotment_id) AS total_allotments FROM allotments WHERE allotment_status='left'");
            
            rs1.next();
            
            String r = rs1.getString("total_allotments");
            
            int noOfColumns = columns.length; // table columns count
            
            int rows = Integer.parseInt(r);
            
            allotments = new String[rows][noOfColumns];
            
            // retrieve all the allotments with student and room details from database
            ResultSet rs2 = stmt.executeQuery("SELECT s.student_name, s.student_f_name, r.room_no, a.allotment_date, a.allotment_left_date FROM students s, rooms r, allotments a WHERE  a.allotment_status='left' AND a.allotment_student_id = s.student_id AND a.allotment_room_id = r.room_id ORDER BY a.allotment_date DESC");
            
            int i=0; // for row
            int j=0;// for column

            int s = 1; // serial number
            while(rs2.next()){
            
                // taking and setting data
                
                allotments[i][j] = String.valueOf(s++);
                j++;
                allotments[i][j] = rs2.getString("student_name");
                j++;
                allotments[i][j] = rs2.getString("student_f_name");
                j++;
                allotments[i][j] = rs2.getString("room_no");
                j++;
                allotments[i][j] = rs2.getString("allotment_date");
                j++;
                allotments[i][j] = rs2.getString("allotment_left_date");
                
                  j = 0; // reset column
                  i++;// goto next row
                
            }
            
            con.close();
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
            JFrame frame = new JFrame("All In-Active Allotments");
        
            // setting table
            JTable jt = new JTable(allotments, columns1);

            jt.setFillsViewportHeight(true);

            jt.setFont(new Font("Sans Serif", Font.PLAIN, 18));

            JScrollPane sp = new JScrollPane(jt);   

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
    
    // called when student room is upadted
    public void updateAllotment(int stdId, int roomId){
        try{
            Class.forName("com.mysql.jdbc.Driver");
                   
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hostel_management_system", "root", "");

            Statement stmt = con.createStatement();
            
            stmt.executeUpdate("UPDATE allotments SET allotment_room_id = "+roomId+" WHERE allotment_student_id = "+stdId);
            
            JOptionPane.showMessageDialog(null, "New room allocated to student!");
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    // called when student is deleted (call if there is no option to delete on cascade)
//    public void deleteAllotment(){
//        
//    }
    
    // display table of all students currently living
    public void viewAllLivingStudents(){
        
        // show a frame with table of all living students alotments
        try{
            Class.forName("com.mysql.jdbc.Driver");
                   
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hostel_management_system", "root", "");
                    
            Statement stmt = con.createStatement();
            
            // counting number of rows in table to initialize 2d array
            ResultSet rs1 = stmt.executeQuery("SELECT COUNT(allotment_id) AS total_allotments FROM allotments WHERE allotment_status = 'living'");
            
            rs1.next();
            
            String r = rs1.getString("total_allotments");
            
            int noOfColumns = columns.length; // table columns count
            
            int rows = Integer.parseInt(r);
            
            allotments = new String[rows][noOfColumns];
            
            // retrieve all the allotments os living studnets with student and room details from database
            ResultSet rs2 = stmt.executeQuery("SELECT s.student_name, s.student_reg_no, r.room_no, r.room_students, a.allotment_date FROM students s, rooms r, allotments a WHERE a.allotment_student_id = s.student_id AND a.allotment_room_id=r.room_id AND a.allotment_status = 'living' ORDER BY a.allotment_date DESC");
            
            int i=0; // for row
            int j=0;// for column

            int s = 1; // serial number
            while(rs2.next()){
            
                // taking and setting data
                
                allotments[i][j] = String.valueOf(s++);
                j++;
                allotments[i][j] = rs2.getString("student_name");
                j++;
                allotments[i][j] = rs2.getString("student_reg_no");
                j++;
                allotments[i][j] = rs2.getString("room_no");
                j++;
                
                // decrease room mates count beacuse exluding self
                int roomMates = (Integer.parseInt(rs2.getString("room_students") ) - 1);
                
                // if decreasing goes less than 0 then set it to 0
                if(roomMates<0){
                   roomMates = 0; 
                }
                
                allotments[i][j] = String.valueOf(roomMates);
                j++;
                allotments[i][j] = rs2.getString("allotment_date");
                
                  j = 0; // reset column
                  i++;// goto next row
                
            }
            
            con.close();
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
            // creating a new frame for all the students
        
            JFrame frame = new JFrame("All Living Students");
        
            // setting table
            JTable jt = new JTable(allotments, columns);

            jt.setFillsViewportHeight(true);

            jt.setFont(new Font("Sans Serif", Font.PLAIN, 18));
            
            // creating a new scrollpane for with the table
            JScrollPane sp = new JScrollPane(jt);   

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
    
    // students who have left record
    
    public void viewAllLeftStudents(){
        // show a frame with table of all living students alotments
        try{
            Class.forName("com.mysql.jdbc.Driver");
                   
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hostel_management_system", "root", "");
                    
            Statement stmt = con.createStatement();
            
            // counting number of rows in table to initialize 2d array
            ResultSet rs1 = stmt.executeQuery("SELECT COUNT(allotment_id) AS total_allotments FROM allotments WHERE allotment_status = 'left'");
            
            rs1.next();
            
            String r = rs1.getString("total_allotments");
            
            int noOfColumns = columns.length; // table columns count
            
            int rows = Integer.parseInt(r);
            
            allotments = new String[rows][noOfColumns];
            
            // retrieve all the allotments os living studnets with student and room details from database
            ResultSet rs2 = stmt.executeQuery("SELECT s.student_name, s.student_reg_no, r.room_no, r.room_students, a.allotment_date, a.allotment_left_date FROM students s, rooms r, allotments a WHERE a.allotment_student_id = s.student_id AND a.allotment_room_id=r.room_id AND a.allotment_status = 'left' ORDER BY a.allotment_date DESC");
            
            int i=0; // for row
            int j=0;// for column

            int s = 1; // serial number
            while(rs2.next()){
            
                // taking and setting data
                
                allotments[i][j] = String.valueOf(s++);
                j++;
                allotments[i][j] = rs2.getString("student_name");
                j++;
                allotments[i][j] = rs2.getString("student_reg_no");
                j++;
                allotments[i][j] = rs2.getString("room_no");
                j++;
                allotments[i][j] = rs2.getString("allotment_date");
                j++;
                allotments[i][j] = rs2.getString("allotment_left_date");
                
                  j = 0; // reset column
                  i++;// goto next row
                
            }
            
            con.close();
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
            // creating a new frame for all the students
        
            JFrame frame = new JFrame("All Left Students");
            
            String columnsNew[] = {"S#", "Name", "Reg No.", "Room No.", "Allotment Date", "Allotment Ending Date"};
        
            // setting table
            JTable jt = new JTable(allotments, columnsNew);

            jt.setFillsViewportHeight(true);

            jt.setFont(new Font("Sans Serif", Font.PLAIN, 18));
            
            // creating a new scrollpane for with the table
            JScrollPane sp = new JScrollPane(jt);   

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
}
