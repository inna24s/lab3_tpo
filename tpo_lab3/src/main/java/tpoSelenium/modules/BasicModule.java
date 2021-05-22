package tpoSelenium.modules;

import org.openqa.selenium.WebDriver;

public abstract class BasicModule {
        protected WebDriver webDriver;

        public BasicModule(WebDriver webDriver) {
            this.webDriver = webDriver;
        }
    }
