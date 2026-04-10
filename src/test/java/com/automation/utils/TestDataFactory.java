package com.automation.utils;

import com.automation.models.User;
import com.github.javafaker.Faker;

public class TestDataFactory {

	private static final Faker faker = new Faker();
	
	private TestDataFactory() {}
	
	public static User createUserPayload() {
		return User.builder()
					.name(faker.name().fullName())
					.email(faker.internet().emailAddress())
					.age(faker.number().numberBetween(18, 60))
					.build();
	}
	
	public static User updateUserPayload() {
		return User.builder()
					.name(faker.name().fullName())
					.email(faker.internet().emailAddress())
					.age(faker.number().numberBetween(18,60))
					.job(faker.job().title())
					.build();
	}
	
	public static User CreateUserPayload(String name, int age, String job) {
		return User.builder()
					.name(name)
					.job(job)
					.age(age)
					.email(faker.internet().emailAddress())
					.build();
	}
	
	
}

