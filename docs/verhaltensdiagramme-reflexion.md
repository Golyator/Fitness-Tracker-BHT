**Liste aller erstellten Diagramme:**
Klassendiagramme:
klassendiagramm-v0.1.png
klassendiagramm-v1.0.png
-> Hier findet man die Klassen: User, Report, ActivityRecord und FoodRecord

Sequenzdiagramme:
  Sequenzdiagramm_UC_01.png
  Sequenzdiagramm_UC_02.png
  Sequenzdiagramm_UC_03.png
    -> Hier findet man die Klassen/Objekten: User, Report, ActivityRecord und FoodRecord

Zustandsdiagramme:
zustandsdigaramm-kalorienverbrauch.png
-> Hier wird der Zustand dargestellt, was passiert wenn man in der App den Kalorienverbauch anzeigen lassen möchte

Aktivitätsdiagramm:
aktivitaetsdiagramm-[aktivitaet-erfassen].png
-> Hier wird beschrieben, wie der Ablauf eines Prozesses(UC-1: Aktivität erfassen) – also wie sich ein Benutzer oder das System Schritt für Schritt verhält.


2. Was zeigen Ihre Diagramme? (3-4 Sätze). Was ist der wichtigste Ablauf in Ihrem System? Welche
   Objektinteraktionen sind zentral? Welche Erkenntnisse haben Sie beim Modellieren gewonnen?

siehe aktivitaetsdiagramme-beschreibung.md

3. Unterschied statisch vs. dynamisch (3-4 Sätze). Was zeigt das Klassendiagramm (Woche 5)? Was zeigen die
   Verhaltensdiagramme (diese Woche)? Warum braucht man beides?

Der Unterschied zwischen **statischer** und **dynamischer Modellierung** liegt darin, dass die statische Modellierung den **Aufbau und die Struktur** eines Systems beschreibt, während die dynamische Modellierung das **Verhalten und den Ablauf** zur Laufzeit darstellt.
Ein **Klassendiagramm** (statisch) zeigt Klassen, Attribute, Methoden und ihre Beziehungen – also den „Bauplan“ der Software.
Die **Verhaltensdiagramme** (dynamisch) wie Aktivitäts-, Sequenz- oder Zustandsdiagramme zeigen dagegen, **wie Objekte interagieren und Prozesse ablaufen**.
Beides ist wichtig, weil man nur durch die Kombination aus Struktur (**was existiert**) und Verhalten (**wie es funktioniert**) ein vollständiges und verständliches Modell des Systems erhält.

4. Herausforderungen & Lessons Learned (3-4 Stichpunkte). Was war schwierig beim Erstellen der Diagramme?
   Welche Erkenntnisse haben Sie über Ihr System gewonnen? Was würden Sie beim nächsten Mal anders
   machen?

Beim Erstellen des Zustandsdiagrammes fällt es schwer nicht in andere schon bekannte Diagrammarten zu verfallen, da das Zustandsdiagramm doch gewisse Eigenarten hat, wie die Entscheidungs-Raute, die keine Ja/Nein-Abfrage ist.
Abgesehen davon wird nicht ganz klar, wie detailiert und kleinschrittig ein Zustandsdiagramm werden soll.
Durch die Sequenzdiagramme wurde deutlich, wie stark die Klassen und Objekte (z. B. Aktivität, Nutzer) miteinander interagieren. Das half, die Verantwortlichkeiten besser zu trennen.
