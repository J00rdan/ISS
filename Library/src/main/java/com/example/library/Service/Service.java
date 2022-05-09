package com.example.library.Service;

import com.example.library.Model.Librarian;
import com.example.library.Repository.LibrarianRepository;

public class Service {
    private LibrarianRepository librarianRepository;

    public Service(LibrarianRepository librarianRepository) {
        this.librarianRepository = librarianRepository;
    }

    public boolean adminLogin(int id, String pass){
        if(!pass.equals("")){
            return librarianRepository.auth(id, pass) != null;
        }
        return false;
    }

    public boolean login(int id, String pass){
        return false;
    }
}
