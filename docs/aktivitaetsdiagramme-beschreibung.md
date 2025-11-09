# Aktivitätsdiagramm – Use Case „Aktivität erfassen“

**Gewählter Use Case:**  
Ein Nutzer erfasst eine Aktivität (z. B. Joggen oder Radfahren) manuell in der App.

**Ablauf:**  
Der Nutzer öffnet den Bereich „Aktivität“, wählt den Aktivitätstyp aus und gibt Dauer sowie Intensität ein.  
Das System prüft, ob alle Felder vollständig und gültig ausgefüllt sind.  
Wenn ja, wird der geschätzte Kalorienverbrauch berechnet, die Aktivität in der lokalen Datenbank gespeichert  
und eine Bestätigung angezeigt. Gleichzeitig wird die Kalorienbilanz des Tages aktualisiert.

**Wichtige Entscheidungspunkte:**

- Sind alle Eingaben vorhanden und korrekt?
- Wird die Aktivität gespeichert oder muss der Nutzer erneut eingeben?

**Fehler- und Ausnahmefälle:**

- Bei unvollständigen oder ungültigen Eingaben zeigt die App eine Fehlermeldung an („Bitte alle Felder ausfüllen“).
- Der Nutzer wird zurück zur Eingabemaske geleitet; keine Speicherung findet statt.
- Nur gültige Eingaben führen zur Speicherung und zur Aktualisierung der Tagesbilanz.

## Sequenzdiagramm 1: UC-01 – Aktivität erfassen

**Welche Interaktion wird gezeigt?**  
Nutzer erfasst eine Aktivität (Typ, Dauer, Intensität, Datum). Das System berechnet den Kalorienverbrauch und speichert den ActivityRecord.

**Welche Klassen sind beteiligt?**  
User (Actor), ActivityController, ActivityService, CaloriesCalculator, ActivityRepository (SQLite)

**Was ist die zentrale Nachricht/Funktionalität?**  
`estimateCalories(user, type, duration, intensity)` → der CaloriesCalculator liefert `kcal` zurück; anschließend `save(ActivityRecord{..., kcal})`.

---

## Sequenzdiagramm 2: UC-02 – Ernährung erfassen

**Welche Interaktion wird gezeigt?**  
Nutzer fügt einen Ernährungseintrag hinzu (Food, Portion, kcal/Einheit). Das System berechnet die Gesamt-kcal und aktualisiert die Tagesbilanz.

**Welche Klassen sind beteiligt?**  
User (Actor), NutritionController, NutritionService, FoodRepository (SQLite), ReportRepository (SQLite)

**Was ist die zentrale Nachricht/Funktionalität?**  
`updateDailyIn(userId, date, kcal = portionSize * caloriesPerUnit)` → Tageskalorien **Eingenommen** werden erhöht; vorher `save(FoodRecord{...})`.
