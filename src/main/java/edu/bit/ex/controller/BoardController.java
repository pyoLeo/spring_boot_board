package edu.bit.ex.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.bit.ex.page.Criteria;
import edu.bit.ex.page.PageVO;
import edu.bit.ex.service.BoardService;
import edu.bit.ex.vo.BoardVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j	//=@Log4j
@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/")
	public String boardlist(Model model) throws Exception {
		
		log.info("list()......");
		model.addAttribute("list", boardService.getList());
		return "board";
	}
	
	@GetMapping("/paged")
	public String pagingBoardList(Criteria cri, Model model) throws Exception {
		log.info("pagingBoardList()......");
		//log.info(cri);
		System.out.println(cri);
		
		model.addAttribute("list", boardService.getListWithPaging(cri));	//list��ü �����Ǿ� getList()���� ���
		int total = boardService.getTotal(cri);
		log.info("total" + total);
		
		model.addAttribute("pageMaker", new PageVO(cri, total));
		return "paged_list";
	}
	
	@RequestMapping("/write_view")
	public String write_view() {
		log.info("write_view()......");
		return "write_view";
	}
	
	@RequestMapping("/write")
	public String write(BoardVO boardVO, Model model) throws Exception {
		log.info("write()......");
		boardService.writeBoard(boardVO);
		return "redirect:paged";
	}
	
	@RequestMapping("/reply_view")
	public String reply_view(BoardVO boardVO, Model model) {
		log.info("reply_view()......");
		model.addAttribute("reply_view", boardService.getContent(boardVO.getbId()));
		return "reply_view";
	}
	
	@RequestMapping("/reply")
	public String write_reply(BoardVO boardVO, Model model) throws Exception {
		log.info("reply()......");
		boardService.writeReply(boardVO);
		return "redirect:paged";
	}
	
	@RequestMapping("/content_view")
	public String content_view(BoardVO boardVO, Model model) throws Exception {
		log.info("content_view()......");
		model.addAttribute("content_view", boardService.getContent(boardVO.getbId()));
		return "content_view";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("bId") int bId) {
		log.info("delete()......");
		boardService.delete(bId);
		return "redirect:paged";
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO boardVO) throws Exception {
		log.info("modify()......");
		boardService.modify(boardVO);
		return "redirect:paged";
	}
}
