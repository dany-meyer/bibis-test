# bibis-test – Übungen „Test und Qualität“

Übungsprojekt zum Kurs *Software Engineering und kooperatives Projektmanagement* (HNU, WiSe 2026/27).
Durchgängiges Beispiel: das **BibIS** (Bibliotheks-Informationssystem).

## Voraussetzungen

- JDK > 21
- IntelliJ IDEA (Maven ist eingebaut)

## Projekt öffnen

IntelliJ → *File → New → Project from Version Control* → URL dieses Repositorys.
Maven lädt JUnit 6, Mockito und Playwright automatisch.

**Vor der Übung ** einmal alle Tests ausführen (`mvn test` oder Rechtsklick auf `src/test/java` → *Run Tests*).
Playwright lädt dabei die Browser herunter (einige Hundert MB).

## Inhalt

| Ordner | Wofür |
|---|---|
| `src/main/java/de/hnu/bibis/ausleihe` | Verlängerungsregel, Ausleihe, VerlaengerungsService, VormerkungsRepository |
| `src/main/java/de/hnu/bibis/statistik` | `AusleiheStatistik` – Übung 1 (statische Analyse) |
| `src/test/java` | hier schreiben Sie Ihre Tests |
| `src/test/java/de/hnu/bibis/e2e` | `SucheE2ETest` – Beispiel für einen End-to-End-Test |
| `bibis-web/index.html` | kleine BibIS-Oberfläche für die End-to-End-Tests (Übung 7) |

## Playwright Codegen

```bash
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="codegen"
```

Im geöffneten Browser die Datei `bibis-web/index.html` öffnen (Adresszeile: `file:///…/bibis-test/bibis-web/index.html`),
klicken und den erzeugten Code im Inspector-Fenster lesen.

Zum Zuschauen beim Testlauf: in der Run-Konfiguration die Umgebungsvariable `PWDEBUG=1` setzen.

## Lasttest mit k6 (optional)

k6 installieren (`brew install k6`, unter Windows `winget install k6 --source winget`), dann im Ordner `bibis-test`:

```bash
python3 -m http.server 8080 --directory bibis-web   # Terminal 1: Oberfläche starten
k6 run src/test/k6/lasttest.js                      # Terminal 2: Lasttest ausführen
```
