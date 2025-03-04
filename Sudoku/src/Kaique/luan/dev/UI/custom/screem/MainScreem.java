package Kaique.luan.dev.UI.custom.screem;

import Kaique.luan.dev.UI.custom.button.CheckGameStatusButton;
import Kaique.luan.dev.UI.custom.button.FinishGameButton;
import Kaique.luan.dev.UI.custom.button.ResetButton;
import Kaique.luan.dev.UI.custom.frame.MainFrame;
import Kaique.luan.dev.UI.custom.input.NumberText;
import Kaique.luan.dev.UI.custom.panel.MainPanel;
import Kaique.luan.dev.UI.custom.panel.SudokuSector;
import Kaique.luan.dev.model.Space;
import Kaique.luan.dev.service.BoardService;
import Kaique.luan.dev.service.NotifierService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static Kaique.luan.dev.service.EventEnum.CLEAR_SPACE;
import static javax.swing.JOptionPane.*;

public class MainScreem {

    private final static Dimension dimension = new Dimension(600, 600);

    private final BoardService boardService;
    private final NotifierService notifierService;

    private JButton checkGameStatusButton;
    private JButton finishGameButton;
    private JButton resetButton;

    public MainScreem(final Map<String, String> gameConfig) {
        this.boardService = new BoardService(gameConfig);
        this.notifierService = new NotifierService();
    }

    public void buildGameScreen() {
        JPanel mainPanel = new MainPanel(dimension);
        JFrame mainFrame = new MainFrame(dimension, mainPanel);

        for (int row = 0; row < 9; row+=3) {
            var endRow = row + 2;
            for (int col = 0; col < 9; col+=3) {
                var endCol = col + 2;
                var spaces = getSpacesFromSector(boardService.getSpaces(), col, endCol, row, endRow);
                JPanel sector = generateSection(spaces);
                mainPanel.add(sector);
            }
        }

        addResetButton(mainPanel);
        addCheckGameStatus(mainPanel);
        addFinishGameButton(mainPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    private List<Space> getSpacesFromSector(final List<List<Space>> spaces,
                                            final int initColl, final int endCol,
                                            final int initRow, final int endRow) {
        List<Space> spaceSector = new ArrayList<>();
        for (int row = initRow; row <= endRow; row++) {
            for (int col = initColl; col <= endCol; col++) {
                spaceSector.add(spaces.get(col).get(row));
            }
        }

        return spaceSector;
    }

    private JPanel generateSection(final List<Space> spaces) {
        List<NumberText> fields = new ArrayList<>(spaces.stream().map(NumberText::new).toList());
        fields.forEach(t -> notifierService.subscribe(CLEAR_SPACE, t));
        return new SudokuSector(fields);
    }

    private void addFinishGameButton(JPanel mainPanel) {
        finishGameButton = new FinishGameButton(e -> {
            if (boardService.gameIsFinished()) {
                showMessageDialog(
                        null,
                        "Parabéns você concluiu o jogo"
                );
                resetButton.setEnabled(false);
                finishGameButton.setEnabled(false);
                checkGameStatusButton.setEnabled(false);
            } else {
                var message = "Seu jogo tem alguma inconsistência, ajuste e tente novamente";
                showMessageDialog(null, message);
            }
        });
        mainPanel.add(finishGameButton);
    }

    private void addCheckGameStatus(JPanel mainPanel) {
        checkGameStatusButton = new CheckGameStatusButton(e -> {
            var hasErrors = boardService.hasErrors();
            var gameStatus = boardService.getStatus();
            var message = switch (gameStatus){
                case NON_STARTED -> "O jogo não foi iniciado";
                case INCOMPLETE -> "O jogo está imcompleto";
                case COMPLETE -> "O jogo está completo";
            };
            message += hasErrors ? " e contém erros" : " e não contém erros";
            showMessageDialog(null, message);
        });
        mainPanel.add(checkGameStatusButton);
    }

    private void addResetButton(JPanel mainPanel) {
        resetButton = new ResetButton(e -> {
            var dialogResult = showConfirmDialog(
                    null,
                    "Deseja realmente reiniciar o jogo ?",
                    "Limpar o jogo",
                    YES_NO_OPTION,
                    QUESTION_MESSAGE
            );
            if (dialogResult == 0) {
                boardService.reset();
                notifierService.notify(CLEAR_SPACE);
            }
        });
        mainPanel.add(resetButton);
    }
}
