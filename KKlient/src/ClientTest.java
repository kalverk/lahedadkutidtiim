import java.util.ArrayList;
import java.util.List;


public class ClientTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		List<String> s = new ArrayList<String>();
//		s.add("wakeboard");
//		s.add("volleyball");
//		s.add("football");
//		s.add("trampoline");
//		Client c = new Client("Rahinge veepark", "12.12,12.12", "Veelaud, palliväljakud, vettehüpped, batuut", "www.rahinge.ee", s);
//		c.startRunning();
//		try{
//		Thread.sleep(5000);
//		}catch(Exception e){}
//		Client c1 = new Client();
//		c1.startRunning();
//		
//		Client c2 = new Client("football");
//		c2.startRunning();
//		
//		Client c3 = new Client("na","12.12,12.12");
//		c3.startRunning();
		
//		
		List<String> s = new ArrayList<String>();
		s.add("running");
		s.add("crosscountry");
		s.add("bicycle");
		s.add("camping");
		s.add("skating");
		s.add("gym");
		
		Client c = new Client("Tähtvere puhkepark", "58.388463, 26.701514", "Igasuguseid asju", "http://arena.ee/puhkepark/spordipark/", s);
		c.startRunning();
//		
		List<String> s1 = new ArrayList<String>();
		s1.add("gym");
		s1.add("swimming");
//		
		Client c1 = new Client("Arena spordiklubi", "58.382372, 26.685151", "Jõusaal, bassein, rühmatreeningud", "http://arenasport.ee/", s1);
		c1.startRunning();
		
	}

}
