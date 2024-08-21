package com.mycompany.weatherapp;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.json.JSONObject;

public class GetIpAddress {
    public static String getIpAddress() {
        String ipAddress = null;
        try {
            HttpResponse<JsonNode> response = Unirest.get("https://api.ipify.org?format=json").asJson();
            if (response.getStatus() == 200) {
                JSONObject responseBody = response.getBody().getObject();
                ipAddress = responseBody.getString("ip");
            } else {
                System.err.println("Failed to get IP address: " + response.getStatusText());
            }
        } catch (Exception e) {
            System.err.println("Exception occurred: " + e);
        }
        return ipAddress;
    }
}
