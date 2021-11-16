import com.chen.crm.utils.DateTimeUtil;

public class Test {
	
	@org.junit.Test
	public void testDate() {
		String expireTimeString = "2022-03-10 10:10:10";
		String currentTimeString = DateTimeUtil.getSysTime();
		int count = expireTimeString.compareTo(currentTimeString);
		System.out.println(count);
	}
}
