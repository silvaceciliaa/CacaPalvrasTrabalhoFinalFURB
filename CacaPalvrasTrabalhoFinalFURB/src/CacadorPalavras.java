import java.util.Scanner;
public class CacadorPalavras {
        
    public CacadorPalavras() {
        Scanner scanner = new Scanner(System.in);
         String palavras[][] = new String[5][2] ;
         char mapa [][] = new char[10][5];
        
        palavrasEntrada(palavras);
        mapaEntrada(mapa);
        mapaPesquisa(palavras, mapa);

        boolean opcaoMenuSwitch = true;
        int opcaoMenu;

        do {
            System.out.println("_____ Menu: Caça Palavras _____\n 1. Listar Palavras\n 2. Listar Mapa\n 3. Listar Respostas\n 4. Sair");
            System.out.print("__ opção: ");
            opcaoMenu = scanner.nextInt();

            switch (opcaoMenu) {
                case 1:
                    palavrasImprimir(palavras);
                    break;
                case 2:
                    mapaImprimir(mapa);
                    break;
                case 3:
                    palavrasRespostas(palavras, mapa);
                    mapaPesquisa(palavras, mapa);
                    break;
                case 4:
                    opcaoMenuSwitch = false;
                    break;
                default:System.out.println("Opção ERRADA, tente novamente!...");
                    break;
            }
        } while (opcaoMenuSwitch);
        scanner.close();
    }

    private void palavrasEntrada(String[][] palavras){
        palavras[0][0] = "IFELSE";
        palavras[1][0] = "FORA";
        palavras[2][0] = "WHILE";
        palavras[3][0] = "OBJETO";
        palavras[4][0] = "VETOR";
    }

    private void mapaEntrada(char[][] mapa) {
        mapa[ 0][ 0]='D';  mapa[ 0][ 1]='C';  mapa[ 0][ 2]='Q';  mapa[ 0][ 3]='W';  mapa[ 0][ 4]='E';
        mapa[ 1][ 0]='I';  mapa[ 1][ 1]='X';  mapa[ 1][ 2]='F';  mapa[ 1][ 3]='O';  mapa[ 1][ 4]='R';
        mapa[ 2][ 0]='F';  mapa[ 2][ 1]='F';  mapa[ 2][ 2]='R';  mapa[ 2][ 3]='G';  mapa[ 2][ 4]='F';
        mapa[ 3][ 0]='E';  mapa[ 3][ 1]='L';  mapa[ 3][ 2]='I';  mapa[ 3][ 3]='H';  mapa[ 3][ 4]='W';
        mapa[ 4][ 0]='L';  mapa[ 4][ 1]='S';  mapa[ 4][ 2]='F';  mapa[ 4][ 3]='O';  mapa[ 4][ 4]='U';
        mapa[ 5][ 0]='S';  mapa[ 5][ 1]='D';  mapa[ 5][ 2]='G';  mapa[ 5][ 3]='T';  mapa[ 5][ 4]='S';
        mapa[ 6][ 0]='E';  mapa[ 6][ 1]='J';  mapa[ 6][ 2]='H';  mapa[ 6][ 3]='E';  mapa[ 6][ 4]='T';
        mapa[ 7][ 0]='I';  mapa[ 7][ 1]='I';  mapa[ 7][ 2]='I';  mapa[ 7][ 3]='J';  mapa[ 7][ 4]='M';
        mapa[ 8][ 0]='X';  mapa[ 8][ 1]='C';  mapa[ 8][ 2]='K';  mapa[ 8][ 3]='B';  mapa[ 8][ 4]='G';
        mapa[ 9][ 0]='V';  mapa[ 9][ 1]='E';  mapa[ 9][ 2]='T';  mapa[ 9][ 3]='O';  mapa[ 9][ 4]='R';
    }

    public void palavrasImprimir(String[][] palavras) {
        for (int i = 0; i < palavras.length; i++) {
            System.out.println(palavras[i][0]);
        }
    }

