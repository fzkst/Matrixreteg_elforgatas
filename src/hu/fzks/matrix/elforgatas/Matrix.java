package hu.fzks.matrix.elforgatas;

import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Math.min;

public class Matrix {
    private int m;
    private int n;
    private int r;
    private int[][] matrix;


    public Matrix(int m, int n, int r) {
        if (m % 2 == 0){
            this.m = m;
        } else {
            m = m + 1;
            this.m = m;
        }
        if (n % 2 == 0){
            this.n = n;
        } else {
            n = n + 1;
            this.n = n;
        }
        this.r = r;
        this.matrix = new int[m][n];
        matrixFeltoltese();
        for (int i = 0; i < this.r; i++) {
            matrixForgatas();
        }
    }



    public void matrixFeltoltese(){
        //korszam = a mátrix aktuális "gyűrűje"
        int korszam = 0;
        int sor = this.m - korszam;
        int oszlop = this.n - korszam;
        int ciklus = 0;
        //ciklus kiszámítása
        if (sor > oszlop){
            ciklus = oszlop / 2;
        } else {
            ciklus = sor / 2;
        }
        for (int x = 0; x < ciklus; x++) {
            int szamlalo = 1;
            //aktuális felső sort feltölti balról jobbra
            for (int i = 0 + korszam; i < oszlop; i++) {
                this.matrix[0 + korszam][i] = szamlalo;
                szamlalo++;
            }
            //aktuális jobb oldalt feltölti fentről lefelé
            for (int i = 1 + korszam; i < sor; i++) {
                this.matrix[i][oszlop -1] = szamlalo;
                szamlalo++;
            }
            //aktuális alsó sort feltölti jobbról balra
            for (int i = oszlop - 2; i > - (1 - korszam); i--) {
                this.matrix[sor - 1][i] = szamlalo;
                szamlalo++;
            }
            //aktuális bal oldalt feltölti lentről felfelé
            for (int i = sor - 2; i > (0 + korszam); i--) {
                this.matrix[i][0 + korszam] = szamlalo;
                szamlalo++;
            }
            korszam++;
            sor--;
            oszlop--;
        }
    }

    public void matrixForgatas(){
        //korszam = a mátrix aktuális "gyűrűje"
        int korszam = 0;
        int sor = this.m - korszam;
        int oszlop = this.n - korszam;
        int ciklus = 0;
        //ciklus kiszámítása
        if (sor > oszlop){
            ciklus = oszlop / 2;
        } else {
            ciklus = sor / 2;
        }
        for (int x = 0; x < ciklus; x++) {
            //eltárolja a mátrix aktuális gyűrűjének legnagyobb értékét és annak pozícióját a mátrixban
            int maxSzam = 0;
            int maxSzamIndexM = 0;
            int maxSzamIndexN = 0;
            int szamlalo = 1;
            //növeli a mátrix aktuális gyűrűjének felső sorának értékeit balról jobbra
            for (int i = 0 + korszam; i < oszlop; i++) {
                this.matrix[0 + korszam][i]++;
                szamlalo++;
                //összehasonlítja a maxSzam értékét a mátrik aktuális elemével. Ha nagyobb, uj értékeket ad a maxSzam-nak,
                //és eltárolja az aktuális elem pozícióját a mátrixban
                if (maxSzam < this.matrix[0 + korszam][i]){
                    maxSzam = this.matrix[0 + korszam][i];
                    maxSzamIndexM = 0 + korszam;
                    maxSzamIndexN = i;
                }
            }
            //növeli a mátrix aktuális gyűrűjének jobb oldali oszlopának értékeit fentről lefelé
            for (int i = 1 + korszam; i < sor; i++) {
                this.matrix[i][oszlop - 1]++;
                szamlalo++;
                //összehasonlítja a maxSzam értékét a mátrik aktuális elemével. Ha nagyobb, uj értékeket ad a maxSzam-nak,
                //és eltárolja az aktuális elem pozícióját a mátrixban
                if (maxSzam < this.matrix[i][oszlop - 1]){
                    maxSzam = this.matrix[i][oszlop - 1];
                    maxSzamIndexM = i;
                    maxSzamIndexN = oszlop - 1;
                }
            }
            //növeli a mátrix aktuális gyűrűjének alsó sorának értékeit jobbról balra
            for (int i = oszlop - 2; i > - (1 - korszam); i--) {
                this.matrix[sor - 1][i]++;
                szamlalo++;
                //összehasonlítja a maxSzam értékét a mátrik aktuális elemével. Ha nagyobb, uj értékeket ad a maxSzam-nak,
                //és eltárolja az aktuális elem pozícióját a mátrixban
                if (maxSzam < this.matrix[sor - 1][i]){
                    maxSzam = this.matrix[sor - 1][i];
                    maxSzamIndexM = sor - 1;
                    maxSzamIndexN = i;
                }
            }
            //növeli //növeli a mátrix aktuális gyűrűjének felső sorának értékeit balról jobbra bal oszlopának értékeit lentről felfelé
            for (int i = sor - 2; i > (0 + korszam); i--) {
                this.matrix[i][0 + korszam]++;
                szamlalo++;
                //összehasonlítja a maxSzam értékét a mátrik aktuális elemével. Ha nagyobb, uj értékeket ad a maxSzam-nak,
                //és eltárolja az aktuális elem pozícióját a mátrixban
                if (maxSzam < this.matrix[i][0 + korszam]){
                    maxSzam = this.matrix[i][0 + korszam];
                    maxSzamIndexM = i;
                    maxSzamIndexN = 0 + korszam;
                }
            }
            korszam++;
            sor--;
            oszlop--;
            //a mátrix aktuális gyűrűjének legmagasabb értékét 1-re módosítja
            this.matrix[maxSzamIndexM][maxSzamIndexN] = 1;
        }
    }



    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < this.m; i++)
        {
            for (int j = 0; j < this.n; j++)
            {
                stringBuilder.append(String.format("%3d ", this.matrix[i][j]));
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}

