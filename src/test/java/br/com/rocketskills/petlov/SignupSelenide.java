package br.com.rocketskills.petlov;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

class SignupSelenide {

	@Test
	@DisplayName("Deve cadastrar um ponto de doação")
	void createDonationPoint() {
		// Opening the signup page
		open("https://petlov.vercel.app/signup");
		$("h1").shouldHave(text("Cadastro de ponto de doação"));
		
		// Filling the form to create a donation point
		$("input[placeholder='Nome do ponto de doação']").setValue("Estação Pet");
		$("input[name='email']").setValue("estacaopet@hotmail.com");
		$("input[name='cep']").setValue("12070740");
		$("input[value='Buscar CEP']").click();
		$("input[name='addressNumber']").setValue("118");
		$("input[name='addressDetails']").setValue("Próximo à creche");
		$(By.xpath("//span[text()=\"Cachorros\"]/..")).click();
		$(".button-register").click();

		// Checking success page after filling the form and create a donation point
		String text = "Seu ponto de doação foi adicionado com sucesso. Juntos, podemos criar um mundo onde todos os animais recebam o amor e cuidado que merecem.";

		$("#success-page p").shouldHave(text(text));		
	}
}
