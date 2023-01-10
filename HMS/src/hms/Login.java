/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// sql classes
import java.sql.*;

/**
 *
 * @author HP
 */

class Login extends JFrame implements ActionListener{
    
    JButton login, forgot, register;
    JLabel ml, l1, l2;
    JTextField tf1;
    JPasswordField pf1;
    JCheckBox show;
    ImageIcon image = new ImageIcon("C:\\Users\\HP\\Pictures\\Saved Pictures\\icon-hms.png");
    ImageIcon loginIcon = new ImageIcon("hostel_images_&_icons\\login.png");
    ImageIcon registerIcon = new ImageIcon("hostel_images_&_icons\\register.png");

    Login(){
        
        setTitle("HMS Login");
        
        // labels
        ml = new JLabel("Welcome to HMS");
        ml.setBounds(200, 50, 500, 80);
        ml.setFont(new Font("Poppins", Font.BOLD, 28));
                
        l1 = new JLabel ("Username");
        l1.setBounds(170, 155, 150, 50);
        l1.setFont(new Font("Sans Serif", Font.BOLD, 18));
        
        l2 = new JLabel ("Password");
        l2.setBounds(170, 265, 150, 50);
        l2.setFont(new Font("Sans Serif", Font.BOLD, 18));
        
        // input fields
        tf1 = new JTextField();
        tf1.setBounds(170, 200, 290, 30);
        tf1.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        
        pf1 = new JPasswordField();
        pf1.setBounds(170, 310, 290, 30);
        pf1.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        
        // Checkbox
        show = new JCheckBox("Show password");
        show.setBounds(310, 350, 200, 40);
        show.setBackground(new Color(252, 219, 3));
        show.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        show.addActionListener(this);
        
        // buttons
        login = new JButton(loginIcon);
        login.setText("Login");
//        login.setIcon(loginIcon);
        
        login.setBounds(220, 410, 200, 40);
        login.setFont(new Font("Sans Serif", Font.PLAIN, 18));
//        b1.setBorder(new Border(Color.BLUE));
        login.addActionListener(this);
        
        forgot = new JButton("Forgot password?");
        forgot.setBounds(380, 470, 180,30);
        forgot.setFont(new Font("Sans Serif", Font.PLAIN, 15));
        forgot.addActionListener(this);
        
        register = new JButton(registerIcon);
        register.setText("New Account? Register Here");
        register.setBounds(100, 470, 240,30);
        register.setFont(new Font("Sans Serif", Font.PLAIN, 15));
        register.addActionListener(this);
        
        // adding components to frame
        add(ml);
        add(l1);
        add(l2);
        add(show);
        add(login);
        add(tf1);
        add(pf1);
        add(forgot);
        add(register);
        
        // frame configurations
        setSize(700,600);
        setLayout(null);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(252, 219, 3));
        setIconImage(image.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==login){
//            if("password".equals(pf1.getPassword())){
//                setVisible(false);
//                JOptionPane.showMessageDialog(null, "Login success"); 
//                new Dashboard();
//            }
            
            
            // trimming username
            String username = tf1.getText().trim();
            
            // converting password to string and trimming
            char[] pass = pf1.getPassword();
//            System.out.println(pass);
            String password = new String (pass); 
//            System.out.println(password);
            password = password.trim();
//            System.out.println(password);
            
            // if both username and password is not empty
            if(!username.isEmpty() && !password.isEmpty()){
                    // trimming username
//                    username.trim();
                    
                   // trimming password
//                    System.out.println("checking");
//                    password.trim();
                    
                    
                try{
                    Class.forName("com.mysql.jdbc.Driver");
                    
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hostel_management_system", "root", "");
                    
                    Statement stmt = con.createStatement();
                    
                    ResultSet rs = stmt.executeQuery("SELECT username, password from users WHERE username= '"+username+"'"+" AND password= '"+password+"'");
                    
                    if(rs.isBeforeFirst()){
//                        setVisible(false);
                        dispose();
                        JOptionPane.showMessageDialog(null, "Login success");       
                        new Dashboard();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Incorrect Username or Password");
                    }
                }
                catch(Exception ee){
                    System.out.println(ee);
                }
                
//                    System.out.println(username+password);
//                if("shahmir".equals(username) && "password".equals(password)){
//                        setVisible(false);
//                        JOptionPane.showMessageDialog(null, "Login success");       
//                        new Dashboard();
//                }
//                else{
//                        JOptionPane.showMessageDialog(null, "incorrect username or password");
//                    }
            }
            // if bothe username and password is empty
            else if(username.isEmpty() && password.isEmpty()){
               JOptionPane.showMessageDialog(null, "Please enter Username and Password!"); 
            }
            // if username is empty
            else if(username.isEmpty()){
                JOptionPane.showMessageDialog(null, "Please enter Username!");
            }
            // if password is empty
            else{
                JOptionPane.showMessageDialog(null, "Please enter password!");
            }
            
//            if(show.isSelected()){
////                pf1.setEchoChar((char)0);
//                pf1.setEchoChar(show.isSelected() ? '\u0000' : (Character) UIManager.get("PasswordField.echoChar"));
//            }
//            else{
//                pf1.setEchoChar('*');
//            }
            
        }
        
        if(e.getSource()==forgot){
//                JOptionPane.showMessageDialog(null, "forgot password frame");
                dispose();
                new ForgotPassword();
            }
        
        if(show.isSelected()){
            pf1.setEchoChar((char)0); //password = JPasswordField
        } else {
           pf1.setEchoChar('•');
        }
    }
}

