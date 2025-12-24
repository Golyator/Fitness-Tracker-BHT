# Build-Befehle – Übersicht

## Setup
- **Build-System:** Maven
- **Voraussetzungen:**
    - Java installiert (JDK 21 oder 23, je nach Setup)
    - Maven

## Standard-Workflows

### Clean Build
**Befehl:**
- Maven-Plugin → Lifecycle → `clean`

**Ergebnis:**
- `BUILD SUCCESS`
- Alle generierten Dateien inklusive des `target`-Ordners wurden gelöscht

---

### Tests ausführen
**Befehl:**
- Maven-Plugin → Lifecycle → `test`

**Ergebnis:**
- `BUILD SUCCESS`
- `Tests run: 30, Failures: 0, Errors: 0, Skipped: 0`

---

### Anwendung bauen
**Befehl:**
- Maven-Plugin → Lifecycle → `package`

**Ergebnis:**
- `BUILD SUCCESS`
- JAR-Datei wird erzeugt, `--- jar:3.4.1:jar (default-jar) @ fitness-tracker-bht ---`
- `.\FTA-BHT\target\fitness-tracker-bht-1.0-SNAPSHOT.jar`

### Anwendung ausführen
**Befehl:**
- Maven-Plugin → Plugins → `javafx:run`

**Ergebnis:**
- Programm startet erfolgreich (JavaFX-Fenster öffnet sich)

## Build-Zeiten
- **Clean Build:** `Total time: 0.246 s`
- **Nur Tests:** `Total time: 2.529 s`