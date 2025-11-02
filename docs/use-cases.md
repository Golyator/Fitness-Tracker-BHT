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


**Vorschläge von weiteren Use Cases basierend auf ChatGPT 5:**
**Aufgabenblatt 4 Aufgabe 4**

|UC-ID|	Titel 	                       |Kurzbeschreibung	                                                                   |Kategorie              |
|-----|--------------------------------|-------------------------------------------------------------------------------------|-----------------------|
|UC-05|	Ziele & Pläne festlegen	       |Gewichts-/Aktivitätsziele, Kalorienziel, Wochenplan definieren	                     |Verwaltung             |
|UC-06|	Erinnerungen & Nudging	       |Benachrichtigungen für Mahlzeiten, Wasser, Schritte; Ruhezeiten	                     |Verwaltung             |
|UC-07|	Lebensmittel/Rezepte verwalten |Eigene Lebensmittel/Rezepte anlegen, Favoriten, Portionen, Barcode-Scan	             |Verwaltung             |
|UC-08|	Einträge korrigieren/Undo	     |Bearbeiten, löschen, Undo/Redo, Änderungsverlauf	                                   |Verwaltung             |
|UC-09|	Datenimport/-export	           |CSV/JSON-Export, Import aus Apple Health/Google Fit/Open Food Facts	                 |Verwaltung             |
|UC-10|	Geräte-/App-Sync	             |Anbindung Wearables (Garmin, Fitbit), bidirektionaler Sync	                         |Verwaltung             |
|UC-11|	Offline & Konfliktlösung	     |Offline erfassen; Merge-Strategie bei Sync-Konflikten	                               |Fehlerszenario         |
|UC-12|	Auth & Konto	                 |Registrierung, Login, Passwort-Reset, 2FA, Geräteverwaltung	                         |Verwaltung             |
|UC-13|	Datenschutz & Einwilligung	   |DSGVO-Consent, Datenlöschung (Right to be forgotten), Export personenbezogener Daten |Verwaltung/Compliance  |
|UC-14|	Backup & Wiederherstellung	   |Automatische/backups, punktgenaue Wiederherstellung	                                 |Wartung                |
|UC-15|	Anomalie-/Plausicheck	         |Unplausible Werte erkennen (−Kalorien, 10.000 kcal/Tag), Duplikate warnen	           |Fehlerszenario         |
|UC-16|	Externe-Dienste-Ausfall	       |Fallback bei API-/Store-/Push-Ausfall, Graceful Degradation	                         |Fehlerszenario         |
|UC-17|	Monitoring & Logging	         |Health-Checks, Metriken (Latenz, Fehlerquote), Audit-Log für Datenänderungen	       |Wartung                |
|UC-18|	Content-Kuration	             |Admin pflegt Nährwertdatenbank, Versionierung von Datensätzen	                       |Wartung/Verwaltung     |
|UC-19|	Support & Feedback	           |In-App-Feedback, Bugreport (mit Log-/Screenshot-Anhang), Statusseite	               |Wartung                |
|UC-20|	Rollen & Sharing	             |Daten selektiv mit Coach/Arzt teilen (Read-Only/Zeitraum)	                           |Verwaltung             |
|UC-21|	Abrechnung & Lizenzen	         |Abo-Verwaltung, Prüfungen (Free/Premium), Belege/Erstattungen	                       |Verwaltung             |
|UC-22|	Barrierefreiheit & Sprache	   |Schriftgrößen, Kontraste, Screenreader, Mehrsprachigkeit	                           |Verwaltung             |
|UC-23|	Release- & Wartungsfenster	   |Geplante Wartungen ankündigen, Read-only-Modus, Rollback	                           |Wartung                |
|UC-24|	Sicherheit & Sessions	         |Gerät sperren, Session-Liste, Forced Logout, Token-Revocation	                       |Wartung/Security       |

**Verbesserungsvorschläge für UC-02**

Vollständigkeit & Logik des Ablaufs

Der Normalablauf ist logisch aufgebaut, klar und verständlich formuliert. Die Schritte folgen einer realistischen Nutzerinteraktion.
Ein paar Feinheiten zur Präzisierung wären aber sinnvoll:

✅ Positiv:

