package kr.co.cesco.econtract.web.drill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/drill")
public class DrillController {

	@GetMapping(value = { "", "main" })
	public ModelAndView drill(ModelAndView mav) {
		log.info("@Web-Drill=============>");
		mav.setViewName("views/web/drill/web-drill");
		return mav;
	}
	
	@GetMapping(value = "crud")
	public ModelAndView crud(ModelAndView mav) {
		log.info("@CRUD-Drill=============>");
		mav.setViewName("views/web/drill/crud-drill");
		return mav;
	}

}
