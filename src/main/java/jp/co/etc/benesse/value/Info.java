package jp.co.etc.benesse.value;

import java.awt.Color;
import java.sql.Date;

public class Info {

	/**
	 * 登録されたニュースを一意に識別するID.
	 */
	private int id;
	/**
	 * ニュースのURLを表す.
	 */
	private String url;
	/**
	 * ニュースのタイトルを表す.
	 */
	private String title;
	/**
	 * ニュース内容を格納する.
	 */
	private String content;
	/**
	 * ニュースが発行された日付を格納する.
	 */
	private Date date;
	/**
	 * このニュースが何のトピックかを表します.
	 */
	private Topic topic;
	/**
	 * どのサイトから得られたニュースかを表す。
	 */
	private Site site;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	public Site getSite() {
		return site;
	}
	public void setSite(Site site) {
		this.site = site;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
	    if (obj instanceof Color) {
			Info b = (Info)obj;
			if(this.url.equals(b.url)) {
				return true; // 実行される
			}
			return false;
		}
		return false;
	}
}
