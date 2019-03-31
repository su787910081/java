package ioc;

public class JdbcTemplate {
	private DataSource dataSource;
	
	public void setDataSource(
			DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public DataSource getDataSource() {
		return dataSource;
	}
}
