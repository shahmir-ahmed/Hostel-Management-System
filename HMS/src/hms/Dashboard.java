/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author HP
 */


class Dashboard extends JFrame implements ActionListener{
    
    JLabel ml, ban;
    JButton b1, b2, b3, b4, b5, logout, roomsReport, allotmentsReport, leftStudents, livingStudents, inActiveAllotments, viewRoomDetails;
    ImageIcon icon = new ImageIcon("C:\\Users\\HP\\Pictures\\Saved Pictures\\icon-hms.png");
    ImageIcon logoutIcon = new ImageIcon("C:\\Users\\HP\\Pictures\\Saved Pictures\\logout.png");
    ImageIcon banner = new ImageIcon("hostel_images_&_icon\\banner.png");
    ImageIcon roomIcon = new ImageIcon("hostel_images_&_icon\\room.png");
    ImageIcon newAllotmentIcon = new ImageIcon("hostel_images_&_icon\\new student.png");
    ImageIcon manageStudentIcon = new ImageIcon("hostel_images_&_icon\\Update & Delete Student.png");
    ImageIcon studentIcon = new ImageIcon("hostel_images_&_icon\\all student living.png");
    ImageIcon allAllotmentIcon = new ImageIcon("hostel_images_&_icon\\All Allotments.png");
//    ImageIcon reportIcon = new ImageIcon("hostel_images_&_icon\\report.png");
    ImageIcon leftStudentsIcon = new ImageIcon("hostel_images_&_icon\\leaved students.png");
    ImageIcon inActiveIcon = new ImageIcon("hostel_images_&_icon\\inactive.png");
    ImageIcon activeIcon = new ImageIcon("hostel_images_&_icon\\active.png");
    
//    Allotment allotment = new Allotment(); // allotment class object
    
//    Student student;
    
//    Image img = Toolkit.getDefaultToolkit().getImage("C:\\Users\\HP\\Pictures\\Saved Pictures\\icon.png");
    
