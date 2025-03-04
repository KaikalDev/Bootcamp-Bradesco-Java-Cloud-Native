package Kaique.luan.dev;

import Kaique.luan.dev.model.Board;
import Kaique.luan.dev.model.Space;
import Kaique.luan.dev.utils.BoardTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import static Kaique.luan.dev.utils.BoardTemplate.BOARD_TEMPLATE;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toMap;

public class Main {

    private final static Scanner sc = new Scanner(System.in);

    private static Board board;

    private final static int BOARD_SIZE = 9;

    public static void main(String[] args) {
        final var position = Stream.of(args)
                .collect(toMap(
                        k -> k.split(";")[0],
                        v -> v.split(";")[1]
                ));
        var option = -1;
        while (true) {
            System.out.println("Selecione uma das opções a seguir");
            System.out.println("1 - Iniciar um novo Jogo");
            System.out.println("2 - Colocar um novo número");
            System.out.println("3 - Remover um número");
            System.out.println("4 - Visualizar jogo atual");
            System.out.println("5 - Verificar status do jogo");
            System.out.println("6 - limpar jogo");
            System.out.println("7 - Finalizar jogo");
            System.out.println("8 - Sair");

            option = sc.nextInt();

            switch (option) {
                case 1 -> StartGame(position);
                case 2 -> inputNumber();
                case 3 -> removeNumber();
                case 4 -> showCurrentGame();
                case 5 -> showGameStatus();
                case 6 -> clearGame();
                case 7 -> finishGame();
                case 8 -> System.exit(0);
                default -> System.out.println("Opção invalida, selecione uma das opções do menu");
            }
        }
    }

    private static void StartGame(Map<String, String> position) {
        if (nonNull(board)) {
            System.out.println("O jogo ja foi iniciado!");
            return;
        }

        List<List<Space>> spaces = new ArrayList<>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            spaces.add(new ArrayList<>());
            for (int j = 0; j < BOARD_SIZE; j++) {
                var positionCnfig = position.get("%s,%s".formatted(i,j));
                var expected = Integer.parseInt(positionCnfig.split(",")[0]);
                var fixed = Boolean.parseBoolean(positionCnfig.split(",")[1]);
                var currentSpace = new Space(expected, fixed);
                spaces.get(i).add(currentSpace);
            }
        }

        board = new Board(spaces);
        System.out.println("O jogo esta pronto para começar");
    }

    private static void inputNumber() {
        var colRow = getColRow();
        if (colRow == null) {
            return;
        }
        var col = colRow[0];
        var row = colRow[1];

        System.out.printf("Informe o número que vai entrar na posição [%s,%s]\n", col, row);
        var value = runUntilGetValidNumber(1,9);

        if (!board.changeValue(col,row,value)) {
            System.out.printf("A posição [%s,%s] tem um valor fixo\n", col, row);
        }
    }

    private static void removeNumber() {
        var colRow = getColRow();
        if (colRow == null) {
            return;
        }
        var col = colRow[0];
        var row = colRow[1];

        if (!board.clearValue(col,row)) {
            System.out.printf("A posição [%s,%s] tem um valor fixo\n", col, row);
        }
    }

    private static void showCurrentGame() {
        if (isNull(board)) {
            System.out.println("O jogo ainda não foi iniciado!");
            return;
        }

        var args = new Object[81];
        var argPos = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (var col: board.getSpaces()) {
                args[argPos++] = " " + (isNull(col.get(i).getActual()) ? " " : col.get(i).getActual());
            }
        }
        System.out.println("Seu jogo se encontra da seguinte forma");
        System.out.printf((BOARD_TEMPLATE) + "\n", args);
    }

    private static void showGameStatus() {
        if (isNull(board)) {
            System.out.println("O jogo ainda não foi iniciado!");
            return;
        }

        System.out.printf("O jogo se encontra no status %s\n", board.getStatus().getLabel());
        if (board.hasErrors()) {
            System.out.println("O jogo contem erros");
        } else {
            System.out.println("O jogo não contem erros");
        }
    }

    private static void clearGame() {
        if (isNull(board)) {
            System.out.println("O jogo ainda não foi iniciado!");
            return;
        }

        System.out.println("Tem certeza que deseja limpar seu jogo ?");
        var comfirm = sc.next();
        while (!comfirm.equalsIgnoreCase("sim") || !comfirm.equalsIgnoreCase("não")) {
            System.out.println("Imforme 'sim' ou 'não'");
            comfirm = sc.next();
        }

        if(comfirm.equalsIgnoreCase("sim")) {
            board.reset();
        }
    }

    private static void finishGame() {
        if (isNull(board)) {
            System.out.println("O jogo ainda não foi iniciado!");
            return;
        }

        if (board.gameIsFinished()) {
            System.out.println("Parabéns você concluiu o jogo");
            showCurrentGame();
            board = null;
        } else if (board.hasErrors()){
            System.out.println("Seu jogo tem erro, verifique e ajuste");
        } else {
            System.out.println("Seu jogo tem espaços em branco, verifique e ajuste");
        }

    }

    private static int runUntilGetValidNumber(final int min, final int max) {
        var current = sc.nextInt();
        while (current < min || current > max) {
            System.out.printf("Informe um numero entre %s e %s \n", min, max);
            current = sc.nextInt();
        }
        return current;
    }


    private static int[] getColRow() {
        if (isNull(board)) {
            System.out.println("O jogo ainda não foi iniciado!");
            return null;
        }

        System.out.println("Informe a coluna em que o número sera inserido");
        var col = runUntilGetValidNumber(0,8);

        System.out.println("Informe a linha em que o número sera inserido");
        var row = runUntilGetValidNumber(0,8);

        return new int[]{col,row};
    }

}
