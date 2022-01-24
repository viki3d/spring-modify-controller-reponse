# spring-modify-controller-reponse

A project demonstrating how to change a Spring RestController response by using Servlet-Filter.

### How we modify the Spring-Controller response:
Actually we do not use Spring here (like Spring Interceptors /they can modify only header/ ) but 
a Servlet technique - Filters:  
![spring-modify-controller-response-01.png](spring-modify-controller-response-01.png?id=1)  

And here is how we update the response JSON with the additional field `"year" : 2020` :
![spring-modify-controller-response-01.png](spring-modify-controller-response-02.png?id=1)  

### JSON libraries:
> &lt;dependency>  
  &nbsp;&nbsp;&lt;groupId>javax.json&lt;/groupId>  
  &nbsp;&nbsp;&lt;artifactId>javax.json-api&lt;/artifactId>  
  &lt;/dependency>  
  &lt;dependency>  
  &nbsp;&nbsp;&lt;groupId>org.glassfish&lt;/groupId>  
  &nbsp;&nbsp;&lt;artifactId>javax.json&lt;/artifactId>  
  &nbsp;&nbsp;&lt;version>1.1.4&lt;/version>  
  &lt;/dependency>  

### Servlet-Filter:
> import javax.servlet.Filter;  
> 
> @Component  
  public class CarFilter implements Filter {  
> &nbsp;&nbsp;  @Override  
  &nbsp;&nbsp;  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,  
> &nbsp;&nbsp;&nbsp;&nbsp;  if (httpRequest.getRequestURI().equals("/modcar")) {  
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  // Forward execution  
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  // Get response  
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  // Convert response as JSON  
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  // Modify the response JSON  
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  ...  
  &nbsp;&nbsp;}


