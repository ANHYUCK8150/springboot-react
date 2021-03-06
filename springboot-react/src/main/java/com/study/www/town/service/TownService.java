package com.study.www.town.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.www.town.dto.CoordinateDto;
import com.study.www.town.dto.TownDto;
import com.study.www.town.repository.TownRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TownService {
	private final TownRepository townRepository;

	private String apiurl = "https://naveropenapi.apigw.ntruss.com/map-reversegeocode/v2/gc";
	@Value("${naver.map.clientId}")
	private String clientId;
	@Value("${naver.map.clientSecret}")
	private String clientKey;

	public TownDto ReverseGeocoding(CoordinateDto coordinateDto) {

		String api = apiurl + "?coords=" + coordinateDto.getLongitude() + "," + coordinateDto.getLatitude()
			+ "&orders=legalcode&output=json";
		StringBuffer sb = new StringBuffer();
		TownDto townDto = new TownDto();

		System.out.println("clientId:" + clientId + " , clientKey : " + clientKey);

		try {
			URL url = new URL(api);
			HttpsURLConnection http = (HttpsURLConnection)url.openConnection();
			http.setRequestProperty("Content-Type", "application/json");
			http.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
			http.setRequestProperty("X-NCP-APIGW-API-KEY", clientKey);
			http.setRequestMethod("GET");
			http.connect();

			InputStreamReader in = new InputStreamReader(http.getInputStream(), "utf-8");
			BufferedReader br = new BufferedReader(in);

			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line).append("\n");
			}

			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject)parser.parse(sb.toString());
			JSONArray jsonArray = (JSONArray)jsonObject.get("results");
			JSONObject jsonResult = (JSONObject)parser.parse(jsonArray.get(0).toString());

			//TownID?????? ?????????.
			JSONObject jsonCode = (JSONObject)jsonResult.get("code");
			townDto.setId(Long.parseLong(jsonCode.get("id").toString()));

			//??????
			JSONObject jsonRegion = (JSONObject)jsonResult.get("region");

			//??????
			JSONObject jsonCity = (JSONObject)jsonRegion.get("area1");
			townDto.setCity(jsonCity.get("name").toString());

			//?????????
			JSONObject jsonDistrict = (JSONObject)jsonRegion.get("area2");
			townDto.setDistrict(jsonDistrict.get("name").toString());

			//?????????
			JSONObject jsonName = (JSONObject)jsonRegion.get("area3");
			townDto.setName(jsonName.get("name").toString());

			//??????
			JSONObject jsonEtc = (JSONObject)jsonRegion.get("area4");
			townDto.setEtc(jsonEtc.get("name").toString());

			townDto.setAdress(townDto.getCity(), townDto.getDistrict(), townDto.getName(), townDto.getEtc());

			br.close();
			in.close();
			http.disconnect();

		} catch (IOException e) {} catch (ParseException e) {}

		return townDto;
	}
}
