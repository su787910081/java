package beans;

public class JdbcTemplate {
	private DataSource dataSource;
	private int nNumber;
	
	public JdbcTemplate() {
	}

	public int getnNumber() {
		return nNumber;
	}

	public void setnNumber(int nNumber) {
		this.nNumber = nNumber;
	}

	public JdbcTemplate(DataSource dataSource, int nNumber) {
		super();
		this.dataSource = dataSource;
		this.nNumber = nNumber;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public String toString() {
		return "JdbcTemplate [dataSource=" + dataSource + ", nNumber=" + nNumber + "]";
	}

	

	
	
	
}
