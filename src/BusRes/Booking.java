package BusRes;
import java.util.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class Booking {
	String passengerName;
	int busNo;
	Date date;
	
	Booking()
	{
		Scanner sc =new Scanner(System.in);
		System.out.println("Enter name of passenger : ");
		passengerName =sc.next();
		System.out.println("Enter bus no : ");
		busNo =sc.nextInt();
		System.out.println("Enter date dd-mm-yyyy : ");
		String dateInput =sc.next();
		SimpleDateFormat dateFormat =new SimpleDateFormat("dd-MM-yyyy");
		
		
		try {
			date=dateFormat.parse(dateInput);
		} catch (ParseException e) {
			e.printStackTrace();
		}
				
	}
	public boolean isAvailable() throws SQLException
	{
		BusDAO busdao=new BusDAO();
		BookingDAO bookingdao =new BookingDAO();
		int capacity =busdao.getCapacity(busNo);
		
		int booked =bookingdao.getBookedCount(busNo,date);
		return booked<capacity;
			
		
	}

}
