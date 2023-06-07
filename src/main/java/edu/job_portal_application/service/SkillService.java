package edu.job_portal_application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.job_portal_application.dao.ApplicantDao;
import edu.job_portal_application.dao.ResumeDao;
import edu.job_portal_application.dao.SkillDao;
import edu.job_portal_application.entity.Applicant;
import edu.job_portal_application.entity.Resume;
import edu.job_portal_application.entity.Skill;
import edu.job_portal_application.util.ResponseStructure;

@Service
public class SkillService {

	@Autowired
	private SkillDao skillDao;

	@Autowired
	private ApplicantDao applicantDao;

	@Autowired
	private ResumeDao resumeDao;

	public ResponseEntity<ResponseStructure<Resume>> addSkill(int applicantId, String[] skills) {
		Applicant applicant = applicantDao.getApplicantById(applicantId);
		if (applicant != null) {
			Resume resume = applicant.getResume();
			if (resume != null) {
				/*
				 * - iterate over the String arrays skills that is received check if the skill
				 * is present with matching name with the user, if present do not add to the
				 * user again, or else create an new skill
				 */
				List<Skill> exSkills = resume.getSkills();
				for (String skill : skills) {
					Skill existingSkill = skillDao.getSkillByName(skill);

					if (existingSkill != null) {
						if (!exSkills.contains(existingSkill)) {
							exSkills.add(existingSkill);
						}
					} else {
						Skill newSkills = new Skill();
						newSkills.setSkillName(skill);
						skillDao.addSkill(newSkills);
						exSkills.add(newSkills);
					}

				}
				/*
				 * setting the exSkills list to the resume
				 */
				resume.setSkills(exSkills);

				resume = resumeDao.saveResume(resume);

				ResponseStructure<Resume> responseStructure = new ResponseStructure<>();
				responseStructure.setStatusCode(HttpStatus.CREATED.value());
				responseStructure.setMessage("Skills add Successfully");
				responseStructure.setData(resume);

				return new ResponseEntity<ResponseStructure<Resume>>(responseStructure, HttpStatus.CREATED);
			} else {
				// throw new ResumeNotFoundByIdException("Failed add skills!!");
				return null;
			}
		} else {
			// throw new ApplicantNotFoundByIdException("Failed to add resume!!");
			return null;
		}

	}

}
