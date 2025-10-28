# 📄 Lastenheft – FitnessTracker App  
**Team 1**  
**Modul:** Softwaretechnik  
**Version:** 1.0  
**Stand:** Oktober 2025  

## 1. Einleitung

**Projektname:** FitnessTracker App  
**Auftraggeber:** Team 1 (Softwaretechnik-Modul)  
**Auftragnehmer:** Team 1 selbst (Entwicklungsteam)

### Ziel des Projekts
Entwicklung einer mobilen Fitness- und Gesundheits-App zur **Erfassung und Auswertung** von Aktivitäten, Ernährung und Energiehaushalt, um das **Wohlbefinden und die Selbstwahrnehmung** der Nutzer zu verbessern.

---

## 2. Ausgangssituation & Motivation

Viele Fitness-Apps sind komplex, erfordern Registrierung oder senden Daten in die Cloud.  
Ziel unseres Projekts ist eine **einfache, lokal funktionierende App**, die **2–3 Kernfunktionen** zuverlässig abbildet:

- Aktivitätserfassung 🏃  
- Ernährungserfassung 🍽️  
- Kalorienbilanz 📊

Optional kann die App später um Login, Wearables oder KI-Funktionen erweitert werden.

---

## 3. Zielsetzung

- Nutzerfreundliche mobile App mit Fokus auf **Usability**  
- **Manuelle** Erfassung von Aktivitäten und Ernährung  
- Berechnung des **täglichen Kalorienhaushalts**  
- Anzeige einfacher **Statistiken (Diagramme)**  
- Speicherung lokal auf dem Gerät (z. B. SQLite)  
- Erweiterbar um zusätzliche Funktionen

---

## 4. Produktfunktionen

| ID   | Funktion                        | Beschreibung                                                                            |
|------|---------------------------------|-----------------------------------------------------------------------------------------|
| LF01 | Aktivität erfassen              | Nutzer kann Aktivität (Typ, Dauer, Intensität) eingeben und Kalorienverbrauch berechnen |
| LF02 | Ernährung erfassen              | Nutzer kann Mahlzeiten/Getränke eintragen, Kalorienaufnahme wird summiert               |
| LF03 | Kalorienauswertung anzeigen     | Visuelle Darstellung von Aufnahme vs. Verbrauch (Diagramm)                              |
| LF04 | Grunddaten eingeben             | Eingabe von Gewicht, Größe, Alter, Geschlecht                                           |
| LF05 | Validierung & UX                | Einfache Bedienung, Eingabeprüfung, kein Cloud-Zwang                                    |

---

## 5. Produktdaten

- **Aktivität:** Typ, Dauer, Intensität, kcal, Datum/Zeit  
- **Ernährung:** Name, Menge, kcal pro Einheit, kcal gesamt, Datum/Zeit  
- **Profil:** Alter, Gewicht, Größe, Geschlecht, Aktivitätslevel  
- **Statistik:** Summierte Werte Intake / Burn / Balance

---

## 6. Qualitätsanforderungen

| Kriterium             | Anforderung                                                   |
|-----------------------|---------------------------------------------------------------|
| Performance           | App startet in max. 3 Sekunden                                |
| Usability             | Intuitive Bedienung, einfache Navigation                      |
| Verfügbarkeit         | Mind. 99 % lokal                                              |
| Datenschutz           | DSGVO-konforme Speicherung, keine Cloud                       |
| Portabilität          | Android oder iOS 

---

## 7. Randbedingungen

- Entwicklung im Rahmen des Moduls *Softwaretechnik*  
- Teamgröße: 5 Personen  
- Technologie: (Frontend + lokale DB)  
- Keine Server- oder Cloud-Infrastruktur  
- Fokus: Kernfunktionen, einfache Architektur

---

## 8. Use Cases (Auszug)

| UC-ID | Titel                       | Beschreibung                                                        |
|-------|---------------------------- |---------------------------------------------------------------------|
| UC-01 | Aktivität erfassen          | Aktivität manuell eingeben, Kalorienverbrauch berechnen             |
| UC-02 | Ernährung erfassen          | Mahlzeit eingeben, Kalorienaufnahme summieren                       |
| UC-03 | Grunddaten eingeben         | Basisdaten speichern für Berechnungen                               |
| UC-04 | Kalorienauswertung anzeigen | Diagramm Intake vs. Verbrauch anzeigen                              |

---

## 9. Abnahmekriterien

- Alle Muss-Anforderungen (Prio A) sind erfüllt ✅  
- UC-01, UC-02, UC-03 und UC-04 laufen fehlerfrei  
- Die App funktioniert **ohne Login** lokal  
- Keine Abstürze oder schwerwiegenden Bugs  
- Dokumentation (Lastenheft, Pflichtenheft, Code) ist vollständig  
- Präsentation inkl. Testdaten möglich

---

## 10. Autoren / Teamrollen

| Name                    | Rolle                          | Aufgabenbereich                            |
|-------------------------|--------------------------------|--------------------------------------------|
| Sönmez Süner            | Frontend & UX                  | UI, Profil, Statistik, Design              |
| Jost Nadwornik          | Product Owner & Fullstack      | UI Anforderungsmanagement, Aktivität       |
| Oksana Orel             | Fullstack & UX                 | Ernährung, UI-Validierungen                |
| Volodymyr Rusobrov      | Backend                        | DB-Anbindung, Statistik                    |
| Magdalena Kretschmer    | Backend & UX                   | Datenbank, Datenmodelle                    |

---

## 11. Glossar (Auszug)

- **Intake** → Kalorienaufnahme durch Mahlzeiten  
- **Burn** → Kalorienverbrauch durch Aktivitäten  
- **Balance** → Intake – Burn (positive = Überschuss, negative = Defizit)  
- **BMR/TDEE** → Grundumsatz / Gesamtumsatz (optional)  
- **MET-Wert** → Standardwert für Energieverbrauch bei Aktivität

---

© 2025 Team 1 – Softwaretechnik, FitnessTracker App
