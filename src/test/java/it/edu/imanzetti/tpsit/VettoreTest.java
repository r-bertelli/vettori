package it.edu.imanzetti.tpsit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class VettoreTest {

    private Vettore v1, v2, v3, v4;

    @BeforeEach
    void setUp() {
        v1 = new Vettore(0, 0, 3, 4);     // Vettore di lunghezza 5
        v2 = new Vettore(1, 1, 4, 5);     // Stesso vettore spostato
        v3 = new Vettore(0, 0, 1, 0);     // Vettore unitario orizzontale
        v4 = new Vettore(-1, -1, 2, 3);   // Vettore diagonale
    }

    // ===== TEST COSTRUTTORI =====

    @Test
    @DisplayName("Costruttore principale con parametri validi")
    void testCostruttorePrincipale() {
        Vettore v = new Vettore(1, 2, 3, 4);
        Assertions.assertEquals(1, v.getX0());
        Assertions.assertEquals(2, v.getY0());
        Assertions.assertEquals(3, v.getX1());
        Assertions.assertEquals(4, v.getY1());
    }

    @Test
    @DisplayName("Costruttore deve lanciare eccezione per vettore nullo")
    void testCostruttoreVettoreNullo() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Vettore(5, 5, 5, 5)
        );
        assertEquals("Vettore nullo non consentito", exception.getMessage());
    }

    @Test
    @DisplayName("Costruttore copia con vettore valido")
    void testCostruttoreCopia() {
        Vettore original = new Vettore(1, 2, 3, 4);
        Vettore copia = new Vettore(original);

        Assertions.assertEquals(original.getX0(), copia.getX0());
        Assertions.assertEquals(original.getY0(), copia.getY0());
        Assertions.assertEquals(original.getX1(), copia.getX1());
        Assertions.assertEquals(original.getY1(), copia.getY1());

        // Verifica che sia una copia indipendente
        copia.setX0(10);
        Assertions.assertNotEquals(original.getX0(), copia.getX0());
    }

    @Test
    @DisplayName("Costruttore copia deve lanciare eccezione per null")
    void testCostruttoreCopiaNullPointer() {
        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> new Vettore(null)
        );
        assertEquals("Il vettore da copiare è null", exception.getMessage());
    }

    // ===== TEST CALCOLO LUNGHEZZA =====

    @Test
    @DisplayName("Calcolo lunghezza vettore 3-4-5")
    void testLength345() {
        Assertions.assertEquals(5.0, v1.length(), 0.0001);
    }

    @Test
    @DisplayName("Calcolo lunghezza vettore unitario")
    void testLengthUnitario() {
        Assertions.assertEquals(1.0, v3.length(), 0.0001);
    }

    @Test
    @DisplayName("Calcolo lunghezza con coordinate negative")
    void testLengthNegative() {
        Vettore vNeg = new Vettore(-3, -4, 0, 0);
        Assertions.assertEquals(5.0, vNeg.length(), 0.0001);
    }

    @Test
    @DisplayName("Calcolo lunghezza con coordinate decimali")
    void testLengthDecimali() {
        Vettore vDec = new Vettore(0.5, 0.5, 1.5, 1.5);
        Assertions.assertEquals(Math.sqrt(2), vDec.length(), 0.0001);
    }

    // ===== TEST EQUALS (UGUAGLIANZA GEOMETRICA) =====

    @Test
    @DisplayName("Equals: vettori geometricamente uguali")
    void testEqualsGeometrici() {
        Assertions.assertTrue(v1.equals(v2)); // Stesso vettore traslato
    }

    @Test
    @DisplayName("Equals: vettore con se stesso")
    void testEqualsStesso() {
        Assertions.assertTrue(v1.equals(v1));
    }

    @Test
    @DisplayName("Equals: vettori geometricamente diversi")
    void testEqualsDiversi() {
        Assertions.assertFalse(v1.equals(v3));
    }

    @Test
    @DisplayName("Equals: confronto con null")
    void testEqualsNull() {
        Assertions.assertFalse(v1.equals(null));
    }

    @Test
    @DisplayName("Equals: confronto con oggetto diverso")
    void testEqualsOggettoDiverso() {
        Assertions.assertFalse(v1.equals("Non sono un vettore"));
    }

    @Test
    @DisplayName("Equals: vettori opposti")
    void testEqualsOpposti() {
        Vettore v1 = new Vettore(0, 0, 3, 4);
        Vettore v2 = new Vettore(0, 0, -3, -4); // Vettore opposto
        Assertions.assertFalse(v1.equals(v2));
    }

    @Test
    @DisplayName("Equals: precisione con double")
    void testEqualsPrecisioneDouble() {
        Vettore v1 = new Vettore(0, 0, 1, 1);
        Vettore v2 = new Vettore(0.1, 0.1, 1.1, 1.1);
        Assertions.assertTrue(v1.equals(v2));

        // Test con piccole differenze di precisione
        Vettore v3 = new Vettore(0, 0, 1.0000000001, 1);
        Vettore v4 = new Vettore(0, 0, 1, 1);
        Assertions.assertFalse(v3.equals(v4)); // Dovrebbe essere false per differenze minime
    }

    // ===== TEST GETTER E SETTER =====

    @Test
    @DisplayName("Test tutti i getter")
    void testGetters() {
        Vettore v = new Vettore(1, 2, 3, 4);
        Assertions.assertEquals(1, v.getX0());
        Assertions.assertEquals(2, v.getY0());
        Assertions.assertEquals(3, v.getX1());
        Assertions.assertEquals(4, v.getY1());
    }

    @Test
    @DisplayName("Test tutti i setter")
    void testSetters() {
        Vettore v = new Vettore(0, 0, 1, 1);

        v.setX0(5);
        v.setY0(6);
        v.setX1(7);
        v.setY1(8);

        Assertions.assertEquals(5, v.getX0());
        Assertions.assertEquals(6, v.getY0());
        Assertions.assertEquals(7, v.getX1());
        Assertions.assertEquals(8, v.getY1());
    }

    // ===== TEST TOSTRING =====

    @Test
    @DisplayName("Test toString formato corretto")
    void testToString() {
        String result = v1.toString();
        assertTrue(result.contains("origine=(0.0,0.0)"));
        assertTrue(result.contains("vertice=(3.0,4.0)"));
        assertTrue(result.contains("lunghezza=(5.0)"));
    }

    // ===== TEST CASI LIMITE =====

    @Test
    @DisplayName("Test con valori molto piccoli")
    void testValoriPiccoli() {
        Vettore vPiccolo = new Vettore(0, 0, Double.MIN_VALUE, Double.MIN_VALUE);
        assertTrue(vPiccolo.length() >= 0);
    }

    @Test
    @DisplayName("Test con zero e numeri negativi")
    void testZeroNegativi() {
        Vettore v = new Vettore(-5, -3, 0, 0);
        Assertions.assertEquals(Math.sqrt(34), v.length(), 0.0001);
    }

    // ===== TEST VALIDAZIONE SETTER (DOVREBBERO IMPEDIRE VETTORI NULLI) =====

    @Test
    @DisplayName("setX1 deve lanciare eccezione se rende il vettore nullo")
    void testSetX1LanciaEccezioneVettoreNullo() {
        Vettore v = new Vettore(0, 0, 1, 0);

        // Tentativo di rendere il vettore nullo tramite setX1
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> v.setX1(0) // Questo renderebbe x1 = x0 = 0, ma y1 != y0
        );

        // Il vettore non dovrebbe essere nullo dopo il fallimento
        Assertions.assertEquals(1.0, v.getX1(), "X1 non dovrebbe essere cambiato dopo l'eccezione");
        assertTrue(v.length() > 0, "Il vettore non dovrebbe essere nullo");
    }

    @Test
    @DisplayName("setY1 deve lanciare eccezione se rende il vettore nullo")
    void testSetY1LanciaEccezioneVettoreNullo() {
        Vettore v = new Vettore(0, 0, 1, 1);

        // Prima rendo x1 = x0, poi tento di rendere anche y1 = y0
        v.setX1(0); // Questo dovrebbe funzionare

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> v.setY1(0) // Questo dovrebbe fallire perché renderebbe il vettore nullo
        );

        Assertions.assertEquals(1.0, v.getY1(), "Y1 non dovrebbe essere cambiato dopo l'eccezione");
    }

    @Test
    @DisplayName("setX0 deve lanciare eccezione se rende il vettore nullo")
    void testSetX0LanciaEccezioneVettoreNullo() {
        Vettore v = new Vettore(1, 2, 5, 2);

        // Tentativo di rendere il vettore nullo tramite setX0
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> v.setX0(5) // x0 = x1 = 5, ma y0 != y1
        );

        Assertions.assertEquals(1.0, v.getX0(), "X0 non dovrebbe essere cambiato dopo l'eccezione");
    }

    @Test
    @DisplayName("setY0 deve lanciare eccezione se rende il vettore nullo")
    void testSetY0LanciaEccezioneVettoreNullo() {
        Vettore v = new Vettore(3, 1, 3, 8);

        // Prima rendo x0 = x1, poi tento di rendere anche y0 = y1
        v.setX0(3); // Questo dovrebbe funzionare

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> v.setY0(8) // Questo dovrebbe fallire perché renderebbe il vettore nullo
        );

        Assertions.assertEquals(1.0, v.getY0(), "Y0 non dovrebbe essere cambiato dopo l'eccezione");
    }

    @Test
    @DisplayName("Setter dovrebbero permettere modifiche valide che non rendono il vettore nullo")
    void testSetterModificheValide() {
        Vettore v = new Vettore(0, 0, 3, 4);
        double lunghezzaOriginale = v.length();

        // Modifiche che non rendono il vettore nullo
        v.setX0(1); // Sposto l'origine
        Assertions.assertNotEquals(lunghezzaOriginale, v.length(), "La lunghezza dovrebbe cambiare");
        assertTrue(v.length() > 0, "Il vettore dovrebbe essere ancora valido");

        v.setY1(10); // Cambio il vertice
        assertTrue(v.length() > 0, "Il vettore dovrebbe essere ancora valido");

        v.setX1(0); // Anche se coincide con x0 originale, ora x0 = 1
        assertTrue(v.length() > 0, "Il vettore dovrebbe essere ancora valido");
    }

    @Test
    @DisplayName("Test casi limite: quasi-nullo ma non completamente nullo")
    void testCasiLimiteQuasiNullo() {
        Vettore v = new Vettore(0, 0, 1, 1);

        // Questi setter dovrebbero funzionare perché non rendono il vettore completamente nullo
        assertDoesNotThrow(() -> v.setX1(0)); // x1 = x0, ma y1 != y0
        assertTrue(v.length() > 0, "Vettore dovrebbe essere ancora valido");

        assertDoesNotThrow(() -> v.setX0(0)); // Riporto x0 = x1 = 0
        assertTrue(v.length() > 0, "Vettore dovrebbe essere ancora valido");

        // Ma questo dovrebbe fallire
        assertThrows(IllegalArgumentException.class, () -> v.setY1(0));
    }

    @Test
    @DisplayName("Messaggio di errore dei setter dovrebbe essere chiaro")
    void testMessaggioErroreSetter() {
        Vettore v = new Vettore(5, 5, 2, 5);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> v.setX0(2) // Questo renderebbe il vettore completamente nullo
        );

        String messaggio = exception.getMessage();
        assertTrue(
                messaggio.contains("nullo") || messaggio.contains("null") || messaggio.contains("zero"),
                "Il messaggio dovrebbe spiegare che l'operazione renderebbe il vettore nullo: " + messaggio
        );
    }

    @Test
    @DisplayName("Test simmetria equals")
    void testEqualsSimmetria() {
        Vettore v1 = new Vettore(0, 0, 3, 4);
        Vettore v2 = new Vettore(1, 1, 4, 5);

        Assertions.assertEquals(v1.equals(v2), v2.equals(v1), "equals deve essere simmetrico");
    }

    @Test
    @DisplayName("Test transitività equals")
    void testEqualsTransitivita() {
        Vettore v1 = new Vettore(0, 0, 2, 3);
        Vettore v2 = new Vettore(1, 1, 3, 4);
        Vettore v3 = new Vettore(5, 5, 7, 8);

        if (v1.equals(v2) && v2.equals(v3)) {
            Assertions.assertTrue(v1.equals(v3), "equals deve essere transitivo");
        }
    }
}