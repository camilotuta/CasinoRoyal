// cSpell:ignore ajqk curr
package main.java.com.casinoRoyal.game.blackJack;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

import main.java.com.casinoRoyal.ui.views.transactions.Transactions;

public class PartidaBlackJack {

    private class Card {
        String value;
        String type;

        Card(String value, String type) {
            this.value = value;
            this.type = type;
        }

        @Override
        public String toString() {
            return value + "-" + type;
        }

        public int getValue() {
            if ("AJQK".contains(value)) {
                if (value.equals("A")) {
                    return 11;
                }
                return 10;
            }
            return Integer.parseInt(value);
        }

        public boolean isAce() {
            return value.equals("A");
        }

        public String getImagePath() {
            return "/img/blackjack/" + toString() + ".png";
        }
    }

    ArrayList<Card> deck;
    Random random = new Random();

    Card hiddenCard;
    ArrayList<Card> dealerHand;
    private int dealerSum;
    int dealerAceCount;
    private int winner = -1;

    ArrayList<Card> playerHand;
    private int playerSum;
    int playerAceCount;
    public boolean partidaEnCurso;

    int cardWidth = 110;
    int cardHeight = 154;

    JPanel gamePanel;
    JPanel buttonPanel = new JPanel();
    JButton hitButton = new JButton("Pedir");
    JButton stayButton = new JButton("Plantarse");

    JLabel playerSumLabel = new JLabel("Tu mano actual: 0");

