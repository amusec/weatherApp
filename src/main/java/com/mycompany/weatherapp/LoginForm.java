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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class LoginForm implements ActionListener {
    JFrame frame;
    JLabel user_label;
    JTextField user_input;
    JLabel password_label;
    JPasswordField password_input;
    Font heading_font;
    JLabel heading_label;
    JButton login;
    JOptionPane pane;
    WeatherApp app;
    public void setVisible(){
        frame.setVisible(true);
    }
    
    public  LoginForm (WeatherApp app) {
        this.app = app;
        frame = new JFrame("User Login");
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
        heading_label = new JLabel("USER LOGIN");
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
        login = new JButton("LOGIN");
        login.setBounds(160,225,200,30);
        login.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        login.setBorderPainted(false);
        login.setBackground(Color.gray);
        login.setMargin(new Insets(0, 0, 0, 0));
        login.addActionListener(this);
        frame.add(login);
        frame.repaint(); 
    }
    
    public void showWarning(String message){
        JFrame f = new JFrame();
        JOptionPane.showMessageDialog(f, message);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("LOGIN")){
            DatabaseOperations operate = new DatabaseOperations();
            String username = user_input.getText();
            String password = password_input.getText();
            
            boolean is_login = operate.checkLogin(username, password);
            if(is_login){
                app.username = username;
                showWarning("Successfulyl Logged IN, Login Screen will be Closed!");
                operate.closeConnection();
                frame.dispose();
            }else{
                showWarning("Login Unsuccessfull");
            }
            
        }
    }
}
