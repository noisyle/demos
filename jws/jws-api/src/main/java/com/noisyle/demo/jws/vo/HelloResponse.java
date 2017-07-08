package com.noisyle.demo.jws.vo;

public class HelloResponse {

	private Result result = null;

	public void setResult(HelloResponse.Result result) {
		this.result = result;
	}

	public HelloResponse.Result getResult() {
		return this.result;
	}

	public static class Result {
		private String message = null;

		public void setMessage(String message) {
			this.message = message;
		}

		public String getMessage() {
			return this.message;
		}
	}

}
