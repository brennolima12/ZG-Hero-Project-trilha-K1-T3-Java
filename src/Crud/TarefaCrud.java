package Crud;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TarefaCrud {
    private final List<Tarefa> tarefas = new ArrayList<>();

    public void create(Tarefa tarefa) {
        tarefas.add(tarefa);
        System.out.println("Tarefa adicionada: " + tarefa.getNome());
    }

    public void read() {
        if (tarefas.isEmpty()) {
            System.out.println("Cadastre uma tarefa.");
        } else {
            System.out.println("\n--- Lista de Tarefas ---");
            printLista(tarefas);
        }
    }

    public void delete(int index) {
        if (index >= 0 && index < tarefas.size()) {
            Tarefa removida = tarefas.remove(index);
            System.out.println("Tarefa deletada: " + removida.getNome());
        } else {
            System.out.println("Não existe essa tarefa");
        }
    }
    public List<Tarefa> getTarefas() {
        return tarefas;
    }
    public void listByCategoria() {
        if (tarefas.isEmpty()) {
            System.out.println("Cadastre uma tarefa.");
            return;
        }
        System.out.println("\n--- Tarefas por Categoria ---");
        List<Tarefa> copia = new ArrayList<>(tarefas);
        copia.sort(Comparator.comparing(Tarefa::getCategoria, String.CASE_INSENSITIVE_ORDER));
        printLista(copia);
    }

    public void listByPrioridade() {
        if (tarefas.isEmpty()) {
            System.out.println("Cadastre uma tarefa.");
            return;
        }
        System.out.println("\n--- Tarefas por Prioridade ---");
        List<Tarefa> copia = new ArrayList<>(tarefas);
        copia.sort(Comparator.comparingInt(t -> t.getPrioridade().ordinal()));
        printLista(copia);
    }
    public void listByStatus() {
        if (tarefas.isEmpty()) {
            System.out.println("Cadastre uma tarefa.");
            return;
        }
        System.out.println("\n--- Tarefas por Status ---");
        List<Tarefa> copia = new ArrayList<>(tarefas);
        copia.sort(Comparator.comparingInt(t -> t.getStatus().ordinal()));
        printLista(copia);
    }
    private void printLista(List<Tarefa> lista) {
        for (int i = 0; i < lista.size(); i++) {
            Tarefa t = lista.get(i);
            System.out.println((i + 1) + ") " + t.getNome()
                    + " - Categoria: " + t.getCategoria()
                    + " - Status: " + t.getStatus()
                    + " - Prioridade: " + t.getPrioridade());
        }
    }
}
