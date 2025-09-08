package com.techie.mybank.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.techie.mybank.Controller.CardsController;
import com.techie.mybank.model.Account;
import com.techie.mybank.model.CreditCards;
import com.techie.mybank.model.Transactions;
import com.techie.mybank.utils.BankDatabase;

public class CardDAOImpl implements CardDAO {

	BankDatabase bd=new BankDatabase();
	
	
	// User Control
	@Override
	public CreditCards uview(Account logac) {
		Connection comm=bd.getConnection();
		long accountid = logac.getAccountid();
		CreditCards cc =new CreditCards();
		try {
			String check = "Select * from creditcard_acc where accountid= ?";
			PreparedStatement ps = comm.prepareStatement(check);
			ps.setLong(1, accountid);
			ResultSet rs = ps.executeQuery();
			if(rs != null) {
				String select = "Select * from creditcard_acc where accountid= ?";
				PreparedStatement ps1 = comm.prepareStatement(select);
				ps1.setLong(1, accountid);
				ResultSet rss=ps1.executeQuery();
				while(rss.next()) {
					cc.setCardHolderName(rss.getString("cardHolderName"));
					cc.setCardNo(rss.getLong("cardNo"));
					cc.setEmail(rss.getString("email"));
					cc.setMobileNo(rss.getLong("mobileNo"));
					cc.setAddress(rss.getString("address"));
				}
			}else {
				System.out.println("You get no Credit Card");
				CardsController obj = new CardsController();
				obj.home(logac);
			}	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return cc;
	}
	
	@Override
	public CreditCards udebit(CreditCards logac) {
		Connection comm = bd.getConnection();
		long accountid = logac.getAccountid();
		int result = 0; 
		try {
			String query="Select * from creditcard_acc where accountid =? ";
			PreparedStatement stmt1= comm.prepareStatement(query);
			stmt1.setLong(1, accountid);
			ResultSet rs = stmt1.executeQuery();
			if(rs.next()) {
			if(rs.getLong("cardBalance") >= logac.getDebitamount()) {
			long total = rs.getLong("cardBalance") - logac.getDebitamount();
			logac.setCardBalance(total);
			String sql="UPDATE creditcard_acc SET cardBalance =? , carddebit = ? where accountid = ?" ;
			PreparedStatement stmt3=comm.prepareStatement(sql);
			stmt3.setLong(1, logac.getCardBalance());
			stmt3.setLong(2, logac.getDebitamount());
			stmt3.setLong(3, accountid);
			
			result = stmt3.executeUpdate();
			
			if(result > 0) {
				String select="Select * from creditcard_acc where accountid =? ";
				PreparedStatement stmt4= comm.prepareStatement(select);
				stmt4.setLong(1, accountid);
				ResultSet rsa = stmt4.executeQuery();
				while(rsa.next()){
					logac.setCardBalance(rsa.getLong("cardBalance"));
					logac.setAccountid(rsa.getLong("accountid"));
					logac.setCardNo(rsa.getLong("cardNo"));
				}
			}
			
			}else {
				System.out.println("Insufficent Amount");
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return logac;
	}
	
	@Override
	public long uviewBalance(Account logac) {
		Connection comm=bd.getConnection();
		long accountid = logac.getAccountid();
		long balance = 0;
		try {
			String select="Select * from creditcard_acc where accountid =? ";
			PreparedStatement stmt1 = comm.prepareStatement(select);
			stmt1.setLong(1, accountid);
			ResultSet rs = stmt1.executeQuery();
			while(rs.next()) {
			balance =rs.getLong("cardBalance");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return balance;
	}
	
	@Override
	public int uupdate(CreditCards logac) {
		Connection comm=bd.getConnection();
		long accountid = logac.getAccountid();
		int result =0;
		try {
			String select="Update creditcard_acc set cardHolderName =?, mobileNo =?, email =?, address =? where accountid =? ";
			PreparedStatement stmt1 = comm.prepareStatement(select);
			stmt1.setString(1, logac.getCardHolderName());
			stmt1.setLong(2, logac.getMobileNo());
			stmt1.setString(3, logac.getEmail());
			stmt1.setString(4, logac.getAddress());
			stmt1.setLong(5, accountid);
			result =stmt1.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public List<Transactions> viewAllTransactions(Account logac) {
		List<Transactions> list = new ArrayList<>();
		Connection comm = bd.getConnection();
		
		
		try {
			String query="Select * from creditcardtransactions where accountid =? ";
			PreparedStatement stmt1 = comm.prepareStatement(query);
			stmt1.setLong(1, logac.getAccountid());
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
	
	// Banking Management
	@Override
	public CreditCards enrollment(CreditCards cc) {
		Connection comm=bd.getConnection();
		long debit = 0;
		try {
			Statement stmt=comm.createStatement();
			String sql = "INSERT INTO creditcard_acc(cardHolderName,cardNo,email, mobileNo, address, accountid, cardBalance, cardCredit, carddebit) "
				    + "VALUES ('" +cc.getCardHolderName() + "', '" + cc.getCardNo() + "', '" +cc.getEmail() + "', '"
				    + cc.getMobileNo() + "', '" + cc.getAddress()+"','" + cc.getAccountid() +"','" + cc.getCardBalance() +"','" + cc.getCardBalance() +"','" +debit +"')";
			
			stmt.executeUpdate(sql);
			comm.close();			
		
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
		}
	
	public List<CreditCards> view() {
		Connection comm=bd.getConnection();
		List<CreditCards> list = new ArrayList<CreditCards>();
		try {
			Statement stmt=comm.createStatement();
			String Q="SELECT * FROM creditcard_acc ";
			ResultSet rss=stmt.executeQuery(Q);
			while(rss.next()) {
				CreditCards cc =new CreditCards();
				cc.setCardHolderName(rss.getString("cardHolderName"));
				cc.setCardNo(rss.getLong("cardNo"));
				cc.setEmail(rss.getString("email"));
				cc.setMobileNo(rss.getLong("mobileNo"));
				cc.setAddress(rss.getString("address"));
				
				list.add(cc);
			}
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	
	public CreditCards viewByAccountId(Long card_id) {
		
		Connection comm=bd.getConnection();
		CreditCards cc =new CreditCards();
		long cardid=card_id;
		
		try {
			Statement stmt=comm.createStatement();
			String readsql="select cardHolderName,cardNo,email, mobileNo, address FROM creditcard_acc  WHERE cardNo=" +cardid;
			ResultSet rss=stmt.executeQuery(readsql);
			
			while(rss.next()) {
				cc.setCardHolderName(rss.getString("cardHolderName"));
				cc.setCardNo(rss.getLong("cardNo"));
				cc.setEmail(rss.getString("email"));
				cc.setMobileNo(rss.getLong("mobileNo"));
				cc.setAddress(rss.getString("address"));
			}			
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
		return cc;
	}


	public int update(CreditCards cc) {
		
		Connection comm=bd.getConnection();
		int result = 0;
		long card_id=cc.getCardid();
		try {
			String query="UPDATE creditcard_acc SET cardHolderName = ?, mobileNo= ?, email =?, address =?  WHERE cardNo =?";
			PreparedStatement ps = comm.prepareStatement(query);
			ps.setString(1, cc.getCardHolderName());
			ps.setLong(2, cc.getMobileNo());
			ps.setString(3, cc.getEmail());
			ps.setString(4, cc.getAddress());
			ps.setLong(5, card_id);
			result = ps.executeUpdate();
//			String selectquery="Select cardHolderName,cardNo,email, mobileNo, address FROM creditcard_acc";
//			ResultSet rss=stmt.executeQuery(selectquery);
//			while(rss.next()) {
//				cc =new CreditCards();
//				cc.setCardHolderName(rss.getString("cardHolderName"));
//				cc.setCardNo(rss.getLong("cardNo"));
//				cc.setEmail(rss.getString("email"));
//				cc.setMobileNo(rss.getLong("mobileNo"));
//				cc.setAddress(rss.getString("address"));
//				
//				System.out.println("CardHolder Name.:"+rss.getString("cardHolderName"));
//				System.out.println("Card No:"+rss.getLong("cardNo"));
//				System.out.println("Email Id:"+rss.getString("email"));
//				System.out.println("Mobile No:"+rss.getLong("mobileNo"));
//				System.out.println("Address:"+rss.getString("address"));
//			}			
		}
		catch(Exception e) {
			e.printStackTrace();
		}		
		return result;
	}


	public int delete(CreditCards cc) {
		Connection comm=bd.getConnection();	
		long card_id=cc.getCardid();
		
		int result = 0;
		try {
			Statement stmt=comm.createStatement();
			String delsql="DELETE FROM creditcard_acc  WHERE cardNo=" + card_id;
			result = stmt.executeUpdate(delsql);
//			String selectquery="Select cardHolderName,cardNo,email, mobileNo, address FROM creditcard_acc";
//			ResultSet rss=stmt.executeQuery(selectquery);
//			while(rss.next()) {
//				cc =new CreditCards();
//				cc.setCardHolderName(rss.getString("cardHolderName"));
//				cc.setCardNo(rss.getLong("cardNo"));
//				cc.setEmail(rss.getString("email"));
//				cc.setMobileNo(rss.getLong("mobileNo"));
//				cc.setAddress(rss.getString("address"));
//				
//				System.out.println("CardHolder Name.:"+rss.getString("cardHolderName"));
//				System.out.println("Card No:"+rss.getLong("cardNo"));
//				System.out.println("Email Id:"+rss.getString("email"));
//				System.out.println("Mobile No:"+rss.getLong("mobileNo"));
//				System.out.println("Address:"+rss.getString("address"));
//			}			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public long checkBalance(Long creditCard) {
		Connection comm = bd.getConnection();
		long balance = 0 ;
		
		try {
			Statement stmt = comm.createStatement();
			String select = "select cardBalance from creditcard_acc where cardNo =" +creditCard;
			ResultSet rs = stmt.executeQuery(select);
			while(rs.next()) {
				balance = rs.getLong("cardBalance");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return balance;
	}

	@Override
	public CreditCards deposit(CreditCards cc) {
		Connection comm = bd.getConnection();
		int result = 0;
		try {
			String query="Select * from creditcard_acc where cardNo= ? ";
			PreparedStatement stmt1= comm.prepareStatement(query);
			stmt1.setLong(1, cc.getCardNo());
			ResultSet rs = stmt1.executeQuery();
			if(rs.next()) {
			long total = rs.getLong("cardBalance") + cc.getCreditamount();
			cc.setCardBalance(total);
			String sql="UPDATE creditcard_acc SET cardBalance =? , cardCredit = ? where cardNo = ?" ;
			PreparedStatement stmt3=comm.prepareStatement(sql);
			stmt3.setLong(1, cc.getCardBalance());
			stmt3.setLong(2, cc.getCreditamount());
			stmt3.setLong(3, cc.getCardNo());
			
			result = stmt3.executeUpdate();
			
			if(result > 0) {
				String select="Select * from creditcard_acc where cardNo =? ";
				PreparedStatement stmt4= comm.prepareStatement(select);
				stmt4.setLong(1, cc.getCardNo());
				ResultSet rsa = stmt4.executeQuery();
				while(rsa.next()){
					cc.setCardBalance(rsa.getLong("cardBalance"));
					cc.setAccountid(rsa.getLong("accountid"));
					cc.setCardNo(rsa.getLong("cardNo"));
				}
			}
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cc;
	}
	
	@Override
	public void transdebit(CreditCards cc) {
		Connection comm = bd.getConnection();
		
		try {
			String transdeposit = "INSERT INTO creditcardtransactions (cardNo, transaction_type, amount, accountid) VALUES (?, ?, ?, ?)";
			PreparedStatement ps = comm.prepareStatement(transdeposit);
			ps.setLong(1, cc.getCardNo());
			ps.setString(2, "debit");
			ps.setLong(3, cc.getDebitamount());
			ps.setLong(4, cc.getAccountid());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			}
	}
	
	@Override
	public void transdeposit(CreditCards cc) {
		Connection comm = bd.getConnection();
		
		try {
			String transdeposit = "INSERT INTO creditcardtransactions (cardNo, transaction_type, amount,accountid) VALUES (?, ?, ?, ?)";
			PreparedStatement ps = comm.prepareStatement(transdeposit);
			ps.setLong(1, cc.getAccountNo());
			ps.setString(2, "desposit");
			ps.setLong(3, cc.getCreditamount());
			ps.setLong(4, cc.getAccountid());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Transactions> viewAllTransactions(CreditCards cc) {
		List<Transactions> list = new ArrayList<>();
		Connection comm = bd.getConnection();
		
		
		try {
			String query="Select * from creditcardtransactions where cardNo =? ";
			PreparedStatement stmt1 = comm.prepareStatement(query);
			stmt1.setLong(1, cc.getCardNo());
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


}
