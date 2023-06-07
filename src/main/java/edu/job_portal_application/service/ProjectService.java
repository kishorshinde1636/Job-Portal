package edu.job_portal_application.service;

import java.util.List;
import java.util.Optional;

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

	public ResponseEntity<ResponseStructure<Project>> getProjectById(int projectId) {

		Optional<Project> optional = projectDao.getProjectById(projectId);

		if (optional.isPresent()) {
			ResponseStructure<Project> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("Project found.");
			responseStructure.setData(optional.get());

			return new ResponseEntity<ResponseStructure<Project>>(responseStructure, HttpStatus.FOUND);
		} else {

			return null;
			// throw new ProjectNotFoundByIdException("Failed to find project!!");
		}
	}

	public ResponseEntity<ResponseStructure<Project>> updateProject(int projectId, ProjectDto projectDto) {
		Optional<Project> optional = projectDao.getProjectById(projectId);

		if (optional.isPresent()) {
			Project project = this.modelMapper.map(projectDto, Project.class);
			project.setProjectId(projectId);
			project = projectDao.addProject(project);

			ResponseStructure<Project> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Project updated successfully.");
			responseStructure.setData(optional.get());

			return new ResponseEntity<ResponseStructure<Project>>(responseStructure, HttpStatus.OK);

		} else {
			return null;
			// throw new ProjectNotFoundByIdException("Failed to update Project!!");
		}

	}

	public ResponseEntity<ResponseStructure<Project>> deleteProject(int projectId, int applicantId) {

		Optional<Project> optional = projectDao.getProjectById(projectId);

		if (optional.isPresent()) {
			Applicant applicant = applicantDao.getApplicantById(applicantId);

			if (applicant != null) {
				Resume resume = applicant.getResume();
				if (resume != null) {
					resume.getProjects().remove(optional.get());
					resumeDao.saveResume(resume);

				}
				projectDao.deleteProject(optional.get());

				ResponseStructure<Project> responseStructure = new ResponseStructure<>();
				responseStructure.setStatusCode(HttpStatus.OK.value());
				responseStructure.setMessage("Project deleted successfully.");
				responseStructure.setData(optional.get());

				return new ResponseEntity<ResponseStructure<Project>>(responseStructure, HttpStatus.OK);

			} else {
				return null;
				// throw new ApplicantNotfoundByIdException("Failed to delete Project!!");
			}
		} else {
			return null;
			// throw new ProjectNotFoundByIdException("Failec to delete Project!!");
		}
	}

}
