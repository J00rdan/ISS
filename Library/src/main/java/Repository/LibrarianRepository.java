package Repository;


import Model.Librarian;

public interface LibrarianRepository extends Repository<Integer, Librarian> {
    Librarian auth(int id, String pass);
}
