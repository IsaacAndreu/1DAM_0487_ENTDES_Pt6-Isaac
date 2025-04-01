package org.entdes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaulerServiceTest {

    private TaulerService taulerService;

    @BeforeEach
    void setUp() {
        taulerService = new TaulerService();
    }

    @Test
    void testComprovarGuanyadorX() {
        taulerService.tractarClicCasella(0, 0);
        taulerService.tractarClicCasella(0, 1);
        taulerService.tractarClicCasella(0, 2);
        taulerService.tractarClicCasella(1, 1);
        taulerService.tractarClicCasella(1, 0);
        taulerService.tractarClicCasella(1, 2);
        taulerService.tractarClicCasella(2, 0);

        assertTrue(taulerService.isGameOver());
        assertEquals("X", taulerService.getGuanyador());
    }

    @Test
    void testComprovarGuanyadorO() {
        taulerService.tractarClicCasella(0, 0);
        taulerService.tractarClicCasella(1, 0);
        taulerService.tractarClicCasella(0, 1);
        taulerService.tractarClicCasella(1, 1);
        taulerService.tractarClicCasella(2, 2);
        taulerService.tractarClicCasella(1, 2);

        assertTrue(taulerService.isGameOver());
        assertEquals("O", taulerService.getGuanyador());
    }

    @Test
    void testEmpat() {
        taulerService.tractarClicCasella(0, 0);
        taulerService.tractarClicCasella(0, 1);
        taulerService.tractarClicCasella(0, 2);
        taulerService.tractarClicCasella(1, 1);
        taulerService.tractarClicCasella(1, 0);
        taulerService.tractarClicCasella(1, 2);
        taulerService.tractarClicCasella(2, 1);
        taulerService.tractarClicCasella(2, 0);
        taulerService.tractarClicCasella(2, 2);

        assertTrue(taulerService.isGameOver());
        assertEquals("", taulerService.getGuanyador());
    }

    @Test
    void testCasellaOcupadaNoCanvia() {
        taulerService.tractarClicCasella(0, 0);
        String resultatSegonClick = taulerService.tractarClicCasella(0, 0);

        assertEquals("X", resultatSegonClick);
    }

    @Test
    void testNoPermetJugarDespresDeGuanyar() {
        taulerService.tractarClicCasella(0, 0);
        taulerService.tractarClicCasella(1, 0);
        taulerService.tractarClicCasella(0, 1);
        taulerService.tractarClicCasella(1, 1);
        taulerService.tractarClicCasella(0, 2);

        assertTrue(taulerService.isGameOver());
        String intentExtra = taulerService.tractarClicCasella(2, 2);

        assertEquals("", intentExtra);
    }
}
