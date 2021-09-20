package life;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;


public class GameOfLife extends JFrame {
    private JPanel info;
    private Map map;

    private JLabel GenerationLabel;
    private JLabel AliveLabel;

    private JButton SettingsButton;
    private JToggleButton PlayToggleButton;
    private JButton ResetButton;

    private JSlider slider;

    private final Color THEME_COLOR_DARK = new Color(49, 49, 51);
    private final Color THEME_COLOR_LIGHT = new Color(61, 61, 64);

    private ProcessThread thread;

    public GameOfLife() {
        //set up JFrame
        super("Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setPreferredSize(new Dimension(1071, 820));
        pack();
        setLocationRelativeTo(null);

        //set up info section
        setContentPane();
        setButtons();
        setLabels();
        setSlider();
        add(info);

        //set up map panel
        this.map = new Map();
        map.setName("Cell Field");
        add(map);

        setVisible(true);
    }


    public void setContentPane() {
        //set up ContentPane
        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));

        //set up rootPane
        getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        getRootPane().setBackground(THEME_COLOR_DARK);

        //divide ContentPane into two sections
        info = new JPanel();
        info.setPreferredSize(new Dimension(250, 800));
        info.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        info.setBackground(THEME_COLOR_DARK);
        info.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 30));
    }

    public void setButtons() {
        //Set up button section layout, customize buttons, add to buttons panel
        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        buttons.setPreferredSize(new Dimension(210, 50));
        buttons.setBackground(THEME_COLOR_LIGHT);
        buttons.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        SettingsButton = new JButton();
        PlayToggleButton = new JToggleButton();
        PlayToggleButton.setName("PlayToggleButton");
        ResetButton = new JButton();
        ResetButton.setName("ResetButton");

        SettingsButton.setPreferredSize(new Dimension(60, 30));
        PlayToggleButton.setPreferredSize(new Dimension(60, 30));
        ResetButton.setPreferredSize(new Dimension(60, 30));

        buttons.add(SettingsButton);
        buttons.add(PlayToggleButton);
        buttons.add(ResetButton);

        SettingsButton.setIcon(new ImageIcon(getClass().getResource("/resources/images/settings.png")));
        JPopupMenu dropDown = new JPopupMenu();
        JMenuItem author = new JMenuItem("Author info");
        dropDown.add(author);
        SettingsButton.addActionListener(e -> {

            dropDown.show(SettingsButton, SettingsButton.getBounds().x, SettingsButton.getBounds().y
                    + SettingsButton.getBounds().height);

        });
        author.addActionListener(x -> {
            showAuthorInfo();
        });

        PlayToggleButton.setIcon(new ImageIcon(getClass().getResource("/resources/images/play.png")));
        PlayToggleButton.setSelectedIcon(new ImageIcon(getClass().getResource("/resources/images/pause.png")));

        ResetButton.setIcon(new ImageIcon(getClass().getResource("/resources/images/loop.png")));

        info.add(buttons);
    }

    public void setLabels() {
        //create labels panel, customize it, customize labels, add to panel
        JPanel labels = new JPanel();
        labels.setPreferredSize(new Dimension(210, 50));
        labels.setLayout(new BoxLayout(labels, BoxLayout.Y_AXIS));
        labels.setBackground(THEME_COLOR_LIGHT);
        labels.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        GenerationLabel = new JLabel();
        GenerationLabel.setName("GenerationLabel");
        GenerationLabel.setText("Generation #0");
        GenerationLabel.setForeground(Color.WHITE);
        GenerationLabel.setFont(new Font("SERIF", Font.PLAIN, 16));

        AliveLabel = new JLabel();
        AliveLabel.setName("AliveLabel");
        AliveLabel.setText("Alive: 0");
        AliveLabel.setForeground(Color.WHITE);
        AliveLabel.setFont(new Font("SERIF", Font.PLAIN, 16));

        labels.add(GenerationLabel);
        labels.add(AliveLabel);
        info.add(labels);
    }

    public void setSlider() {
        //set up slider panel, create / customize slider description + slider, add to panel
        JPanel speedSlider = new JPanel();
        speedSlider.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 5));
        speedSlider.setPreferredSize(new Dimension(210, 75));
        speedSlider.setBackground(THEME_COLOR_LIGHT);
        speedSlider.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        JLabel sliderLabel = new JLabel();
        sliderLabel.setName("SpeedMode");
        sliderLabel.setText("   Fast                                               Slow");
        sliderLabel.setFont(new Font("SERIF", Font.PLAIN, 10));
        sliderLabel.setForeground(Color.WHITE);

        slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        slider.setMajorTickSpacing(100);
        slider.setSnapToTicks(true);
        slider.setPaintTicks(true);
        slider.setBackground(THEME_COLOR_LIGHT);
        slider.setMinimum(100);
        slider.setMaximum(700);
        slider.setValue(400);
        speedSlider.add(sliderLabel);
        speedSlider.add(slider);

        info.add(speedSlider);
    }

    public void showAuthorInfo() {
        //drop-down pop up window for author tag via button press
        JFrame authorInfo = new JFrame();
        authorInfo.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        authorInfo.setSize(260, 125);
        authorInfo.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        authorInfo.getContentPane().setBackground(THEME_COLOR_LIGHT);
        authorInfo.setResizable(false);
        authorInfo.setLocationRelativeTo(null);
        authorInfo.setBackground(Color.BLACK);
        JLabel tag = new JLabel();
        tag.setForeground(Color.WHITE);

        tag.setIcon(new ImageIcon(getClass().getResource("/resources/images/tag.png")));

        authorInfo.add(tag);
        authorInfo.setLocationRelativeTo(null);
        authorInfo.setVisible(true);
    }

    // getters and setters for updateView method of process thread's controller class field
    public Map getMap() {
        return this.map;
    }

    public JLabel getGenerationLabel() {
        return this.GenerationLabel;
    }

    public JLabel getAliveLabel() {
        return this.AliveLabel;
    }

    private void initActionListeners() {
        ResetButton.addActionListener(e -> {
            thread.resetGame();
        });
        PlayToggleButton.addActionListener(e -> {
            thread.playPauseGame();
        });
    }

    public int getSliderValue() {
        return slider.getValue();
    }

    public void setGameThread(ProcessThread thread) {
        this.thread = thread;
        initActionListeners();
    }
}
