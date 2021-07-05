package jp.co.etc.benesse.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jp.co.etc.benesse.crawler.Yahoo;
import jp.co.etc.benesse.dao.InfoDao;
import jp.co.etc.benesse.dao.SiteDao;
import jp.co.etc.benesse.dao.TopicDao;
import jp.co.etc.benesse.db.ConnectionManager;
import jp.co.etc.benesse.value.Info;
import jp.co.etc.benesse.value.Site;
import jp.co.etc.benesse.value.Topic;

public class InfoService {

	public List<Info> collectingTodayInfo() {
		// yahooのニュースを集めてくる。
		Yahoo yahoo = new Yahoo();
		yahoo.executeCrowing();
		List<Info> todayNews = yahoo.getTodayNews();

		// 同じデータを削除する作業
		ConnectionManager connectionManager = null;
		try {
			connectionManager = new ConnectionManager();
			Connection connection = connectionManager.getConnection();

			InfoDao infoDao = new InfoDao(connection);

			List<String> urls = infoDao.getUrlList();

			List<Info> copyInfos = new ArrayList<Info>(todayNews);

			for (int index = 0; index < todayNews.size(); index++) {
				for (int index_j = 0; index_j < urls.size(); index_j++) {
					if (urls.get(index_j)
							.equals(todayNews.get(index).getUrl())) {
						copyInfos.remove(
								copyInfos.indexOf(todayNews.get(index)));
						break;
					}
				}
			}

			// TODO : categoryのセット
			TopicDao topicDao = new TopicDao(connection);
			List<Topic> topics = topicDao.selectAll();

			for(Info info : copyInfos) {
				for(Topic compareTopic : topics) {
					if(info.getTopic().getTopic().equals(compareTopic.getTopic())) {
						info.getTopic().setTopicID(compareTopic.getTopicID());
						break;
					}
				}
			}

			SiteDao siteDao = new SiteDao(connection);
			List<Site> sites = siteDao.selectAll();

			for(Info info : copyInfos) {
				for(Site comparesite : sites) {
					if(info.getSite().getSiteName().equals(comparesite.getSiteName())) {
						info.getSite().setSiteID(comparesite.getSiteID());
						break;
					}
				}
			}

			for(Info insertInfo : copyInfos) {
				System.out.println("-------------------");
				System.out.println(insertInfo.getTitle());
				System.out.println(insertInfo.getUrl());
				System.out.println(insertInfo.getTopic().getTopicID() + " : " + insertInfo.getTopic().getTopic());
				System.out.println(insertInfo.getSite().getSiteID() + " : " + insertInfo.getSite().getSiteName());
				System.out.println("-------------------");
			}

			List<Info> newInfos = copyInfos.stream().distinct().collect(Collectors.toList());


			for(Info insertInfo : newInfos) {
				infoDao.insert(insertInfo);
			}

			connectionManager.commit();

			return copyInfos;

		} catch (Exception e) {
			connectionManager.rollback();
			throw new RuntimeException(e);
		}
	}
}
