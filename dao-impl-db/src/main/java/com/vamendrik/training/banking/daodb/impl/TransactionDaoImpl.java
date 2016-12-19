package com.vamendrik.training.banking.daodb.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vamendrik.training.banking.daoapi.ITransactionDao;
import com.vamendrik.training.banking.daodb.impl.abstractions.AbstractDaoImpl;
import com.vamendrik.training.banking.datamodel.Transaction;
import com.vamendrik.training.banking.datamodel.annotations.Entity;

@Repository
@Entity(object = "Transaction", table = "transaction")
public class TransactionDaoImpl extends AbstractDaoImpl<Transaction, Long> implements ITransactionDao {

	@Override
	public void delete(Transaction entity) {
		this.jdbcTemplate.update("delete from transaction where id=?", entity.getId());

	}

	@Override
	public List<Transaction> getAllByCreditCardId(Long creditCardId) {

		return this.getAllByFieldValue(
				"SELECT public.transaction.* FROM public.transaction  join public.credit_card on public.transaction.credit_card_id=public.credit_card.id where public.credit_card.id=? order by public.transaction.id asc",
				new Object[] { creditCardId });

	}

	@Override
	public List<Transaction> getAllByBankAccountId(Long bankAccountId) {
		
		return this.getAllByFieldValue("SELECT public.transaction.* FROM public.transaction join public.credit_card on public.transaction.credit_card_id=public.credit_card.id join public.bank_account on public.credit_card.bank_account_id=public.bank_account.id where public.bank_account.id=? order by public.transaction.id asc", new Object[] { bankAccountId });
		
	}

	@Override
	public List<Transaction> getAllByUserId(Long userId) {
		
		return this.getAllByFieldValue("SELECT public.transaction.* FROM public.transaction join public.credit_card on public.transaction.credit_card_id=public.credit_card.id join public.bank_account on public.credit_card.bank_account_id=public.bank_account.id join public.\"user\" on public.bank_account.user_id=public.\"user\".id where public.\"user\".id=? order by public.transaction.id asc", new Object[] { userId });
		
	}

}
