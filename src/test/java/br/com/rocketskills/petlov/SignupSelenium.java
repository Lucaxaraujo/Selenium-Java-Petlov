package br.com.rocketskills.petlov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

class SignupSelenium {

	WebDriver driver;

	@BeforeEach
	void starSession() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	};

	@AfterEach
	void finisSession() {
		driver.close();
	};

	@Test
	@DisplayName("Deve cadastrar um ponto de doação")
	void createDonationPoint() {
		// Opening the signup page
		driver.get("https://petlov.vercel.app/signup");

		WebElement title = driver.findElement(By.cssSelector("h1"));

		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		wait.until(d -> title.isDisplayed());

		assertEquals("Cadastro de ponto de doação", title.getText(), "Verificando o título do formulário");

		// Filling the form to create a donation point
		WebElement donationPointName = driver.findElement(By.cssSelector("input[placeholder='Nome do ponto de doação']"));
		donationPointName.sendKeys("Ponto Teste");

		WebElement email = driver.findElement(By.cssSelector("input[name='email']"));
		email.sendKeys("emailteste@gmail.com");

		WebElement zipcode = driver.findElement(By.cssSelector("input[name='cep']"));
		zipcode.sendKeys("12070740");

		WebElement cepButton = driver.findElement(By.cssSelector("input[value='Buscar CEP']"));
		cepButton.click();

		WebElement addressNumber = driver.findElement(By.cssSelector("input[name='addressNumber']"));
		addressNumber.sendKeys("50");

		WebElement addressDetails = driver.findElement(By.cssSelector("input[name='addressDetails']"));
		addressDetails.sendKeys("Ao lado da farmácia");

		driver.findElement(By.xpath("//span[text()=\"Cachorros\"]/..")).click();

		driver.findElement(By.className("button-register")).click();

		// Checking success page after filling the form and create a donation point
		WebElement successPageText = driver.findElement(By.cssSelector("#success-page p"));

		Wait<WebDriver> waitSuccessPageText = new WebDriverWait(driver, Duration.ofSeconds(2));
		waitSuccessPageText.until(d -> successPageText.isDisplayed());

		String text = "Seu ponto de doação foi adicionado com sucesso. Juntos, podemos criar um mundo onde todos os animais recebam o amor e cuidado que merecem.";

		assertEquals(text, successPageText.getText(), "Verificando a mensagem da página de sucesso");
	}
}
