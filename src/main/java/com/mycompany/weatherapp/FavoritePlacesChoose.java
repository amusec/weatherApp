package com.mycompany.weatherapp;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class FavoritePlacesChoose implements ListSelectionListener {
    JList<String> places;
    WeatherApp app;
    JFrame mainFrame;
    DatabaseOperations op;
    public FavoritePlacesChoose(WeatherApp app,String username) {
        this.app = app;
        op = new DatabaseOperations();
        ArrayList<String> favoritePlaces = op.getFavPlaces(username);
        
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String place : favoritePlaces) {
            listModel.addElement(place);
        }
        this.places = new JList<>(listModel);
    }
    public void choosePlace(){
        mainFrame = new JFrame("User Favorite Places");
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.setSize(400, 300);
        mainFrame.setResizable(false);
        JScrollPane scrollPane = new JScrollPane(places);
        scrollPane.setBounds(50, 50, 300, 150);
        Image icon = null;
        try {
            icon = ImageIO.read(WeatherApp.class.getClassLoader().getResource("weather_icons/location/cloudy.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        mainFrame.setIconImage(icon);
        this.places.addListSelectionListener(this);
        mainFrame.add(scrollPane);
        mainFrame.setLayout(null);
        mainFrame.setVisible(true);
    }
    public void dispose(){
        op.closeConnection();
        mainFrame.dispose();
    }
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(!e.getValueIsAdjusting()){
            String selectedPlace = places.getSelectedValue();
            if (selectedPlace != null && app != null) {
                app.city_name.setText(selectedPlace);
                dispose();
            }
        }
    }    
}
