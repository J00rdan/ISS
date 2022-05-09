package Controllers;

import Service.Service;

public class Controller {
    protected Service srv;

    public void setService(Service srv){
        this.srv = srv;
    }
}
