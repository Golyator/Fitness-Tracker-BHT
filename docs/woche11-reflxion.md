# Woche 11 – Reflexion & Lessons Learned

**Thema:** Buildmanagement & CI/CD

## 1. Was haben wir in dieser Woche gelernt?

In dieser Woche haben wir gelernt, was Buildmanagement ist und warum es ein zentraler Bestandteil professioneller Softwareentwicklung ist. Uns ist klar geworden, dass ein Build-System nicht nur zum Kompilieren dient, sondern auch Abhängigkeiten verwaltet, Tests ausführt und reproduzierbare Builds ermöglicht.

Zusätzlich haben wir verstanden, wie Continuous Integration (CI) funktioniert und welchen Mehrwert eine CI/CD-Pipeline bietet. Besonders wichtig war für uns die Erkenntnis, dass Tests automatisch bei jedem Push ausgeführt werden und Fehler dadurch sehr früh erkannt werden können.

## 2. Was hat gut funktioniert?

Gut funktioniert hat die Einrichtung des Build-Systems sowie das lokale Ausführen der Build- und Testbefehle. Auch das Einrichten der CI-Pipeline mit GitHub Actions war für uns gut nachvollziehbar, da die einzelnen Schritte klar strukturiert sind (Checkout, Setup, Build, Test).

Besonders hilfreich war es für uns, die Pipeline direkt nach dem Push im Repository beobachten zu können und sofort Feedback über den Build-Status zu erhalten.

## 3. Welche Probleme oder Herausforderungen gab es?

Eine Herausforderung für uns war die korrekte Konfiguration der Pipeline, insbesondere kleine Fehler in der YAML-Datei (z. B. Einrückungen oder falsche Befehle). Außerdem mussten wir darauf achten, dass Build-Artefakte korrekt ignoriert werden und nicht im Git-Repository landen.

Auch das Verständnis der vielen neuen Begriffe (CI, CD, Artefakte, Caching, Quality Gates) war für uns anfangs etwas anspruchsvoll.

## 4. Warum ist CI/CD in der Praxis so wichtig?

CI/CD ist wichtig, weil es die Qualität von Software erhöht und den Entwicklungsprozess effizienter macht. Fehler werden früh erkannt, Builds sind reproduzierbar und das Team erhält sofort Feedback über den Zustand des Projekts. Für größere Projekte ist CI/CD aus unserer Sicht unverzichtbar, um stabile Releases sicherzustellen und manuelle Fehler zu vermeiden.

## 5. Was würden wir beim nächsten Projekt anders oder besser machen?

Beim nächsten Projekt würden wir das Build-System und die CI-Pipeline deutlich früher einrichten und nicht erst am Ende. Außerdem würden wir von Anfang an automatisierte Tests konsequent schreiben, damit die Pipeline einen echten Mehrwert liefert.

Zusätzlich würden wir uns intensiver mit erweiterten Features wie Code Coverage, Quality Gates und automatischer Versionierung beschäftigen.

## 6. Persönliches Fazit

Buildmanagement und CI/CD haben uns gezeigt, wie professionell Softwareentwicklung in der Praxis abläuft. Auch wenn die Einrichtung am Anfang etwas Zeit kostet, spart sie langfristig viel Arbeit und erhöht die Qualität des Projekts deutlich.
