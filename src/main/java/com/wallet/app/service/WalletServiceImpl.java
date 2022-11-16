package com.wallet.app.service;

import com.wallet.app.dao.WalletDao;
import com.wallet.app.dao.WalletDaoImpl;
import com.wallet.app.dto.Wallet;
import com.wallet.app.exception.WalletException;
import com.wallet.app.exception.WalletRepositoryException;

public class WalletServiceImpl implements WalletService {

	private WalletDao walletRepository = new WalletDaoImpl();

	public Wallet registerWallet(Wallet newWallet) throws WalletException
	{
		try {
			System.out.println("Wallet successfully Registered");
			return this.walletRepository.addWallet(newWallet);

		}
		catch (Exception e){
			throw new WalletException("Sorry! Could not register the wallet");

		}
	}

	public Boolean login(Integer walletId, String password) throws WalletException
	{
		try {
		if(this.walletRepository.getWalletById(walletId).getPassword()==password){
			System.out.println("Logged-in Successfully");
		}else
		{
			System.out.println("Wrong ID or password");
		}
		return true;
	}
	catch (Exception w){
		throw new WalletException("Wallet does not exist");
	}
	}

	public Double addFundsToWallet(Integer walletId, Double amount) throws WalletException, WalletRepositoryException {
		try {
			Wallet initialWallet = this.walletRepository.getWalletById(walletId);
			if (initialWallet == null)
				throw new WalletException("Wallet does not exists for id:" + walletId);
			Double initialAmount = initialWallet.getBalance();
			initialAmount += amount;
			initialWallet.setBalance(initialAmount);
			this.walletRepository.updateWallet(initialWallet);
			return initialAmount;
		}
		catch (Exception w) {
			throw new WalletException("Funds could not be added");
		}

	}

	public Double showWalletBalance(Integer walletId) throws WalletException, WalletRepositoryException {
		try {
			Wallet foundWallet = this.walletRepository.getWalletById(walletId);

			if (foundWallet == null)
				throw new WalletException("Wallet does not exists for id:" + walletId);

			return foundWallet.getBalance();
		}
		catch (Exception w){
			throw new WalletException("Amount could not be displayed");
		}
	}

	public Boolean fundTransfer(Integer fromId, Integer toId, Double amount) throws WalletException, WalletRepositoryException {
		try {
			Wallet fromWallet = this.walletRepository.getWalletById(fromId);
			Wallet toWallet = this.walletRepository.getWalletById(toId);
			if (fromWallet == null)
				throw new WalletException("From Wallet id does not exists");
			if (toWallet == null)
				throw new WalletException("To Wallet id does not exists");

			Double fromBalance = fromWallet.getBalance();
			if (fromBalance < amount)
				throw new WalletException("From wallet have insufficent balance:" + fromBalance);
			fromBalance -= amount;
			fromWallet.setBalance(fromBalance);
			Double toBalance = toWallet.getBalance();
			toBalance += amount;
			toWallet.setBalance(toBalance);
			this.walletRepository.updateWallet(fromWallet);
			this.walletRepository.updateWallet(toWallet);

			return true;
		}
		catch (Exception w){
			throw new WalletException("Amount cannot be transferred");
		}
	}

	public Wallet unRegisterWallet(Integer walletId, String password) throws WalletException, WalletRepositoryException {
		Wallet foundWallet = this.walletRepository.getWalletById(walletId);
		if(foundWallet == null)
			throw new WalletException("Wallet not found to unregister");

		if(!foundWallet.getPassword().equals(password))
			throw new WalletException("Password does't match to unregister your account.");

		Wallet deletedWallet;
		try {
			deletedWallet = this.walletRepository.deleteWalletById(walletId);
		} catch (WalletRepositoryException e) {

			throw new WalletException(e.getMessage());
		}
		return deletedWallet;
	}

}