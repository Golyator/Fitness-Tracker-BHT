# Team-1-Gruppenarbeit-Softwaretechnik

Team 1 Gruppenarbeit Softwaretechnik

# Requirements-Dokument – Gesundheits-Tracker (Version 2)

## 1. Projektbeschreibung

Das Team entwickelt eine mobile Gesundheits-Tracker-App, die Nutzer:innen dabei unterstützt, ihr Wohlbefinden ganzheitlich zu überwachen. Die App ermöglicht die Erfassung und Auswertung von Ernährung, Hydration, Schlaf, Bewegung und physischer Kapazität. Ziel ist es, gesundheitsrelevante Daten übersichtlich darzustellen, um Bewusstsein, Motivation und Lebensqualität zu fördern.

## 2. Stakeholder-Analyse

| Stakeholder       | Rolle                        | Interesse / Ziel                                 |
| ----------------- | ---------------------------- | ------------------------------------------------ |
| Endnutzer:in      | Verwendet die App            | Gesundheitsdaten erfassen und Fortschritte sehen |
| Produktmanager:in | Definiert Features und Ziele | Nutzerbindung, Datenqualität                     |
| Entwickler:in     | Implementierung              | Klare und testbare Anforderungen                 |
| UX/UI-Designer:in | Gestaltet die App-Oberfläche | Benutzerfreundlichkeit und Motivation            |
| Betreiber         | Wartung & Sicherheit         | Zuverlässige, datenschutzkonforme Infrastruktur  |

## 3. Funktionale Anforderungen

| ID  | Kurzbeschreibung                                                                          | MUSS/SOLL/KANN | Priorität (vorläufig) | Kategorie              |
| --- | ----------------------------------------------------------------------------------------- | -------------- | --------------------- | ---------------------- |
| F01 | Trainingsdaten erfassen (Schritte, Kalorien, Zeit)                                        | SOLL           | Mittel                | Bewegung               |
| F02 | Nutzerregistrierung und Login                                                             | MUSS           | Hoch                  | Sicherheit             |
| F03 | Grafische Statistiken anzeigen                                                            | SOLL           | Mittel                | Benutzerfreundlichkeit |
| F04 | Nutzer:in MUSS persönliche Basisdaten (Alter, Geschlecht, Gewicht, Größe) eingeben können | MUSS           | Hoch                  | Benutzerverwaltung     |
| F05 | App SOLL aus Basisdaten den täglichen Kalorien- und Wasserbedarf berechnen                | SOLL           | Mittel                | Benutzerverwaltung     |
| F06 | Trinkmenge pro Tag eingeben                                                               | MUSS           | Hoch                  | Hydration              |
| F07 | Tägliches Trinkziel anzeigen und Fortschritt berechnen                                    | SOLL           | Mittel                | Hydration              |
| F08 | Mahlzeiten bzw. Kalorien manuell erfassen                                                 | MUSS           | Hoch                  | Ernährung              |
| F09 | Empfohlene Tageskalorien berechnen                                                        | KANN           | Niedrig               | Ernährung              |
| F10 | Schlafdauer erfassen                                                                      | MUSS           | Hoch                  | Schlaf                 |
| F11 | Einfache Schlafqualitäts-Auswertung anzeigen                                              | KANN           | Niedrig               | Schlaf                 |
| F12 | Subjektives Energielevel erfassen                                                         | KANN           | Niedrig               | Physische Kapazität    |
| F13 | Basisdaten zu Bewegung erfassen (Schritte, Aktivitätszeit)                                | SOLL           | Mittel                | Bewegung               |

## 4. Nicht-funktionale Anforderungen

| ID   | Beschreibung                                        | Kategorie      | Priorität |
| ---- | --------------------------------------------------- | -------------- | --------- |
| NF01 | App MUSS innerhalb von 3 Sekunden starten           | Performance    | Hoch      |
| NF02 | UI SOLL intuitiv und barrierearm gestaltet sein     | Usability      | Mittel    |
| NF03 | Nutzerdaten MÜSSEN DSGVO-konform gespeichert werden | Security/Legal | Hoch      |
| NF04 | App SOLL auf iOS und Android lauffähig sein         | Portabilität   | Hoch      |
| NF05 | System MUSS eine Verfügbarkeit von 99 % erreichen   | Betrieb        | Hoch      |

## 5. User Stories (vollständig: US01–US10)

| ID   | User Story                                                                                        | Akzeptanzkriterien                                                                         |
| ---- | ------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------ |
| US01 | Als Nutzer:in möchte ich meine täglichen Schritte sehen, um meinen Fortschritt zu erkennen.       | Schritte werden korrekt erfasst und im Dashboard angezeigt.                                |
| US02 | Als Nutzer:in möchte ich Wochenstatistiken ansehen, um Trends in meinem Training zu erkennen.     | Wochenübersicht zeigt aggregierte Daten korrekt an.                                        |
| US03 | Als Nutzer:in möchte ich mich sicher anmelden können, damit meine Daten geschützt sind.           | Registrierung, Login und sichere Übertragung sind vorhanden; Passwortregeln implementiert. |
| US04 | Als Nutzer:in möchte ich Trainings manuell hinzufügen, wenn ich offline war.                      | Manuelle Eingabe möglich; Daten werden bei Verbindung synchronisiert.                      |
| US05 | Als Nutzer:in möchte ich meine Erfolge teilen, um motiviert zu bleiben.                           | Teilen über soziale Medien/Link-Funktion ist verfügbar.                                    |
| US06 | Als Nutzer:in möchte ich meine tägliche Trinkmenge erfassen, damit ich sehe, ob ich genug trinke. | Eingabe in ml möglich; Tagesziel und Fortschritt sichtbar.                                 |
| US07 | Als Nutzer:in möchte ich meine Mahlzeiten eingeben, um meinen Kalorienverbrauch zu verfolgen.     | Eingabe von Kalorienwerten möglich; Tagesgesamt wird berechnet und angezeigt.              |
| US08 | Als Nutzer:in möchte ich meine Schlafdauer erfassen, um meine Erholung zu beobachten.             | Schlafzeit-Eingabe möglich; Durchschnittswerte über Wochen werden angezeigt.               |
| US09 | Als Nutzer:in möchte ich mein Energielevel angeben, um meine Tagesform einzuschätzen.             | Skala (1–5) oder Auswahl (niedrig/mittel/hoch) vorhanden; Historie speicherbar.            |
| US10 | Als Nutzer:in möchte ich sehen, wie aktiv ich heute war, damit ich motiviert bleibe.              | Schritte, Bewegungszeit und geschätzter Kalorienverbrauch werden angezeigt.                |

## 6. Priorisierung (nach Aufwand und Wichtigkeit)

1️⃣ **Basisfunktionen (kurzfristig umsetzbar):**

- Hydration
- Ernährung (Kalorien)
- Schlaf (manuell)

2️⃣ **Erweiterung:**

- Bewegungserfassung (Schritte, Aktivitätszeit)

3️⃣ **Optionale Features:**

- Energielevel
- Kalorienberechnung
- Schlafanalyse

---

**Erstellt am:** 08.10.2025
