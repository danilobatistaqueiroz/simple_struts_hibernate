package com.keylesson.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.keylesson.dao.UserDAO;
import com.keylesson.form.UserForm;

public class UserAction extends DispatchAction {

	public ActionForward goToSearchPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("goToSearchPage");
		return mapping.findForward("search");
	}
	
	public ActionForward goToAddPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("goToAddPage");
		return mapping.findForward("add");
	}
	
	public ActionForward searchUsers(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("searchUsers");
		UserForm userForm = (UserForm) form;
		List users = new UserDAO().getUsers(userForm.getName());
		request.setAttribute("users", users);
		return mapping.findForward("search");
	}
	
	public ActionForward addUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("addUser");
		UserForm userForm = (UserForm) form;
		String result = new UserDAO().createUser(userForm.getName(), userForm.getAge());
		if(result.equals("success")){
			request.setAttribute("success", true);
			userForm.reset();
		}else{
			request.setAttribute("failure", true);
		}
		return mapping.findForward("add");
	}

}
