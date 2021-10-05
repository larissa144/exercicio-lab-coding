package br.com.impacta.exercicioLab.service;

import br.com.impacta.exercicioLab.dto.OrderServiceDTO;
import br.com.impacta.exercicioLab.entity.Order;
import br.com.impacta.exercicioLab.exception.EntityNotFoundException;
import br.com.impacta.exercicioLab.exception.InvalidRegistryException;
import br.com.impacta.exercicioLab.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private OrderRepository repository;
    private int idAtual;

    @Autowired
    public OrderService(OrderRepository repository) {
        this.repository = repository;
        this.idAtual = 0;
    }

    public OrderServiceDTO findById(int id) throws EntityNotFoundException {
        Order order = repository.findById(id);

        if (order == null)
            throw new EntityNotFoundException("Pedido com ID " + id + " não encontrado.");

        OrderServiceDTO orderDTO = new OrderServiceDTO(order.getEmail(), order.getNomeCompleto(), order.getAddress(),
                order.getIdPedido(), order.getDescricao(), order.getQntdPedidos(), order.getPrecoUnitario(),
                order.getPrecoTotal(), order.getFormaPagamento(), order.getDataPedido(), order.getStatus(),
                order.getIdTransacao(), order.getNumeroCartao(), order.getValidadeCartao(), order.getBandeira());
        return orderDTO;
    }

    public void save(OrderServiceDTO order) {
        Order ord = new Order(idAtual, order.getEmail(), order.getNomeCompleto(), order.getAddress(),
                order.getIdPedido(), order.getDescricao(), order.getQntdPedidos(), order.getPrecoUnitario(),
                order.getPrecoTotal(), order.getFormaPagamento(), order.getDataPedido(), order.getStatus(),
                order.getIdTransacao(), order.getNumeroCartao(), order.getValidadeCartao(), order.getBandeira());
        this.repository.save(ord);
        idAtual++;
    }

    public void update(int idOrder, OrderServiceDTO order) throws EntityNotFoundException, InvalidRegistryException {
        if (repository.findById(idOrder) == null)
            throw new EntityNotFoundException("Pedido de id " + idOrder + " não encontrado.");
        repository.update(idOrder, order);
    }

    public OrderServiceDTO delete(int idOrder) {
        Order order = repository.delete(idOrder);
        OrderServiceDTO orderDTO = new OrderServiceDTO(order.getEmail(), order.getNomeCompleto(), order.getAddress(),
                order.getIdPedido(), order.getDescricao(), order.getQntdPedidos(), order.getPrecoUnitario(),
                order.getPrecoTotal(), order.getFormaPagamento(), order.getDataPedido(), order.getStatus(),
                order.getIdTransacao(), order.getNumeroCartao(), order.getValidadeCartao(), order.getBandeira());
        return orderDTO;
    }

    public int getCurrentId() {
        return this.idAtual;
    }
}
