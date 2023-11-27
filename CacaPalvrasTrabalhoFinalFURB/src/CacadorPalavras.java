import java.util.Scanner;

public class CacadorPalavras {

    public static void main(String[] args) throws Exception {
        new CacaPalavras();
    }
}

class CacaPalavras {
    private static Scanner scanner = new Scanner(System.in);

    private String[][] palavras;
    private char[][] mapa;

    public CacaPalavras() {
        palavras = new String[5][2];
        mapa = new char[10][5];

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
                    palavrasRespostas(palavras);
                    break;
                case 4:
                    opcaoMenuSwitch = false;
                    break;
                default:System.out.println("Opção ERRADA, tente novamente!...");
                    break;
            }
        } while (opcaoMenuSwitch);

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

    public void palavrasRespostas(String[][] palavras) {
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

        for (char[] linha : mapa) {
            for (char caractere : linha) {
                System.out.print(caractere + " ");
            }
            System.out.println();
        }
    }

    public void mapaPesquisa(String[][] palavras, char[][] mapa) {
    for (int palavraAtual = 0; palavraAtual < palavras.length; palavraAtual++) {
        String palavra = palavras[palavraAtual][0];
        String coordenadas = "";

        coordenadas = buscarHorizontalmente(palavra, mapa);
        if (!coordenadas.equals("")) {
            palavras[palavraAtual][1] = coordenadas;
            continue;
        }

        coordenadas = buscarHorizontalmente(inverterString(palavra), mapa);
        if (!coordenadas.equals("")) {
            palavras[palavraAtual][1] = coordenadas;
            continue;
        }

        coordenadas = buscarVerticalmente(palavra, mapa);
        if (!coordenadas.equals("")) {
            palavras[palavraAtual][1] = coordenadas;
            continue;
        }

        coordenadas = buscarVerticalmente(inverterString(palavra), mapa);
        if (!coordenadas.equals("")) {
            palavras[palavraAtual][1] = coordenadas;
        }
    }
}

private String buscarHorizontalmente(String palavra, char[][] mapa) {
    for (int linha = 0; linha < mapa.length; linha++) {
        for (int coluna = 0; coluna < mapa[0].length - palavra.length() + 1; coluna++) {
            if (verificarPalavra(palavra, linha, coluna, 0, 1, mapa)) {
                return "[" + linha + "," + coluna + "]";
            }
        }
    }
    return "";
}

private String buscarVerticalmente(String palavra, char[][] mapa) {
    for (int coluna = 0; coluna < mapa[0].length; coluna++) {
        for (int linha = 0; linha < mapa.length - palavra.length() + 1; linha++) {
            if (verificarPalavra(palavra, linha, coluna, 1, 0, mapa)) {
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
}