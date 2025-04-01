package org.entdes;

public class TaulerService {

    private static final int BOARD_SIZE = 3;

    private final String[][] caselles;
    private boolean esTornX = true;
    private boolean gameOver = false;
    private String guanyador = "";

    public TaulerService() {
        this.caselles = new String[BOARD_SIZE][BOARD_SIZE];
        inicialitzarTauler();
    }

    private void inicialitzarTauler() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                caselles[i][j] = "";
            }
        }
    }

    public String tractarClicCasella(int fila, int columna) {
        if (!gameOver && esCasellaBuida(fila, columna)) {
            marcarCasella(fila, columna);
            comprovarGuanyador();
            comprovarEmpat();
        }
        return caselles[fila][columna];
    }

    private boolean esCasellaBuida(int fila, int columna) {
        return caselles[fila][columna].isEmpty();
    }

    private void marcarCasella(int fila, int columna) {
        caselles[fila][columna] = esTornX ? "X" : "O";
        esTornX = !esTornX;
    }

    private void comprovarGuanyador() {
        comprovarFiles();
        comprovarColumnes();
        comprovarDiagonals();
        if (!guanyador.isEmpty()) {
            gameOver = true;
        }
    }

    private void comprovarFiles() {
        for (int fila = 0; fila < BOARD_SIZE; fila++) {
            if (tresIguals(caselles[fila][0], caselles[fila][1], caselles[fila][2])) {
                guanyador = caselles[fila][0];
                return;
            }
        }
    }

    private void comprovarColumnes() {
        for (int col = 0; col < BOARD_SIZE; col++) {
            if (tresIguals(caselles[0][col], caselles[1][col], caselles[2][col])) {
                guanyador = caselles[0][col];
                return;
            }
        }
    }

    private void comprovarDiagonals() {
        if (tresIguals(caselles[0][0], caselles[1][1], caselles[2][2])) {
            guanyador = caselles[0][0];
        } else if (tresIguals(caselles[0][2], caselles[1][1], caselles[2][0])) {
            guanyador = caselles[0][2];
        }
    }

    private boolean tresIguals(String a, String b, String c) {
        return !a.isEmpty() && a.equals(b) && b.equals(c);
    }

    private void comprovarEmpat() {
        boolean taulerPle = true;
        for (int fila = 0; fila < BOARD_SIZE; fila++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (caselles[fila][col].isEmpty()) {
                    taulerPle = false;
                    break;
                }
            }
        }
        if (taulerPle && guanyador.isEmpty()) {
            gameOver = true;
        }
    }

    public String getGuanyador() {
        return guanyador;
    }

    public boolean isGameOver() {
        return gameOver;
    }
}
