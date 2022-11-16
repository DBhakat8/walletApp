package com.wallet.app.service;

import com.wallet.app.dto.Wallet;
import com.wallet.app.exception.WalletException;
import com.wallet.app.exception.WalletRepositoryException;

public interface WalletService {

	Wallet registerWallet(Wallet newWallet) throws WalletException;

	Boolean login(Integer walletId,String password)throws WalletException;

	Double addFundsToWallet(Integer walletId, Double amount) throws WalletException, WalletRepositoryException;

	Double showWalletBalance(Integer walletId) throws WalletException, WalletRepositoryException;

	Boolean fundTransfer(Integer fromId, Integer toId, Double amount) throws WalletException, WalletRepositoryException;

	Wallet unRegisterWallet(Integer walletId,String password) throws WalletException, WalletRepositoryException;
}