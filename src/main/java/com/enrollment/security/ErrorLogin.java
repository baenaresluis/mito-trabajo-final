package com.enrollment.security;

import java.util.Date;

public class ErrorLogin {

	
	private String mensaje;
	private Date timestamp;
	
	public ErrorLogin(String mensaje, Date timesstamp) {
		this.mensaje = mensaje;
		this.timestamp = timesstamp;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
}
