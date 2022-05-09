package com.example.library.Controllers;

import com.example.library.GUI;
import com.example.library.Service.Service;

public class Controller {
    protected Service srv;
    protected GUI gui;

    public void setService(Service service, GUI gui){
        this.srv = service;
        this.gui = gui;
    }
}
