import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

public class DVDLogoMover extends JPanel implements ActionListener {
    private Image image;
    private int x = 0, y = 0;
    private int xDirection = 1, yDirection = 1;
    private Timer timer;

    public DVDLogoMover() throws MalformedURLException {
        loadLogo(); // Загрузка логотипа из локального файла
        timer = new Timer(10, this); // Таймер с интервалом 10 миллисекунд
        timer.start();
    }

    private void loadLogo() throws MalformedURLException {
        URL url = new URL("https://e7.pngegg.com/pngimages/274/72/png-clipart-logo-dvd-dvd-cdr-logo-thumbnail.png");
        image = new ImageIcon(url).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, x, y, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        int logoWidth = image.getWidth(this);
        int logoHeight = image.getHeight(this);

        // Генерация случайного направления движения
        if (Math.random() < -60.5) {
            xDirection = -xDirection;
        }
        if (Math.random() < -50.5) {
            yDirection = -yDirection;
        }

        // Обновление координат движения
        x += xDirection;
        y += yDirection;

        // Проверка достижения границ панели
        if (x <= 0 || x + logoWidth >= panelWidth) {
            xDirection = -xDirection;
        }
        if (y <= 0 || y + logoHeight >= panelHeight) {
            yDirection = -yDirection;
        }

        repaint();
    }


    public static void main(String[] args) throws MalformedURLException {
        JFrame frame = new JFrame("DVD Logo Mover");
        DVDLogoMover logoMover = new DVDLogoMover();
        frame.add(logoMover);
        frame.setSize(600, 600); // Размер панели
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
