
public class Shadow {

	public static void main(String[] args) {
		public static void main(String[] args) throws InterruptedException {
			WebDriver d=new ChromeDriver();
			d.manage().window().maximize();
			d.get("https://demoapps.qspiders.com/ui?scenario=1");
			Thread.sleep(1000);
			d.findElement((By.xpath("//section[contains(text(),'Shadow')][1]"))).click();
			Thread.sleep(1000);
			d.findElement((By.xpath("//section[contains(text(),'Shadow')][1]"))).click();
			Thread.sleep(2000);
			SearchContext shadow_host = d.findElement(By.xpath("//form/div[1]")).getShadowRoot();
			//Thread.sleep(1000);
			shadow_host.findElement(By.cssSelector("//input[@type='text']")).sendKeys("MADHURA");
	}

}
