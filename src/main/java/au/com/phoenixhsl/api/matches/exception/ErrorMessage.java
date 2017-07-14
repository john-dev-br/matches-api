package au.com.phoenixhsl.api.matches.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class ErrorMessage {

	private int status;
	private String message;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ErrorMessage(ApiException ex) {

		this.status = ex.getStatus();
		this.message = ex.getMessage();
	}

	public ErrorMessage(Throwable ex) {

		this.status = (ex instanceof WebApplicationException) ? 
				((WebApplicationException) ex).getResponse().getStatus() 
				: Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
		
		this.message = ex.getMessage();
	}
}