

    package com.mycompany.weatherapp;

    import java.awt.Color;
    import java.awt.Image;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.io.IOException;
    import java.util.logging.Level;
    import java.util.logging.Logger;
    import javax.imageio.ImageIO;
    import javax.swing.JButton;
    import javax.swing.ImageIcon;
    import javax.swing.JFrame;
    import javax.swing.JLabel;
    import javax.swing.JMenu;
    import javax.swing.JMenuBar;
    import javax.swing.JMenuItem;
    import javax.swing.JOptionPane;
    import javax.swing.JTextField;


    public class WeatherApp implements ActionListener {

        String username = null;


        JMenuBar menubar = new JMenuBar();
        JMenu file,user_operations;
        JMenuItem userlogin,usersignup;
        JMenuItem favplaces;

        JFrame frame = new JFrame("Weather App");
        JLabel city_label = new JLabel("Enter City Name");
        JTextField city_name = new JTextField();
        JButton submit = new JButton("Search By City");
        JButton searchByIp = new JButton("Search By IP");
        JButton fav = new JButton();
        JLabel weatherIcon = new JLabel();
        JLabel weatherDetails = new JLabel("Weather: Sunny, 25°C");

        JLabel day1 = new JLabel();
        JLabel day1_text = new JLabel("Temp °C");
        JLabel day2 = new JLabel();
        JLabel day2_text = new JLabel("Temp °C");
        JLabel day3 = new JLabel();
        JLabel day3_text = new JLabel("Temp °C");


        JLabel dayLabel1 = new JLabel();
        JLabel dayLabel2 = new JLabel();
        JLabel dayLabel3 = new JLabel();
        JLabel locationIcon = new JLabel();
        JLabel locationText = new JLabel();




        public void runApp(){

            frame.setVisible(true);
            frame.setSize(470,650);
            frame.setLayout(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().setBackground(Color.WHITE);
            frame.setResizable(false);


            file = new JMenu("File");
            userlogin = new JMenuItem("Login");
            usersignup = new JMenuItem("Signup");
            favplaces = new JMenuItem("Get Favorite Places");
            favplaces.addActionListener(this);


            userlogin.addActionListener(this);
            usersignup.addActionListener(this);

            user_operations = new JMenu("User Operations");
            user_operations.add(userlogin);
            user_operations.add(usersignup);
            file.add(favplaces);
            menubar.add(file);
            menubar.add(user_operations);
            menubar.setBackground(Color.white);
            frame.setJMenuBar(menubar);

            city_label.setBounds(20, 20, 200, 30);
            frame.add(city_label);

            city_name.setBounds(200, 25, 200, 30);
            frame.add(city_name);

            submit.setBounds(50, 70, 150, 30);
            submit.setBackground(Color.LIGHT_GRAY); 
            submit.setForeground(Color.BLACK);
            submit.setFocusPainted(false);
            submit.addActionListener(this);
            frame.add(submit);

            searchByIp.setBounds(220, 70, 150, 30);
            searchByIp.setBackground(Color.LIGHT_GRAY); 
            searchByIp.setForeground(Color.BLACK);
            searchByIp.addActionListener(this);

            frame.add(searchByIp);

            weatherIcon.setBounds(150, 150, 150, 150);
            frame.add(weatherIcon);

            ImageIcon locationico = new ImageIcon(WeatherApp.class.getClassLoader().getResource("weather_icons/location/location.png"));
            locationIcon.setIcon(locationico);
            locationIcon.setBounds(20,110,30,30);
            frame.add(locationIcon);

            locationText.setBounds(50,110,250,30);
            frame.add(locationText);

            ImageIcon favIco = new ImageIcon(WeatherApp.class.getClassLoader().getResource("weather_icons/location/favorite.png"));
            fav.setIcon(favIco);
            fav.setBounds(300, 110, 26, 26);
            fav.addActionListener(this);
            frame.add(fav);
            weatherDetails.setBounds(175, 280, 200, 65);


            day1.setBounds(10, 400, 150, 150);
            day1_text.setBounds(35,550,150,30);
            dayLabel1.setBounds(35,400,150,20);

            Image icon = null;
            try {
                icon = ImageIO.read(WeatherApp.class.getClassLoader().getResource("weather_icons/location/cloudy.png"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            frame.setIconImage(icon);

            frame.add(day1_text);
            frame.add(day1);
            frame.add(dayLabel1);
            day2.setBounds(160, 400, 150, 150);
            day2_text.setBounds(185,550,150,30);
            dayLabel2.setBounds(185,400,150,20);

            frame.add(day2_text);
            frame.add(day2);
            frame.add(dayLabel2);

            day3.setBounds(310, 400, 150, 150);
            day3_text.setBounds(335,550,150,30);
            dayLabel3.setBounds(335,400,150,20);

            frame.add(day3_text);
            frame.add(day3);
            frame.add(dayLabel3);
            frame.add(weatherDetails);
            frame.repaint();
        }

        public void setWeather(){
            day1_text.setText("<html> Temp °C  "+GetWeatherForecast.getForecastDayTempC(1)+"<br> Temp °F  "+GetWeatherForecast.getForecastDayTempF(1)+"</html>");
            ImageIcon day1_icon = new ImageIcon(WeatherApp.class.getClassLoader().getResource("weather_icons/day/"+GetWeatherForecast.getForecastDayIconCode(1)+".png"));
            dayLabel1.setText("Tomorrow");

            day1.setIcon(day1_icon);

            day2_text.setText("<html>Temp °C  "+GetWeatherForecast.getForecastDayTempC(2)+"<br> Temp °F  "+GetWeatherForecast.getForecastDayTempF(2)+"</html>");
            ImageIcon day2_icon = new ImageIcon(WeatherApp.class.getClassLoader().getResource("weather_icons/day/"+GetWeatherForecast.getForecastDayIconCode(2)+".png"));
            day2.setIcon(day2_icon);
            dayLabel2.setText(GetWeatherForecast.getForecastDay(2));

            day3_text.setText("<html>Temp °C  "+GetWeatherForecast.getForecastDayTempC(3)+"<br> Temp °F  "+GetWeatherForecast.getForecastDayTempF(3)+"</html>");
            ImageIcon day3_icon = new ImageIcon(WeatherApp.class.getClassLoader().getResource("weather_icons/day/"+GetWeatherForecast.getForecastDayIconCode(3)+".png"));
            day3.setIcon(day3_icon);
            dayLabel3.setText(GetWeatherForecast.getForecastDay(3));

            weatherDetails.setText("<html>Temp °C  "+GetWeatherForecast.getCurrentTempC()+"<br>Temp °F  "+GetWeatherForecast.getCurrentTempF()+"<br>Feelslike "+GetWeatherForecast.getCurrentFeelslike()+"<br>Text: "+GetWeatherForecast.getCurrentConditionText()+"</html>");
            ImageIcon current_icon = new ImageIcon(WeatherApp.class.getClassLoader().getResource("weather_icons/day/"+GetWeatherForecast.getCurrentIconCode()+".png"));
            weatherIcon.setIcon(current_icon);

            locationText.setText(GetWeatherForecast.getCurrentLocation());
        }

        public static void main(String[] args) {
            WeatherApp app = new WeatherApp();
            String ip = GetIpAddress.getIpAddress();
            GetWeatherForecast.getWeatherData(ip, 4);
            app.runApp();
            app.setWeather();
        }

        public void showWarning(String message){
            JFrame f = new JFrame();
            JOptionPane.showMessageDialog(f, message);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("Search By City")){
                GetWeatherForecast.getWeatherData(city_name.getText(), 4);
                setWeather();
            }

            if(e.getActionCommand().equals("Search By IP")){
                String ip = GetIpAddress.getIpAddress();
                GetWeatherForecast.getWeatherData(ip, 4);
                setWeather();
            }

            if(e.getActionCommand().equals("Login")){
                LoginForm form = new LoginForm(this);
                form.setVisible();
            }
            if(e.getActionCommand().equals("Signup")){
                SignupForm form  = new SignupForm();
                form.setVisible();
            }

            if(e.getSource() == fav){
                if(username != null){
                    String[] location = locationText.getText().split(",");
                    String name = location[0];
                    String region = location[1];
                    String country = location[2];
                    DatabaseOperations op = new DatabaseOperations();
                    boolean isInsert = op.addFavoritePlace(username, region, country, name);
                    showWarning("Successfully Added To Fav Place");
                }else{
                    showWarning("Please Login First to Add Favorite Place");
                }
            }

            if(e.getSource() == favplaces){
                if(username != null){
                    FavoritePlacesChoose places = new FavoritePlacesChoose(this,username);
                    places.choosePlace();
                }else{
                    showWarning("Please Login First to choose Favorite Place");
                }
            }
        }
    }
