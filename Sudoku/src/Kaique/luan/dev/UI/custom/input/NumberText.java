package Kaique.luan.dev.UI.custom.input;

import Kaique.luan.dev.model.Space;
import Kaique.luan.dev.service.EventEnum;
import Kaique.luan.dev.service.EventLisener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

import static Kaique.luan.dev.service.EventEnum.CLEAR_SPACE;
import static java.awt.Font.PLAIN;

public class NumberText extends JTextField implements EventLisener {

    private final Space space;

    public NumberText(final Space space) {
        this.space = space;
        var dimension = new Dimension(50, 50);
        this.setSize(dimension);
        this.setPreferredSize(dimension);
        this.setVisible(true);
        this.setFont(new Font("Arial", PLAIN, 20));
        this.setHorizontalAlignment(CENTER);
        this.setDocument(new NumberTextLimit());
        this.setEnabled(!space.isFixed());
        if (space.isFixed()) {
            this.setText(space.getActual().toString());
        }
        this.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                changeSpace();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changeSpace();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                changeSpace();
            }

            private void changeSpace() {
                if (getText().isEmpty()) {
                    space.clearSpace();
                    return;
                }

                space.setActual(Integer.parseInt(getText()));
            }
        });
    }

    @Override
    public void update(EventEnum eventType) {
        if (eventType.equals(CLEAR_SPACE) && (this.isEnabled())) {
            this.setText("");
        }
    }
}
