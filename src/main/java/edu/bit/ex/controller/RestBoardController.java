package edu.bit.ex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import edu.bit.ex.page.Criteria;
import edu.bit.ex.page.PageVO;
import edu.bit.ex.service.BoardService;
import edu.bit.ex.vo.BoardVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/restful/*")
public class RestBoardController {

	@Autowired
	private BoardService boardService;
	
	@GetMapping("/board")
	public ModelAndView list(ModelAndView mav, Criteria cri) {
		log.info("rest/list()......");
		mav.setViewName("rest/rest_list");
		mav.addObject("list", boardService.getListWithPaging(cri));
		int total = boardService.getTotal(cri);
		mav.addObject("pageMaker", new PageVO(cri, total));
		log.info("total : " + total);
		return mav;
	}
	
	@GetMapping("/board/{bId}")
	public ModelAndView rest_content_view(BoardVO boardVO, ModelAndView mav) {
		log.info("rest/content_view()......");
		mav.setViewName("rest/rest_content_view");
		mav.addObject("content_view", boardService.getContent(boardVO.getbId()));
		return mav;
	}
	
	@GetMapping("/write_view")
	public ModelAndView rest_write_view(ModelAndView mav) {
		mav.setViewName("rest/rest_write_view");
		return mav;
	}
	
	@PostMapping("/write")
	public ResponseEntity<String> rest_write(@RequestBody BoardVO boardVO) {
		log.info("rest/write()......");
		
		ResponseEntity<String> entity = null;
		
		try {
			log.info("writing......");
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@GetMapping("/reply_view")
	public ModelAndView rest_reply_view(ModelAndView mav) {
		mav.setViewName("rest/rest_reply_view");
		return mav;
	}
	
	@PostMapping("/reply")
	public ResponseEntity<String> rest_reply(@RequestBody BoardVO boardVO) {
		log.info("rest/reply()......");
		
		ResponseEntity<String> entity = null;
		
		try {
			log.info("replying......");
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@PutMapping("/board/{bId}")
	public ResponseEntity<String> rest_modify(@RequestBody BoardVO boardVO, ModelAndView mav) {
		log.info("rest/modify()......");
		
		ResponseEntity<String> entity = null;
		
		try {
			log.info("updating......");
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
}
