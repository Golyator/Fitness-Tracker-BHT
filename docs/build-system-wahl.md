# Aufgabe 1 – Build-System verstehen und auswählen

## a) Build-Konzepte verstehen

Ein Build-System automatisiert zentrale Aufgaben im Softwareentwicklungsprozess.  
In unserem Projekt übernimmt das Build-System folgende Funktionen:

1. **Kompilieren**  
   Der Java-Quellcode wird mit dem Maven-Compiler-Plugin in Bytecode übersetzt.

2. **Abhängigkeitsmanagement**  
   Externe Bibliotheken (z. B. JUnit, JavaFX, TestFX, Mockito) werden zentral über das `pom.xml` verwaltet und automatisch geladen.

3. **Tests ausführen**  
   Unit- und Integrationstests werden automatisiert über das Maven-Surefire-Plugin ausgeführt.  
   Testergebnisse werden im Ordner `target/surefire-reports` abgelegt.

4. **Paketieren**  
   Das Projekt kann als JAR-Datei gebaut werden (`mvn package`).

5. **Dokumentation & Reporting**  
   Mit Plugins wie JaCoCo und Maven Site können Test- und Coverage-Reports erzeugt werden.

6. **Deployment-Vorbereitung**  
   Maven ermöglicht reproduzierbare Builds und ist für Continuous Integration (CI) geeignet.

---

## b) Wahl des Build-Systems für unser Projekt

### Gewähltes Build-System
**Maven**

### Begründung

- Unser Fitness-Tracker ist ein **Java-Projekt** mit JavaFX-GUI; Maven ist dafür ein etablierter Standard.
- Maven folgt dem Prinzip **„Convention over Configuration“** und sorgt für eine einheitliche Projektstruktur im Team.
- Abhängigkeiten, Tests, Builds und Reports werden automatisiert und reproduzierbar ausgeführt.
- Das Projekt nutzt Maven bereits aktiv (z. B. `pom.xml`, `target/`, `surefire-reports`, `jacoco.exec`).

---

## Standard-Verzeichnisstruktur

Unser Projekt folgt den Maven-Standardkonventionen:

```text
fitness-tracker-bht/
├── pom.xml                    # Maven Build-Konfiguration
├── docs/                      # Projektdokumentation
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── controller     # Steuerungslogik (MVC)
│   │       ├── model          # Domänenlogik & Berechnungen
│   │       ├── view           # JavaFX GUI
│   │       └── database       # Repository-Schicht
│   └── test/
│       └── java/
│           ├── model              # Unit-Tests
│           └── integration_tests  # Integrationstests
├── target/                   # Build-Artefakte (nicht versioniert)
├── README.md
└── .gitignore
