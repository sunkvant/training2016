package com.vamendrik.training.banking.daodb.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vamendrik.training.banking.daoapi.IRoleDao;
import com.vamendrik.training.banking.daodb.impl.abstractions.AbstractDaoImpl;
import com.vamendrik.training.banking.datamodel.Role;
import com.vamendrik.training.banking.datamodel.annotations.Entity;

@Repository
@Entity(object = "Role", table = "role")
public class RoleDaoImpl extends AbstractDaoImpl<Role, Long> implements IRoleDao {

	@Override
	public void delete(Role entity) {
		this.jdbcTemplate.update("update role set is_delete=true where id=?", entity.getId());

	}

	@Override
	public Role getByRoleType(String roleType) {

		return this.getByFieldValue("select * from role where role_type=?", new Object[] { roleType });

	}

	@Override
	public List<Role> getAllByUserId(Long userId) {

		return this.getAllByFieldValue(
				"SELECT public.role.* FROM public.role join public.user_to_role on  public.user_to_role.role_id=public.role.id join public.\"user\" on public.user_to_role.user_id= public.\"user\".id where public.\"user\".id=? order by public.role.id asc",
				new Object[] { userId });

	}

}
