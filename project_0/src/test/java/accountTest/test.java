package accountTest;

import java.time.LocalDate;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import com.example.entity.Transaction;
import com.example.entity.User;

public class test {

	static Transaction tran ;
	static User user;
	
	
	@BeforeEach
	public static void accountObj () {
		Date date=java.util.Calendar.getInstance().getTime();
		//System.out.println(date);
		
		tran = new Transaction(3, date, 500,"debit");
		user = new User(1,500);
		
		
	}
	
	
	
	
	
	
}
