package ru.vrn.lukashev.UI.Window;

import ru.vrn.lukashev.Game;
import ru.vrn.lukashev.Music;
import ru.vrn.lukashev.cards.Card;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class MainPanel extends JPanel
{


    private int frameWidth;
    private int frameHeight;

    public int getFrameWidth() {
        return frameWidth;
    }

    public void setFrameWidth(int frameWidth) {
        this.frameWidth = frameWidth;
    }

    public int getFrameHeight() {
        return frameHeight;
    }

    public void setFrameHeight(int frameHeight) {
        this.frameHeight = frameHeight;
    }





    Game game = new Game();
    private List<JLabel> jLabelList = new ArrayList<>();
    private List<JLabel> infoLable = new ArrayList<>();
    private List<JComponent> menuComponentsList = new ArrayList<>();

    private List<JButton> jButtonList = new ArrayList<>();
    private Timer timer;

    private boolean isPlaying = true;
    private Music musicPlayer = new Music();
    private JLabel background = new JLabel();

    private Color buttonColor = Color.decode("#1DA59D");



    private List<JComponent> allComponents = new ArrayList<>();



    private void clearAll(){
        for(JComponent jComponent : allComponents)
            this.remove(jComponent);
        allComponents = new ArrayList<>();

    }





    public MainPanel(int frameWidth, int frameHeight){
        this.setLayout(null);

        this.setFrameHeight(frameHeight);
        this.setFrameWidth(frameWidth);

//        System.out.println(weight + " " + height);

//        addGameNameLabel();
        musicPlayer.setFile(new File("").getAbsolutePath() + "/genres-chill-140266.wav");
        musicPlayer.play();

        timer = new Timer(70, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                repaint();
            }
        });

        timer.start();
        background = createBackground();

        addGameNameLabel();
        addDealerImage();
        changeScreen();
    }

    private void deleteAll(){
        this.removeAll();
    }

    private void createMenuButtons(){
        JButton newGameButton = createNewGameButton();
        newGameButton.setBounds(changeByX(600), changeByY(300),
                changeByX(300), changeByY( 75));
//        newGameButton.setBackground(buttonColor);
        menuComponentsList.add(newGameButton);

        menuComponentsList.add(createMusicButton());

        menuComponentsList.add(createPolicyButton());

        menuComponentsList.add(createExitButton());

        menuComponentsList.add(createBookInMenu());

        allComponents.addAll(menuComponentsList);

    }

    private void deleteMenu(){
        for (JComponent comp : menuComponentsList){
            allComponents.remove(comp);
        }
    }

    private JLabel createBookInMenu() {
        Image image = null;
        try {

            image = ImageIO.read(new File(new File("").getAbsolutePath() + "/book.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (image != null) {
            JLabel curLabel = new JLabel(new ImageIcon(image));
            curLabel.setLayout(null);
            curLabel.setIcon(new ImageIcon(scaleImage(image, changeByX(500), changeByY(450))));
            curLabel.setBounds(changeByX(50), changeByY(100),
                    changeByX(500), changeByY(450));
            return curLabel;
        }
        return null;
    }

    private JButton createExitButton(){
        JButton exitButton = new JButton("Exit");

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        exitButton.setBackground(buttonColor);
        exitButton.setFont(new Font("Arial", Font.PLAIN, 30));

        exitButton.setBounds(changeByX(600), changeByY(600),
                changeByX(300), changeByY(75));
        exitButton.setFocusPainted(false);
        exitButton.setBorder(null);
        exitButton.setEnabled(true);

        return exitButton;
    }


    private JButton createPolicyButton(){
        JButton policyButton = new JButton("Policy");

        policyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openWebsite("https://docs.google.com/document/d/16oOAJbEfojXqQS8e7YCEAC2a-CoJSGbjtIFr9YmpKI8/edit?usp=sharing");
            }
        });

        policyButton.setBackground(buttonColor);
        policyButton.setFont(new Font("Arial", Font.PLAIN, 30));

        policyButton.setBounds(changeByX(600), changeByY(500),
                changeByX(300), changeByY(75));
        policyButton.setFocusPainted(false);
        policyButton.setBorder(null);
        policyButton.setEnabled(true);


        return policyButton;
    }

    private void openWebsite(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }


    private JButton createMusicButton(){

        JButton toggleButton = new JButton("Stop music");

        toggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isPlaying) {
                    stopMusic();
                    toggleButton.setText("Play Music");
                } else {
                    playMusic();
                    toggleButton.setText("Stop Music");
                }
                isPlaying = !isPlaying;
                changeScreen();
            }
        });

        toggleButton.setBackground(buttonColor);
        toggleButton.setFont(new Font("Arial", Font.PLAIN, 30));

        toggleButton.setBounds(changeByX(600), changeByY(400),
                changeByX(300), changeByY(75));
        toggleButton.setFocusPainted(false);
        toggleButton.setBorder(null);
        toggleButton.setEnabled(true);

        return toggleButton;
    }


    private int changeByX(int absoluteX){
        return absoluteX * getFrameWidth() / 1536;
    }


    private int changeByY(int absoluteY){
        return absoluteY * getFrameHeight() / 900;
    }




    private void playMusic(){
        musicPlayer.play();
    }

    private void stopMusic(){
        try {
            musicPlayer.stop();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private JButton createNewGameButton(){
        JButton newGameButton = new JButton("New Game");

        newGameButton.setBackground(buttonColor);
        newGameButton.setFont(new Font("Arial", Font.PLAIN, 30));
        newGameButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                newGameButton.setBackground(buttonColor);
                addGameButtons();
                game.newGame();
                addGamerInfo();
                changeScreen();

            }

        });
        newGameButton.setBounds(changeByX(20), changeByY(120),
                changeByX(300), changeByY(75));
        newGameButton.setFocusPainted(false);
        newGameButton.setBorder(null);
        newGameButton.setEnabled(true);

        return newGameButton;
    }

    private JButton createOneMoreCardButton(){
        JButton oneMoreCardButton = new JButton("One more");

        oneMoreCardButton.setFont(new Font("Arial", Font.PLAIN, 25));
        oneMoreCardButton.setBackground(buttonColor);
        oneMoreCardButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                oneMoreCardButton.setBackground(buttonColor);
                game.giveCardToGamer();
//                deleteAllCards();
                changeScreen();

            }

        });
        oneMoreCardButton.setBounds(changeByX(70), changeByY(450),
                changeByX(150), changeByY(50));
        oneMoreCardButton.setFocusPainted(false);
        oneMoreCardButton.setBorder(null);
        oneMoreCardButton.setEnabled(true);

        return oneMoreCardButton;
    }

    private JButton createEnoughCardsButton(){
        JButton enoughCardsButton = new JButton("Enough");

        enoughCardsButton.setBackground(buttonColor);
        enoughCardsButton.setFont(new Font("Arial", Font.PLAIN, 25));
        enoughCardsButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                enoughCardsButton.setBackground(buttonColor);
                changeScreen();
                game.enoughToGamer();
            }

        });
        enoughCardsButton.setBounds(changeByX(70), changeByY(520),
                changeByX(150), changeByY(50));
        enoughCardsButton.setFocusPainted(false);
        enoughCardsButton.setBorder(null);
        enoughCardsButton.setEnabled(true);

        return enoughCardsButton;
    }

    private JButton createBackToMenuButton(){
        JButton backToMenuButton = new JButton("Menu");

        backToMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.backToMenu();
//                System.out.println("sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
                clearAll();

                deleteInfo();
                addDealerImage();
                addGameNameLabel();
                repaint();
                changeScreen();
            }
        });

        backToMenuButton.setBackground(buttonColor);
        backToMenuButton.setFont(new Font("Arial", Font.PLAIN, 30));
        backToMenuButton.setBounds(changeByX(20), changeByY(30),
                changeByX(300), changeByY(75));
        backToMenuButton.setFocusPainted(false);
        backToMenuButton.setBorder(null);
        backToMenuButton.setEnabled(true);

        return backToMenuButton;
    }

    private void addGameButtons(){
        jButtonList.add(createOneMoreCardButton());
        jButtonList.add(createEnoughCardsButton());
        allComponents.addAll(jButtonList);
    }

    private void deleteGameButtons(){
        for(JButton j : jButtonList){
            allComponents.remove(j);
        }
    }

    private void addNewGameButton(){
        allComponents.add(createNewGameButton());
    }

    private void addBackToMenuButton(){
        allComponents.add(createBackToMenuButton());
    }

    private void drawGamerCardOnTable(){
        List<Card> cardList = game.getGamer().getListCard();
        BufferedImage cardImage = null;
        if(cardList != null){
            int x = changeByX(50), y = changeByY(650),
                    height = changeByX(150), width = changeByY(125);
            for (Card c : cardList) {
                try {
                    if (c != null)
                        cardImage = ImageIO.read(new File(c.getId()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (cardImage != null) {
                    JLabel curLabel = new JLabel(new ImageIcon(cardImage));
                    curLabel.setLayout(null);
                    curLabel.setBounds(x, y, width, height);
                    jLabelList.add(curLabel);
                }
                x += width;
            }
        }
    }

    private void drawBotCardOnTable(){
        List<Card> cardList = game.getBot().getListCard();
        BufferedImage cardImage = null;
        if(cardList != null){
            int x = changeByX(50), y = changeByY(400),
                    height = changeByX(150), width = changeByY(125);
            for (Card c : cardList) {
                try {
                    if (c != null)
                        cardImage = ImageIO.read(new File(c.getId()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (cardImage != null) {
                    JLabel curLabel = new JLabel(new ImageIcon(cardImage));
                    curLabel.setLayout(null);
                    curLabel.setBounds(x, y, width, height);
                    jLabelList.add(curLabel);
                }
                x += width;
            }
        }
    }

    private void addCards(){
        drawGamerCardOnTable();
        drawBotCardOnTable();

        for(JComponent component : jLabelList)
            allComponents.add(0, component);
    }


    private void drawAll(){
        this.removeAll();

        for(JComponent jComponent : allComponents)
            this.add(jComponent);
    }

    private void deleteAllCards(){
        for(JLabel jLabel : jLabelList){
            allComponents.remove(jLabel);
            jLabelList = new ArrayList<>();
        }
    }

    private void addLabelWinner(){
        String text = "";
        switch (game.getWinner()){
            case BOT -> text = "You are loser ( ";
            case DRAW -> text = "Draw!";
            case GAMER -> text = "You are winner!";
        }

        JLabel infoWinner = new JLabel( text);
        infoWinner.setLayout(null);
        infoWinner.setBounds(changeByX(50), changeByY(200),
                changeByX(800), changeByY(150));
        infoWinner.setFont(new Font("Serief", Font.PLAIN, 80));
        infoWinner.setForeground(buttonColor);
        jLabelList.add(infoWinner);
    }

    private void addGamerInfo(){
        JLabel info = new JLabel("Your cards:");
        info.setLayout(null);
        info.setBounds(changeByX(70), changeByY(550),
                changeByX(300), changeByY(150));
        info.setFont(new Font("Serief", Font.ITALIC + Font.BOLD, 30));
        info.setForeground(buttonColor);
        infoLable.add(info);
    }

    private void addGameNameLabel(){
        JLabel info = new JLabel("21 Game");
        info.setLayout(null);
        info.setBounds(changeByX(700), changeByY(20),
                changeByX(700), changeByY(150));
        info.setFont(new Font("Serief", Font.ITALIC + Font.BOLD, 100));
        info.setForeground(buttonColor);
        allComponents.add(info);
    }

    private void addBotInfo(){
        JLabel info = new JLabel("Opponent's cards:");
        info.setLayout(null);
        info.setBounds(changeByX(70), changeByY(300),
                changeByX(300), changeByY(150));
        info.setFont(new Font("Serief", Font.ITALIC + Font.BOLD, 30));
        info.setForeground(buttonColor);
        infoLable.add(info);
    }

    private void drawInfo(){
        allComponents.addAll(infoLable);
    }

    private void deleteInfo(){
        for(JLabel jLabel : infoLable){
            allComponents.remove(jLabel);
        }
        infoLable = new ArrayList<>();
    }

    private JLabel createBackground(){
        ImageIcon imageIcon = loadImage(new File("").getAbsolutePath() + "/background.jpeg");

        assert imageIcon != null;
        background.setIcon(new ImageIcon(scaleImage(imageIcon.getImage(),changeByX(1550), changeByY(900))));
        background.setBounds(changeByX(0), changeByY(0), changeByX(1550), changeByY(900));
        background.setLayout(new CardLayout());
        return background;
    }

    private void drawBackground(){
        allComponents.add(background);
    }

    private void deleteBackground(){
        allComponents.remove(background);
    }

    private ImageIcon loadImage(String path) {
        try {
            BufferedImage image = ImageIO.read(new File(path));
            return new ImageIcon(image);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Image scaleImage(Image image, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = scaledImage.createGraphics();
        graphics.drawImage(image, 0, 0, width, height, null);
        graphics.dispose();
        return scaledImage;
    }

    private void addDealerImage(){
        Image image = null;
        try {

            image = ImageIO.read(new File(new File("").getAbsolutePath() + "/persona2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (image != null) {
            JLabel curLabel = new JLabel(new ImageIcon(image));
            curLabel.setLayout(null);
            curLabel.setBounds(changeByX(550), changeByY(80),
                    changeByX(1300), changeByY(1000));
            allComponents.add(curLabel);
        }
    }


    private void changeScreen(){
        deleteMenu();
        deleteBackground();
        switch (game.getGameStatus()){
            case MENU -> createMenuButtons();
            case NEW_GAME -> {
                deleteAllCards();

                deleteMenu();
                deleteInfo();
                game.gamerGetsTwoCard();
                repaint();
                addCards();
            }
            case PLAYER_GET_TWO_CARD -> {
                deleteInfo();
                addNewGameButton();
                addBackToMenuButton();

                addGamerInfo();

                drawInfo();
                repaint();
            }
            case PLAYER_TAKE_CARDS ->{
                deleteAllCards();
                addCards();
            }
            case BOT_TAKE_CARDS ->{
                deleteInfo();

                drawInfo();

                addNewGameButton();
                addBackToMenuButton();

                addBotInfo();
                game.botThinkToTakeCard();
                deleteAllCards();
                addCards();

                drawAll();
                repaint();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            case BOT_GET_TWO_CARD -> {
                deleteAllCards();

                addCards();

                drawGamerCardOnTable();
                drawBotCardOnTable();
                drawAll();
                repaint();

            }
            case ENDGAME -> {
                deleteInfo();
                addBotInfo();
                addGamerInfo();
                drawInfo();

                addNewGameButton();
                addBackToMenuButton();

                deleteAllCards();
                addLabelWinner();
                addCards();
                deleteGameButtons();
                drawAll();

            }
        }
        drawBackground();
        drawAll();
    }




    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
//        Graphics2D g =(Graphics2D) graphics;
        this.setBackground(Color.decode("#3d8a66"));

        if(game.gameStatusChanged())
            changeScreen();



        setFrameHeight(this.getHeight());
        setFrameWidth(this.getWidth());

        System.out.println(getFrameHeight() + " " + getFrameWidth());

    }
}
