**Test-Statistiken**

Anzahl Unit Tests: 30
Anzahl Integration Tests: 5
Gesamt Tests: 35
Alle Tests bestanden: ✅

**Code Coverage**

Tool: JaCoCo
Line Coverage: 11.8% (28 / 237 Lines)
Branch Coverage: 63.3%
Ziel erreicht (≥50%): ❌ (bezogen auf Line Coverage)

**Coverage pro Klasse**
T
BMR / Nutzer (model)
UserProfile: 100% ✅
BmrCalculator: 83.3% ✅
Status: ✅ sehr gut getestet

**TDD-Feature**
Feature: Alle Food-Klassen

**Lessons Learned (4–5 Punkte)**

Was habe ich über Testing gelernt?
Unit-Tests sichern die Fachlogik ab und geben sofort Feedback, wenn sich Berechnungen oder Datenmodelle durch Änderungen ungewollt verändern.

Unterschied Unit vs. Integration Tests?
Unit-Tests testen eine Klasse isoliert (z. B. Berechnung), Integrationstests testen das Zusammenspiel mehrerer Komponenten (z. B. Controller + View + Repository + UserContext).

TDD: Vor- und Nachteile?
Vorteil: Anforderungen werden als Tests klar spezifiziert, Code wird automatisch testbarer. 
Nachteil: Anfangs mehr Aufwand und man muss Design/Schnittstellen vorher sauber denken.

Welche Fehler wurden durch Tests gefunden?
Typische Fehler waren Eingabevalidierungen (z. B. Dauer “abc”, Dauer ≤ 0), Testmethoden-Signaturen (private @Test wird nicht ausgeführt) und Typkonflikte (double/int), außerdem Projektstruktur-Probleme (pom.xml/Root-Pfad), die Builds sofort sichtbar machen.

Wie werde ich in Zukunft testen?
Neue Features zuerst als Tests formulieren (TDD), bestehende Teile schrittweise mit Unit-Tests absichern und zusätzlich wenige Integrationstests für kritische Flows beibehalten.