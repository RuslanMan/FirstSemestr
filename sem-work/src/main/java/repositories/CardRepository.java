package repositories;

import models.Card;

public interface CardRepository {
    Card findById(long id);
}
