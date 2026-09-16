package br.com.coderbank.gestao_financeira.specification;

import br.com.coderbank.gestao_financeira.entities.Transacao;
import br.com.coderbank.gestao_financeira.entities.enums.TipoTransacao;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.UUID;

public class TransacaoSpecification{

    public static Specification<Transacao> porTipo(TipoTransacao tipoTransacao){
        return new Specification<Transacao>() {
            @Override
            public Predicate toPredicate(Root<Transacao> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (tipoTransacao == null){
                    return criteriaBuilder.conjunction();
                }
                return criteriaBuilder.equal(root.get("tipo"), tipoTransacao);
            }
        };
    }

    public static Specification<Transacao> porCategoria(UUID idCategoria){
        return new Specification<Transacao>() {
            @Override
            public Predicate toPredicate(Root<Transacao> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (idCategoria == null){
                    return criteriaBuilder.conjunction();
                }
                return criteriaBuilder.equal(root.get("categoria").get("id"), idCategoria);
            }
        };
    }

    public static Specification<Transacao> porDataInicio(LocalDate dataInicio){
        return new Specification<Transacao>() {
            @Override
            public Predicate toPredicate(Root<Transacao> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (dataInicio == null){
                    return criteriaBuilder.conjunction();
                }
                return criteriaBuilder.greaterThanOrEqualTo(root.get("data"), dataInicio);
            }
        };
    }

    public static Specification<Transacao> porDataFim(LocalDate dataFim) {
        return new Specification<Transacao>() {
            @Override
            public Predicate toPredicate(
                    Root<Transacao> root,
                    CriteriaQuery<?> query,
                    CriteriaBuilder criteriaBuilder) {

                if (dataFim == null) {
                    return criteriaBuilder.conjunction();
                }

                return criteriaBuilder.lessThanOrEqualTo(
                        root.get("data"),
                        dataFim
                );
            }
        };
    }

    }
