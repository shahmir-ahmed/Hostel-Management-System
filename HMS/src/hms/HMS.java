/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hms;

/**
 *
 * @author HP
 */

//class Login extends JFrame implements ActionListener{
//    
//    JButton b1;
//    JLabel ml, l1, l2;
//    JTextField tf1;
//    JPasswordField pf1;
//    ImageIcon image = new ImageIcon("C:\\Users\\HP\\Pictures\\Saved Pictures\\icon-hms.png");
//
//    Login(){
//        setTitle("HMS Login");
//        // labels
//        ml = new JLabel("Welcome to HMS");
//        ml.setBounds(200, 50, 500, 80);
//        ml.setFont(new Font("Poppins", Font.BOLD, 28));
//                
//        l1 = new JLabel ("Username: ");
//        l1.setBounds(150, 155, 150, 50);
//        l1.setFont(new Font("Sans Serif", Font.BOLD, 18));
//        
//        l2 = new JLabel ("Password: ");
//        l2.setBounds(150, 265, 150, 50);
//        l2.setFont(new Font("Sans Serif", Font.BOLD, 18));
//        
//        // input fields
//        tf1 = new JTextField();
//        tf1.setBounds(300, 165, 180, 30);
//        tf1.setFont(new Font("Sans Serif", Font.PLAIN, 18));
//        
//        pf1 = new JPasswordField();
//        pf1.setBounds(300, 275, 180, 30);
//        pf1.setFont(new Font("Sans Serif", Font.PLAIN, 18));
//        
//        // button
//        b1 = new JButton("Login");
//        
//        b1.setBounds(250, 350, 100, 40);
//        b1.setFont(new Font("Sans Serif", Font.PLAIN, 18));
////        b1.setBorder(new Border(Color.BLUE));
//        b1.addActionListener(this);
//        
//        // adding components to frame
//        add(ml);
//        add(l1);
//        add(l2);
//        add(b1);
//        add(tf1);
//        add(pf1);
//        
//        // frame configurations
//        setSize(700,600);
//        setLayout(null);
//        setResizable(false);
//        setVisible(true);
//        setLocationRelativeTo(null);
//        getContentPane().setBackground(new Color(252, 219, 3));
//        setIconImage(image.getImage());
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        
//    }
//    
//    @Override
//    public void actionPerformed(ActionEvent e){
//        if(e.getSource()==b1){
//            if("password".equals(pf1.getText())){
//                setVisible(false);
//                JOptionPane.showMessageDialog(this, "Login success");       
//                new Dashboard();
//            }
//            else{
//               JOptionPane.showMessageDialog(this, "incorrect username or password"); 
//            }
//            
//        }
//    }
//}

//class Dashboard extends JFrame implements ActionListener{
//    
//    JLabel ml, ban;
//    JButton b1, b2, b3, b4, b5, b6, b7;
//    ImageIcon icon = new ImageIcon("C:\\Users\\HP\\Pictures\\Saved Pictures\\icon-hms.png");
//    ImageIcon logout = new ImageIcon("C:\\Users\\HP\\Pictures\\Saved Pictures\\logout.png");
//    ImageIcon banner = new ImageIcon("C:\\Users\\HP\\Pictures\\Saved Pictures\\banner.jpg");
//    
////    Image img = Toolkit.getDefaultToolkit().getImage("C:\\Users\\HP\\Pictures\\Saved Pictures\\icon.png");
//    
//    Dashboard(){
//        setTitle("HMS Dasboard");
//        
//        
//        // Label
//        ml = new JLabel("HMS Dashboard");
//        ml.setBounds(500, 10, 500, 80);
//        ml.setFont(new Font("Poppins", Font.BOLD, 35));
//        ml.setForeground(Color.blue);
//        
//        ban = new JLabel(banner);
//        ban.setBounds(30, 300, 800, 500);
//        
//        
//        // Buttons
//        b1 = new JButton("Manage Rooms");
//        b1.setBounds(30, 110, 180, 50);
//        b1.setFont(new Font("Sans Serif", Font.PLAIN, 18));
//        b1.addActionListener(this);
//        
//        b2 = new JButton("New Allotment");
//        b2.setBounds(240, 110, 180, 50);
//        b2.setFont(new Font("Sans Serif", Font.PLAIN, 18));
//        b2.addActionListener(this);
//        
//        b3 = new JButton("Manage Students");
//        b3.setFont(new Font("Sans Serif", Font.PLAIN, 18));
//        b3.setBounds(470, 110, 180,50);
//        
////        b4 = new JButton("Student Mess");
//        b4 = new JButton("Add Room");
//        b4.setFont(new Font("Sans Serif", Font.PLAIN, 18));
//        b4.setBounds(700, 110, 180,50);
//        
////        b5 = new JButton("All Student Living");
//        
//        b5 = new JButton("Manage Allotments");
//        b5.setFont(new Font("Sans Serif", Font.PLAIN, 18));
//        b5.setBounds(920, 110, 200,50);
//        
//        b6 = new JButton(logout);
//        b6.setBounds(1200, 110, 50, 50);
//        b6.addActionListener(this);
//        
//        // addimg
//        add(ml);
//        add(ban);
//        add(b1);
//        add(b2);
//        add(b3);
//        add(b4);
//        add(b5);
//        add(b6);
//        
////        setContentPane(new JPanel() {
////             @Override
////             public void paintComponent(Graphics g) {
////                super.paintComponent(g);
////                g.drawImage(img, 100, 800, null);
////             }
////          });
//        
//        
//        // frame configs
//        setSize(1300,900);
//        setLayout(null);
//        setResizable(false);
//        setVisible(true);
//        setLocationRelativeTo(null);
//        getContentPane().setBackground(new Color(252, 219, 3));
//        setIconImage(icon.getImage());
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    }
//    
//    public void actionPerformed(ActionEvent e){
//        if(e.getSource()==b1){
//               JOptionPane.showMessageDialog(null, "Managing rooms frame");
//        }
//        else if(e.getSource()==b2){
//             JOptionPane.showMessageDialog(null, "New Allotment Frame");
//         }
//        else if(e.getSource()==b6){
//            setVisible(false);
//            JOptionPane.showMessageDialog(null, "Logout success");
//            new Login();
//        }
//    }
//}

// Student class for managing students i.e. viewing students in the hostel, updating student record, deleting student record and also its allotment from hostel will be deleted 
//class Student{
//    public void viewStudents(){
//        
//    }
//    
//    public void updateStudent(){
//        
//    }
//    
//    public void deleteStudent(){
//        
//    }
//}

// Room class to manage rooms
//class Room extends JFrame implements ActionListener{
//    // attributes
//    
//    // methods
//    public void addRoom(){
//    
//    }
//    
//    public void viewRooms(){
//        
//    }
//    
//    public void updateRoom(){
//        
//    }
//    
//    public void deleteRoom(){
//        
//    }
//    
//    public void actionPerformed(ActionEvent e){
//    
//    }
//}

// Allotment class to manage allotments
//class Allotment{
//    public void createAllotment(){ // also student record is created here
//        
//    }
//    
//    public void viewAllotments(){
//        
//    }
//    
//    public void updateAllotment(){
//        
//    }
//    
//    public void deleteAllotment(){
//        
//    }
//}

// Mess class to manage mess of students
class Mess{
    
}

public class HMS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        Student student = new Student();
//        
//        student.createStudentForm();
        
//        new Room();
        
        new Login();
//        new Dashboard();
//        
//        new ForgotPassword();
        
//        new ResetPassword("");
        
//        Student student = new Student(); 
//            student.studentEditFormByRegNo();
    }
}
