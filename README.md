# Selenium Exercise

### Discuss Pros and Cons with manual versus automated tests

**Manual** testing has the ability to be more flexible than automated tests, and the startup period is much much shorter, allowing it to actually be used i things such as exploratory testing.

It does however come with the issues of efficiency, as not only is it often much more time-consuming in the long run, but as the system grows in size, testing a single component can take the combined time of a full integration test to fulfill. There is also the issue of human error, as the monotony of manual testing can cause laxness in the testing procedure.

**Automated** tests bear the great benefit of much more efficient maintainability, especially with use of regression testing throughout the development lifecycle, and also the benefit of being able to much more easily and quickly test small, specific components of code through unit testing during development, allowing for much quicker response to your code implementations.

The overhead costs of automated tests are however also fairly considerable, especially if using a more demanding testing framework such as Karma-Jasmine, or a framework such as Selenium, wherein maintenance of configurations and test cases can be very time-consuming as the system changes throughout development. 

### Explain about the Test Pyramid and whether this exercise supported the ideas in the "pyramid"

The Test Pyramid explains how much focus should be given to different types of testing, or different layers of testing, depending on the cost of their operation. The pyramid in particular posits that high-level end-to-end tests on UI are costly, middle-level API tests are less costly but still considerable, and low-level unit tests are the cheapest of the bunch. Therefore, unit tests should be by far the most numerous, service tests should be less numerous, and UI tests should be least numerous.

This exercise definitely does not support the ideas in the Test Pyramid, given that it only requires costly high-level UI tests.

### Discuss some of the problems with automated GUI tests and what makes such tests "vulnerable" 

Automated GUI tests have a number of vulnerabilities, stemming primarily from their requirement of running in an actual live browser. They can encounter race condition-like issues in attempting to fulfill actions before the page has fully loaded the required data, the browsers can run into compatibility issues (although this could also be spun out into a test case by itself), and most of all, the maintainibility of these tests can be very costly. 

If anything in the HTML changes, it can cause entire tests to fail, needing to be reconfigured before they run again.

### Demonstrate details in how to create a Selenium Test using the code for the exercise

You create a Selenium test first by connecting to a webpage, using the WebDriver object, and then using Selenium's Java library to send commands through that WebDriver to the opened page.

```java
driver = new ChromeDriver();
driver.get("localhost:3000");
```

Sending actions to the webpage is done by calling methods on the instance of the WebDriver connected to the page. For example, if we want to find an input box on the page for filtering items, you can look up its ID in the browser console, and then send commands to it through selecting that ID.

```java
driver.findElement(By.id("filter")).sendKeys("2002");
```

### Explain shortly about the DOM, and how you have read/manipulated DOM-elements in your test 

The DOM is the markup that creates the entire structure of an HTML pages. It's a collection of tags that function as containers, either for other tags, or for content to be displayed on the web page. These tags can all be specified to great degrees through CSS and the like, and can be specified through the usage of names, ID's and classes.

I have read/manipulated the DOM in my test through the WebDriver methods Selenium provides, allowing me to load the page through the WebDriver, and then telling the driver to target specific elements in the DOM through ID's, contents, or even regexp, such that I can either get their values, or send keystrokes to the element.

### Explain how (and why it was necessary) you have solved "waiting" problems in your test

First off I used this piece of code given in our first Selenium example. From what I can gather, it tells the WebDriver instance to wait either for a set number of seconds, or for a condition to be fulfilled, which in this case would be apply(), which seems to mean the page fully loading.

```java
(new WebDriverWait(driver, 3)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver wd) {
                
            }
        });
```

Later on I ran into similar problems, in that the Javascript had to fully load in the SPA, but since the page itself was loaded already, I could not use that code snippet. Instead, I set up a different WebDriverWait, which specifically waited for an HTML element to appear, containing a specific string.

```java
WebElement wait = (new WebDriverWait(driver, 10))
           .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), '" + elementToWatch + "')]")));
```
