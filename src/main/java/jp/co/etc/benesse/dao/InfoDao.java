package jp.co.etc.benesse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.etc.benesse.value.Info;
import jp.co.etc.benesse.value.Site;
import jp.co.etc.benesse.value.Topic;

public class InfoDao {

	private Connection connection;

	public InfoDao(Connection connection) {
		this.connection = connection;
	}

	public List<Info> selectAll() {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Info> allNews = new ArrayList<>();
		try {
			final String sql = "SELECT i.id, i.url, i.title, i.content, i.date, i.topicid, t.topic, i.siteid, s.siteName"
					+ " from Info as i INNER JOIN Topic as t"
					+ " ON i.topicid = t.topicid INNER JOIN SITE as s"
					+ " ON i.siteid = s.siteID;";
			preparedStatement = this.connection.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();

			Info tmpInfo = null;
			while (resultSet.next()) {
				tmpInfo = new Info();

				tmpInfo.setId(resultSet.getInt(1));
				tmpInfo.setUrl(resultSet.getString(2));
				tmpInfo.setTitle(resultSet.getString(3));
				tmpInfo.setContent(resultSet.getString(4));
				tmpInfo.setDate(resultSet.getDate(5));
				tmpInfo.setTopic(
						new Topic(resultSet.getInt(6), resultSet.getString(7)));
				tmpInfo.setSite(
						new Site(resultSet.getInt(8), resultSet.getString(9)));

				allNews.add(tmpInfo);
			}

			return allNews;
		} catch (SQLException e) {
			throw new RuntimeException("SELECTで失敗しました。", e);
		}
	}

	public int insert(Info info) {
		PreparedStatement preparedStatement = null;
		try {
			final String sql = "INSERT INTO Info (url, title, content, date, topicid, siteid)"
					+ " VALUES (?,?,?,?,?,?)";
			preparedStatement = this.connection.prepareStatement(sql);

			preparedStatement.setString(1, info.getUrl());
			preparedStatement.setString(2, info.getTitle());
			preparedStatement.setString(3, info.getContent());
			preparedStatement.setDate(4, info.getDate());
			preparedStatement.setInt(5, info.getTopic().getTopicID());
			preparedStatement.setInt(6, info.getSite().getSiteID());

			int result = preparedStatement.executeUpdate();
			return result;
		} catch (SQLException e) {
			throw new RuntimeException("SELECTで失敗しました。", e);
		}
	}

	public List<String> getUrlList() {
		List<String> urls = new ArrayList<String>();
		PreparedStatement preparedStatement = null;
		try {
			String sql = "Select url from info";
			preparedStatement = connection.prepareStatement(sql);

			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next()) {
				urls.add(resultSet.getString(1));
			}

			return urls;
 		} catch (Exception e) {
			throw new RuntimeException(e);
		}
 	}

}
