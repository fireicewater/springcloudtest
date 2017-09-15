package com.piggymetrics.account.service;

import com.piggymetrics.account.domain.Account;
import com.piggymetrics.account.domain.Currency;
import com.piggymetrics.account.domain.Saving;
import com.piggymetrics.account.domain.User;
import com.piggymetrics.account.repository.AccountRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class AccountServiceImpl implements AccountService {

    private final Logger log = LoggerFactory.getLogger(getClass());

//	@Autowired
//	private StatisticsServiceClient statisticsClient;
//
//	@Autowired
//	private AuthServiceClient authClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AccountRepository repository;

    @Autowired
    private UserService userService;

    /**
     * {@inheritDoc}
     */
    @Override
    public Account findByName(String accountName) {
        Assert.hasLength(accountName);
        return repository.findByName(accountName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Account create(User user) {

        Account existing = repository.findByName(user.getUsername());
        Assert.isNull(existing, "account already exists: " + user.getUsername());

//		authClient.createUser(user);
        userService.create(user);
        Saving saving = new Saving();
        saving.setAmount(new BigDecimal(0));
        saving.setCurrency(Currency.getDefault());
        saving.setInterest(new BigDecimal(0));
        saving.setDeposit(false);
        saving.setCapitalization(false);

        Account account = new Account();
        account.setName(user.getUsername());
        account.setLastSeen(new Date());
        account.setSaving(saving);

        repository.save(account);

        log.info("new account has been created: " + account.getName());

        return account;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveChanges(String name, Account update) {

        Account account = repository.findByName(name);
        Assert.notNull(account, "can't find account with name " + name);

        account.setIncomes(update.getIncomes());
        account.setExpenses(update.getExpenses());
        account.setSaving(update.getSaving());
        account.setNote(update.getNote());
        account.setLastSeen(new Date());
        repository.save(account);
        log.debug("account {} changes has been saved", name);
//		/statistics/{accountName}
        restTemplate.put("", account, name);
//		statisticsClient.updateStatistics(name, account);
    }
}
