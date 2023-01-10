package hms;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;


class ResetPassword extends JFrame implements ActionListener
{
    JLabel l, l1;
    JPasswordField pf1, pf2;
    JButton reset, back;
    String email;
    ImageIcon icon = new ImageIcon("C:\\Users\\HP\\Pictures\\Saved Pictures\\icon-hms.png");
    
    ResetPassword(String email)
    {

        setTitle("Reset Password");
        
        //
        this.email = email;
        
        l = new JLabel("New Password");
        l.setBounds(150, 50, 300, 50);
        l.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        
        pf1 = new JPasswordField();
        pf1.setBounds(150, 100, 260,30);
        pf1.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        
        l1 = new JLabel("Confirm Password");
        l1.setBounds(150, 125, 1000, 50);
        l1.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        
        pf2 = new JPasswordField();
        pf2.setBounds(150, 165, 260,30);
        pf2.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        
        reset = new JButton("Reset");
        reset.setBounds(200, 215, 100, 30);
        reset.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        reset.setForeground(Color.black);
        reset.setBackground(Color.blue);
        reset.addActionListener(this);
        
        back = new JButton("Back");
        back.setBounds(10, 10, 100, 30);
        back.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        back.setForeground(Color.white);
        back.setBackground(Color.lightGray);
        back.addActionListener(this);
        
        // adding components to frame
        add(l);
        add(l1);
        add(pf1);
        add(pf2);
        add(reset);
        add(back);
        
        // frame configurations
        setSize(500,400);
        setLayout(null);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(252, 219, 3));
        setIconImage(icon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void resetPassword(){
        
        String password  = pf1.getText().trim();
        
        try
            {
                Class.forName("com.mysql.jdbc.Driver");
                
                 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hostel_management_system","root","");
                 
                 // update the password of user according to the email
                 
                 Statement stmt = con.createStatement();
                 
                 String query = "UPDATE users SET password = '"+password+"'WHERE email = '"+email+"'";
                 
                 stmt.executeUpdate(query);
                 
                 JOptionPane.showMessageDialog(null, "Password updated successfully\n Please login to your accoun using your new password!");
                
                 dispose(); // destroy the frame object
                 
                 new Login(); // show login page
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
    }

    @Override
        public void actionPerformed(ActionEvent e)
        {
            
            
            if(e.getSource()==reset){
                
                String password1 = pf1.getText().trim();
                String password2 = pf2.getText().trim();
//                System.out.println(password1);
                // if any of the password field is empty
                if(password1.isEmpty() || password2.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Passwords cannot be blank!");
                }
                // if both are not same
                else if(!password1.equals(password2)){
                    JOptionPane.showMessageDialog(null, "Please make sure both passwords are same!");
                }
                // if valid
                else{
                    resetPassword();
                }
            }
            
            // back button
            if(e.getSource()==back){
                dispose();
                new ForgotPassword();
            }
        }
       
    }
        
