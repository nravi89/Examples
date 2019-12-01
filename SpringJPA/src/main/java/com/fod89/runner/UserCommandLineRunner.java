package com.fod89.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fod89.dao.UserDAO;
import com.fod89.entity.User;
import com.fod89.repository.UserRepository;

@Component
public class UserCommandLineRunner implements CommandLineRunner{
	
	private static Logger logger = LoggerFactory.getLogger(UserCommandLineRunner.class);

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public void run(String... args) throws Exception {
		User u1 = new User("Ravi","Manager");
		userRepo.save(u1);
		logger.info("User saved."+u1);
	}

}
