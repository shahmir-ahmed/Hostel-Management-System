package hms;


import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;


class ForgotPassword extends JFrame implements ActionListener
{
    JLabel l, l1;
    JTextField tf;
    JButton submit, back;
    ImageIcon icon = new ImageIcon("C:\\Users\\HP\\Pictures\\Saved Pictures\\icon-hms.png");
    
    ForgotPassword()
    {
          
        setTitle("Forgot Password");
        
        l = new JLabel("Forgot Password");
        l.setBounds(210, 5, 200, 50);
        l.setFont(new Font("Sans Serif", Font.PLAIN, 22));
        
        l1= new JLabel("Enter your e-mail address and we'll send you a link to reset your password.");
        l1.setBounds(30, 70, 1300, 50);
        l1.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        
        tf = new JTextField();
        tf.setBounds(140,150,350,30);
        tf.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        
        submit = new JButton("Submit");
        submit.setBounds(240, 220, 100, 30);
        submit.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        submit.setForeground(Color.white);
        submit.setBackground(Color.blue);
        submit.addActionListener(this);
        
        
        back = new JButton("Back");
        back.setBounds(10, 10, 100, 30);
        back.setFont(new Font("Sans Serif", Font.PLAIN, 18));
        back.setForeground(Color.white);
        back.setBackground(Color.lightGray);
        back.addActionListener(this);
        
        // adding components to frame
        add(l);
        add(l1);
        add(tf);
        add(submit);
        add(back);
        
        // frame configs
        setSize(650, 400);
        setLayout(null);   
	setVisible(true);  
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(252, 219, 3));
        setIconImage(icon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void verifyEmail(){
        
        String email = tf.getText().trim();
        
         try
        {              
             Class.forName("com.mysql.jdbc.Driver");
             
             Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hostel_management_system","root","");
             
             Statement stmt = con.createStatement();
             
             ResultSet rs = stmt.executeQuery("SELECT email FROM users WHERE email = '"+email+"'");
             
             // user found
             if(rs.isBeforeFirst()){
//                 setVisible(false);
                 dispose();
                 JOptionPane.showMessageDialog(null, "Email verified!\nYou are now beinig redirected to next page!\nPlease reset your password on the page!");
                 new ResetPassword(email);
             }  
             // user not found
             else{
                 JOptionPane.showMessageDialog(null, "Sorry entered email does not exist!");
             }
             
         }
         catch(Exception e)
         {
             e.printStackTrace();
         }
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
            
            // submit button
        if(e.getSource()==submit){
            String email = tf.getText().trim();
            
            if(email.isEmpty()){
                JOptionPane.showMessageDialog(null, "Please fill the email field!");
            }
            else{
                verifyEmail();
            }
        }
        
        // back button
        if(e.getSource()==back){
            dispose();
            new Login();
        }
    }
}
