package org.zerock.b01.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.zerock.b01.domain.Board;

public interface BoardSearch {

    Page<Board> searchAll(String keyword, Pageable pageable);

//    Slice<Board> searchSlice(Long bno, String keyword, Pageable pageable);
}
