package com.twitter.demo.Services.Mapper;

import com.twitter.demo.Domain.Tweets;
import com.twitter.demo.Dtos.TweetsDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import twitter4j.Status;

@Mapper(componentModel = "spring")
public interface TwitterMapper {
    TwitterMapper INSTANCE = Mappers.getMapper(TwitterMapper.class);

    TweetsDto toDto(Tweets entity, @MappingTarget TweetsDto dto);

    Tweets toEntity(TweetsDto dto, @MappingTarget Tweets entity);


}
