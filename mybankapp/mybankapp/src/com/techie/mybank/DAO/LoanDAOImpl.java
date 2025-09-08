package com.techie.mybank.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.techie.mybank.Controller.LoansController;
import com.techie.mybank.model.Account;
import com.techie.mybank.model.Loans;
import com.techie.mybank.model.Transactions;
import com.techie.mybank.utils.BankDatabase;

public class LoanDAOImpl  implements LoanDAO{
	
	BankDatabase bd=new BankDatabase();
	
	
	// user controller
	
	@Override
	public Loans uview(Account logac) {
		
		long accountid = logac.getAccountid();
		
		Connection comm = bd.getConnection();
		Loans lod = new Loans();
		boolean result = false;
		try {
			String select1 ="Select * from loan_acc where accountid = ?";
			PreparedStatement ps1 = comm.prepareStatement(select1);
			ps1.setLong(1, accountid);
			result = ps1.execute();
			if(result == true) {
			String select2 ="Select * from loan_acc where accountid = ?";
			PreparedStatement ps = comm.prepareStatement(select2);
			ps.setLong(1, accountid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				lod.setLoanNumber(rs.getLong("loanNumber"));
				lod.setAmount(rs.getLong("loanamount"));
				lod.setBalanceamount(rs.getLong("balanceloan"));
				lod.setName(rs.getString("name"));
				lod.setEmail(rs.getString("email"));
				lod.setMobileNo(rs.getLong("mobileNo"));
				lod.setLoandate(rs.getDate("loandate"));
				lod.setAddress(rs.getString("address"));
			}
		}else {
			System.out.println("You not get loan");
			LoansController lc = new LoansController();
			lc.home(logac);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lod;
	}
	
	@Override
	public Loans uviewloanAmount(Account logac) {
		
		long accountid = logac.getAccountid();
		
		Connection comm = bd.getConnection();
		Loans lod = new Loans();
		boolean result = false;
		try {
			String select1 ="Select * from loan_acc where accountid = ?";
			PreparedStatement ps1 = comm.prepareStatement(select1);
			ps1.setLong(1, accountid);
			result = ps1.execute();
			if(result == true) {
			String select2 ="Select * from loan_acc where accountid = ?";
			PreparedStatement ps = comm.prepareStatement(select2);
			ps.setLong(1, accountid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				lod.setLoanNumber(rs.getLong("loanNumber"));
				lod.setAmount(rs.getLong("loanamount"));
			}
		}else {
			System.out.println("You not get loan");
			LoansController lc = new LoansController();
			lc.home(logac);
		}
		}catch(Exception e) {
			e.getStackTrace();
		}
		
		return lod;
	}
	
	@Override
	public Loans uviewloanbalance(Account logac) {
		
		long accountid = logac.getAccountid();
		
		Connection comm = bd.getConnection();
		Loans lod = new Loans();
		boolean result = false;
		try {
			String select1 ="Select * from loan_acc where accountid = ?";
			PreparedStatement ps1 = comm.prepareStatement(select1);
			ps1.setLong(1, accountid);
			result = ps1.execute();
			if(result == true) {
			String select2 ="Select * from loan_acc where accountid = ?";
			PreparedStatement ps = comm.prepareStatement(select2);
			ps.setLong(1, accountid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				lod.setLoanNumber(rs.getLong("loanNumber"));
				lod.setBalanceamount(rs.getLong("balanceloan"));
			}
		}else {
			System.out.println("You not get loan");
			LoansController lc = new LoansController();
			lc.home(logac);
		}
		}catch(Exception e) {
			e.getStackTrace();
		}
		return lod;
	}

	@Override
	public List<Transactions> utransaction(Account logac) {
		
		long accountid = logac.getAccountid();
		Connection comm = bd.getConnection();
		List<Transactions> list = new ArrayList<>();
		boolean result = false;
		try {
			String select1 ="Select * from loan_acc where accountid = ?";
			PreparedStatement ps1 = comm.prepareStatement(select1);
			ps1.setLong(1, accountid);
			result = ps1.execute();
			if(result == true) {
			String select = "Select * from loantransactions where accountid=?";
			PreparedStatement ps = comm.prepareStatement(select);
			ps.setLong(1, accountid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Transactions transhis = new Transactions();
				transhis.setTransactiondate(rs.getDate("transaction_date"));;
				transhis.setTransactionId(rs.getLong("loanNumber"));
				transhis.setAmount(rs.getDouble("amount"));
				
				list.add(transhis);
			}
			}else {
				System.out.println("You not get loan");
				LoansController lc = new LoansController();
				lc.home(logac);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	// banking controller


	public void enrollment(Loans loan) {
		Connection comm=bd.getConnection();
		try {
			
			Statement stmt=comm.createStatement();	
		
			String sql = "INSERT INTO loan_acc(name,loanNumber,email, mobileNo, address,loanamount,balanceloan,accountid) "
				    + "VALUES ('" +loan.getName() + "', '" + loan.getLoanNumber() + "', '" +loan.getEmail() + "', '"
				    + loan.getMobileNo() + "', '" +loan.getAddress()+"','"+loan.getAmount()+"','"+loan.getAmount()+"','"+loan.getAccountid()+"')";
			stmt.executeUpdate(sql);
			comm.close();		
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	public List<Loans> view() {
		Connection comm=bd.getConnection();
		List<Loans> list = new ArrayList<>();
		try {
			Statement stmt=comm.createStatement();
			String view="Select * from loan_acc ";
			ResultSet rss=stmt.executeQuery(view);
			
			while(rss.next()) {
				Loans loans =new Loans();
				loans.setName(rss.getString("name"));
				loans.setLoanNumber(rss.getLong("loanNumber"));
				loans.setAmount(rss.getLong("loanamount"));
				loans.setBalanceamount(rss.getLong("balanceloan"));
				loans.setEmail(rss.getString("email"));
				loans.setMobileNo(rss.getLong("mobileNo"));
				loans.setAddress(rss.getString("address"));
				
				list.add(loans);
			}
			
			comm.close();
					
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;	
		
	}

	
	public Loans ViewAccountById(Loans loan) {
		Connection comm=bd.getConnection();
		long loan_id=loan.getLoanid();
		Loans loans =new Loans();
		try {
			Statement stmt=comm.createStatement();
			String viewbyid="Select * from loan_acc  WHERE loanNumber="+loan_id;
			ResultSet rss=stmt.executeQuery(viewbyid);
			
			while(rss.next()) {
				
				loans.setName(rss.getString("name"));
				loans.setLoanNumber(rss.getLong("loanNumber"));
				loans.setAmount(rss.getLong("loanamount"));
				loans.setBalanceamount(rss.getLong("balanceloan"));
				loans.setEmail(rss.getString("email"));
				loans.setMobileNo(rss.getLong("mobileNo"));
				loans.setAddress(rss.getString("address"));
				

			}
			comm.close();		
		}catch(Exception e) {
		e.printStackTrace();
	}
		return loans;
	}

	
	public int update(Loans loan) {
		
		
		Connection comm=bd.getConnection();
		long loan_id=loan.getLoanid();
		long mobileNo=loan.getMobileNo();
		String address=loan.getAddress();
		
		int result = 0;
		try {
			Statement stmt=comm.createStatement();			
			String updateQuery="UPDATE  loan_acc SET mobileNo='" + mobileNo+ "',address='"+address+"' WHERE loanNumber=" +loan_id;
			result = stmt.executeUpdate(updateQuery);
			}catch(Exception e) {
				e.printStackTrace();
				}
		return result;
	}


	@Override
	public int closure(Loans loan) {
		Connection comm=bd.getConnection();	
		long loan_id=loan.getLoanid();
		int result = 0;
		try {
			Statement stmt=comm.createStatement();
			String delsql="DELETE FROM loan_acc  WHERE loanNumber=" + loan_id;
			result = stmt.executeUpdate(delsql);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public long deposit(Loans loan) {
		Connection comm = bd.getConnection();
		long loan_number = loan.getLoanNumber(); 
		int result = 0;
		long accountid = 0;
		try {
			Statement stmt =comm.createStatement();
			String select = "Select * from loan_acc  where loanNumber ="+loan_number;
			ResultSet rs = stmt.executeQuery(select);
			while(rs.next()) {
			if(rs.getLong("balanceloan") >= loan.getAmount()) {
				long balanceloanamount = rs.getLong("balanceloan")-loan.getAmount();
				String update = "Update loan_acc set deposit =?, balanceloan =? where loanNumber =?";
				PreparedStatement ps = comm.prepareStatement(update);
				ps.setLong(1, loan.getAmount());
				ps.setLong(2, balanceloanamount);
				ps.setLong(3, loan.getLoanNumber());
				result = ps.executeUpdate();
				if(result > 0){
					System.out.println("Your amount is paid");
					accountid = rs.getLong("accountid");
				}else {
					System.out.println("your amount is not paid");
				}
			}else if(rs.getLong("balanceloan")!= 0){
				System.out.println("Your loan amount: " + rs.getLong("balanceloan"));
			}else {
				System.out.println("your loan amount is fully paid");
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accountid;
	}
	
	@Override
	public void transdeposit(Loans loan) {
		
		Connection comm = bd.getConnection();
		
		try {
			String add ="Insert into loantransactions(loanNumber, amount, accountid) values (?,?,?)";
			PreparedStatement ps = comm.prepareStatement(add);
			ps.setLong(1, loan.getLoanNumber());
			ps.setLong(2, loan.getAmount());
			ps.setLong(3, loan.getAccountid());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public List<Transactions> transaction(Loans loan) {
		Connection comm = bd.getConnection();
		List<Transactions> list = new ArrayList<>();
		
		try {
			String select = "Select * from loantransactions where loanNumber=?";
			PreparedStatement ps = comm.prepareStatement(select);
			ps.setLong(1, loan.getLoanNumber());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Transactions transhis = new Transactions();
				transhis.setTransactiondate(rs.getDate("transaction_date"));;
				transhis.setTransactionId(rs.getLong("loanNumber"));
				transhis.setAmount(rs.getDouble("amount"));
				
				list.add(transhis);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

}

	
