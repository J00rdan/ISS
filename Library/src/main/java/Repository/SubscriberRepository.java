package Repository;

import Model.Librarian;
import Model.Subscriber;

public interface SubscriberRepository extends Repository<Integer, Subscriber> {
    Subscriber auth(int id, String pass);
}
