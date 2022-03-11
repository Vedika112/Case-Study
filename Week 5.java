-------com.pizzamanagement.model----------
  
package com.pizzamanagement.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PizzaDetails") // table name in database
public class Pizza {
	@Id
	private int pizzaNo; // acts as primary key
	private String pizzaType;
	private int pizzaPrice;
	private String deliveryAdd;

	// getters and setters
	public int getPizzaNo() {
		return pizzaNo;
	}

	public void setPizzaNo(int pizzaNo) {
		this.pizzaNo = pizzaNo;
	}

	public String getPizzaType() {
		return pizzaType;
	}

	public void setPizzaType(String pizzaType) {
		this.pizzaType = pizzaType;
	}

	public int getPizzaPrice() {
		return pizzaPrice;
	}

	public void setPizzaPrice(int pizzaPrice) {
		this.pizzaPrice = pizzaPrice;
	}

	public String getDeliveryAdd() {
		return deliveryAdd;
	}

	public void setDeliveryAdd(String deliveryAdd) {
		this.deliveryAdd = deliveryAdd;
	}

    //Default constructor	
	public Pizza() {
		// TODO Auto-generated constructor stub
	}

	//Parameterized Constructor
	public Pizza(int pizzaNo, String pizzaType, int pizzaPrice, String deliveryAdd) {
		super();
		this.pizzaNo = pizzaNo;
		this.pizzaType = pizzaType;
		this.pizzaPrice = pizzaPrice;
		this.deliveryAdd = deliveryAdd;
	}
    
	//toString Method
	@Override
	public String toString() {
		return "Pizza [pizzaNo=" + pizzaNo + ", pizzaType=" + pizzaType + ", pizzaPrice=" + pizzaPrice
				+ ", deliveryAdd=" + deliveryAdd + "]";
	}
}


---------com.pizzamanagement.service----------
package com.pizzamanagement.service;

import com.pizzamanagement.model.Pizza;

public interface PizzaService {

	void addPizza(Pizza pz);
	
	void updatePizza(Pizza pz);
	
	void deletePizza(Pizza pz);
	
	Pizza findPizzaById(int id);
}

=====service implementation====
package com.pizzamanagement.service;

import com.pizzamanagement.Dao.PizzaDao;
import com.pizzamanagement.Dao.PizzaDaoImp;
import com.pizzamanagement.model.Pizza;

//providing implementation of services added in PizzaSevice Interface
public class PizzaServiceImp implements PizzaService{
	
	//creating Dao object TO Accesses the data 
	PizzaDao dao;
	public PizzaServiceImp () {
    	dao=new PizzaDaoImp();}

	@Override
	public void addPizza(Pizza pz) {
		dao.beginTransction();
		dao.addPizza(pz);
		dao.commitTransaction();
		
	}

	@Override
	public void updatePizza(Pizza pz) {
		dao.beginTransction();
		dao.updatePizza(pz);
		dao.commitTransaction();
		
	}

	@Override
	public void deletePizza(Pizza pz) {
		dao.beginTransction();
		dao.deletePizza(pz);
		dao.commitTransaction();
		
	}

	@Override
	public Pizza findPizzaById(int id) {
		Pizza pz=dao.findPizzaById(id);
		return pz;
	}

}



------------com.pizzamanagement.Dao-----------
===dao interface===  
package com.pizzamanagement.Dao;

import com.pizzamanagement.model.Pizza;

public interface PizzaDao {
	
   void addPizza(Pizza pz);
	
	void updatePizza(Pizza pz);
	
	void deletePizza(Pizza pz);
	
	Pizza findPizzaById(int id);
	
	void beginTransction();
	void commitTransaction();

}

====dao implementation====
package com.pizzamanagement.Dao;

import javax.persistence.EntityManager;

import com.pizzamanagement.model.Pizza;

public class PizzaDaoImp implements PizzaDao {
	
	//create EntityManager object To Access the data from database
	private EntityManager entityManager;
	public PizzaDaoImp() {
		entityManager=JPAUtil.getEntityManager();
	}

	@Override
	public void addPizza(Pizza pz) {
	   entityManager.persist(pz);
		
	}

