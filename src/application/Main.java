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
		
		
		
		sc.close();

	}

}
