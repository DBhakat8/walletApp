package com.wallet.app.controller;

import com.wallet.app.dto.Wallet;
import com.wallet.app.exception.WalletException;
import com.wallet.app.exception.WalletRepositoryException;
import com.wallet.app.service.WalletService;
import com.wallet.app.service.WalletServiceImpl;

public class Controller {

	public static void main(String[] args) {
		
		WalletService walletService = new WalletServiceImpl();

		try{
		Wallet wallet = walletService.registerWallet(new Wallet(1, "Ford", 1000.0, "123"));
		System.out.println(wallet);

		System.out.println("Wallet id 1, balance=" + walletService.showWalletBalance(1));
		// System.out.println("Wallet id 2,balance="+walletService.showWalletBalance(2));
			Wallet wallet2 = walletService.registerWallet(new Wallet(2, "adarsh", 2000.0, "100"));
			Wallet wallet3 = walletService.registerWallet(new Wallet(3, "Rehaan", 3000.0, "151"));
			Wallet wallet4 = walletService.registerWallet(new Wallet(3, "Rima", 4000.0, "565"));
			Wallet wallet5 = walletService.registerWallet(new Wallet(3, "Pari", 5000.0, "787"));
		System.out.println("Wallet 2:" + wallet2);
		if (walletService.fundTransfer(1, 2, 250.0) == true)
			System.out.println("Fund transfer successful.");
		System.out.println("W1:" + wallet);
		System.out.println("W2:" + wallet2);
		walletService.fundTransfer(1, 2, 750.0);
		System.out.println("W1:" + wallet);
		System.out.println("W2:" + wallet2);

		//login
		walletService.login(1,"123");
		//loginfail
			walletService.login(6,"13");
		Wallet deletedWallet = walletService.unRegisterWallet(1, "123");
		System.out.println("Successfully unregistered your wallet:" + deletedWallet);
		System.out.println("Wallet id 1, balance=" + walletService.showWalletBalance(1));
		}
		catch (WalletException e) {
		// TODO Auto-generated catch block
		// e.printStackTrace();
		System.out.println(e.getMessage());
		} catch (WalletRepositoryException e) {
			throw new RuntimeException(e);
		}
	}

}
