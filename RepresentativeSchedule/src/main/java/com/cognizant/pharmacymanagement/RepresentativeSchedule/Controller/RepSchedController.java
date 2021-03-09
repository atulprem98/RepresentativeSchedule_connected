package com.cognizant.pharmacymanagement.RepresentativeSchedule.Controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


import com.cognizant.pharmacymanagement.RepresentativeSchedule.Model.RepSchedule;
import com.cognizant.pharmacymanagement.RepresentativeSchedule.Service.RepScheduleService;
import com.cognizant.pharmacymanagement.RepresentativeSchedule.feignClient.StockFeignClient;

@RestController
public class RepSchedController {
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }
	
	@Autowired
	private RepScheduleService service;
		
	@RequestMapping(value ="/viewSchedule", method = RequestMethod.GET)
	public ModelAndView showdate() {
		return new ModelAndView("viewSchedule");
	}
	
	@GetMapping("/RepSchedule")
	public ModelAndView showschedule(@RequestParam String startdate,ModelMap model) throws ParseException{    
		model.put("details",service.returnschedule(startdate));
		return new ModelAndView("returnSchedule");
	}
	
}
