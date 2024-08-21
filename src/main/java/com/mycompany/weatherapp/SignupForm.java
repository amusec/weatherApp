/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.weatherapp;



import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class SignupForm implements ActionListener{
    JFrame frame;
    JLabel user_label;
    JTextField user_input;
    JLabel password_label;
    JPasswordField password_input;
    Font heading_font;
    JLabel heading_label;
    JButton signup;
    JOptionPane pane;
    public void setVisible(){
        frame.setVisible(true);
    }
    
    public  SignupForm (){
        frame = new JFrame("User Signup");
        frame.setLayout(null);
        frame.setSize(400,450);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.white);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        
        
        
        Image icon = null;
        try {
            icon = ImageIO.read(WeatherApp.class.getClassLoader().getResource("weather_icons/location/cloudy.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        frame.setIconImage(icon);
        
        
        heading_label = new JLabel("USER SIGNUP");
        heading_label.setBounds(75,10,300,90);
        heading_font = new Font("Ariel",Font.BOLD,30);
        heading_label.setFont(heading_font);
        heading_label.setForeground(Color.blue);
        frame.add(heading_label);
        
        
        
        user_label = new JLabel("Enter Username");
        user_input = new JTextField();
        password_label = new JLabel("Enter Password");
        password_input = new JPasswordField();   
        password_input.setEchoChar('*');       
        
        
        user_label.setBounds(20,120,200,20);
        frame.add(user_label);
        
        
        
        user_input.setBounds(160,120,200,30);
        frame.add(user_input);
        
        
        
        password_label.setBounds(20,170,200,20);
        frame.add(password_label);
        
        password_input.setBounds(160,170,200,30);
        frame.add(password_input);
        
        
        signup = new JButton("SIGNUP");
        signup.setBounds(160,225,200,30);
        signup.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        signup.setBorderPainted(false);
        signup.setBackground(Color.gray);
        signup.setMargin(new Insets(0, 0, 0, 0));
        signup.addActionListener(this);
        frame.add(signup);
        
        
        frame.repaint(); 
    }
    
    public void showWarning(String message){
        JFrame f = new JFrame();
        JOptionPane.showMessageDialog(f, message);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("SIGNUP")){
            DatabaseOperations operate = new DatabaseOperations();
            String username = user_input.getText();
            String password = password_input.getText();
            boolean b = operate.addUser(username, password);
            
            if(b){
                showWarning("Successfully Signed Up, Login Screen Will open to allow login now!");
                operate.closeConnection();
                frame.dispose();
            }else{
                showWarning("Sorry! Some error occured. Kindly use another username");
                user_input.setText("");
                password_input.setText("");
            }
        }
    }
}
