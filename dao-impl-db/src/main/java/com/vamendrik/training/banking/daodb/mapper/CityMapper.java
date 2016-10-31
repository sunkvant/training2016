package com.vamendrik.training.banking.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.vamendrik.training.banking.datamodel.City;

public class CityMapper implements RowMapper<City> {

	@Override
	public City mapRow(ResultSet rs, int arg1) throws SQLException {
		City city=new City();
		city.setId(rs.getLong("id"));
		city.setCoutryId(rs.getLong("country_id"));
		city.setCityName(rs.getString("city_name"));
		return city;
	}

}