    public PartidaBlackJack(JPanel externalPanel, double valorApostado) {
        partidaEnCurso = true;
        this.gamePanel = externalPanel;

        playerSumLabel.setFont(new Font("Arial", Font.BOLD, 14));
        playerSumLabel.setForeground(Color.WHITE);

        this.gamePanel.setLayout(new BorderLayout());

        this.gamePanel.add(new JComponent() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    Image hiddenCardImg = new ImageIcon(getClass().getResource("/img/blackjack/BACK.png")).getImage();
                    if (!stayButton.isEnabled()) {
                        hiddenCardImg = new ImageIcon(getClass().getResource(hiddenCard.getImagePath())).getImage();
                    }
                    g.drawImage(hiddenCardImg, 20, 20, cardWidth, cardHeight, null);

                    for (int i = 0; i < dealerHand.size(); i++) {
                        Card card = dealerHand.get(i);
                        Image cardImg = new ImageIcon(getClass().getResource(card.getImagePath())).getImage();
                        g.drawImage(cardImg, cardWidth + 25 + (cardWidth + 5) * i, 20, cardWidth, cardHeight, null);
                    }

                    for (int i = 0; i < playerHand.size(); i++) {
                        Card card = playerHand.get(i);
                        Image cardImg = new ImageIcon(getClass().getResource(card.getImagePath())).getImage();
                        g.drawImage(cardImg, 20 + (cardWidth + 5) * i, 220, cardWidth, cardHeight, null);
                    }

                    if (!stayButton.isEnabled()) {
                        new Thread(() -> {
                            try {
                                Thread.sleep(2000);

                                dealerSum = reduceDealerAce();
                                playerSum = reducePlayerAce();
                                String message;
                                final double[] valorGanado = { 0 };

                                if (playerSum > 21) {
                                    message = "¡Perdiste!";
                                } else if (dealerSum > 21) {
                                    message = "¡Ganaste!";
                                    valorGanado[0] = valorApostado * 2;
                                } else if (playerSum == dealerSum) {
                                    message = "¡Empate!";
                                    valorGanado[0] = valorApostado;
                                } else if (playerSum > dealerSum) {
                                    message = "¡Ganaste!";
                                    valorGanado[0] = valorApostado * 2;
                                } else {
                                    message = "¡Perdiste!";
                                }

                                String mensaje = message + "\nTu total fue de "
                                        + getPlayerSum() + " frente al "
                                        + getDealerSum() + ".\nGanancia: $" + valorGanado[0];
                                Transactions.sumarFondos(valorGanado[0]);
                                JOptionPane.showMessageDialog(this, mensaje, "Resultado de Blackjack",
                                        JOptionPane.INFORMATION_MESSAGE);
                                partidaEnCurso = false;
                                cerrarJuego();
                            } catch (InterruptedException e) {
                                JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        }).start();
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }, BorderLayout.CENTER);

        initializeGame();
    }

    private void initializeGame() {
        startGame();

        hitButton.setFocusable(false);
        hitButton.setBackground(new Color(34, 139, 34));
        buttonPanel.add(hitButton);

        stayButton.setFocusable(false);
        stayButton.setBackground(new Color(255, 99, 71));
        buttonPanel.add(stayButton);

        buttonPanel.add(playerSumLabel);
        // buttonPanel.setBackground(new Color(61, 61, 61));

        gamePanel.add(buttonPanel, BorderLayout.SOUTH);

        hitButton.addActionListener(e -> {
            Card card = deck.remove(deck.size() - 1);
            playerSum += card.getValue();
            playerAceCount += card.isAce() ? 1 : 0;
            playerHand.add(card);

            playerSumLabel.setText("Tu mano actual: " + reducePlayerAce());

            if (reducePlayerAce() > 21) {
                hitButton.setEnabled(false);
            }
            gamePanel.repaint();
        });

        stayButton.addActionListener(e -> {
            hitButton.setEnabled(false);
            stayButton.setEnabled(false);
            while (dealerSum < 17) {
                Card card = deck.remove(deck.size() - 1);
                dealerSum += card.getValue();
                dealerAceCount += card.isAce() ? 1 : 0;
                dealerHand.add(card);
            }
            gamePanel.repaint();
        });

        gamePanel.repaint();
        gamePanel.revalidate();
    }

    public void startGame() {
        buildDeck();
        shuffleDeck();

        dealerHand = new ArrayList<>();
        dealerSum = 0;
        dealerAceCount = 0;

        hiddenCard = deck.remove(deck.size() - 1);
        dealerSum += hiddenCard.getValue();
        dealerAceCount += hiddenCard.isAce() ? 1 : 0;

        Card card = deck.remove(deck.size() - 1);
        dealerSum += card.getValue();
        dealerAceCount += card.isAce() ? 1 : 0;
        dealerHand.add(card);

        playerHand = new ArrayList<>();
        playerSum = 0;
        playerAceCount = 0;

        for (int i = 0; i < 2; i++) {
            card = deck.remove(deck.size() - 1);
            playerSum += card.getValue();
            playerAceCount += card.isAce() ? 1 : 0;
            playerHand.add(card);
        }

        playerSumLabel.setText("Tu mano actual: " + playerSum);
    }

    public void buildDeck() {
        deck = new ArrayList<>();
        String[] values = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
        String[] types = { "C", "D", "H", "S" };

        for (String type : types) {
            for (String value : values) {
                deck.add(new Card(value, type));
            }
        }
    }

    public void shuffleDeck() {
        for (int i = 0; i < deck.size(); i++) {
            int j = random.nextInt(deck.size());
            Card currCard = deck.get(i);
            Card randomCard = deck.get(j);
            deck.set(i, randomCard);
            deck.set(j, currCard);
        }
    }

    private void cerrarJuego() {
        gamePanel.removeAll();
        gamePanel.revalidate();
        gamePanel.repaint();
    }

    public int reducePlayerAce() {
        while (playerSum > 21 && playerAceCount > 0) {
            playerSum -= 10;
            playerAceCount -= 1;
        }
        return playerSum;
    }

    public int reduceDealerAce() {
        while (dealerSum > 21 && dealerAceCount > 0) {
            dealerSum -= 10;
            dealerAceCount -= 1;
        }
        return dealerSum;
    }

    public int getDealerSum() {
        return dealerSum;
    }

    public int getWinner() {
        return winner;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }

    public int getPlayerSum() {
        return playerSum;
    }
}
