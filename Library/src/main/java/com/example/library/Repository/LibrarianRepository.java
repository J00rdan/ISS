package com.example.library.Repository;

import com.example.library.Model.Librarian;

public interface LibrarianRepository extends Repository<Integer, Librarian> {
    Librarian auth(int id, String pass);
}
