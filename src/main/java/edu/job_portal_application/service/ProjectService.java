package edu.job_portal_application.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.job_portal_application.dao.ApplicantDao;
import edu.job_portal_application.dao.ProjectDao;
import edu.job_portal_application.dao.ResumeDao;
import edu.job_portal_application.dto.ProjectDto;
import edu.job_portal_application.entity.Applicant;
import edu.job_portal_application.entity.Project;
import edu.job_portal_application.entity.Resume;
import edu.job_portal_application.util.ResponseStructure;

@Service
public class ProjectService {

	@Autowired
	private ProjectDao projectDao;

	@Autowired
	private ApplicantDao applicantDao;

	@Autowired
	private ResumeDao resumeDao;

	@Autowired
	private ModelMapper modelMapper;

	public ResponseEntity<ResponseStructure<Resume>> addProject(int applicantId, ProjectDto projectDto) {
		Applicant applicant = applicantDao.getApplicantById(applicantId);

		if (applicant != null) {
			Resume resume = applicant.getResume();
			if (resume != null) {
				List<Project> existingProject = resume.getProjects();
				Project project = this.modelMapper.map(projectDto, Project.class);

				project = projectDao.addProject(project);
				existingProject.add(project);
				resumeDao.saveResume(resume);

				ResponseStructure<Resume> responseStructure = new ResponseStructure<>();
				responseStructure.setStatusCode(HttpStatus.CREATED.value());
				responseStructure.setMessage("Project added successfully!!");
				responseStructure.setData(resume);

				return new ResponseEntity<ResponseStructure<Resume>>(responseStructure, HttpStatus.CREATED);

			} else {
				return null;
				// throw new ReumseNotFoundByIdException("Failed to add Project!!");
			}

		} else {
			return null;
			// throw new ApplicantNotFoundByIdException("Failed to add Project!!");
		}

	}

}
