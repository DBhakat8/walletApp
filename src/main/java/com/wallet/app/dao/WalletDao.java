package com.wallet.app.dao;

import com.wallet.app.dto.Wallet;
import com.wallet.app.exception.WalletException;
import com.wallet.app.exception.WalletRepositoryException;

public interface WalletDao {
	// CRUD
	Wallet addWallet(Wallet newWallet) throws WalletException;

	Wallet getWalletById(Integer walletId) throws WalletException, WalletRepositoryException;

	Wallet updateWallet(Wallet updateWallet) throws WalletException;

	Wallet deleteWalletById(Integer walletID) throws WalletRepositoryException;
}
