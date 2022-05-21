package com.study.www;

import java.io.UnsupportedEncodingException;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.study.www.util.Geocoding;

@SpringBootTest
class SpringbootReactApplicationTests {

	@Test
	void contextLoads() throws UnsupportedEncodingException, ParseException {
		Geocoding geo = new Geocoding();
		
		geo.trans("분당구 불정로 6");
	}

}
