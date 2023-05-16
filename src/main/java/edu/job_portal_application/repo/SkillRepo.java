package edu.job_portal_application.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.job_portal_application.entity.Skill;

public interface SkillRepo extends JpaRepository<Skill, Integer> {
	
	@Query(value = "select s from Skill s where s.skillName=?1")
	public Optional<Skill> getSkillByName(String skillName);

}
