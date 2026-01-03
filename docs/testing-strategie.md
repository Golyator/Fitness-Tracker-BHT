# Testing-Strategie

## Unit Tests (isoliert)
**Getestete Klassen:**
**Training / Aktivität**
ActivityCalculatorTest: 4 Tests
ActivityTypeTest: 3 Tests
ActivityRecordTest: 1 Test
Summe Training: 8 Tests

**Ernährung**
FoodTypeTest: 2 Tests
FoodRecordTest: 2 Tests
Summe Ernährung: 4 Tests

**Grundlage / Nutzer & Zusammenfassung**
UserProfileTest: 6 Tests
BmrCalculatorTest: 2 Tests
DailySummaryTest: 10 Tests


**Was wird getestet:**
Konstruktor/Objektzustand: Ob Objekte korrekt erstellt werden (z. B. Werte gespeichert, Standardwerte wie BMR anfangs sinnvoll).
Getter/Setter: Ob Daten korrekt zurückgegeben/gesetzt werden (z. B. setBmr() / getBmr()).
Geschäftslogik: Reine Berechnungen und Regeln, z. B.
    BMR-Berechnung (Formel + Geschlechtszweig)
    Kalorienberechnung bei Aktivitäten (MET/Dauer/Gewicht)
    Kalorienberechnung bei Nahrung (Portion × kcal/Einheit)
    Aggregation/Summenbildung im Daily Summary (In/Out/Netto pro Tag)

## Integration Tests (Zusammenspiel)
**Getestete Interaktionen:**
ActivityControllerFxIT: 5 Tests
(Controller + View + Repository + CurrentUserContext + DailySummaryController)

**Was wird getestet:**
Validierungs- und Fehlermeldungslogik im Controller:
    kein Nutzerprofil gesetzt → Fehler + kein Speichern
    Dauer nicht parsbar ("abc") → spezifische Fehlermeldung + kein Speichern
    Dauer ≤ 0 → Fehlermeldung + kein Speichern
    Aktivitätstyp fehlt → Fehlermeldung + kein Speichern
    Intensität fehlt → erwartetes Abbruchverhalten (bei euch wird zumindest geprüft, dass keine Kalorien addiert werden)
Nebenwirkungen / Zusammenarbeit über mehrere Komponenten:
    Repository wird bei Fehlerfällen nicht aufgerufen (verifyNoInteractions(repository))
    Daily Summary wird nicht verändert (totalActivityCalories bleibt 0)

## Lessons Learned (2-3 Sätze)
Unit-Tests prüfen einzelne Klassen isoliert und machen Fehler in Rechenlogik oder Datenhaltung schnell sichtbar. Integrationstests prüfen das Zusammenspiel mehrerer Komponenten (z. B. Controller ↔ View ↔ Repository ↔ Context) und decken typische „Wiring“-Probleme auf, wie falsche Validierung oder unerwünschtes Speichern trotz Fehler. Durch die Tests wurden u. a. Eingabe- und Validierungsfälle (z. B. ungültige Dauer, fehlender Nutzer/Typ) sauber abgesichert; außerdem sind typische Typ-Probleme (double/int) schnell aufgefallen, weil die Tests beim Kompilieren/Build sofort knallen.