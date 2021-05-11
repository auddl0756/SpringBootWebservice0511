package com.roon.board.web.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SampleResponseDtoTest {

    @Test
    public void 롬복_테스트(){
        String name="test";
        int amount=1000;

        SampleResponseDto dto = new SampleResponseDto(name,amount);

        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
