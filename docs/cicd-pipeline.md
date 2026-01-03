# CI/CD Pipeline

**Platform:** GitHub Actions / GitLab CI

## Automatisierte Schritte
1. ✅ Code auschecken
2. ✅ Umgebung einrichten (Java 21)
3. ✅ Dependencies laden (gecacht)
4. ✅ Projekt kompilieren
5. ✅ Tests ausführen 
6. ✅ Coverage-Report generieren
7. ✅ JAR-Artefakt erstellen

## Trigger
- Bei Push auf `main` Branch
- Bei Pull Requests

## Pipeline-Dauer
- Durchschnittlich: ca. 30 Sekunden

## Artefakte
- JAR-Datei: `target/fitness-tracker-bht-1.0-SNAPSHOT.jar`
- Test-Reports: `target/surefire-reports/`
- Coverage-Report: `target/site/jacoco/`

## Links
- Pipeline-Runs: https://github.com/Golyator/Fitness-Tracker-BHT/actions
- Letzter erfolgreicher Build: https://github.com/Golyator/Fitness-Tracker-BHT/actions/workflows/ci.yml?query=branch%3Amain+is%3Asuccess&per_page=1

## Erweiterte Features

### Automatische Versionierung
- Format: 1.0.[Git-Commit-Count]
- Beispiel: 1.0.42
- Build-Info-Datei wird generiert

### Quality Gates
- ✅ Code Coverage muss ≥ 50% sein
- ✅ Alle Tests müssen bestehen
- ✅ Code muss kompilieren

### Branch-Strategie
- `main`: Stable releases
- `development`: Integration branch
- `feature/*`: Feature branches werden getestet
```