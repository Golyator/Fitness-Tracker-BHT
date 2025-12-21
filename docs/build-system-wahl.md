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

## Vergleich der Build-Tools – Begründete Abgrenzung

### Warum **Ant** für unser Projekt ungeeignet ist

Ant bietet eine sehr hohe Flexibilität, ist für unser Fitness-Tracker-Projekt jedoch mit deutlichen Nachteilen verbunden:

- **Kein integriertes Dependency Management**  
  Externe Bibliotheken wie JUnit, JavaFX, TestFX oder Mockito müssten manuell eingebunden und gepflegt werden.
- **Keine festen Konventionen**  
  Es gibt keine standardisierte Projektstruktur, was die Zusammenarbeit im Team erschwert.
- **Sehr umfangreiche XML-Konfigurationen**  
  Build-Skripte werden schnell unübersichtlich und wartungsintensiv.
- **Geringe Unterstützung für moderne Build-Workflows**  
  Test-, Reporting- und CI-Prozesse müssen weitgehend selbst konfiguriert werden.

**Fazit:**  
Ant eignet sich eher für Legacy- oder Spezialprojekte, nicht jedoch für ein modernes JavaFX-Projekt mit automatisierten Tests.

---

### Warum **Gradle** keinen Mehrwert bietet

Gradle ist ein modernes Build-Tool, bringt für unser Projekt jedoch keine entscheidenden Vorteile:

- **Steilere Lernkurve**  
  Die Groovy- bzw. Kotlin-DSL ist für Einsteiger weniger intuitiv als Maven-XML.
- **Hohe Flexibilität erhöht Fehleranfälligkeit**  
  Gerade bei kleineren oder akademischen Projekten kann dies zu Inkonsistenzen führen.
- **Kein funktionaler Vorteil für unser Projekt**  
  JavaFX, Tests und Reports werden bereits vollständig durch Maven unterstützt.
- **Projekt nutzt Maven bereits aktiv**  
  Vorhandene Dateien und Ordner wie `pom.xml`, `target/`, `surefire-reports` und `jacoco.exec` sprechen klar für Maven.

**Fazit:**  
Gradle ist sinnvoll für sehr große oder hochgradig angepasste Build-Prozesse, bietet hier jedoch keinen zusätzlichen Nutzen.

---

### Warum **Maven** die beste Wahl ist

Maven stellt für unser Projekt die optimale Lösung dar:

- **Etablierter Standard für Java-Projekte**
- **Klare Projektkonventionen (Convention over Configuration)**
- **Zentrales Dependency Management über `pom.xml`**
- **Nahtlose Integration von**
    - Unit-Tests (JUnit)
    - Integrationstests
    - Build- und Coverage-Reports (Surefire, JaCoCo)
- **CI-geeignet und reproduzierbare Builds**
- **Bereits vollständig im Projekt integriert**

**Gesamtfazit:**  
Maven bietet die beste Balance aus Struktur, Automatisierung und Teamtauglichkeit und ist daher das am besten geeignete Build-System für unseren Fitness-Tracker.

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
