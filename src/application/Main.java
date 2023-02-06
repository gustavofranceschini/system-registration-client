package application;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.TypeClientDao;
import models.entities.TypeClient;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		TypeClientDao typeClientDao = DaoFactory.createTypeClientDao();
		
		System.out.println("-- Testing 1 : TypeClient --> FindById --");
		TypeClient typeClient = typeClientDao.findById(1);
		System.out.println(typeClient);
		
		System.out.println();
		
		System.out.println("-- Testing 2 : TypeClient --> FindAll --");
		List<TypeClient> list = typeClientDao.findAll();
		
		for (TypeClient tp : list) {
			System.out.println(tp);
		}		
		
		System.out.println();
//		
//		System.out.println("-- Testing 3 :  TypeClient --> Insert --");
//		TypeClient tp1 = new TypeClient(null, "Pessoa Jurídica");
//		typeClientDao.insert(tp1);
//		for (TypeClient tp : list) {
//			System.out.println(tp);
//		}		
//		
		
		
//		System.out.println("-- Testing 4 : TypeClient --> deleteById --");
//		typeClientDao.deleteById(2);
//		System.out.println("Delete completed!! ");
////		System.out.println("List of type client:");
////		for (TypeClient tp : list) {
////			System.out.println(tp);
////		}
		
		System.out.println("-- Testing 5 :  TypeClient --> Update --");
		TypeClient tp2 = typeClientDao.findById(1);
		tp2.setType("Pessoa Jurídica");
		typeClientDao.update(tp2);
		System.out.println("Update Completed!");
		System.out.println(tp2);
		
		
		
		sc.close();

	}

}
