package org.database;

import org.model.UserProfile;

public interface BmrRepository {
    void save(UserProfile user);
    // Hier könnten später Methoden wie 'List<UserProfile> findAll()' stehen
}
