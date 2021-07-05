package jp.co.etc.benesse.crawler;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import jp.co.etc.benesse.value.Info;
import jp.co.etc.benesse.value.Site;
import jp.co.etc.benesse.value.Topic;

public class Yahoo implements Crowlerable {

	private List<Info> todayNews = new ArrayList<>();

	public static final String URL = "https://news.yahoo.co.jp";

	public List<Info> getTodayNews() {
		return todayNews;
	}

	@Override
	public void executeCrowing() {

		try {
			Document document = Jsoup.connect(URL).get();
			Element topics = document.getElementById("snavi");
			Elements lists = topics.select("ul").get(0).select("li");

			for (Element list : lists) {
				String getInfoUrl = URL + list.select("a").attr("href");
				String topic = list.select("a").text();

				this.collectingNews(getInfoUrl, topic);

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	private void collectingNews(String getInfoUrl, String topic) {
		try {
			Document document = Jsoup.connect(getInfoUrl).get();
			Element newsBlock = document.getElementById("uamods-topics");

			Elements news = newsBlock.select("li");

			Info info = null;
			for (Element element : news) {
				info = new Info();

				info.setSite(new Site("Yahoo"));
				info.setTitle(element.select("a").text());
				info.setUrl(element.select("a").attr("href"));
				Date date = Date.valueOf(LocalDate.now());
				info.setDate(date);
				String content = this.getContent(info.getUrl());
				info.setContent(content);
				info.setTopic(new Topic(topic));

				this.todayNews.add(info);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	private String getContent(String url2) {
		try {
			Document document = Jsoup.connect(url2).get();

			String content = document
					.getElementsByClass("highLightSearchTarget").get(0).html();
			return content;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
