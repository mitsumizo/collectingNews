package jp.co.etc.benesse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jp.co.etc.benesse.value.Topic;

public class TopicDao {
	private Connection connection;

	public TopicDao(Connection connection) {
		this.connection = connection;
	}

	public List<Topic> selectAll() {
		List<Topic> topics = new ArrayList<Topic>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			String sql = "Select topicid, topic from topic";
			preparedStatement = connection.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();

			Topic topic = null;
			while(resultSet.next()) {
				topic = new Topic();

				topic.setTopicID(resultSet.getInt(1));
				topic.setTopic(resultSet.getString(2));

				topics.add(topic);
			}

			return topics;

		} catch (Exception e) {
			throw new RuntimeException("TopciのSelectに失敗しました。", e);
		}
	}
}
