package jp.co.etc.benesse.value;

public class Topic {

	/**
	 * トピックを識別するIDである.
	 */
	private int topicID;

	/**
	 * トピック名を表す.
	 */
	private String topic;

	public int getTopicID() {
		return topicID;
	}

	public Topic(int topicID, String topic) {
		this.topicID = topicID;
		this.topic = topic;
	}

	public Topic(int topicID) {
		this.topicID = topicID;
	}

	public Topic(String topic) {
		this.topic = topic;
	}

	public Topic() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public void setTopicID(int topicID) {
		this.topicID = topicID;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}
}