    Dashboard(){
        
//        student = new Student();
        
        setTitle("HMS Dasboard");
        
        
        // Label
        ml = new JLabel("HMS Dashboard");
        ml.setBounds(500, 10, 500, 80);
        ml.setFont(new Font("Monospace", Font.BOLD, 40));
        ml.setForeground(Color.gray);
        
        ban = new JLabel(banner);
        ban.setBounds(180, 270, 900, 550);
        
        
        // Buttons
        b1 = new JButton(roomIcon);
        b1.setText("Manage Rooms");
        b1.setBounds(40, 110, 198, 50);
        b1.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        b1.addActionListener(this);
        
        //
        b2 = new JButton(newAllotmentIcon);
        b2.setText(" New Allotment");
        b2.setBounds(270, 110, 200, 50);
        b2.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        b2.addActionListener(this);
        
//        b3 = new JButton("Manage Students")
        b3 = new JButton(manageStudentIcon);
        b3.setText(" Manage Student");
        b3.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        b3.setBounds(504, 110, 220,50);
        b3.addActionListener(this);
        
//        b4 = new JButton("Student Mess");
//        b4 = new JButton("Add Room");
//        b4 = new JButton("View Full Rooms");
        b4 = new JButton(studentIcon);
        b4.setText(" All Students");
        b4.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        b4.setBounds(755, 110, 180,50);
        b4.addActionListener(this);
        
        livingStudents = new JButton(allAllotmentIcon);
        livingStudents.setText(" All Students Living");
        livingStudents.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        livingStudents.setBounds(970, 110, 230, 50);
        livingStudents.addActionListener(this);
        
        viewRoomDetails = new JButton(allAllotmentIcon);
        viewRoomDetails.setText(" View Room Details");
        viewRoomDetails.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        viewRoomDetails.setBounds(100, 190, 250, 50);
        viewRoomDetails.addActionListener(this);
        
//        b5 = new JButton("All Student Living");
        b5 = new JButton(activeIcon);
        b5.setText(" All Active Allotments");
        b5.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        b5.setBounds(390, 190, 240,50);
        b5.addActionListener(this);
        
        inActiveAllotments = new JButton(inActiveIcon);
        inActiveAllotments.setText(" All Inactive Allotments");
        inActiveAllotments.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        inActiveAllotments.setBounds(660, 190, 250, 50);
        inActiveAllotments.addActionListener(this);
        
        leftStudents = new JButton(leftStudentsIcon);
        leftStudents.setText(" All Students Left");
        leftStudents.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        leftStudents.setBounds(940, 190, 230, 50);
        leftStudents.addActionListener(this);
        
//        allotmentsReport = new JButton(reportIcon);
//        allotmentsReport.setText(" Allotment Report");
//        allotmentsReport.setFont(new Font("Sans Serif", Font.PLAIN, 18));
//        allotmentsReport.setBounds(860, 190, 230, 50);
//        allotmentsReport.addActionListener(this);
        
        
        logout = new JButton(logoutIcon);
        logout.setBounds(1200, 27, 50, 50);
        logout.addActionListener(this);
        
        // adding
        add(ml);
        add(ban);
        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(b5);
        add(livingStudents);
        add(leftStudents);
//        add(allotmentsReport);
        add(inActiveAllotments);
        add(viewRoomDetails);
        add(logout);
        
//        setContentPane(new JPanel() {
//             @Override
//             public void paintComponent(Graphics g) {
//                super.paintComponent(g);
//                g.drawImage(img, 100, 800, null);
//             }
//          });
        
        
        // frame configs
        setSize(1300,900);
        setLayout(null);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(252, 219, 3));
//        getContentPane().setBackground(new Color(136,141,245));
        setIconImage(icon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
//    public void choiceForm(){
//        
//        JFrame frame = new JFrame("Search student option");
//        
//        JLabel label = new JLabel("Select search student by... ");
//        label.setBounds(100, 10, 200, 80);
//        label.setFont(new Font("Sans Serif", Font.BOLD, 20));
//        
//        regOption = new JButton("Search by student Reg. no.");
//        regOption.setFont(new Font("Sans Serif", Font.PLAIN, 18));
//        regOption.setBounds(80, 110, 220, 50);
//        regOption.addActionListener(this);
//        
//        conOption = new JButton("Search by contact number");
//        conOption.setFont(new Font("Sans Serif", Font.PLAIN, 18));
//        conOption.setBounds(80, 220, 220, 50);
//        conOption.addActionListener(this);
//        
//        
//        
//        // adding to frame
//        frame.add(label);
//        frame.add(regOption);
//        frame.add(conOption);
//        
//        // frame configs
//        frame.setSize(500,600);
//        frame.setLayout(null);
//        frame.setResizable(false);
//        frame.setVisible(true);
//        frame.setLocationRelativeTo(null);
//        frame.getContentPane().setBackground(new Color(252, 219, 3));
//        frame.setIconImage(icon.getImage());
//    }
    
    public void actionPerformed(ActionEvent e){
        // Manage rooms
        if(e.getSource()==b1){
//               JOptionPane.showMessageDialog(null, "Managing rooms frame");
            Room room = new Room();
            room.ManageRooms();
        }
        // new allotment
        if(e.getSource()==b2){
            // form
//            allotment.createAllotment();
//            new Student();
            Student student = new Student();
            student.createStudentForm();
//            JOptionPane.showMessageDialog(null, "New Allotment Frame");
         }
        // manage students
//        if(e.getSource()==b3){
//
//            choiceForm();
//            
////             JOptionPane.showMessageDialog(null, "managing students frame i.e. viewing, updating and deleting student from hostel");
//         }
        
        // search student by reg_no
        if(e.getSource()==b3){
            // same form as above but with search, dropdown of current room and others and delete and update button and reset button
            Student student = new Student(); 
            student.studentEditFormByRegNo(); // new student form for searching then updating or deleting student
        }
        // search student by contact number
//        if(e.getSource()==conOption){
//            Student student = new Student(); 
//            student.studentEditFormByContact();
//        }
        
        // view all students
        if(e.getSource()==b4){
            // all students in hostel
            Student student = new Student();
            student.viewStudents();
         }
        
        // view all allotments
        if(e.getSource()==b5){
            // all allotments
            Allotment allotment = new Allotment();
            allotment.viewActiveAllotments();
        }
        
        // view all studnets currently living
        if(e.getSource()==livingStudents){
           Allotment allotment = new Allotment();
            allotment.viewAllLivingStudents();
        }
        
        // view all left students
        if(e.getSource()==leftStudents){
            Allotment allotment = new Allotment();
            allotment.viewAllLeftStudents();
        }
        
        // view all inactive allotments
        if(e.getSource()==inActiveAllotments){
            Allotment allotment = new Allotment();
            allotment.viewInActiveAllotments();
        }
        
        // logout
        if(e.getSource()==logout){
            setVisible(false);
            JOptionPane.showMessageDialog(null, "Logged out successfully!");
            new Login();
        }
        
        // view specific room students
        if(e.getSource()==viewRoomDetails){
            Room room = new Room();
            room.searchRoomStudentsFrame();
        }
    }
}