Die Sequenz ist schlüssig (Start → Eingabe → Bestätigung → Aktualisierung).
Die Aktion „App aktualisiert automatisch die tägliche Kalorienaufnahme“ ist funktional korrekt und schließt den Vorgang gut ab.

🔧 Verbesserungsvorschläge:

Nach Schritt 5 („Hinzufügen“) wäre eine Bestätigung oder visuelle Rückmeldung üblich, z. B.:
„App zeigt Snack als neuen Eintrag in der Tagesliste an.“
→ Das hilft bei der Trennung von System- und Nutzeraktionen.

Erwähne optional Zeitpunkt oder Mahlzeitenkategorie (Frühstück, Mittag etc.), da das oft Teil eines Ernährungstrackers ist.
→ „Nutzer wählt Mahlzeitenkategorie oder Zeitpunkt.“

2️⃣ Fehlende Alternativen & Fehlerszenarien

Du hast bereits gute Alternativen drin, aber folgende ergänzende Szenarien sind in der Praxis relevant:

Fehlerszenario / Alternative	Systemverhalten / Reaktion
Verbindung verloren (Cloud-Sync)	App speichert Eintrag lokal und synchronisiert später (Offline-Modus).
Ungültige Eingabe (z. B. keine Zahl bei Portionsgröße)	App zeigt Fehlermeldung: „Bitte gültige Zahl eingeben.“
Doppelte Eingabe erkannt	App warnt: „Dieses Lebensmittel wurde bereits heute hinzugefügt. Trotzdem übernehmen?“
Nahrungsmittel unbekannt / nicht gefunden	Option: „Neues Lebensmittel anlegen“ oder Suche in Online-Datenbank.
Kalorienwert ungewöhnlich hoch/niedrig	App fragt nach Bestätigung („Sicher, dass 12.000 kcal korrekt sind?“).
Abbruch	Nutzer kann Eingabe abbrechen → App speichert nichts, bleibt im vorherigen Zustand.

Diese Punkte verbessern die Robustheit und zeigen, dass du auch Fehler und Datenvalidierung durchdenkst.

3️⃣ Vor- und Nachbedingungen

Die sind grundsätzlich richtig, aber etwas zu knapp.
Hier die überarbeitete, klarere Formulierung:

Vorbedingungen:
Nutzer ist eingeloggt (oder im Gastmodus).
App ist gestartet und Datenbank verfügbar (lokal oder über Server).
Nutzer befindet sich auf der Seite „Ernährung“ oder einer entsprechenden Ansicht.

Nachbedingungen:
Neuer Ernährungseintrag ist in der Tagesliste gespeichert (persistente Speicherung).
Tagesbilanz (Kalorienaufnahme) ist aktualisiert.
Systemstatus: synchronisiert oder markiert Eintrag als „offline gespeichert“.

4️⃣ Fazit (kurz und ehrlich)
Aspekt	Bewertung	Kommentar
Struktur	✅ Sehr gut	Klare, nachvollziehbare Gliederung
Ablauflogik	✅ Logisch	Kleine Erweiterungen sinnvoll (Feedback, Kategorie)
Alternativen / Fehler	⚠️ Teilweise	Offline-, Validierungs- und Dublettenfälle ergänzen
Vor-/Nachbedingungen	⚠️ Etwas knapp	Login & Persistenz ergänzen

**Reflexion**

Die KI hat den Use Case ausführlich analysiert und zu jedem Bereich sinnvolle Ergänzungen gemacht. 
Vor allem zu Vor- und Nachbedingungen war ich positiv überrascht, denn an die technischen Aspekte der Nachbedingungen und er Alternativen Abläufe, 
also das Speichern der neuen Daten oder was tun bei Verbindungsverlust als zwei Beispiele hatte ich im ersten Moment nicht gedacht.  
Aber mir ist auch aufgefallen, dass er einen alternativen Ablauf als neu angegeben hat, obwohl ich ihn im Prompt drin hatte, es geht um den Fall, 
dass ein Lebensmittel nicht vorhanden ist. Da fällt auf, dass die KI zwar analysiert aber vielleicht auch nicht alles in 
Detail und das da eine Überarbeitung unbedingt notwendig ist. 
