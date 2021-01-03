package kr.co.cesco.econtract.web.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/")
public class MainController {

	@GetMapping(value = { "", "/main" })
	public ModelAndView list(ModelAndView mav) {
		log.info("@Web-Drill=============>");
		mav.setViewName("views/web/drill/web-drill");
		return mav;
	}

}
