package com.quizGpt.formManagement.Common.Controller;

import jakarta.servlet.http.HttpSession;

public class SessionManager {

    protected HttpSession session;
    public SessionManager(HttpSession session){
        this.session = session;
        if(this.session.isNew()){
            this.session.setAttribute("loggedIn", false);
            this.session.setMaxInactiveInterval(60*30);

        }
    }

    private void Logout(){
        if((boolean)this.session.getAttribute("loggedIn")){
        }
    }

}
