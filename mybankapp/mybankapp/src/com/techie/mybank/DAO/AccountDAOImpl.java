package com.techie.mybank.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.techie.mybank.model.Account;
import com.techie.mybank.model.Transactions;
import com.techie.mybank.utils.BankDatabase;

public class AccountDAOImpl implements AccountDAO{
	
	
	BankDatabase bd=new BankDatabase();

	
	@Override
	public List<Account> viewAll(Account account) {
		List<Account> list = new ArrayList<>();
		Connection comm=bd.getConnection();
		Account raccountdt = new Account();
		try {
				String query="Select * from accounts where accountid =? ";
				PreparedStatement stmt1= comm.prepareStatement(query);
				stmt1.setLong(1, account.getAccountid());
				ResultSet rs=stmt1.executeQuery();
				
				while(rs.next()) {
				
					raccountdt.setAccountNo(rs.getLong("accountno"));
					raccountdt.setAccountName(rs.getString("accountName"));
					raccountdt.setEmail(rs.getString("email"));
					raccountdt.setMobileno(rs.getLong("mobileNo"));
					raccountdt.setAddress(rs.getString("address"));
					}		
				
		}catch(Exception e) {
			e.printStackTrace();
		}
		list.add(raccountdt);
		return list;
		
	}
	
		
	@Override
	public int update(Account account) {
		Connection comm=bd.getConnection();
		int result = 0;
		try {
			
		String sql="UPDATE accounts SET mobileno= ?, address = ? where accountid = ?" ;
		PreparedStatement stmt3=comm.prepareStatement(sql);
		stmt3.setLong(1, account.getMobileno());
		stmt3.setString(2, account.getAddress());
		stmt3.setLong(3, account.getAccountid());
		
		result=stmt3.executeUpdate();
	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public long viewBalance(Account account) {
		
		Connection comm=bd.getConnection();
		long balance = 0;
		try {
			String select="Select * from accounts where accountid =? ";
			PreparedStatement stmt1 = comm.prepareStatement(select);
			stmt1.setLong(1, account.getAccountid());
			ResultSet rs = stmt1.executeQuery();
			while(rs.next()) {
			balance =rs.getLong("accountBalance");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return balance;
	}
	
	@Override
	public List<Transactions> utransactions(Account account) {
		List<Transactions> list = new ArrayList<>();
		Connection comm = bd.getConnection();
		
		
		try {
			String query="Select * from accountstransactions where accountid =? ";
			PreparedStatement stmt1 = comm.prepareStatement(query);
			stmt1.setLong(1, account.getAccountid());
			ResultSet rs = stmt1.executeQuery();
			while(rs.next()) {
				Transactions transhis = new Transactions();
				transhis.setTransactiondate(rs.getDate("transaction_date"));;
				transhis.setTransactiontype(rs.getString("transaction_type"));
				transhis.setAmount(rs.getDouble("amount"));
				
				list.add(transhis);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	// Banking management
	
	@Override
	public long open(Account account) {
		Connection comm=bd.getConnection();
		long result = 0;
		try {
			Statement stmt=comm.createStatement();
			String sql = "INSERT INTO accounts(accountno, accountName, email, mobileNo, address, accountBalance, creditAmount, debitAmount) "
				    + "VALUES ('" + account.getAccountNo() + "', '" + account.getAccountName() + "', '" + account.getEmail() + "', '"
				    + account.getMobileno() + "', '" + account.getAddress() + "', '" + account.getAccountBalance() + "','"
				    + account.getCreditAmount() + "','" + account.getDebitAmount() + "')";
			
			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = stmt.getGeneratedKeys();
	        if (rs.next()) {
	            result = rs.getInt(1);  // get the generated primary key
	        }

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public Account viewAccountById(Account account) {
		Connection comm=bd.getConnection();
		Account accounts = new Account();
		try {
			String readsql="select *  FROM accounts WHERE email= ?";
			PreparedStatement stmt2 = comm.prepareStatement(readsql);
			stmt2.setString(1, account.getEmail());
			ResultSet rs=stmt2.executeQuery();				
			while(rs.next()){	
				accounts.setAccountNo(rs.getLong("accountno"));
				accounts.setAccountName(rs.getString("accountName"));
				accounts.setEmail(rs.getString("email"));
				accounts.setMobileno(rs.getLong("mobileNo"));
				accounts.setAddress(rs.getString("address"));
				accounts.setAccountBalance(rs.getLong("accountBalance"));
			}
			comm.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return accounts;
	}
	
	@Override
	public Account viewAccountByNo(Long accountNo) {
		
		Connection comm=bd.getConnection();
		Account account = new Account();
		try {
			String readsql="select *  FROM accounts WHERE accountno= ?";
			PreparedStatement stmt2 = comm.prepareStatement(readsql);
			stmt2.setLong(1, accountNo);
			ResultSet rs=stmt2.executeQuery();				
			while(rs.next()){
				account = new Account();
				account.setAccountid(rs.getLong("accountid"));
				account.setAccountNo(rs.getLong("accountno"));
				account.setAccountName(rs.getString("accountName"));
				account.setEmail(rs.getString("email"));
				account.setMobileno(rs.getLong("mobileNo"));
				account.setAddress(rs.getString("address"));
				account.setAccountBalance(rs.getLong("accountBalance"));
			}
			comm.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return account;
	}
	
	@Override
	public int mupdate(Account account) {
		Connection comm = bd.getConnection();
		int result = 0;
		try {
		String sql="UPDATE accounts SET mobileno= ?, address = ? where email = ?" ;
		PreparedStatement stmt3=comm.prepareStatement(sql);
		stmt3.setLong(1, account.getMobileno());
		stmt3.setString(2, account.getAddress());
		stmt3.setString(3, account.getEmail());
		
		result=stmt3.executeUpdate();
	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public Account deposit(Account account) {
		
		Connection comm=bd.getConnection();
		
		try {
			String query="Select * from accounts where accountno =? ";
			PreparedStatement stmt1= comm.prepareStatement(query);
			stmt1.setLong(1, account.getAccountNo());
			ResultSet rs = stmt1.executeQuery();
			if(rs.next()) {
			long total = rs.getLong("accountBalance") + account.getCreditAmount();
			account.setAccountBalance(total);
			account.setAccountid(rs.getLong("accountid"));
			String sql="UPDATE accounts SET accountBalance =? , creditAmount = ? where accountno = ?" ;
			PreparedStatement stmt3=comm.prepareStatement(sql);
			stmt3.setLong(1, account.getAccountBalance());
			stmt3.setLong(2, account.getCreditAmount());
			stmt3.setLong(3, account.getAccountNo());
			
			stmt3.executeUpdate();
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return account;
	}

	@Override
	public Account debit(Account account) {
		
		Connection comm=bd.getConnection();
		int result = 0; 
		try {
			String query="Select * from accounts where accountno =? ";
			PreparedStatement stmt1= comm.prepareStatement(query);
			stmt1.setLong(1, account.getAccountNo());
			ResultSet rs = stmt1.executeQuery();
			if(rs.next()) {
			if(rs.getLong("accountBalance") >= account.getDebitAmount()) {
			long total = rs.getLong("accountBalance") - account.getDebitAmount();
			account.setAccountBalance(total);
			String sql="UPDATE accounts SET accountBalance =? , debitAmount = ? where accountno = ?" ;
			PreparedStatement stmt3=comm.prepareStatement(sql);
			stmt3.setLong(1, account.getAccountBalance());
			stmt3.setLong(2, account.getDebitAmount());
			stmt3.setLong(3, account.getAccountNo());
			
			result = stmt3.executeUpdate();
			
			if(result > 0) {
				String select="Select * from accounts where accountno =? ";
				PreparedStatement stmt4= comm.prepareStatement(select);
				stmt4.setLong(1, account.getAccountNo());
				ResultSet rsa = stmt4.executeQuery();
				while(rsa.next()){
					account.setAccountBalance(rsa.getLong("accountBalance"));
					account.setAccountid(rsa.getLong("accountid"));
				}
			}
			
			}else {
				System.out.println("Insufficent Amount");
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return account;
	}
	
	
	@Override
	public int delete(Account account) {
		Connection comm=bd.getConnection();
		int result = 0;
		try {
			String deletesql="DELETE from accounts WHERE accountno = ?";
			PreparedStatement stmt2=comm.prepareStatement(deletesql);
			stmt2.setLong(1, account.getAccountNo());
			
			result = stmt2.executeUpdate();
			
		}
		catch(Exception e) {
		e.printStackTrace();	
		}
		
		return result;
	}
	
	@Override
	public List<Transactions> transactions(Account account) {
		List<Transactions> list = new ArrayList<>();
		Connection comm = bd.getConnection();
		
		
		try {
			String query="Select * from accountstransactions where account_no =? ";
			PreparedStatement stmt1 = comm.prepareStatement(query);
			stmt1.setLong(1, account.getAccountNo());
			ResultSet rs = stmt1.executeQuery();
			while(rs.next()) {
				Transactions transhis = new Transactions();
				transhis.setTransactiondate(rs.getDate("transaction_date"));;
				transhis.setTransactiontype(rs.getString("transaction_type"));
				transhis.setAmount(rs.getDouble("amount"));
				
				list.add(transhis);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}


	@Override
	public void transdeposit(Account account) {
		Connection comm = bd.getConnection();
		
		try {
			String transdeposit = "INSERT INTO accountstransactions (account_no, transaction_type, amount,accountid) VALUES (?, ?, ?, ?)";
			PreparedStatement ps = comm.prepareStatement(transdeposit);
			ps.setLong(1, account.getAccountNo());
			ps.setString(2, "desposit");
			ps.setLong(3, account.getCreditAmount());
			ps.setLong(4, account.getAccountid());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void transdebit(Account account) {
		Connection comm = bd.getConnection();
		
		try {
			String transdeposit = "INSERT INTO accountstransactions (account_no, transaction_type, amount, accountid) VALUES (?, ?, ?, ?)";
			PreparedStatement ps = comm.prepareStatement(transdeposit);
			ps.setLong(1, account.getAccountNo());
			ps.setString(2, "debit");
			ps.setLong(3, account.getDebitAmount());
			ps.setLong(4, account.getAccountid());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			}
	}

}