	@Override
	public void updatePizza(Pizza pz) {
		entityManager.merge(pz);
		
	}

	@Override
	public void deletePizza(Pizza pz) {
		entityManager.remove(pz);
		
	}

	@Override
	public Pizza findPizzaById(int id) {
		Pizza pz=entityManager.find(Pizza.class, id);
		return pz;
	}

	@Override
	public void beginTransction() {
		entityManager.getTransaction().begin(); //To start Transaction
		
	}

	@Override
	public void commitTransaction() {
		entityManager.getTransaction().commit();//To commit Transaction
		
	}

}

===JPAUtill====
package com.pizzamanagement.Dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
private static EntityManagerFactory factory;
private static EntityManager entityManager;

static {
	factory=Persistence.createEntityManagerFactory("PizzaManagement");
}
public static EntityManager getEntityManager() {
	if(entityManager==null || !entityManager.isOpen()) {
		entityManager = factory.createEntityManager();
	}
	return entityManager;
	
}
}


-----------com.pizzamanagement.Ui-----------
 package com.pizzamanagement.Ui;

import java.util.Scanner;

import com.pizzamanagement.model.Pizza;
import com.pizzamanagement.service.PizzaService;
import com.pizzamanagement.service.PizzaServiceImp;

public class Test {
	public static void main(String[] args) {
		PizzaService service=new PizzaServiceImp();
		int ch=0;
		Scanner sc=new Scanner(System.in);
		
		System.out.println("1.ADD");
		System.out.println("2.update");
		System.out.println("3.Delete");
		System.out.println("4.get pizza by id");
		ch=sc.nextInt();
		switch(ch) {
		case 1:
//          ------Add pizza----
			Pizza pz=new Pizza(128,"Cheese",200,"Mumbai");
			service.addPizza(pz);
		    Pizza pz3=new Pizza(126,"Paneer",580,"Delhi");
		    Pizza pz4=new Pizza(127,"Farm House",780,"Noida");
		    Pizza pz1=new Pizza(124,"Veg Pizza",200,"Mumbai");
			service.addPizza(pz3);
			service.addPizza(pz4);
			service.addPizza(pz1);
			System.out.println("Pizza is added successfully");
			break;
		case 2:
//          ------To update pizza-------			
			Pizza pz2=new Pizza(125,"Margherita",480,"Jaipur");
			pz2.setPizzaNo(111);
			pz2.setDeliveryAdd("Kanpur");
			service.updatePizza(pz2);
			System.out.println("Pizza is updated successfully");
			break;
		case 3:
//			--------To Delete Pizza----------
	        Pizza pz6=new Pizza(1293,"Veg Pizza",200,"Mumbai");
	        service.deletePizza(pz6);
	        System.out.println("Pizza is deleted successfully");
	        break;
		case 4:
//	      --------Find pizza by id---------		
	        Pizza p=service.findPizzaById(128);
	        System.out.println(p);
	        break;
		default:
			System.out.println("wrong input");	
		}
		
	}

}

------persistence.xml-------
<?xml version="1.0" encoding="UTF-8"?>

<persistence xmlns="http://java.sun.com/xml/ns/persistence"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://java.sun.com/xml/ns/persistence
                      http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd"
  version="2.0">
  
	<persistence-unit name="PizzaManagement" transaction-type="RESOURCE_LOCAL">
	
		<provider>org.hibernate.ejb.HibernatePersistence</provider>
		<class>com.pizzamanagement.model.Pizza</class>
		
		<properties>
		<!-- database properties -->
			<property name="javax.persistence.jdbc.url" value="jdbc:postgresql://localhost/student" />
			<property name="javax.persistence.jdbc.user" value="postgres" />
			<property name="javax.persistence.jdbc.password" value="password" />
			<property name="javax.persistence.jdbc.driver" value="org.postgresql.Driver" />
			<!-- hibernate properties -->
			<property name="hibernate.dialect" value="org.hibernate.dialect.PostgreSQLDialect"/>
			<property name="hibernate.hbm2ddl.auto" value="update"/><!-- create/update  -->
			<property name="hibernate.show_sql" value="true"/>
		</properties>
		
	</persistence-unit>
	
</persistence>
