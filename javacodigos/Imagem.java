import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Imagem {
  private BufferedImage img;
  private Rectangle rectangle = new Rectangle();
  private int x;
  private int y;

  public void desenhar(Graphics g) {
    //Desenhando a imagem na tela
    g.drawImage(this.getImg(), this.getX(), this.getY(), null);
  }

  public boolean intercepta(Imagem i) {
    if(rectangle.intersects(i.getRectangle())) {
      return true;
    }
    else {
      return false;
    }
  }

  public Rectangle getRectangle() {
    return rectangle;
  }

  public void setRectangle(Rectangle rectangle) {
    this.rectangle = rectangle;
  }

  public BufferedImage getImg() {
    return img;
  }

  //EVITAR ALTERAR ESSE METODO
  public void setImg(String path) {
    File fileImg = new File(path);
    try {
      img = ImageIO.read(fileImg);
      rectangle.width = img.getWidth();
      rectangle.height = img.getHeight();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
    rectangle.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
    rectangle.y = y;
  }
}