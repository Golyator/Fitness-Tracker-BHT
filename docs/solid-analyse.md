# ✅ **Aufgabe 1c – Verbesserungen dokumentieren**

## **SOLID-Analyse für das Fitness-Tracker-Projekt**

### **1. Bereits gut eingehaltene SOLID-Prinzipien**

- **SRP (Single Responsibility Principle)**
  Die Domain-Klassen `User`, `FoodRecord`, `ActivityRecord` sind klar fokussiert auf eine einzelne Aufgabe.

- **LSP (Liskov Substitution Principle)**
  Wird eingehalten, da aktuell keine Vererbung verwendet wird.

### **2. Gefundene Verstöße / Risiken**

#### **SRP-Verstoß – Klasse `Report`**

- aggregiert Daten
- berechnet Kalorien
  Hier haben wir zwei Verantwortlichkeiten, daher Verstoß.

#### **OCP-Verstoß – Verwendung von enums**

- `ActivityType` und `FoodType` sind enums
- neue Typenänderung am enum bedeuten Veränderung in bestehenden Codes,was OCP verletzt

#### **DIP-Verstoß – fehlende Abstraktionen**

- `Report` greift direkt auf konkrete Klassen zu

### **3. Konkrete Verbesserungsvorschläge**

#### **(1) Report aufspalten**

Report = nur Datencontainer
ReportService = berechnet Kalorien (Business-Logik)

#### **(2) Strategy-Pattern einführen**

Ersetzt enums durch polymorphe Klassen:

```java
interface Activity { float calculateCalories(...); }
class Running implements Activity { ... }
```

Dadurch sind Erweiterungen ohne Änderungen möglich und OCP ist erfüllt.

#### **(3) Abstraktionen / Interfaces einführen**

Beispiel:

```java
interface ActivityRepository { List<ActivityRecord> findByUser(User u); }
interface FoodRepository { List<FoodRecord> findByUser(User u); }
```

→ DIP erfüllt
