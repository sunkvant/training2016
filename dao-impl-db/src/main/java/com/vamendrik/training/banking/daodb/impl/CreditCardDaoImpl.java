package com.vamendrik.training.banking.daodb.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vamendrik.training.banking.daoapi.ICreditCardDao;
import com.vamendrik.training.banking.daodb.impl.abstractions.AbstractDaoImpl;
import com.vamendrik.training.banking.datamodel.CreditCard;
import com.vamendrik.training.banking.datamodel.annotations.Entity;

@Repository
@Entity(object = "CreditCard", table = "credit_card")
public class CreditCardDaoImpl extends AbstractDaoImpl<CreditCard, Long> implements ICreditCardDao {


	@Override
	public CreditCard getLast() {

		return this.getByFieldValue("select * from credit_card ORDER BY id DESC LIMIT 1", null);

	}

	@Override
	public void delete(CreditCard entity) {
		this.jdbcTemplate.update("update credit_card set is_delete=true where id=?", entity.getId());

	}

	@Override
	public List<CreditCard> getAllByNumberBankAccount(Long numberAccount) {

		return this.getAllByFieldValue(
				"SELECT public.credit_card.* from public.credit_card join public.bank_account on public.credit_card.bank_account_id=public.bank_account.id where public.bank_account.number_account=? order by public.credit_card.id asc",
				new Object[] { numberAccount });

	}

	@Override
	public CreditCard getByNumberCard(Long numberCard) {

		return this.getByFieldValue("select * from credit_card where number_card=?", new Object[] { numberCard });

	}

	@Override
	public List<CreditCard> getAllByUserId(Long userId) {

		return this.getAllByFieldValue(
				"select public.credit_card.* from public.credit_card join public.bank_account on public.credit_card.bank_account_id=public.bank_account.id join public.\"user\" on public.bank_account.user_id=public.\"user\".id where public.\"user\".id=? order by public.credit_card.id asc",
				new Object[] { userId });

	}
}
