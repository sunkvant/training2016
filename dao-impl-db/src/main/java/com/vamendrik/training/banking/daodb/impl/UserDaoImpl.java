package com.vamendrik.training.banking.daodb.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.vamendrik.training.banking.daoapi.IUserDao;
import com.vamendrik.training.banking.daodb.impl.abstractions.AbstractDaoImpl;
import com.vamendrik.training.banking.datamodel.User;
import com.vamendrik.training.banking.datamodel.annotations.Entity;

@Repository
@Entity(object = "User", table = "\"user\"")
public class UserDaoImpl  extends AbstractDaoImpl<User, Long> implements IUserDao {

	@Inject
	private JdbcTemplate jdbcTemplate;



	@Override
	public void delete(User entity) {
		jdbcTemplate.update("update \"user\" set is_delete=true where id=?", entity.getId());
	}

	@Override
	public List<User> getAllByRoleId(Long roleId) {
		
		return this.getAllByFieldValue("SELECT public.\"user\".* FROM public.\"user\" join public.user_to_role on  public.\"user\".id=public.user_to_role.user_id join public.role on public.user_to_role.role_id=public.role.id where public.role.id=?",
				 new Object[] { roleId });
	}

	@Override
	public List<User> getAllByCityId(Long cityId) {
		return this.getAllByFieldValue("SELECT public.\"user\".* FROM public.\"user\" join public.city on public.\"user\".city_id=public.city.id where public.city.id=?",
				 new Object[] { cityId });
		
	}

	@Override
	public List<User> getAllByCountryId(Long countryId) {
		
		return this.getAllByFieldValue("SELECT public.\"user\".* FROM public.\"user\" join public.city on public.\"user\".city_id=public.city.id join public.country on public.city.country_id=public.country.id where public.country.id=?",
				 new Object[] { countryId });

	}

	@Override
	public User getByNumberPassport(String numberOfPassport) {
		return this.getByFieldValue("select * from \"user\" where number_of_passport=?", new Object[] { numberOfPassport });

	}

	@Override
	public User getByLogin(String login) {
		
		return this.getByFieldValue("select * from \"user\" where login=?", new Object[] { login });

	}

}
