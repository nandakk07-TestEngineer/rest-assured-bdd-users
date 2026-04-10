package com.automation.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserListResponse {
	
	@JsonProperty("page")
	private int page;
	
	@JsonProperty("page_size")
	private int page_size;
	
	@JsonProperty("total")
	private int total;
	
	@JsonProperty("total_size")
	private int total_size;
	
	@JsonProperty("data")
	private List<User> data;
}
