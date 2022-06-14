package com.study.www.book.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import com.study.www.book.dto.InterParkBookResponse;

@Service
public class InterParkAPIService {
	@Value("${interpark.book.key}")
	private String key;

	public List<InterParkBookResponse> search(String query) {
		List<InterParkBookResponse> list = null;
		try {
			URL url;
			url = new URL("https://book.interpark.com/api/"
				+ "search.api?query="
				+ URLEncoder.encode(query, "UTF-8")
				+ "&key=" + key);

			URLConnection urlConn = url.openConnection();

			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = factory.newPullParser();
			parser.setInput(
				new InputStreamReader(urlConn.getInputStream()));

			int eventType = parser.getEventType();
			InterParkBookResponse b = null;
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
					case XmlPullParser.END_DOCUMENT:
						break;
					case XmlPullParser.START_DOCUMENT:
						list = new ArrayList<InterParkBookResponse>();
						break;
					case XmlPullParser.END_TAG: {
						String tag = parser.getName();
						if (tag.equals("item")) {
							list.add(b);
							b = null;
						}
					}
					case XmlPullParser.START_TAG: {
						String tag = parser.getName();
						switch (tag) {
							case "item":
								b = new InterParkBookResponse();
								break;
							case "title":
								if (b != null)
									b.setName(parser.nextText());
								break;
							case "description":
								if (b != null)
									b.setDescription(parser.nextText());
								break;
							case "pubDate":
								if (b != null)
									b.setPublishedDate(parser.nextText());
								break;
							case "priceStandard":
								if (b != null)
									b.setPrice(Integer.parseInt(parser.nextText()));
								break;
							case "coverLargeUrl":
								if (b != null)
									b.setImage(parser.nextText());
								break;
							case "categoryId":
								try {
									if (b != null)
										b.setCategoryId(Long.parseLong(parser.nextText()));
								} catch (Exception e) {}
								break;
							case "publisher":
								if (b != null)
									b.setPublisher(parser.nextText());
								break;
							case "author":
								if (b != null)
									b.setAuthor(parser.nextText());
								break;
							case "isbn":
								if (b != null)
									b.setIsbn(parser.nextText());
								break;
						}

					}
				}
				eventType = parser.next();
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}

		return list;
	}
}
