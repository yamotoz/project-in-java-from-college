public class ImagemMovida extends Imagem {

    public void moverDireita(int shift) throws PosicaoInvalidaException  {
      int novaX = this.getX() + shift;
      if (novaX >= 1200) {
          throw new PosicaoInvalidaException("TIRAAAAAAAAA, A MOTO VAI EXPLODIRRRRR");
      }
      this.setX(novaX);
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
}

