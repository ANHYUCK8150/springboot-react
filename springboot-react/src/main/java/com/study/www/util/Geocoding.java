package com.study.www.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Geocoding {
	
	public void trans(String address) throws ParseException, UnsupportedEncodingException {

	     String addr = URLEncoder.encode(address,"utf-8");
	     String api = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query="+addr;
	     StringBuffer sb = new StringBuffer();

	     try {
	      URL url = new URL(api);
	      HttpsURLConnection http = (HttpsURLConnection)url.openConnection();
	      http.setRequestProperty("Content-Type", "application/json");
	      http.setRequestProperty("X-NCP-APIGW-API-KEY-ID", "zm0p3diwp9");
	      http.setRequestProperty("X-NCP-APIGW-API-KEY", "GSHzx52UKdBkcXiu6z3bNZ8TNgQgCFT3TelH7uXo");
	      http.setRequestMethod("GET");
	      http.connect();
	      
	      InputStreamReader in = new InputStreamReader(http.getInputStream(),"utf-8");
	      BufferedReader br = new BufferedReader(in);

	      String line;
	      while ((line = br.readLine()) != null) {
	       sb.append(line).append("\n");
	      }

	      JSONParser parser = new JSONParser();
	      JSONObject jsonObject;
	      JSONObject jsonObject2;
	      JSONArray jsonArray;
	      String x = "";
	      String y = "";

	     //트리형태로 온 JSON 파싱 :: 멋쟁이인중(saltkeeper) 블로그에서 도움 받음. 감사합니다^^
	      jsonObject = (JSONObject)parser.parse(sb.toString());
	      jsonArray = (JSONArray)jsonObject.get("addresses");
	      for(int i=0;i<jsonArray.size();i++){
	       jsonObject2 = (JSONObject) jsonArray.get(i);
	       if(null != jsonObject2.get("x")){
	        x = (String) jsonObject2.get("x").toString();
	       }
	       if(null != jsonObject2.get("y")){
	        y = (String) jsonObject2.get("y").toString();
	       }
	      }

	      br.close();
	      in.close();
	      http.disconnect();

	      System.out.println("Latitude >> " + y + "Longitude >> " + x);

	     } catch (IOException e) {
	      //알아서
	     }
	}
}
