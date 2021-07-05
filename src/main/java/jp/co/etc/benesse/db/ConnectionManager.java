package jp.co.etc.benesse.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	private Connection connection;

	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("ドライバのロードに失敗しました。", e);
		}
	}

	public Connection getConnection() {
		if(this.connection == null) {
			try {
				String url = "jdbc:postgresql://localhost:5432/news_scrayping";
				String user = "mitsumizo_news";
				String password = "Yamaguchi.eiji5752";

				this.connection = DriverManager.getConnection(url, user, password);
				this.connection.setAutoCommit(false);
			} catch (SQLException e) {
				throw new RuntimeException("データベースの接続に失敗しました。", e);
			}
		}
		return connection;
	}

	public void closeConnection() {
		try {
			if (this.connection != null) {
				this.connection.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException("データベースの切断に失敗しました。", e);
		}
	}

	public void commit() {
		try {
			if(this.connection != null) {
				this.connection.commit();
			}
		} catch (SQLException e) {
			throw new RuntimeException("トランザクションのコミットに失敗しました。", e);
		}
	}

	public void rollback() {
		try {
			if(this.connection != null) {
				this.connection.rollback();
			}
		} catch (SQLException e) {
			throw new RuntimeException("トランザクションのロールバックに失敗しました。", e);
		}
	}
}
