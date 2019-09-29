package com.goneat.goneat.io.entity;

import javax.persistence.*;

@Entity(name="city")
public class CityEntity {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long cityId;
	
	@Column
	private String cityName;
	
	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

}
