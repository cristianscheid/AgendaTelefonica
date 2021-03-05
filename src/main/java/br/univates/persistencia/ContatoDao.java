package br.univates.persistencia;

import br.univates.negocio.Contato;
import java.util.ArrayList;

public class ContatoDao
{

    private static ArrayList<Contato> contatos;

    public ContatoDao()
    {
        contatos = new ArrayList<>();
    }

    public ContatoDao(ArrayList<Contato> cont)
    {
        contatos = cont;
    }

    public void salvar(ArrayList<Contato> contatos)
    {
        Arquivo dados = new Arquivo("dados.txt");
        if (dados.abrirEscrita()) {
            contatos.forEach(contato -> {
                dados.escreverLinha(contato.getTelefone() + ";" + contato.getNome());
            });
            dados.fecharArquivo();
        }
    }

    public ArrayList<Contato> ler(int qtdColunas)
    {
        Arquivo dados = new Arquivo("dados.txt");

        if (dados.abrirLeitura()) {

            try {

                String[] info = dados.lerLinha().split(";");

                for (int i = 0; i < info.length; i += qtdColunas) {
                    Contato c = new Contato(info[i], info[i + 1]);
                    contatos.add(c);
                }

            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Nenhum contato");
            }
        }
        dados.fecharArquivo();
        return contatos;

    }

    public boolean criar(Contato contato)
    {
        return contatos.add(contato);
    }

    public Contato getContato(String telefone)
    {
        Contato c = null;
        for (int i = 0; i < contatos.size() && c == null; i++) {
            Contato contato = contatos.get(i);
            System.out.println(contato);
            if (contato != null && contato.getTelefone().equals(telefone)) {
                c = contato;
            }
        }
        return c;
    }

    public boolean deletar(Contato contato)
    {
        return contatos.remove(contato);
    }

    public ArrayList<Contato> getContatos()
    {
        return contatos;
    }
}
