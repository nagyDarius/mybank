package org.finance.mybank.mappers.converters;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.metadata.Type;
import org.finance.mybank.dto.PostingDTO;
import org.finance.mybank.persistence.posting.PostingEntity;
import org.springframework.stereotype.Component;

@Component
public class PostingToDtoConverter extends CustomConverter<PostingEntity, PostingDTO> {

	@Override
	public PostingDTO convert(PostingEntity postingEntity, Type<? extends PostingDTO> type) {
		return new PostingDTO(postingEntity.getId(), postingEntity.getAmount(), postingEntity.getFrom().getId(), postingEntity.getTo().getId());
	}
}
