package controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import beans.User;
import business.MyTimerService;
import business.OrderBusinessInterface;

@ManagedBean
@ViewScoped
public class FormController 
{
	@Inject
	OrderBusinessInterface services;
	
	@EJB
	MyTimerService timer;
	
	public String onSubmit()
	{
		// Get user values from input form
		FacesContext context = FacesContext.getCurrentInstance();
		User user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
		
		// Prints a message to the console to tell us which business service is currently selected in the beans.xml
		// file as an alternative
		services.test();
		
		// Start a timer when the login is clicked
		timer.setTimer(10000);
		
		// Put user data in the POST request
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
		
		// Show next page
		return "TestResponse.xhtml";
	}
	
	public OrderBusinessInterface getService()
	{
		return services;
	}
}