public class JatoLuxo extends ImagemMovida {
    private double angulo;
    public void moverDireita(int shift) {
        this.setX(this.getX() + shift);
      }

      public void moverEsquerda(int shift) {
        this.setX(this.getX() - shift);
      }

      public void moverCima(int shift) {
        this.setY(this.getY() - shift);
      }

      public void moverBaixo(int shift) {
        this.setY(this.getY() + shift);
      }
      public void setAngulo(double angulo){
        this.angulo = angulo;
      }
      public double getAngulo(){
        return angulo;
      }
}

