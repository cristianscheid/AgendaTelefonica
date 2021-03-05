package br.univates.persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * **********
 * Arquivo - versão 25/05/2007
 *
 * abrirEscrita() --> boolean abrirLeitura() --> boolean lerLinha() --> string
 * escreverLinha( <string> ) fecharArquivo()
 */
public class Arquivo
{

    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String nomeArquivo;
    private char status;

    public Arquivo(String nomeArquivo)
    {
        this.nomeArquivo = nomeArquivo;
        status = 'C'; // Closed, Read, Write
    }

    public Arquivo()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean abrirLeitura()
    {
        boolean sucesso = true;
        FileReader fileReader = null;
        if (status != 'C') {
            System.err.println("-- Arquivo \"" + nomeArquivo + "\" já está aberto");
            sucesso = false;
        }
        else {
            try {
                fileReader = new FileReader(nomeArquivo);
            } catch (FileNotFoundException e) {
                System.err.println("-- Arquivo \"" + nomeArquivo + "\" não encontrado");
                sucesso = false;
                //e.printStackTrace();
            }
            if (sucesso) {
                status = 'R';
                bufferedReader = new BufferedReader(fileReader);
            }
        }
        return (sucesso);
    }

    public boolean abrirEscrita()
    {
        boolean sucesso = true;
        FileWriter fileWriter = null;
        if (status != 'C') {
            System.err.println("-- Arquivo \"" + nomeArquivo + "\" já está aberto");
            sucesso = false;
        }
        else {
            try {
                fileWriter = new FileWriter(nomeArquivo);
            } catch (IOException e) {
                System.err.println("-- Erro de escrita no arquivo \"" + nomeArquivo + "\"");
                sucesso = false;
                //e.printStackTrace();
            }
            if (sucesso) {
                status = 'W';
                bufferedWriter = new BufferedWriter(fileWriter);
            }
        }
        return (sucesso);
    }

    public String lerLinha()
    {
        String linha = "";
        String linhas = "";
        if (status == 'R') {
            try {
                while ((linha = bufferedReader.readLine()) != null) {
                    linhas = linhas.concat(linha + ";");
                }
            } catch (IOException e) {
                System.err.println("-- Erro de leitura no arquivo \"" + nomeArquivo + "\"");
                //e.printStackTrace();
            }
        }
        else {
            System.err.println("-- Arquivo \"" + nomeArquivo + "\" não está pronto para leitura");
        }
        return (linhas);
    }

    public void escreverLinha(String linha)
    {
        if (status == 'W') {
            try {
                bufferedWriter.write(linha + "\n");
            } catch (IOException e) {
                System.err.println("Erro de escrita no arquivo \"" + nomeArquivo + "\"");
                //e.printStackTrace();
            }
        }
        else {
            System.err.println("-- Arquivo \"" + nomeArquivo + "\" não está pronto para escrita");
        }
    }

    public void fecharArquivo()
    {
        if (status == 'R') // estava aberto para leitura
        {
            try {
                bufferedReader.close();
                status = 'C';
            } catch (IOException e) {
                System.err.println("Erro ao fechar o arquivo \"" + nomeArquivo + "\"");
                //e.printStackTrace();
            }
        }
        if (status == 'W') // estava aberto para gravação
        {
            try {
                bufferedWriter.close();
                status = 'C';
            } catch (IOException e) {
                System.err.println("Erro ao fechar o arquivo \"" + nomeArquivo + "\"");
                //e.printStackTrace();
            }
        }
    }
}
