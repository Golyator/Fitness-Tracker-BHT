**Platform:** GitHub Actions

## Automatisierte Schritte

1. ✅ Code auschecken
2. ✅ Umgebung einrichten (Java 21)
3. ✅ Dependencies laden (gecacht mit Maven)
4. ✅ Projekt kompilieren
5. ✅ Tests ausführen (JUnit 5)
6. ✅ Code Coverage Report generieren (JaCoCo)
7. ✅ JAR-Artefakt erstellen

## Trigger

- Bei Push auf `main` Branch
- Bei Push auf `develop` Branch
- Bei Pull Requests gegen `main` Branch

## Pipeline-Dauer

- Durchschnittlich: ca. 35-45 Sekunden

## Artefakte

- **JAR-Datei:** `target/fitness-tracker-bht-1.0-SNAPSHOT.jar`
- **Test-Reports:** `target/surefire-reports/`
- **Coverage-Report:** `target/site/jacoco/`
- Abrufbar unter jedem erfolgreichen Pipeline-Run z.B. [GitHub Actions - Merge pull request #22 from Golyator/feature-rework-cicd-pipeline #14](https://github.com/Golyator/Fitness-Tracker-BHT/actions/runs/20677516271)

## Links

- Pipeline-Runs: [GitHub Actions](https://github.com/Golyator/Fitness-Tracker-BHT/actions)
- Letzter erfolgreicher Build: [URL](https://github.com/Golyator/Fitness-Tracker-BHT/actions/runs/20677516271)

### Automatische Versionierung

- Format: 1.0.[Git-Commit-Count]
- Beispiel: 1.0.42
- Build-Info-Datei wird generiert

## Quality Gates

- ✅ Code Coverage muss ≥ 50% sein
- ✅ Alle Tests müssen bestehen
- ✅ Code muss kompilieren
- ✅ Maven Build erfolgreich
- ✅ Artefakte (Applikation, Tests und Test Coverage Report) werden erfolgreich erstellt

### Branch-Strategie

- `main`: Stable releases
- `develop`: Integration branch
- `feature-*`: Feature branches werden getestet