    public void palavrasRespostas(String[][] palavras, char[][] mapa) {
        for(int i = 0; i < palavras.length; ++i) {
            String achou = palavras[i][1];
            if (achou == null) {
                System.out.println("Palavra NÃO encontrada: " + palavras[i][0]);
            } else {
                System.out.println(palavras[i][1] + " - " + palavras[i][0]);
            }
        }
    }

    public void mapaImprimir(char[][] mapa) {
        for (int i = 0; i < mapa.length - 1; i++) {
    }
    System.out.println();
    for (char linha = 0; linha < mapa.length; linha++) {
         System.out.print("---------------------");
         System.out.println(" ");
        for (char coluna = 0; coluna < mapa[linha].length; coluna++) {
            if (mapa[linha][coluna] < 10) {
                System.out.print("|  " + mapa[linha][coluna] + " ");
           } else {
                System.out.print("| " + mapa[linha][coluna] + " ");
           }
        }
        System.out.println("|");
    }
    for (int i = 0; i < mapa.length - 1; i++) {
        System.out.print("--");
    }
    System.out.println();
}
    
public void mapaPesquisa(String[][] palavras, char[][] mapa) {
 
    for (int palavraAtual = 0; palavraAtual < palavras.length; palavraAtual++) {
        String palavra = palavras[palavraAtual][0];
        String coordenadas = "";

        coordenadas = buscarHorizontalmente(palavra, mapa, false);
        if (!coordenadas.equals("")) {
            palavras[palavraAtual][1] = coordenadas;
        }

        coordenadas = buscarHorizontalmente(inverterString(palavra), mapa, true);

        if (!coordenadas.equals("")) {
            palavras[palavraAtual][1] = coordenadas;
        }

        coordenadas = buscarVerticalmente(palavra, mapa, false);
        if (!coordenadas.equals("")) {
            palavras[palavraAtual][1] = coordenadas;
        }

        coordenadas = buscarVerticalmente(inverterString(palavra), mapa, true);
        if (!coordenadas.equals("")) {
            palavras[palavraAtual][1] = coordenadas;
        }
    }
}


private String buscarHorizontalmente(String palavra, char[][] mapa, boolean invertida) {
    for (int linha = 0; linha < mapa.length; linha++) {
        for (int coluna = 0; coluna < mapa[0].length - palavra.length() + 1; coluna++) {
            if (verificarPalavra(palavra, linha, coluna, 0, 1, mapa)) {
                if (invertida) {
                    coluna = coluna + palavra.length() - 1;
                }
                return "[" + linha + "," + coluna + "]";
            }
        }
    }
    return "";
}


private String buscarVerticalmente(String palavra, char[][] mapa, boolean invertida) {
    for (int coluna = 0; coluna < mapa[0].length; coluna++) {
        for (int linha = 0; linha < mapa.length - palavra.length() + 1; linha++) {
            if (verificarPalavra(palavra, linha, coluna, 1, 0, mapa)) {
                if (invertida) {
                    linha = linha + palavra.length() - 1;
                }
                return "[" + linha + "," + coluna + "]";
            }
        }
    }
    return "";
}

private boolean verificarPalavra(String palavra, int linha, int coluna, int incrementoLinha, int incrementoColuna, char[][] mapa) {
    char[] palavraArray = palavra.toCharArray();
    for (int i = 0; i < palavraArray.length; i++) {
        if (mapa[linha + i * incrementoLinha][coluna + i * incrementoColuna] != palavraArray[i]) {
            return false;
        }
    }
    return true;
}

private String inverterString(String palavra) {
    char[] palavraArray = palavra.toCharArray();
    int i = 0;
    int j = palavraArray.length - 1;
    while (i < j) {
        char temp = palavraArray[i];
        palavraArray[i] = palavraArray[j];
        palavraArray[j] = temp;
        i++;
        j--;
    }
    return new String(palavraArray);
    }

    public static void main(String[] args) throws Exception {
        new CacadorPalavras();
    }
}   