package edu.job_portal_application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.job_portal_application.entity.Resume;
import edu.job_portal_application.service.SkillService;
import edu.job_portal_application.util.ResponseStructure;

@RestController
@RequestMapping("/skills")
public class SkillController {

	@Autowired
	private SkillService skillService;

	@PostMapping
	public ResponseEntity<ResponseStructure<Resume>> addSkills(@RequestParam int applicantId,@RequestParam String[] skills) {
		return skillService.addSkill(applicantId, skills);
	}

}
