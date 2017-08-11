/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author nigeldasilvalima
 */
public class RunningTimeCounter {
    
    static long startTime; //armazena o tempo de execuçao atual antes do algoritmo começar a rodar
    static long endTime; //armazena o tempo de execuçao atual apos a execuçao do algoritmo
    static long totalTime; //recebe a diferença entre o tempo final e o inicial

    //metodo que inicializa a contagem da execuçao
    public static void start()
    {
        startTime = System.nanoTime();
    }

    //metodo que finaliza a contagem, calcula e exibe o resultado
    public static void stop()
    {
        endTime = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println("Tempo de execução " + totalTime);
    }
}
