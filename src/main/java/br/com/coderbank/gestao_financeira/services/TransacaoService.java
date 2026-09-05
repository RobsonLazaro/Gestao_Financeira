package br.com.coderbank.gestao_financeira.services;

import br.com.coderbank.gestao_financeira.dtos.requests.EntradaRequestDTO;
import br.com.coderbank.gestao_financeira.dtos.requests.SaidaRequestDTO;
import br.com.coderbank.gestao_financeira.dtos.responses.CategoriaResponseDTO;
import br.com.coderbank.gestao_financeira.dtos.responses.TransacaoResponseDTO;
import br.com.coderbank.gestao_financeira.entities.Categoria;
import br.com.coderbank.gestao_financeira.entities.Transacao;
import br.com.coderbank.gestao_financeira.entities.enums.TipoTransacao;
import br.com.coderbank.gestao_financeira.exceptions.RecursoNaoEncontradoException;
import br.com.coderbank.gestao_financeira.repositories.CategoriaRepository;
import br.com.coderbank.gestao_financeira.repositories.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class TransacaoService {

    @Autowired
    TransacaoRepository transacaoRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    public TransacaoResponseDTO cadastrarTransacaoDeEntrada(EntradaRequestDTO entradaRequestDTO){

        Transacao transacao = new Transacao();

        transacao.setValor(entradaRequestDTO.valor());
        transacao.setData(entradaRequestDTO.data());
        transacao.setDescricao(entradaRequestDTO.descricao());
        transacao.setTipo(TipoTransacao.ENTRADA);

        var transacaoSalva = transacaoRepository.save(transacao);

        return new TransacaoResponseDTO(
                transacaoSalva.getIdTransacao(),
                transacaoSalva.getTipo(),
                transacaoSalva.getValor(),
                transacaoSalva.getData(),
                transacaoSalva.getDescricao(),
                null,
                transacaoSalva.getDataCriacao()
                );
    }

    public List<TransacaoResponseDTO> listarTransacoesDeEntrada(LocalDate dataInicio, LocalDate dataFim){
        if (dataInicio == null && dataFim == null){
            return transacaoRepository.findByTipo(TipoTransacao.ENTRADA)
                    .stream()
                    .map(transacao -> new TransacaoResponseDTO(
                            transacao.getIdTransacao(),
                            transacao.getTipo(),
                            transacao.getValor(),
                            transacao.getData(),
                            transacao.getDescricao(),
                            null,
                            transacao.getDataCriacao()))
                    .toList();
        }

        else if (dataInicio != null && dataFim != null){
            return transacaoRepository.findByTipoAndDataBetween(TipoTransacao.ENTRADA, dataInicio, dataFim)
                    .stream()
                    .map(transacao ->new TransacaoResponseDTO(
                            transacao.getIdTransacao(),
                            transacao.getTipo(),
                            transacao.getValor(),
                            transacao.getData(),
                            transacao.getDescricao(),
                            null,
                            transacao.getDataCriacao()))
                    .toList();
        }
        else if (dataInicio != null && dataFim == null){
            return transacaoRepository.findByTipoAndDataGreaterThanEqual(TipoTransacao.ENTRADA, dataInicio)
                    .stream()
                    .map(transacao ->new TransacaoResponseDTO(
                            transacao.getIdTransacao(),
                            transacao.getTipo(),
                            transacao.getValor(),
                            transacao.getData(),
                            transacao.getDescricao(),
                            null,
                            transacao.getDataCriacao()))
                    .toList();
        }
        return transacaoRepository.findByTipoAndDataLessThanEqual(TipoTransacao.ENTRADA, dataFim)
                .stream()
                .map(transacao ->new TransacaoResponseDTO(
                        transacao.getIdTransacao(),
                        transacao.getTipo(),
                        transacao.getValor(),
                        transacao.getData(),
                        transacao.getDescricao(),
                        null,
                        transacao.getDataCriacao()))
                .toList();
    }

    public TransacaoResponseDTO cadastrarTransacaoDeSaida(SaidaRequestDTO saidaRequestDTO){

        Transacao transacao = new Transacao();

        transacao.setValor(saidaRequestDTO.valor());
        transacao.setData(saidaRequestDTO.data());
        transacao.setDescricao(saidaRequestDTO.descricao());
        transacao.setTipo(TipoTransacao.SAIDA);

        Categoria categoria = categoriaRepository.findById(saidaRequestDTO.idCategoria()).orElseThrow(() -> new RecursoNaoEncontradoException());
        transacao.setCategoria( categoria);

        var transacaoDeSaidaSalva = transacaoRepository.save(transacao);
        var categoriaResponse = new CategoriaResponseDTO(categoria.getId(), categoria.getNome());

        return new TransacaoResponseDTO(transacaoDeSaidaSalva.getIdTransacao(), transacaoDeSaidaSalva.getTipo(), transacaoDeSaidaSalva.getValor(),transacaoDeSaidaSalva.getData(),transacaoDeSaidaSalva.getDescricao(), categoriaResponse,transacaoDeSaidaSalva.getDataCriacao());
    }

    public List<TransacaoResponseDTO> listarTransacoesDeSaida(LocalDate dataInicio, LocalDate dataFim, UUID idCategoria) {
        if (dataInicio == null && dataFim == null && idCategoria == null){
            return transacaoRepository.findByTipo(TipoTransacao.SAIDA)
                    .stream()
                    .map(transacao -> new TransacaoResponseDTO(
                            transacao.getIdTransacao(),
                            transacao.getTipo(),
                            transacao.getValor(),
                            transacao.getData(),
                            transacao.getDescricao(),
                            new CategoriaResponseDTO(transacao.getCategoria().getId(),transacao.getCategoria().getNome()),
                            transacao.getDataCriacao()))
                    .toList();
        }

        else if(dataInicio == null && dataFim == null && idCategoria != null){
            return transacaoRepository.findByTipoAndCategoria_Id(TipoTransacao.SAIDA, idCategoria)
                    .stream()
                    .map(transacao -> new TransacaoResponseDTO(
                            transacao.getIdTransacao(),
                            transacao.getTipo(),
                            transacao.getValor(),
                            transacao.getData(),
                            transacao.getDescricao(),
                            new CategoriaResponseDTO(transacao.getCategoria().getId(), transacao.getCategoria().getNome()),
                            transacao.getDataCriacao()))
                    .toList();
        }
        else if (dataInicio != null && dataFim == null && idCategoria == null){
            return transacaoRepository.findByTipoAndDataGreaterThanEqual(TipoTransacao.SAIDA, dataInicio)
                    .stream()
                    .map(transacao ->new TransacaoResponseDTO(
                            transacao.getIdTransacao(),
                            transacao.getTipo(),
                            transacao.getValor(),
                            transacao.getData(),
                            transacao.getDescricao(),
                            new CategoriaResponseDTO(
                                    transacao.getCategoria().getId(),
                                    transacao.getCategoria().getNome()
                            ),
                            transacao.getDataCriacao()))
                    .toList();
        } else if (dataInicio == null && dataFim != null && idCategoria == null) {
            return transacaoRepository.findByTipoAndDataLessThanEqual(TipoTransacao.SAIDA, dataFim)
                    .stream()
                    .map(transacao ->new TransacaoResponseDTO(
                            transacao.getIdTransacao(),
                            transacao.getTipo(),
                            transacao.getValor(),
                            transacao.getData(),
                            transacao.getDescricao(),
                            new CategoriaResponseDTO(
                                    transacao.getCategoria().getId(),
                                    transacao.getCategoria().getNome()
                            ),
                            transacao.getDataCriacao()))
                    .toList();
        }
        else if (dataInicio != null && dataFim != null && idCategoria == null) {
            return transacaoRepository.findByTipoAndDataBetween(
                            TipoTransacao.SAIDA,
                            dataInicio,
                            dataFim
                    )
                    .stream()
                    .map(transacao -> new TransacaoResponseDTO(
                            transacao.getIdTransacao(),
                            transacao.getTipo(),
                            transacao.getValor(),
                            transacao.getData(),
                            transacao.getDescricao(),
                            new CategoriaResponseDTO(
                                    transacao.getCategoria().getId(),
                                    transacao.getCategoria().getNome()
                            ),
                            transacao.getDataCriacao()
                    ))
                    .toList();
        }

        else if (dataInicio != null && dataFim == null && idCategoria != null) {
            return transacaoRepository.findByTipoAndCategoria_IdAndDataGreaterThanEqual(
                            TipoTransacao.SAIDA,
                            idCategoria,
                            dataInicio
                    )
                    .stream()
                    .map(transacao -> new TransacaoResponseDTO(
                            transacao.getIdTransacao(),
                            transacao.getTipo(),
                            transacao.getValor(),
                            transacao.getData(),
                            transacao.getDescricao(),
                            new CategoriaResponseDTO(
                                    transacao.getCategoria().getId(),
                                    transacao.getCategoria().getNome()
                            ),
                            transacao.getDataCriacao()
                    ))
                    .toList();
        }

        else if (dataInicio == null && dataFim != null && idCategoria != null) {
            return transacaoRepository.findByTipoAndCategoria_IdAndDataLessThanEqual(
                            TipoTransacao.SAIDA,
                            idCategoria,
                            dataFim
                    )
                    .stream()
                    .map(transacao -> new TransacaoResponseDTO(
                            transacao.getIdTransacao(),
                            transacao.getTipo(),
                            transacao.getValor(),
                            transacao.getData(),
                            transacao.getDescricao(),
                            new CategoriaResponseDTO(
                                    transacao.getCategoria().getId(),
                                    transacao.getCategoria().getNome()
                            ),
                            transacao.getDataCriacao()
                    ))
                    .toList();
        }

        return transacaoRepository
                .findByTipoAndCategoria_IdAndDataBetween(
                TipoTransacao.SAIDA,
                        idCategoria,
                        dataInicio,
                        dataFim
                )
                .stream()
                .map(transacao -> new TransacaoResponseDTO(
                        transacao.getIdTransacao(),
                        transacao.getTipo(),
                        transacao.getValor(),
                        transacao.getData(),
                        transacao.getDescricao(),
                        new CategoriaResponseDTO(
                                transacao.getCategoria().getId(),
                                transacao.getCategoria().getNome()
                        ),
                        transacao.getDataCriacao()
                        )
                )
                .toList();
    }



}


