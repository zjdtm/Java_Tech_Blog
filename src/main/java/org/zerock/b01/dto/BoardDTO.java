package org.zerock.b01.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zerock.b01.domain.Member;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

    private Long bno;

    @NotEmpty(message = "제목은 최소 3글자 이상이어야 합니다.")
    @Size(min = 3, max = 100)
    private String title;

    @NotEmpty(message = "내용은 비어있을 수 없습니다.")
    private String content;

    private Member member;

    private LocalDateTime regDate;

    private LocalDateTime modDate;


}
