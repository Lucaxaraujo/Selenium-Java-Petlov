package br.com.rocketskills.petlov;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

class DonationPoint {
	String name;
	String email;
	String zipCode;
	Integer addressNumber;
	String addressDetail;
	String pets;

	public DonationPoint(String name, String email, String zipCode, Integer addressNumber, String addressDetail,
			String pets) {
		this.name = name;
		this.email = email;
		this.zipCode = zipCode;
		this.addressNumber = addressNumber;
		this.addressDetail = addressDetail;
		this.pets = pets;
	};
};

class SignupSelenide {

	@Test
	@DisplayName("Deve cadastrar um ponto de doação")
	void createDonationPoint() {
		//(pre condition)
		DonationPoint point = new DonationPoint(
			"Estação Pet", 
			"estacaopet@hotmail.com", 
			"12070740", 
			118,
			"Próximo à creche", 
			"Cachorros"		
		);
		
		// Opening the signup page 
		open("https://petlov.vercel.app/signup");
		$("h1").shouldHave(text("Cadastro de ponto de doação"));

		// Filling the form to create a donation point (action)
		$("input[placeholder='Nome do ponto de doação']").setValue(point.name);
		$("input[name='email']").setValue(point.email);
		$("input[name='cep']").setValue(point.zipCode);
		$("input[value='Buscar CEP']").click();
		$("input[name='addressNumber']").setValue(point.addressNumber.toString());
		$("input[name='addressDetails']").setValue(point.addressDetail);
		$(By.xpath("//span[text()=\"" +point.pets + "\"]/..")).click();
		$(".button-register").click();

		// Checking success page after filling the form and create a donation point (expected result)
		String text = "Seu ponto de doação foi adicionado com sucesso. Juntos, podemos criar um mundo onde todos os animais recebam o amor e cuidado que merecem.";
		$("#success-page p").shouldHave(text(text));
	}
}
