package au.com.phoenixhsl.api.matches.exception;

public class ApiException extends RuntimeException {

	private static final long serialVersionUID = -292406186628505542L;

	private Integer status;

	public ApiException(int status, String message) {

		super(message);
		this.status = status;
	}

	public int getStatus() {
		return status;
	}
}