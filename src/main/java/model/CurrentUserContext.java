package model;

/**
 * Hält das aktuell verwendete UserProfile zentral,
 * damit z.B. der Activity-Bereich auf Gewicht usw. zugreifen kann,
 * ohne dass der Benutzer alles erneut eingeben muss.
 *
 * In einer größeren Anwendung würde man das eleganter lösen
 * (z.B. Dependency Injection), für dieses Projekt reicht ein
 * einfacher statischer Kontext.
 */
public class CurrentUserContext {

    private static UserProfile currentUser;

    public static void setCurrentUser(UserProfile user) {
        currentUser = user;
    }

    public static UserProfile getCurrentUser() {
        return currentUser;
    }

    public static boolean hasUser() {
        return currentUser != null;
    }
}