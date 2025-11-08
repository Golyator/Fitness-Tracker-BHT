# Design-Entscheidungen

## 1. Wichtigste Designentscheidungen

Die Klassenstruktur wurde so gewählt, dass jede Entität (`User`, `ActivityRecord`, `FoodRecord`, `Report`) eine klar abgegrenzte Verantwortung hat.  
Der `User` verwaltet persönliche Daten und aggregierte Berechnungen, während `ActivityRecord` und `FoodRecord` einzelne Aktivitäten bzw. Mahlzeiten erfassen.  
Die Klasse `Report` dient zur periodischen Auswertung dieser Daten, um Kalorienverbrauch und -aufnahme zu vergleichen.  
Alternativ hätte man eine zentralisierte **„Tracker“**-Klasse erstellen können, die alle Berechnungen übernimmt; jedoch fördert die aktuelle Aufteilung bessere **Modularität**, **Wartbarkeit** und spätere **Erweiterbarkeit** (z. B. durch neue Record-Typen oder Report-Formate).

## 2. Offene Fragen

- Sollten `ActivityType` und `FoodType` zukünftig in separate Datenbanken oder Konfigurationsdateien ausgelagert werden, um dynamisch erweiterbar zu sein?
- Wie genau soll die **Kalorienberechnung** für Aktivitäten und Lebensmittel erfolgen – über feste Formeln oder externe APIs?
- Wird der **Report** automatisch generiert (z. B. täglich) oder manuell durch den Benutzer ausgelöst?
