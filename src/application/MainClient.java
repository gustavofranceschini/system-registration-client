package application;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.ClientDao;
import model.dao.DaoFactory;
import models.entities.Client;
import models.entities.TypeClient;

public class MainClient {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		ClientDao clientDao = DaoFactory.createClientDao();
		
//		System.out.println("--- Testing 1: Client --> FindById ---");
//		System.out.print("Enter id client: ");
//		int idClient = sc.nextInt();
//		Client client = clientDao.findById(idClient);
//		System.out.println("You select the client: "+ client.getName());
//		
//		
//		System.out.println();
//		System.out.println("--- Testing 2: Client --> FindAll ---");
//		List<Client> list = clientDao.findAll();
//		
//		for (Client c : list) {
//			System.out.println(c);
//		}
		
		System.out.println();
		System.out.println("---- Testing 3: Client --> Insert ---");
		Client c1 = new Client(null, "Maria Antonia", 654321, 654321, new Date(), new TypeClient(1, null));
		clientDao.insert(c1);
		
		List<Client> list = new ArrayList<>();
		list.add(c1);
		
		for (Client c : list) {
			System.out.println(c);
		}
		
		System.out.println();
		System.out.println("--- Testing 4: Client --> DeleteById ---");
		System.out.print("Enter id client for delete: ");
		int idClientForDelete = sc.nextInt();
		clientDao.deleteById(idClientForDelete);		
		
		
		
		
		

		
		
		sc.close();

	}

}
