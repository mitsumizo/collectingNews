package jp.co.etc.benesse.value;

public class Site {

	/**
	 * サイトの種類を識別するIDである.
	 */
	private int siteID;

	/**
	 * サイト名を表す.
	 */
	private String siteName;

	public Site(int siteID, String siteName) {
		this.siteID = siteID;
		this.siteName = siteName;
	}

	public Site(int siteID) {
		this.siteID = siteID;
	}

	public Site(String siteName) {
		this.siteName = siteName;
	}

	public Site() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public int getSiteID() {
		return siteID;
	}

	public void setSiteID(int siteID) {
		this.siteID = siteID;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

}
