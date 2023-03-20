package org.zerock.b01.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.b01.dto.*;
import org.zerock.b01.service.BoardService;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/")
    public String BoardList(PageRequestDTO pageRequestDTO, Model model){

        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);

        log.info(String.valueOf(responseDTO));

        model.addAttribute("responseDTO", responseDTO);

        return "/list";

    }

    @GetMapping("/write")
    public String getWrite(Model model) {
        model.addAttribute("boardForm", new BoardDTO());
        return "/write";
    }

    @PostMapping("/write")
    public String registerBoard(@Valid BoardDTO boardDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){

            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            return "redirect:/write";
        }

        boardService.register(boardDTO);

        return "redirect:";
    }

}
