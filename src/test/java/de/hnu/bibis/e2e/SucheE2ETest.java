package de.hnu.bibis.e2e;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Beispiel für einen End-to-End-Test mit Playwright.
 *
 * <p><b>Wie Playwright aufgebaut ist:</b> Der eigentliche Kern ist ein Treiber in Node.js,
 * der die Browser (Chromium, Firefox, WebKit) steuert. Java, Python, C# und JavaScript
 * sind nur Sprachbindungen: Sie bringen den Treiber mit und schicken ihm die Befehle.
 * Locators, automatisches Warten und Codegen funktionieren daher in allen Sprachen gleich.
 * Unterschiede gibt es nur in der Schreibweise, z. B. {@code getByRole} / {@code assertThat}
 * in Java statt {@code get_by_role} / {@code expect} in Python.</p>
 *
 * <p><b>Zu diesem Test:</b></p>
 * <ul>
 *   <li>Die BibIS-Oberfläche liegt als statische Seite unter {@code bibis-web/index.html}.</li>
 *   <li>Elemente werden über ihre {@code id} gefunden ({@code page.locator("#suche")}),
 *       so wie im Skript der Seite mit {@code document.getElementById("suche")}.</li>
 *   <li>{@code @UsePlaywright} startet den Browser; jeder Test bekommt eine frische {@code Page}.</li>
 *   <li>Beim ersten Start lädt Playwright die Browser herunter (Wartezeit).</li>
 * </ul>
 *
 * Ausführen im Debug Mode: PWDEBUG=1 mvn test -Dtest=SucheE2ETest
 */

@UsePlaywright
class SucheE2ETest {

    static final String BIBIS = Paths.get("bibis-web/index.html").toUri().toString();

    @Test
    void sucheFindetPassendeTitel(Page page) {
        // Arrange
        page.navigate(BIBIS);
        // Act
        page.locator("#suche").fill("test");
        page.locator("#suche-starten").click();

        // Assert
        String text = page.locator("#suchmeldung").innerText();
        assertEquals(text, "4 Treffer");

        Locator treffer = page.locator("#treffer-basiswissen");
        assertTrue(treffer.isVisible());

    }
}
