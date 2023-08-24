package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
public class ChatGPTAPIExample {

    public static void listTokens() {
        try {
//This API will fetch the models available.
            URL url = new URL("https://api.openai.com/v1/models");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
//Make sure you put the right Organization key saved earlier.
            con.setRequestProperty("OpenAI-Organization", "org-xxxx"); // DO NOT SHARE - DO NOT HARDCODE
            con.setDoOutput(true);
//Make sure you put the right API Key saved earlier.
            con.setRequestProperty("Authorization", "Bearer sk-xxxx"); // DO NOT SHARE - DO NOT HARDCODE
            int responseCode = con.getResponseCode();
            System.out.println("Response Code : " + responseCode);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println(response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void prompts() {
        try {
            URL url = new URL("https://api.openai.com/v1/completions");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
//Make sure you put the right Organization key saved earlier.
            con.setRequestProperty("OpenAI-Organization", "org-xxxxxx"); // DO NOT SHARE - DO NOT HARDCODE
            con.setDoOutput(true);
//Make sure you put the right API Key saved earlier.
            con.setRequestProperty("Authorization", "Bearer sk-xxxxxx"); // DO NOT SHARE - DO NOT HARDCODE
//Make sure to relace the path of the json file created earlier.
            String jsonInputString = FileHelper.readLinesAsString(new File("src/com/company/Request.json"));
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
            int responseCode = con.getResponseCode();
            System.out.println("Response Code : " + responseCode);
            System.out.println("Response Message : "+con.getResponseMessage());
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println(response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
        listTokens();
        prompts();
    }
}