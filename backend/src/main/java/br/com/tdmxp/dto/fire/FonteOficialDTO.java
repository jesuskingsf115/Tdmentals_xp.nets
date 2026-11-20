package br.com.tdmxp.dto.fire;

import java.time.LocalDateTime;

public record FonteOficialDTO(
        Long id,
        String nome,
        String url,
        String orgao,
        String esfera,
        String estado,
        String cidade,
        Boolean verificada,
        Boolean ativa,
        LocalDateTime dataCadastro
) {
}
