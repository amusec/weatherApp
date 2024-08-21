package com.mycompany.weatherapp;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;

public class GetWeatherForecast {

    private static final String API_KEY = "YOUR_API_KEY";
    private static JSONObject weatherData;

    
    public static int getHour(){
        int hours  = LocalTime.now().getHour();
        return hours%24;
    }
    
    
    public static void getWeatherData(String location, int days) {
        try {
            HttpResponse<JsonNode> response = Unirest.get("https://api.weatherapi.com/v1/forecast.json")
                    .queryString("key", API_KEY)
                    .queryString("q", location)
                    .queryString("aqi", "no")
                    .queryString("days", days)
                    .queryString("hour", getHour())
                    .asJson();

            if (response.getStatus() == 200) {
                weatherData = response.getBody().getObject();
            } else {
                System.err.println("Failed to get weather forecast: " + response.getStatusText());
            }
        } catch (Exception e) {
            System.err.println("Exception occurred: " + e);
        }
    }

    public static double getForecastDayTempC(int dayIndex) {
        if (weatherData == null) {
            return Double.NaN; 
        }
        JSONObject forecast = weatherData.getJSONObject("forecast");
        JSONArray forecastDays = forecast.getJSONArray("forecastday");
        JSONObject forecastObj = forecastDays.getJSONObject(dayIndex - 1);
        JSONObject dayObj = forecastObj.getJSONObject("day");
        return dayObj.getDouble("avgtemp_c");
    }

    public static double getForecastDayTempF(int dayIndex) {
        if (weatherData == null) {
            return Double.NaN; 
        }
        JSONObject forecast = weatherData.getJSONObject("forecast");
        JSONArray forecastDays = forecast.getJSONArray("forecastday");
        JSONObject forecastObj = forecastDays.getJSONObject(dayIndex - 1);
        JSONObject dayObj = forecastObj.getJSONObject("day");
        return dayObj.getDouble("avgtemp_f");
    }

    public static String getForecastDayIconCode(int dayIndex) {
        if (weatherData == null) {
            return ""; 
        }
        JSONObject forecast = weatherData.getJSONObject("forecast");
        JSONArray forecastDays = forecast.getJSONArray("forecastday");
        JSONObject forecastObj = forecastDays.getJSONObject(dayIndex - 1);
        JSONObject dayObj = forecastObj.getJSONObject("day");
        JSONObject conditionObj = dayObj.getJSONObject("condition");
        return ""+conditionObj.getInt("code");
    }

    public static String getForecastDayIconText(int dayIndex) {
        if (weatherData == null) {
            return ""; 
        }
        JSONObject forecast = weatherData.getJSONObject("forecast");
        JSONArray forecastDays = forecast.getJSONArray("forecastday");
        JSONObject forecastObj = forecastDays.getJSONObject(dayIndex - 1);
        JSONObject dayObj = forecastObj.getJSONObject("day");
        JSONObject conditionObj = dayObj.getJSONObject("condition");
        return conditionObj.getString("text");
    }

    public static double getForecastDayWindSpeedKph(int dayIndex) {
        if (weatherData == null) {
            return Double.NaN; 
        }
        JSONObject forecast = weatherData.getJSONObject("forecast");
        JSONArray forecastDays = forecast.getJSONArray("forecastday");
        JSONObject forecastObj = forecastDays.getJSONObject(dayIndex - 1);
        JSONObject dayObj = forecastObj.getJSONObject("day");
        return dayObj.getDouble("maxwind_kph");
    }
    
    public static double getCurrentTempC() {
        if (weatherData == null) {
            return Double.NaN; 
        }
        JSONObject current = weatherData.getJSONObject("current");
        return current.getDouble("temp_c");
    }
    
    

    public static double getCurrentTempF() {
        if (weatherData == null) {
            return Double.NaN; 
        }
        JSONObject current = weatherData.getJSONObject("current");
        return current.getDouble("temp_f");
    }
    
    
    
    public static String getForecastDay(int dayIndex) {
        if (weatherData == null) {
            return ""; 
        }        
        if (dayIndex == 1) {
            return "Tomorrow";
        } else if (dayIndex >= 2 && dayIndex <= 3) {
            JSONObject forecast = weatherData.getJSONObject("forecast");
            JSONArray forecastDays = forecast.getJSONArray("forecastday");
            
            if (dayIndex > forecastDays.length()) {
                return ""; 
            }
            
            JSONObject forecastObj = forecastDays.getJSONObject(dayIndex - 1);
            String dateStr = forecastObj.getString("date");
            LocalDate date = LocalDate.parse(dateStr);
            
            // Get the day of the week name
            return date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        }
        
        return "";
    }
    
    

    public static boolean isDayTime() {
        if (weatherData == null) {
            return false; 
        }
        JSONObject current = weatherData.getJSONObject("current");
        return current.getInt("is_day") == 1;
    }

    
    
    public static String getCurrentConditionText() {
        if (weatherData == null) {
            return ""; 
        }
        JSONObject current = weatherData.getJSONObject("current");
        JSONObject condition = current.getJSONObject("condition");
        return condition.getString("text");
    }

    
    
    public static double getCurrentWindSpeedKph() {
        if (weatherData == null) {
            return Double.NaN; 
        }
        JSONObject current = weatherData.getJSONObject("current");
        return current.getDouble("wind_kph");
    }
    
    
    
    public static String getCurrentFeelslike() {
        if (weatherData == null) {
            return "";
        }
        JSONObject current = weatherData.getJSONObject("current");
        return ""+current.getDouble("feelslike_c");
    }
    
    

    public static String getCurrentWindDirection() {
        if (weatherData == null) {
            return ""; 
        }
        JSONObject current = weatherData.getJSONObject("current");
        return current.getString("wind_dir");
    }
    
    

    public static int getCurrentHumidity() {
        if (weatherData == null) {
            return 0; 
        }
        JSONObject current = weatherData.getJSONObject("current");
        return current.getInt("humidity");
    }
    
    
    
    public static String getCurrentIconCode() {
        if (weatherData == null) {
            return ""; 
        }
        JSONObject current = weatherData.getJSONObject("current");
        JSONObject condition = current.getJSONObject("condition");
        return "" + condition.getInt("code");
    }
    
    
    
    public static String getCurrentLocation() {
        if (weatherData == null) {
            return "";
        }
        JSONObject location = weatherData.getJSONObject("location");
        String name = location.getString("name");
        String region = location.getString("region");
        String country = location.getString("country");
        return  name+", "+region+", "+country;
    }
    

}
