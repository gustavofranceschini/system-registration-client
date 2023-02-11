package application;

import java.util.Scanner;

import db.DB;
import model.dao.ClientDao;
import model.dao.DaoFactory;
import model.dao.TypeClientDao;

public class Main {

	public static void main(String[] args) {	
		
		DB.getConnection();
		
		TypeClientDao typeClientDao = DaoFactory.createTypeClientDao();		
		ClientDao clientDao = DaoFactory.createClientDao();
		
		Scanner sc = new Scanner(System.in);		
		
		System.out.println("WELCOME TO THE CUSTOMER REGISTRATION SYSTEM");
		System.out.println("<----------------------------------------->");
		System.out.println("<----------------------------------------->");
		
		System.out.print("Enter id client: ");
		int id = sc.nextInt();
		
		
		
		
		sc.close();

	}

}
