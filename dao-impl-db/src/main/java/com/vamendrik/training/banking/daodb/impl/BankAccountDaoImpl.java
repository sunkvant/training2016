package com.vamendrik.training.banking.daodb.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vamendrik.training.banking.daoapi.IBankAccountDao;
import com.vamendrik.training.banking.daodb.impl.abstractions.AbstractDaoImpl;
import com.vamendrik.training.banking.datamodel.BankAccount;
import com.vamendrik.training.banking.datamodel.annotations.Entity;

@Repository
@Entity(object = "BankAccount", table = "bank_account")
public class BankAccountDaoImpl extends AbstractDaoImpl<BankAccount, Long> implements IBankAccountDao {

	@Override
	public BankAccount getLast() {
		
		return this.getByFieldValue("select * from bank_account where is_delete=false ORDER BY id DESC LIMIT 1", null);
		
	}

	@Override
	public void delete(BankAccount entity) {
		this.jdbcTemplate.update("update bank_account set is_delete=true where id=?", entity.getId());

	}

	@Override
	public List<BankAccount> getAllByUserId(Long userId) {

		return this.getAllByFieldValue(
				"SELECT public.bank_account.* FROM public.bank_account join public.\"user\" on public.bank_account.user_id=public.\"user\".id where public.\"user\".id=? and public.\"user\".is_delete=false and public.bank_account.is_delete=false order by public.bank_account.id asc",
				new Object[] { userId });

	}

	@Override
	public BankAccount getByNumberAccount(Long numberAccount) {
		return this.getByFieldValue("select * from bank_account where number_account=?", new Object[] { numberAccount });
	}

}
