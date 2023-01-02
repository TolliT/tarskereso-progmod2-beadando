package com.beadando.tarskereso;


import com.beadando.tarskereso.model.Nem_enum;
import com.beadando.tarskereso.model.User;
import com.beadando.tarskereso.services.Tarskereso_services;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TarskeresoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TarskeresoApplication.class, args);
	}

	{
		ArrayList<Integer> likee = new ArrayList<>();
		likee.add(1);
		likee.add(2);

		//User valaki = new User(2, "Varhalmi Botond", 12, "She said she was 12!!!", likee);
		//valaki.setNem(Nem_enum.ferfi);
		Tarskereso_services alma = new Tarskereso_services();

		//alma.toFile("src/main/resources/Tarskereso_db.csv", valaki);
		List<User> users = new ArrayList<>();

		users = alma.fromFile("src/main/resources/Tarskereso_db.csv");
		for(int i = 0; i<users.size(); i++){
			System.out.println(users.get(i).getId());
			System.out.println(users.get(i).getNem());
			System.out.println(users.get(i).getNev());
			System.out.println(users.get(i).getKor());
			System.out.println(users.get(i).getLeiras());
			System.out.println(users.get(i).likeok);
		}

	}
}
