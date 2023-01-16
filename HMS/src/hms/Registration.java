package hms;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

//Regex
import java.util.regex.Matcher; 
import java.util.regex.Pattern;

public class Registration extends JFrame implements ActionListener{
    
    JLabel l1, l2, l3, l4, l5, l6, l7;  
    JTextField tf1, tf2, tf5, tf6, tf7;  
    JButton btn1, btn2;  
    JPasswordField p1, p2;  
    
    // DB configs
    String url= "jdbc:mysql://localhost/";
    String dbName= "hostel_management_system";
    String driver= "com.mysql.jdbc.Driver";
    String userName= "root";
    String pass= "";
    
    Registration()  
    {  
//        // labels
//        l1 = new JLabel("Registration Form"); 
//        l1.setBounds(200, 30, 400, 50);  
//        l1.setForeground(Color.blue);  
//        l1.setFont(new Font("Sans Serif", Font.BOLD, 30));  
//        
//        l2 = new JLabel("Name:"); 
//        l2.setBounds(80, 120, 200, 30);  
//        l2.setFont(new Font("Sans Serif", Font.BOLD, 20)); 
//        
//        l3 = new JLabel("Email-ID:");
//        l3.setBounds(80, 180, 200, 30);  
//        l3.setFont(new Font("Sans Serif", Font.BOLD, 20));  
//        
//        l4 = new JLabel("User name");  
//        l4.setBounds(80, 210, 200, 30);  
//        l4.setFont(new Font("Sans Serif", Font.BOLD, 20));  
//        
//        l5 = new JLabel("Password:");
//        l5.setBounds(80, 260, 200, 30);
//        l5.setFont(new Font("Sans Serif", Font.BOLD, 20));  
//        
//        l6 = new JLabel("Confirm Password:");
//        l6.setBounds(80, 300, 200, 30); 
//        l6.setFont(new Font("Sans Serif", Font.BOLD, 20));
//        
//        l7 = new JLabel("Already Registered?");
//        l7.setFont(new Font("Sans Serif", Font.BOLD, 20));
//        l7.setBounds(200, 410, 500, 50);
//        l7.setForeground(Color.blue);
//
//        // textfields
//        tf1 = new JTextField(); 
//        tf1.setBounds(300, 110, 200, 30);  
//        tf1.setFont(new Font("Sans Serif", Font.PLAIN, 20));
//        
//        tf2 = new JTextField();
//        tf2.setBounds(300, 180, 200, 30);  
//        tf2.setFont(new Font("Sans Serif", Font.PLAIN, 20));
//        
//        tf5 = new JTextField(); 
//        tf5.setBounds(300, 210, 200, 30);
//        tf5.setFont(new Font("Sans Serif", Font.PLAIN, 20));
//        
//        // password fields
//        p1 = new JPasswordField();
//        p1.setBounds(300, 230, 200, 30); 
//        p1.setFont(new Font("Sans Serif", Font.PLAIN, 20));
//        
//        p2 = new JPasswordField();
//        p2.setBounds(300, 270, 200, 30); 
//        p2.setFont(new Font("Sans Serif", Font.PLAIN, 20));
//         
//          
//        // buttons
//        btn1 = new JButton("Submit");
//        btn1.setFont(new Font("Sans Serif", Font.BOLD, 20)); 
//        btn1.addActionListener(this);  
//        btn1.setBounds(200, 340, 200, 30); 
//        
//        btn2 = new JButton("Login");
//        btn2.setFont(new Font("Sans Serif", Font.BOLD, 20)); 
//        btn2.addActionListener(this);  
//        btn2.setBounds(300, 380, 200, 50);
//        
//        // adding components to frame
//        add(l1);  
//        
//        add(l2);  
//        add(tf1);
//        
//        add(l3);  
//        add(tf2);
//        
//        add(l4);  
//        add(p1);
//        
//        add(l5);  
//        add(p2);
//        
//        add(l6);  
//        add(tf5);
//        
//        add(btn1);  
//        add(l7);
//        add(btn2);
//
//   
//        // frame config
//        setVisible(true);  
//        setSize(700, 700);  
//        setLayout(null); 
//        setLocationRelativeTo(null);
//        getContentPane().setBackground(new Color(252, 219, 3));
//        setResizable(false);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
//        setTitle("Registration Form");  
        
        // labels
        l1 = new JLabel("Registration Form"); 
        l1.setBounds(300, 60, 400, 50);  
        l1.setForeground(Color.blue);  
        l1.setFont(new Font("Sans Serif", Font.BOLD, 40));  
        
        l2 = new JLabel("Name"); 
        l2.setBounds(200, 180, 200, 30);  
        l2.setFont(new Font("Sans Serif", Font.BOLD, 18)); 
        
        l3 = new JLabel("Email");
        l3.setBounds(200, 260, 200, 30);  
        l3.setFont(new Font("Sans Serif", Font.BOLD, 18));  
        
        l4 = new JLabel("Username");  
        l4.setBounds(200, 320, 200, 30); 
        l4.setFont(new Font("Sans Serif", Font.BOLD, 18));  
        
        l5 = new JLabel("Password");
        l5.setBounds(200, 400, 200, 30);
        l5.setFont(new Font("Sans Serif", Font.BOLD, 18));  
        
        l6 = new JLabel("Confirm Password");
        l6.setBounds(200, 480, 200, 30); 
        l6.setFont(new Font("Sans Serif", Font.BOLD, 18));
        
//        l7 = new JLabel("Already have an account?");
//        l7.setFont(new Font("Sans Serif", Font.BOLD, 25));
//        l7.setBounds(220, 570, 500, 50);
//        l7.setForeground(Color.blue);

        // textfields
        tf1 = new JTextField(); 
        tf1.setBounds(400, 180, 350, 30);  
        tf1.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        
        tf2 = new JTextField();
        tf2.setBounds(400, 260, 350, 30);  
        tf2.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        
        tf5 = new JTextField(); 
        tf5.setBounds(400, 320, 350, 30);
        tf5.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        
        // password fields
        p1 = new JPasswordField();
        p1.setBounds(400, 400, 350, 30); 
        p1.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        
        p2 = new JPasswordField();
        p2.setBounds(400, 480, 350, 30); 
        p2.setFont(new Font("Sans Serif", Font.PLAIN, 18));
         
          
        // buttons
        btn1 = new JButton("Submit");
        btn1.setFont(new Font("Sans Serif", Font.BOLD, 20)); 
        btn1.addActionListener(this);  
        btn1.setBounds(350, 570, 200, 30); 
        
        btn2 = new JButton("Login");
        btn2.setFont(new Font("Sans Serif", Font.BOLD, 20)); 
        btn2.addActionListener(this);  
        btn2.setForeground(Color.blue);
        btn2.setBounds(550, 575, 120, 40);
        
        // adding components to frame
        add(l1);  
        
        add(l2);  
        add(tf1);
        
        add(l3);  
        add(tf2);
        
        add(l4);  
        add(p1);
        
        add(l5);  
        add(p2);
        
        add(l6);  
        add(tf5);
        
//        add(l7);
        add(btn1);
        
        add(btn2);
   
        // frame config
        setVisible(true);  
        setSize(850, 800);  
        setLayout(null); 
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(252, 219, 3));
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  
        setTitle("Registration Form");  
    }  
    
    // register new user
    public void register(){
        
                String name = tf1.getText().trim();
                String email = tf2.getText().trim();
                String password = p1.getText().trim();
                String username = tf5.getText().trim();
               
               try {
                   Class.forName(driver);

                   Connection conn = DriverManager.getConnection(url+dbName, userName, pass);
                   
                   Statement stmt = conn.createStatement();
                   
                   String query1 = "INSERT INTO users" +"(name, email, username, password)" +"VALUES ('"+name+"','"+email+"', '"+username+"','"+password+"')";
                    
                   stmt.executeUpdate(query1);
                   
                   conn.close();
                   
                   JOptionPane.showMessageDialog(btn1, "Registered Successfully");  
                   
                   // destroy frame
                   dispose();
                   
                   // pop up login page
//                   new Login();
                   
//                   tf1.setText("");
//                   tf2.setText("");
//                   p1.setText("");
//                   p2.setText("");
//                   tf5.setText("");    
               
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
    }
    
    // Function to check entered email already exists in DB
    public void checkEmail(){
        //        
        String email = tf2.getText().trim();
        
         
        try {
            Class.forName(driver);

            Connection conn = DriverManager.getConnection(url+dbName, userName, pass);
                   
            Statement stmt = conn.createStatement();
            
            String query = "select email FROM users where email='" + email + "'";
            
            ResultSet rs = stmt.executeQuery(query);
            
            // if user with the email already exists
            if(rs.isBeforeFirst())
            {
                JOptionPane.showMessageDialog(null, "The entered email is already registered!\nPlease enter a different email");
            }
            // if not exists
            else
            {
                register(); // register user
            }
            
        } 
        catch (Exception ex) {
            ex.printStackTrace();
        }
        
         
   
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
            // register button
            if(e.getSource() == btn1)
            {

                String name = tf1.getText().trim();
                String email = tf2.getText().trim();
                String username = tf5.getText().trim();  
                String password = p1.getText().trim();
                String cpassword = p2.getText().trim(); 
                
                // validating name for numbers
//                char[] chars = name.toCharArray();
//                    StringBuilder sb = new StringBuilder();
//                    for(char c : chars){
//                       if(Character.isDigit(c)){
//                          sb.append(c);
//                       }
//                    }
//                    
//                    // converting found string of numbers from name
//                    String str = sb.toString();
                    
                // validating name field for special characters
                Pattern digit = Pattern.compile("[0-9]");
                Pattern special = Pattern.compile ("[!@#$%^&*()-_/+=;:\"\'<>/~`,.|<>?{}\\[\\]~-]");
                
                Matcher hasDigit = digit.matcher(name);
                Matcher hasSpecial = special.matcher(name);
                
                // if any field is empty
                if(name.isEmpty() || email.isEmpty() || password.isEmpty() || cpassword.isEmpty() || username.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Please fill all the fields!");  
                }
               // if name contains numbers or special characters or both
                else if(hasDigit.find() || hasSpecial.find()){
                       JOptionPane.showMessageDialog(null, "Name should only have characters!");
                }
                // if both passwords are not same
                else if(!password.equals(cpassword)){
                    JOptionPane.showMessageDialog(null, "Both passwords must be same!");  
                }
                // if everything is valid then check if email already exists in DB or not
                else{
                    checkEmail();
                }
        }
            // login button
            if(e.getSource()==btn2){
                dispose(); // destroy register frame
                
                new Login();// open login page
            }
    }
    
}