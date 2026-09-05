package br.com.coderbank.gestao_financeira.repositories;

import br.com.coderbank.gestao_financeira.entities.Transacao;
import br.com.coderbank.gestao_financeira.entities.enums.TipoTransacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TransacaoRepository extends JpaRepository <Transacao, UUID> {

    List<Transacao> findByTipo(TipoTransacao tipoTransacao);

    List<Transacao> findByTipoAndDataBetween(
            TipoTransacao tipo,
            LocalDate dataInicio,
            LocalDate dataFim
    );
    List<Transacao> findByTipoAndDataGreaterThanEqual(
            TipoTransacao tipo,
            LocalDate dataInicio
    );

    List<Transacao> findByTipoAndDataLessThanEqual(
            TipoTransacao tipo,
            LocalDate dataFim
    );

    List<Transacao> findByTipoAndCategoria_Id(
            TipoTransacao tipo,
            UUID idCategoria
    );
    List<Transacao> findByTipoAndCategoria_IdAndDataGreaterThanEqual(
            TipoTransacao tipo,
            UUID idCategoria,
            LocalDate dataInicio
    );

    List<Transacao> findByTipoAndCategoria_IdAndDataLessThanEqual(
            TipoTransacao tipo,
            UUID idCategoria,
            LocalDate dataFim
    );

    List<Transacao> findByTipoAndCategoria_IdAndDataBetween(
            TipoTransacao tipo,
            UUID idCategoria,
            LocalDate dataInicio,
            LocalDate dataFim
    );

}
