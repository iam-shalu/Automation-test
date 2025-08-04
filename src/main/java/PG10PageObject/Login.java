package PG10PageObject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login 
{
	WebDriver driver;

	public Login(WebDriver driver) 
	{
		// TODO Auto-generated constructor stub
		
		this.driver=driver;
		PageFactory.initElements(driver , this);
	}
	
	//***************************************************************************************
	
	@FindBy(xpath = "//input[@id=\"Username\"]")
	WebElement username;
	
	public void username(String usrname)
	{
		username.click();		
		username.sendKeys(usrname);
	}
	
	//***************************************************************************************
	
	@FindBy(xpath = "//input[@id='Password']")
	WebElement enterpass;
	
	public void enterpass(String pass)
	{
		enterpass.sendKeys(pass);
	}
	
	//****************************************************************************************
	
	@FindBy(xpath = "//button[@id='btn-submit']")
	WebElement pclickonlogin;
	
	public void pclickonlogin()
	{
		pclickonlogin.click();


	}
	
	
}




