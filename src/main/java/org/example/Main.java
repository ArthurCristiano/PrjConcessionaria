package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.model.Cliente;
import org.example.model.Funcionario;
import org.example.model.Veiculo;
import org.example.model.Venda;
import org.example.service.ClienteService;
import org.example.service.FuncionarioService;
import org.example.service.VeiculoService;
import org.example.service.VendaService;
import org.example.vo.RelatorioValorVendidoFunc;
import org.example.vo.RelatorioVendasPeriodo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static EntityManager em;

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PostgresPU");
        em = emf.createEntityManager();

        ClienteService clienteService = new ClienteService(em);
        FuncionarioService FuncionarioService = new FuncionarioService(em);
        VeiculoService veiculoService = new VeiculoService(em);
        VendaService vendaService = new VendaService(em);

        boolean continuar = true;
        while (continuar) {
            System.out.println("----- MENU -----");

            System.out.println("\n------ Funcionário ---------");
            System.out.println("1. Cadastrar Funcionário.");
            System.out.println("2. Alterar Funcionário.");
            System.out.println("3. Excluir Funcionário.");

            System.out.println("\n------- Cliente --------");
            System.out.println("4. Cadastrar Cliente.");
            System.out.println("5. Alterar Cliente.");
            System.out.println("6. Excluir Cliente.");

            System.out.println("\n------- Veículo --------");
            System.out.println("7. Cadastrar Veículo.");
            System.out.println("8. Alterar Veículo.");
            System.out.println("9. Excluir Veículo.");
            System.out.println("10. Listar Veículos.");

            System.out.println("\n--------- Venda ---------");
            System.out.println("11. Cadastrar Venda.");
            System.out.println("12. Excluir Venda.");

            System.out.println("\n------- Relatórios --------");
            System.out.println("13. Relatório de vendas por período.");
            System.out.println("14. Relatório de valor vendido por funcionário.");

            System.out.println("0. Sair.");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> cadastrarFuncionario(FuncionarioService);
                case 2 -> alterarFuncionario(FuncionarioService);
                case 3 -> excluirFuncionario(FuncionarioService);

                case 4 -> cadastrarCliente(clienteService);
                case 5 -> alterarCliente(clienteService);
                case 6 -> excluirCliente(clienteService);

                case 7 -> cadastrarVeiculo(veiculoService);
                case 8 -> alterarVeiculo(veiculoService);
                case 9 -> excluirVeiculo(veiculoService);
                case 10 -> listarVeiculos(veiculoService);

                case 11 -> cadastrarVenda(vendaService, veiculoService);
                case 12 -> excluirVenda(vendaService);

                case 13 -> consultarVendaPorPeriodo(vendaService);
                case 14 -> consultarValorVendidoFunc(vendaService);

                case 0 -> continuar = false;
                default -> System.out.println("Opção inválida!");
            }
        }
        em.close();
        emf.close();
    }

    private static void cadastrarFuncionario(FuncionarioService funcionarioService) {
        System.out.print("Digite o nome do funcionário: ");
        String nome = scanner.nextLine();

        String documento;
        while (true) {
            System.out.println("Digite o documento do funcionário (ou 0 para cancelar): ");
            documento = scanner.nextLine();

            if (documento.equals("0")) {
                System.out.println("Cadastro cancelado.");
                return;
            }

            if (funcionarioService.buscarFuncionarioPorDocumento(documento) != null) {
                System.out.println("Erro: Documento já cadastrado! Tente novamente.");
            } else {
                break;
            }
        }

        System.out.println("Digite o salário do funcionário: ");
        Double salario = scanner.nextDouble();
        scanner.nextLine();

        Funcionario funcionario = new Funcionario(nome, documento, salario);
        funcionarioService.inserir(funcionario);

        System.out.println("Funcionário cadastrado com sucesso!");
    }

    private static void alterarFuncionario(FuncionarioService funcionarioService) {
        List<Funcionario> funcionarioList = funcionarioService.buscarTodosOsFuncionarios();
        funcionarioList.forEach(System.out::println);

        System.out.print("Digite o ID do funcionário a ser alterada: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        Funcionario funcionario = funcionarioService.buscarFuncionarioPorId(id);
        if (funcionario != null) {
            System.out.print("Digite o novo nome do funcionário: ");
            funcionario.setNomeFuncionario(scanner.nextLine());
            System.out.print("Digite o novo documento: ");
            funcionario.setDocFuncionario(scanner.nextLine());
            System.out.println("Digite o novo salário: ");
            funcionario.setDocFuncionario(scanner.nextLine());
            funcionarioService.alterar(funcionario);
            System.out.println("Funcionário alterada com sucesso!");
        } else {
            System.out.println("Funcionário não encontrada.");
        }
    }

    private static void excluirFuncionario(FuncionarioService funcionarioService) {
        List<Funcionario> funcionarioList = funcionarioService.buscarTodosOsFuncionarios();
        funcionarioList.forEach(System.out::println);

        System.out.print("Digite o ID da funcionário a ser excluído: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        Funcionario funcionario = funcionarioService.buscarFuncionarioPorId(id);
        if (funcionario != null) {
            funcionarioService.excluir(funcionario);
            System.out.println("Funcionário excluído com sucesso!");
        } else {
            System.out.println("Funcionário não encontrado.");
        }
    }

    private static void cadastrarCliente(ClienteService clienteService) {
        System.out.print("Digite o nome do cliente: ");
        String nome = scanner.nextLine();

        String documento;
        while (true) {
            System.out.println("Digite o documento do cliente: (ou 0 para cancelar): ");
            documento = scanner.nextLine();

            if (documento.equals("0")) {
                System.out.println("Cadastro cancelado.");
                return;
            }

            if (clienteService.buscarClientePorDocumento(documento) != null) {
                System.out.println("Erro: Documento já cadastrado! Tente novamente.");
            } else {
                break;
            }
        }

        Cliente cliente = new Cliente(nome, documento);
        clienteService.inserir(cliente);

        System.out.println("Cliente cadastrado com sucesso!");
    }

    private static void alterarCliente(ClienteService clienteService) {
        List<Cliente> clienteList = clienteService.buscarTodosOsClientes();
        clienteList.forEach(System.out::println);

        System.out.print("Digite o ID do cliente a ser alterada: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        Cliente cliente = clienteService.buscarClientesPorId(id);
        if (cliente != null) {
            System.out.print("Digite o novo nome do cliente: ");
            cliente.setNomeCliente(scanner.nextLine());;
            System.out.print("Digite o novo documento: ");
            cliente.setDocCliente(scanner.nextLine());
            clienteService.alterar(cliente);

            System.out.println("Cliente alterado com sucesso!");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private static void excluirCliente(ClienteService clienteService) {
        List<Cliente> clienteList = clienteService.buscarTodosOsClientes();
        clienteList.forEach(System.out::println);

        System.out.print("Digite o ID do cliente a ser excluído: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        Cliente cliente = clienteService.buscarClientesPorId(id);
        if (cliente != null) {
            clienteService.excluir(cliente);
            System.out.println("Cliente excluío com sucesso!");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private static void cadastrarVeiculo(VeiculoService veiculoService) {
        System.out.print("Digite a marca do veículo: ");
        String marca = scanner.nextLine();
        System.out.println("Digite o modelo: ");
        String modelo = scanner.nextLine();
        System.out.println("Digite o ano: ");
        String ano = scanner.nextLine();
        System.out.println("Digite a cor: ");
        String cor = scanner.nextLine();
        System.out.println("Digite o preço: ");
        Double preco = scanner.nextDouble();
        System.out.println("Digite a quantidade: ");
        Integer qtde = scanner.nextInt();

        Veiculo veiculo = new Veiculo(marca, modelo, ano, cor, preco, qtde);
        veiculoService.inserir(veiculo);

        System.out.println("Veículo cadastrado com sucesso!");
    }

    private static void alterarVeiculo(VeiculoService veiculoService) {
        List<Veiculo> veiculosList = veiculoService.buscarTodosOsVeiculos();
        veiculosList.forEach(System.out::println);

        System.out.print("Digite o ID do veiculo a ser alterada: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        Veiculo veiculo = veiculoService.buscarVeiculoPorId(id);
        if (veiculo != null) {
            System.out.print("Digite a marca do veículo: ");
            veiculo.setMarca(scanner.nextLine());
            System.out.println("Digite o modelo: ");
            veiculo.setModelo(scanner.nextLine());
            System.out.println("Digite o ano: ");
            veiculo.setAno(scanner.nextLine());
            System.out.println("Digite a cor: ");
            veiculo.setCor(scanner.nextLine());
            System.out.println("Digite o preço: ");
            veiculo.setPreco(scanner.nextDouble());
            System.out.println("Digite a quantidade: ");
            veiculo.setQtde(scanner.nextInt());
            veiculoService.alterar(veiculo);

            System.out.println("Veículo alterado com sucesso!");
        } else {
            System.out.println("Veículo não encontrado.");
        }
    }

    private static void excluirVeiculo(VeiculoService veiculoService) {
        List<Veiculo> veiculosList = veiculoService.buscarTodosOsVeiculos();
        veiculosList.forEach(System.out::println);

        System.out.print("Digite o ID do veículo a ser excluído: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        Veiculo veiculo = veiculoService.buscarVeiculoPorId(id);
        if (veiculo != null) {
            veiculoService.excluir(veiculo);
            System.out.println("Veículo excluío com sucesso!");
        } else {
            System.out.println("Veículo não encontrado.");
        }
    }

    private static List<Veiculo> listarVeiculos(VeiculoService veiculoService){
        List<Veiculo> veiculosList = veiculoService.buscarTodosOsVeiculos();
        veiculosList.forEach(System.out::println);
        return veiculosList;
    }

    private static void cadastrarVenda(VendaService vendaService, VeiculoService veiculoService) {
        System.out.println("==== VENDA ====\n");

        System.out.print("Digite o ID do cliente: ");
        Long clienteId = scanner.nextLong();
        Cliente cliente = em.find(Cliente.class, clienteId);

        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.print("Digite o ID do funcionário: ");
        Long funcionarioId = scanner.nextLong();
        Funcionario funcionario = em.find(Funcionario.class, funcionarioId);

        if (funcionario == null) {
            System.out.println("Funcionário não encontrado.");
            return;
        }

        System.out.print("Digite o ID do veículo: ");
        Long veiculoId = scanner.nextLong();
        Veiculo veiculo = em.find(Veiculo.class, veiculoId);

        if (veiculo == null) {
            System.out.println("Veículo não encontrado.");
            return;
        }

        System.out.print("Informe o valor da venda: ");
        Double valorVenda = scanner.nextDouble();

        if (valorVenda <= 0) {
            valorVenda = veiculo.getPreco();
            System.out.println("Nenhum valor válido informado. Usando o preço do veículo: " + valorVenda);
        }

        Venda venda = new Venda(cliente, funcionario, veiculo, valorVenda);
        vendaService.inserir(venda);

        System.out.println("Venda cadastrada com sucesso!");
    }

    private static void excluirVenda(VendaService vendaService) {
        System.out.print("Digite o ID da venda a ser excluída: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        Venda venda = vendaService.buscarVendaPorId(id);
        if (venda != null) {
            vendaService.excluir(venda);
            System.out.println("Venda excluída com sucesso!");
        } else {
            System.out.println("Venda não encontrado.");
        }
    }

    private static void consultarVendaPorPeriodo(VendaService vendaService) {
        System.out.print("Digite a data de início (yyyy-MM-dd): ");
        LocalDate dataInicio = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.print("Digite a data de fim (yyyy-MM-dd): ");
        LocalDate dataFim = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ISO_LOCAL_DATE);

        List<RelatorioVendasPeriodo> vendas = vendaService.retornaRelatorioDeVendas(dataInicio, dataFim);
        if (!vendas.isEmpty()) {
            vendas.forEach(System.out::println);

            System.out.println("Deseja exportar o relatório para um arquivo .txt? (s/n): ");
            String resposta = scanner.nextLine();

            if (resposta.equals("s")) {
                exportarRelatorioPeriodoParaTxt(vendas, dataInicio, dataFim);
            }

        } else {
            System.out.println("Nenhuma venda encontrada no período informado.");
        }
    }

    public static void consultarValorVendidoFunc(VendaService vendaService) {
        System.out.println("Digite o id do funcionário: ");
        Long IdFuncionario = scanner.nextLong();
        scanner.nextLine();

        List<RelatorioValorVendidoFunc> relatorio = vendaService.retornaRelatorioVendidoFunc(IdFuncionario);
        if(!relatorio.isEmpty()) {
            relatorio.forEach(System.out::println);

            System.out.print("Deseja exportar o relatório para um arquivo .txt? (s/n): ");
            String resposta = scanner.nextLine();

            if (resposta.equals("s")) {
                exportarRelatorioValorVendidoTxt(relatorio);
            }

        }else{
            System.out.println("Nenhuma venda encontrada.");
        }
    }

    private static void exportarRelatorioPeriodoParaTxt(List<RelatorioVendasPeriodo> vendas, LocalDate dataInicio, LocalDate dataFim) {
        File arquivo = new File("C:\\Users\\arthu\\OneDrive\\Área de Trabalho\\Relatorio_Vendas_" + dataInicio + "_a_" + dataFim + ".txt");

        try (FileWriter arquivoEscrito = new FileWriter(arquivo);
             BufferedWriter buffer = new BufferedWriter(arquivoEscrito)) {

            buffer.write("Relatório de Vendas - Período: " + dataInicio + " a " + dataFim);
            buffer.newLine();
            buffer.write("---------------------------------------------------");
            buffer.newLine();

            for (RelatorioVendasPeriodo venda : vendas) {
                buffer.write(venda.toString());
                buffer.newLine();
            }

            buffer.flush();
            System.out.println("Arquivo gravado com sucesso! Nome: " + arquivo);

        } catch (IOException e) {
            System.out.println("Erro ao salvar o relatório: " + e.getMessage());
        }
    }

    private static void exportarRelatorioValorVendidoTxt(List<RelatorioValorVendidoFunc> relatorio) {
        File arquivo = new File("C:\\Users\\arthu\\OneDrive\\Área de Trabalho\\Relatorio_Valor_Vendido.txt");

        try (FileWriter arquivoEscrito = new FileWriter(arquivo);
             BufferedWriter buffer = new BufferedWriter(arquivoEscrito)) {

            buffer.write("Relatório Valor vendido por Funcionário");
            buffer.newLine();
            buffer.write("---------------------------------------------------");
            buffer.newLine();

            for (RelatorioValorVendidoFunc rel : relatorio) {
                buffer.write(rel.toString());
                buffer.newLine();
            }

            buffer.flush();
            System.out.println("Arquivo gravado com sucesso! Nome: " + arquivo);

        } catch (IOException e) {
            System.out.println("Erro ao salvar o relatório: " + e.getMessage());
        }
    }
}