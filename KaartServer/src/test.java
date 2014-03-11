import java.util.List;




import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import kaart.dao.GenericDAO;
import kaart.dao.PointDAO;
import kaart.entities.Category;
import kaart.entities.Point;
import kaart.translation.Translation;


public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub	
//		
//		Translation t = new Translation();
////		t.translate("üks;kaks;kolm"); exception
////		t.translate("üks;kaks;kolm;neli");
//		int a = t.translate("üks;kaks");
//		t.getA();
//		t.getB();
//		t.getC();
//		t.getD();
//		System.out.println(t.getA()+
//		t.getB()+
//		t.getC()+
//		t.getD());
//		
//		System.out.println(a);
//		
		
//		PointDAO pointDAO = new PointDAO();
//		Point point = new Point("Rahinge veepark", "12.12,12.12", "Veelaud, palliplatsid, vettehüppemägi", "www.rahinge.ee");
//		pointDAO.persistPoint(point);
//		Category category = new Category("wakeboard", point);
//		pointDAO.persistCategory(category);
//		category = new Category("volleyball", point);		
//		pointDAO.persistCategory(category);
//		
//		List<Category> allPoints = pointDAO.getAllPointsByCategory("wakeboard");
//		String result = pointDAO.convertCategoryListToString(allPoints);
//		System.out.println(result);
//		
////		
////		List<Category> c = pointDAO.getAllPointsByCategory("wakeboard");
////		for(Category a : c){
////			System.out.println("SIIN");
////			System.out.println(a.toString());
////		}
		
		
		
//		
////		Point p6 = new Point();
////		p6.setName("test123");
////		p6.setDescription("asfasfasfas");
////		p6.setLocation("14.12332,12.2321");
////		p6.setLink("www.www.www");
////		
////		Point p7 = new Point();
////		p7.setName("test123");
////		p7.setDescription("asfasfasfas123");
////		p7.setLocation("14.12332,12.2321123");
////		p7.setLink("www.www.www123");
////		
////		Point p8 = new Point();
////		p8.setName("test1234");
////		p8.setDescription("asfasfasfas1234");
////		p8.setLocation("14.12332,12.23211234");
////		p8.setLink("www.www.www1234");
//		
//		
////		try{
////			pointDAO.persistPoint(p6);
////			pointDAO.persistPoint(p7);
////			pointDAO.persistPoint(p8);
////			System.out.println("Inserted point");
////		}catch(Exception e){
////			System.out.println("Err while saving");
////		}
//		List<Point> allPoints = pointDAO.getAllPoints();
//		for(Point point : allPoints){
//			System.out.println(point.toString());
//		}
//		
//		GenericDAO.closeEntityManager();
	}

}
