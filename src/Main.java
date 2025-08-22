import Crud.Tarefa;
import Crud.TarefaCrud;
import Enums.Prioridade;
import Enums.Status;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TarefaCrud manager = new TarefaCrud();

        int opcao = 10;

        while (opcao != 0) {
            System.out.println("\n--- GERENCIADOR DE TAREFAS ---");
            System.out.println("1 - Criar tarefa");
            System.out.println("2 - Listar tarefas");
            System.out.println("4 - Deletar tarefa");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1 -> {
                        try {
                            System.out.print("Nome: ");
                            String nome = scanner.nextLine();

                            System.out.print("Descrição: ");
                            String descricao = scanner.nextLine();

                            System.out.print("Data de término (AAAA-MM-DD): ");
                            String dataTermino = scanner.nextLine();

                            System.out.print("Enums.Prioridade (1 a 5): ");
                            int prioridadeNum = 0;
                            Prioridade prioridade = null;

                            while (prioridadeNum < 1 || prioridadeNum > 5) {
                                System.out.print("Enums.Prioridade (1 a 5): ");
                                String entrada = scanner.nextLine();

                                try {
                                    prioridadeNum = Integer.parseInt(entrada);

                                    if (prioridadeNum == 1) prioridade = Prioridade.UM;
                                    else if (prioridadeNum == 2) prioridade = Prioridade.DOIS;
                                    else if (prioridadeNum == 3) prioridade = Prioridade.TRES;
                                    else if (prioridadeNum == 4) prioridade = Prioridade.QUATRO;
                                    else if (prioridadeNum == 5) prioridade = Prioridade.CINCO;
                                    else System.out.println("Número fora do intervalo. Digite de 1 a 5.");

                                } catch (NumberFormatException e) {
                                    System.out.println("Entrada inválida! Digite apenas números.");
                                    prioridadeNum = 0;
                                }
                            }

                            System.out.print("Categoria: ");
                            String categoria = scanner.nextLine();

                            int statusNum = 0;
                            Status status = null;

                            while (statusNum < 1 || statusNum > 3) {
                                System.out.print("Enums.Status (1 - TODO, 2 - DOING, 3 - DONE): ");
                                String entrada2 = scanner.nextLine();

                                try {
                                    statusNum = Integer.parseInt(entrada2);
                                    if (statusNum == 1) status = Status.TODO;
                                    else if (statusNum == 2) status = Status.DOING;
                                    else if (statusNum == 2) status = Status.DONE;
                                    else System.out.println("Número fora do intervalo. Digite de 1 a 3.");
                                } catch (NumberFormatException e) {
                                    System.out.println("Entrada inválida! Digite apenas números.");
                                    statusNum = 0;
                                }
                            }
                            Tarefa novaTarefa = new Tarefa(nome, descricao, dataTermino, prioridade, categoria, status);
                            manager.create(novaTarefa);

                        } catch (IllegalArgumentException e) {
                            System.out.println("Erro: " + e.getMessage());
                        }
                    }
                    case 2 -> {
                        manager.read();
                        System.out.print("Listar por: ");
                        int listar = 0;
                        while (listar < 1 || listar > 3) {
                            System.out.println("1 - Categoria, 2- Prioridade, 3 - Status ");
                            String entrada3 = scanner.nextLine();

                            try{
                                listar = Integer.parseInt(entrada3);
                                if (listar == 1) manager.listByCategoria();
                                else if (listar == 2) manager.listByPrioridade();
                                else if (listar == 3) manager.listByStatus();
                                else System.out.println("Número fora do intervalo. Digite de 1 a 3.");
                            }catch (NumberFormatException e) {
                                System.out.println("Entrada inválida! Digite apenas números.");
                                listar = 0;
                            }
                        }
                    }
                    //case 3 -> update
                    case 4 -> {
                        manager.read();
                        System.out.print("Digite o número da tarefa que deseja remover: ");
                        int index = scanner.nextInt() - 1;
                        scanner.nextLine();
                        manager.delete(index);
                    }
                    case 0 -> System.out.println("Saindo");
                    default -> System.out.println("Opção inválida!");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Entrada inválida! Digite apenas números.");
                scanner.nextLine();
                opcao = 10;
            }
        }
    }
}
