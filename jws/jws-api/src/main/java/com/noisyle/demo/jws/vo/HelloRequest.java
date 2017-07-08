package com.noisyle.demo.jws.vo;

public class HelloRequest {

	private User user = null;

	public void setUser(HelloRequest.User user) {
		this.user = user;
	}

	public HelloRequest.User getUser() {
		return this.user;
	}

	public static class User {
		private String name = null;

		public void setName(String name) {
			this.name = name;
		}

		public String getName() {
			return this.name;
		}
	}

}
