package build;

import java.sql.ResultSet;
import java.util.Map;

public interface sqlDao {
	/**
	 * 
	 * @param sql
	 * @param values
	 * @return
	 */
	public ResultSet selecttexecute(String sql, Map<String,String> values);
	/**
	 * 
	 * @param sql
	 * @param valuse
	 * @return
	 */
	public int insertexecte(String sql, String[] valuse);
}
