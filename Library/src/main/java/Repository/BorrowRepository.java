package Repository;

import Model.Book;
import Model.Borrow;

public interface BorrowRepository extends Repository<Integer, Borrow>{

    public Borrow findBorrow(int idBook, int idSub);
}
