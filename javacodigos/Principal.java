import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;


public class Principal extends JFrame implements MouseMotionListener {


    private BufferedImage background;
    private ImagemMovida moto;
    private JatoLuxo jatinho;
    private int targetX;
    private int targetY;

    // config referente a largura da tela onde o jato vai parar e retroceder
    private final int telaLargura = 900;



    public Principal() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addMouseMotionListener(this);

        jatinho = new JatoLuxo();
        jatinho.setX(10);
        jatinho.setY(50);
        jatinho.setImg("jato.png");


        moto = new ImagemMovida();
        moto.setX(59);
        moto.setY(541);
        moto.setImg("PanigaleV4.png");

        // Inicializa o destino da moto para a posição inicial
        targetX = moto.getX();
        targetY = moto.getY();
        targetX = jatinho.getX();
        targetY = jatinho.getY();
        try {
            // Carrega a imagem de fundo
            background = ImageIO.read(new File("pistaFinal.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }



        //CRIANDO THREAD PARA o Jato
        Thread threadR3 = new Thread(new Runnable() {
            public void run(){
                while (true){
                    atualizarJato();
                    repaint();
                    try{
                        Thread.sleep(1);
                    } catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        });
        threadR3.start();    
    }


  //ESSE EH O METODO QUE DEVE SER ADAPTADO AO PROJETO
    public void renderizarImagens(Graphics g2) {
        g2.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        moto.desenhar(g2);
        jatinho.desenhar(g2);
        if(moto.intercepta(jatinho)){
            System.out.println("sons de explosão BUMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
            moto.setImg("Motobatida.png");
        }
        if(jatinho.intercepta(moto)){
            jatinho.setImg("jatinhoquebrado.png");


        }

    }




    // MOVIMENTACAÇÃO DO MOTO COM O MOUSE
    public void atualizarPosicaoMoto() {
        // Calcula a direção do movimento
        int deltaX = targetX - moto.getX();
        int deltaY = targetY - moto.getY();


        // Move a moto em direção ao destino
        try{
            if (Math.abs(deltaX) > 1) {
            int incrementX = (int) Math.signum(deltaX);
            moto.moverDireita(incrementX);
        }

        if (Math.abs(deltaY) > 1) {
            int incrementY = (int) Math.signum(deltaY);
            moto.moverBaixo(incrementY);
        }
        } catch(PosicaoInvalidaException e){
            System.err.println("Erro: " + e.getMessage());
        }


    }
//Math.abs() é usado para obter o valor absoluto de um número.
//Math.signum() está em situações em que você deseja saber apenas a direção (positiva, negativa ou nula) de um número, sem se preocupar com o valor real.

   public void atualizarJato(){
        int velocidade = 1;
        int deltaX = targetX - jatinho.getX();


        if (moto.intercepta(jatinho)) {

             for (int i = 0; i < deltaX; i++) {
                jatinho.moverBaixo(velocidade);
                repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } 
        else {

            for (int i = 0; i < deltaX; i++) {
                jatinho.moverDireita(velocidade);
                repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        // calculo onde se o jato bater na largura da tela ele volta pra uma certa localização(no caso o 100 ali em baixo)
        if (jatinho.getX() < 1) {
            // Reposicione o jato para o início da tela
            jatinho.setX(telaLargura);
        } else if (jatinho.getX() > telaLargura) {
            // Reposicione o jato para o início da tela
            jatinho.setX(30);
        }  
   }






    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        System.out.println("[" + e.getX() + ";" + e.getY() + "]");

        // Atualiza o destino da moto para a posição do mouse
        targetX = e.getX();
        targetY = e.getY();
    }

    public void gameLoop() {
        atualizarPosicaoMoto();
        repaint();
        //repaint() é usado para garantir que os gráficos sejam atualizados de acordo com as alterações no estado do jogo ou do aplicativo gráfico.
    }

     //EVITAR ALTERAR ESSE METODO
    public static void main(String[] args){
        Principal t = new Principal();
        t.setSize(1300, 750);
        t.createBufferStrategy(1);
        t.setVisible(true);
        t.createBufferStrategy(2);

        // Inicie um loop de jogo para atualizar a posição da moto
        // o t.gameLoop(); foi criado para setar o fps e novas atualizações do joguin
        //Animação da moto vermelha PANIGALE V4
        while (true) {
            t.gameLoop();
            try {
                Thread.sleep(1); // FPS da moto
            } catch (InterruptedException e) {
                e.printStackTrace();
                // Isso é útil para depuração e diagnóstico de erros em um programa Java.
            }
        }

    }





    //EVITAR ALTERAR ESSE METODO
    //plano de fundo
    public void renderizarGraphics() {
        if (!getBufferStrategy().contentsLost()) getBufferStrategy().show();
        Graphics g = getBufferStrategy().getDrawGraphics();
        Graphics g2 = g.create();
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, getWidth(), getHeight());
        renderizarImagens(g2);
        g.dispose();
        g2.dispose();
    }

    //EVITAR ALTERAR ESSE METODO
    public void paint(Graphics g) {
        this.renderizarGraphics();
    }


}
