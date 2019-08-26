package ui.messagedialogs;

import java.sql.SQLException;

public class ExceptionDialogs extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 12L;
	private final SQLException code;


	public ExceptionDialogs(String string, SQLException e) {
		super(string);
		this.code = e;
	}
	
	public SQLException getCode() {
		return this.code;
	}
}
