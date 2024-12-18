package main.java.factory;

import main.java.dao.*;

public class DAOFactory {
    private static volatile AlunoDAO alunoDAO;
    private static volatile DebitoDAO debitoDAO;
    private static volatile DevolucaoDAO devolucaoDAO;
    private static volatile EmprestimoDAO emprestimoDAO;
    private static volatile ItemEmprestimoDAO itemEmprestimoDAO;
    private static volatile LivroDAO livroDAO;

    // Retorna instância de AlunoDAO
    public static AlunoDAO getAlunoDAO() {
        if (alunoDAO == null) {
            synchronized (DAOFactory.class) {
                if (alunoDAO == null) {
                    alunoDAO = new AlunoDAO();
                }
            }
        }
        return alunoDAO;
    }

    // Retorna instância de DebitoDAO
    public static DebitoDAO getDebitoDAO() {
        if (debitoDAO == null) {
            synchronized (DAOFactory.class) {
                if (debitoDAO == null) {
                    debitoDAO = new DebitoDAO();
                }
            }
        }
        return debitoDAO;
    }

    // Retorna instância de DevolucaoDAO
    public static DevolucaoDAO getDevolucaoDAO() {
        if (devolucaoDAO == null) {
            synchronized (DAOFactory.class) {
                if (devolucaoDAO == null) {
                    devolucaoDAO = new DevolucaoDAO();
                }
            }
        }
        return devolucaoDAO;
    }

    // Retorna instância de EmprestimoDAO
    public static EmprestimoDAO getEmprestimoDAO() {
        if (emprestimoDAO == null) {
            synchronized (DAOFactory.class) {
                if (emprestimoDAO == null) {
                    emprestimoDAO = new EmprestimoDAO();
                }
            }
        }
        return emprestimoDAO;
    }

    // Retorna instância de ItemEmprestimoDAO
    public static ItemEmprestimoDAO getItemEmprestimoDAO() {
        if (itemEmprestimoDAO == null) {
            synchronized (DAOFactory.class) {
                if (itemEmprestimoDAO == null) {
                    itemEmprestimoDAO = new ItemEmprestimoDAO();
                }
            }
        }
        return itemEmprestimoDAO;
    }

    // Retorna instância de LivroDAO
    public static LivroDAO getLivroDAO() {
        if (livroDAO == null) {
            synchronized (DAOFactory.class) {
                if (livroDAO == null) {
                    livroDAO = new LivroDAO();
                }
            }
        }
        return livroDAO;
    }

    // Método opcional para limpar as instâncias (caso necessário)
    public static void clearInstances() {
        alunoDAO = null;
        debitoDAO = null;
        devolucaoDAO = null;
        emprestimoDAO = null;
        itemEmprestimoDAO = null;
        livroDAO = null;
    }
}
