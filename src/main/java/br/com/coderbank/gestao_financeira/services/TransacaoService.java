package br.com.coderbank.gestao_financeira.services;

import br.com.coderbank.gestao_financeira.dtos.requests.EntradaRequestDTO;
import br.com.coderbank.gestao_financeira.dtos.responses.TransacaoResponseDTO;
import br.com.coderbank.gestao_financeira.entities.Transacao;
import br.com.coderbank.gestao_financeira.entities.enums.TipoTransacao;
import br.com.coderbank.gestao_financeira.repositories.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransacaoService {

    @Autowired
    TransacaoRepository transacaoRepository;

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

}
