package jp.co.etc.benesse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jp.co.etc.benesse.value.Site;

public class SiteDao {

	private Connection connection;

	public SiteDao(Connection connection) {
		this.connection = connection;
	}

	public List<Site> selectAll() {
		List<Site> sites = new ArrayList<Site>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			String sql = "Select siteid, sitename from site";
			preparedStatement = connection.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();

			Site site = null;
			while(resultSet.next()) {
				site = new Site();

				site.setSiteID(resultSet.getInt(1));
				site.setSiteName(resultSet.getString(2));

				sites.add(site);
			}

			return sites;

		} catch (Exception e) {
			throw new RuntimeException("SiteのSelectに失敗しました。", e);
		}
	}
}
