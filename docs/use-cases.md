# Aufgabe 2: Detaillierte Use Case Beschreibungen

---

## **UC-01: Aktivität erfassen**

**Kurzbeschreibung:**
Nutzer erfasst eine Aktivität (z. B. Joggen, Radfahren oder Training) manuell über die Aktivitätseingabemaske.

**Akteur(e):**

- Nutzer

**Auslöser:**

- Nutzer tippt im Hauptmenü auf „Aktivität hinzufügen“.

**Vorbedingungen:**

- Nutzer hat die App gestartet.
- App ist gestartet; Grunddaten (Alter, Gewicht etc.) sind hinterlegt.

**Normalablauf:**

1. Nutzer öffnet den Bereich **Aktivitäten**.
2. Wählt aus einer Dropdown-Liste die Art der Aktivität (z. B. Gehen, Radfahren, Workout).
3. Gibt die Dauer (in Minuten) und die Intensität manuell ein.
4. Klickt auf **Hinzufügen**.
5. App berechnet den geschätzten Kalorienverbrauch anhand der Grunddaten.
6. System speichert die Aktivität lokal (in SQLite-DB).

**Ergebnis:**

- Aktivität wird im Tagesprotokoll angezeigt.
- Die Kalorienbilanz wird eingerechnet („Verbraucht“ wird aktualisiert).

**Alternative Abläufe:**

- Nutzer verlässt die Eingabemaske ohne Speichern → keine Speicherung.
- Ungültige Eingabe (z. B. leere Dauer) → Fehlermeldung: _„Bitte alle Felder ausfüllen.“_
- Die Aktivität existiert noch nicht → Nutzer muss neue Aktivität erstellen (Name und MET-Wert).

---

## **UC-02: Ernährung erfassen**

**Kurzbeschreibung:**
Nutzer dokumentiert, was er gegessen oder getrunken hat, inklusive geschätzter Kalorien.

**Akteur(e):**

- Nutzer

**Auslöser:**

- Nutzer klickt auf „Ernährung hinzufügen“.

**Vorbedingungen:**

- Nutzer hat die App gestartet.
- Nutzer befindet sich auf der Start- oder Ernährungsseite.

**Normalablauf:**

1. Nutzer öffnet den Bereich **Ernährung**.
2. Tippt auf **„Nahrungsmittel hinzufügen“**.
3. Gibt Name der Mahlzeit oder des Getränks ein.
4. Trägt die Portionsgröße ein.
5. Klickt auf **„Hinzufügen“**.
6. App aktualisiert automatisch die tägliche Kalorienaufnahme.

**Ergebnis:**

- Eintrag wird in der Ernährungsliste angezeigt und in der Tagesbilanz berücksichtigt („Eingenommen“ wird aktualisiert).

**Alternative Abläufe:**

- Nutzer gibt kein Nahrungsmittel ein → App fordert zur Eingabe auf.
- App rundet Kalorienangaben automatisch.
- Das Nahrungsmittel existiert noch nicht → Nutzer muss ein neues Nahrungsmittel erstellen (Name und Kalorien).

---

## **UC-03: Grunddaten eingeben**

**Kurzbeschreibung:**
Nutzer gibt persönliche Basisdaten ein, die zur Berechnung des Kalorienverbrauchs dienen.

**Akteur(e):**

- Nutzer

**Auslöser:**

- Nutzer öffnet den Bereich **„Profil“** oder **„Einstellungen“**.

**Vorbedingungen:**

- App wurde mindestens einmal gestartet.

**Normalablauf:**

1. Nutzer klickt auf den Profilbutton oder Einstellungen.
2. Gibt Alter, Größe, Gewicht und Geschlecht ein.
3. Optional: legt Aktivitätslevel (z. B. niedrig, mittel, hoch) fest.
4. Tippt auf **„Speichern“**.
5. App speichert Werte lokal.
6. System verwendet die Daten für Kalorienberechnung in UC-01.

**Ergebnis:**

- Daten sind dauerhaft gespeichert.
- Daten können bei Bedarf bearbeitet werden.
- Geschätzter Grundumsatz (BMR) wird berechnet und angezeigt.

**Alternative Abläufe:**

- Nutzer überspringt Eingabe → Standardwerte werden genutzt.

---

## **UC-04: Kalorienauswertung anzeigen**

**Kurzbeschreibung:**
Nutzer sieht eine grafische Auswertung des täglichen Energiehaushalts (Kalorienaufnahme vs. Kalorienverbrauch).

**Akteur(e):**

- Nutzer

**Auslöser:**

- Nutzer öffnet im Menü den Bereich **„Statistik“**.

**Vorbedingungen:**

- Mindestens eine Aktivität oder Ernährung wurde erfasst.

**Normalablauf:**

1. App lädt alle gespeicherten Tagesdaten.
2. System summiert Kalorienaufnahme und -verbrauch.
3. App berechnet den täglichen Energiehaushalt (= Aufnahme – Verbrauch).
4. Ergebnisse werden in einem Balken- oder Kreisdiagramm dargestellt.
5. Nutzer kann Zeitraum filtern (Tag/Woche/Monat).

**Ergebnis:**

- Diagramm zeigt, ob Nutzer im Kalorienüberschuss oder -defizit ist.

**Alternative Abläufe:**

- Keine Daten vorhanden → nichts wird ausgewertet.
- Diagramm aktualisiert sich automatisch bei neuen Einträgen.
